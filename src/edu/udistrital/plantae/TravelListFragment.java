package edu.udistrital.plantae;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.ViajeDao;

import java.util.ArrayList;
import java.util.List;

public class TravelListFragment extends ListFragment implements View.OnClickListener{

    private Long colectorPrincipalID;
    private ViajeDao viajeDao;
    private Long[] itemsSelected;
    private ActionMode actionMode;
    private Animation checkToMiddle;
    private Animation checkFromMiddle;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_travel_list, container, false);
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        actionMode = null;
        colectorPrincipalID = getArguments().getLong("colectorPrincipal");
        viajeDao = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession().getViajeDao();
        checkToMiddle = AnimationUtils.loadAnimation(getActivity(), R.anim.check_to_middle);
        checkFromMiddle = AnimationUtils.loadAnimation(getActivity(), R.anim.check_from_middle);
        loadTravels();
        setHasOptionsMenu(true);
		return rootView;
	}

    /**
     * Cargar los viajes del Colector principal loggeado o un aviso de agregar un nuevo viaje
     */
    private void loadTravels(){
        ((MainActivity)getActivity()).updateNavDrawerList();
        List<Viaje> viajes = viajeDao.queryBuilder().where(ViajeDao.Properties.ColectorPrincipalID.eq(colectorPrincipalID)).list();
        List<ListItem> listItems = new ArrayList<ListItem>(viajes.size());
        for (Viaje viaje : viajes) {
            listItems.add(new ListItem(viaje.getId(), viaje.getNombre(), viaje.getProyecto().getNombre(), R.drawable.ic_action_location_map, "0", false));
        }
        itemsSelected = new Long[viajes.size()];
        setListAdapter(new ListItemAdapter(getActivity().getApplicationContext(), listItems, this));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.travel_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = getActivity().getApplicationContext();
        switch (item.getItemId()){
            case R.id.action_add:
                // call travelcreateactivity
                Intent createTravelIntent = new Intent(context, CreateTravelActivity.class);
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
    public void onClick(View v) {
        int position = getListView().getPositionForView(v);
        ImageView imageView = (ImageView) v;
        imageView.clearAnimation();
        imageView.setAnimation(checkToMiddle);
        imageView.startAnimation(checkToMiddle);
        ListItem listItem = ((ListItem)getListAdapter().getItem(position));
        if (itemsSelected[position] != null && itemsSelected[position].equals(listItem.getId())){
            itemsSelected[position] = null;
            imageView.setImageResource(R.drawable.ic_action_location_map);
            imageView.clearAnimation();
            imageView.setAnimation(checkFromMiddle);
            imageView.startAnimation(checkFromMiddle);
            actionMode.invalidate();
            if (itemsSelectedCount() == 0 && actionMode != null){
                actionMode.finish();
            }
        }else{
            itemsSelected[position]= listItem.getId();
            imageView.setImageResource(R.drawable.ic_action_navigation_accept);
            imageView.clearAnimation();
            imageView.setAnimation(checkFromMiddle);
            imageView.startAnimation(checkFromMiddle);
            if (actionMode == null) {
                actionMode = ((ActionBarActivity)getActivity()).startSupportActionMode(new ListMultiSelectionActionMode());
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
                viajeDao.delete(viaje);
            }
        }
    }

    private void editTravel(Long id){
        Viaje viaje = viajeDao.load(id);
        Intent editTravelIntent = new Intent(getActivity().getApplicationContext(), CreateTravelActivity.class);
        editTravelIntent.putExtra("viaje", viaje.getId());
        startActivityForResult(editTravelIntent, 0);
        loadTravels();
    }

    private final class ListMultiSelectionActionMode implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.travel_list_contextual, menu);
            return true;
        }

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
                case R.id.action_edit:
                    // Load travel data and show CreateActivityTravel
                    for (int i = 0; i < itemsSelected.length; i++) {
                        if (itemsSelected[i] != null){
                            editTravel(itemsSelected[i]);
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
            loadTravels();
        }
    }
}
