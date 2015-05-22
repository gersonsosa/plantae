package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.OnClickWrapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.EspecimenColectorSecundario;
import edu.udistrital.plantae.logicadominio.datosespecimen.Flor;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fotografia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fruto;
import edu.udistrital.plantae.logicadominio.datosespecimen.Hoja;
import edu.udistrital.plantae.logicadominio.datosespecimen.Inflorescencia;
import edu.udistrital.plantae.logicadominio.datosespecimen.MuestraAsociada;
import edu.udistrital.plantae.logicadominio.datosespecimen.Raiz;
import edu.udistrital.plantae.logicadominio.datosespecimen.Tallo;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.logicadominio.taxonomia.EpitetoEspecifico;
import edu.udistrital.plantae.logicadominio.taxonomia.Familia;
import edu.udistrital.plantae.logicadominio.taxonomia.Genero;
import edu.udistrital.plantae.logicadominio.taxonomia.IdentidadTaxonomica;
import edu.udistrital.plantae.logicadominio.taxonomia.NombreComun;
import edu.udistrital.plantae.logicadominio.taxonomia.Uso;
import edu.udistrital.plantae.logicadominio.ubicacion.Departamento;
import edu.udistrital.plantae.logicadominio.ubicacion.Municipio;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.EspecimenDao;
import edu.udistrital.plantae.persistencia.ViajeDao;
import edu.udistrital.plantae.ui.adapter.SpecimenListItemAdapter;
import edu.udistrital.plantae.utils.CSVWriter;

/**
 * Created by Gerson Sosa on 5/5/14.
 */
public class SpecimenListFragment extends ListFragment implements View.OnClickListener{

    public static final String[] csvHeader = {"Numero Colección",
            "Fecha inicial recolección", "Fecha final recolección", "Método de colección",
            "Estación del año", "Colectores Secundarios", "Nombre localidad", "Municipio",
            "Departamento", "Pais", "Elevación mínima", "Elevación máxima", "Latitud",
            "Longitud", "Descripción localidad", "Familia", "Genero", "Especie",
            "Fecha determinación", "Determinador", "Tipo", "Nombres comunes", "Usos",
            "Vegetacion", "Suelo/Sustrato", "Especies asociadas", "Habito", "Fenologia",
            "Altura de la Planta", "DAP", "Abundancia", "Descripción de la planta",
            "Nombre colores", "Descrición colores", "RGB colores", "Munsell colores",
            "Descripción muestras asociadas", "Método tratamiento muestras asociadas",
            "Descripción flor", "Nombre color de la corola", "Descripción color de la corola",
            "RGB color de la corola", "Munsell color de la corola", "Nombre color del caliz",
            "Descripción color del caliz", "RGB color del caliz", "Munsell color del caliz",
            "Nombre color del gineceo", "Descripción color del gineceo", "RGB color del gineceo",
            "Munsell color del gineceo", "Nombre color de los estambres",
            "Descripción color de los estambres", "RGB color de los estambres",
            "Munsell color de los estambres", "Nombre color de los estigmas",
            "Descripción color de los estigmas", "RGB color de los estigmas",
            "Munsell color de los estigmas", "Nombre color de los pistiliodios",
            "Descripción color de los pistiliodios", "RGB color de los pistiliodios",
            "Munsell color de los pistiliodios", "Descripción fruto",
            "Consistencia del pericarpio", "Nombre color del endocarpio",
            "Descripción color del endocarpio", "RGB color del endocarpio",
            "Munsell color del endocarpio", "Nombre color del exocarpio",
            "Descripción color del exocarpio", "RGB color del exocarpio",
            "Munsell color del exocarpio", "Descripción inflorescencia",
            "Naturaleza de las bracteas pedunculares", "Naturaleza del profilo",
            "Posicion de las bracteas pedunculares", "Posicion de las inflorescencias", "Raquilas",
            "Raquis", "Tamaño de las bracteas pedunculares", "Tamaño del pedunculo",
            "Tamaño del profilo", "Tamaño del raquis", "Tamaño de raquilas",
            "Nombre color de la inflorescencia en flor", "Descripción color de la inflorescencia en flor",
            "RGB color de la inflorescencia en flor", "Munsell color de la inflorescencia en flor",
            "Nombre color de la inflorescencia en fruto",
            "Descripción color de la inflorescencia en fruto", "RGB color de la inflorescencia en fruto",
            "Munsell color de la inflorescencia en fruto", "Descripción raiz", "Diametro de las raices",
            "Diametro en la base", "Forma de las espinas", "Tamaño de las espinas",
            "Raiz armada().to string", "Altura del cono().to string", "Descripcion",
            "Cobertura del peciolo", "Dispocicion de las pinnas", "Disposicion de las hojas",
            "Forma del peciolo", "Longuitud del raquis", "Naturaleza de la vaina",
            "Naturaleza del limbo", "Numero de pinnas", "Numero hojas", "Tamaño de las hojas",
            "Tamaño del peciolo", "Nombre color de las hojas", "Descripción color de las hojas",
            "RGB color de las hojas", "Munsell color de las hojas", "Nombre color del peciolo",
            "Descripción color del peciolo", "RGB color del peciolo", "Munsell color del peciolo",
            "Descripción tallo", "Desnudo cubierto", "Entrenudos conspicuos", "Espinas",
            "Nombre color del tallo", "Descripción color del tallo", "RGB color del tallo",
            "Munsell color del tallo"};
    public static final int SPECIMEN_CREATION_REQUEST = 777;
    private Viaje viaje;
    private ColectorPrincipal colectorPrincipal;
    private EspecimenDao especimenDao;
    private Long[] itemsSelected;
    private ActionMode actionMode;
    private ViajeDao viajeDao;
    private DaoSession daoSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_specimen_list, container, false);
        Long viajeID = getArguments().getLong("viaje");
        Long colectorPrincipalID = getArguments().getLong("colectorPrincipal");
        daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();
        colectorPrincipal = daoSession.getColectorPrincipalDao().load(colectorPrincipalID);
        // Cargar los especímenes del viaje seleccionado por defec  to o un aviso de agregar un nuevo especimen
        if (viajeID != 0l) {
            viajeDao = daoSession.getViajeDao();
            viaje = viajeDao.loadDeep(viajeID);
            getActivity().setTitle(viaje.getNombre());
            loadSpecimens();
        }
        actionMode = null;
        especimenDao = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession().getEspecimenDao();
        setHasOptionsMenu(true);
        return rootView;
    }

    void reloadFromArguments(Bundle arguments) {
        if (arguments == null) {
            arguments = new Bundle();
        } else {
            // since we might make changes, don't meddle with caller's copy
            arguments = (Bundle) arguments.clone();
        }
        String query = arguments.getString("query");
        List<SpecimenListItem> specimenListItem = arguments.getParcelableArrayList("specimens");
        setListAdapter(new SpecimenListItemAdapter(getActivity().getApplicationContext(), specimenListItem, null, getResources()));
    }

    private void loadSpecimens() {
        LoadSpecimensTask loadSpecimensTask = new LoadSpecimensTask(this);
        loadSpecimensTask.execute(viaje.getId());
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
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == SPECIMEN_CREATION_REQUEST && resultCode == Activity.RESULT_OK){

            OnClickWrapper onClickWrapper = new OnClickWrapper("savefeedbacktoast", new SuperToast.OnClickListener() {
                @Override
                public void onClick(View view, Parcelable parcelable) {
                    Long newEspecimenId = data.getLongExtra("newSpecimenId", 0l);
                    Especimen especimen = daoSession.getEspecimenDao().loadDeep(newEspecimenId);
                    EspecimenDTO clone = new EspecimenDTO((Especimen) especimen.clone());
                    Intent createCloneIntent = new Intent(getActivity().getApplicationContext(), CreateSpecimenActivity.class);
                    createCloneIntent.putExtra("clone", clone);
                    startActivityForResult(createCloneIntent, SPECIMEN_CREATION_REQUEST);
                }
            });

            SuperActivityToast superActivityToast = new SuperActivityToast(getActivity(), SuperToast.Type.BUTTON);
            superActivityToast.setDuration(SuperToast.Duration.EXTRA_LONG);
            superActivityToast.setText(getString(R.string.save_feedback));
            superActivityToast.setButtonIcon(SuperToast.Icon.Dark.SAVE, getString(R.string.string_action_save_and_new   ));
            superActivityToast.setOnClickWrapper(onClickWrapper);
            superActivityToast.show();

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
                startActivityForResult(createSpecimenIntent, SPECIMEN_CREATION_REQUEST);
                return true;
            case R.id.action_add_detailed:
                // call CreateSpecimenActivity
                Intent createDetailedSpecimenIntent = new Intent(getActivity().getApplicationContext(), CreateSpecimenActivity.class);
                createDetailedSpecimenIntent.putExtra("viaje", viaje.getId());
                createDetailedSpecimenIntent.putExtra("specimenType", SpecimenPagesAdapter.SPECIMEN_DETAILED);
                startActivityForResult(createDetailedSpecimenIntent, SPECIMEN_CREATION_REQUEST);
                return true;
            case R.id.action_rebuild_journey:
                Intent trayectoIntent = new Intent(getActivity().getApplicationContext(), RouteActivity.class);
                trayectoIntent.putExtra("viaje", viaje.getId());
                startActivity(trayectoIntent);
                return true;
            case R.id.action_export_csv:
                ExportCSVTask exportCSVTask = new ExportCSVTask();
                exportCSVTask.execute(" ");
                return true;
            case R.id.search:
                Intent searchactivityIntent = new Intent(getActivity().getApplicationContext(), SearchActivity.class);
                searchactivityIntent.putExtra("colectorPrincipal", colectorPrincipal.getId());
                startActivity(searchactivityIntent);
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        editSpecimen(((SpecimenListItem)l.getItemAtPosition(position)).getId());
    }

    @Override
    public void onClick(View v) {
        int position = getListView().getPositionForView(v);
        ImageView imageView = (ImageView) v;
        SpecimenListItem listItem = ((SpecimenListItem)getListAdapter().getItem(position));
        if (itemsSelected[position] != null && itemsSelected[position].equals(listItem.getId())){
            itemsSelected[position] = null;
            imageView.setImageResource(R.drawable.plantae);
            actionMode.invalidate();
            if (itemsSelectedCount() == 0 && actionMode != null){
                actionMode.finish();
            }
        }else{
            itemsSelected[position]= listItem.getId();
            imageView.setImageResource(R.drawable.checkmark_primary);
            if (actionMode == null) {
                actionMode = ((AppCompatActivity)getActivity()).startSupportActionMode(new SpecimenListMultiSelectionActionMode());
            }else{
                actionMode.invalidate();
            }
        }
        if (itemsSelectedCount() > 1){
            actionMode.invalidate();
        }
    }

    private void deleteSpecimens(){
        for (Long id : itemsSelected) {
            final Especimen especimen = especimenDao.load(id);
            if (especimen != null) {
                daoSession.runInTx(new Runnable() {
                    @Override
                    public void run() {
                        especimenDao.delete(especimen);
                        for (IdentidadTaxonomica determinacion:especimen.getDeterminaciones()) {
                            daoSession.getIdentidadTaxonomicaDao().delete(determinacion);
                        }
                        for (ColorEspecimen colorEspecimen:especimen.getColores()) {
                            daoSession.getColorEspecimenDao().delete(colorEspecimen);
                            daoSession.getColorMunsellDao().delete(colorEspecimen.getColorMunsell());
                        }
                        for (MuestraAsociada muestraAsociada:especimen.getMuestrasAsociadas()) {
                            daoSession.getMuestraAsociadaDao().delete(muestraAsociada);
                        }
                        for (EspecimenColectorSecundario especimenColectorSecundario:especimen.getColectoresSecundarios()) {
                            daoSession.getEspecimenColectorSecundarioDao().delete(especimenColectorSecundario);
                        }
                        if (especimen.getLocalidadID() != null && especimenDao.queryBuilder().where(EspecimenDao.Properties.LocalidadID.eq(especimen.getLocalidadID())).list().isEmpty()) {
                            daoSession.getLocalidadDao().delete(especimen.getLocalidad());
                        }
                        if (especimen.getFlor() != null) {
                            daoSession.getFlorDao().delete(especimen.getFlor());
                        }
                        if (especimen.getRaiz() != null) {
                            daoSession.getRaizDao().delete(especimen.getRaiz());
                        }
                        if (especimen.getTallo() != null) {
                            daoSession.getTalloDao().delete(especimen.getTallo());
                        }
                        if (especimen.getInflorescencia() != null) {
                            daoSession.getInflorescenciaDao().delete(especimen.getInflorescencia());
                        }
                        if (especimen.getFruto() != null) {
                            daoSession.getFrutoDao().delete(especimen.getFruto());
                        }
                        if (especimen.getHoja() != null) {
                            daoSession.getHojaDao().delete(especimen.getHoja());
                        }
                    }
                });
            }
        }
    }

    private void editSpecimen(Long id){
        Intent editEspecimenIntent = new Intent(getActivity().getApplicationContext(), CreateSpecimenActivity.class);
        editEspecimenIntent.putExtra("especimen", id);
        startActivityForResult(editEspecimenIntent, 0);
    }

    private class SpecimenListMultiSelectionActionMode extends ListMultiSelectionActionMode {

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
                            .setMessage(R.string.delete_specimen_confirmation_message)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteSpecimens();
                                    loadSpecimens();
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
            loadSpecimens();
        }

    }

    class LoadSpecimensTask extends AsyncTask<Long, Void, List<SpecimenListItem>> {

        private List<SpecimenListItem> specimenListItems;
        private View.OnClickListener onClickListener;

        public LoadSpecimensTask(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        protected void onPostExecute(List<SpecimenListItem> listItems) {
            if (listItems != null && !listItems.isEmpty()) {
                setListAdapter(new SpecimenListItemAdapter(getActivity().getApplicationContext(), listItems, onClickListener, getResources()));
            }
        }

        @Override
        protected List<SpecimenListItem> doInBackground(Long... params) {
            viaje = viajeDao.loadDeep(params[0]);
            viaje.resetEspecimenes();
            List<Especimen> especimens = viaje.getEspecimenes();
            if (especimens.size()>0){
                specimenListItems = new ArrayList<>(especimens.size());
                for (Especimen especimen : especimens) {
                    Fotografia fotografia;
                    especimen = daoSession.getEspecimenDao().loadDeep(especimen.getId());
                    String title = especimen.getNumeroDeColeccion();
                    String scientificName = especimen.getDeterminacionActual() != null ? especimen.getDeterminacionActual().aString() : null;
                    String specimenLocality = especimen.getLocalidad() != null ? especimen.getLocalidad().aString() : null;
                    String descriptionText = (especimen.getDescripcionEspecimen() != null && !especimen.getDescripcionEspecimen().isEmpty()) ? especimen.getDescripcionEspecimen() : especimen.aString();
                    if (especimen.getFotografias() != null && ! especimen.getFotografias().isEmpty()){
                        fotografia = especimen.getFotografias().iterator().next();
                        specimenListItems.add(new SpecimenListItem(especimen.getId(), title, scientificName, specimenLocality, descriptionText, fotografia.getRutaArchivo(), especimen.getLocalidad() != null));
                    }else{
                        specimenListItems.add(new SpecimenListItem(especimen.getId(), title, scientificName, specimenLocality, descriptionText, R.drawable.plantae, especimen.getLocalidad() != null));
                    }

                }
                itemsSelected = new Long[specimenListItems.size()];
            }
            return specimenListItems;
        }
    }

    class ExportCSVTask extends AsyncTask<String, Integer, Boolean> {

        private final ProgressDialog dialog = new ProgressDialog(getActivity());
        boolean memoryError = false;
        private File exportDir;

        @Override
        protected void onPreExecute() {
            this.dialog.setTitle(getActivity().getString(R.string.exporting_travel));
            this.dialog.setMessage(getActivity().getString(R.string.exporting_travel));
            this.dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.dialog.setProgress(0);
            this.dialog.setMax(100);
            this.dialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            this.dialog.incrementProgressBy(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            this.dialog.hide();
            super.onCancelled();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Plantae/csv");
            if ( ! exportDir.exists() && ! exportDir.mkdirs()){
                Log.d("Plantae", "failed to create directory");
                return null;
            }
            // Create a media file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            File CSVFile = new File(exportDir.getPath() + File.separator +
                        viaje.getNombre()+ timeStamp + ".csv");
            long freeBytesInternal = new File(getActivity().getApplicationContext().getFilesDir().getAbsoluteFile().toString()).getFreeSpace();
            long megAvailable = freeBytesInternal / 1048576;

            if (megAvailable < 0.1) {
                Log.d("Plantae","Please check"+megAvailable);
                memoryError = true;
            }else {
                try {
                    if (!CSVFile.createNewFile()) {
                        // TODO Show error and cancel task
                        Log.d("Plantae", "The csv file couldn't be created");
                    }
                    CSVWriter csvWriter = new CSVWriter(new FileWriter(CSVFile));
                    csvWriter.writeNext(csvHeader);
                    for (Especimen especimen:viaje.getEspecimenes()) {
                        String[] rowData = new String[csvHeader.length];
                        rowData[0] = especimen.getNumeroDeColeccion();
                        String fechaInicial = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(especimen.getFechaInicial());
                        String fechaFinal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(especimen.getFechaFinal());
                        rowData[1] = fechaInicial;
                        rowData[2] = fechaFinal;
                        rowData[3] = especimen.getMetodoColeccion();
                        rowData[4] = especimen.getEstacionDelAño();
                        String colectoresSecundarios = "";
                        for (EspecimenColectorSecundario especimenColectorSecundario:especimen.getColectoresSecundarios()) {
                            colectoresSecundarios = colectoresSecundarios.concat(especimenColectorSecundario.getColectorSecundario().getNombres().concat(" ").concat(especimenColectorSecundario.getColectorSecundario().getApellidos()));
                            if (especimen.getColectoresSecundarios().indexOf(especimenColectorSecundario) != especimen.getColectoresSecundarios().size()-1) {
                                colectoresSecundarios = colectoresSecundarios.concat("|");
                            }
                        }
                        rowData[5] = colectoresSecundarios;
                        if (especimen.getLocalidad() != null) {
                            rowData[6] = especimen.getLocalidad().getNombre();
                            if (especimen.getLocalidad().getRegion().getClass().equals(Municipio.class)){
                                rowData[7] = especimen.getLocalidad().getRegion().getNombre();
                                rowData[8] = especimen.getLocalidad().getRegion().getRegionPadre().getNombre();
                                rowData[9] = especimen.getLocalidad().getRegion().getRegionPadre().getRegionPadre().getNombre();
                            }else if (especimen.getLocalidad().getRegion().getClass().equals(Departamento.class)) {
                                rowData[8] = especimen.getLocalidad().getRegion().getNombre();
                                rowData[9] = especimen.getLocalidad().getRegion().getRegionPadre().getNombre();
                            }else{
                                rowData[9] = especimen.getLocalidad().getRegion().getNombre();
                            }
                            if (especimen.getLocalidad().getAltitudMinima() != null) {
                                rowData[10] = especimen.getLocalidad().getAltitudMinima().toString();
                            }
                            if (especimen.getLocalidad().getAltitudMaxima() != null) {
                                rowData[11] = especimen.getLocalidad().getAltitudMaxima().toString();
                            }
                            if (especimen.getLocalidad().getLatitud() != null) {
                                rowData[12] = especimen.getLocalidad().getLatitud().toString();
                            }
                            if (especimen.getLocalidad().getLongitud() != null) {
                                rowData[13] = especimen.getLocalidad().getLongitud().toString();
                            }
                            rowData[14] = especimen.getLocalidad().getDescripcion();
                        }
                        if (especimen.getDeterminacionActual() != null) {
                            if (especimen.getDeterminacionActual().getTaxon().getClass().equals(EpitetoEspecifico.class)) {
                                rowData[15] = especimen.getDeterminacionActual().getTaxon().getTaxonPadre().getTaxonPadre().getNombre();
                                rowData[16] = especimen.getDeterminacionActual().getTaxon().getTaxonPadre().getNombre();
                                rowData[17] = especimen.getDeterminacionActual().getTaxon().getNombre();
                            } else if (especimen.getDeterminacionActual().getTaxon().getClass().equals(Genero.class)){
                                rowData[15] = especimen.getDeterminacionActual().getTaxon().getTaxonPadre().getNombre();
                                rowData[16] = especimen.getDeterminacionActual().getTaxon().getNombre();
                                rowData[17] = "";
                            } else if (especimen.getDeterminacionActual().getTaxon().getClass().equals(Familia.class)){
                                rowData[15] = especimen.getDeterminacionActual().getTaxon().getNombre();
                                rowData[16] = "";
                                rowData[17] = "";
                            }
                            String fechaDeterminacion = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(especimen.getDeterminacionActual().getFechaIdentificacion());
                            rowData[18] = fechaDeterminacion;
                            rowData[19] = especimen.getDeterminacionActual().getDeterminador().getNombres() + "/" + especimen.getDeterminacionActual().getDeterminador().getApellidos();
                            rowData[20] = especimen.getDeterminacionActual().getTipo();
                            String nombresComunesText = "";
                            for (NombreComun nombreComun:especimen.getDeterminacionActual().getTaxon().getNombresComunes()){
                                nombresComunesText = nombresComunesText.concat(nombreComun.getNombre());
                                if (especimen.getDeterminacionActual().getTaxon().getNombresComunes().indexOf(nombreComun) != especimen.getDeterminacionActual().getTaxon().getNombresComunes().size()-1) {
                                    nombresComunesText = nombresComunesText.concat("|");
                                }
                            }
                            nombresComunesText = nombresComunesText.trim();
                            rowData[21] = nombresComunesText;
                            String usosText = "";
                            for (Uso uso:especimen.getDeterminacionActual().getTaxon().getUsos()) {
                                usosText = usosText.concat(uso.getDescripcion());
                                if (especimen.getDeterminacionActual().getTaxon().getUsos().indexOf(uso) != especimen.getDeterminacionActual().getTaxon().getUsos().size()-1) {
                                    usosText = usosText.concat("|");
                                }
                            }
                            usosText = usosText.trim();
                            rowData[22] = usosText;
                        }
                        if (especimen.getHabitat() != null) {
                            rowData[23] = especimen.getHabitat().getVegetacion();
                            rowData[24] = especimen.getHabitat().getSueloSustrato();
                            rowData[25] = especimen.getHabitat().getEspeciesAsociadas();
                        }
                        if (especimen.getHabito() != null) {
                            rowData[26] = especimen.getHabito().getHabito();
                        }
                        if (especimen.getFenologia() != null) {
                            rowData[27] = especimen.getFenologia().getFenologia();
                        }

                        if (especimen.getAlturaDeLaPlanta() != null) {
                            rowData[28] = especimen.getAlturaDeLaPlanta().toString();
                        }
                        if (especimen.getDap() != null) {
                            rowData[29] = especimen.getDap().toString();
                        }
                        rowData[30] = especimen.getAbundancia();
                        rowData[31] = especimen.getDescripcionEspecimen();
                        String coloresName = "";
                        String coloresDescripcion = "";
                        String coloresRGB = "";
                        String coloresMunsell = "";
                        for (ColorEspecimen colorEspecimen:especimen.getColores()) {
                            if (colorEspecimen.getOrganoDeLaPlanta() == null || colorEspecimen.getOrganoDeLaPlanta().isEmpty()) {
                                if (especimen.getColores().indexOf(colorEspecimen) != 0) {
                                    coloresName = coloresName.concat("|");
                                    coloresDescripcion = coloresDescripcion.concat("|");
                                    coloresRGB = coloresRGB.concat("|");
                                    coloresMunsell = coloresMunsell.concat("|");
                                }
                                coloresName = coloresName.concat(colorEspecimen.getNombre());
                                coloresDescripcion = coloresDescripcion.concat(colorEspecimen.getDescripcion());
                                coloresRGB = coloresRGB.concat(String.format("#%06X", (0xFFFFFF & colorEspecimen.getColorRGB())));
                                coloresMunsell = coloresMunsell.concat(colorEspecimen.getColorMunsell().aString());
                            }
                        }
                        rowData[32] = coloresName;
                        rowData[33] = coloresDescripcion;
                        rowData[34] = coloresRGB;
                        rowData[35] = coloresMunsell;
                        String muestrasAsociadasDescripcion = "";
                        String muestrasAsociadasMetodo = "";
                        for (MuestraAsociada muestraAsociada:especimen.getMuestrasAsociadas()) {
                            if (especimen.getMuestrasAsociadas().indexOf(muestraAsociada) != 0) {
                                muestrasAsociadasDescripcion = muestrasAsociadasDescripcion.concat("|");
                                muestrasAsociadasMetodo = muestrasAsociadasMetodo.concat("|");
                            }
                            muestrasAsociadasDescripcion = muestrasAsociadasDescripcion.concat(muestraAsociada.getDescripcion());
                            muestrasAsociadasMetodo = muestrasAsociadasMetodo.concat(muestraAsociada.getMetodoDeTratamiento());
                        }
                        rowData[36] = muestrasAsociadasDescripcion;
                        rowData[37] = muestrasAsociadasMetodo;
                        if (especimen.getFlor() != null) {
                            Flor flor = especimen.getFlor();
                            rowData[38] = flor.getDescripcion();
                            if (flor.getColorDeLaCorola() != null) {
                                rowData[39] = flor.getColorDeLaCorola().getNombre();
                                rowData[40] = flor.getColorDeLaCorola().getDescripcion();
                                rowData[41] = String.format("#%06X", (0xFFFFFF & flor.getColorDeLaCorola().getColorRGB()));
                                rowData[42] = flor.getColorDeLaCorola().getColorMunsell().aString();
                            }
                            if (flor.getColorDelCaliz() != null) {
                                rowData[43] = flor.getColorDelCaliz().getNombre();
                                rowData[44] = flor.getColorDelCaliz().getDescripcion();
                                rowData[45] = String.format("#%06X", (0xFFFFFF & flor.getColorDelCaliz().getColorRGB()));
                                rowData[46] = flor.getColorDelCaliz().getColorMunsell().aString();
                            }
                            if (flor.getColorDelGineceo() != null) {
                                rowData[47] = flor.getColorDelGineceo().getNombre();
                                rowData[48] = flor.getColorDelGineceo().getDescripcion();
                                rowData[49] = String.format("#%06X", (0xFFFFFF & flor.getColorDelGineceo().getColorRGB()));
                                rowData[50] = flor.getColorDelGineceo().getColorMunsell().aString();
                            }
                            if (flor.getColorDeLosEstambres() != null) {
                                rowData[51] = flor.getColorDeLosEstambres().getNombre();
                                rowData[52] = flor.getColorDeLosEstambres().getDescripcion();
                                rowData[53] = String.format("#%06X", (0xFFFFFF & flor.getColorDeLosEstambres().getColorRGB()));
                                rowData[54] = flor.getColorDeLosEstambres().getColorMunsell().aString();
                            }
                            if (flor.getColorDeLosEstigmas() != null) {
                                rowData[55] = flor.getColorDeLosEstigmas().getNombre();
                                rowData[56] = flor.getColorDeLosEstigmas().getDescripcion();
                                rowData[57] = String.format("#%06X", (0xFFFFFF & flor.getColorDeLosEstigmas().getColorRGB()));
                                rowData[58] = flor.getColorDeLosEstigmas().getColorMunsell().aString();
                            }
                            if (flor.getColorDeLosPistiliodios() != null) {
                                rowData[59] = flor.getColorDeLosPistiliodios().getNombre();
                                rowData[60] = flor.getColorDeLosPistiliodios().getDescripcion();
                                rowData[61] = String.format("#%06X", (0xFFFFFF & flor.getColorDeLosPistiliodios().getColorRGB()));
                                rowData[62] = flor.getColorDeLosPistiliodios().getColorMunsell().aString();
                            }
                        }
                        if (especimen.getFruto() != null) {
                            Fruto fruto = especimen.getFruto();
                            rowData[63] = fruto.getDescripcion();
                            rowData[64] = fruto.getConsistenciaDelPericarpio();
                            if (fruto.getColorDelExocarpio() != null) {
                                rowData[65] = fruto.getColorDelExocarpio().getNombre();
                                rowData[66] = fruto.getColorDelExocarpio().getDescripcion();
                                rowData[67] = String.format("#%06X", (0xFFFFFF & fruto.getColorDelExocarpio().getColorRGB()));
                                rowData[68] = fruto.getColorDelExocarpio().getColorMunsell().aString();
                            }
                            if (fruto.getColorDelMesocarpio() != null) {
                                rowData[69] = fruto.getColorDelMesocarpio().getNombre();
                                rowData[70] = fruto.getColorDelMesocarpio().getDescripcion();
                                rowData[71] = String.format("#%06X", (0xFFFFFF & fruto.getColorDelMesocarpio().getColorRGB()));
                                rowData[72] = fruto.getColorDelMesocarpio().getColorMunsell().aString();
                            }
                            if (fruto.getColorDelExocarpioInmaduro() != null) {
                                rowData[73] = fruto.getColorDelExocarpioInmaduro().getNombre();
                                rowData[74] = fruto.getColorDelExocarpioInmaduro().getDescripcion();
                                rowData[75] = String.format("#%06X", (0xFFFFFF & fruto.getColorDelExocarpioInmaduro().getColorRGB()));
                                rowData[76] = fruto.getColorDelExocarpioInmaduro().getColorMunsell().aString();
                            }
                            if (fruto.getColorDelMesocarpioInmaduro() != null) {
                                rowData[77] = fruto.getColorDelMesocarpioInmaduro().getNombre();
                                rowData[78] = fruto.getColorDelMesocarpioInmaduro().getDescripcion();
                                rowData[79] = String.format("#%06X", (0xFFFFFF & fruto.getColorDelMesocarpioInmaduro().getColorRGB()));
                                rowData[80] = fruto.getColorDelMesocarpioInmaduro().getColorMunsell().aString();
                            }
                        }
                        Inflorescencia inflorescencia = especimen.getInflorescencia();
                        if (inflorescencia != null) {
                            rowData[81] = inflorescencia.getDescripcion();
                            rowData[82] = inflorescencia.getNaturalezaDeLasBracteasPedunculares();
                            rowData[83] = inflorescencia.getNaturalezaDelProfilo();
                            rowData[84] = inflorescencia.getPosicionDeLasBracteasPedunculares();
                            rowData[85] = inflorescencia.getPosicionDeLasInflorescencias();
                            rowData[86] = inflorescencia.getRaquilas();
                            rowData[87] = inflorescencia.getRaquis();
                            rowData[88] = inflorescencia.getTamañoDeLasBracteasPedunculares();
                            rowData[89] = inflorescencia.getTamañoDelPedunculo();
                            rowData[90] = inflorescencia.getTamañoDelProfilo();
                            rowData[91] = inflorescencia.getTamañoDelRaquis();
                            rowData[92] = inflorescencia.getTamañoDeRaquilas();
                            if (inflorescencia.getColorDeLaInflorescenciaEnFlor() != null) {
                                rowData[93] = inflorescencia.getColorDeLaInflorescenciaEnFlor().getNombre();
                                rowData[94] = inflorescencia.getColorDeLaInflorescenciaEnFlor().getDescripcion();
                                rowData[95] = String.format("#%06X", (0xFFFFFF & inflorescencia.getColorDeLaInflorescenciaEnFlor().getColorRGB()));
                                rowData[96] = inflorescencia.getColorDeLaInflorescenciaEnFlor().getColorMunsell().aString();
                            }
                            if (inflorescencia.getColorDeLaInflorescenciaEnFruto() != null) {
                                rowData[97] = inflorescencia.getColorDeLaInflorescenciaEnFruto().getNombre();
                                rowData[98] = inflorescencia.getColorDeLaInflorescenciaEnFruto().getDescripcion();
                                rowData[99] = String.format("#%06X", (0xFFFFFF & inflorescencia.getColorDeLaInflorescenciaEnFruto().getColorRGB()));
                                rowData[100] = inflorescencia.getColorDeLaInflorescenciaEnFruto().getColorMunsell().aString();
                            }
                        }
                        Raiz raiz = especimen.getRaiz();
                        if (raiz != null) {
                            rowData[101] = raiz.getDescripcion();
                            rowData[102] = raiz.getDiametroDeLasRaices();
                            rowData[103] = raiz.getDiametroEnLaBase();
                            rowData[104] = raiz.getFormaDeLasEspinas();
                            rowData[105] = raiz.getTamañoDeLasEspinas();
                            rowData[106] = raiz.getRaizArmada().toString();
                            rowData[107] = raiz.getAlturaDelCono().toString();
                            if (raiz.getColorDelCono() != null) {
                                rowData[108] = raiz.getColorDelCono().getNombre();
                                rowData[109] = raiz.getColorDelCono().getDescripcion();
                                rowData[110] = String.format("#%06X", (0xFFFFFF & raiz.getColorDelCono().getColorRGB()));
                                rowData[111] = raiz.getColorDelCono().getColorMunsell().aString();
                            }
                        }
                        Hoja hoja = especimen.getHoja();
                        if (hoja != null) {
                            rowData[112] = hoja.getDescripcion();
                            rowData[113] = hoja.getCoberturaDelPeciolo();
                            rowData[114] = hoja.getDispocicionDeLasPinnas();
                            rowData[115] = hoja.getDisposicionDeLasHojas();
                            rowData[116] = hoja.getFormaDelPeciolo();
                            rowData[117] = hoja.getLonguitudDelRaquis();
                            rowData[118] = hoja.getNaturalezaDeLaVaina();
                            rowData[119] = hoja.getNaturalezaDelLimbo();
                            rowData[120] = hoja.getNumeroDePinnas();
                            rowData[121] = hoja.getNumeroHojas();
                            rowData[122] = hoja.getTamañoDeLaVaina();
                            rowData[123] = hoja.getTamañoDelPeciolo();
                            if (hoja.getColorDeLaVaina() != null) {
                                rowData[124] = hoja.getColorDeLaVaina().getNombre();
                                rowData[125] = hoja.getColorDeLaVaina().getDescripcion();
                                rowData[126] = String.format("#%06X", (0xFFFFFF & hoja.getColorDeLaVaina().getColorRGB()));
                                rowData[127] = hoja.getColorDeLaVaina().getColorMunsell().aString();
                            }
                            if (hoja.getColorDelPeciolo() != null) {
                                rowData[128] = hoja.getColorDelPeciolo().getNombre();
                                rowData[129] = hoja.getColorDelPeciolo().getDescripcion();
                                rowData[130] = String.format("#%06X", (0xFFFFFF & hoja.getColorDelPeciolo().getColorRGB()));
                                rowData[131] = hoja.getColorDelPeciolo().getColorMunsell().aString();
                            }
                        }
                        Tallo tallo = especimen.getTallo();
                        if (tallo != null) {
                            rowData[132] = tallo.getDescripcion();
                            rowData[133] = tallo.getDesnudoCubierto().toString();
                            rowData[134] = tallo.getEntrenudosConspicuos().toString();
                            rowData[135] = tallo.getEspinas().toString();
                            if (tallo.getColorDelTallo() != null) {
                                rowData[136] = tallo.getColorDelTallo().getNombre();
                                rowData[137] = tallo.getColorDelTallo().getDescripcion();
                                rowData[138] = String.format("#%06X", (0xFFFFFF & tallo.getColorDelTallo().getColorRGB()));
                                rowData[139] = tallo.getColorDelTallo().getColorMunsell().aString();
                            }
                        }
                        csvWriter.writeNext(rowData);
                        publishProgress(100/viaje.getEspecimenes().size());
                    }
                    csvWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                this.dialog.hide();
                SuperToast superToast = new SuperToast(getActivity());
                superToast.setDuration(SuperToast.Duration.EXTRA_LONG);
                superToast.setText(getActivity().getString(R.string.csv_exported_succesfully) + exportDir.getAbsolutePath());
                superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                superToast.show();
            }
            super.onPostExecute(aBoolean);
        }
    }
}