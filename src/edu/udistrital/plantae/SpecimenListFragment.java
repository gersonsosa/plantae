package edu.udistrital.plantae;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.*;
import android.widget.ImageView;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.EspecimenDao;
import edu.udistrital.plantae.persistencia.ViajeDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerson Sosa on 5/5/14.
 */
public class SpecimenListFragment extends ListFragment implements View.OnClickListener{
    private Viaje viaje;
    private ColectorPrincipal colectorPrincipal;
    private EspecimenDao especimenDao;
    private Long[] itemsSelected;
    private ActionMode actionMode;
    private ViajeDao viajeDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_specimen_list, container, false);
        Long viajeID = getArguments().getLong("viaje");
        Long colectorPrincipalID = getArguments().getLong("colectorPrincipal");
        DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();
        colectorPrincipal = daoSession.getColectorPrincipalDao().load(colectorPrincipalID);
        // Cargar los especímenes del viaje seleccionado por defec  to o un aviso de agregar un nuevo especimen
        viajeDao = daoSession.getViajeDao();
        viaje = viajeDao.loadDeep(viajeID);
        getActivity().setTitle(viaje.getNombre());
        loadSpecimens();
        actionMode = null;
        especimenDao = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession().getEspecimenDao();
        setHasOptionsMenu(true);
        return rootView;
    }

    private void loadSpecimens() {
        viaje = viajeDao.loadDeep(viaje.getId());
        viaje.resetEspecimenes();
        List<Especimen> especimens = viaje.getEspecimenes();
        if (especimens.size()>0){
            List<ListItem> listItems = new ArrayList<ListItem>(especimens.size());
            for (Especimen especimen : especimens) {
                listItems.add(new ListItem(especimen.getId(), especimen.getNumeroDeColeccion(), especimen.getDescripcionEspecimen(), R.drawable.ic_action_location_map, "0", especimen.getLocalidad() != null));
            }
            itemsSelected = new Long[listItems.size()];
            setListAdapter(new ListItemAdapter(getActivity().getApplicationContext(), listItems, this));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.specimen_list, menu);
        if (colectorPrincipal != null && colectorPrincipal.getTipoCapturaDatos() > 0) {
            menu.findItem(R.id.action_add_detailed).setVisible(true);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK){
            loadSpecimens();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                // call CreateSpecimenActivity
                Intent createSpecimenIntent = new Intent(getActivity().getApplicationContext(), CreateSpecimenActivity.class);
                createSpecimenIntent.putExtra("viaje", viaje.getId());
                createSpecimenIntent.putExtra("specimenType", SpecimenPagesAdapter.SPECIMEN_SINGLE);
                startActivityForResult(createSpecimenIntent, 0);
                return true;
            case R.id.action_add_detailed:
                // call CreateSpecimenActivity
                Intent createDetailedSpecimenIntent = new Intent(getActivity().getApplicationContext(), CreateSpecimenActivity.class);
                createDetailedSpecimenIntent.putExtra("viaje", viaje.getId());
                createDetailedSpecimenIntent.putExtra("specimenType", SpecimenPagesAdapter.SPECIMEN_DETAILED);
                startActivityForResult(createDetailedSpecimenIntent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            imageView.setImageResource(R.drawable.ic_action_location_map);
            actionMode.invalidate();
            if (itemsSelectedCount() == 0 && actionMode != null){
                actionMode.finish();
            }
        }else{
            itemsSelected[position]= listItem.getId();
            imageView.setImageResource(R.drawable.ic_action_navigation_accept);
            if (actionMode == null) {
                actionMode = ((ActionBarActivity)getActivity()).startSupportActionMode(new SpecimenListMultiSelectionActionMode());
            }else{
                actionMode.invalidate();
            }
        }
        if (itemsSelectedCount() > 1){
            actionMode.invalidate();
        }
    }

    private void deleteSpecimens(){
        // TODO delete specimens
        for (Long id : itemsSelected) {
            Especimen especimen = especimenDao.load(id);
            if (especimen != null) {
                especimenDao.delete(especimen);
            }
        }
    }

    private void editSpecimen(Long id){
        // TODO edit selected especimen
        Especimen especimen = especimenDao.load(id);
        Intent editEspecimen = new Intent(getActivity().getApplicationContext(), CreateSpecimenActivity.class);
        editEspecimen.putExtra("viaje", viaje.getId());
        startActivityForResult(editEspecimen, 0);
    }

    private class SpecimenListMultiSelectionActionMode extends ListMultiSelectionActionMode {

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            if (itemsSelectedCount() == 1){
                MenuItem item = menu.findItem(R.id.action_edit);
                item.setVisible(true);
                return true;
            } else {
                MenuItem item = menu.findItem(R.id.action_edit);
                item.setVisible(false);
                return true;
            }
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_delete:
                    // delete selected items
                    new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.delete_specimen_confirmation_message)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteSpecimens();
                                    actionMode.finish();
                                }

                            })
                            .setNegativeButton(R.string.no, null)
                            .show();
                    return true;
                case R.id.action_edit:
                    // Load travel data and show CreateActivityTravel
                    for (Long anItemsSelected : itemsSelected) {
                        if (anItemsSelected != null) {
                            editSpecimen(anItemsSelected);
                            actionMode.finish();
                        }
                    }
                    return true;
                default:
                    mode.finish();
                    return true;
            }
        }
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // update viajes list
            int length = itemsSelected.length;
            for (int i = 0; i < length; i++) {
                getListView().setItemChecked(i, false);
            }
            if (mode.equals(actionMode)){
                actionMode = null;
                itemsSelected = new Long[length];
            }
            loadSpecimens();
        }

    }
}