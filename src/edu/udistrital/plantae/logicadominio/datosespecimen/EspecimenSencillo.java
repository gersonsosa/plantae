package edu.udistrital.plantae.logicadominio.datosespecimen;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.logicadominio.recoleccion.Recoleccion;
import edu.udistrital.plantae.logicadominio.taxonomia.IdentidadTaxonomica;
import edu.udistrital.plantae.logicadominio.ubicacion.Localidad;
import edu.udistrital.plantae.persistencia.*;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class EspecimenSencillo implements Especimen {

    private Long id;
    private String numeroDeColeccion;
    private long alturaDeLaPlanta;
    private long dap;
    private String abundancia;
    private String fenologia;
    private String descripcionEspecimen;
    private Habito habito;
    private Habitat habitat;
    private Localidad localidad;
    private Flor flor;
    private Etiqueta etiqueta;
    private ColectorPrincipal colectorPrincipal;
	private Recoleccion recoleccion;
	private List<ColectorSecundario> colectoresSecundarios;
	private List<MuestraAsociada> muestrasAsociadas;
	private List<Fotografia> fotografias;
    private List<IdentidadTaxonomica> determinaciones;

    private long recoleccionID;
    private Long identidadTaxonomicaID;
    private Long habitoID;
    private Long habitatID;
    private Long localidadID;
    private Long florID;

	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient EspecimenDao myDao;

    private Long habito__resolvedKey;
    private Long habitat__resolvedKey;
    private Long localidad__resolvedKey;
    private Long flor__resolvedKey;
    private Long recoleccion__resolvedKey;

	public EspecimenSencillo(){
	}

	public void finalize() throws Throwable {
	}

    /**
     *
     * @param numeroDeColector
     */
    public EspecimenSencillo(String numeroDeColector){
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEspecimenDao() : null;
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

	public String generarNumeroDeColector(){
		return "";
	}

    public void agregarMuestraAsociada(){

    }

    public void agregarTodosColectores(){

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

    public long getRecoleccionID() {
        return recoleccionID;
    }

    public void setRecoleccionID(long recoleccionID) {
        this.recoleccionID = recoleccionID;
    }

    public Long getIdentidadTaxonomicaID() {
        return identidadTaxonomicaID;
    }

    public void setIdentidadTaxonomicaID(Long identidadTaxonomicaID) {
        this.identidadTaxonomicaID = identidadTaxonomicaID;
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

    public Long getFlorID() {
        return florID;
    }

    public void setFlorID(Long florID) {
        this.florID = florID;
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

	public Object clone(){
		return null;
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

	public String generarNumeroDeColeccion(){
		return "";
	}

	/**
	 *
	 * @param colectorSecundario
	 */
	public void quitarColector(ColectorSecundario colectorSecundario){

	}

	/**
	 *
	 * @param muestra
	 */
	public void agregarMuestraAsociada(MuestraAsociada muestra){

	}

	public Etiqueta getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(Etiqueta etiqueta) {
		this.etiqueta = etiqueta;
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

    /** To-one relationship, resolved on first access. */
	public Recoleccion getRecoleccion() {
        long __key = this.recoleccionID;
        if (recoleccion__resolvedKey == null || !recoleccion__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RecoleccionDao targetDao = daoSession.getRecoleccionDao();
            Recoleccion recoleccionNew = targetDao.load(__key);
            synchronized (this) {
                recoleccion = recoleccionNew;
            	recoleccion__resolvedKey = __key;
            }
        }
		return recoleccion;
	}

	public void setRecoleccion(Recoleccion recoleccion) {
        if (recoleccion == null) {
            throw new DaoException("To-one property 'recoleccionID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
		    this.recoleccion = recoleccion;
            recoleccionID = recoleccion.getId();
            recoleccion__resolvedKey = recoleccionID;
	    }
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

	public void setMuestraAsociada(List<MuestraAsociada> muestrasAsociadas) {
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

	/**
	 * 
	 * @param fotografía
	 */
	public void agregarFotografia(Fotografia fotografía){

	}

}