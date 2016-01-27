package edu.udistrital.plantae.logicadominio.taxonomia;

import java.util.List;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.NombreComunDao;
import edu.udistrital.plantae.persistencia.TaxonDao;
import edu.udistrital.plantae.persistencia.UsoDao;
import edu.udistrital.plantae.persistencia.UsuarioDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public abstract class Taxon {

    private Long id;
    private String nombre;
    private List<Uso> usos;
    private List<NombreComun> nombresComunes;
	private Taxon taxonPadre;
	private String autor;
	private String nombreCientifico;
    private long usuarioId;

	/* greendao specific properties */
	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient TaxonDao myDao;

    private Usuario usuario;
    private Long usuario__resolvedKey;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaxonDao() : null;
    }

	public Taxon(){
	}

	public void finalize() throws Throwable {
	}

    public Taxon getTaxonPadre(){
        return taxonPadre;
    }

    public void setTaxonPadre(Taxon taxonPadre) {
        this.taxonPadre = taxonPadre;
        if (getClass().equals(EpitetoEspecifico.class)) {
            nombreCientifico = taxonPadre.getNombre().concat(" ").concat(nombre);
        }
    }

    /**
	 * 
	 * @param nombre
	 */
	public Taxon(String nombre){
        this.nombre = nombre;
        if (getClass().equals(Familia.class)|| getClass().equals(Genero.class)) {
            nombreCientifico = nombre;
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

    public String getAutor() {
        return autor;
    }

    /**
     *
     * @param autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    /**
     *
     * @param nombreCientifico
     */
    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
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

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<NombreComun> getNombresComunes() {
        if (nombresComunes == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NombreComunDao targetDao = daoSession.getNombreComunDao();
            List<NombreComun> nombresComunesNew = targetDao._queryTaxon_NombresComunes(id);
            synchronized (this) {
                if(nombresComunes == null) {
                    nombresComunes = nombresComunesNew;
                }
            }
        }
        return nombresComunes;
    }

    /**
     *
     * @param nombresComunes
     */
    public void setNombresComunes(List<NombreComun> nombresComunes){
        this.nombresComunes = nombresComunes;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Uso> getUsos() {
        if (usos == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsoDao targetDao = daoSession.getUsoDao();
            List<Uso> usosNew = targetDao._queryTaxon_Usos(id);
            synchronized (this) {
                if(usos == null) {
                    usos = usosNew;
                }
            }
        }
        return usos;
    }

    /**
     *
     * @param usos
     */
    public void setUsos(List<Uso> usos){
        this.usos = usos;
    }

    public String aString() {
        return nombreCientifico != null ? nombreCientifico : nombre;
    }

    @Override
    public String toString() {
        return "Taxon{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", usos=" + usos +
                ", nombresComunes=" + nombresComunes +
                ", taxonPadre=" + taxonPadre +
                ", autor='" + autor + '\'' +
                ", nombreCientifico='" + nombreCientifico + '\'' +
                ", usuarioId=" + usuarioId +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", usuario=" + usuario +
                ", usuario__resolvedKey=" + usuario__resolvedKey +
                '}';
    }
}