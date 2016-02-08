package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
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
import edu.udistrital.plantae.logicadominio.datosespecimen.MuestraAsociada;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.MuestraAsociadaDao;
import edu.udistrital.plantae.ui.adapter.ListItemCheckAdapter;

/**
 * Created by hghar on 12/4/14.
 */
public class AssociatedSamplesFragment extends ListFragment implements View.OnClickListener {

    public static final int ASSOCIATED_SAMPLE_CREATION_REQUEST = 15;
    public static final int ASSOCIATED_SAMPLE_EDIT_REQUEST = 300;
    private Integer[] itemsSelected;
    private OnAssociatedSampleListener associatedSampleListener;
    private ActionMode mActionMode;
    private MuestraAsociadaDao muestraAsociadaDao;
    private ArrayList<MuestraAsociada> muestraAsociadas;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            associatedSampleListener = (OnAssociatedSampleListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnAssociatedSampleListener");
        }
    }

    public interface OnAssociatedSampleListener {

        public void onAssociatedSampleAdded(MuestraAsociada muestraAsociada);
        public void onAssociatedSampleRemoved(MuestraAsociada muestraAsociada);
        public void onActionModeFinished(AssociatedSamplesFragment associatedSamplesFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_associated_samples, container, false);
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayList<? extends Parcelable> parcelables = getArguments().getParcelableArrayList("muestrasAsociadas");
        if (parcelables == null) {
            muestraAsociadas = new ArrayList<>();
        }else{
            muestraAsociadas = new ArrayList<>();
            for (Parcelable parcelable:parcelables) {
                if (MuestraAsociada.class.isInstance(parcelable)) {
                    muestraAsociadas.add((MuestraAsociada) parcelable);
                }
            }
        }
        muestraAsociadaDao = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession().getMuestraAsociadaDao();
        loadAssociatedSamples();
        mActionMode = ((AppCompatActivity)getActivity()).startSupportActionMode(mActionModeCallback);
        mActionMode.setTitle(getString(R.string.plant_sample_title));
        return rootView;
    }

    public List<MuestraAsociada> getMuestraAsociadas() {
        return muestraAsociadas;
    }

    private void loadAssociatedSamples() {
        List<ListItem> listItems = new ArrayList<>();
        if (itemsSelected == null) {
            itemsSelected = new Integer[muestraAsociadas.size()];
        }
        if (itemsSelected.length != muestraAsociadas.size()) {
            Integer[] itemsSelectedLast = itemsSelected;
            itemsSelected = new Integer[muestraAsociadas.size()];
            System.arraycopy(itemsSelectedLast, 0, itemsSelected, 0, itemsSelectedLast.length);
        }
        for (MuestraAsociada muestraAsociada:muestraAsociadas) {
            listItems.add(new ListItem((long) muestraAsociadas.indexOf(muestraAsociada), muestraAsociada.getDescripcion(), muestraAsociada.getMetodoDeTratamiento(), "0", false, false));
        }
        setListAdapter(new ListItemCheckAdapter(getActivity().getApplicationContext(), R.layout.list_item_check, this, listItems));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ASSOCIATED_SAMPLE_CREATION_REQUEST && resultCode == Activity.RESULT_OK){
            MuestraAsociada muestraAsociada = data.getParcelableExtra("muestraAsociada");
            muestraAsociadas.add(muestraAsociada);
            loadAssociatedSamples();
            associatedSampleListener.onAssociatedSampleAdded(muestraAsociada);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        int position = getListView().getPositionForView(v);
        ListItem listItem = ((ListItem)getListAdapter().getItem(position));
        if (itemsSelected[position] != null && itemsSelected[position].equals(listItem.getId().intValue())){
            itemsSelected[position] = null;
        }else{
            itemsSelected[position]= listItem.getId().intValue();
        }
        mActionMode.invalidate();
    }

    private int itemsSelectedCount(){
        int i = 0;
        for (Integer anItemsSelected : itemsSelected) {
            if (anItemsSelected != null) {
                i++;
            }
        }
        return i;
    }

    private void clearItemsSelected() {
        itemsSelected = new Integer[itemsSelected.length];
    }

    private void editAssociatedSample(Integer index) {
        Intent editSecondaryCollectorIntent = new Intent(getActivity().getApplicationContext(), CreateAssociatedSampleActivity.class);
        editSecondaryCollectorIntent.putExtra("associatedSample", muestraAsociadas.get(index));
        startActivityForResult(editSecondaryCollectorIntent, ASSOCIATED_SAMPLE_EDIT_REQUEST);
    }

    private void deleteAssociatedSamples() {
        for (Integer index : itemsSelected) {
            MuestraAsociada muestraAsociada = muestraAsociadas.get(index);
            if (muestraAsociada.getId() != null) {
                muestraAsociadaDao.delete(muestraAsociada);
            }else{
                muestraAsociadas.remove(muestraAsociada);
            }
            associatedSampleListener.onAssociatedSampleRemoved(muestraAsociada);
        }
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.associated_sample_list_contextual, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            int itemsSelected = itemsSelectedCount();
            menu.findItem(R.id.action_add_associated_sample).setVisible(true);
            menu.findItem(R.id.action_edit_associated_sample).setVisible(itemsSelected == 1);
            menu.findItem(R.id.action_delete_associated_samples).setVisible(itemsSelected >= 1);
            return true;
        }

        @Override
        public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_add_associated_sample:
                    Intent createAssociatedSampleIntent = new Intent(getActivity().getApplicationContext(), CreateAssociatedSampleActivity.class);
                    getActivity().startActivityForResult(createAssociatedSampleIntent, ASSOCIATED_SAMPLE_CREATION_REQUEST);
                    return true;
                case R.id.action_delete_associated_samples:
                    // delete selected items
                    new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.delete_associated_sample_confirmation_message)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteAssociatedSamples();
                                    clearItemsSelected();
                                    loadAssociatedSamples();
                                    actionMode.invalidate();
                                }

                            })
                            .setNegativeButton(R.string.no, null)
                            .show();
                    return true;
                case R.id.action_edit_associated_sample:
                    // Load data and show CreateActivity
                    for (Integer anItemsSelected : itemsSelected) {
                        if (anItemsSelected != null) {
                            editAssociatedSample(anItemsSelected);
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
            // update list
            int length = itemsSelected.length;
            for (int i = 0; i < length; i++) {
                getListView().setItemChecked(i, false);
            }
            itemsSelected = new Integer[length];
            loadAssociatedSamples();
            mActionMode = null;
            callbackDestroyActionMode();
        }
    };

    private void callbackDestroyActionMode() {
        associatedSampleListener.onActionModeFinished(this);
    }
}