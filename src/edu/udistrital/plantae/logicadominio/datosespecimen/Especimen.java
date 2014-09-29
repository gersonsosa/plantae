package edu.udistrital.plantae.logicadominio.datosespecimen;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.logicadominio.taxonomia.IdentidadTaxonomica;
import edu.udistrital.plantae.logicadominio.ubicacion.Localidad;
import edu.udistrital.plantae.persistencia.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Especimen {

    private Long id;
    private String numeroDeColeccion;
    private String abundancia;
    private String fenologia;
    private String descripcionEspecimen;
    private Long alturaDeLaPlanta;
    private Long dap;
    private Date fechaInicial;
    private Date fechaFinal;
    private String metodoColeccion;
    private String estacionDelAño;
    private Long habitoID;
    private Long habitatID;
    private Long localidadID;
    private long viajeID;
    private long colectorPrincipalID;
    private Long raizID;
    private Long talloID;
    private Long inflorescenciaID;
    private Long frutoID;
    private Long florID;
    private Long hojaID;
    private String tipo;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient EspecimenDao myDao;

    private Etiqueta etiqueta;
    private Habito habito;
    private Long habito__resolvedKey;

    private Habitat habitat;
    private Long habitat__resolvedKey;

    private Localidad localidad;
    private Long localidad__resolvedKey;

    private Inflorescencia inflorescencia;
    private Long inflorescencia__resolvedKey;

    private ColectorPrincipal colectorPrincipal;
    private Long colectorPrincipal__resolvedKey;

    private Viaje viaje;
    private Long viaje__resolvedKey;

    private Hoja hoja;
    private Long hoja__resolvedKey;

    private Fruto fruto;
    private Long fruto__resolvedKey;

    private Tallo tallo;
    private Long tallo__resolvedKey;

    private Raiz raiz;
    private Long raiz__resolvedKey;

    private Flor flor;
    private Long flor__resolvedKey;

	private List<ColectorSecundario> colectoresSecundarios;
    private List<MuestraAsociada> muestrasAsociadas;
	private List<Fotografia> fotografias;
    private List<IdentidadTaxonomica> determinaciones;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEspecimenDao() : null;
    }

	public Especimen(){
        colectoresSecundarios = new ArrayList<ColectorSecundario>();
	}

	public void finalize() throws Throwable {
	}

	/**
	 * 
	 * @param numeroDeColector
	 */
	protected Especimen(String numeroDeColector, long viajeID, long colectorPrincipalID){
        this.numeroDeColeccion = numeroDeColector;
        this.viajeID = viajeID;
        this.colectorPrincipalID = colectorPrincipalID;
        colectoresSecundarios = new ArrayList<ColectorSecundario>();
	}

    /**
     * Agrega todos los colectores secundarios del viaje al espécimen actual
     */
	public void agregarTodosColectores(){
        colectoresSecundarios.addAll(daoSession.load(Viaje.class, getViajeID()).getColectoresSecundarios());
	}

    public Object clone(){
        return null;
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

    public String getNumeroDeColeccion() {
        return numeroDeColeccion;
    }

    public void setNumeroDeColeccion(String numeroDeColeccion) {
        this.numeroDeColeccion = numeroDeColeccion;
    }

    public String getAbundancia() {
        return abundancia;
    }

    public void setAbundancia(String abundancia) {
        this.abundancia = abundancia;
    }

    public String getFenologia() {
        return fenologia;
    }

    public void setFenologia(String fenologia) {
        this.fenologia = fenologia;
    }

    public String getDescripcionEspecimen() {
        return descripcionEspecimen;
    }

    public void setDescripcionEspecimen(String descripcionEspecimen) {
        this.descripcionEspecimen = descripcionEspecimen;
    }

    public Long getAlturaDeLaPlanta() {
		return alturaDeLaPlanta;
	}

    public void setAlturaDeLaPlanta(Long alturaDeLaPlanta) {
        this.alturaDeLaPlanta = alturaDeLaPlanta;
	}

    public Long getDap() {
		return dap;
	}

    public void setDap(Long dap) {
        this.dap = dap;
	}

    public Date getFechaInicial(){
        return fechaInicial;
    }

    /**
     *
     * @param fechaInicial
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal(){
        return fechaFinal;
    }

    /**
     *
     * @param fechaFinal
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getMetodoColeccion() {
        return metodoColeccion;
    }

    /**
     *
     * @param metodoColeccion
     */
    public void setMetodoColeccion(String metodoColeccion) {
        this.metodoColeccion = metodoColeccion;
    }

    public String getEstacionDelAño() {
        return estacionDelAño;
    }

    /**
     *
     * @param estacionDelAño
     */
    public void setEstacionDelAño(String estacionDelAño) {
        this.estacionDelAño = estacionDelAño;
    }

    public Long getHabitoID() {
        return habitoID;
    }

    public void setHabitoID(Long habitoID) {
        this.habitoID = habitoID;
    }

    public Long getHabitatID() {
        return habitatID;
    }

    public void setHabitatID(Long habitatID) {
        this.habitatID = habitatID;
    }

    public Long getLocalidadID() {
        return localidadID;
    }

    public void setLocalidadID(Long localidadID) {
        this.localidadID = localidadID;
    }

    public long getViajeID() {
        return viajeID;
    }

    public void setViajeID(long viajeID) {
        this.viajeID = viajeID;
    }

    public long getColectorPrincipalID() {
        return colectorPrincipalID;
    }

    public void setColectorPrincipalID(long colectorPrincipalID) {
        this.colectorPrincipalID = colectorPrincipalID;
    }

    public Long getRaizID() {
        return raizID;
    }

    public void setRaizID(Long raizID) {
        this.raizID = raizID;
    }

    public Long getTalloID() {
        return talloID;
    }

    public void setTalloID(Long talloID) {
        this.talloID = talloID;
    }

    public Long getInflorescenciaID() {
        return inflorescenciaID;
    }

    public void setInflorescenciaID(Long inflorescenciaID) {
        this.inflorescenciaID = inflorescenciaID;
    }

    public Long getFrutoID() {
        return frutoID;
    }

    public void setFrutoID(Long frutoID) {
        this.frutoID = frutoID;
    }

    public Long getFlorID() {
        return florID;
    }

    public void setFlorID(Long florID) {
        this.florID = florID;
    }

    public Long getHojaID() {
        return hojaID;
    }

    public void setHojaID(Long hojaID) {
        this.hojaID = hojaID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /** To-one relationship, resolved on first access. */
    public Habito getHabito() {
        Long __key = this.habitoID;
        if (habito__resolvedKey == null || !habito__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HabitoDao targetDao = daoSession.getHabitoDao();
            Habito habitoNew = targetDao.load(__key);
            synchronized (this) {
                habito = habitoNew;
            	habito__resolvedKey = __key;
            }
        }
        return habito;
	}

	/**
	 *
	 * @param habito
	 */
    public void setHabito(Habito habito) {
        synchronized (this) {
            this.habito = habito;
            habitoID = habito == null ? null : habito.getId();
            habito__resolvedKey = habitoID;
        }
	}

    /** To-one relationship, resolved on first access. */
    public Habitat getHabitat() {
        Long __key = this.habitatID;
        if (habitat__resolvedKey == null || !habitat__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HabitatDao targetDao = daoSession.getHabitatDao();
            Habitat habitatNew = targetDao.load(__key);
            synchronized (this) {
                habitat = habitatNew;
            	habitat__resolvedKey = __key;
            }
        }
		return habitat;
	}

	/**
	 *
	 * @param habitat
	 */
    public void setHabitat(Habitat habitat) {
        synchronized (this) {
		    this.habitat = habitat;
            habitatID = habitat == null ? null : habitat.getId();
            habitat__resolvedKey = habitatID;
	    }
    }

    /** To-one relationship, resolved on first access. */
    public Localidad getLocalidad() {
        Long __key = this.localidadID;
        if (localidad__resolvedKey == null || !localidad__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LocalidadDao targetDao = daoSession.getLocalidadDao();
            Localidad localidadNew = targetDao.load(__key);
            synchronized (this) {
                localidad = localidadNew;
            	localidad__resolvedKey = __key;
            }
        }
		return localidad;
	}

	/**
	 *
	 * @param localidad
	 */
    public void setLocalidad(Localidad localidad) {
        synchronized (this) {
            this.localidad = localidad;
            localidadID = localidad == null ? null : localidad.getId();
            localidad__resolvedKey = localidadID;
        }
	}

    /** To-one relationship, resolved on first access. */
	public Inflorescencia getInflorescencia() {
        Long __key = this.inflorescenciaID;
        if (inflorescencia__resolvedKey == null || !inflorescencia__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            InflorescenciaDao targetDao = daoSession.getInflorescenciaDao();
            Inflorescencia inflorescenciaNew = targetDao.load(__key);
            synchronized (this) {
                inflorescencia = inflorescenciaNew;
            	inflorescencia__resolvedKey = __key;
            }
        }
        return inflorescencia;
	}

	public void setInflorescencia(Inflorescencia inflorescencia) {
        synchronized (this) {
		    this.inflorescencia = inflorescencia;
            inflorescenciaID = inflorescencia == null ? null : inflorescencia.getId();
            inflorescencia__resolvedKey = inflorescenciaID;
	    }
    }

    /** To-one relationship, resolved on first access. */
    public ColectorPrincipal getColectorPrincipal() {
        long __key = this.colectorPrincipalID;
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
        if (colectorPrincipal == null) {
            throw new DaoException("To-one property 'colectorPrincipalID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.colectorPrincipal = colectorPrincipal;
            colectorPrincipalID = colectorPrincipal.getId();
            colectorPrincipal__resolvedKey = colectorPrincipalID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Viaje getViaje() {
        long __key = this.viajeID;
        if (viaje__resolvedKey == null || !viaje__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ViajeDao targetDao = daoSession.getViajeDao();
            Viaje viajeNew = targetDao.load(__key);
            synchronized (this) {
                viaje = viajeNew;
            	viaje__resolvedKey = __key;
            }
        }
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        if (viaje == null) {
            throw new DaoException("To-one property 'viajeID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.viaje = viaje;
            viajeID = viaje.getId();
            viaje__resolvedKey = viajeID;
        }
    }

    /** To-one relationship, resolved on first access. */
	public Hoja getHoja() {
        Long __key = this.hojaID;
        if (hoja__resolvedKey == null || !hoja__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HojaDao targetDao = daoSession.getHojaDao();
            Hoja hojaNew = targetDao.load(__key);
            synchronized (this) {
                hoja = hojaNew;
            	hoja__resolvedKey = __key;
            }
        }
		return hoja;
	}

	public void setHoja(Hoja hoja) {
        synchronized (this) {
		    this.hoja = hoja;
            hojaID = hoja == null ? null : hoja.getId();
            hoja__resolvedKey = hojaID;
	    }
    }

    /** To-one relationship, resolved on first access. */
	public Fruto getFruto() {
        Long __key = this.frutoID;
        if (fruto__resolvedKey == null || !fruto__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FrutoDao targetDao = daoSession.getFrutoDao();
            Fruto frutoNew = targetDao.load(__key);
            synchronized (this) {
                fruto = frutoNew;
            	fruto__resolvedKey = __key;
            }
        }
		return fruto;
	}

	public void setFruto(Fruto fruto) {
        synchronized (this) {
		    this.fruto = fruto;
            frutoID = fruto == null ? null : fruto.getId();
            fruto__resolvedKey = frutoID;
	    }
    }

    /** To-one relationship, resolved on first access. */
	public Tallo getTallo() {
        Long __key = this.talloID;
        if (tallo__resolvedKey == null || !tallo__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TalloDao targetDao = daoSession.getTalloDao();
            Tallo talloNew = targetDao.load(__key);
            synchronized (this) {
                tallo = talloNew;
            	tallo__resolvedKey = __key;
            }
        }
		return tallo;
	}

	public void setTallo(Tallo tallo) {
        synchronized (this) {
		    this.tallo = tallo;
            talloID = tallo == null ? null : tallo.getId();
            tallo__resolvedKey = talloID;
	    }
    }

    /** To-one relationship, resolved on first access. */
	public Raiz getRaiz() {
        Long __key = this.raizID;
        if (raiz__resolvedKey == null || !raiz__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RaizDao targetDao = daoSession.getRaizDao();
            Raiz raizNew = targetDao.load(__key);
            synchronized (this) {
                raiz = raizNew;
            	raiz__resolvedKey = __key;
            }
        }
		return raiz;
	}

	public void setRaiz(Raiz raiz) {
        synchronized (this) {
		    this.raiz = raiz;
            raizID = raiz == null ? null : raiz.getId();
            raiz__resolvedKey = raizID;
	    }
    }

    /** To-one relationship, resolved on first access. */
	public Flor getFlor() {
        Long __key = this.florID;
        if (flor__resolvedKey == null || !flor__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FlorDao targetDao = daoSession.getFlorDao();
            Flor florNew = targetDao.load(__key);
            synchronized (this) {
                flor = florNew;
            	flor__resolvedKey = __key;
            }
        }
		return flor;
	}

	public void setFlor(Flor flor) {
        synchronized (this) {
            this.flor = flor;
            florID = flor == null ? null : flor.getId();
            flor__resolvedKey = florID;
        }
	}

	public Etiqueta getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(Etiqueta etiqueta) {
		this.etiqueta = etiqueta;
	}

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
	public List<ColectorSecundario> getColectoresSecundarios() {
        if (colectoresSecundarios == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColectorSecundarioDao targetDao = daoSession.getColectorSecundarioDao();
            List<ColectorSecundario> colectoresSecundariosNew = targetDao._queryEspecimen_ColectoresSecundarios(id);
            synchronized (this) {
                if(colectoresSecundarios == null) {
                    colectoresSecundarios = colectoresSecundariosNew;
                }
            }
        }
		return colectoresSecundarios;
	}

	public void setColectoresSecundarios(List<ColectorSecundario> colectoresSecundarios) {
		this.colectoresSecundarios = colectoresSecundarios;
	}

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<MuestraAsociada> getMuestrasAsociadas() {
        if (muestrasAsociadas == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MuestraAsociadaDao targetDao = daoSession.getMuestraAsociadaDao();
            List<MuestraAsociada> muestrasAsociadasNew = targetDao._queryEspecimen_MuestrasAsociadas(id);
            synchronized (this) {
                if(muestrasAsociadas == null) {
                    muestrasAsociadas = muestrasAsociadasNew;
                }
            }
        }
        return muestrasAsociadas;
	}

	public void setMuestrasAsociadas(List<MuestraAsociada> muestrasAsociadas) {
		this.muestrasAsociadas = muestrasAsociadas;
	}

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
	public List<Fotografia> getFotografias() {
        if (fotografias == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FotografiaDao targetDao = daoSession.getFotografiaDao();
            List<Fotografia> fotografiasNew = targetDao._queryEspecimen_Fotografias(id);
            synchronized (this) {
                if(fotografias == null) {
                    fotografias = fotografiasNew;
                }
            }
        }
		return fotografias;
	}

	public void setFotografias(List<Fotografia> fotografias) {
		this.fotografias = fotografias;
	}

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<IdentidadTaxonomica> getDeterminaciones() {
        if (determinaciones == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IdentidadTaxonomicaDao targetDao = daoSession.getIdentidadTaxonomicaDao();
            List<IdentidadTaxonomica> determinacionesNew = targetDao._queryEspecimen_Determinaciones(id);
            synchronized (this) {
                if(determinaciones == null) {
                    determinaciones = determinacionesNew;
                }
            }
        }
        return determinaciones;
    }

    public void setDeterminaciones(List<IdentidadTaxonomica> determinaciones) {
        this.determinaciones = determinaciones;
    }

    /**
     * Agrega una nueva muestra al espécimen
     * @param muestraAsociada La muestra a agregar
     */
	public void agregarMuestraAsociada(MuestraAsociada muestraAsociada){

	}

    /**
     * Crea un colector secundario nuevo con el apellido y nombre y lo agrega
     * a la lista de colectores secundarios del espécimen.
     * @<Nota>Este método solo se debe llamar luego de guardar el espécimen.</Nota>
     * @param nombre del nuevo colector secundario
     * @param apellido del nuevo colector secundario
     */
    public void agregarColector(String apellido, String nombre) {
        ColectorSecundario colectorSecundario = new ColectorSecundario();
        Persona colectorSecundarioPersona = new Persona(apellido, nombre);
        daoSession.getPersonaDao().insert(colectorSecundarioPersona);
        colectorSecundario.setPersona(colectorSecundarioPersona);
        colectoresSecundarios.add(colectorSecundario);
    }

    /**
     * Quita el colector secundario especificado del espécimen
     * @param colectorSecundario El colector secundario a quitar
     */
	public void quitarColector(ColectorSecundario colectorSecundario){

	}

	/**
	 * 
	 * @param fotografía
	 */
	public void agregarFotografia(Fotografia fotografía){

	}
}