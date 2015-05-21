package edu.udistrital.plantae.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.nononsenseapps.filepicker.FilePickerActivity;

import java.util.ArrayList;
import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fenologia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habito;
import edu.udistrital.plantae.logicadominio.taxonomia.EpitetoEspecifico;
import edu.udistrital.plantae.logicadominio.taxonomia.Familia;
import edu.udistrital.plantae.logicadominio.taxonomia.Genero;
import edu.udistrital.plantae.logicadominio.taxonomia.Taxon;
import edu.udistrital.plantae.logicadominio.ubicacion.Departamento;
import edu.udistrital.plantae.logicadominio.ubicacion.Municipio;
import edu.udistrital.plantae.logicadominio.ubicacion.Pais;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.FenologiaDao;
import edu.udistrital.plantae.persistencia.HabitoDao;
import edu.udistrital.plantae.persistencia.RegionDao;
import edu.udistrital.plantae.persistencia.TaxonDao;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnAutoCompleteValueListener}
 * interface.
 */
public class AutoCompleteValueFragment extends Fragment implements AbsListView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String AUTO_COMPLETE_LIST1 = "autoCompleteList1";
    private static final String USUARIO_ID2 = "usuarioId2";
    private static final int FILE_CODE = 99;

    private String autoCompleteList1;
    private Long usuarioId2;

    private OnAutoCompleteValueListener mListener;

    private List<ListItem> listItems;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;
    private DaoSession daoSession;
    private String classType;

    public static AutoCompleteValueFragment newInstance(String autoCompleteList1, Long usuarioId2) {
        AutoCompleteValueFragment fragment = new AutoCompleteValueFragment();
        Bundle args = new Bundle();
        args.putString(AUTO_COMPLETE_LIST1, autoCompleteList1);
        args.putLong(USUARIO_ID2, usuarioId2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AutoCompleteValueFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            autoCompleteList1 = getArguments().getString(AUTO_COMPLETE_LIST1);
            usuarioId2 = getArguments().getLong(USUARIO_ID2);
            String[] autoCompleteLists = getResources().getStringArray(R.array.auto_complete);
            if (autoCompleteList1.equals(autoCompleteLists[0])) { // County list
                classType = Municipio.class.getName();
            }else if (autoCompleteList1.equals(autoCompleteLists[1])) { // State list
                classType = Departamento.class.getName();
            }else if (autoCompleteList1.equals(autoCompleteLists[2])) { // Country list
                classType = Pais.class.getName();
            }else if (autoCompleteList1.equals(autoCompleteLists[3])) { // Family list
                classType = Familia.class.getName();
            }else if (autoCompleteList1.equals(autoCompleteLists[4])) { // Genus list
                classType = Genero.class.getName();
            }else if (autoCompleteList1.equals(autoCompleteLists[5])) { // Species list
                classType = EpitetoEspecifico.class.getName();
            }else if (autoCompleteList1.equals(autoCompleteLists[6])) { // Habit list
                classType = Habito.class.getName();
            }else if (autoCompleteList1.equals(autoCompleteLists[7])) { // Fenology list
                classType = Fenologia.class.getName();
            }
        }

        listItems = new ArrayList<>();
        daoSession = DataBaseHelper.getDataBaseHelper(
                getActivity().getApplicationContext()).getDaoSession();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto_complete_value, container, false);

        mListView = (AbsListView) view.findViewById(android.R.id.list);

        setEmptyText(getActivity().getString(R.string.no_values_found));

        setHasOptionsMenu(true);

        LoadAutoCompleteListValues loadAutoCompleteListValues =
                new LoadAutoCompleteListValues(daoSession);
        loadAutoCompleteListValues.execute(autoCompleteList1);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAutoCompleteValueListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAutoCompleteListListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_auto_complete_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_replace_list) {
            // This always works
            Intent i = new Intent(getActivity(), FilePickerActivity.class);
            // This works if you defined the intent filter
            // Intent i = new Intent(Intent.ACTION_GET_CONTENT);

            // Set these depending on your use case. These are the defaults.
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
            i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);

            // Configure initial directory like so
            i.putExtra(FilePickerActivity.EXTRA_START_PATH, "/storage/emulated/0/");

            startActivityForResult(i, FILE_CODE);
            return true;
        }
        if (id == R.id.action_add_value) {
            EditValueDialogFragment valueDialogFragment = EditValueDialogFragment.newInstance(null, classType, null);
            valueDialogFragment.show(getFragmentManager(), "valueDialogFragment");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILE_CODE && resultCode == Activity.RESULT_OK) {
            if (data.getBooleanExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
                // For JellyBean and above
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ClipData clip = data.getClipData();

                    if (clip != null) {
                        for (int i = 0; i < clip.getItemCount(); i++) {
                            Uri uri = clip.getItemAt(i).getUri();
                            // Do something with the URI
                        }
                    }
                    // For Ice Cream Sandwich
                } else {
                    ArrayList<String> paths = data.getStringArrayListExtra
                            (FilePickerActivity.EXTRA_PATHS);

                    if (paths != null) {
                        for (String path: paths) {
                            Uri uri = Uri.parse(path);
                            // Do something with the URI
                        }
                    }
                }

            } else {
                Uri uri = data.getData();
                // Do something with the URI
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            // create dialogs to edit name or content of values
            ListItem listItem = ((ListItem) mListView.getItemAtPosition(position));
            Long listItemId = listItem.getId();
            String classType = listItem.getClassType();
            String value = listItem.getTitle();
            EditValueDialogFragment valueDialogFragment = EditValueDialogFragment.newInstance(listItemId, classType, value);
            valueDialogFragment.show(getFragmentManager(), "valueDialogFragment");
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public void createValue(String newValue) {
        if (classType.equals(Municipio.class.getName())) {
            Municipio municipio = new Municipio(newValue);
            municipio.setUsuarioId(usuarioId2);
            daoSession.getRegionDao().insert(municipio);
        }else if (classType.equals(Departamento.class.getName())) {
            Departamento departamento = new Departamento(newValue);
            departamento.setUsuarioId(usuarioId2);
            daoSession.getRegionDao().insert(departamento);
        }else if (classType.equals(Pais.class.getName())) {
            Pais pais = new Pais(newValue);
            pais.setUsuarioId(usuarioId2);
            daoSession.getRegionDao().insert(pais);
        }if (classType.equals(Familia.class.getName())) {
            Familia familia = new Familia(newValue);
            familia.setUsuarioId(usuarioId2);
            daoSession.getTaxonDao().insert(familia);
        }else if (classType.equals(Genero.class.getName())) {
            Genero genero = new Genero(newValue);
            genero.setUsuarioId(usuarioId2);
            daoSession.getTaxonDao().insert(genero);
        }else if (classType.equals(EpitetoEspecifico.class.getName())) {
            EpitetoEspecifico epitetoEspecifico = new EpitetoEspecifico(newValue);
            epitetoEspecifico.setUsuarioId(usuarioId2);
            daoSession.getTaxonDao().insert(epitetoEspecifico);
        }else if (classType.equals(Habito.class.getName())) {
            Habito habito = new Habito(newValue);
            habito.setUsuarioID(usuarioId2);
            daoSession.getHabitoDao().insert(habito);
        }else if (classType.equals(Fenologia.class.getName())) {
            Fenologia fenologia = new Fenologia(newValue);
            fenologia.setUsuarioID(usuarioId2);
            daoSession.getFenologiaDao().insert(fenologia);
        }
        LoadAutoCompleteListValues loadAutoCompleteListValues =
                new LoadAutoCompleteListValues(daoSession);
        loadAutoCompleteListValues.execute(autoCompleteList1);
    }

    public void updateValue(Long id, String classType, String newValue) {
        if (classType.equals(Region.class.getName())) {
            Region region = daoSession.getRegionDao().load(id);
            region.setNombre(newValue);
            daoSession.getRegionDao().update(region);
        }else if (classType.equals(Taxon.class.getName())) {
            Taxon taxon = daoSession.getTaxonDao().load(id);
            taxon.setNombre(newValue);
            daoSession.getTaxonDao().update(taxon);
        }else if (classType.equals(Habito.class.getName())) {
            Habito habito = daoSession.getHabitoDao().load(id);
            habito.setHabito(newValue);
            daoSession.getHabitoDao().update(habito);
        }else if (classType.equals(Fenologia.class.getName())) {
            Fenologia fenologia = daoSession.getFenologiaDao().load(id);
            fenologia.setFenologia(newValue);
            daoSession.getFenologiaDao().update(fenologia);
        }
        LoadAutoCompleteListValues loadAutoCompleteListValues =
                new LoadAutoCompleteListValues(daoSession);
        loadAutoCompleteListValues.execute(autoCompleteList1);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAutoCompleteValueListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String id);
    }

    class LoadAutoCompleteListValues extends AsyncTask<String, Void, Boolean> {

        private DaoSession daoSession;

        public LoadAutoCompleteListValues(DaoSession daoSession) {
            this.daoSession = daoSession;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String[] autoCompleteLists = getResources().getStringArray(R.array.auto_complete);
            List<Region> list = null;
            String autoCompleteList1 = params[0];
            if (autoCompleteList1.equals(autoCompleteLists[0])) { // County list
                list = daoSession.queryBuilder(Region.class).where(RegionDao
                        .Properties.UsuarioId.eq(usuarioId2)).where(RegionDao
                        .Properties.Rango.eq("municipio")).list();
            }else if (autoCompleteList1.equals(autoCompleteLists[1])) { // State list
                list = daoSession.queryBuilder(Region.class).where(RegionDao
                        .Properties.UsuarioId.eq(usuarioId2)).where(RegionDao
                        .Properties.Rango.eq("departamento")).list();
            }else if (autoCompleteList1.equals(autoCompleteLists[2])) { // Country list
                list = daoSession.queryBuilder(Region.class).where(RegionDao
                        .Properties.UsuarioId.eq(usuarioId2)).where(RegionDao
                        .Properties.Rango.eq("pais")).list();
            }
            if (list != null && !list.isEmpty()) {
                listItems = new ArrayList<>();
                for (Region region:list) {
                    listItems.add(new ListItem(region.getId(), Region.class.getName(), region.getNombre(),
                            region.getRegionPadre() != null ? region.getRegionPadre().getNombre():null));
                }
            }
            List<Taxon> taxonList = null;
            if (autoCompleteList1.equals(autoCompleteLists[3])) { // Family list
                taxonList = daoSession.queryBuilder(Taxon.class).where(TaxonDao
                        .Properties.UsuarioId.eq(usuarioId2)).where(TaxonDao
                        .Properties.Rango.eq("familia")).list();
            }else if (autoCompleteList1.equals(autoCompleteLists[4])) { // Genus list
                taxonList = daoSession.queryBuilder(Taxon.class).where(TaxonDao
                        .Properties.UsuarioId.eq(usuarioId2)).where(TaxonDao
                        .Properties.Rango.eq("genero")).list();
            }else if (autoCompleteList1.equals(autoCompleteLists[5])) { // Species list
                taxonList = daoSession.queryBuilder(Taxon.class).where(TaxonDao
                        .Properties.UsuarioId.eq(usuarioId2)).where(TaxonDao
                        .Properties.Rango.eq("epitetoespecifico")).list();
            }
            if (taxonList != null && !taxonList.isEmpty()) {
                listItems = new ArrayList<>();
                for (Taxon taxon:taxonList) {
                    listItems.add(new ListItem(taxon.getId(), Taxon.class.getName(),taxon.getNombre(),
                            taxon.getTaxonPadre().getNombre()));
                }
            }
            if (autoCompleteList1.equals(autoCompleteLists[6])) { // Habit list
                List<Habito> habitoList = daoSession.queryBuilder(Habito.class)
                        .where(HabitoDao.Properties.UsuarioID.eq(usuarioId2)).list();
                listItems = new ArrayList<>();
                for (Habito habito:habitoList) {
                    listItems.add(new ListItem(habito.getId(), Habito.class.getName(), habito.getHabito(), ""));
                }
            }else if (autoCompleteList1.equals(autoCompleteLists[7])) { // Fenology list
                List<Fenologia> fenologiaList = daoSession.queryBuilder(Fenologia.class)
                        .where(FenologiaDao.Properties.UsuarioID.eq(usuarioId2)).list();
                listItems = new ArrayList<>();
                for (Fenologia fenologia:fenologiaList) {
                    listItems.add(new ListItem(fenologia.getId(), Fenologia.class.getName(), fenologia.getFenologia(), ""));
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                setupListView();
            }
        }
    }

    private void setupListView() {
        mAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listItems);

        // Set the adapter
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
    }

}
