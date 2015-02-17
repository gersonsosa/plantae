package edu.udistrital.plantae.logicadominio.ubicacion;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.RegionDao;
import edu.udistrital.plantae.persistencia.UsuarioDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public abstract class Region {

    private Long id;
    private String nombre;
    private Region regionPadre;
    private String nombreCompleto;
    private long usuarioId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient RegionDao myDao;

    private Usuario usuario;
    private Long usuario__resolvedKey;

	public Region(){
	}

	public void finalize() throws Throwable {
	}

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRegionDao() : null;
    }

    /**
     *
     * @param nombre
     */
    public Region(String nombre){
        this.nombre = nombre;
        if (getClass().equals(Pais.class)) {
            nombreCompleto = nombre;
        }
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

	public String getNombre(){
		return nombre;
	}

	/**
	 *
	 * @param nombre
	 */
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Region getRegionPadre() {
        return regionPadre;
    }

    public void setRegionPadre(Region regionPadre) {
        this.regionPadre = regionPadre;
        nombreCompleto = regionPadre.getNombreCompleto().concat(" ").concat(nombre);
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    /** To-one relationship, resolved on first access. */
    public Usuario getUsuario() {
        long __key = this.usuarioId;
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

    public void setUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new DaoException("To-one property 'usuarioId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.usuario = usuario;
            usuarioId = usuario.getId();
            usuario__resolvedKey = usuarioId;
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}