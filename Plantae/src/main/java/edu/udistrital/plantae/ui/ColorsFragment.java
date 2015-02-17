package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.Flor;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fruto;
import edu.udistrital.plantae.logicadominio.datosespecimen.Hoja;
import edu.udistrital.plantae.logicadominio.datosespecimen.Inflorescencia;
import edu.udistrital.plantae.logicadominio.datosespecimen.MuestraAsociada;
import edu.udistrital.plantae.logicadominio.datosespecimen.Tallo;
import edu.udistrital.plantae.logicadominio.listasparametros.Colores;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.ui.adapter.ListItemAdapter;
import edu.udistrital.plantae.ui.adapter.ListItemCheckAdapter;

/**
 * Created by hghar on 12/4/14.
 */
public class ColorsFragment extends ListFragment {

    public static final int COLOR_CREATION_REQUEST = 16;
    public static final int COLOR_EDIT_REQUEST = 26;
    private OnColorChangedListener colorChangedListener;
    private EspecimenDTO especimenDTO;
    private boolean[] itemsSelected;
    private ActionMode mActionMode;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            colorChangedListener = (OnColorChangedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnColorChangedListener");
        }
    }

    public interface OnColorChangedListener {

        public void onColorAdded(ColorEspecimenDTO colorEspecimen, String plantOrgan);
        public void onColorRemoved(ColorEspecimenDTO colorEspecimen, String plantOrgan);
        public void onActionModeFinished(ColorsFragment colorsFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_colors, container, false);
        Bundle bundle = getArguments();
        especimenDTO = bundle.getParcelable("especimen");
        loadColors();
        ColorEspecimenDao colorEspecimenDao = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession().getColorEspecimenDao();
        mActionMode = ((ActionBarActivity)getActivity()).startSupportActionMode(mActionModeCallback);
        mActionMode.setTitle(getString(R.string.colors_title));
        return rootView;
    }

    private void loadColors() {
        HashMap<String, ColorEspecimenDTO> colores = especimenDTO.getColores();
        List<ListItem> listItems = new ArrayList<>();
        if (itemsSelected == null) {
            itemsSelected = new boolean[colores.size()];
        }
        if (itemsSelected.length < colores.size()) {
            boolean[] itemsSelectedLast = itemsSelected;
            itemsSelected = new boolean[colores.size()];
            System.arraycopy(itemsSelectedLast, 0, itemsSelected, 0, itemsSelectedLast.length);
        }
        for (Map.Entry<String, ColorEspecimenDTO> entry : colores.entrySet()) {
            listItems.add(new ListItem(0l, entry.getValue().getNombre(), entry.getKey().equals("especimen")?entry.getValue().getDescripcion():entry.getKey(), "0", false, false));
        }
        setListAdapter(new ListItemAdapter(getActivity().getApplicationContext(), listItems));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        itemsSelected[position] = true;
        mActionMode.invalidate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == COLOR_CREATION_REQUEST && resultCode == Activity.RESULT_OK ) {
            ColorEspecimenDTO colorEspecimen = data.getParcelableExtra("colorEspecimen");
            String plantOrgan = data.getStringExtra("plantOrgan");
            especimenDTO.getColores().put(plantOrgan, colorEspecimen);
            addColorToOrgan(colorEspecimen, plantOrgan);
            loadColors();
            colorChangedListener.onColorAdded(colorEspecimen, plantOrgan);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addColorToOrgan(ColorEspecimenDTO colorEspecimen, String plantOrgan) {
        String[] plantOrgans = getResources().getStringArray(R.array.plant_organs);
        if (plantOrgan.equals(plantOrgans[0])) { // Flower Corolla
            especimenDTO.setColorDeLaCorola(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[1])) { //Flower Calyx
            especimenDTO.setColorDelCaliz(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[2])) { //Flower Gineceo
            especimenDTO.setColorDelGineceo(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[3])) { //Flower Stamens
            especimenDTO.setColorDeLosEstambres(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[4])) { //Flower Stigmata
            especimenDTO.setColorDeLosEstigmas(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[5])) { //Flower Pistiliodios
            especimenDTO.setColorDeLosPistiliodios(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[6])) { //Fruit Endocarp
            especimenDTO.setColorDelEndocarpio(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[7])) { //Fruit Excarp
            especimenDTO.setColorDelExocarpio(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[8])) { //Inflorescence Flower
            especimenDTO.setColorDeLaInflorescenciaEnFlor(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[9])) { //Inflorescence Fruit
            especimenDTO.setColorDeLaInflorescenciaEnFruto(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[10])) { //Leaves
            especimenDTO.setColorDeLasHojas(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[11])) { //Leaves Petiole
            especimenDTO.setColorDelPeciolo(colorEspecimen);
        }else if (plantOrgan.equals(plantOrgans[12])) { //Stem
            especimenDTO.setColorDelTallo(colorEspecimen);
        }
    }

    private int itemsSelectedCount(){
        int i = 0;
        for (boolean checked : itemsSelected) {
            if (checked) {
                i++;
            }
        }
        return i;
    }

    private void clearItemsSelected() {
        itemsSelected = new boolean[itemsSelected.length];
    }

    public void destroyActionMode() {
        mActionMode.finish();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.colors_list_contextual, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            int itemsSelected = itemsSelectedCount();
            menu.findItem(R.id.action_add_color).setVisible(true);
            menu.findItem(R.id.action_edit_color).setVisible(itemsSelected == 1);
            menu.findItem(R.id.action_delete_colors).setVisible(itemsSelected >= 1);
            return true;
        }

        @Override
        public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_add_color:
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    getActivity().startActivityForResult(createColorIntent, COLOR_CREATION_REQUEST);
                    return true;
                case R.id.action_delete_colors:
                    // delete selected items
                    new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.delete_colors_confirmation_message)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteColors();
                                    clearItemsSelected();
                                    loadColors();
                                    actionMode.invalidate();
                                }

                            })
                            .setNegativeButton(R.string.no, null)
                            .show();
                    return true;
                case R.id.action_edit_color:
                    // Load data and show CreateActivity
                    editColor(getSelectedItemPosition());
                    return true;
                default:
                    actionMode.finish();
                    return true;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            // update list
            int length = itemsSelected.length;
            for (int i = 0; i < length; i++) {
                getListView().setItemChecked(i, false);
            }
            itemsSelected = new boolean[length];
            loadColors();
            mActionMode = null;
            callbackDestroyActionMode();
        }
    };

    private void editColor(Integer anItemsSelected) {
        Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
        //createColorIntent.putExtra("color", (ColorEspecimen) especimenDTO.getColores().values().toArray()[anItemsSelected]);
        getActivity().startActivityForResult(createColorIntent, COLOR_EDIT_REQUEST);
    }

    private void deleteColors() {
//        for (Integer integer:itemsSelected) {
//            ColorEspecimen colorEspecimen = especimenDTO.getColores().get(integer);
//            if (colorEspecimen.getId() != null) {
//                colorEspecimenDao.delete(colorEspecimen);
//            }else{
//                colores.remove(colorEspecimen);
//            }
//            colorChangedListener.onColorRemoved(colorEspecimen);
//        }
    }

    private void callbackDestroyActionMode() {
        colorChangedListener.onActionModeFinished(this);
    }
}