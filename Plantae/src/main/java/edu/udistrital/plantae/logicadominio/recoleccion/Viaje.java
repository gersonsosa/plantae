package edu.udistrital.plantae.logicadominio.recoleccion;
import com.google.android.gms.maps.MapView;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.datosespecimen.*;
import edu.udistrital.plantae.logicadominio.taxonomia.*;
import edu.udistrital.plantae.logicadominio.ubicacion.*;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.*;
import edu.udistrital.plantae.ui.SpecimenPagesAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class Viaje {

    private Long id;
    private String nombre;
    private BuilderEspecimen builderEspecimen;
    private FabricaPrototipadoEspecimen fabricaPrototipadoEspecimen;
    private ColectorPrincipal colectorPrincipal;
    private Trayecto trayecto;
    private Proyecto proyecto;
    private List<ViajeColectorSecundario> colectoresSecundarios;
    private List<Especimen> especimenes;
    private Long colectorPrincipalID;
    private Long proyectoID;

    private Long colectorPrincipal__resolvedKey;
    private Long proyecto__resolvedKey;

	/* greendao specific properties */
    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ViajeDao myDao;

	public Viaje(){

	}

    public Viaje(ColectorPrincipal colectorPrincipal) {
        setColectorPrincipal(colectorPrincipal);
        //builderEspecimen = colectorPrincipal.getTipoCapturaDatos() == 1 ? new BuilderEspecimenDetallado():new BuilderEspecimenSencillo();
    }

    public void finalize() throws Throwable {

    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getViajeDao() : null;
    }

	/**
	 * Crea el viaje en base a los datos que se pasan como paramentros.
	 * @param colectores Colectores secundarios del viaje
	 * @param proyecto Proyecto al que pertenece el viaje
	 * @param nombre    nombre
	 */
	public Viaje(ViajeColectorSecundario[] colectores, Proyecto proyecto, String nombre){
        colectoresSecundarios = new ArrayList<ViajeColectorSecundario>(Arrays.asList(colectores));
        this.proyecto = proyecto;
        this.nombre = nombre;
	}

	/**
	 *
	 * @param proyecto
	 * @param nombre    nombre
	 */
	public Viaje(Proyecto proyecto, String nombre){
        this.proyecto = proyecto;
        this.nombre = nombre;
	}

    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getColectorPrincipalID() {
        return colectorPrincipalID;
    }

    public void setColectorPrincipalID(Long colectorPrincipalID) {
        this.colectorPrincipalID = colectorPrincipalID;
    }

    public Long getProyectoID() {
        return proyectoID;
    }

    public void setProyectoID(Long proyectoID) {
        this.proyectoID = proyectoID;
    }

	public Trayecto reconstruirTrayecto(){
		return null;
	}

	private MapView construirMapa(){
		return null;
	}

	/**
	 * Crea un colector secundario nuevo con el apellido y nombre y lo agrega
     * a la lista de colectores secundarios del viaje.
     * @<NOTE>Este método solo se debe llamar luego de guardar el viaje.</NOTE>
     * @param nombre del nuevo colector secundario
	 * @param apellido del nuevo colector secundario
	 */
	public void agregarColector(String nombre, String apellido){
        ColectorSecundario colectorSecundario = new ColectorSecundario();
        Persona colectorSecundarioPersona = new Persona(apellido, nombre);
        daoSession.getPersonaDao().insert(colectorSecundarioPersona);
        colectorSecundario.setPersona(colectorSecundarioPersona);
        ViajeColectorSecundario viajeColectorSecundario = new ViajeColectorSecundario();
        viajeColectorSecundario.setColectorSecundario(colectorSecundario);
        viajeColectorSecundario.setViaje(this);
        daoSession.getViajeColectorSecundarioDao().insert(viajeColectorSecundario);
        daoSession.getColectorSecundarioDao().insert(colectorSecundario);
	}

	/**
	 *
	 * @param colector    colector
	 */
	public void eliminarColector(ColectorSecundario colector){

	}

	public void agregarEspecimen(final EspecimenDTO especimenDTO){
        daoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                Long localidadId = null;
                Long habitatId = null;
                Long habitoId = null;
                Long fenologiaId = null;
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
                    localidad.setLatitud(especimenDTO.getLatitud());
                    localidad.setLongitud(especimenDTO.getLongitud());
                    localidad.setAltitudMinima(especimenDTO.getAltitudMinima());
                    localidad.setAltitudMaxima(especimenDTO.getAltitudMaxima());
                    localidad.setDatum(especimenDTO.getDatum());
                    localidad.setMarcaDispositivo(especimenDTO.getMarcaDispositivo());
                    localidad.setDescripcion(especimenDTO.getLocalidadDescripcion());
                    if (especimenDTO.getRegion() != null) {
                        Region region;
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
                if (especimenDTO.getFenologia() != null && especimenDTO.getFenologia().getId() == null){
                    fenologiaId = daoSession.getFenologiaDao().insert(especimenDTO.getFenologia());
                }
                IdentidadTaxonomica identidadTaxonomica = null;
                if (especimenDTO.getTaxon() != null) {
                    Taxon taxon;
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
                    identidadTaxonomica.setDeterminador(colectorPrincipal.getPersona());
                    identidadTaxonomica.setTipo(especimenDTO.getTipo());
                }
                // Save the list of colors
                String coloresFullTextSearch = "";
                List<ColorEspecimen> coloresEspecimen = new ArrayList<>();
                if (!especimenDTO.getColores().isEmpty()) {
                    for (Map.Entry<String, ColorEspecimenDTO> entry:especimenDTO.getColores().entrySet()) {
                        long colorEspecimenId = entry.getValue().getId() == null ? 0l : entry.getValue().getId();
                        long colorMunsellId = entry.getValue().getColorMunsellId() == null ? 0l : entry.getValue().getColorMunsellId();
                        ColorEspecimen colorEspecimen = new ColorEspecimen();
                        colorEspecimen.setNombre(entry.getValue().getNombre());
                        colorEspecimen.setDescripcion(entry.getValue().getDescripcion());
                        colorEspecimen.setColorRGB(entry.getValue().getColorRGB());
                        if (colorEspecimenId == 0l) {
                            if (colorMunsellId == 0l) {
                                colorMunsellId = daoSession.getColorMunsellDao().insert(new ColorMunsell(entry.getValue().getHue(), entry.getValue().getValue(), entry.getValue().getChroma()));
                            }
                            colorEspecimen.setUsuarioID(especimenDTO.getUsuarioId());
                            colorEspecimen.setColorMunsellID(colorMunsellId);
                            colorEspecimenId = daoSession.getColorEspecimenDao().insert(colorEspecimen);
                            coloresEspecimen.add(colorEspecimen);
                        } else {
                            daoSession.getColorEspecimenDao().update(colorEspecimen);
                            coloresEspecimen.add(colorEspecimen);
                        }
                        if (!entry.getKey().contains("especimen")) {
                            if(entry.getKey().contains("Flower Corolla")) {
                                especimenDTO.setColorDeLaCorolaID(colorEspecimenId);
                            }else if(entry.getKey().contains("Flower Calyx")) {
                                especimenDTO.setColorDelCalizID(colorEspecimenId);
                            }else if(entry.getKey().contains("Flower Gineceo")) {
                                especimenDTO.setColorDelGineceoID(colorEspecimenId);
                            }else if(entry.getKey().contains("Flower Stamens")) {
                                especimenDTO.setColorDeLosEstambresID(colorEspecimenId);
                            }else if(entry.getKey().contains("Flower Stigmata")) {
                                especimenDTO.setColorDeLosEstigmasID(colorEspecimenId);
                            }else if(entry.getKey().contains("Flower Pistiliodios")) {
                                especimenDTO.setColorDeLosPistiliodiosID(colorEspecimenId);
                            }else if(entry.getKey().contains("Fruit Endocarp")) {
                                especimenDTO.setColorDelEndocarpioID(colorEspecimenId);
                            }else if(entry.getKey().contains("Fruit Excarp")) {
                                especimenDTO.setColorDelExocarpioID(colorEspecimenId);
                            }else if(entry.getKey().contains("Inflorescence Flower")) {
                                especimenDTO.setColorDeLaInflorescenciaEnFlorID(colorEspecimenId);
                            }else if(entry.getKey().contains("Inflorescence Fruit")) {
                                especimenDTO.setColorDeLaInflorescenciaEnFrutoID(colorEspecimenId);
                            }else if(entry.getKey().contains("Leaves")) {
                                especimenDTO.setColorDelPecioloID(colorEspecimenId);
                            }else if(entry.getKey().contains("Leaves Petiole")) {
                                especimenDTO.setColorDelPecioloID(colorEspecimenId);
                            }else if(entry.getKey().contains("Stem")) {
                                especimenDTO.setColorDelTalloID(colorEspecimenId);
                            }
                        }
                        coloresFullTextSearch = coloresFullTextSearch.concat(colorEspecimen.getNombre().concat(" ").concat(colorEspecimen.getColorName()!=null ? colorEspecimen.getColorName():""));
                    }
                }
                if (especimenDTO.getFlorDescripcion() != null && especimenDTO.getFlorId() == null) {
                    Flor flor = new Flor();
                    flor.setDescripcion(especimenDTO.getFlorDescripcion());
                    flor.setColorDeLaCorolaID(especimenDTO.getColorDeLaCorolaID());
                    flor.setColorDelCalizID(especimenDTO.getColorDelCalizID());
                    flor.setColorDelGineceoID(especimenDTO.getColorDelGineceoID());
                    flor.setColorDeLosEstambresID(especimenDTO.getColorDeLosEstambresID());
                    flor.setColorDeLosEstigmasID(especimenDTO.getColorDeLosEstigmasID());
                    flor.setColorDeLosPistiliodiosID(especimenDTO.getColorDeLosPistiliodiosID());
                    florId = daoSession.getFlorDao().insert(flor);
                }else if (especimenDTO.getFlorId() != null) {
                    Flor flor = daoSession.getFlorDao().load(especimenDTO.getFlorId());
                    flor.setColorDeLaCorolaID(especimenDTO.getColorDeLaCorolaID());
                    flor.setColorDelCalizID(especimenDTO.getColorDelCalizID());
                    flor.setColorDelGineceoID(especimenDTO.getColorDelGineceoID());
                    flor.setColorDeLosEstambresID(especimenDTO.getColorDeLosEstambresID());
                    flor.setColorDeLosEstigmasID(especimenDTO.getColorDeLosEstigmasID());
                    flor.setColorDeLosPistiliodiosID(especimenDTO.getColorDeLosPistiliodiosID());
                    daoSession.getFlorDao().update(flor);
                    florId = especimenDTO.getFlorId();
                }
                if (especimenDTO.getInflorescenciaDescripcion() != null && especimenDTO.getInflorescenciaId() == null) {
                    Inflorescencia inflorescencia = new Inflorescencia();
                    inflorescencia.setDescripcion(especimenDTO.getInflorescenciaDescripcion());
                    inflorescencia.setColorDeLaInflorescenciaEnFlorID(especimenDTO.getColorDeLaInflorescenciaEnFlorID());
                    inflorescencia.setColorDeLaInflorescenciaEnFrutoID(especimenDTO.getColorDeLaInflorescenciaEnFrutoID());
                    inflorescencia.setNaturalezaDeLasBracteasPedunculares(especimenDTO.getNaturalezaDeLasBracteasPedunculares());
                    inflorescencia.setNaturalezaDelProfilo(especimenDTO.getNaturalezaDelProfilo());
                    inflorescencia.setPosicionDeLasBracteasPedunculares(especimenDTO.getPosicionDeLasBracteasPedunculares());
                    inflorescencia.setPosicionDeLasInflorescencias(especimenDTO.getPosicionDeLasInflorescencias());
                    inflorescencia.setRaquilas(especimenDTO.getRaquilas());
                    inflorescencia.setRaquis(especimenDTO.getRaquis());
                    inflorescencia.setTamañoDeLasBracteasPedunculares(especimenDTO.getTamañoDeLasBracteasPedunculares());
                    inflorescencia.setTamañoDelPedunculo(especimenDTO.getTamañoDelPedunculo());
                    inflorescencia.setTamañoDelProfilo(especimenDTO.getTamañoDelProfilo());
                    inflorescencia.setTamañoDelRaquis(especimenDTO.getTamañoDelRaquis());
                    inflorescencia.setTamañoDeRaquilas(especimenDTO.getTamañoDeRaquilas());
                    inflorescencia.setInflorescenciaSolitaria(especimenDTO.getInflorescenciaSolitaria());
                    inflorescencia.setNumeroDeLasBracteasPedunculares(especimenDTO.getNumeroDeLasBracteasPedunculares());
                    inflorescencia.setNumeroDeRaquilas(especimenDTO.getNumeroDeRaquilas());
                    inflorescenciaId = daoSession.getInflorescenciaDao().insert(inflorescencia);
                }else if (especimenDTO.getInflorescenciaId() != null){
                    Inflorescencia inflorescencia = daoSession.getInflorescenciaDao().load(especimenDTO.getInflorescenciaId());
                    inflorescencia.setDescripcion(especimenDTO.getInflorescenciaDescripcion());
                    inflorescencia.setColorDeLaInflorescenciaEnFlorID(especimenDTO.getColorDeLaInflorescenciaEnFlorID());
                    inflorescencia.setColorDeLaInflorescenciaEnFrutoID(especimenDTO.getColorDeLaInflorescenciaEnFrutoID());
                    inflorescencia.setNaturalezaDeLasBracteasPedunculares(especimenDTO.getNaturalezaDeLasBracteasPedunculares());
                    inflorescencia.setNaturalezaDelProfilo(especimenDTO.getNaturalezaDelProfilo());
                    inflorescencia.setPosicionDeLasBracteasPedunculares(especimenDTO.getPosicionDeLasBracteasPedunculares());
                    inflorescencia.setPosicionDeLasInflorescencias(especimenDTO.getPosicionDeLasInflorescencias());
                    inflorescencia.setRaquilas(especimenDTO.getRaquilas());
                    inflorescencia.setRaquis(especimenDTO.getRaquis());
                    inflorescencia.setTamañoDeLasBracteasPedunculares(especimenDTO.getTamañoDeLasBracteasPedunculares());
                    inflorescencia.setTamañoDelPedunculo(especimenDTO.getTamañoDelPedunculo());
                    inflorescencia.setTamañoDelProfilo(especimenDTO.getTamañoDelProfilo());
                    inflorescencia.setTamañoDelRaquis(especimenDTO.getTamañoDelRaquis());
                    inflorescencia.setTamañoDeRaquilas(especimenDTO.getTamañoDeRaquilas());
                    inflorescencia.setInflorescenciaSolitaria(especimenDTO.getInflorescenciaSolitaria());
                    inflorescencia.setNumeroDeLasBracteasPedunculares(especimenDTO.getNumeroDeLasBracteasPedunculares());
                    inflorescencia.setNumeroDeRaquilas(especimenDTO.getNumeroDeRaquilas());
                    daoSession.getInflorescenciaDao().update(inflorescencia);
                    inflorescenciaId = especimenDTO.getInflorescenciaId();
                }
                if (especimenDTO.getTalloDescripcion() != null && especimenDTO.getTalloId() == null) {
                    Tallo tallo = new Tallo();
                    tallo.setAlturaDelTallo(especimenDTO.getAlturaDelTallo());
                    tallo.setColorDelTalloID(especimenDTO.getColorDelTalloID());
                    tallo.setDiametroDelTallo(especimenDTO.getDiametroDelTallo());
                    tallo.setDisposicionDeLasEspinas(especimenDTO.getDisposicionDeLasEspinas());
                    tallo.setFormaDelTallo(especimenDTO.getFormaDelTallo());
                    tallo.setLongitudEntrenudos(especimenDTO.getLongitudEntrenudos());
                    tallo.setNaturalezaDelTallo(especimenDTO.getNaturalezaDelTallo());
                    tallo.setDesnudoCubierto(especimenDTO.getDesnudoCubierto());
                    tallo.setEntrenudosConspicuos(especimenDTO.getEntrenudosConspicuos());
                    tallo.setEspinas(especimenDTO.getEspinas());
                    talloId = daoSession.getTalloDao().insert(tallo);
                }else if (especimenDTO.getTalloId() != null) {
                    Tallo tallo = daoSession.getTalloDao().load(especimenDTO.getTalloId());
                    tallo.setAlturaDelTallo(especimenDTO.getAlturaDelTallo());
                    tallo.setColorDelTalloID(especimenDTO.getColorDelTalloID());
                    tallo.setDiametroDelTallo(especimenDTO.getDiametroDelTallo());
                    tallo.setDisposicionDeLasEspinas(especimenDTO.getDisposicionDeLasEspinas());
                    tallo.setFormaDelTallo(especimenDTO.getFormaDelTallo());
                    tallo.setLongitudEntrenudos(especimenDTO.getLongitudEntrenudos());
                    tallo.setNaturalezaDelTallo(especimenDTO.getNaturalezaDelTallo());
                    tallo.setDesnudoCubierto(especimenDTO.getDesnudoCubierto());
                    tallo.setEntrenudosConspicuos(especimenDTO.getEntrenudosConspicuos());
                    tallo.setEspinas(especimenDTO.getEspinas());
                    daoSession.getTalloDao().update(tallo);
                    talloId = especimenDTO.getTalloId();
                }
                if (especimenDTO.getRaizDescripcion() != null && especimenDTO.getRaizId() == null) {
                    Raiz raiz = new Raiz();
                    raiz.setDiametroDeLasRaices(especimenDTO.getDiametroDeLasRaices());
                    raiz.setDiametroEnLaBase(especimenDTO.getDiametroEnLaBase());
                    raiz.setFormaDeLasEspinas(especimenDTO.getFormaDeLasEspinas());
                    raiz.setTamañoDeLasEspinas(especimenDTO.getTamañoDeLasEspinas());
                    raiz.setDescripcion(especimenDTO.getRaizDescripcion());
                    raiz.setRaizArmada(especimenDTO.getRaizArmada());
                    raiz.setAlturaDelCono(especimenDTO.getAlturaDelCono());
                    raizId = daoSession.getRaizDao().insert(raiz);
                }else if (especimenDTO.getRaizId() != null) {
                    Raiz raiz = daoSession.getRaizDao().load(especimenDTO.getRaizId());
                    raiz.setDiametroDeLasRaices(especimenDTO.getDiametroDeLasRaices());
                    raiz.setDiametroEnLaBase(especimenDTO.getDiametroEnLaBase());
                    raiz.setFormaDeLasEspinas(especimenDTO.getFormaDeLasEspinas());
                    raiz.setTamañoDeLasEspinas(especimenDTO.getTamañoDeLasEspinas());
                    raiz.setDescripcion(especimenDTO.getRaizDescripcion());
                    raiz.setRaizArmada(especimenDTO.getRaizArmada());
                    raiz.setAlturaDelCono(especimenDTO.getAlturaDelCono());
                    daoSession.getRaizDao().update(raiz);
                    raizId = especimenDTO.getRaizId();
                }
                if (especimenDTO.getFrutoDescripcion() != null && especimenDTO.getFrutoId() == null) {
                    Fruto fruto = new Fruto();
                    fruto.setColorDelEndocarpioID(especimenDTO.getColorDelEndocarpioID());
                    fruto.setColorDelExocarpioID(especimenDTO.getColorDelExocarpioID());
                    fruto.setConsistenciaDelPericarpio(especimenDTO.getConsistenciaDelPericarpio());
                    fruto.setDescripcion(especimenDTO.getFrutoDescripcion());
                    frutoId = daoSession.getFrutoDao().insert(fruto);
                }else if (especimenDTO.getFrutoId() != null) {
                    Fruto fruto = daoSession.getFrutoDao().load(especimenDTO.getFrutoId());
                    fruto.setColorDelEndocarpioID(especimenDTO.getColorDelEndocarpioID());
                    fruto.setColorDelExocarpioID(especimenDTO.getColorDelExocarpioID());
                    fruto.setConsistenciaDelPericarpio(especimenDTO.getConsistenciaDelPericarpio());
                    fruto.setDescripcion(especimenDTO.getFrutoDescripcion());
                    daoSession.getFrutoDao().update(fruto);
                    frutoId = especimenDTO.getFrutoId();
                }
                if (especimenDTO.getHojaDescripcion() != null && especimenDTO.getHojaId() == null) {
                    Hoja hoja = new Hoja();
                    hoja.setCoberturaDelPeciolo(especimenDTO.getCoberturaDelPeciolo());
                    hoja.setColorDeLasHojasID(especimenDTO.getColorDeLasHojasID());
                    hoja.setColorDelPecioloID(especimenDTO.getColorDelPecioloID());
                    hoja.setDispocicionDeLasPinnas(especimenDTO.getDispocicionDeLasPinnas());
                    hoja.setDisposicionDeLasHojas(especimenDTO.getDisposicionDeLasHojas());
                    hoja.setFormaDelPeciolo(especimenDTO.getFormaDelPeciolo());
                    hoja.setLonguitudDelRaquis(especimenDTO.getLonguitudDelRaquis());
                    hoja.setNaturalezaDeLaVaina(especimenDTO.getNaturalezaDeLaVaina());
                    hoja.setNaturalezaDelLimbo(especimenDTO.getNaturalezaDelLimbo());
                    hoja.setNumeroDePinnas(especimenDTO.getNumeroDePinnas());
                    hoja.setNumeroHojas(especimenDTO.getNumeroHojas());
                    hoja.setTamañoDeLasHojas(especimenDTO.getTamañoDeLasHojas());
                    hoja.setTamañoDelPeciolo(especimenDTO.getTamañoDelPeciolo());
                    hoja.setDescripcion(especimenDTO.getHojaDescripcion());
                    hojaId = daoSession.getHojaDao().insert(hoja);
                }else if (especimenDTO.getHojaId() != null) {
                    Hoja hoja = daoSession.getHojaDao().load(especimenDTO.getHojaId());
                    hoja.setCoberturaDelPeciolo(especimenDTO.getCoberturaDelPeciolo());
                    hoja.setColorDeLasHojasID(especimenDTO.getColorDeLasHojasID());
                    hoja.setColorDelPecioloID(especimenDTO.getColorDelPecioloID());
                    hoja.setDispocicionDeLasPinnas(especimenDTO.getDispocicionDeLasPinnas());
                    hoja.setDisposicionDeLasHojas(especimenDTO.getDisposicionDeLasHojas());
                    hoja.setFormaDelPeciolo(especimenDTO.getFormaDelPeciolo());
                    hoja.setLonguitudDelRaquis(especimenDTO.getLonguitudDelRaquis());
                    hoja.setNaturalezaDeLaVaina(especimenDTO.getNaturalezaDeLaVaina());
                    hoja.setNaturalezaDelLimbo(especimenDTO.getNaturalezaDelLimbo());
                    hoja.setNumeroDePinnas(especimenDTO.getNumeroDePinnas());
                    hoja.setNumeroHojas(especimenDTO.getNumeroHojas());
                    hoja.setTamañoDeLasHojas(especimenDTO.getTamañoDeLasHojas());
                    hoja.setTamañoDelPeciolo(especimenDTO.getTamañoDelPeciolo());
                    hoja.setDescripcion(especimenDTO.getHojaDescripcion());
                    daoSession.getHojaDao().update(hoja);
                    hojaId = especimenDTO.getHojaId();
                }
                BuilderEspecimen builderEspecimen;
                if (especimenDTO.getTipoCaptura() == SpecimenPagesAdapter.SPECIMEN_SINGLE){
                    builderEspecimen = new BuilderEspecimenSencillo(especimenDTO.getNumeroDeColeccion(), especimenDTO.getViajeID(), especimenDTO.getColectorPrincipalID())
                            .fechaInicial(especimenDTO.getFechaInicial()).fechaFinal(especimenDTO.getFechaFinal())
                            .metodoColeccion(especimenDTO.getMetodoColeccion()).estacionDelAño(especimenDTO.getEstacionDelAño())
                            .colectoresSecundarios(especimenDTO.getColectoresSecundarios())
                            .localidadID(localidadId)
                            .identidadTaxonomica(identidadTaxonomica)
                            .habitatID(habitatId)
                            .habitoID(habitoId)
                            .fenologiaID(fenologiaId)
                            .alturaDeLaPlanta(especimenDTO.getAlturaDeLaPlanta()).dap(especimenDTO.getDap())
                            .abundancia(especimenDTO.getAbundancia())
                            .descripcionEspecimen(especimenDTO.getDescripcionEspecimen())
                            .muestrasAsociadas(especimenDTO.getMuestrasAsociadas())
                            .florID(florId)
                            .fotografias(especimenDTO.getFotografias());
                }else{
                    builderEspecimen = new BuilderEspecimenDetallado(especimenDTO.getNumeroDeColeccion(), especimenDTO.getViajeID(), especimenDTO.getColectorPrincipalID())
                            .fechaInicial(especimenDTO.getFechaInicial()).fechaFinal(especimenDTO.getFechaFinal())
                            .metodoColeccion(especimenDTO.getMetodoColeccion()).estacionDelAño(especimenDTO.getEstacionDelAño())
                            .colectoresSecundarios(especimenDTO.getColectoresSecundarios())
                            .localidadID(localidadId)
                            .identidadTaxonomica(identidadTaxonomica)
                            .habitatID(habitatId)
                            .habitoID(habitoId)
                            .fenologiaID(fenologiaId)
                            .alturaDeLaPlanta(especimenDTO.getAlturaDeLaPlanta()).dap(especimenDTO.getDap())
                            .abundancia(especimenDTO.getAbundancia())
                            .descripcionEspecimen(especimenDTO.getDescripcionEspecimen())
                            .muestrasAsociadas(especimenDTO.getMuestrasAsociadas())
                            .florID(florId)
                            .raizID(raizId)
                            .talloID(talloId)
                            .inflorescenciaID(inflorescenciaId)
                            .frutoID(frutoId)
                            .hojaID(hojaId)
                            .fotografias(especimenDTO.getFotografias());
                }
                builderEspecimen.build();
                Especimen especimen = builderEspecimen.getEspecimen();
                Long especimenId = daoSession.getEspecimenDao().insert(especimen);
                especimen.getColectorPrincipal().setNumeroColeccionActual(especimen.getNumeroDeColeccion());
                daoSession.getColectorPrincipalDao().update(especimen.getColectorPrincipal());
                if (!coloresEspecimen.isEmpty()) {
                    for (ColorEspecimen colorEspecimen : coloresEspecimen) {
                        colorEspecimen.setEspecimenID(especimenId);
                        daoSession.getColorEspecimenDao().update(colorEspecimen);
                    }
                }
                /* Insert secondary collectors */
                if (especimen.getColectoresSecundarios() != null && !especimen.getColectoresSecundarios().isEmpty()){
                    for (EspecimenColectorSecundario especimenColectorSecundario : especimen.getColectoresSecundarios()) {
                        especimenColectorSecundario.setEspecimenID(especimenId);
                        daoSession.getEspecimenColectorSecundarioDao().insert(especimenColectorSecundario);
                    }
                }
                if (especimen.getMuestrasAsociadas() != null && ! especimen.getMuestrasAsociadas().isEmpty()){
                    for (MuestraAsociada muestraAsociada : especimen.getMuestrasAsociadas()){
                        muestraAsociada.setEspecimenID(especimenId);
                        if (muestraAsociada.getId() == null) {
                            daoSession.getMuestraAsociadaDao().insert(muestraAsociada);
                        } else {
                            daoSession.getMuestraAsociadaDao().update(muestraAsociada);
                        }
                    }
                }
                if (especimen.getFotografias() != null && ! especimen.getFotografias().isEmpty()){
                    for (Fotografia fotografia : especimen.getFotografias()){
                        fotografia.setEspecimenID(especimenId);
                        daoSession.getFotografiaDao().insert(fotografia);
                    }
                }
                String identidadTaxonomicaText = "";
                if (identidadTaxonomica != null){
                    identidadTaxonomica.setEspecimenID(especimenId);
                    daoSession.getIdentidadTaxonomicaDao().insert(identidadTaxonomica);
                    identidadTaxonomicaText = identidadTaxonomica.getTaxon().toString().concat(" ").concat(identidadTaxonomica.getTipo() != null?identidadTaxonomica.getTipo():"");
                }
                /**
                 * Since the colors and determination are inserted after the specimen
                 * the trigger that inserts the specimen information into the FTS table
                 * cannot insert this information. If the colors or determination
                 * of the specimen is not empty it updates the full text search
                 * (FTS) table for queries.
                 */
                if (!coloresFullTextSearch.equals("") || !identidadTaxonomicaText.equals("")) {
                    FTSEspecimenDao ftsEspecimenDao = new FTSEspecimenDao(daoSession);
                    ftsEspecimenDao.update(coloresFullTextSearch, identidadTaxonomicaText, especimenId);
                }
            }
        });
	}

	/**
	 * Agrega una muestra asociada al especimen
	 * @param metodoDeTratamiento El método de tratamiento que se quiere agregar al Especimen
	 * @param especimen El especimen
	 */
	public void agregarMuestraAsociada(String metodoDeTratamiento, Especimen especimen){

	}

    public Trayecto getTrayecto(){
        return trayecto;
    }

    public void setTrayecto(Trayecto trayecto){
        this.trayecto = trayecto;
    }

    public ColectorPrincipal getColectorPrincipal() {
        Long __key = this.colectorPrincipalID;
        if (colectorPrincipal__resolvedKey == null || !colectorPrincipal__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColectorPrincipalDao targetDao = daoSession.getColectorPrincipalDao();
            ColectorPrincipal colectorPrincipalNew = targetDao.load(__key);
            synchronized (this) {
                colectorPrincipal = colectorPrincipalNew;
            	colectorPrincipal__resolvedKey = __key;
            }
        }
        return colectorPrincipal;
    }

    public void setColectorPrincipal(ColectorPrincipal colectorPrincipal) {
        synchronized (this) {
            this.colectorPrincipal = colectorPrincipal;
            colectorPrincipalID = colectorPrincipal == null ? null : colectorPrincipal.getId();
            colectorPrincipal__resolvedKey = colectorPrincipalID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Proyecto getProyecto() {
        Long __key = this.proyectoID;
        if (proyecto__resolvedKey == null || !proyecto__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProyectoDao targetDao = daoSession.getProyectoDao();
            Proyecto proyectoNew = targetDao.load(__key);
            synchronized (this) {
                proyecto = proyectoNew;
            	proyecto__resolvedKey = __key;
            }
        }
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        synchronized (this) {
            this.proyecto = proyecto;
            proyectoID = proyecto == null ? null : proyecto.getId();
            proyecto__resolvedKey = proyectoID;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ViajeColectorSecundario> getColectoresSecundarios() {
        if (colectoresSecundarios == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ViajeColectorSecundarioDao targetDao = daoSession.getViajeColectorSecundarioDao();
            List<ViajeColectorSecundario> colectoresSecundariosNew = targetDao._queryViaje_ColectoresSecundarios(id);
            synchronized (this) {
                if(colectoresSecundarios == null) {
                    colectoresSecundarios = colectoresSecundariosNew;
                }
            }
        }
        return colectoresSecundarios;
    }

    public void setColectoresSecundarios(List<ViajeColectorSecundario> colectoresSecundarios) {
        this.colectoresSecundarios = colectoresSecundarios;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Especimen> getEspecimenes() {
        if (especimenes == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EspecimenDao targetDao = daoSession.getEspecimenDao();
            List<Especimen> especimenesNew = targetDao._queryViaje_Especimenes(id);
            synchronized (this) {
                if(especimenes == null) {
                    especimenes = especimenesNew;
                }
            }
        }
        return especimenes;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetEspecimenes() {
        especimenes = null;
    }

}
