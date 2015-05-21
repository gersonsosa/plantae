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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.ui.adapter.ListItemAdapter;

/**
 * Created by hghar on 12/4/14.
 */
public class ColorsFragment extends ListFragment {

    public static final int COLOR_CREATION_REQUEST = 16;
    public static final int COLOR_EDIT_REQUEST = 26;
    private OnColorChangedListener colorChangedListener;
    private Integer[] itemsSelected;
    private ActionMode mActionMode;
    private List<ColorEspecimenDTO> colors;

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

        public void onColorAdded(ColorEspecimenDTO colorEspecimen);
        public void onColorEdited(ColorEspecimenDTO colorEspecimen);
        public void onColorRemoved(ColorEspecimenDTO colorEspecimen);
        public void onActionModeFinished(ColorsFragment colorsFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_colors, container, false);
        Bundle bundle = getArguments();
        ArrayList<Parcelable> colores = bundle.getParcelableArrayList("colores");
        colors = new ArrayList<>(bundle.size());
        for (Parcelable parcelable:colores) {
            colors.add((ColorEspecimenDTO) parcelable);
        }
        loadColors();
        mActionMode = ((ActionBarActivity)getActivity()).startSupportActionMode(mActionModeCallback);
        mActionMode.setTitle(getString(R.string.colors_title));
        return rootView;
    }

    private void loadColors() {
        List<ListItem> listItems = new ArrayList<>();
        if (itemsSelected == null) {
            itemsSelected = new Integer[colors.size()];
        }
        if (itemsSelected.length < colors.size()) {
            Integer[] itemsSelectedLast = itemsSelected;
            itemsSelected = new Integer[colors.size()];
            System.arraycopy(itemsSelectedLast, 0, itemsSelected, 0, itemsSelectedLast.length);
        }
        for (ColorEspecimenDTO colorEspecimenDTO : colors) {
            listItems.add(new ListItem((long) colors.indexOf(colorEspecimenDTO), colorEspecimenDTO.getNombre(),
                    (colorEspecimenDTO.getOrganoDeLaPlanta() == null
                            || colorEspecimenDTO.getOrganoDeLaPlanta().isEmpty()) ?
                            colorEspecimenDTO.getDescripcion():
                            colorEspecimenDTO.getOrganoDeLaPlanta(), "0", false, false));
        }
        setListAdapter(new ListItemAdapter(getActivity().getApplicationContext(), listItems));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        itemsSelected[position] = position;
        Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
        editColorIntent.putExtra("color", colors.get(position));
        getActivity().startActivityForResult(editColorIntent, COLOR_EDIT_REQUEST);
        mActionMode.invalidate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == COLOR_CREATION_REQUEST && resultCode == Activity.RESULT_OK ) {
            ColorEspecimenDTO colorEspecimen = data.getParcelableExtra("colorEspecimen");
            colors.add(colorEspecimen);
            loadColors();
            colorChangedListener.onColorAdded(colorEspecimen);
        }
        if (requestCode == COLOR_EDIT_REQUEST && resultCode == Activity.RESULT_OK) {
            ColorEspecimenDTO colorEspecimen = data.getParcelableExtra("colorEspecimen");
            loadColors();
            colorChangedListener.onColorEdited(colorEspecimen);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private int itemsSelectedCount(){
        int i = 0;
        for (Integer item : itemsSelected) {
            if (item != null) {
                i++;
            }
        }
        return i;
    }

    private void clearItemsSelected() {
        itemsSelected = new Integer[itemsSelected.length];
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
            itemsSelected = new Integer[length];
            loadColors();
            mActionMode = null;
            callbackDestroyActionMode();
        }
    };

    private void deleteColors() {
        for (Integer integer:itemsSelected) {
            colorChangedListener.onColorRemoved(colors.get(integer));
        }
    }

    private void callbackDestroyActionMode() {
        colorChangedListener.onActionModeFinished(this);
    }
}