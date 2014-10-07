package edu.udistrital.plantae;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import edu.udistrital.plantae.logicadominio.datosespecimen.*;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.logicadominio.taxonomia.*;
import edu.udistrital.plantae.logicadominio.ubicacion.*;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.*;

import java.util.Date;

/**
 * Created by Gerson Sosa on 5/15/14.
 */
public class CreateSpecimenActivity extends ActionBarActivity {

    private ViewPager viewPager;
    private SpecimenPagesAdapter pagesAdapter;
    private EspecimenDTO especimenDTO;
    private DaoSession daoSession;
    private int specimenType;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_specimen);

        daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();

        // TODO Create the content of the tabs

        specimenType = getIntent().getIntExtra("specimenType", 0);
        Long viajeId = getIntent().getLongExtra("viaje", 0l);

        ColectorPrincipal colectorPrincipal = daoSession
                .getViajeDao().load(viajeId).getColectorPrincipal();
        especimenDTO = new EspecimenDTO();
        especimenDTO.setViajeID(viajeId);
        especimenDTO.setColectorPrincipalID(colectorPrincipal.getId());
        especimenDTO.setNumeroDeColeccion(colectorPrincipal.generarNumeroDeColeccion());
        especimenDTO.setFechaInicial(new Date(System.currentTimeMillis()));
        especimenDTO.setUsuarioId(colectorPrincipal.getPersona().getUsuarioID());

        pagesAdapter = new SpecimenPagesAdapter(getSupportFragmentManager(), specimenType, this, especimenDTO);

        viewPager = (ViewPager) findViewById(R.id.specimen_view_pager);
        viewPager.setAdapter(pagesAdapter);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                viewPager.setCurrentItem(tab.getPosition());
                actionBar.setSelectedNavigationItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                // Nothing
            }
        };

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // Add fragment tabs
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_event).setTabListener(tabListener)); // Collecting information
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Locality information
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Taxon Information
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Habitat Information
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Plant Attributes
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Flower information
        if (pagesAdapter.getCount() > 6){
            actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Fruit information
            actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Inflorescence information
            actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Root information
            actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Leaves information
            actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_action_web_site).setTabListener(tabListener)); // Stem information
        }
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
            case R.id.action_discard:
                setResult(RESULT_CANCELED);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveSpecimen(){
        // Which fields are filled, decide which fields to save
        daoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                Long localidadId = null;
                Long habitatId = null;
                Long habitoId = null;
                Long florId = null;
                Long inflorescenciaId = null;
                Long talloId = null;
                Long raizId = null;
                Long frutoId = null;
                Long hojaId = null;
                if (especimenDTO.getLocalidadNombre() != null || especimenDTO.getRegion() != null
                        || especimenDTO.getLatitud() > 0 || especimenDTO.getLongitud() > 0
                        || especimenDTO.getAltitudMinima() > 0 || especimenDTO.getAltitudMaxima() > 0) {
                    Localidad localidad = new Localidad(especimenDTO.getLocalidadNombre());
                    localidad.setDescripcion(especimenDTO.getLocalidadDescripcion());
                    localidad.setAltitudMinima(especimenDTO.getAltitudMinima());
                    localidad.setAltitudMaxima(especimenDTO.getAltitudMaxima());
                    localidad.setDatum(especimenDTO.getDatum());
                    localidad.setLatitud(especimenDTO.getLatitud());
                    localidad.setLongitud(especimenDTO.getLongitud());
                    localidad.setMarcaDispositivo(especimenDTO.getMarcaDispositivo());
                    if (especimenDTO.getRegion() != null) {
                        Region region = null;
                        if (especimenDTO.getRegion().getId() == null) {
                            Region pais = null;
                            if (especimenDTO.getRegion().getPais() != null) {
                                pais = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Pais.eq(especimenDTO.getRegion().getPais())).where(RegionDao.Properties.Rango.eq("pais")).unique();
                            }
                            Region departamento = null;
                            if (especimenDTO.getRegion().getDepartamento() != null) {
                                departamento = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Departamento.eq(especimenDTO.getRegion().getDepartamento())).where(RegionDao.Properties.Rango.eq("departamento")).unique();
                            }
                            if (pais == null) {
                                pais = new Pais(especimenDTO.getRegion().getPais());
                                pais.setUsuarioId(especimenDTO.getUsuarioId());
                                daoSession.getRegionDao().insert(pais);
                            }
                            if (departamento == null) {
                                departamento = new Departamento(especimenDTO.getRegion().getDepartamento());
                                departamento.setUsuarioId(especimenDTO.getUsuarioId());
                                departamento.setRegionPadre(pais);
                                daoSession.getRegionDao().insert(departamento);
                            }

                            if (especimenDTO.getRegion().getMunicipio() != null) {
                                region = new Municipio(especimenDTO.getRegion().getMunicipio());
                                region.setUsuarioId(especimenDTO.getUsuarioId());
                                region.setRegionPadre(departamento);
                                daoSession.getRegionDao().insert(region);
                            }else if (especimenDTO.getRegion().getDepartamento() != null) {
                                region = departamento;
                            }else{
                                region = pais;
                            }
                            localidad.setRegion(region);
                        } else {
                            localidad.setRegionID(especimenDTO.getRegion().getId());
                        }
                    }
                    localidadId = daoSession.getLocalidadDao().insert(localidad);
                }
                if (especimenDTO.getHabitat() != null && especimenDTO.getHabitat().getId() == null) {
                    habitatId = daoSession.getHabitatDao().insert(especimenDTO.getHabitat());
                }
                if (especimenDTO.getHabito() != null && especimenDTO.getHabito().getId() == null) {
                    habitoId = daoSession.getHabitoDao().insert(especimenDTO.getHabito());
                }
                IdentidadTaxonomica identidadTaxonomica = null;
                if (especimenDTO.getTaxon() != null) {
                    Taxon taxon = null;
                    if (especimenDTO.getTaxon().getId() == null) {
                        Taxon genero = null;
                        if (especimenDTO.getTaxon().getGenero() != null) {
                            genero = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Genero.eq(especimenDTO.getTaxon().getGenero())).where(TaxonDao.Properties.Rango.eq("genero")).unique();
                        }
                        Taxon familia = null;
                        if (especimenDTO.getTaxon().getFamilia() != null) {
                            familia = daoSession.getTaxonDao().queryBuilder().where(TaxonDao.Properties.Familia.eq(especimenDTO.getTaxon().getFamilia())).where(TaxonDao.Properties.Rango.eq("familia")).unique();
                        }
                        if (familia == null) {
                            familia = new Familia(especimenDTO.getTaxon().getFamilia());
                            familia.setUsuarioId(especimenDTO.getUsuarioId());
                            daoSession.getTaxonDao().insert(familia);
                        }
                        if (genero == null) {
                            genero = new Genero(especimenDTO.getTaxon().getGenero());
                            genero.setUsuarioId(especimenDTO.getUsuarioId());
                            genero.setTaxonPadre(familia);
                            daoSession.getTaxonDao().insert(genero);
                        }
                        if (especimenDTO.getTaxon().getEspecie() != null) {
                            taxon = new EpitetoEspecifico(especimenDTO.getTaxon().getEspecie());
                            taxon.setUsuarioId(especimenDTO.getUsuarioId());
                            taxon.setTaxonPadre(genero);
                            daoSession.getTaxonDao().insert(taxon);
                        }else if (especimenDTO.getTaxon().getGenero() != null) {
                            taxon = genero;
                        }else{
                            taxon = familia;
                        }
                    }else{
                        taxon = daoSession.getTaxonDao().load(especimenDTO.getTaxon().getId());
                    }
                    identidadTaxonomica = new IdentidadTaxonomica();
                    identidadTaxonomica.setFechaIdentificacion(new Date(System.currentTimeMillis()));
                    identidadTaxonomica.setTaxonID(taxon.getId());
                    identidadTaxonomica.setDeterminador(daoSession.getPersonaDao().queryBuilder().where(PersonaDao.Properties.UsuarioID.eq(especimenDTO.getUsuarioId())).unique());
                    identidadTaxonomica.setTipo(especimenDTO.getTipo());
                }
                if (especimenDTO.getFlor() != null && especimenDTO.getFlor().getId() == null) {
                    florId = daoSession.getFlorDao().insert(especimenDTO.getFlor());
                }
                if (especimenDTO.getInflorescencia() != null && especimenDTO.getInflorescencia().getId() == null) {
                    inflorescenciaId = daoSession.getInflorescenciaDao().insert(especimenDTO.getInflorescencia());
                }
                if (especimenDTO.getTallo() != null && especimenDTO.getTallo().getId() == null) {
                    talloId = daoSession.getTalloDao().insert(especimenDTO.getTallo());
                }
                if (especimenDTO.getRaiz() != null && especimenDTO.getRaiz().getId() == null) {
                    raizId = daoSession.getRaizDao().insert(especimenDTO.getRaiz());
                }
                if (especimenDTO.getFruto() != null && especimenDTO.getFruto().getId() == null) {
                    frutoId = daoSession.getFrutoDao().insert(especimenDTO.getFruto());
                }
                if (especimenDTO.getHoja() != null && especimenDTO.getHoja().getId() == null) {
                    hojaId = daoSession.getHojaDao().insert(especimenDTO.getHoja());
                }
                BuilderEspecimen builderEspecimen;
                if (specimenType == SpecimenPagesAdapter.SPECIMEN_SINGLE){
                    builderEspecimen = new BuilderEspecimenSencillo(especimenDTO.getNumeroDeColeccion(), especimenDTO.getViajeID(), especimenDTO.getColectorPrincipalID())
                            .alturaDeLaPlanta(especimenDTO.getAlturaDeLaPlanta()).dap(especimenDTO.getDap())
                            .fechaInicial(especimenDTO.getFechaInicial()).fechaFinal(especimenDTO.getFechaFinal())
                            .metodoColeccion(especimenDTO.getMetodoColeccion()).estacionDelAño(especimenDTO.getEstacionDelAño())
                            .habitoID(habitoId).habitatID(habitatId)
                            .localidadID(localidadId).florID(florId)
                            .colectoresSecundarios(especimenDTO.getColectoresSecundarios())
                            .muestrasAsociadas(especimenDTO.getMuestrasAsociadas())
                            .fotografias(especimenDTO.getFotografias())
                            .identidadTaxonomica(identidadTaxonomica);
                }else{
                    builderEspecimen = new BuilderEspecimenDetallado(especimenDTO.getNumeroDeColeccion(), especimenDTO.getViajeID(), especimenDTO.getColectorPrincipalID())
                            .alturaDeLaPlanta(especimenDTO.getAlturaDeLaPlanta()).dap(especimenDTO.getDap())
                            .fechaInicial(especimenDTO.getFechaInicial()).fechaFinal(especimenDTO.getFechaFinal())
                            .metodoColeccion(especimenDTO.getMetodoColeccion()).estacionDelAño(especimenDTO.getEstacionDelAño())
                            .habitoID(habitoId).habitatID(habitatId)
                            .localidadID(localidadId).florID(florId)
                            .raizID(raizId).talloID(talloId)
                            .inflorescenciaID(inflorescenciaId).frutoID(frutoId).hojaID(hojaId)
                            .colectoresSecundarios(especimenDTO.getColectoresSecundarios())
                            .muestrasAsociadas(especimenDTO.getMuestrasAsociadas())
                            .fotografias(especimenDTO.getFotografias())
                            .identidadTaxonomica(identidadTaxonomica);;
                }
                builderEspecimen.build();
                Especimen especimen = builderEspecimen.getEspecimen();
                Long especimenId = daoSession.getEspecimenDao().insert(especimen);
                especimen.getColectorPrincipal().setNumeroColeccionActual(especimen.getNumeroDeColeccion());
                daoSession.getColectorPrincipalDao().update(especimen.getColectorPrincipal());
                /* Insert secondary collectors */
                if (especimen.getColectoresSecundarios() != null && !especimen.getColectoresSecundarios().isEmpty()){
                    for (ColectorSecundario colectorSecundario : especimen.getColectoresSecundarios()) {
                        colectorSecundario.setEspecimenID(especimenId);
                        daoSession.getColectorSecundarioDao().insert(colectorSecundario);
                    }
                }
                if (especimen.getMuestrasAsociadas() != null && !especimen.getMuestrasAsociadas().isEmpty()){
                    for (MuestraAsociada muestraAsociada : especimen.getMuestrasAsociadas()){
                        muestraAsociada.setEspecimenID(especimenId);
                        daoSession.getMuestraAsociadaDao().insert(muestraAsociada);
                    }
                }
                if (especimen.getFotografias() != null && !especimen.getFotografias().isEmpty()){
                    for (Fotografia fotografia : especimen.getFotografias()){
                        fotografia.setEspecimenID(especimenId);
                        daoSession.getFotografiaDao().insert(fotografia);
                    }
                }
                /* Insert identidad taxonómica */
                if (identidadTaxonomica != null){
                    identidadTaxonomica.setEspecimenID(especimenId);
                    daoSession.getIdentidadTaxonomicaDao().insert(identidadTaxonomica);
                }
            }
        });
    }

}