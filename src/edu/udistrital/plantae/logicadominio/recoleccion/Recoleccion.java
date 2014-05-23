package edu.udistrital.plantae.logicadominio.recoleccion;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.ubicacion.Localidad;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;
import edu.udistrital.plantae.persistencia.ColectorPrincipalDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.EspecimenDao;
import edu.udistrital.plantae.persistencia.RecoleccionDao;

import java.util.Date;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 25-Jun-2013 11:54:36 PM
 */
public class Recoleccion {

    private Long id;
    private Date fechaInicial;
    private Date fechaFinal;
    private String metodoColeccion;
    private String estacionDelAño;
	private Especimen especimen;
	private ColectorPrincipal colectorPrincipal;
    private long viajeID;
    private long colectorPrincipalID;
    private Long especimenID;

    private Long colectorPrincipal__resolvedKey;
    private Long especimen__resolvedKey;

	/* greendao specific properties */
	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient RecoleccionDao myDao;

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
    public void setFechaFinal(java.util.Date fechaFinal) {
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

    public Long getEspecimenID() {
        return especimenID;
    }

    public void setEspecimenID(Long especimenID) {
        this.especimenID = especimenID;
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
    public Especimen getEspecimen() {
        Long __key = this.especimenID;
        if (especimen__resolvedKey == null || !especimen__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EspecimenDao targetDao = daoSession.getEspecimenDao();
            Especimen especimenNew = targetDao.load(__key);
            synchronized (this) {
                especimen = especimenNew;
            	especimen__resolvedKey = __key;
            }
        }
        return especimen;
    }

    public void setEspecimen(Especimen especimen) {
        synchronized (this) {
            this.especimen = especimen;
            especimenID = especimen == null ? null : especimen.getId();
            especimen__resolvedKey = especimenID;
        }
    }

	public void finalize() throws Throwable {

	}

	public Recoleccion(){

	}

	/**
	 *
	 * @param nombre
	 * @param region
	 */
	public Localidad registrarUbicacionGeografica(String nombre, Region region){
		return null;
	}

	/** called by internal mechanisms, do not call yourself. */
	public void __setDaoSession(DaoSession daoSession) {
		this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRecoleccionDao() : null;
	}

}