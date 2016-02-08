package edu.udistrital.plantae.logicadominio.taxonomia;

import android.os.Parcel;
import android.os.Parcelable;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.TaxonDao;
import edu.udistrital.plantae.persistencia.UsoDao;
import edu.udistrital.plantae.persistencia.UsuarioDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:39 PM
 */
public class Uso implements Parcelable {

    private Long id;
    private String descripcion;
    private Long usuarioID;
    private long taxonID;

	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient UsoDao myDao;

    private Usuario usuario;
    private Long usuario__resolvedKey;

    private Taxon taxon;
    private Long taxon__resolvedKey;



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUsoDao() : null;
    }

	public Uso(){
	}

	public void finalize() throws Throwable {
	}

	/**
	 *
	 * @param nombre
	 */
	public Uso(String nombre){
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

    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public long getTaxonID() {
        return taxonID;
    }

    public void setTaxonID(long taxonID) {
        this.taxonID = taxonID;
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

    public void setUsuario(Usuario usuario) {
        synchronized (this) {
            this.usuario = usuario;
            usuarioID = usuario == null ? null : usuario.getId();
            usuario__resolvedKey = usuarioID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Taxon getTaxon() {
        long __key = this.taxonID;
        if (taxon__resolvedKey == null || !taxon__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaxonDao targetDao = daoSession.getTaxonDao();
            Taxon taxonNew = targetDao.load(__key);
            synchronized (this) {
                taxon = taxonNew;
            	taxon__resolvedKey = __key;
            }
        }
        return taxon;
    }

    public void setTaxon(Taxon taxon) {
        if (taxon == null) {
            throw new DaoException("To-one property 'taxonID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.taxon = taxon;
            taxonID = taxon.getId();
            taxon__resolvedKey = taxonID;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.descripcion);
        dest.writeValue(this.usuarioID);
        dest.writeLong(this.taxonID);
        dest.writeValue(this.usuario__resolvedKey);
        dest.writeValue(this.taxon__resolvedKey);
    }

    private Uso(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.descripcion = in.readString();
        this.usuarioID = (Long) in.readValue(Long.class.getClassLoader());
        this.taxonID = in.readLong();
        this.usuario__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.taxon__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Uso> CREATOR = new Parcelable.Creator<Uso>() {
        public Uso createFromParcel(Parcel source) {
            return new Uso(source);
        }

        public Uso[] newArray(int size) {
            return new Uso[size];
        }
    };
}