package edu.udistrital.plantae.logicadominio.recoleccion;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.datosespecimen.BuilderEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.BuilderEspecimenDetallado;
import edu.udistrital.plantae.logicadominio.datosespecimen.BuilderEspecimenSencillo;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorMunsell;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.EspecimenColectorSecundario;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fenologia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Flor;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fotografia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fruto;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habitat;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habito;
import edu.udistrital.plantae.logicadominio.datosespecimen.Hoja;
import edu.udistrital.plantae.logicadominio.datosespecimen.Inflorescencia;
import edu.udistrital.plantae.logicadominio.datosespecimen.MuestraAsociada;
import edu.udistrital.plantae.logicadominio.datosespecimen.Raiz;
import edu.udistrital.plantae.logicadominio.datosespecimen.Tallo;
import edu.udistrital.plantae.logicadominio.taxonomia.EpitetoEspecifico;
import edu.udistrital.plantae.logicadominio.taxonomia.Familia;
import edu.udistrital.plantae.logicadominio.taxonomia.Genero;
import edu.udistrital.plantae.logicadominio.taxonomia.IdentidadTaxonomica;
import edu.udistrital.plantae.logicadominio.taxonomia.Taxon;
import edu.udistrital.plantae.logicadominio.ubicacion.Departamento;
import edu.udistrital.plantae.logicadominio.ubicacion.Localidad;
import edu.udistrital.plantae.logicadominio.ubicacion.Municipio;
import edu.udistrital.plantae.logicadominio.ubicacion.Pais;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.ColectorPrincipalDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.EspecimenColectorSecundarioDao;
import edu.udistrital.plantae.persistencia.EspecimenDao;
import edu.udistrital.plantae.persistencia.FTSEspecimenDao;
import edu.udistrital.plantae.persistencia.ProyectoDao;
import edu.udistrital.plantae.persistencia.RegionDao;
import edu.udistrital.plantae.persistencia.TaxonDao;
import edu.udistrital.plantae.persistencia.ViajeColectorSecundarioDao;
import edu.udistrital.plantae.persistencia.ViajeDao;
import edu.udistrital.plantae.ui.SpecimenPagesAdapter;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class Viaje {

    private Long id;
    private String nombre;
    private BuilderEspecimen builderEspecimen;
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
        colectoresSecundarios = new ArrayList<>(Arrays.asList(colectores));
        this.proyecto = proyecto;
        this.nombre = nombre;
	}

	/**
	 * Crea un viaje
	 * @param proyecto el proyecto al que pertenece el viaje
	 * @param nombre el nombre del viaje
	 */
	public Viaje(Proyecto proyecto, String nombre){
        this.proyecto = proyecto;
        this.nombre = nombre;
	}

    public Long getId() {
        return id;
    }

    /**
     * Cambia el id del viaje
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del viaje
     * @param nombre nuevo nombre
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
                Long localidadId = actualizarLocalidad(especimenDTO);
                Long habitatId = actualizarHabitat(especimenDTO);
                Long habitoId = actualizarHabito(especimenDTO);
                Long fenologiaId = actualizarFenologia(especimenDTO);
                IdentidadTaxonomica identidadTaxonomica = actualizarIdentidadTaxonomica(especimenDTO);
                // Save the list of colors
                String coloresFullTextSearch;
                List<ColorEspecimen> coloresEspecimen = new ArrayList<>();
                coloresFullTextSearch = actualizarColores(coloresEspecimen, especimenDTO);
                Long florId = actualizarFlor(especimenDTO);
                Long inflorescenciaId = actualizarInflorescencia(especimenDTO);
                Long talloId = actualizarTallo(especimenDTO);
                Long raizId = actualizarRaiz(especimenDTO);
                Long frutoId = actualizarFruto(especimenDTO);
                Long hojaId = actualizarHoja(especimenDTO);
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
                especimenDTO.setId(especimenId);
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
                    identidadTaxonomicaText = identidadTaxonomica.getTaxon().aString().concat(" ").concat(identidadTaxonomica.getTipo() != null?identidadTaxonomica.getTipo():"");
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

    public void actualizarEspecimen(final EspecimenDTO especimenDTO) {
        daoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                Long localidadId = actualizarLocalidad(especimenDTO);
                Long habitatId = actualizarHabitat(especimenDTO);
                Long habitoId = actualizarHabito(especimenDTO);
                Long fenologiaId = actualizarFenologia(especimenDTO);
                IdentidadTaxonomica identidadTaxonomica = actualizarIdentidadTaxonomica(especimenDTO);
                // Save the list of colors
                String coloresFullTextSearch;
                List<ColorEspecimen> coloresEspecimen = new ArrayList<>();
                coloresFullTextSearch = actualizarColores(coloresEspecimen, especimenDTO);
                Long florId = actualizarFlor(especimenDTO);
                Long inflorescenciaId = actualizarInflorescencia(especimenDTO);
                Long talloId = actualizarTallo(especimenDTO);
                Long raizId = actualizarRaiz(especimenDTO);
                Long frutoId = actualizarFruto(especimenDTO);
                Long hojaId = actualizarHoja(especimenDTO);
                Especimen especimen = daoSession.getEspecimenDao().loadDeep(especimenDTO.getId());
                especimen.setMetodoColeccion(especimenDTO.getMetodoColeccion());
                especimen.setEstacionDelAño(especimenDTO.getEstacionDelAño());
                especimen.setAlturaDeLaPlanta(especimenDTO.getAlturaDeLaPlanta());
                especimen.setDap(especimenDTO.getDap());
                especimen.setAbundancia(especimenDTO.getAbundancia());
                especimen.setDescripcionEspecimen(especimenDTO.getDescripcionEspecimen());
                especimen.setColectoresSecundarios(especimenDTO.getColectoresSecundarios());
                especimen.setLocalidadID(localidadId);
                if (identidadTaxonomica != null) {
                    List<IdentidadTaxonomica> determinaciones = new ArrayList<>(1);
                    determinaciones.add(identidadTaxonomica);
                    especimen.setDeterminaciones(determinaciones);
                }
                especimen.setHabitatID(habitatId);
                especimen.setHabitoID(habitoId);
                especimen.setFenologiaID(fenologiaId);
                especimen.setColores(coloresEspecimen);
                especimen.setMuestrasAsociadas(especimenDTO.getMuestrasAsociadas());
                especimen.setFlorID(florId);
                if (especimenDTO.getTipoCaptura() != SpecimenPagesAdapter.SPECIMEN_SINGLE) {
                    especimen.setRaizID(raizId);
                    especimen.setTalloID(talloId);
                    especimen.setInflorescenciaID(inflorescenciaId);
                    especimen.setFrutoID(frutoId);
                    especimen.setHojaID(hojaId);
                }
                especimen.setFotografias(especimenDTO.getFotografias());
                daoSession.getEspecimenDao().update(especimen);
                especimen.getColectorPrincipal().setNumeroColeccionActual(especimen.getNumeroDeColeccion());
                daoSession.getColectorPrincipalDao().update(especimen.getColectorPrincipal());
                if (!coloresEspecimen.isEmpty()) {
                    for (ColorEspecimen colorEspecimen : coloresEspecimen) {
                        colorEspecimen.setEspecimenID(especimenDTO.getId());
                        daoSession.getColorEspecimenDao().update(colorEspecimen);
                    }
                }
                /* Insert secondary collectors */
                if (especimen.getColectoresSecundarios() != null && !especimen.getColectoresSecundarios().isEmpty()){
                    for (EspecimenColectorSecundario especimenColectorSecundario : especimen.getColectoresSecundarios()) {
                        if (daoSession.getEspecimenColectorSecundarioDao()
                                .queryBuilder().where(EspecimenColectorSecundarioDao.Properties
                                        .EspecimenID.eq(especimenColectorSecundario.getEspecimenID()))
                                .where(EspecimenColectorSecundarioDao.Properties
                                        .ColectorSecundarioID.eq(especimenColectorSecundario.getColectorSecundarioID())).count() == 0) {
                            especimenColectorSecundario.setEspecimenID(especimenDTO.getId());
                            daoSession.getEspecimenColectorSecundarioDao().insert(especimenColectorSecundario);
                        }
                    }
                }
                if (especimen.getMuestrasAsociadas() != null && ! especimen.getMuestrasAsociadas().isEmpty()){
                    for (MuestraAsociada muestraAsociada : especimen.getMuestrasAsociadas()){
                        muestraAsociada.setEspecimenID(especimenDTO.getId());
                        if (muestraAsociada.getId() == null) {
                            daoSession.getMuestraAsociadaDao().insert(muestraAsociada);
                        } else {
                            daoSession.getMuestraAsociadaDao().update(muestraAsociada);
                        }
                    }
                }
                if (especimen.getFotografias() != null && ! especimen.getFotografias().isEmpty()){
                    for (Fotografia fotografia : especimen.getFotografias()){
                        if (fotografia.getId() == null) {
                            fotografia.setEspecimenID(especimenDTO.getId());
                            daoSession.getFotografiaDao().insert(fotografia);
                        }else{
                            daoSession.getFotografiaDao().update(fotografia);
                        }
                    }
                }
                String identidadTaxonomicaText = "";
                if (identidadTaxonomica != null){
                    identidadTaxonomica.setEspecimenID(especimenDTO.getId());
                    daoSession.getIdentidadTaxonomicaDao().insert(identidadTaxonomica);
                    identidadTaxonomicaText = identidadTaxonomica.getTaxon().aString().concat(" ").concat(identidadTaxonomica.getTipo() != null?identidadTaxonomica.getTipo():"");
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
                    ftsEspecimenDao.update(coloresFullTextSearch, identidadTaxonomicaText, especimenDTO.getId());
                }
            }
        });
    }

    private String actualizarColores(List<ColorEspecimen> coloresEspecimen, EspecimenDTO especimenDTO) {
        String coloresFullTextSearch = "";
        if (!especimenDTO.getColores().isEmpty()) {
            for (ColorEspecimenDTO colorEspecimenDTO : especimenDTO.getColores()) {
                long colorEspecimenId = colorEspecimenDTO.getId() == null ? 0l : colorEspecimenDTO.getId();
                long colorMunsellId = colorEspecimenDTO.getColorMunsellId() == null ? 0l : colorEspecimenDTO.getColorMunsellId();
                if (colorMunsellId == 0l) {
                    colorMunsellId = daoSession.getColorMunsellDao().insert(new ColorMunsell(colorEspecimenDTO.getHue(), colorEspecimenDTO.getValue(), colorEspecimenDTO.getChroma()));
                }
                ColorEspecimen colorEspecimen;
                if (colorEspecimenId == 0l) {
                    colorEspecimen = new ColorEspecimen();
                    colorEspecimen.setNombre(colorEspecimenDTO.getNombre());
                    colorEspecimen.setDescripcion(colorEspecimenDTO.getDescripcion());
                    colorEspecimen.setOrganoDeLaPlanta(colorEspecimenDTO.getOrganoDeLaPlanta());
                    colorEspecimen.setColorRGB(colorEspecimenDTO.getColorRGB());
                    colorEspecimen.setUsuarioID(especimenDTO.getUsuarioId());
                    colorEspecimen.setColorMunsellID(colorMunsellId);
                    colorEspecimenId = daoSession.getColorEspecimenDao().insert(colorEspecimen);
                    coloresEspecimen.add(colorEspecimen);
                } else {
                    colorEspecimen = daoSession.getColorEspecimenDao().load(colorEspecimenId);
                    colorEspecimen.setNombre(colorEspecimenDTO.getNombre());
                    colorEspecimen.setDescripcion(colorEspecimenDTO.getDescripcion());
                    colorEspecimen.setOrganoDeLaPlanta(colorEspecimenDTO.getOrganoDeLaPlanta());
                    colorEspecimen.setColorRGB(colorEspecimenDTO.getColorRGB());
                    colorEspecimen.setUsuarioID(especimenDTO.getUsuarioId());
                    colorEspecimen.setColorMunsellID(colorMunsellId);
                    daoSession.getColorEspecimenDao().update(colorEspecimen);
                    coloresEspecimen.add(colorEspecimen);
                }
                String organoDeLaPlanta = colorEspecimenDTO.getOrganoDeLaPlanta();
                if (organoDeLaPlanta != null && !organoDeLaPlanta.isEmpty()) {
                    if (organoDeLaPlanta.contains("Flower Corolla")) {
                        especimenDTO.setColorDeLaCorolaID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Flower Calyx")) {
                        especimenDTO.setColorDelCalizID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Flower Gineceo")) {
                        especimenDTO.setColorDelGineceoID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Flower Stamens")) {
                        especimenDTO.setColorDeLosEstambresID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Flower Stigmata")) {
                        especimenDTO.setColorDeLosEstigmasID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Flower Pistiliodios")) {
                        especimenDTO.setColorDeLosPistiliodiosID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Fruit Mesocarp")) {
                        especimenDTO.setColorDelMesocarpioID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Fruit Excarp")) {
                        especimenDTO.setColorDelExocarpioID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Fruit Inmature Excarp")) {
                        especimenDTO.setColorDelExocarpioInmaduroID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Fruit Inmature Mesocarp")) {
                        especimenDTO.setColorDelMesocarpioInmaduroID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Inflorescence Flower")) {
                        especimenDTO.setColorDeLaInflorescenciaEnFlorID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Inflorescence Fruit")) {
                        especimenDTO.setColorDeLaInflorescenciaEnFrutoID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Leaves Sheath")) {
                        especimenDTO.setColorDelConoID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Leaves Petiole")) {
                        especimenDTO.setColorDelPecioloID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Root Cone")) {
                        especimenDTO.setColorDelConoID(colorEspecimenId);
                    } else if (organoDeLaPlanta.contains("Stem")) {
                        especimenDTO.setColorDelTalloID(colorEspecimenId);
                    }
                }
                coloresFullTextSearch = coloresFullTextSearch.concat(colorEspecimen.getNombre().concat(" ").concat(colorEspecimen.getColorName() != null ? colorEspecimen.getColorName() : ""));
            }
        }
        return coloresFullTextSearch;
    }

    private Long actualizarHoja(EspecimenDTO especimenDTO) {
        Long hojaId = especimenDTO.getHojaId();
        if (hojaId == null){
            if (especimenDTO.getHojaDescripcion() != null
                    || especimenDTO.getCoberturaDelPeciolo() != null
                    || (especimenDTO.getColorDeLaVainaID() != null
                        && especimenDTO.getColorDeLaVainaID() > 0l)
                    || (especimenDTO.getColorDelPecioloID() != null
                        && especimenDTO.getColorDelPecioloID() > 0l)
                    || especimenDTO.getDispocicionDeLasPinnas() != null
                    || especimenDTO.getDisposicionDeLasHojas() != null
                    || especimenDTO.getFormaDelPeciolo() != null
                    || especimenDTO.getLonguitudDelRaquis() != null
                    || especimenDTO.getNaturalezaDeLaVaina() != null
                    || especimenDTO.getNaturalezaDelLimbo() != null
                    || especimenDTO.getNumeroDePinnas() != null
                    || especimenDTO.getNumeroHojas() != null
                    || especimenDTO.getTamañoDeLaVaina() != null
                    || especimenDTO.getTamañoDelPeciolo() != null) {
                Hoja hoja = new Hoja();
                hoja.setCoberturaDelPeciolo(especimenDTO.getCoberturaDelPeciolo());
                hoja.setColorDeLaVainaID(especimenDTO.getColorDeLaVainaID());
                hoja.setColorDelPecioloID(especimenDTO.getColorDelPecioloID());
                hoja.setDispocicionDeLasPinnas(especimenDTO.getDispocicionDeLasPinnas());
                hoja.setDisposicionDeLasHojas(especimenDTO.getDisposicionDeLasHojas());
                hoja.setFormaDelPeciolo(especimenDTO.getFormaDelPeciolo());
                hoja.setLonguitudDelRaquis(especimenDTO.getLonguitudDelRaquis());
                hoja.setNaturalezaDeLaVaina(especimenDTO.getNaturalezaDeLaVaina());
                hoja.setNaturalezaDelLimbo(especimenDTO.getNaturalezaDelLimbo());
                hoja.setNumeroDePinnas(especimenDTO.getNumeroDePinnas());
                hoja.setNumeroHojas(especimenDTO.getNumeroHojas());
                hoja.setTamañoDeLaVaina(especimenDTO.getTamañoDeLaVaina());
                hoja.setTamañoDelPeciolo(especimenDTO.getTamañoDelPeciolo());
                hoja.setDescripcion(especimenDTO.getHojaDescripcion());
                hojaId = daoSession.getHojaDao().insert(hoja);
            }
        }else{
            Hoja hoja = daoSession.getHojaDao().load(especimenDTO.getHojaId());
            hoja.setCoberturaDelPeciolo(especimenDTO.getCoberturaDelPeciolo());
            hoja.setColorDeLaVainaID(especimenDTO.getColorDeLaVainaID());
            hoja.setColorDelPecioloID(especimenDTO.getColorDelPecioloID());
            hoja.setDispocicionDeLasPinnas(especimenDTO.getDispocicionDeLasPinnas());
            hoja.setDisposicionDeLasHojas(especimenDTO.getDisposicionDeLasHojas());
            hoja.setFormaDelPeciolo(especimenDTO.getFormaDelPeciolo());
            hoja.setLonguitudDelRaquis(especimenDTO.getLonguitudDelRaquis());
            hoja.setNaturalezaDeLaVaina(especimenDTO.getNaturalezaDeLaVaina());
            hoja.setNaturalezaDelLimbo(especimenDTO.getNaturalezaDelLimbo());
            hoja.setNumeroDePinnas(especimenDTO.getNumeroDePinnas());
            hoja.setNumeroHojas(especimenDTO.getNumeroHojas());
            hoja.setTamañoDeLaVaina(especimenDTO.getTamañoDeLaVaina());
            hoja.setTamañoDelPeciolo(especimenDTO.getTamañoDelPeciolo());
            hoja.setDescripcion(especimenDTO.getHojaDescripcion());
            daoSession.getHojaDao().update(hoja);
        }
        return hojaId;
    }

    private Long actualizarFruto(EspecimenDTO especimenDTO) {
        Long frutoId = especimenDTO.getFrutoId();
        if (frutoId == null){
            if (especimenDTO.getFrutoDescripcion() != null
                    || (especimenDTO.getColorDelExocarpioID() != null
                        && especimenDTO.getColorDelExocarpioID() > 0l)
                    || (especimenDTO.getColorDelMesocarpioID() != null
                        && especimenDTO.getColorDelMesocarpioID() > 0l)
                    || (especimenDTO.getColorDelExocarpioInmaduroID() != null
                        && especimenDTO.getColorDelExocarpioInmaduroID() > 0l)
                    || (especimenDTO.getColorDelMesocarpioInmaduroID() != null
                        && especimenDTO.getColorDelMesocarpioInmaduroID() > 0l)
                    || especimenDTO.getConsistenciaDelPericarpio() != null) {
                Fruto fruto = new Fruto();
                fruto.setColorDelExocarpioID(especimenDTO.getColorDelExocarpioID());
                fruto.setColorDelMesocarpioID(especimenDTO.getColorDelMesocarpioID());
                fruto.setColorDelExocarpioInmaduroID(especimenDTO.getColorDelExocarpioInmaduroID());
                fruto.setColorDelMesocarpioInmaduroID(especimenDTO.getColorDelMesocarpioInmaduroID());
                fruto.setConsistenciaDelPericarpio(especimenDTO.getConsistenciaDelPericarpio());
                fruto.setDescripcion(especimenDTO.getFrutoDescripcion());
                frutoId = daoSession.getFrutoDao().insert(fruto);
            }
        }else{
            Fruto fruto = daoSession.getFrutoDao().load(especimenDTO.getFrutoId());
            fruto.setColorDelExocarpioID(especimenDTO.getColorDelExocarpioID());
            fruto.setColorDelMesocarpioID(especimenDTO.getColorDelMesocarpioID());
            fruto.setColorDelExocarpioInmaduroID(especimenDTO.getColorDelExocarpioInmaduroID());
            fruto.setColorDelMesocarpioInmaduroID(especimenDTO.getColorDelMesocarpioInmaduroID());
            fruto.setConsistenciaDelPericarpio(especimenDTO.getConsistenciaDelPericarpio());
            fruto.setDescripcion(especimenDTO.getFrutoDescripcion());
            daoSession.getFrutoDao().update(fruto);
        }
        return frutoId;
    }

    private Long actualizarRaiz(EspecimenDTO especimenDTO) {
        Long raizId = especimenDTO.getRaizId();
        if (raizId == null){
            if (especimenDTO.getRaizDescripcion() != null
                    || especimenDTO.getDiametroDeLasRaices() != null
                    || especimenDTO.getDiametroEnLaBase() != null
                    || especimenDTO.getFormaDeLasEspinas() != null
                    || especimenDTO.getTamañoDeLasEspinas() != null
                    || especimenDTO.getRaizArmada() != null
                    || especimenDTO.getAlturaDelCono() != null
                    || (especimenDTO.getColorDelConoID() != null
                        && especimenDTO.getColorDelConoID() > 0l)) {
                Raiz raiz = new Raiz();
                raiz.setDiametroDeLasRaices(especimenDTO.getDiametroDeLasRaices());
                raiz.setDiametroEnLaBase(especimenDTO.getDiametroEnLaBase());
                raiz.setFormaDeLasEspinas(especimenDTO.getFormaDeLasEspinas());
                raiz.setTamañoDeLasEspinas(especimenDTO.getTamañoDeLasEspinas());
                raiz.setDescripcion(especimenDTO.getRaizDescripcion());
                raiz.setRaizArmada(especimenDTO.getRaizArmada());
                raiz.setAlturaDelCono(especimenDTO.getAlturaDelCono());
                raiz.setColorDelConoID(especimenDTO.getColorDelConoID());
                raizId = daoSession.getRaizDao().insert(raiz);
            }
        }else{
            Raiz raiz = daoSession.getRaizDao().load(especimenDTO.getRaizId());
            raiz.setDiametroDeLasRaices(especimenDTO.getDiametroDeLasRaices());
            raiz.setDiametroEnLaBase(especimenDTO.getDiametroEnLaBase());
            raiz.setFormaDeLasEspinas(especimenDTO.getFormaDeLasEspinas());
            raiz.setTamañoDeLasEspinas(especimenDTO.getTamañoDeLasEspinas());
            raiz.setDescripcion(especimenDTO.getRaizDescripcion());
            raiz.setRaizArmada(especimenDTO.getRaizArmada());
            raiz.setAlturaDelCono(especimenDTO.getAlturaDelCono());
            raiz.setColorDelConoID(especimenDTO.getColorDelConoID());
            daoSession.getRaizDao().update(raiz);
        }
        return raizId;
    }

    private Long actualizarTallo(EspecimenDTO especimenDTO) {
        Long talloId = especimenDTO.getTalloId();
        if (talloId == null){
            if (especimenDTO.getTalloDescripcion() != null
                    || especimenDTO.getAlturaDelTallo() != null
                    || (especimenDTO.getColorDelTalloID() != null
                        && especimenDTO.getColorDelTalloID() > 0l)
                    || especimenDTO.getDiametroDelTallo() != null
                    || especimenDTO.getDisposicionDeLasEspinas() != null
                    || especimenDTO.getFormaDelTallo() != null
                    || especimenDTO.getLongitudEntrenudos() != null
                    || especimenDTO.getNaturalezaDelTallo() != null
                    || especimenDTO.getDesnudoCubierto() != null
                    || especimenDTO.getEntrenudosConspicuos() != null
                    || especimenDTO.getEspinas() != null) {
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
            }
        }else{
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
        }
        return talloId;
    }

    private Long actualizarInflorescencia(EspecimenDTO especimenDTO) {
        Long inflorescenciaId = especimenDTO.getInflorescenciaId();
        if (inflorescenciaId == null) {
            if (especimenDTO.getInflorescenciaDescripcion() != null
                    || (especimenDTO.getColorDeLaInflorescenciaEnFlorID() != null
                        && especimenDTO.getColorDeLaInflorescenciaEnFlorID() > 0l)
                    || (especimenDTO.getColorDeLaInflorescenciaEnFrutoID() != null
                        && especimenDTO.getColorDeLaInflorescenciaEnFrutoID() > 0l)
                    || especimenDTO.getNaturalezaDeLasBracteasPedunculares() != null
                    || especimenDTO.getNaturalezaDelProfilo() != null
                    || especimenDTO.getPosicionDeLasBracteasPedunculares() != null
                    || especimenDTO.getPosicionDeLasInflorescencias() != null
                    || especimenDTO.getRaquilas() != null
                    || especimenDTO.getRaquis() != null
                    || especimenDTO.getTamañoDeLasBracteasPedunculares() != null
                    || especimenDTO.getTamañoDelPedunculo() != null
                    || especimenDTO.getTamañoDelProfilo() != null
                    || especimenDTO.getTamañoDelRaquis() != null
                    || especimenDTO.getTamañoDeRaquilas() != null
                    || especimenDTO.getInflorescenciaSolitaria() != null
                    || especimenDTO.getNumeroDeLasBracteasPedunculares() != null
                    || especimenDTO.getNumeroDeRaquilas() != null) {
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
            }
        }else{
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
        }
        return inflorescenciaId;
    }

    private Long actualizarFlor(EspecimenDTO especimenDTO) {
        Long florId = especimenDTO.getFlorId();
        if (florId == null){
            if (especimenDTO.getFlorDescripcion() != null
                    || (especimenDTO.getColorDeLaCorolaID() != null
                    && especimenDTO.getColorDeLaCorolaID() > 0l)
                    || (especimenDTO.getColorDelCalizID() != null
                    && especimenDTO.getColorDelCalizID() > 0l)
                    || (especimenDTO.getColorDelGineceoID() != null
                    && especimenDTO.getColorDelGineceoID() > 0l)
                    || (especimenDTO.getColorDeLosEstambresID() != null
                    && especimenDTO.getColorDeLosEstambresID() > 0l)
                    || (especimenDTO.getColorDeLosEstigmasID() != null
                    && especimenDTO.getColorDeLosEstigmasID() > 0l)
                    || (especimenDTO.getColorDeLosPistiliodiosID() != null
                    && especimenDTO.getColorDeLosPistiliodiosID() > 0l)) {
                Flor flor = new Flor();
                flor.setDescripcion(especimenDTO.getFlorDescripcion());
                flor.setColorDeLaCorolaID(especimenDTO.getColorDeLaCorolaID());
                flor.setColorDelCalizID(especimenDTO.getColorDelCalizID());
                flor.setColorDelGineceoID(especimenDTO.getColorDelGineceoID());
                flor.setColorDeLosEstambresID(especimenDTO.getColorDeLosEstambresID());
                flor.setColorDeLosEstigmasID(especimenDTO.getColorDeLosEstigmasID());
                flor.setColorDeLosPistiliodiosID(especimenDTO.getColorDeLosPistiliodiosID());
                florId = daoSession.getFlorDao().insert(flor);
            }
        }else{
            Flor flor = daoSession.getFlorDao().load(especimenDTO.getFlorId());
            flor.setColorDeLaCorolaID(especimenDTO.getColorDeLaCorolaID());
            flor.setColorDelCalizID(especimenDTO.getColorDelCalizID());
            flor.setColorDelGineceoID(especimenDTO.getColorDelGineceoID());
            flor.setColorDeLosEstambresID(especimenDTO.getColorDeLosEstambresID());
            flor.setColorDeLosEstigmasID(especimenDTO.getColorDeLosEstigmasID());
            flor.setColorDeLosPistiliodiosID(especimenDTO.getColorDeLosPistiliodiosID());
            daoSession.getFlorDao().update(flor);
        }
        return florId;
    }

    private IdentidadTaxonomica actualizarIdentidadTaxonomica(EspecimenDTO especimenDTO) {
        IdentidadTaxonomica identidadTaxonomica = null;
        if (especimenDTO.getTaxon() != null) {
            Long taxonId = especimenDTO.getTaxon().getId();
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
                    taxonId = daoSession.getTaxonDao().insert(familia);
                }
                if (genero == null) {
                    genero = new Genero(especimenDTO.getTaxon().getGenero());
                    genero.setUsuarioId(especimenDTO.getUsuarioId());
                    genero.setTaxonPadre(familia);
                    taxonId = daoSession.getTaxonDao().insert(genero);
                }
                if (especimenDTO.getTaxon().getEspecie() != null) {
                    Taxon taxon = new EpitetoEspecifico(especimenDTO.getTaxon().getEspecie());
                    taxon.setUsuarioId(especimenDTO.getUsuarioId());
                    taxon.setTaxonPadre(genero);
                    taxonId = daoSession.getTaxonDao().insert(taxon);
                }
            }
            identidadTaxonomica = new IdentidadTaxonomica();
            identidadTaxonomica.setFechaIdentificacion(new Date(System.currentTimeMillis()));
            identidadTaxonomica.setTaxonID(taxonId);
            identidadTaxonomica.setDeterminador(colectorPrincipal.getPersona());
            identidadTaxonomica.setTipo(especimenDTO.getTipo());
        }
        return identidadTaxonomica;
    }

    private Long actualizarFenologia(EspecimenDTO especimenDTO) {
        Long fenologiaId = especimenDTO.getFenologia() != null ? especimenDTO.getFenologia().getId() : null;
        if (especimenDTO.getFenologia() != null) {
            if (fenologiaId == null) {
                fenologiaId = daoSession.getFenologiaDao().insert(especimenDTO.getFenologia());
            } else {
                Fenologia fenologia = daoSession.getFenologiaDao().load(fenologiaId);
                if (!fenologia.equals(especimenDTO.getFenologia())) {
                    fenologia.setFenologia(especimenDTO.getFenologia().getFenologia());
                    daoSession.getFenologiaDao().update(fenologia);
                }
            }
        }
        return fenologiaId;
    }

    private Long actualizarHabito(EspecimenDTO especimenDTO) {
        Long habitoId = especimenDTO.getHabito() != null ? especimenDTO.getHabito().getId() : null;
        if (especimenDTO.getHabito() != null) {
            if (habitoId == null) {
                habitoId = daoSession.getHabitoDao().insert(especimenDTO.getHabito());
            } else {
                Habito habito = daoSession.getHabitoDao().load(habitoId);
                if (!habito.equals(especimenDTO.getHabito())) {
                    habito.setHabito(especimenDTO.getHabito().getHabito());
                    daoSession.getHabitoDao().update(habito);
                    daoSession.getHabitoDao().update(habito);
                }
            }
        }
        return habitoId;
    }

    private Long actualizarHabitat(EspecimenDTO especimenDTO) {
        Long habitatId = especimenDTO.getHabitat() != null ? especimenDTO.getHabitat().getId() : null;
        if (especimenDTO.getHabitat() != null) {
            if (habitatId == null) {
                habitatId = daoSession.getHabitatDao().insert(especimenDTO.getHabitat());
            } else {
                Habitat habitat = daoSession.getHabitatDao().load(habitatId);
                if (!habitat.equals(especimenDTO.getHabitat())) {
                    habitat.setEspeciesAsociadas(especimenDTO.getHabitat().getEspeciesAsociadas());
                    habitat.setSueloSustrato(especimenDTO.getHabitat().getSueloSustrato());
                    habitat.setVegetacion(especimenDTO.getHabitat().getVegetacion());
                    daoSession.getHabitatDao().update(habitat);
                }
            }
        }
        return habitatId;
    }

    private Long actualizarLocalidad(final EspecimenDTO especimenDTO) {
        Long localidadId = especimenDTO.getLocalidadId();
        Long regionId = especimenDTO.getRegion() != null ? especimenDTO.getRegion().getId() : null;
        if (especimenDTO.getRegion() != null) {
            if (regionId == null) {
                Region pais = null;
                if (especimenDTO.getRegion().getPais() != null) {
                    pais = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Pais.eq(especimenDTO.getRegion().getPais())).where(RegionDao.Properties.Rango.eq("pais")).unique();
                }
                Region departamento = null;
                if (especimenDTO.getRegion().getDepartamento() != null) {
                    departamento = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Departamento.eq(especimenDTO.getRegion().getDepartamento())).where(RegionDao.Properties.Rango.eq("departamento")).unique();
                }else{
                    especimenDTO.getRegion().setMunicipio(null);
                }
                if (pais == null) {
                    pais = new Pais(especimenDTO.getRegion().getPais());
                    pais.setUsuarioId(especimenDTO.getUsuarioId());
                    regionId = daoSession.getRegionDao().insert(pais);
                }
                if (departamento == null) {
                    departamento = new Departamento(especimenDTO.getRegion().getDepartamento());
                    departamento.setUsuarioId(especimenDTO.getUsuarioId());
                    departamento.setRegionPadre(pais);
                    regionId = daoSession.getRegionDao().insert(departamento);
                }
                if (especimenDTO.getRegion().getMunicipio() != null) {
                    Municipio municipio = (Municipio) daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Municipio.eq(especimenDTO.getRegion().getMunicipio())).where(RegionDao.Properties.Rango.eq("municipio")).unique();
                    if (municipio != null && municipio.getRegionPadre().getNombre().equals(departamento.getNombre()) && municipio.getRegionPadre().getRegionPadre().getNombre().equals(pais.getNombre())) {
                        regionId = municipio.getId();
                    }else{
                        Region region = new Municipio(especimenDTO.getRegion().getMunicipio());
                        region.setUsuarioId(especimenDTO.getUsuarioId());
                        region.setRegionPadre(departamento);
                        regionId = daoSession.getRegionDao().insert(region);
                    }
                }
            }
        }
        Localidad localidad;
        if (localidadId == null) {
            if (especimenDTO.getLocalidadNombre() != null || regionId != null
                    || especimenDTO.getLatitud() != null || especimenDTO.getLongitud() != null
                    || especimenDTO.getAltitudMinima() != null || especimenDTO.getAltitudMaxima() != null) {
                localidad = new Localidad(especimenDTO.getLocalidadNombre());
                localidad.setLatitud(especimenDTO.getLatitud());
                localidad.setLongitud(especimenDTO.getLongitud());
                localidad.setAltitudMinima(especimenDTO.getAltitudMinima());
                localidad.setAltitudMaxima(especimenDTO.getAltitudMaxima());
                localidad.setDatum(especimenDTO.getDatum());
                localidad.setMarcaDispositivo(especimenDTO.getMarcaDispositivo());
                localidad.setDescripcion(especimenDTO.getLocalidadDescripcion());
                if (regionId != null) {
                    localidad.setRegionID(regionId);
                }
                localidadId = daoSession.getLocalidadDao().insert(localidad);
            }
        } else {
            localidad = daoSession.getLocalidadDao().load(localidadId);
            localidad.setLatitud(especimenDTO.getLatitud());
            localidad.setLongitud(especimenDTO.getLongitud());
            localidad.setAltitudMinima(especimenDTO.getAltitudMinima());
            localidad.setAltitudMaxima(especimenDTO.getAltitudMaxima());
            localidad.setDatum(especimenDTO.getDatum());
            localidad.setMarcaDispositivo(especimenDTO.getMarcaDispositivo());
            localidad.setDescripcion(especimenDTO.getLocalidadDescripcion());
            if (regionId != null) {
                localidad.setRegionID(regionId);
            }
            daoSession.getLocalidadDao().update(localidad);
        }
        return localidadId;
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
