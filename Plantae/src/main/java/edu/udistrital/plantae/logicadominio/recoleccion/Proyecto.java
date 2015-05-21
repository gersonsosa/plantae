package edu.udistrital.plantae.logicadominio.recoleccion;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.ProyectoDao;
import edu.udistrital.plantae.persistencia.ViajeDao;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class Proyecto {

    private Long id;
    private String nombre;
    private String agenciaFinanciera;
    private String agenciaEjecutora;
    private String numeroConvenio;
    private String permisoColeccion;
    private String numeroPermiso;
    private String emisorPermiso;
	private List<Viaje> viajes;
    private Long colectorPrincipalID;
	
	/* greendao specific properties */
	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient ProyectoDao myDao;

	public Proyecto(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 *
	 * @param nombre
	 */
	public Proyecto(String nombre){
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

    public String getAgenciaFinanciera() {
		return agenciaFinanciera;
	}

    public void setAgenciaFinanciera(String agenciaFinanciera) {
        this.agenciaFinanciera = agenciaFinanciera;
	}

    public String getAgenciaEjecutora() {
		return agenciaEjecutora;
	}

    public void setAgenciaEjecutora(String agenciaEjecutora) {
        this.agenciaEjecutora = agenciaEjecutora;
	}

    public String getNumeroConvenio() {
		return numeroConvenio;
	}

    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
	}

    public String getPermisoColeccion() {
		return permisoColeccion;
	}

    public void setPermisoColeccion(String permisoColeccion) {
        this.permisoColeccion = permisoColeccion;
	}

    public String getNumeroPermiso() {
		return numeroPermiso;
	}

    public void setNumeroPermiso(String numeroPermiso) {
        this.numeroPermiso = numeroPermiso;
	}

    public String getEmisorPermiso() {
		return emisorPermiso;
	}

    public void setEmisorPermiso(String emisorPermiso) {
        this.emisorPermiso = emisorPermiso;
	}

    public Long getColectorPrincipalID() {
        return colectorPrincipalID;
    }

    public void setColectorPrincipalID(Long colectorPrincipalID) {
        this.colectorPrincipalID = colectorPrincipalID;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Viaje> getViajes() {
        if (viajes == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ViajeDao targetDao = daoSession.getViajeDao();
            List<Viaje> viajesNew = targetDao._queryProyecto_Viajes(id);
            synchronized (this) {
                if(viajes == null) {
                    viajes = viajesNew;
                }
            }
        }
		return viajes;
	}

	/**
	 * 
	 * @param viajes
	 */
	public void setViajes(List<Viaje> viajes){
		this.viajes = viajes;
	}
	
	/** called by internal mechanisms, do not call yourself. */
	public void __setDaoSession(DaoSession daoSession) {
		this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getProyectoDao() : null;
	}

}
