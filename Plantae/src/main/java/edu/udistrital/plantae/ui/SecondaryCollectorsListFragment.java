package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.*;
import android.widget.CheckBox;
import android.widget.ListView;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.persistencia.ColectorSecundarioDao;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.ui.adapter.ListItemCheckAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hghar on 11/18/14.
 */
public class SecondaryCollectorsListFragment extends ListFragment implements View.OnClickListener {
    static final int SECONDARY_COLLECTOR_CREATION_REQUEST = 201;
    static final int SECONDARY_COLLECTOR_EDIT_REQUEST = 202;
    private ColectorSecundarioDao colectorSecundarioDao;
    private Long[] itemsSelected;
    private OnSecondaryCollectorSelectedListener secondaryCollectorSelectedListener;
    private ActionMode mActionMode;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            secondaryCollectorSelectedListener = (OnSecondaryCollectorSelectedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnSecondaryCollectorSelectedListener");
        }
    }

    public interface OnSecondaryCollectorSelectedListener {
        public void onSecondaryCollectorAdded(ColectorSecundario colectorSecundario);
        public void onSecondaryCollectorRemoved(ColectorSecundario colectorSecundario);
        public void onActionModeFinished();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_secondary_collectors_list, container, false);
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        colectorSecundarioDao = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession().getColectorSecundarioDao();
        loadSecondaryCollectors();
        mActionMode = ((ActionBarActivity)getActivity()).startSupportActionMode(mActionModeCallback);
        mActionMode.setTitle(getString(R.string.select_secondary_collectors));
        return rootView;
    }

    private void loadSecondaryCollectors() {
        List<ColectorSecundario> secondaryCollectors = colectorSecundarioDao.loadAll();
        List<ListItem> listItems = new ArrayList<>();
        if (itemsSelected == null) {
            itemsSelected = new Long[secondaryCollectors.size()];
        }
        if (itemsSelected.length != secondaryCollectors.size()) {
            Long[] itemsSelectedLast = itemsSelected;
            itemsSelected = new Long[secondaryCollectors.size()];
            System.arraycopy(itemsSelectedLast, 0, itemsSelected, 0, itemsSelectedLast.length);
        }
        int pos = 0;
        for (ColectorSecundario colectorSecundario:secondaryCollectors) {
            listItems.add(new ListItem(colectorSecundario.getId(), colectorSecundario.getPersona().getNombres() + " " + colectorSecundario.getPersona().getApellidos(), colectorSecundario.getPersona().getInstitucion(), "0",false,itemsSelected[pos] != null));
            pos++;
        }
        setListAdapter(new ListItemCheckAdapter(getActivity().getApplicationContext(), R.layout.list_item_check, listItems));
    }

    public void updateSelectedSecondaryCollectors(Long[] itemsSelected) {
        this.itemsSelected = itemsSelected;
    }

    private void clearItemsSelected() {
        itemsSelected = new Long[itemsSelected.length];
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SECONDARY_COLLECTOR_CREATION_REQUEST && resultCode == Activity.RESULT_OK){
            loadSecondaryCollectors();
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
        ListItem listItem = ((ListItem)getListAdapter().getItem(position));
        if (itemsSelected[position] != null && itemsSelected[position].equals(listItem.getId())){
            secondaryCollectorSelectedListener.onSecondaryCollectorRemoved(colectorSecundarioDao.load(listItem.getId()));
            itemsSelected[position] = null;
        }else{
            itemsSelected[position]= listItem.getId();
            secondaryCollectorSelectedListener.onSecondaryCollectorAdded(colectorSecundarioDao.load(listItem.getId()));
        }
        mActionMode.invalidate();
    }

    private void deleteSecondaryCollectors() {
        for (Long id : itemsSelected) {
            ColectorSecundario colectorSecundario = colectorSecundarioDao.load(id);
            if (colectorSecundario != null) {
                colectorSecundarioDao.delete(colectorSecundario);
            }
        }
    }

    private void editSecondaryCollector(Long id){
        Intent editSecondaryCollectorIntent = new Intent(getActivity().getApplicationContext(), CreateSecondaryCollectorActivity.class);
        editSecondaryCollectorIntent.putExtra("secondaryCollector", id);
        startActivityForResult(editSecondaryCollectorIntent, SECONDARY_COLLECTOR_EDIT_REQUEST);
    }

    public void destroyActionMode() {
        mActionMode.finish();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.secondary_collector_list_contextual, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            int itemsSelected = itemsSelectedCount();
            menu.findItem(R.id.action_add_secondary_collector).setVisible(true);
            menu.findItem(R.id.action_edit_secondary_collector).setVisible(itemsSelected == 1);
            menu.findItem(R.id.action_delete_secondary_collectors).setVisible(itemsSelected >= 1);
            return true;
        }

        @Override
        public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_add_secondary_collector:
                    // call projectcreateactivity
                    Intent createSecondaryCollectorIntent = new Intent(getActivity().getApplicationContext(), CreateSecondaryCollectorActivity.class);
                    getActivity().startActivityForResult(createSecondaryCollectorIntent, SECONDARY_COLLECTOR_CREATION_REQUEST);
                    return true;
                case R.id.action_delete_secondary_collectors:
                    // delete selected items
                    new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.delete_secondary_collector_confirmation_message)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteSecondaryCollectors();
                                    clearItemsSelected();
                                    loadSecondaryCollectors();
                                    actionMode.invalidate();
                                }

                            })
                            .setNegativeButton(R.string.no, null)
                            .show();
                    return true;
                case R.id.action_edit_secondary_collector:
                    // Load travel data and show CreateActivityProject
                    for (Long anItemsSelected : itemsSelected) {
                        if (anItemsSelected != null) {
                            editSecondaryCollector(anItemsSelected);
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
            loadSecondaryCollectors();
            mActionMode = null;
            secondaryCollectorSelectedListener.onActionModeFinished();
        }
    };
}