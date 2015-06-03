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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import de.greenrobot.dao.query.Query;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.EspecimenColectorSecundario;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fenologia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fotografia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habitat;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habito;
import edu.udistrital.plantae.logicadominio.datosespecimen.MuestraAsociada;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.logicadominio.recoleccion.ViajeColectorSecundario;
import edu.udistrital.plantae.logicadominio.taxonomia.EpitetoEspecifico;
import edu.udistrital.plantae.logicadominio.taxonomia.Genero;
import edu.udistrital.plantae.logicadominio.taxonomia.Taxon;
import edu.udistrital.plantae.logicadominio.ubicacion.Departamento;
import edu.udistrital.plantae.logicadominio.ubicacion.Municipio;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.RegionDTO;
import edu.udistrital.plantae.objetotransferenciadatos.TaxonDTO;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.FenologiaDao;
import edu.udistrital.plantae.persistencia.HabitoDao;
import edu.udistrital.plantae.persistencia.RegionDao;
import edu.udistrital.plantae.persistencia.TaxonDao;
import edu.udistrital.plantae.persistencia.ViajeColectorSecundarioDao;
import edu.udistrital.plantae.ui.view.SlidingTabLayout;
import edu.udistrital.plantae.ui.view.ViewPagerPlantae;

/**
 * Created by Gerson Sosa on 5/15/14.
 */
public class CreateSpecimenActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener,
        SecondaryCollectorsListFragment.OnSecondaryCollectorSelectedListener,
        CollectingInformationFragment.OnCollectingInformationUpdated,
        CollectingInformationFragment.OnEditModeStarted,
        LocalityInformationFragment.OnLocationUpdateRequest,
        LocalityInformationFragment.OnLocalityChangeListener,
        TaxonInformationFragment.OnTaxonInformationUpdated,
        HabitatInformationFragment.OnHabitatChangedListener,
        PlantAttributesFragment.OnPlantAttributesChangedListener,
        PlantAttributesFragment.OnEditModeStarted,
        AssociatedSamplesFragment.OnAssociatedSampleListener,
        ColorsFragment.OnColorChangedListener,
        FlowerInformationFragment.OnFlowersInformationChangedListener,
        FruitInformationFragment.OnFruitInformationChangedListener,
        InflorescenceInformationFragment.OnInflorescenceInformationChangedListener,
        RootInformationFragment.OnRootInformationChangedListener,
        LeavesInformationFragment.OnLeavesInformationChangedListener,
        StemInformationFragment.OnStemInformationChangedListener{

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int IMAGE_GALLERY_ACTIVITY_REQUEST_CODE = 101;
    private EspecimenDTO especimenDTO;
    private DaoSession daoSession;
    private GoogleApiClient googleApiClient;
    private SpecimenPagesAdapter pagesAdapter;
    private String picturePath;
    private List<ColectorSecundario> colectoresSecundarios;
    private CollectingInformationFragment collectingInformationFragment;
    private SecondaryCollectorsListFragment secondaryCollectorsListFragment;
    private LocalityInformationFragment localityInformationFragment;
    private TaxonInformationFragment taxonInformationFragment;
    private PlantAttributesFragment plantAttributesFragment;
    private FlowerInformationFragment flowersInformationFragment;
    private FruitInformationFragment fruitInformationFragment;
    private InflorescenceInformationFragment inflorescenceInformationFragment;
    private LeavesInformationFragment leavesInformationFragment;
    private StemInformationFragment stemInformationFragment;
    private RootInformationFragment rootInformationFragment;
    private SlidingTabLayout slidingTabLayout;
    private ViewPagerPlantae viewPager;
    private Viaje viaje;
    private boolean editMode;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_specimen);

        // Load Google Play Services for LocationRequest
        servicesConnected();

        // Setup the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        setSupportActionBar(toolbar);

        daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();

        // Get the specimen type and travel id from Intent
        int specimenType;

        // Check if the specimen is new or in editing mode
        final Long especimenId = getIntent().getLongExtra("especimen", 0l);
        if (especimenId != 0l) {
            editMode = true;
            setTitle(R.string.edit_specimen_title);
            Especimen especimen = daoSession.getEspecimenDao().loadDeep(especimenId);
            especimen.getDeterminaciones();
            viaje = especimen.getViaje();
            if (especimen.getTipo().equals("ES")) {
                specimenType = SpecimenPagesAdapter.SPECIMEN_SINGLE;
            } else {
                specimenType = SpecimenPagesAdapter.SPECIMEN_DETAILED;
            }
            especimenDTO = new EspecimenDTO(especimen);
            loadSecondaryCollectorsFromSpecimen(especimenDTO.getColectoresSecundarios());
        }else if (getIntent().getParcelableExtra("clone") != null) {
            especimenDTO = getIntent().getParcelableExtra("clone");
            specimenType = especimenDTO.getTipoCaptura();
            viaje = daoSession.getViajeDao().load(especimenDTO.getViajeID());
            especimenDTO.setNumeroDeColeccion(viaje.getColectorPrincipal().generarNumeroDeColeccion());
            loadSecondaryCollectorsFromSpecimen(especimenDTO.getColectoresSecundarios());
        }else{
            final Long viajeId = getIntent().getLongExtra("viaje", 0l);
            viaje = daoSession.getViajeDao().load(viajeId);
            specimenType = getIntent().getIntExtra("specimenType", 0);
            ColectorPrincipal colectorPrincipal = viaje.getColectorPrincipal();
            especimenDTO = new EspecimenDTO();
            especimenDTO.setViajeID(viajeId);
            especimenDTO.setColectorPrincipalID(colectorPrincipal.getId());
            especimenDTO.setNumeroDeColeccion(colectorPrincipal.generarNumeroDeColeccion());
            especimenDTO.setFechaInicial(new Date(System.currentTimeMillis()));
            especimenDTO.setUsuarioId(colectorPrincipal.getPersona().getUsuarioID());
            final List<ViajeColectorSecundario> viajeColectorSecundarios = daoSession.getViajeColectorSecundarioDao().queryBuilder().where(ViajeColectorSecundarioDao.Properties.ViajeID.eq(viajeId)).list();
            loadSecondaryCollectorsFromTravel(viajeColectorSecundarios);
        }

        especimenDTO.setTipoCaptura(specimenType);

        Bundle fragmentsBundle = createFragmentsBundle();

        pagesAdapter = new SpecimenPagesAdapter(getSupportFragmentManager(), specimenType, this, fragmentsBundle);

        collectingInformationFragment = (CollectingInformationFragment) pagesAdapter.getItem(0);
        localityInformationFragment = (LocalityInformationFragment) pagesAdapter.getItem(1);
        taxonInformationFragment = (TaxonInformationFragment) pagesAdapter.getItem(2);
        plantAttributesFragment = (PlantAttributesFragment) pagesAdapter.getItem(4);
        flowersInformationFragment = (FlowerInformationFragment) pagesAdapter.getItem(5);
        if (specimenType == SpecimenPagesAdapter.SPECIMEN_DETAILED){
            fruitInformationFragment = (FruitInformationFragment) pagesAdapter.getItem(6);
            inflorescenceInformationFragment = (InflorescenceInformationFragment) pagesAdapter.getItem(7);
            rootInformationFragment = (RootInformationFragment) pagesAdapter.getItem(8);
            leavesInformationFragment = (LeavesInformationFragment) pagesAdapter.getItem(9);
            stemInformationFragment = (StemInformationFragment) pagesAdapter.getItem(10);
        }

        viewPager = (ViewPagerPlantae) findViewById(R.id.specimen_view_pager);
        viewPager.setAdapter(pagesAdapter);
        viewPager.setOffscreenPageLimit(7);

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
    }

    private Bundle createFragmentsBundle() {
        Bundle fragmentsBundle = new Bundle();
        if (especimenDTO.getId() != null) {
            fragmentsBundle.putLong("id", especimenDTO.getId());
        }
        if (especimenDTO.getNumeroDeColeccion() != null) {
            fragmentsBundle.putString("numeroDeColeccion", especimenDTO.getNumeroDeColeccion());
        }
        if (especimenDTO.getAlturaDeLaPlanta() != null) {
            fragmentsBundle.putLong("alturaDeLaPlanta", especimenDTO.getAlturaDeLaPlanta());
        }
        if (especimenDTO.getDap() != null) {
            fragmentsBundle.putLong("dap", especimenDTO.getDap());
        }
        if (especimenDTO.getAbundancia() != null) {
            fragmentsBundle.putString("abundancia", especimenDTO.getAbundancia());
        }
        if (especimenDTO.getFenologia() != null) {
            fragmentsBundle.putString("fenologia", especimenDTO.getFenologia().getFenologia());
        }
        if (especimenDTO.getDescripcionEspecimen() != null) {
            fragmentsBundle.putString("descripcionEspecimen", especimenDTO.getDescripcionEspecimen());
        }
        if (especimenDTO.getHabito() != null) {
            fragmentsBundle.putString("habito", especimenDTO.getHabito().getHabito());
        }
        if (especimenDTO.getHabitat() != null) {
            if (especimenDTO.getHabitat().getEspeciesAsociadas() != null) {
                fragmentsBundle.putString("especiesAsociadas", especimenDTO.getHabitat().getEspeciesAsociadas());
            }
            if (especimenDTO.getHabitat().getSueloSustrato() != null) {
                fragmentsBundle.putString("sueloSustrato", especimenDTO.getHabitat().getSueloSustrato());
            }
            if (especimenDTO.getHabitat().getVegetacion() != null) {
                fragmentsBundle.putString("vegetacion", especimenDTO.getHabitat().getVegetacion());
            }
        }
        if (especimenDTO.getLocalidadId() != null) {
            fragmentsBundle.putLong("localidadId", especimenDTO.getLocalidadId());
        }
        if (especimenDTO.getLocalidadNombre() != null) {
            fragmentsBundle.putString("localidadNombre", especimenDTO.getLocalidadNombre());
        }
        if (especimenDTO.getLatitud() != null) {
            fragmentsBundle.putDouble("latitud", especimenDTO.getLatitud());
        }
        if (especimenDTO.getLongitud() != null) {
            fragmentsBundle.putDouble("longitud", especimenDTO.getLongitud());
        }
        if (especimenDTO.getDatum() != null) {
            fragmentsBundle.putString("datum", especimenDTO.getDatum());
        }
        if (especimenDTO.getAltitudMinima() != null) {
            fragmentsBundle.putDouble("altitudMinima", especimenDTO.getAltitudMinima());
        }
        if (especimenDTO.getAltitudMaxima() != null) {
            fragmentsBundle.putDouble("altitudMaxima", especimenDTO.getAltitudMaxima());
        }
        if (especimenDTO.getLocalidadDescripcion() != null) {
            fragmentsBundle.putString("localidadDescripcion", especimenDTO.getLocalidadDescripcion());
        }
        if (especimenDTO.getMarcaDispositivo() != null) {
            fragmentsBundle.putString("marcaDispositivo", especimenDTO.getMarcaDispositivo());
        }
        if (especimenDTO.getRegion() != null) {
            if (especimenDTO.getRegion().getMunicipio() != null) {
                fragmentsBundle.putString("municipio", especimenDTO.getRegion().getMunicipio());
            }
            if (especimenDTO.getRegion().getDepartamento() != null) {
                fragmentsBundle.putString("departamento", especimenDTO.getRegion().getDepartamento());
            }
            if (especimenDTO.getRegion().getPais() != null) {
                fragmentsBundle.putString("pais", especimenDTO.getRegion().getPais());
            }
        }
        if (especimenDTO.getFechaInicial() != null) {
            fragmentsBundle.putLong("fechaInicial", especimenDTO.getFechaInicial() != null ? especimenDTO.getFechaInicial().getTime() : -1);
        }
        if (especimenDTO.getFechaFinal() != null) {
            fragmentsBundle.putLong("fechaFinal", especimenDTO.getFechaFinal() != null ? especimenDTO.getFechaFinal().getTime() : -1);
        }
        if (especimenDTO.getMetodoColeccion() != null) {
            fragmentsBundle.putString("metodoColeccion", especimenDTO.getMetodoColeccion());
        }
        if (especimenDTO.getEstacionDelAño() != null) {
            fragmentsBundle.putString("estacionDelAño", especimenDTO.getEstacionDelAño());
        }
        if (especimenDTO.getViajeID() != null) {
            fragmentsBundle.putLong("viajeID", especimenDTO.getViajeID());
        }
        if (especimenDTO.getColectorPrincipalID() != null) {
            fragmentsBundle.putLong("colectorPrincipalID", especimenDTO.getColectorPrincipalID());
        }
        if (especimenDTO.getFlorId() != null) {
            fragmentsBundle.putLong("florId", especimenDTO.getFlorId());
        }
        if (especimenDTO.getFlorDescripcion() != null) {
            fragmentsBundle.putString("florDescripcion", especimenDTO.getFlorDescripcion());
        }
        if (especimenDTO.getColorDeLaCorolaID() != null) {
            fragmentsBundle.putLong("colorDeLaCorolaID", especimenDTO.getColorDeLaCorolaID());
        }
        if (especimenDTO.getColorDeLaCorola() != null) {
            fragmentsBundle.putParcelable("colorDeLaCorola", especimenDTO.getColorDeLaCorola());
        }
        if (especimenDTO.getColorDelCalizID() != null) {
            fragmentsBundle.putLong("colorDelCalizID", especimenDTO.getColorDelCalizID());
        }
        if (especimenDTO.getColorDelCaliz() != null) {
            fragmentsBundle.putParcelable("colorDelCaliz", especimenDTO.getColorDelCaliz());
        }
        if (especimenDTO.getColorDelGineceoID() != null) {
            fragmentsBundle.putLong("colorDelGineceoID", especimenDTO.getColorDelGineceoID());
        }
        if (especimenDTO.getColorDelGineceo() != null) {
            fragmentsBundle.putParcelable("colorDelGineceo", especimenDTO.getColorDelGineceo());
        }
        if (especimenDTO.getColorDeLosEstambresID() != null) {
            fragmentsBundle.putLong("colorDeLosEstambresID", especimenDTO.getColorDeLosEstambresID());
        }
        if (especimenDTO.getColorDeLosEstambres() != null) {
            fragmentsBundle.putParcelable("colorDeLosEstambres", especimenDTO.getColorDeLosEstambres());
        }
        if (especimenDTO.getColorDeLosEstigmasID() != null) {
            fragmentsBundle.putLong("colorDeLosEstigmasID", especimenDTO.getColorDeLosEstigmasID());
        }
        if (especimenDTO.getColorDeLosEstigmas() != null) {
            fragmentsBundle.putParcelable("colorDeLosEstigmas", especimenDTO.getColorDeLosEstigmas());
        }
        if (especimenDTO.getColorDeLosPistiliodiosID() != null) {
            fragmentsBundle.putLong("colorDeLosPistiliodiosID", especimenDTO.getColorDeLosPistiliodiosID());
        }
        if (especimenDTO.getColorDeLosPistiliodios() != null) {
            fragmentsBundle.putParcelable("colorDeLosPistiliodios", especimenDTO.getColorDeLosPistiliodios());
        }
        if (especimenDTO.getInflorescenciaId() != null) {
            fragmentsBundle.putLong("inflorescenciaId", especimenDTO.getInflorescenciaId());
        }
        if (especimenDTO.getColorDeLaInflorescenciaEnFlorID() != null) {
            fragmentsBundle.putLong("colorDeLaInflorescenciaEnFlorID", especimenDTO.getColorDeLaInflorescenciaEnFlorID());
        }
        if (especimenDTO.getColorDeLaInflorescenciaEnFlor() != null) {
            fragmentsBundle.putParcelable("colorDeLaInflorescenciaEnFlor", especimenDTO.getColorDeLaInflorescenciaEnFlor());
        }
        if (especimenDTO.getColorDeLaInflorescenciaEnFrutoID() != null) {
            fragmentsBundle.putLong("colorDeLaInflorescenciaEnFrutoID", especimenDTO.getColorDeLaInflorescenciaEnFrutoID());
        }
        if (especimenDTO.getColorDeLaInflorescenciaEnFruto() != null) {
            fragmentsBundle.putParcelable("colorDeLaInflorescenciaEnFruto", especimenDTO.getColorDeLaInflorescenciaEnFruto());
        }
        if (especimenDTO.getNaturalezaDeLasBracteasPedunculares() != null) {
            fragmentsBundle.putString("naturalezaDeLasBracteasPedunculares", especimenDTO.getNaturalezaDeLasBracteasPedunculares());
        }
        if (especimenDTO.getNaturalezaDelProfilo() != null) {
            fragmentsBundle.putString("naturalezaDelProfilo", especimenDTO.getNaturalezaDelProfilo());
        }
        if (especimenDTO.getPosicionDeLasBracteasPedunculares() != null) {
            fragmentsBundle.putString("posicionDeLasBracteasPedunculares", especimenDTO.getPosicionDeLasBracteasPedunculares());
        }
        if (especimenDTO.getPosicionDeLasInflorescencias() != null) {
            fragmentsBundle.putString("posicionDeLasInflorescencias", especimenDTO.getPosicionDeLasInflorescencias());
        }
        if (especimenDTO.getRaquilas() != null) {
            fragmentsBundle.putString("raquilas", especimenDTO.getRaquilas());
        }
        if (especimenDTO.getRaquis() != null) {
            fragmentsBundle.putString("raquis", especimenDTO.getRaquis());
        }
        if (especimenDTO.getTamañoDeLasBracteasPedunculares() != null) {
            fragmentsBundle.putString("tamañoDeLasBracteasPedunculares", especimenDTO.getTamañoDeLasBracteasPedunculares());
        }
        if (especimenDTO.getTamañoDelPedunculo() != null) {
            fragmentsBundle.putString("tamañoDelPedunculo", especimenDTO.getTamañoDelPedunculo());
        }
        if (especimenDTO.getTamañoDelProfilo() != null) {
            fragmentsBundle.putString("tamañoDelProfilo", especimenDTO.getTamañoDelProfilo());
        }
        if (especimenDTO.getTamañoDelRaquis() != null) {
            fragmentsBundle.putString("tamañoDelRaquis", especimenDTO.getTamañoDelRaquis());
        }
        if (especimenDTO.getTamañoDeRaquilas() != null) {
            fragmentsBundle.putString("tamañoDeRaquilas", especimenDTO.getTamañoDeRaquilas());
        }
        if (especimenDTO.getInflorescenciaDescripcion() != null) {
            fragmentsBundle.putString("inflorescenciaDescripcion", especimenDTO.getInflorescenciaDescripcion());
        }
        if (especimenDTO.getInflorescenciaSolitaria() != null) {
            fragmentsBundle.putBoolean("inflorescenciaSolitaria", especimenDTO.getInflorescenciaSolitaria());
        }
        if (especimenDTO.getNumeroDeLasBracteasPedunculares() != null) {
            fragmentsBundle.putInt("numeroDeLasBracteasPedunculares", especimenDTO.getNumeroDeLasBracteasPedunculares());
        }
        if (especimenDTO.getNumeroDeRaquilas() != null) {
            fragmentsBundle.putInt("numeroDeRaquilas", especimenDTO.getNumeroDeRaquilas());
        }
        if (especimenDTO.getHojaId() != null) {
            fragmentsBundle.putLong("hojaId", especimenDTO.getHojaId());
        }
        if (especimenDTO.getCoberturaDelPeciolo() != null) {
            fragmentsBundle.putString("coberturaDelPeciolo", especimenDTO.getCoberturaDelPeciolo());
        }
        if (especimenDTO.getColorDeLaVainaID() != null) {
            fragmentsBundle.putLong("colorDeLaVainaID", especimenDTO.getColorDeLaVainaID());
        }
        if (especimenDTO.getColorDeLaVaina() != null) {
            fragmentsBundle.putParcelable("colorDeLaVaina", especimenDTO.getColorDeLaVaina());
        }
        if (especimenDTO.getColorDelPecioloID() != null) {
            fragmentsBundle.putLong("colorDelPecioloID", especimenDTO.getColorDelPecioloID());
        }
        if (especimenDTO.getColorDelPeciolo() != null) {
            fragmentsBundle.putParcelable("colorDelPeciolo", especimenDTO.getColorDelPeciolo());
        }
        if (especimenDTO.getDispocicionDeLasPinnas() != null) {
            fragmentsBundle.putString("dispocicionDeLasPinnas", especimenDTO.getDispocicionDeLasPinnas());
        }
        if (especimenDTO.getDisposicionDeLasHojas() != null) {
            fragmentsBundle.putString("disposicionDeLasHojas", especimenDTO.getDisposicionDeLasHojas());
        }
        if (especimenDTO.getFormaDelPeciolo() != null) {
            fragmentsBundle.putString("formaDelPeciolo", especimenDTO.getFormaDelPeciolo());
        }
        if (especimenDTO.getLonguitudDelRaquis() != null) {
            fragmentsBundle.putString("longuitudDelRaquis", especimenDTO.getLonguitudDelRaquis());
        }
        if (especimenDTO.getNaturalezaDeLaVaina() != null) {
            fragmentsBundle.putString("naturalezaDeLaVaina", especimenDTO.getNaturalezaDeLaVaina());
        }
        if (especimenDTO.getNaturalezaDelLimbo() != null) {
            fragmentsBundle.putString("naturalezaDelLimbo", especimenDTO.getNaturalezaDelLimbo());
        }
        if (especimenDTO.getNumeroDePinnas() != null) {
            fragmentsBundle.putString("numeroDePinnas", especimenDTO.getNumeroDePinnas());
        }
        if (especimenDTO.getNumeroHojas() != null) {
            fragmentsBundle.putString("numeroHojas", especimenDTO.getNumeroHojas());
        }
        if (especimenDTO.getTamañoDeLaVaina() != null) {
            fragmentsBundle.putString("tamañoDeLaVaina", especimenDTO.getTamañoDeLaVaina());
        }
        if (especimenDTO.getTamañoDelPeciolo() != null) {
            fragmentsBundle.putString("tamañoDelPeciolo", especimenDTO.getTamañoDelPeciolo());
        }
        if (especimenDTO.getHojaDescripcion() != null) {
            fragmentsBundle.putString("hojaDescripcion", especimenDTO.getHojaDescripcion());
        }
        if (especimenDTO.getFrutoId() != null) {
            fragmentsBundle.putLong("frutoId", especimenDTO.getFrutoId());
        }
        if (especimenDTO.getColorDelExocarpioID() != null) {
            fragmentsBundle.putLong("colorDelExocarpioID", especimenDTO.getColorDelExocarpioID());
        }
        if (especimenDTO.getColorDelExocarpio() != null) {
            fragmentsBundle.putParcelable("colorDelExocarpio", especimenDTO.getColorDelExocarpio());
        }
        if (especimenDTO.getColorDelMesocarpioID() != null) {
            fragmentsBundle.putLong("colorDelMesocarpioID", especimenDTO.getColorDelMesocarpioID());
        }
        if (especimenDTO.getColorDelMesocarpio() != null) {
            fragmentsBundle.putParcelable("colorDelMesocarpio", especimenDTO.getColorDelMesocarpio());
        }
        if (especimenDTO.getColorDelExocarpioInmaduroID() != null) {
            fragmentsBundle.putLong("colorDelExocarpioInmaduroID", especimenDTO.getColorDelExocarpioInmaduroID());
        }
        if (especimenDTO.getColorDelExocarpioInmaduro() != null) {
            fragmentsBundle.putParcelable("colorDelExocarpioInmaduro", especimenDTO.getColorDelExocarpioInmaduro());
        }
        if (especimenDTO.getColorDelMesocarpioInmaduroID() != null) {
            fragmentsBundle.putLong("colorDelMesocarpioInmaduroID", especimenDTO.getColorDelMesocarpioInmaduroID());
        }
        if (especimenDTO.getColorDelMesocarpioInmaduro() != null) {
            fragmentsBundle.putParcelable("colorDelMesocarpioInmaduro", especimenDTO.getColorDelMesocarpioInmaduro());
        }
        if (especimenDTO.getConsistenciaDelPericarpio() != null) {
            fragmentsBundle.putString("consistenciaDelPericarpio", especimenDTO.getConsistenciaDelPericarpio());
        }
        if (especimenDTO.getFrutoDescripcion() != null) {
            fragmentsBundle.putString("frutoDescripcion", especimenDTO.getFrutoDescripcion());
        }
        if (especimenDTO.getTalloId() != null) {
            fragmentsBundle.putLong("talloId", especimenDTO.getTalloId());
        }
        if (especimenDTO.getAlturaDelTallo() != null) {
            fragmentsBundle.putString("alturaDelTallo", especimenDTO.getAlturaDelTallo());
        }
        if (especimenDTO.getColorDelTalloID() != null) {
            fragmentsBundle.putLong("colorDelTalloID", especimenDTO.getColorDelTalloID());
        }
        if (especimenDTO.getColorDelTallo() != null) {
            fragmentsBundle.putParcelable("colorDelTallo", especimenDTO.getColorDelTallo());
        }
        if (especimenDTO.getDiametroDelTallo() != null) {
            fragmentsBundle.putString("diametroDelTallo", especimenDTO.getDiametroDelTallo());
        }
        if (especimenDTO.getDisposicionDeLasEspinas() != null) {
            fragmentsBundle.putString("disposicionDeLasEspinas", especimenDTO.getDisposicionDeLasEspinas());
        }
        if (especimenDTO.getFormaDelTallo() != null) {
            fragmentsBundle.putString("formaDelTallo", especimenDTO.getFormaDelTallo());
        }
        if (especimenDTO.getLongitudEntrenudos() != null) {
            fragmentsBundle.putString("longitudEntrenudos", especimenDTO.getLongitudEntrenudos());
        }
        if (especimenDTO.getNaturalezaDelTallo() != null) {
            fragmentsBundle.putString("naturalezaDelTallo", especimenDTO.getNaturalezaDelTallo());
        }
        if (especimenDTO.getTalloDescripcion() != null) {
            fragmentsBundle.putString("talloDescripcion", especimenDTO.getTalloDescripcion());
        }
        if (especimenDTO.getDesnudoCubierto() != null) {
            fragmentsBundle.putBoolean("desnudoCubierto", especimenDTO.getDesnudoCubierto());
        }
        if (especimenDTO.getEntrenudosConspicuos() != null) {
            fragmentsBundle.putBoolean("entrenudosConspicuos", especimenDTO.getEntrenudosConspicuos());
        }
        if (especimenDTO.getEspinas() != null) {
            fragmentsBundle.putBoolean("espinas", especimenDTO.getEspinas());
        }
        if (especimenDTO.getRaizId() != null) {
            fragmentsBundle.putLong("raizId", especimenDTO.getRaizId());
        }
        if (especimenDTO.getDiametroDeLasRaices() != null) {
            fragmentsBundle.putString("diametroDeLasRaices", especimenDTO.getDiametroDeLasRaices());
        }
        if (especimenDTO.getDiametroEnLaBase() != null) {
            fragmentsBundle.putString("diametroEnLaBase", especimenDTO.getDiametroEnLaBase());
        }
        if (especimenDTO.getFormaDeLasEspinas() != null) {
            fragmentsBundle.putString("formaDeLasEspinas", especimenDTO.getFormaDeLasEspinas());
        }
        if (especimenDTO.getTamañoDeLasEspinas() != null) {
            fragmentsBundle.putString("tamañoDeLasEspinas", especimenDTO.getTamañoDeLasEspinas());
        }
        if (especimenDTO.getRaizDescripcion() != null) {
            fragmentsBundle.putString("raizDescripcion", especimenDTO.getRaizDescripcion());
        }
        if (especimenDTO.getRaizArmada() != null) {
            fragmentsBundle.putBoolean("raizArmada", especimenDTO.getRaizArmada());
        }
        if (especimenDTO.getAlturaDelCono() != null) {
            fragmentsBundle.putLong("alturaDelCono", especimenDTO.getAlturaDelCono());
        }
        if (especimenDTO.getColorDelCono() != null) {
            fragmentsBundle.putParcelable("colorDelCono", especimenDTO.getColorDelCono());
        }
        if (colectoresSecundarios != null) {
            fragmentsBundle.putParcelableArrayList("colectoresSecundarios", (ArrayList<? extends Parcelable>) colectoresSecundarios);
        }
        if (secondaryCollectorsNames() != null) {
            fragmentsBundle.putString("secondaryCollectorsNames", secondaryCollectorsNames());
        }
        if (especimenDTO.getMuestrasAsociadas() != null) {
            fragmentsBundle.putParcelableArrayList("muestrasAsociadas", (ArrayList<? extends Parcelable>) especimenDTO.getMuestrasAsociadas());
        }
        if (especimenDTO.getColores() != null) {
            fragmentsBundle.putParcelableArrayList("colores", (ArrayList<? extends Parcelable>) especimenDTO.getColores());
        }
        if (especimenDTO.getFechaIdentificacion() != null) {
            fragmentsBundle.putLong("fechaIdentificacion", especimenDTO.getFechaIdentificacion() != null ? especimenDTO.getFechaIdentificacion().getTime() : -1);
        }
        if (especimenDTO.getTipo() != null) {
            fragmentsBundle.putString("tipo", especimenDTO.getTipo());
        }
        if (especimenDTO.getTaxon() != null) {
            if (especimenDTO.getTaxon().getEspecie() != null) {
                fragmentsBundle.putString("especie", especimenDTO.getTaxon().getEspecie());
            }
            if (especimenDTO.getTaxon().getGenero() != null) {
                fragmentsBundle.putString("genero", especimenDTO.getTaxon().getGenero());
            }
            if (especimenDTO.getTaxon().getFamilia() != null) {
                fragmentsBundle.putString("familia", especimenDTO.getTaxon().getFamilia());
            }
            if (especimenDTO.getTaxon().getUsos() != null) {
                fragmentsBundle.putParcelableArrayList("usos", (ArrayList<? extends Parcelable>) especimenDTO.getTaxon().getUsos());
            }
            if (especimenDTO.getTaxon().getNombresComunes() != null) {
                fragmentsBundle.putParcelableArrayList("nombresComunes", (ArrayList<? extends Parcelable>) especimenDTO.getTaxon().getNombresComunes());
            }
        }
        fragmentsBundle.putLong("usuarioId", especimenDTO.getUsuarioId());
        fragmentsBundle.putInt("tipoCaptura", especimenDTO.getTipoCaptura());
        return fragmentsBundle;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (picturePath != null) {
            outState.putString("photo_uri", picturePath);
            outState.putParcelable("especimen", especimenDTO);
        }else{
            outState.putParcelable("especimen", especimenDTO);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            picturePath = savedInstanceState.getString("photo_uri");
            especimenDTO = savedInstanceState.getParcelable("especimen");
            loadSecondaryCollectorsFromSpecimen(especimenDTO.getColectoresSecundarios());
            //secondaryCollectorsListFragment = collectingInformationFragment.getFragmentSecondaryCollectosList();
            //secondaryCollectorsListFragment.updateSelectedSecondaryCollectors(getSelectedSecondaryCollectors());
            //plantAttributesFragment.updateAssociatedSamplesText(textAssociatedSamples());
            //plantAttributesFragment.updateColorsText(textColors());
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
        LocationRequest locationRequest = LocationRequest.create();
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
                // Show toast with action to create new
                saveSpecimen();
                Intent result = new Intent();
                result.putExtra("newSpecimenId", especimenDTO.getId());
                setResult(RESULT_OK, result);
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
                return true;
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
                    List<Fotografia> fotografias = new ArrayList<>();
                    for (Parcelable parcelable : data.getParcelableArrayListExtra("fotografias")) {
                        fotografias.add((Fotografia) parcelable);
                    }
                    especimenDTO.setFotografias(fotografias);
                }
                break;
            case SecondaryCollectorsListFragment.SECONDARY_COLLECTOR_CREATION_REQUEST:
                if (collectingInformationFragment != null) {
                    collectingInformationFragment.onActivityResult(requestCode,resultCode,data);
                }
                break;
            case AssociatedSamplesFragment.ASSOCIATED_SAMPLE_CREATION_REQUEST:
                if (plantAttributesFragment != null) {
                    plantAttributesFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case ColorsFragment.COLOR_CREATION_REQUEST:
                if (plantAttributesFragment != null) {
                    plantAttributesFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case ColorsFragment.COLOR_EDIT_REQUEST:
                if (plantAttributesFragment != null) {
                    plantAttributesFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case FlowerInformationFragment.FLOWER_COLOR_EDIT_REQUEST:
                if (flowersInformationFragment != null) {
                    flowersInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case FlowerInformationFragment.FLOWER_COLOR_CREATION_REQUEST:
                if (flowersInformationFragment != null) {
                    flowersInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case FruitInformationFragment.FRUIT_COLOR_CREATION_REQUEST:
                if (fruitInformationFragment != null) {
                    fruitInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case FruitInformationFragment.FRUIT_COLOR_EDIT_REQUEST:
                if (fruitInformationFragment != null) {
                    fruitInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case InflorescenceInformationFragment.INFLORESCENCE_COLOR_CREATION_REQUEST:
                if (inflorescenceInformationFragment != null) {
                    inflorescenceInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case InflorescenceInformationFragment.INFLORESCENCE_COLOR_EDIT_REQUEST:
                if (inflorescenceInformationFragment != null) {
                    inflorescenceInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case LeavesInformationFragment.LEAVES_COLOR_CREATION_REQUEST:
                if (leavesInformationFragment != null) {
                    leavesInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case LeavesInformationFragment.LEAVES_COLOR_EDIT_REQUEST:
                if (leavesInformationFragment != null) {
                    leavesInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case RootInformationFragment.ROOT_COLOR_CREATION_REQUEST:
                if (rootInformationFragment != null) {
                    rootInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case RootInformationFragment.ROOT_COLOR_EDIT_REQUEST:
                if (rootInformationFragment != null) {
                    rootInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case StemInformationFragment.STEM_COLOR_CREATION_REQUEST:
                if (stemInformationFragment != null) {
                    stemInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
            case StemInformationFragment.STEM_COLOR_EDIT_REQUEST:
                if (stemInformationFragment != null) {
                    stemInformationFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addPicture() {
        List<Fotografia> especimenDTOFotografias = especimenDTO.getFotografias();
        if (especimenDTOFotografias == null) {
            especimenDTOFotografias = new ArrayList<>(1);
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
        if (especimenDTO.getId() == null) {
            viaje.agregarEspecimen(especimenDTO);
        } else {
            viaje.actualizarEspecimen(especimenDTO);
        }
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

        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + "/Plantae/images");
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
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
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

    private String textColors() {
        StringBuilder stringBuilder = new StringBuilder();
        if (especimenDTO.getColores().size() == 0) {
            return getString(R.string.no_colors);
        }else if (especimenDTO.getColores().size() > 0) {
            Iterator<ColorEspecimenDTO> iterator = especimenDTO.getColores().iterator();
            for (int i = 0; i < especimenDTO.getColores().size(); i++) {
                ColorEspecimenDTO colorEspecimenDTO = iterator.next();
                if (i > 0) {
                    if (i == especimenDTO.getColores().size() - 1) {
                        stringBuilder.append(" ").append(getString(R.string.and)).append(" ");
                    }else {
                        stringBuilder.append(getString(R.string.comma)).append(" ");
                    }
                }
                stringBuilder.append(colorEspecimenDTO.getDescripcion()).append(" ").append(colorEspecimenDTO.getNombre());
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void onColorAdded(ColorEspecimenDTO colorEspecimen) {
        plantAttributesFragment.updateColorsText(textColors());
        updatePlantOrgans(colorEspecimen);
        especimenDTO.getColores().add(colorEspecimen);
    }

    private void updatePlantOrgans(ColorEspecimenDTO colorEspecimen) {
        switch (colorEspecimen.getOrganoDeLaPlanta()) {
            case "Flower Corolla":
                flowersInformationFragment.setCorollaColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDeLaCorola(colorEspecimen);
                break;
            case "Flower Calyx":
                flowersInformationFragment.setCalyxColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDelCaliz(colorEspecimen);
                break;
            case "Flower Gineceo":
                flowersInformationFragment.setGineceoColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDelGineceo(colorEspecimen);
                break;
            case "Flower Stamens":
                flowersInformationFragment.setStamensColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDeLosEstambres(colorEspecimen);
                break;
            case "Flower Stigmata":
                flowersInformationFragment.setStigmataColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDeLosPistiliodios(colorEspecimen);
                break;
            case "Flower Pistiliodios":
                flowersInformationFragment.setPistiliodioColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDeLosEstigmas(colorEspecimen);
                break;
            case "Fruit Mesocarp":
                fruitInformationFragment.setMesocarpColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDelMesocarpio(colorEspecimen);
                break;
            case "Fruit Excarp":
                fruitInformationFragment.setExocarpColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDelExocarpio(colorEspecimen);
                break;
            case "Fruit Inmature Excarp":
                fruitInformationFragment.setExocarpImmatureColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDelExocarpioInmaduro(colorEspecimen);
                break;
            case "Fruit Inmature Mesocarp":
                fruitInformationFragment.setMesocarpImmatureColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDelMesocarpioInmaduro(colorEspecimen);
                break;
            case "Inflorescence Flower":
                inflorescenceInformationFragment.setInFlowerColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDeLaInflorescenciaEnFlor(colorEspecimen);
                break;
            case "Inflorescence Fruit":
                inflorescenceInformationFragment.setInFruitColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDeLaInflorescenciaEnFruto(colorEspecimen);
                break;
            case "Leaves Sheath":
                leavesInformationFragment.setSheathColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDeLaVaina(colorEspecimen);
                break;
            case "Leaves Petiole":
                leavesInformationFragment.setPetioleColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDelPeciolo(colorEspecimen);
                break;
            case "Root Cone":
                rootInformationFragment.setConeColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDelCono(colorEspecimen);
                break;
            case "Stem":
                stemInformationFragment.setStemColor(colorEspecimen.getColorRGB());
                especimenDTO.setColorDelTallo(colorEspecimen);
                break;
        }
    }

    @Override
    public void onColorEdited(ColorEspecimenDTO colorEspecimen) {
        especimenDTO.getColores().add(colorEspecimen);
        plantAttributesFragment.updateColorsText(textColors());
        updatePlantOrgans(colorEspecimen);
    }

    @Override
    public void onColorRemoved(ColorEspecimenDTO colorEspecimen) {
        especimenDTO.getColores().remove(colorEspecimen);
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

    @Override
    public void onLocalityNameChanged(String localityName) {
        especimenDTO.setLocalidadNombre(localityName);
    }

    @Override
    public void onCountyChanged(String county) {
        RegionDTO regionDTO = especimenDTO.getRegion() != null ? especimenDTO.getRegion() : new RegionDTO();
        Query<Region> regionQuery = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Municipio.eq(county)).limit(1).build();
        Region municipio;
        municipio = regionQuery.unique();
        if (municipio != null) {
            regionDTO = new RegionDTO(municipio);
            // Propagate changes to fragment
            localityInformationFragment.updateRegion(regionDTO.getPais(), regionDTO.getDepartamento(), false);
        }else{
            regionDTO.setMunicipio(county);
            regionDTO.setId(null);
        }
        especimenDTO.setRegion(regionDTO);
    }

    @Override
    public void onStateChanged(String state) {
        boolean clearCounty = false;
        RegionDTO regionDTO = new RegionDTO();
        if (especimenDTO.getRegion() != null) {
            regionDTO = especimenDTO.getRegion();
        }
        Query<Region> regionQuery = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("departamento")).where(RegionDao.Properties.Departamento.eq(state)).limit(1).build();
        Region departamento;
        departamento = regionQuery.unique();
        if (departamento != null) {
            // Propagate changes to fragment
            Long oldId = regionDTO.getId();
            String municipio = regionDTO.getMunicipio();
            regionDTO = new RegionDTO(departamento);
            if (oldId != null && oldId != 0l) {
                // Verify county was choosed and belongs to state otherwise clear
                Region region = daoSession.getRegionDao().load(oldId);
                if (region.getClass().equals(Municipio.class)) {
                    if (region.getRegionPadre().getNombre().equals(departamento.getNombre())){
                        regionDTO.setId(oldId);
                        regionDTO.setMunicipio(municipio);
                    }else{
                        // Clear the county doesn't belong to state
                        clearCounty = true;
                    }
                }
            }else{
                regionDTO.setId(null);
                regionDTO.setMunicipio(municipio);
            }
            localityInformationFragment.updateRegion(regionDTO.getPais(), regionDTO.getDepartamento(), clearCounty);
        }else{
            regionDTO.setId(null);
            regionDTO.setDepartamento(state);
        }
        especimenDTO.setRegion(regionDTO);
    }

    @Override
    public void onCountryChanged(String country) {
        boolean clearCounty = false;
        RegionDTO regionDTO = new RegionDTO();
        if (especimenDTO.getRegion() != null) {
            regionDTO = especimenDTO.getRegion();
        }
        Region pais = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("pais")).where(RegionDao.Properties.Pais.eq(country)).limit(1).unique();
        if (pais != null) {
            Long oldId = regionDTO.getId();
            String municipio = regionDTO.getMunicipio();
            String departamento = regionDTO.getDepartamento();
            regionDTO = new RegionDTO(pais);
            if (oldId != null && oldId != 0l) {
                // Verify county was choosed and belongs to state otherwise clear
                Region region = daoSession.getRegionDao().load(oldId);
                if (region.getClass().equals(Departamento.class)){
                    if (region.getRegionPadre().getId().equals(pais.getId())){
                        regionDTO.setId(oldId);
                        regionDTO.setDepartamento(departamento);
                    }else{
                        // Clear the county doesn't belong to state
                        clearCounty = true;
                    }
                }else if (region.getClass().equals(Municipio.class)) {
                    if (region.getRegionPadre().getRegionPadre().getNombre().equals(pais.getNombre())) {
                        regionDTO.setId(oldId);
                        regionDTO.setDepartamento(departamento);
                        regionDTO.setMunicipio(municipio);
                    }
                }
            }else {
                regionDTO.setDepartamento(departamento);
                regionDTO.setMunicipio(municipio);
                regionDTO.setId(null);
            }
            // Propagate changes to fragment
            localityInformationFragment.updateRegion(regionDTO.getPais(), regionDTO.getDepartamento(), clearCounty);
        }else{
            regionDTO.setPais(country);
        }
        especimenDTO.setRegion(regionDTO);
    }

    @Override
    public void onMinAltitudeChanged(double minAltidude) {
        especimenDTO.setAltitudMinima(minAltidude);
    }

    @Override
    public void onMaxAltitudeChanged(double maxAltitude) {
        especimenDTO.setAltitudMaxima(maxAltitude);
    }

    @Override
    public void onLatitudeChanged(double lat) {
        especimenDTO.setLatitud(lat);
    }

    @Override
    public void onLongitudeChanged(double lon) {
        especimenDTO.setLongitud(lon);
    }

    @Override
    public void onDatumChanged(String datum) {
        especimenDTO.setDatum(datum);
    }

    @Override
    public void onDeviceBrandChanged(String deviceBrand) {
        especimenDTO.setMarcaDispositivo(deviceBrand);
    }

    @Override
    public void onLocalityDescriptionChanged(String localityDescription) {
        especimenDTO.setLocalidadDescripcion(localityDescription);
    }

    @Override
    public void onCollectorNumberUpdated(String collectorNumber) {
        especimenDTO.setNumeroDeColeccion(collectorNumber);
    }

    @Override
    public void onCollectionMethodUpdated(String collectionMethod) {
        especimenDTO.setMetodoColeccion(collectionMethod);
    }

    @Override
    public void onStationUpdated(String station) {
        especimenDTO.setEstacionDelAño(station);
    }

    @Override
    public void onFamilyUpdated(String family) {
        TaxonDTO taxonDTO = especimenDTO.getTaxon() != null ? especimenDTO.getTaxon() : new TaxonDTO();
        if (!TextUtils.isEmpty(family)) {
            boolean clearSpecies = false;
            Long oldId = taxonDTO.getId();
            String genus = taxonDTO.getGenero();
            String species = taxonDTO.getEspecie();
            Taxon familia = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(family)).where(TaxonDao.Properties.Rango.eq("familia")).limit(1).unique();
            if (familia != null) {
                taxonDTO = new TaxonDTO(familia);
                if (oldId != null && !oldId.equals(0l)) {
                    Taxon taxon = daoSession.getTaxonDao().load(oldId);
                    if (taxon.getClass().equals(Genero.class) && taxon.getTaxonPadre().getNombre().equals(familia.getNombre())) {
                        taxonDTO.setGenero(genus);
                        taxonDTO.setId(oldId);
                    }else{
                        clearSpecies = true;
                    }
                    if (taxon.getClass().equals(EpitetoEspecifico.class) && taxon.getTaxonPadre().getTaxonPadre().getNombre().equals(familia.getNombre())) {
                        taxonDTO.setGenero(genus);
                        taxonDTO.setEspecie(species);
                        taxonDTO.setId(oldId);
                    }else{
                        clearSpecies = true;
                    }
                }else{
                    taxonDTO.setGenero(genus);
                    taxonDTO.setEspecie(species);
                    taxonDTO.setId(null);
                }
            }else{
                taxonDTO.setFamilia(family);
                taxonDTO.setId(null);
            }
            taxonInformationFragment.updateTaxon(taxonDTO.getFamilia(), taxonDTO.getGenero(), clearSpecies);
            especimenDTO.setTaxon(taxonDTO);
        }else{
            taxonDTO.setId(null);
            taxonDTO.setFamilia(null);
        }
    }

    @Override
    public void onGenusUpdated(String genus) {
        TaxonDTO taxonDTO = especimenDTO.getTaxon() != null ? especimenDTO.getTaxon() : new TaxonDTO();
        if (!TextUtils.isEmpty(genus)) {
            boolean clearSpecies = false;
            Long oldId = taxonDTO.getId();
            String species = taxonDTO.getEspecie();
            Taxon genero = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(genus)).where(TaxonDao.Properties.Rango.eq("genero")).limit(1).unique();
            if (genero != null) {
                taxonDTO = new TaxonDTO(genero);
                if (oldId != null && !oldId.equals(0l)) {
                    Taxon taxon = daoSession.getTaxonDao().load(oldId);
                    if (taxon.getClass().equals(EpitetoEspecifico.class) && taxon.getTaxonPadre().getNombre().equals(genero.getNombre())) {
                        taxonDTO.setEspecie(species);
                        taxonDTO.setId(oldId);
                    }else{
                        clearSpecies = true;
                    }
                }else if (!TextUtils.isEmpty(species)) {
                    taxonDTO.setEspecie(species);
                    taxonDTO.setId(oldId);
                }
                taxonInformationFragment.updateTaxon(genero.getTaxonPadre().getNombre(), genus, clearSpecies);
                especimenDTO.setTaxon(taxonDTO);
            }else {
                taxonDTO.setGenero(genus);
                taxonDTO.setId(null);
            }
        }
    }

    @Override
    public void onSpeciesUpdated(String species) {
        TaxonDTO taxonDTO = especimenDTO.getTaxon() != null ? especimenDTO.getTaxon() : new TaxonDTO();
        if (!TextUtils.isEmpty(species)) {
            Taxon especie = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Especie.eq(species)).where(TaxonDao.Properties.Rango.eq("epitetoespecifico")).limit(1).unique();
            if (especie != null) {
                taxonDTO = new TaxonDTO(especie);
                taxonInformationFragment.updateTaxon(taxonDTO.getFamilia(), taxonDTO.getGenero(), false);
            }else {
                taxonDTO.setEspecie(species);
                taxonDTO.setId(null);
            }
            especimenDTO.setTaxon(taxonDTO);
        }
    }

    @Override
    public void onVegetationChanged(String vegetation){
        Habitat habitat = especimenDTO.getHabitat() == null ? new Habitat() : especimenDTO.getHabitat();
        habitat.setVegetacion(vegetation);
        especimenDTO.setHabitat(habitat);
    }

    @Override
    public void onSoilSubstratumChanged(String soilSubstratum){
        Habitat habitat = especimenDTO.getHabitat() == null ? new Habitat() : especimenDTO.getHabitat();
        habitat.setSueloSustrato(soilSubstratum);
        especimenDTO.setHabitat(habitat);
    }

    @Override
    public void onAssociatedSpeciesChanged(String associatedSpecies){
        Habitat habitat = especimenDTO.getHabitat() == null ? new Habitat() : especimenDTO.getHabitat();
        habitat.setEspeciesAsociadas(associatedSpecies);
        especimenDTO.setHabitat(habitat);
    }

    @Override
    public void onHabitChanged(String habit) {
        Habito habito = daoSession.getHabitoDao().queryBuilder().where(HabitoDao.Properties.Habito.eq(habit)).unique();
        if (habito == null) {
            especimenDTO.setHabito(new Habito(habit));
        } else {
            especimenDTO.setHabito(habito);
        }
    }

    @Override
    public void onAlturaDeLaPlantaChanged(int alturaDeLaPlanta) {
        especimenDTO.setAlturaDeLaPlanta((long) alturaDeLaPlanta);
    }

    @Override
    public void onDapChanged(int dap) {
        especimenDTO.setDap((long) dap);
    }

    @Override
    public void onAbundanciaChanged(String abundancia) {
        especimenDTO.setAbundancia(abundancia);
    }

    @Override
    public void onFenologiaChanged(String fenology) {
        if (!TextUtils.isEmpty(fenology)) {
            Fenologia fenologia = daoSession.getFenologiaDao().queryBuilder().where(FenologiaDao.Properties.Fenologia.eq(fenology)).unique();
            if (fenologia == null) {
                especimenDTO.setFenologia(new Fenologia(fenology));
            } else {
                especimenDTO.setFenologia(fenologia);
            }
        }
    }

    @Override
    public void onDescripcionEspecimenChanged(String descripcionEspecimen) {
        especimenDTO.setDescripcionEspecimen(descripcionEspecimen);
    }

    @Override
    public void onCalyxColorChanged(ColorEspecimenDTO calyxColor) {
        if (calyxColor != null) {
            especimenDTO.setColorDelCaliz(calyxColor);
            especimenDTO.getColores().add(calyxColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onCorollaColorChanged(ColorEspecimenDTO corollaColor) {
        if (corollaColor != null) {
            especimenDTO.setColorDeLaCorola(corollaColor);
            especimenDTO.getColores().add(corollaColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onStamensColorChanged(ColorEspecimenDTO stamensColor) {
        if (stamensColor != null) {
            especimenDTO.setColorDeLosEstambres(stamensColor);
            especimenDTO.getColores().add(stamensColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onPistiliodioColorChanged(ColorEspecimenDTO pistiliodioColor) {
        if (pistiliodioColor != null) {
            especimenDTO.setColorDeLosPistiliodios(pistiliodioColor);
            especimenDTO.getColores().add(pistiliodioColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onGineceoColorChanged(ColorEspecimenDTO gineceoColor) {
        if (gineceoColor != null) {
            especimenDTO.setColorDelGineceo(gineceoColor);
            especimenDTO.getColores().add(gineceoColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onStigmataColorChanged(ColorEspecimenDTO stigmataColor) {
        if (stigmataColor != null) {
            especimenDTO.setColorDeLosEstigmas(stigmataColor);
            especimenDTO.getColores().add(stigmataColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onFlowersDescriptionChanged(String flowersDescription) {
        especimenDTO.setFlorDescripcion(flowersDescription);
    }

    @Override
    public void onPericarpConsistencyChanged(String pericarp) {
        especimenDTO.setConsistenciaDelPericarpio(pericarp);
    }

    @Override
    public void onExocarpColorChanged(ColorEspecimenDTO exocarpColor) {
        if (exocarpColor != null) {
            especimenDTO.setColorDelExocarpio(exocarpColor);
            especimenDTO.getColores().add(exocarpColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onMesocarpColorChanged(ColorEspecimenDTO mesocarpColor) {
        if (mesocarpColor != null) {
            especimenDTO.setColorDelMesocarpio(mesocarpColor);
            especimenDTO.getColores().add(mesocarpColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onExocarpImmatureColorChanged(ColorEspecimenDTO exocarpImmatureColor) {
        if (exocarpImmatureColor != null) {
            especimenDTO.setColorDelExocarpioInmaduro(exocarpImmatureColor);
            especimenDTO.getColores().add(exocarpImmatureColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onMesocarpImmatureColorChanged(ColorEspecimenDTO mesocarpImmatureColor) {
        if (mesocarpImmatureColor != null) {
            especimenDTO.setColorDelMesocarpioInmaduro(mesocarpImmatureColor);
            especimenDTO.getColores().add(mesocarpImmatureColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onFruitDescriptionChanged(String fruitDescription) {
        especimenDTO.setFrutoDescripcion(fruitDescription);
    }

    @Override
    public void onInflorescencePositionChanged(String inflorescencePosition) {
        especimenDTO.setPosicionDeLasInflorescencias(inflorescencePosition);
    }

    @Override
    public void onInflorescenceAloneChanged(boolean inflorescenceAlone) {
        especimenDTO.setInflorescenciaSolitaria(inflorescenceAlone);
    }

    @Override
    public void onProphyllNatureChanged(String prophyllNature) {
        especimenDTO.setNaturalezaDelProfilo(prophyllNature);
    }

    @Override
    public void onProphyllSizeChanged(String prophyllSize) {
        especimenDTO.setTamañoDelProfilo(prophyllSize);
    }

    @Override
    public void onBractsNumberChanged(String bractsNumber) {
        if (bractsNumber != null) {
            try {
                Integer castedParameter = Integer.parseInt(bractsNumber);
                especimenDTO.setNumeroDeLasBracteasPedunculares(castedParameter);
            } catch (NumberFormatException e) {
                Log.e("Plantae", "Couldn't cast bractsNumber expression "+ bractsNumber);
            }
        }
    }

    @Override
    public void onBractsPositionChanged(String bractsPosition) {
        especimenDTO.setPosicionDeLasBracteasPedunculares(bractsPosition);
    }

    @Override
    public void onBractsSizeChanged(String bractsSize) {
        especimenDTO.setTamañoDeLasBracteasPedunculares(bractsSize);
    }

    @Override
    public void onBractsNatureChanged(String bractsNature) {
        especimenDTO.setNaturalezaDeLasBracteasPedunculares(bractsNature);
    }

    @Override
    public void onPeduncleSizeChanged(String peduncleSize) {
        especimenDTO.setTamañoDelPedunculo(peduncleSize);
    }

    @Override
    public void onRachisSizeChanged(String rachisSize) {
        especimenDTO.setTamañoDelRaquis(rachisSize);
    }

    @Override
    public void onRachillaeNumberChanged(String rachillaeNumber) {
        if (rachillaeNumber != null) {
            try {
                Integer castedParameter = Integer.parseInt(rachillaeNumber);
                especimenDTO.setNumeroDeRaquilas(castedParameter);
            } catch (NumberFormatException e) {
                Log.e("Plantae", "Couldn't cast rachillaeNumber expression "+ rachillaeNumber);
            }
        }
    }

    @Override
    public void onRachillaeSizeChanged(String rachillaeSize) {
        especimenDTO.setTamañoDeRaquilas(rachillaeSize);
    }

    @Override
    public void onInflorescenceDescriptionChanged(String inflorescenceDescription) {
        especimenDTO.setInflorescenciaDescripcion(inflorescenceDescription);
    }

    @Override
    public void onInFlowerColorChanged(ColorEspecimenDTO inFlowerColor) {
        if (inFlowerColor != null) {
            especimenDTO.setColorDeLaInflorescenciaEnFlor(inFlowerColor);
            especimenDTO.getColores().add(inFlowerColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onInFruitColorChanged(ColorEspecimenDTO inFruitColor) {
        if (inFruitColor != null) {
            especimenDTO.setColorDeLaInflorescenciaEnFruto(inFruitColor);
            especimenDTO.getColores().add(inFruitColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onRachisLengthChanged(String rachisLength) {
        especimenDTO.setLonguitudDelRaquis(rachisLength);
    }

    @Override
    public void onPinnaeArrangementChanged(String pinnaeArrangement) {
        especimenDTO.setDispocicionDeLasPinnas(pinnaeArrangement);
    }

    @Override
    public void onLeafArrangementChanged(String leafArrangement) {
        especimenDTO.setDisposicionDeLasEspinas(leafArrangement);
    }

    @Override
    public void onPetioleSizeChanged(String petioleSize) {
        especimenDTO.setTamañoDelPeciolo(petioleSize);
    }

    @Override
    public void onPetioleFormChanged(String petioleForm) {
        especimenDTO.setFormaDelPeciolo(petioleForm);
    }

    @Override
    public void onSheathNatureChanged(String sheathNature) {
        especimenDTO.setNaturalezaDeLaVaina(sheathNature);
    }

    @Override
    public void onPetioleCoverageChanged(String petioleCoverage) {
        especimenDTO.setCoberturaDelPeciolo(petioleCoverage);
    }

    @Override
    public void onLimboNatureChanged(String limboNature) {
        especimenDTO.setNaturalezaDelLimbo(limboNature);
    }

    @Override
    public void onPinnaeNumberChanged(String pinnaeNumber) {
        especimenDTO.setNumeroDePinnas(pinnaeNumber);
    }

    @Override
    public void onLeavesDescriptionChanged(String leavesDespcription) {
        especimenDTO.setHojaDescripcion(leavesDespcription);
    }

    @Override
    public void onLeavesNumberChanged(String leavesNumber) {
        especimenDTO.setNumeroHojas(leavesNumber);
    }

    @Override
    public void onSheathSizeChanged(String sheathSize) {
        especimenDTO.setTamañoDeLaVaina(sheathSize);
    }

    @Override
    public void onPetioleColorChanged(ColorEspecimenDTO petioleColor) {
        if (petioleColor != null) {
            especimenDTO.setColorDelPeciolo(petioleColor);
            especimenDTO.getColores().add(petioleColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onSheathColorChanged(ColorEspecimenDTO sheathColor) {
        if (sheathColor != null) {
            especimenDTO.setColorDeLaVaina(sheathColor);
            especimenDTO.getColores().add(sheathColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onInBaseDiameterChanged(String inBaseDiameter) {
        especimenDTO.setDiametroEnLaBase(inBaseDiameter);
    }

    @Override
    public void onRootsDiameterChanged(String rootsDiameter) {
        especimenDTO.setDiametroDeLasRaices(rootsDiameter);
    }

    @Override
    public void onSpinesShapeChanged(String spinesShape) {
        especimenDTO.setFormaDeLasEspinas(spinesShape);
    }

    @Override
    public void onSpinesSizeChanged(String spinesSize) {
        especimenDTO.setTamañoDeLasEspinas(spinesSize);
    }

    @Override
    public void onArmedChanged(boolean armed) {
        especimenDTO.setRaizArmada(armed);
    }

    @Override
    public void onConeHeightChanged(String coneHeight) {
        if (coneHeight != null) {
            try {
                Long castedParameter = Long.parseLong(coneHeight);
                especimenDTO.setAlturaDelCono(castedParameter);
            } catch (NumberFormatException e) {
                Log.e("Plantae", "Couldn't cast coneHeight expression "+ coneHeight);
            }
        }
    }

    @Override
    public void onConeColorChanged(ColorEspecimenDTO coneColor) {
        if (coneColor != null) {
            especimenDTO.setColorDelCono(coneColor);
            especimenDTO.getColores().add(coneColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onRootsDescriptionChanged(String rootsDescription) {
        especimenDTO.setRaizDescripcion(rootsDescription);
    }

    @Override
    public void onStemHeightChanged(String stemHeight) {
        especimenDTO.setAlturaDelTallo(stemHeight);
    }

    @Override
    public void onStemNatureChanged(String stemNature) {
        especimenDTO.setNaturalezaDelTallo(stemNature);
    }

    @Override
    public void onStemDiameterChanged(String stemDiameter) {
        especimenDTO.setDiametroDelTallo(stemDiameter);
    }

    @Override
    public void onStemFormChanged(String stemForm) {
        especimenDTO.setFormaDelTallo(stemForm);
    }

    @Override
    public void onInternodesLengthChanged(String internodesLength) {
        especimenDTO.setLongitudEntrenudos(internodesLength);
    }

    @Override
    public void onConspicuousInternodesChanged(boolean conspicuousInternodes) {
        especimenDTO.setEntrenudosConspicuos(conspicuousInternodes);
    }

    @Override
    public void onStemNakedChanged(boolean stemNaked) {
        especimenDTO.setDesnudoCubierto(stemNaked);
    }

    @Override
    public void onStemCoveredChanged(boolean stemCovered) {
        especimenDTO.setDesnudoCubierto(stemCovered);
    }

    @Override
    public void onThornsChanged(boolean thorns) {
        especimenDTO.setEspinas(thorns);
    }

    @Override
    public void onThornArrangementChanged(String thornArrangement) {
        especimenDTO.setDisposicionDeLasEspinas(thornArrangement);
    }

    @Override
    public void onStemColorChanged(ColorEspecimenDTO stemColor) {
        if (stemColor != null) {
            especimenDTO.setColorDelTallo(stemColor);
            especimenDTO.getColores().add(stemColor);
            plantAttributesFragment.updateColorsText(textColors());
        }
    }

    @Override
    public void onStemDescriptionChanged(String stemDescription) {
        especimenDTO.setTalloDescripcion(stemDescription);
    }
}