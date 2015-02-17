package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.*;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.logicadominio.recoleccion.ViajeColectorSecundario;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.*;
import edu.udistrital.plantae.ui.view.SlidingTabLayout;
import edu.udistrital.plantae.ui.view.ViewPagerPlantae;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Gerson Sosa on 5/15/14.
 */
public class CreateSpecimenActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener,
        SecondaryCollectorsListFragment.OnSecondaryCollectorSelectedListener,
        CollectingInformationFragment.OnEditModeStarted,
        LocalityInformationFragment.OnLocationUpdateRequest,
        PlantAttributesFragment.OnEditModeStarted,
        AssociatedSamplesFragment.OnAssociatedSampleListener,
        ColorsFragment.OnColorChangedListener {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int IMAGE_GALLERY_ACTIVITY_REQUEST_CODE = 101;
    private EspecimenDTO especimenDTO;
    private DaoSession daoSession;
    private int specimenType;
    private GoogleApiClient googleApiClient;
    private SpecimenPagesAdapter pagesAdapter;
    private LocationRequest locationRequest;
    private String picturePath;
    private List<ColectorSecundario> colectoresSecundarios;
    private CollectingInformationFragment collectingInformationFragment;
    private SecondaryCollectorsListFragment secondaryCollectorsListFragment;
    private SlidingTabLayout slidingTabLayout;
    private ViewPagerPlantae viewPager;
    private PlantAttributesFragment plantAttributesFragment;
    private FlowersInformationFragment flowersInformationFragment;
    private FruitInformationFragment fruitInformationFragment;
    private InflorescenceInformationFragment inflorescenceInformationFragment;
    private Viaje viaje;
    private boolean editMode;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_specimen);

        // Load Google Play Services for LocationRequest
        servicesConnected();

        // Setup the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_back);
        setSupportActionBar(toolbar);

        daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();

        // Get the specimen type and travel id from Intent
        specimenType = getIntent().getIntExtra("specimenType", 0);
        final Long viajeId = getIntent().getLongExtra("viaje", 0l);
        viaje = daoSession.getViajeDao().load(viajeId);

        // Check if the specimen is new or in editing mode
        final Long especimen = getIntent().getLongExtra("especimen", 0l);
        if (especimen != 0l) {
            editMode = true;
            especimenDTO = new EspecimenDTO(daoSession.getEspecimenDao().loadDeep(especimen));
            loadSecondaryCollectorsFromSpecimen(especimenDTO.getColectoresSecundarios());
        }else{
            ColectorPrincipal colectorPrincipal = daoSession
                    .getViajeDao().load(viajeId).getColectorPrincipal();
            especimenDTO = new EspecimenDTO();
            especimenDTO.setViajeID(viajeId);
            especimenDTO.setColectorPrincipalID(colectorPrincipal.getId());
            especimenDTO.setNumeroDeColeccion(colectorPrincipal.generarNumeroDeColeccion());
            especimenDTO.setFechaInicial(new Date(System.currentTimeMillis()));
            especimenDTO.setUsuarioId(colectorPrincipal.getPersona().getUsuarioID());
            final List<ViajeColectorSecundario> viajeColectorSecundarios = daoSession.getViajeColectorSecundarioDao().queryBuilder().where(ViajeColectorSecundarioDao.Properties.ViajeID.eq(viajeId)).list();
            loadSecondaryCollectorsFromTravel(viajeColectorSecundarios);
        }

        pagesAdapter = new SpecimenPagesAdapter(getSupportFragmentManager(), specimenType, this, especimenDTO, secondaryCollectorsNames());

        collectingInformationFragment = (CollectingInformationFragment) pagesAdapter.getItem(0);
        plantAttributesFragment = (PlantAttributesFragment) pagesAdapter.getItem(4);
        flowersInformationFragment = (FlowersInformationFragment) pagesAdapter.getItem(5);

        viewPager = (ViewPagerPlantae) findViewById(R.id.specimen_view_pager);
        viewPager.setAdapter(pagesAdapter);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setViewPager(viewPager);

         /*
         * Create a new location client, using the enclosing class to
         * handle callbacks.
         */
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        if (savedInstanceState != null) {
            picturePath = savedInstanceState.getString("photo_uri");
            especimenDTO = savedInstanceState.getParcelable("especimen");
            loadSecondaryCollectorsFromSpecimen(especimenDTO.getColectoresSecundarios());
            secondaryCollectorsListFragment.updateSelectedSecondaryCollectors(getSelectedSecondaryCollectors());
            plantAttributesFragment.updateAssociatedSamplesText(textAssociatedSamples());
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    private void loadSecondaryCollectorsFromTravel(List<ViajeColectorSecundario> viajeColectorSecundarios) {
        colectoresSecundarios = new ArrayList<>();
        for (ViajeColectorSecundario viajeColectorSecundario:viajeColectorSecundarios) {
            colectoresSecundarios.add(viajeColectorSecundario.getColectorSecundario());
        }
    }

    private void loadSecondaryCollectorsFromSpecimen(List<EspecimenColectorSecundario> especimenColectorSecundarios) {
        colectoresSecundarios = new ArrayList<>();
        for (EspecimenColectorSecundario especimenColectorSecundario:especimenColectorSecundarios) {
            colectoresSecundarios.add(especimenColectorSecundario.getColectorSecundario());
        }
    }

    /*
     * Called when the Activity becomes visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        if (!editMode) {
            googleApiClient.connect();
        }
    }

    /*
     * Called when the Activity is no longer visible.
     */
    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        googleApiClient.disconnect();
        super.onStop();
    }

    private boolean servicesConnected() {
        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.
                        isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates",
                    "Google Play services is available.");
            // Continue
            return true;
            // Google Play services was not available for some reason.
            // resultCode holds the error code.
        } else {
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                    resultCode,
                    this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                MainActivity.ErrorDialogFragment errorFragment =
                        new MainActivity.ErrorDialogFragment();
                // Set the dialog in the DialogFragment
                errorFragment.setDialog(errorDialog);
                // Show the error dialog in the DialogFragment
                errorFragment.show(getSupportFragmentManager(),
                        "Location Updates");
            }
            return false;
        }
    }

    /*
     * Called by Location Services when the request to connect the
     * client finishes successfully. At this point, you can
     * request the current location or start periodic updates
     */
    @Override
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setNumUpdates(1);

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
    @Override
    public void onConnectionSuspended(int i) {
        // Display the connection status
        Log.i("Plantae", "Disconnected. Please re-connect.");
    }

    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            showErrorDialog(connectionResult.getErrorCode());
        }
    }

    // Define the callback method that receives location updates
    @Override
    public void onLocationChanged(Location location) {
        ((LocalityInformationFragment) pagesAdapter.getItem(1)).updateLocation(location);
        googleApiClient.disconnect();
    }

    private void showErrorDialog(int errorCode) {
        Log.d("Plantae", "Error location services" + errorCode);
    }

    @Override
    public void onSecondaryCollectorAdded(ColectorSecundario colectorSecundario) {
        colectoresSecundarios.add(colectorSecundario);
        collectingInformationFragment.updateSecodaryCollectorsText(secondaryCollectorsNames());
    }

    @Override
    public void onSecondaryCollectorRemoved(ColectorSecundario colectorSecundario) {
        colectoresSecundarios.remove(colectorSecundario);
        collectingInformationFragment.updateSecodaryCollectorsText(secondaryCollectorsNames());
    }

    @Override
    public void onActionModeFinished() {
        if (collectingInformationFragment.inEditMode()) {
            collectingInformationFragment.hideSecondaryCollectorList();
        }
    }

    private String secondaryCollectorsNames() {
        StringBuilder stringBuilder = new StringBuilder();
        if (colectoresSecundarios.size() == 0) {
            return getString(R.string.no_secondary_collectors_selected);
        }else if (colectoresSecundarios.size() > 0) {
            Iterator<ColectorSecundario> iterator = colectoresSecundarios.iterator();
            for (int i = 0; i < colectoresSecundarios.size(); i++) {
                ColectorSecundario colectorSecundario = iterator.next();
                if (i > 0) {
                    if (i == colectoresSecundarios.size() - 1) {
                        stringBuilder.append(" ").append(getString(R.string.and)).append(" ");
                    }else {
                        stringBuilder.append(getString(R.string.comma)).append(" ");
                    }
                }
                stringBuilder.append(colectorSecundario.getPersona().getNombres()).append(" ").append(colectorSecundario.getPersona().getApellidos());
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_specimen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveSpecimen();
                setResult(RESULT_OK);
                finish();
                return true;
            case R.id.action_photo:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                     Uri fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                    if (fileUri != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
                        picturePath = fileUri.getPath();
                        startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                    }else{
                        Toast.makeText(this, R.string.plantae_failed_create_directory, Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            case R.id.action_pictures:
                Intent pictureGalleryIntent = new Intent(getApplicationContext(), PictureGalleryActivity.class);
                pictureGalleryIntent.putParcelableArrayListExtra("fotografias", (ArrayList<? extends Parcelable>) especimenDTO.getFotografias());
                pictureGalleryIntent.putExtra("numeroColeccion", especimenDTO.getNumeroDeColeccion());
                startActivityForResult(pictureGalleryIntent, IMAGE_GALLERY_ACTIVITY_REQUEST_CODE);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CONNECTION_FAILURE_RESOLUTION_REQUEST :
                /*
                 * If the result code is Activity.RESULT_OK, try
                 * to connect again
                 */
                switch (resultCode) {
                    case Activity.RESULT_OK :
                        /*
                        * Try the request again
                        */
                        servicesConnected();
                        break;
                }
                break;
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    // Image captured and saved to fileUri specified in the Intent
                    Toast.makeText(this, "Image saved to:\n" +
                            picturePath, Toast.LENGTH_LONG).show();
                    addPicture();
                } else if (resultCode == RESULT_CANCELED) {
                    // User cancelled the image capture
                    Toast.makeText(this, "No image saved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Image capture failed", Toast.LENGTH_LONG).show();
                }
                break;
            case IMAGE_GALLERY_ACTIVITY_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    List<Fotografia> fotografias = new ArrayList<Fotografia>();
                    for (Parcelable parcelable : data.getParcelableArrayListExtra("fotografias")) {
                        fotografias.add((Fotografia) parcelable);
                    }
                    especimenDTO.setFotografias(fotografias);
                }
                break;
            case SecondaryCollectorsListFragment.SECONDARY_COLLECTOR_CREATION_REQUEST:
                collectingInformationFragment.onActivityResult(requestCode,resultCode,data);
                break;
            case AssociatedSamplesFragment.ASSOCIATED_SAMPLE_CREATION_REQUEST:
                plantAttributesFragment.onActivityResult(requestCode, resultCode, data);
                break;
            case ColorsFragment.COLOR_CREATION_REQUEST:
                plantAttributesFragment.onActivityResult(requestCode, resultCode, data);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addPicture() {
        final List<Fotografia> especimenDTOFotografias = especimenDTO.getFotografias();
        if (especimenDTOFotografias == null) {
            especimenDTO.setFotografias(new ArrayList<Fotografia>(1));
        }
        Fotografia fotografia = new Fotografia();
        fotografia.setContexto(especimenDTO.getNumeroDeColeccion());
        fotografia.setRutaArchivo(picturePath);
        especimenDTOFotografias.add(fotografia);
    }

    private List<EspecimenColectorSecundario> convertColectoresSecundariosEspecimen(){
        ArrayList<EspecimenColectorSecundario> especimenColectorSecundarios = new ArrayList<>(colectoresSecundarios.size());
        for (ColectorSecundario colectorSecundario:colectoresSecundarios) {
            EspecimenColectorSecundario especimenColectorSecundario = new EspecimenColectorSecundario();
            especimenColectorSecundario.setColectorSecundario(colectorSecundario);
            especimenColectorSecundarios.add(especimenColectorSecundario);
        }
        return especimenColectorSecundarios;
    }

    private void saveSpecimen(){
        // Persist the specimen
        especimenDTO.setColectoresSecundarios(convertColectoresSecundariosEspecimen());
        viaje.agregarEspecimen(especimenDTO);
    }

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    /** Create a file Uri for saving an image or video */
    public static Uri getOutputMediaFileUri(int type){
        Uri uri;
        try {
            uri = Uri.fromFile(getOutputMediaFile(type));
        }catch (NullPointerException e) {
            return null;
        }
        return uri;
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Plantae/images");
        /*if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "Plantae");
        }else{
            mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "plantae/images");
        }*/
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if ( ! mediaStorageDir.exists() && ! mediaStorageDir.mkdirs()){
            Log.d("Plantae", "failed to create directory");
            return null;
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (picturePath != null) {
            outState.putString("photo_uri", picturePath);
            outState.putParcelable("especimen", especimenDTO);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSecondaryCollectorsEditModeStarted() {
        slidingTabLayout.setVisibility(View.GONE);
        viewPager.setPagingEnabled(false);
        secondaryCollectorsListFragment = collectingInformationFragment.getFragmentSecondaryCollectosList();
        secondaryCollectorsListFragment.updateSelectedSecondaryCollectors(getSelectedSecondaryCollectors());
    }

    private Long[] getSelectedSecondaryCollectors() {
        List<ColectorSecundario> secondaryCollectors = daoSession.getColectorSecundarioDao().loadAll();
        final Long[] itemsSelected = new Long[secondaryCollectors.size()];
        int pos = 0;
        for (ColectorSecundario colectorSecundario:secondaryCollectors) {
            if (colectoresSecundarios.contains(colectorSecundario)) {
                itemsSelected[pos] = colectorSecundario.getId();
            }
            pos++;
        }
        return itemsSelected;
    }

    public void onAssociaedSamplesEditModeStarted(){
        slidingTabLayout.setVisibility(View.GONE);
        viewPager.setPagingEnabled(false);
    }
    public void onColorsEditModeStarted(){
        slidingTabLayout.setVisibility(View.GONE);
        viewPager.setPagingEnabled(false);
    }

    @Override
    public void onEditModeFinished() {
        slidingTabLayout.setVisibility(View.VISIBLE);
        viewPager.setPagingEnabled(true);
    }

    @Override
    public void onAssociatedSampleAdded(MuestraAsociada muestraAsociada) {
        especimenDTO.getMuestrasAsociadas().add(muestraAsociada);
        plantAttributesFragment.updateAssociatedSamplesText(textAssociatedSamples());
    }

    private String textAssociatedSamples() {
        StringBuilder stringBuilder = new StringBuilder();
        if (especimenDTO.getMuestrasAsociadas().size() == 0) {
            return getString(R.string.no_associated_samples);
        }else if (especimenDTO.getMuestrasAsociadas().size() > 0) {
            Iterator<MuestraAsociada> iterator = especimenDTO.getMuestrasAsociadas().iterator();
            for (int i = 0; i < especimenDTO.getMuestrasAsociadas().size(); i++) {
                MuestraAsociada muestraAsociada = iterator.next();
                if (i > 0) {
                    if (i == especimenDTO.getMuestrasAsociadas().size() - 1) {
                        stringBuilder.append(" ").append(getString(R.string.and)).append(" ");
                    }else {
                        stringBuilder.append(getString(R.string.comma)).append(" ");
                    }
                }
                stringBuilder.append(muestraAsociada.getDescripcion());
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void onAssociatedSampleRemoved(MuestraAsociada muestraAsociada) {
        especimenDTO.getMuestrasAsociadas().remove(muestraAsociada);
        plantAttributesFragment.updateAssociatedSamplesText(textAssociatedSamples());
    }

    @Override
    public void onActionModeFinished(AssociatedSamplesFragment associatedSamplesFragment) {
        if (associatedSamplesFragment.isVisible()) {
            plantAttributesFragment.hideAssociatedSamplesList();
        }
    }

    public void onColorAdded(ColorEspecimenDTO colorEspecimen, String plantOrgan){
        plantAttributesFragment.updateColorsText(textColors());
        if (plantOrgan.equals("Flower Corolla")) {
            flowersInformationFragment.setCorollaColor(colorEspecimen);
        }else if (plantOrgan.equals("Flower Calyx")) {
            flowersInformationFragment.setCalyxColor(colorEspecimen);
        }else if (plantOrgan.equals("Flower Gineceo")) {
            flowersInformationFragment.setGineceoColor(colorEspecimen);
        }else if (plantOrgan.equals("Flower Stamens")) {
            flowersInformationFragment.setStamensColor(colorEspecimen);
        }else if (plantOrgan.equals("Flower Stigmata")) {
            flowersInformationFragment.setStigmataColor(colorEspecimen);
        }else if (plantOrgan.equals("Flower Pistiliodios")) {
            flowersInformationFragment.setPistiliodioColor(colorEspecimen);
        }else if (plantOrgan.equals("Fruit Endocarp")) {

        }else if (plantOrgan.equals("Fruit Excarp")) {

        }else if (plantOrgan.equals("Inflorescence Flower")) {

        }else if (plantOrgan.equals("Inflorescence Fruit")) {

        }else if (plantOrgan.equals("Leaves")) {

        }else if (plantOrgan.equals("Leaves Petiole")) {

        }else if (plantOrgan.equals("Stem")) {

        }
    }

    private String textColors() {
        StringBuilder stringBuilder = new StringBuilder();
        if (especimenDTO.getColores().size() == 0) {
            return getString(R.string.no_colors);
        }else if (especimenDTO.getColores().size() > 0) {
            Iterator<Map.Entry<String, ColorEspecimenDTO>> iterator = especimenDTO.getColores().entrySet().iterator();
            for (int i = 0; i < especimenDTO.getColores().size(); i++) {
                Map.Entry<String, ColorEspecimenDTO> entry = iterator.next();
                if (i > 0) {
                    if (i == especimenDTO.getColores().size() - 1) {
                        stringBuilder.append(" ").append(getString(R.string.and)).append(" ");
                    }else {
                        stringBuilder.append(getString(R.string.comma)).append(" ");
                    }
                }
                stringBuilder.append(entry.getValue().getDescripcion()).append(" ").append(entry.getValue().getNombre());
            }
        }
        return stringBuilder.toString();
    }

    public void onColorRemoved(ColorEspecimenDTO colorEspecimen, String plantOrgan){
        especimenDTO.getColores().remove(plantOrgan);
        plantAttributesFragment.updateColorsText(textColors());
    }

    public void onActionModeFinished(ColorsFragment colorsFragment){
        if (colorsFragment.isVisible()) {
            plantAttributesFragment.hideColorsList();
        }
    }

    @Override
    public void onLocationUpdateRequested() {
        googleApiClient.connect();
    }
}