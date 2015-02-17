package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.EspecimenDao;
import edu.udistrital.plantae.persistencia.ViajeDao;
import edu.udistrital.plantae.ui.adapter.SpecimenListItemAdapter;
import edu.udistrital.plantae.utils.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gerson Sosa on 5/5/14.
 */
public class SpecimenListFragment extends ListFragment implements View.OnClickListener{

    public static final String[] csvHeader = {"Numero Colección", "Fecha inicial recolección", "Fecha final recolección", "Método de colección", "Estación del año", "Colectores Secundarios", "Nombre localidad", "Municipio", "Departamento", "Pais", "Elevación mínima", "Elevación máxima", "Latitud", "Longitud", "Descripción localidad", "Familia", "Genero", "Especie", "Fecha determinación", "Determinador", "Tipo", "Nombres comunes", "Usos", "Vegetacion", "Suelo/Sustrato", "Especies asociadas", "Habito", "Fenologia", "Altura de la Planta", "DAP", "Abundancia", "Descripción de la planta", "Nombre colores", "Descrición colores", "RGB colores", "Munsell colores", "Descripción muestras asociadas", "Método tratamiento muestras asociadas;", "Descripción flor", "Nombre color de la corola", "Descripción color de la corola", "RGB color de la corola", "Munsell color de la corola", "Nombre color del caliz", "Descripción color del caliz", "RGB color del caliz", "Munsell color del caliz", "Nombre color del gineceo", "Descripción color del gineceo", "RGB color del gineceo", "Munsell color del gineceo", "Nombre color de los estambres", "Descripción color de los estambres", "RGB color de los estambres", "Munsell color de los estambres", "Nombre color de los estigmas", "Descripción color de los estigmas", "RGB color de los estigmas", "Munsell color de los estigmas", "Nombre color de los pistiliodios", "Descripción color de los pistiliodios", "RGB color de los pistiliodios", "Munsell color de los pistiliodios", "Descripción fruto", "Consistencia del pericarpio", "Nombre color del endocarpio", "Descripción color del endocarpio", "RGB color del endocarpio", "Munsell color del endocarpio", "Nombre color del exocarpio", "Descripción color del exocarpio", "RGB color del exocarpio", "Munsell color del exocarpio", "Descripción inflorescencia", "Naturaleza de las bracteas pedunculares", "Naturaleza del profilo", "Posicion de las bracteas pedunculares", "Posicion de las inflorescencias", "Raquilas", "Raquis", "Tamaño de las bracteas pedunculares", "Tamaño del pedunculo", "Tamaño del profilo", "Tamaño del raquis", "Tamaño de raquilas", "Nombre color de la inflorescencia en flor", "Descripción color de la inflorescencia en flor", "RGB color de la inflorescencia en flor", "Munsell color de la inflorescencia en flor", "Nombre color de la inflorescencia en fruto", "Descripción color de la inflorescencia en fruto", "RGB color de la inflorescencia en fruto", "Munsell color de la inflorescencia en fruto", "Descripción raiz", "Diametro de las raices", "Diametro en la base", "Forma de las espinas", "Tamaño de las espinas", "Raiz armada().to string", "Altura del cono().to string", "Descripcion", "Cobertura del peciolo", "Dispocicion de las pinnas", "Disposicion de las hojas", "Forma del peciolo", "Longuitud del raquis", "Naturaleza de la vaina", "Naturaleza del limbo", "Numero de pinnas", "Numero hojas", "Tamaño de las hojas", "Tamaño del peciolo", "Nombre color de las hojas", "Descripción color de las hojas", "RGB color de las hojas", "Munsell color de las hojas", "Nombre color del peciolo", "Descripción color del peciolo", "RGB color del peciolo", "Munsell color del peciolo", "Descripción tallo", "Desnudo cubierto", "Entrenudos conspicuos", "Espinas", "Nombre color del tallo", "Descripción color del tallo", "RGB color del tallo", "Munsell color del tallo"};
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
            case R.id.action_rebuild_journey:
                Intent trayectoIntent = new Intent(getActivity().getApplicationContext(), TrayectoActivity.class);
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
        Toast.makeText(getActivity(), "Click en especimen" + v.getId(),Toast.LENGTH_SHORT);
    }

    @Override
    public void onClick(View v) {
        int position = getListView().getPositionForView(v);
        ImageView imageView = (ImageView) v;
        SpecimenListItem listItem = ((SpecimenListItem)getListAdapter().getItem(position));
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
                        if (especimenDao.queryBuilder().where(EspecimenDao.Properties.LocalidadID.eq(especimen.getLocalidadID())).list().isEmpty()) {
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
        editEspecimenIntent.putExtra("viaje", viaje.getId());
        editEspecimenIntent.putExtra("especimen", id);
        startActivityForResult(editEspecimenIntent, 0);
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
                                    loadSpecimens();
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
                    String title = especimen.getNumeroDeColeccion();
                    String scientificName = especimen.getDeterminacionActual() != null ? especimen.getDeterminacionActual().toString() : null;
                    String specimenLocality = especimen.getLocalidad() != null ? especimen.getLocalidad().toString() : null;
                    String descriptionText = especimen.getDescripcionEspecimen() != null ? especimen.getDescripcionEspecimen() : especimen.toString();
                    if (especimen.getFotografias() != null && ! especimen.getFotografias().isEmpty()){
                        fotografia = especimen.getFotografias().iterator().next();
                        specimenListItems.add(new SpecimenListItem(especimen.getId(), title, scientificName, specimenLocality, descriptionText, fotografia.getRutaArchivo(), especimen.getLocalidad() != null));
                    }else{
                        specimenListItems.add(new SpecimenListItem(especimen.getId(), title, scientificName, specimenLocality, descriptionText, R.drawable.ic_action_location_map, especimen.getLocalidad() != null));
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
            File exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Plantae/csv");
            if ( ! exportDir.exists() && ! exportDir.mkdirs()){
                Log.d("Plantae", "failed to create directory");
                return null;
            }
            // Create a media file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
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
                        String fechaInicial = new SimpleDateFormat("yyyy-MM-dd").format(especimen.getFechaInicial());
                        String fechaFinal = new SimpleDateFormat("yyyy-MM-dd").format(especimen.getFechaFinal());
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
                            rowData[10] = especimen.getLocalidad().getAltitudMinima().toString();
                            rowData[11] = especimen.getLocalidad().getAltitudMaxima().toString();
                            rowData[12] = especimen.getLocalidad().getLatitud().toString();
                            rowData[13] = especimen.getLocalidad().getLongitud().toString();
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
                            String fechaDeterminacion = new SimpleDateFormat("yyyy-MM-dd").format(especimen.getDeterminacionActual().getFechaIdentificacion());
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
                            if (!isOrganColor(especimen, colorEspecimen)) {
                                if (especimen.getColores().indexOf(colorEspecimen) != 0) {
                                    coloresName = coloresName.concat("|");
                                    coloresDescripcion = coloresDescripcion.concat("|");
                                    coloresRGB = coloresRGB.concat("|");
                                    coloresMunsell = coloresMunsell.concat("|");
                                }
                                coloresName = coloresName.concat(colorEspecimen.getNombre());
                                coloresDescripcion = coloresDescripcion.concat(colorEspecimen.getDescripcion());
                                coloresRGB = coloresRGB.concat(String.format("#%06X", (0xFFFFFF & colorEspecimen.getColorRGB())));
                                coloresMunsell = coloresMunsell.concat(colorEspecimen.getColorMunsell().toString());
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
                                rowData[42] = flor.getColorDeLaCorola().getColorMunsell().toString();
                            }
                            if (flor.getColorDelCaliz() != null) {
                                rowData[43] = flor.getColorDelCaliz().getNombre();
                                rowData[44] = flor.getColorDelCaliz().getDescripcion();
                                rowData[45] = String.format("#%06X", (0xFFFFFF & flor.getColorDelCaliz().getColorRGB()));
                                rowData[46] = flor.getColorDelCaliz().getColorMunsell().toString();
                            }
                            if (flor.getColorDelGineceo() != null) {
                                rowData[47] = flor.getColorDelGineceo().getNombre();
                                rowData[48] = flor.getColorDelGineceo().getDescripcion();
                                rowData[49] = String.format("#%06X", (0xFFFFFF & flor.getColorDelGineceo().getColorRGB()));
                                rowData[50] = flor.getColorDelGineceo().getColorMunsell().toString();
                            }
                            if (flor.getColorDeLosEstambres() != null) {
                                rowData[51] = flor.getColorDeLosEstambres().getNombre();
                                rowData[52] = flor.getColorDeLosEstambres().getDescripcion();
                                rowData[53] = String.format("#%06X", (0xFFFFFF & flor.getColorDeLosEstambres().getColorRGB()));
                                rowData[54] = flor.getColorDeLosEstambres().getColorMunsell().toString();
                            }
                            if (flor.getColorDeLosEstigmas() != null) {
                                rowData[55] = flor.getColorDeLosEstigmas().getNombre();
                                rowData[56] = flor.getColorDeLosEstigmas().getDescripcion();
                                rowData[57] = String.format("#%06X", (0xFFFFFF & flor.getColorDeLosEstigmas().getColorRGB()));
                                rowData[58] = flor.getColorDeLosEstigmas().getColorMunsell().toString();
                            }
                            if (flor.getColorDeLosPistiliodios() != null) {
                                rowData[59] = flor.getColorDeLosPistiliodios().getNombre();
                                rowData[60] = flor.getColorDeLosPistiliodios().getDescripcion();
                                rowData[61] = String.format("#%06X", (0xFFFFFF & flor.getColorDeLosPistiliodios().getColorRGB()));
                                rowData[62] = flor.getColorDeLosPistiliodios().getColorMunsell().toString();
                            }
                        }
                        if (especimen.getFruto() != null) {
                            Fruto fruto = especimen.getFruto();
                            rowData[63] = fruto.getDescripcion();
                            rowData[64] = fruto.getConsistenciaDelPericarpio();
                            if (fruto.getColorDelEndocarpio() != null) {
                                rowData[65] = fruto.getColorDelEndocarpio().getNombre();
                                rowData[66] = fruto.getColorDelEndocarpio().getDescripcion();
                                rowData[67] = String.format("#%06X", (0xFFFFFF & fruto.getColorDelEndocarpio().getColorRGB()));
                                rowData[68] = fruto.getColorDelEndocarpio().getColorMunsell().toString();
                            }
                            if (fruto.getColorDelExocarpio() != null) {
                                rowData[69] = fruto.getColorDelExocarpio().getNombre();
                                rowData[70] = fruto.getColorDelExocarpio().getDescripcion();
                                rowData[71] = String.format("#%06X", (0xFFFFFF & fruto.getColorDelExocarpio().getColorRGB()));
                                rowData[72] = fruto.getColorDelExocarpio().getColorMunsell().toString();
                            }
                        }
                        Inflorescencia inflorescencia = especimen.getInflorescencia();
                        if (inflorescencia != null) {
                            rowData[73] = inflorescencia.getDescripcion();
                            rowData[74] = inflorescencia.getNaturalezaDeLasBracteasPedunculares();
                            rowData[75] = inflorescencia.getNaturalezaDelProfilo();
                            rowData[76] = inflorescencia.getPosicionDeLasBracteasPedunculares();
                            rowData[77] = inflorescencia.getPosicionDeLasInflorescencias();
                            rowData[78] = inflorescencia.getRaquilas();
                            rowData[79] = inflorescencia.getRaquis();
                            rowData[80] = inflorescencia.getTamañoDeLasBracteasPedunculares();
                            rowData[81] = inflorescencia.getTamañoDelPedunculo();
                            rowData[82] = inflorescencia.getTamañoDelProfilo();
                            rowData[83] = inflorescencia.getTamañoDelRaquis();
                            rowData[84] = inflorescencia.getTamañoDeRaquilas();
                            if (inflorescencia.getColorDeLaInflorescenciaEnFlor() != null) {
                                rowData[85] = inflorescencia.getColorDeLaInflorescenciaEnFlor().getNombre();
                                rowData[86] = inflorescencia.getColorDeLaInflorescenciaEnFlor().getDescripcion();
                                rowData[87] = String.format("#%06X", (0xFFFFFF & inflorescencia.getColorDeLaInflorescenciaEnFlor().getColorRGB()));
                                rowData[88] = inflorescencia.getColorDeLaInflorescenciaEnFlor().getColorMunsell().toString();
                            }
                            if (inflorescencia.getColorDeLaInflorescenciaEnFruto() != null) {
                                rowData[89] = inflorescencia.getColorDeLaInflorescenciaEnFruto().getNombre();
                                rowData[90] = inflorescencia.getColorDeLaInflorescenciaEnFruto().getDescripcion();
                                rowData[91] = String.format("#%06X", (0xFFFFFF & inflorescencia.getColorDeLaInflorescenciaEnFruto().getColorRGB()));
                                rowData[92] = inflorescencia.getColorDeLaInflorescenciaEnFruto().getColorMunsell().toString();
                            }
                        }
                        Raiz raiz = especimen.getRaiz();
                        if (raiz != null) {
                            rowData[93] = raiz.getDescripcion();
                            rowData[94] = raiz.getDiametroDeLasRaices();
                            rowData[95] = raiz.getDiametroEnLaBase();
                            rowData[96] = raiz.getFormaDeLasEspinas();
                            rowData[97] = raiz.getTamañoDeLasEspinas();
                            rowData[98] = raiz.getRaizArmada().toString();
                            rowData[99] = raiz.getAlturaDelCono().toString();
                        }
                        Hoja hoja = especimen.getHoja();
                        if (hoja != null) {
                            rowData[100] = hoja.getDescripcion();
                            rowData[101] = hoja.getCoberturaDelPeciolo();
                            rowData[102] = hoja.getDispocicionDeLasPinnas();
                            rowData[103] = hoja.getDisposicionDeLasHojas();
                            rowData[104] = hoja.getFormaDelPeciolo();
                            rowData[105] = hoja.getLonguitudDelRaquis();
                            rowData[106] = hoja.getNaturalezaDeLaVaina();
                            rowData[107] = hoja.getNaturalezaDelLimbo();
                            rowData[108] = hoja.getNumeroDePinnas();
                            rowData[109] = hoja.getNumeroHojas();
                            rowData[110] = hoja.getTamañoDeLasHojas();
                            rowData[111] = hoja.getTamañoDelPeciolo();
                            if (hoja.getColorDeLasHojas() != null) {
                                rowData[112] = hoja.getColorDeLasHojas().getNombre();
                                rowData[113] = hoja.getColorDeLasHojas().getDescripcion();
                                rowData[114] = String.format("#%06X", (0xFFFFFF & hoja.getColorDeLasHojas().getColorRGB()));
                                rowData[115] = hoja.getColorDeLasHojas().getColorMunsell().toString();
                            }
                            if (hoja.getColorDelPeciolo() != null) {
                                rowData[116] = hoja.getColorDelPeciolo().getNombre();
                                rowData[117] = hoja.getColorDelPeciolo().getDescripcion();
                                rowData[118] = String.format("#%06X", (0xFFFFFF & hoja.getColorDelPeciolo().getColorRGB()));
                                rowData[119] = hoja.getColorDelPeciolo().getColorMunsell().toString();
                            }
                        }
                        Tallo tallo = especimen.getTallo();
                        if (tallo != null) {
                            rowData[120] = tallo.getDescripcion();
                            rowData[121] = tallo.getDesnudoCubierto().toString();
                            rowData[122] = tallo.getEntrenudosConspicuos().toString();
                            rowData[123] = tallo.getEspinas().toString();
                            if (tallo.getColorDelTallo() != null) {
                                rowData[124] = tallo.getColorDelTallo().getNombre();
                                rowData[125] = tallo.getColorDelTallo().getDescripcion();
                                rowData[126] = String.format("#%06X", (0xFFFFFF & tallo.getColorDelTallo().getColorRGB()));
                                rowData[127] = tallo.getColorDelTallo().getColorMunsell().toString();
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

        private boolean isOrganColor(Especimen especimen, ColorEspecimen colorEspecimen) {
            if (especimen.getFlor() != null) {
                if (especimen.getFlor().getColorDeLaCorola() != null && especimen.getFlor().getColorDeLaCorola().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
                if (especimen.getFlor().getColorDelCaliz() != null && especimen.getFlor().getColorDelCaliz().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
                if (especimen.getFlor().getColorDelGineceo() != null && especimen.getFlor().getColorDelGineceo().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
                if (especimen.getFlor().getColorDeLosEstambres() != null && especimen.getFlor().getColorDeLosEstambres().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
                if (especimen.getFlor().getColorDeLosEstigmas() != null && especimen.getFlor().getColorDeLosEstigmas().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
                if (especimen.getFlor().getColorDeLosPistiliodios() != null && especimen.getFlor().getColorDeLosPistiliodios().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
            }
            if (especimen.getFruto() != null) {
                if (especimen.getFruto().getColorDelEndocarpio() != null && especimen.getFruto().getColorDelEndocarpio().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
                if (especimen.getFruto().getColorDelExocarpio() != null && especimen.getFruto().getColorDelExocarpio().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
            }
            if (especimen.getHoja() != null) {
                if (especimen.getHoja().getColorDeLasHojas() != null && especimen.getHoja().getColorDeLasHojas().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
                if (especimen.getHoja().getColorDelPeciolo() != null && especimen.getHoja().getColorDelPeciolo().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
            }
            if (especimen.getInflorescencia() != null) {
                if (especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor() != null && especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
                if (especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto() != null && especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
            }
            if (especimen.getTallo() != null) {
                if (especimen.getTallo().getColorDelTallo() != null && especimen.getTallo().getColorDelTallo().getId().equals(colorEspecimen.getId())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                this.dialog.hide();
            }
            super.onPostExecute(aBoolean);
        }
    }
}