package edu.udistrital.plantae.logicadominio.autenticacion;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.PersonaDao;
import edu.udistrital.plantae.persistencia.UsuarioDao;

import java.io.Serializable;


/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 19-May-2013 11:50:34 PM
 */
public class Persona implements Serializable {

    private Long id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String institucion;
    private Long usuarioID;

    private Usuario usuario;
    private Long usuario__resolvedKey;
	
	/* greendao specific properties */
	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient PersonaDao myDao;

	public Persona(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param apellidos
	 * @param nombres
	 */
	public Persona(String apellidos, String nombres){

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getNombres() {
		return nombres;
	}

    public void setNombres(String nombres) {
        this.nombres = nombres;
	}

    public String getApellidos() {
		return apellidos;
	}

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
	}

    public String getDireccion() {
		return direccion;
	}

    public void setDireccion(String direccion) {
        this.direccion = direccion;
	}

    public String getTelefono() {
		return telefono;
	}

    public void setTelefono(String telefono) {
        this.telefono = telefono;
	}

    public String getInstitucion() {
		return institucion;
	}

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
	}

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    /** To-one relationship, resolved on first access. */
    public Usuario getUsuario() {
        Long __key = this.usuarioID;
        if (usuario__resolvedKey == null || !usuario__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsuarioDao targetDao = daoSession.getUsuarioDao();
            Usuario usuarioNew = targetDao.load(__key);
            synchronized (this) {
                usuario = usuarioNew;
            	usuario__resolvedKey = __key;
            }
        }
		return usuario;
	}

	/**
	 * 
	 * @param usuario
	 */
    public void setUsuario(Usuario usuario) {
        synchronized (this) {
		    this.usuario = usuario;
            usuarioID = usuario == null ? null : usuario.getId();
            usuario__resolvedKey = usuarioID;
	    }
    }

	/** called by internal mechanisms, do not call yourself. */
	public void __setDaoSession(DaoSession daoSession) {
		this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonaDao() : null;
	}

}
