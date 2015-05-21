package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.*;
import android.widget.ImageView;
import android.widget.ListView;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.logicadominio.recoleccion.ViajeColectorSecundario;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.ViajeDao;
import edu.udistrital.plantae.ui.adapter.ListItemImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class TripListFragment extends ListFragment implements View.OnClickListener{

    private Long colectorPrincipalID;
    private ViajeDao viajeDao;
    private Long[] itemsSelected;
    private ActionMode actionMode;
    private DaoSession daoSession;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.manage_travels));
		View rootView=inflater.inflate(R.layout.fragment_trip_list, container, false);
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        actionMode = null;
        colectorPrincipalID = getArguments().getLong("colectorPrincipal");
        daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();
        viajeDao = daoSession.getViajeDao();
        loadTravels();
        setHasOptionsMenu(true);
		return rootView;
	}

    /**private ActionMode actionMode;
     * Cargar los viajes del Colector principal loggeado o un aviso de agregar un nuevo viaje
     */
    private void loadTravels(){
        ((MainActivity)getActivity()).updateNavDrawerList();
        List<Viaje> viajes = viajeDao.queryBuilder().where(ViajeDao.Properties.ColectorPrincipalID.eq(colectorPrincipalID)).list();
        List<ListItem> listItems = new ArrayList<ListItem>(viajes.size());
        for (Viaje viaje : viajes) {
            listItems.add(new ListItem(viaje.getId(), viaje.getNombre(), viaje.getProyecto().getNombre(), R.drawable.compass_primary, "0", false));
        }
        itemsSelected = new Long[viajes.size()];
        setListAdapter(new ListItemImageAdapter(getActivity().getApplicationContext(), listItems, this));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = getActivity().getApplicationContext();
        switch (item.getItemId()){
            case R.id.action_add:
                // call travelcreateactivity
                Intent createTravelIntent = new Intent(context, CreateTripActivity.class);
                createTravelIntent.putExtra("colectorPrincipal", colectorPrincipalID);
                startActivityForResult(createTravelIntent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK){
            loadTravels();
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        editTravel(((ListItem) l.getItemAtPosition(position)).getId());
    }

    @Override
    public void onClick(View v) {
        int position = getListView().getPositionForView(v);
        ImageView imageView = (ImageView) v;
        ListItem listItem = ((ListItem)getListAdapter().getItem(position));
        if (itemsSelected[position] != null && itemsSelected[position].equals(listItem.getId())){
            itemsSelected[position] = null;
            imageView.setImageResource(R.drawable.compass_primary);
            actionMode.invalidate();
            if (itemsSelectedCount() == 0 && actionMode != null){
                actionMode.finish();
            }
        }else{
            itemsSelected[position]= listItem.getId();
            imageView.setImageResource(R.drawable.checkmark_primary);
            if (actionMode == null) {
                actionMode = ((ActionBarActivity)getActivity()).startSupportActionMode(new TravelListMultiSelectionActionMode());
            }else{
                actionMode.invalidate();
            }
        }
        if (itemsSelectedCount() > 1){
            actionMode.invalidate();
        }
    }

    private void deleteTravels(){
        for (Long id : itemsSelected) {
            Viaje viaje = viajeDao.load(id);
            if (viaje != null) {
                for (ViajeColectorSecundario viajeColectorSecundario:viaje.getColectoresSecundarios()){
                    daoSession.getViajeColectorSecundarioDao().delete(viajeColectorSecundario);
                }
                for (Especimen especimen:viaje.getEspecimenes()) {
                    // TODO DELETE ALL SPECIMEN CONTENTS
                    daoSession.getEspecimenDao().delete(especimen);
                }
                viajeDao.delete(viaje);
            }
        }
    }

    private void editTravel(Long id){
        Intent editTravelIntent = new Intent(getActivity().getApplicationContext(), CreateTripActivity.class);
        editTravelIntent.putExtra("colectorPrincipal", colectorPrincipalID);
        editTravelIntent.putExtra("viaje", id);
        startActivityForResult(editTravelIntent, 0);
    }

    private class TravelListMultiSelectionActionMode extends ListMultiSelectionActionMode {

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_delete:
                    // delete selected items
                    new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.delete_travel_confirmation_message)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteTravels();
                                    actionMode.finish();
                                }

                            })
                            .setNegativeButton(R.string.no, null)
                            .show();
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
            loadTravels();
        }
    }
}
