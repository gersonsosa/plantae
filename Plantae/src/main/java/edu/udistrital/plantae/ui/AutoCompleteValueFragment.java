package edu.udistrital.plantae.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import android.widget.Toast;

import com.nononsenseapps.filepicker.FilePickerActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.dao.query.Query;
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
 */
public class AutoCompleteValueFragment extends Fragment implements AbsListView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String AUTO_COMPLETE_LIST1 = "autoCompleteList1";
    private static final String USUARIO_ID2 = "usuarioId2";
    private static final int FILE_CODE = 19;

    private String autoCompleteList1;
    private Long usuarioId2;

    private List<ListItem> listItems;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;
    private View mCsvImportStatusView;

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

        mCsvImportStatusView = view.findViewById(R.id.csv_import_status_list);

        setEmptyText(getActivity().getString(R.string.no_values_found));

        setHasOptionsMenu(true);

        loadValues();

        return view;
    }

    public void loadValues() {
        LoadAutoCompleteListValues loadAutoCompleteListValues =
                new LoadAutoCompleteListValues(daoSession);
        loadAutoCompleteListValues.execute(autoCompleteList1);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_auto_complete_list, menu);
    }

    /**
     * Shows the progress UI and hides the register form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);

            mCsvImportStatusView.setVisibility(View.VISIBLE);
            mCsvImportStatusView.animate().setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mCsvImportStatusView.setVisibility(show ? View.VISIBLE
                                    : View.GONE);
                        }
                    });

            mListView.setVisibility(View.VISIBLE);
            mListView.animate().setDuration(shortAnimTime)
                    .alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mListView.setVisibility(show ? View.GONE
                                    : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mCsvImportStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            mListView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_import_csv) {
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

            getActivity().startActivityForResult(i, FILE_CODE);
            return true;
        }
        if (id == R.id.action_add_value) {
            startValueDialog(null, null, null);
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
                handleFileImport(uri);
            }
        }
    }

    private void handleFileImport(Uri uri) {
        showProgress(true);
        LoadCsvValuesTask loadCsvValuesTask = new LoadCsvValuesTask();
        loadCsvValuesTask.execute(uri.getPath());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        // create dialogs to edit name or content of values
        ListItem listItem = ((ListItem) mListView.getItemAtPosition(position));
        Long listItemId = listItem.getId();
        String value = listItem.getTitle();
        String parentValue = listItem.getSuperTitle();
        startValueDialog(listItemId, value, parentValue);
    }

    private void startValueDialog(Long listItemId, String value, String parentValue) {
        EditValueDialogFragment valueDialogFragment;
        if (this.classType.equals(Municipio.class.getName())) {
            ArrayList<String> stringArrayList = new ArrayList<>();
            for (Region region:daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("departamento")).list()) {
                stringArrayList.add(region.getNombre());
            }
            valueDialogFragment = EditValueDialogFragment.newInstance(listItemId, classType, value, parentValue, stringArrayList);
        } else if (this.classType.equals(Departamento.class.getName())) {
            ArrayList<String> stringArrayList = new ArrayList<>();
            for (Region region:daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("pais")).list()) {
                stringArrayList.add(region.getNombre());
            }
            valueDialogFragment = EditValueDialogFragment.newInstance(listItemId, classType, value, parentValue, stringArrayList);
        } else if (this.classType.equals(Genero.class.getName())) {
            ArrayList<String> stringArrayList = new ArrayList<>();
            for (Taxon taxon:daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Rango.eq("familia")).list()) {
                stringArrayList.add(taxon.getNombre());
            }
            valueDialogFragment = EditValueDialogFragment.newInstance(listItemId, classType, value, parentValue, stringArrayList);
        } else if (this.classType.equals(EpitetoEspecifico.class.getName())) {
            ArrayList<String> stringArrayList = new ArrayList<>();
            for (Taxon taxon:daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Rango.eq("genero")).list()) {
                stringArrayList.add(taxon.getNombre());
            }
            valueDialogFragment = EditValueDialogFragment.newInstance(listItemId, classType, value, parentValue, stringArrayList);
        } else {
            valueDialogFragment = EditValueDialogFragment.newInstance(listItemId, classType, value);
        }
        valueDialogFragment.show(getFragmentManager(), "valueDialogFragment");
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

    public Object createValue(String newValue) {
        if (classType.equals(Pais.class.getName()) || classType.equals(Departamento.class.getName()) || classType.equals(Municipio.class.getName())) {
            Query<Region> countryQuery = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Pais.eq(newValue)).limit(1).build();
            Pais pais = (Pais) countryQuery.unique();
            if (pais == null) {
                pais = new Pais(newValue);
                pais.setUsuarioId(usuarioId2);
                daoSession.getRegionDao().insert(pais);
            }
            return pais;
        }if (classType.equals(Familia.class.getName()) || classType.equals(Genero.class.getName()) || classType.equals(EpitetoEspecifico.class.getName())) {
            Query<Taxon> familyQuery = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(newValue)).limit(1).build();
            Familia familia = (Familia) familyQuery.unique();
            if (familia == null) {
                familia = new Familia(newValue);
                familia.setUsuarioId(usuarioId2);
                daoSession.getTaxonDao().insert(familia);
            }
            return familia;
        }else if (classType.equals(Habito.class.getName())) {
            Query<Habito> habitoQuery = daoSession.getHabitoDao().queryBuilder().where(HabitoDao.Properties.Habito.eq(newValue)).limit(1).build();
            Habito habito = habitoQuery.unique();
            if (habito == null) {
                habito = new Habito(newValue);
                habito.setUsuarioID(usuarioId2);
                daoSession.getHabitoDao().insert(habito);
            }
            return habito;
        }else if (classType.equals(Fenologia.class.getName())) {
            Query<Fenologia> fenologiaQuery = daoSession.getFenologiaDao().queryBuilder().where(FenologiaDao.Properties.Fenologia.eq(newValue)).limit(1).build();
            Fenologia fenologia = fenologiaQuery.unique();
            if (fenologia == null) {
                fenologia = new Fenologia(newValue);
                fenologia.setUsuarioID(usuarioId2);
                daoSession.getFenologiaDao().insert(fenologia);
            }
            return fenologia;
        }
        return null;
    }

    public Taxon createGenus(String newValue, String parentValue) {
        Query<Taxon> genusQuery = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Genero.eq(newValue)).limit(1).build();
        Genero genero = (Genero) genusQuery.unique();
        if (genero == null) {
            genero = new Genero(newValue);
            Familia familia = (Familia) daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(parentValue)).limit(1).unique();
            if (familia == null) {
                familia = (Familia) createValue(parentValue);
            }
            genero.setTaxonPadre(familia);
            genero.setUsuarioId(usuarioId2);
            daoSession.getTaxonDao().insert(genero);
        }
        return genero;
    }

    public Region createState(String newValue, String parentRegion) {
        Query<Region> stateQuery = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Departamento.eq(newValue)).limit(1).build();
        Departamento departamento = (Departamento) stateQuery.unique();
        if (departamento == null) {
            departamento = new Departamento(newValue);
            Region country = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Pais.eq(parentRegion)).limit(1).unique();
            if (country == null) {
                country = (Region) createValue(parentRegion);
            }
            departamento.setRegionPadre(country);
            departamento.setUsuarioId(usuarioId2);
            daoSession.getRegionDao().insert(departamento);
        }
        return departamento;
    }

    public void createCounty(String newValue, String parentValue, String parentParentValue) {
        Query<Region> countyQuery = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Municipio.eq(newValue)).limit(1).build();
        Municipio municipio = (Municipio) countyQuery.unique();
        if (municipio == null) {
            municipio = new Municipio(newValue);
            Region departamento = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Departamento.eq(parentValue)).limit(1).unique();
            if (departamento == null) {
                departamento = createState(parentValue, parentParentValue);
            }
            municipio.setRegionPadre(departamento);
            municipio.setUsuarioId(usuarioId2);
            daoSession.getRegionDao().insert(municipio);
        }
    }

    public void createSpecies(String newValue, String parentValue, String parentParentValue) {
        Query<Taxon> speciesQuery = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Especie.eq(newValue)).limit(1).build();
        EpitetoEspecifico epitetoEspecifico = speciesQuery.unique() == null ? new EpitetoEspecifico(newValue) : (EpitetoEspecifico) speciesQuery.unique();
        Taxon genero = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Genero.eq(parentValue)).limit(1).unique();
        if (genero == null) {
            genero = createGenus(parentValue, parentParentValue);
        }
        epitetoEspecifico.setTaxonPadre(genero);
        epitetoEspecifico.setUsuarioId(usuarioId2);
        daoSession.getTaxonDao().insert(epitetoEspecifico);
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
        loadValues();
    }

    public Taxon updateGenus(Long id, String newValue, String parentValue) {
        Genero genero = (Genero) daoSession.getTaxonDao().load(id);
        Familia familia = (Familia) daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(parentValue)).limit(1).unique();
        if (familia == null) {
            familia = (Familia) createValue(parentValue);
        }
        genero.setNombre(newValue);
        genero.setTaxonPadre(familia);
        genero.setUsuarioId(usuarioId2);
        daoSession.getTaxonDao().update(genero);
        return genero;
    }

    public Region updateState(Long id, String newValue, String parentRegion) {
        Departamento departamento = (Departamento) daoSession.getRegionDao().load(id);
        Region country = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Pais.eq(parentRegion)).limit(1).unique();
        if (country == null) {
            country = (Region) createValue(parentRegion);
        }
        departamento.setNombre(newValue);
        departamento.setRegionPadre(country);
        departamento.setUsuarioId(usuarioId2);
        daoSession.getRegionDao().update(departamento);
        return departamento;
    }

    public void updateCounty(Long id, String newValue, String parentValue, String parentParentValue) {
        Municipio municipio = (Municipio) daoSession.getRegionDao().load(id);
        Region departamento = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Departamento.eq(parentValue)).limit(1).unique();
        if (departamento == null) {
            departamento = createState(parentValue, parentParentValue);
        }
        municipio.setNombre(newValue);
        municipio.setRegionPadre(departamento);
        municipio.setUsuarioId(usuarioId2);
        daoSession.getRegionDao().update(municipio);
    }

    public void updateSpecies(Long id, String newValue, String parentValue, String parentParentValue) {
        EpitetoEspecifico epitetoEspecifico = (EpitetoEspecifico) daoSession.getTaxonDao().load(id);
        Taxon genero = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Genero.eq(parentValue)).limit(1).unique();
        if (genero == null) {
            genero = createGenus(parentValue, parentParentValue);
        }
        epitetoEspecifico.setNombre(newValue);
        epitetoEspecifico.setTaxonPadre(genero);
        epitetoEspecifico.setUsuarioId(usuarioId2);
        daoSession.getTaxonDao().update(epitetoEspecifico);
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
                    listItems.add(new ListItem(region.getId(), region.getNombre(),
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
                    listItems.add(new ListItem(taxon.getId(), taxon.getNombre(),
                            taxon.getTaxonPadre() != null ? taxon.getTaxonPadre().getNombre():null));
                }
            }
            if (autoCompleteList1.equals(autoCompleteLists[6])) { // Habit list
                List<Habito> habitoList = daoSession.queryBuilder(Habito.class)
                        .where(HabitoDao.Properties.UsuarioID.eq(usuarioId2)).list();
                listItems = new ArrayList<>();
                for (Habito habito:habitoList) {
                    listItems.add(new ListItem(habito.getId(), habito.getHabito(), ""));
                }
            }else if (autoCompleteList1.equals(autoCompleteLists[7])) { // Fenology list
                List<Fenologia> fenologiaList = daoSession.queryBuilder(Fenologia.class)
                        .where(FenologiaDao.Properties.UsuarioID.eq(usuarioId2)).list();
                listItems = new ArrayList<>();
                for (Fenologia fenologia:fenologiaList) {
                    listItems.add(new ListItem(fenologia.getId(), fenologia.getFenologia(), ""));
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

    class LoadCsvValuesTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String path = params[0];
            InputStream inputStream = null;
            int csvColumnNumber;
            BufferedReader reader;
            try {
                inputStream = new FileInputStream(path);
                reader = new BufferedReader(new InputStreamReader(inputStream));
                csvColumnNumber = csvColumns(reader.readLine());
                if (classType.equals(Departamento.class.getName()) || classType.equals(Genero.class.getName())) {
                    // Check two column csv file comma separated
                    if (csvColumnNumber != 2) {
                        // Mark task as failed and show error
                        CsvErrorDialogFragment csvErrorDialogFragment = CsvErrorDialogFragment.newInstance(R.string.bad_format_sv_file);
                        csvErrorDialogFragment.show(getFragmentManager(), "formatErrorDialog");
                        return false;
                    }
                }else if (classType.equals(Municipio.class.getName()) || classType.equals(EpitetoEspecifico.class.getName())) {
                    // Check tree column csv file comma separated
                    if (csvColumnNumber != 3) {
                        // Mark task as failed
                        CsvErrorDialogFragment csvErrorDialogFragment = CsvErrorDialogFragment.newInstance(R.string.bad_format_sv_file);
                        csvErrorDialogFragment.show(getFragmentManager(), "formatErrorDialog");
                        return false;
                    }
                }
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] rowData = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                    if (rowData.length == csvColumnNumber) {
                        switch (csvColumnNumber) {
                            case 1:
                                createValue(rowData[0]);
                                break;
                            case 2:
                                if (classType.equals(Departamento.class.getName())) {
                                    createState(rowData[0], rowData[1]);
                                }else{
                                    createGenus(rowData[0], rowData[1]);
                                }
                                break;
                            case 3:
                                if (classType.equals(Municipio.class.getName())) {
                                    createCounty(rowData[0], rowData[1], rowData[2]);
                                }else{
                                    createSpecies(rowData[0], rowData[1], rowData[2]);
                                }
                                break;
                            default:
                                CsvErrorDialogFragment csvErrorDialogFragment = CsvErrorDialogFragment.newInstance(R.string.bad_format_sv_file);
                                csvErrorDialogFragment.show(getFragmentManager(), "formatErrorDialog");
                                return false;
                        }
                    }else{
                        CsvErrorDialogFragment csvErrorDialogFragment = CsvErrorDialogFragment.newInstance(R.string.bad_format_sv_file);
                        csvErrorDialogFragment.show(getFragmentManager(), "formatErrorDialog");
                        return false;
                    }
                }
            } catch (FileNotFoundException e) {
                Log.e("Plantae", "File " + path + " doesn't exists." + Arrays.toString(e.getStackTrace()));
                CsvErrorDialogFragment csvErrorDialogFragment = CsvErrorDialogFragment.newInstance(R.string.error_importing_csv_file);
                csvErrorDialogFragment.show(getFragmentManager(), "formatErrorDialog");
                return false;
            } catch (IOException ex) {
                CsvErrorDialogFragment csvErrorDialogFragment = CsvErrorDialogFragment.newInstance(R.string.error_importing_csv_file);
                csvErrorDialogFragment.show(getFragmentManager(), "formatErrorDialog");
                return false;
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                catch (IOException e) {
                    CsvErrorDialogFragment csvErrorDialogFragment = CsvErrorDialogFragment.newInstance(R.string.error_importing_csv_file);
                    csvErrorDialogFragment.show(getFragmentManager(), "formatErrorDialog");
                }
            }
            return true;
        }

        public int csvColumns(String firstLine) {
            return firstLine.split(",",-1).length;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            showProgress(false);
            if (aBoolean) {
                loadValues();
                Toast.makeText(getActivity(), getActivity().getString(R.string.file_imported_succesfully), Toast.LENGTH_LONG).show();
            }
        }
    }

    public static class CsvErrorDialogFragment extends DialogFragment {

        private static final String RES_ID_MESSAGE1 = "resIdMessage1";
        private int resIdMesage;

        public static CsvErrorDialogFragment newInstance(int resIdMesage) {
            CsvErrorDialogFragment fragment =  new CsvErrorDialogFragment();
            Bundle args = new Bundle();
            if (resIdMesage != 0) {
                args.putInt(RES_ID_MESSAGE1, resIdMesage);
            }
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            resIdMesage = getArguments().getInt(RES_ID_MESSAGE1);
            builder.setMessage(resIdMesage)
                    .setPositiveButton(R.string.ok_action_dialog_fragment, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

}
