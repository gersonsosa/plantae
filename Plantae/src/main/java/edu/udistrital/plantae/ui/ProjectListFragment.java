package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.recoleccion.Proyecto;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.ProyectoDao;
import edu.udistrital.plantae.ui.adapter.ListItemImageAdapter;

/**
 * Created by hghar on 11/4/14.
 */
public class ProjectListFragment extends ListFragment implements View.OnClickListener {

    static final int PROJECT_CREATION_REQUEST = 1;
    static final int PROJECT_EDIT_REQUEST = 2;
    private Long colectorPrincipalID;
    private ProyectoDao proyectoDao;
    private Long[] itemsSelected;
    private OnProjectSelectedListener projectSelectedListener;
    private ActionMode mActionMode;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            projectSelectedListener = (OnProjectSelectedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnProjectSelectedListener");
        }
    }

    public interface OnProjectSelectedListener {
        void onProjectSelected(Proyecto proyecto);
        void onActionModeFinished();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project_list, container, false);
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        colectorPrincipalID = getArguments().getLong("colectorPrincipal");
        proyectoDao = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession().getProyectoDao();
        loadProjects();
        mActionMode = ((AppCompatActivity)getActivity()).startSupportActionMode(mActionModeCallback);
        mActionMode.setTitle(getString(R.string.select_project));
        return rootView;
    }

    private void loadProjects() {
        List<Proyecto> proyectos = proyectoDao.queryBuilder().where(ProyectoDao.Properties.ColectorPrincipalID.eq(colectorPrincipalID)).list();
        List<ListItem> listItems = new ArrayList<ListItem>(proyectos.size());
        for (Proyecto proyecto:proyectos) {
            listItems.add(new ListItem(proyecto.getId(), proyecto.getNombre(), proyecto.getAgenciaEjecutora(), R.drawable.adventure_primary, Integer.toString(proyecto.getViajes().size()),false));
        }
        itemsSelected = new Long[proyectos.size()];
        setListAdapter(new ListItemImageAdapter(getActivity().getApplicationContext(), listItems, this));
    }

    private void clearItemsSelected() {
        itemsSelected = new Long[itemsSelected.length];
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PROJECT_CREATION_REQUEST && resultCode == Activity.RESULT_OK){
            loadProjects();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private int itemsSelectedCount(){
        int i = 0;
        for (Long anItemsSelected : itemsSelected) {
            if (anItemsSelected != null) {
                i++;
            }
        }
        return i;
    }

    @Override
    public void onClick(View v) {
        int position = getListView().getPositionForView(v);
        ImageView imageView = (ImageView) v;
        ListItem listItem = ((ListItem)getListAdapter().getItem(position));
        if (itemsSelected[position] != null && itemsSelected[position].equals(listItem.getId())){
            itemsSelected[position] = null;
            imageView.setImageResource(R.drawable.adventure_primary);
        }else{
            itemsSelected[position]= listItem.getId();
            imageView.setImageResource(R.drawable.checkmark_primary);
            if (itemsSelectedCount() == 1) {
                projectSelectedListener.onProjectSelected(proyectoDao.load(listItem.getId()));
            }
        }
        mActionMode.invalidate();
    }

    private void deleteProjects() {
        for (Long id : itemsSelected) {
            Proyecto proyecto = proyectoDao.load(id);
            if (proyecto != null) {
                proyectoDao.delete(proyecto);
            }
        }
    }

    private void editProject(Long id){
        Intent editProjectIntent = new Intent(getActivity().getApplicationContext(), CreateProjectActivity.class);
        editProjectIntent.putExtra("proyecto", id);
        editProjectIntent.putExtra("colectorPrincipal", colectorPrincipalID);
        startActivityForResult(editProjectIntent, PROJECT_EDIT_REQUEST);
    }

    public void destroyActionMode() {
        mActionMode.finish();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.project_list_contextual, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            int itemsSelected = itemsSelectedCount();
            menu.findItem(R.id.add_project).setVisible(itemsSelected == 0);
            menu.findItem(R.id.edit_project).setVisible(itemsSelected == 1);
            menu.findItem(R.id.delete_projects).setVisible(itemsSelected >= 1);
            return true;
        }

        @Override
        public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.add_project:
                    // call projectcreateactivity
                    Intent createProjectIntent = new Intent(getActivity().getApplicationContext(), CreateProjectActivity.class);
                    createProjectIntent.putExtra("colectorPrincipal", colectorPrincipalID);
                    startActivityForResult(createProjectIntent, PROJECT_CREATION_REQUEST);
                    return true;
                case R.id.delete_projects:
                    // delete selected items
                    new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.delete_project_confirmation_message)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteProjects();
                                    clearItemsSelected();
                                    loadProjects();
                                    actionMode.invalidate();
                                }

                            })
                            .setNegativeButton(R.string.no, null)
                            .show();
                    return true;
                case R.id.edit_project:
                    // Load travel data and show CreateActivityProject
                    for (Long anItemsSelected : itemsSelected) {
                        if (anItemsSelected != null) {
                            editProject(anItemsSelected);
                        }
                    }
                    return true;
                default:
                    actionMode.finish();
                    return true;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            // update viajes list
            int length = itemsSelected.length;
            for (int i = 0; i < length; i++) {
                getListView().setItemChecked(i, false);
            }
            itemsSelected = new Long[length];
            loadProjects();
            mActionMode = null;
            projectSelectedListener.onActionModeFinished();
        }
    };
}
