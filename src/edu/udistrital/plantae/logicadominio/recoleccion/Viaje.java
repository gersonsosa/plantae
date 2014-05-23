package edu.udistrital.plantae.logicadominio.recoleccion;
import com.google.android.maps.MapView;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.FabricaEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.FabricaPrototipadoEspecimen;
import edu.udistrital.plantae.persistencia.*;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class Viaje {

    private Long id;
    private String nombre;
    private FabricaEspecimen fabricaEspecimen;
    private FabricaPrototipadoEspecimen fabricaPrototipadoEspecimen;
    private ColectorPrincipal colectorPrincipal;
    private Trayecto trayecto;
    private Proyecto proyecto;
    private List<Recoleccion> recolecciones;
	private List<ColectorSecundario> colectoresSecundarios;
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

	public void finalize() throws Throwable {

	}

	/**
	 *
	 * @param colectores
	 * @param proyecto
	 * @param nombre    nombre
	 */
	public Viaje(ColectorSecundario[] colectores, Proyecto proyecto, String nombre){

	}

	/**
	 *
	 * @param proyecto
	 * @param nombre    nombre
	 */
	public Viaje(Proyecto proyecto, String nombre){

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
	 *
	 * @param apellido
	 * @param nombre    nombre
	 */
	public void agregarColector(String apellido, String nombre){

	}

	/**
	 *
	 * @param colector    colector
	 */
	public void eliminarColector(ColectorSecundario colector){

	}

	public void agregarEspecimen(){

	}

	/**
	 *
	 * @param metodoDeTratamiento
	 * @param especimen    especimen
	 */
	public void agregarMuestraAsociada(String metodoDeTratamiento, Especimen especimen){

	}

    public Trayecto getTrayecto(){
        return trayecto;
    }

    /**
     *
     * @param newVal
     */
    public void setTrayecto(Trayecto newVal){
        trayecto = newVal;
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
    public List<ColectorSecundario> getColectoresSecundarios() {
        if (colectoresSecundarios == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColectorSecundarioDao targetDao = daoSession.getColectorSecundarioDao();
            List<ColectorSecundario> colectoresSecundariosNew = targetDao._queryViaje_ColectoresSecundarios(id);
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
    public List<Recoleccion> getRecolecciones() {
        if (recolecciones == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RecoleccionDao targetDao = daoSession.getRecoleccionDao();
            List<Recoleccion> recoleccionesNew = targetDao._queryViaje_Recolecciones(id);
            synchronized (this) {
                if(recolecciones == null) {
                    recolecciones = recoleccionesNew;
                }
            }
        }
        return recolecciones;
    }

    public void setRecolecciones(List<Recoleccion> recolecciones) {
        this.recolecciones = recolecciones;
    }

	/** called by internal mechanisms, do not call yourself. */
	public void __setDaoSession(DaoSession daoSession) {
		// TODO Auto-generated method stub
		this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getViajeDao() : null;
	}

}
