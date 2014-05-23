package edu.udistrital.plantae.logicadominio.recoleccion;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.persistencia.*;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:13 a.m.
 */
public class ColectorPrincipal extends Persona {

	private Long id;
    private String numeroColeccionActual;
    private int tipoCapturaDatos;
    private List<Viaje> viajes;
    private List<Proyecto> proyectos;
    private List<Recoleccion> recolecciones;
    private long personaID;

	private Persona persona;
    private Long persona__resolvedKey;

	/* greendao specific properties */
	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient ColectorPrincipalDao myDao;

	public void finalize() throws Throwable {
		super.finalize();
	}

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getColectorPrincipalDao() : null;
    }

	public ColectorPrincipal() {
	}

	/**
	 *
	 * @param usuario
	 * @param contraseña
	 */
	public ColectorPrincipal(String usuario, String contraseña){
		super();
		persona = new Persona();
		persona.setUsuario(Usuario.getUsuario(usuario, contraseña));
	}

    @Override
    public Long getId(){
        return id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(Long id){
        this.id = id;
    }

	public List<Viaje> getListaViajes(){
		return viajes;
	}

	public List<Proyecto> getListaProyectos(){
		return proyectos;
	}

    public String getNumeroColeccionActual() {
		return numeroColeccionActual;
	}

    public void setNumeroColeccionActual(String numeroColeccionActual) {
        this.numeroColeccionActual = numeroColeccionActual;
	}

	public int getTipoCapturaDatos(){
		return tipoCapturaDatos;
	}

    public void setTipoCapturaDatos(Integer tipoCapturaDatos) {
        this.tipoCapturaDatos = tipoCapturaDatos;
	}

    public long getPersonaID() {
        return personaID;
    }

    public void setPersonaID(long personaID) {
        this.personaID = personaID;
    }

    /** To-one relationship, resolved on first access. */
	public Persona getPersona() {
        long __key = this.personaID;
        if (persona__resolvedKey == null || !persona__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonaDao targetDao = daoSession.getPersonaDao();
            Persona personaNew = targetDao.load(__key);
            synchronized (this) {
                persona = personaNew;
            	persona__resolvedKey = __key;
            }
        }
		return persona;
	}

	public void setPersona(Persona persona) {
        if (persona == null) {
            throw new DaoException("To-one property 'personaID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
		    this.persona = persona;
            personaID = persona.getId();
            persona__resolvedKey = personaID;
	    }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Proyecto> getProyectos() {
        if (proyectos == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProyectoDao targetDao = daoSession.getProyectoDao();
            List<Proyecto> proyectosNew = targetDao._queryColectorPrincipal_Proyectos(id);
            synchronized (this) {
                if(proyectos == null) {
                    proyectos = proyectosNew;
                }
            }
        }
        return proyectos;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Viaje> getViajes() {
        if (viajes == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ViajeDao targetDao = daoSession.getViajeDao();
            List<Viaje> viajesNew = targetDao._queryColectorPrincipal_Viajes(id);
            synchronized (this) {
                if(viajes == null) {
                    viajes = viajesNew;
                }
            }
        }
        return viajes;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Recoleccion> getRecolecciones() {
        if (recolecciones == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RecoleccionDao targetDao = daoSession.getRecoleccionDao();
            List<Recoleccion> recoleccionesNew = targetDao._queryColectorPrincipal_Recolecciones(id);
            synchronized (this) {
                if(recolecciones == null) {
                    recolecciones = recoleccionesNew;
                }
            }
        }
        return recolecciones;
    }

}