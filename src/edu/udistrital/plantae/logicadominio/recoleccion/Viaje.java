package edu.udistrital.plantae.logicadominio.recoleccion;
import com.google.android.gms.maps.MapView;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.datosespecimen.*;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<Especimen> especimenes;
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
	public Viaje(ColectorSecundario[] colectores, Proyecto proyecto, String nombre){
        colectoresSecundarios = new ArrayList<ColectorSecundario>(Arrays.asList(colectores));
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
        colectorSecundario.setEspecimenID(this.getId());
        daoSession.getColectorSecundarioDao().insert(colectorSecundario);
	}

	/**
	 *
	 * @param colector    colector
	 */
	public void eliminarColector(ColectorSecundario colector){

	}

	public void agregarEspecimen(EspecimenDTO especimenDTO){
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
