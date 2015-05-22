package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.HabitoDao;
import edu.udistrital.plantae.persistencia.UsuarioDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Habito implements Parcelable {

    private Long id;
    private String habito;
    private Long usuarioID;

    public Habito(String habito) {
        this.habito = habito;
    }

    public Habito(){

	}

	public void finalize() throws Throwable {

	}

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient HabitoDao myDao;

    private Usuario usuario;
    private Long usuario__resolvedKey;



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHabitoDao() : null;
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

    public String getHabito() {
		return habito;
	}

    public void setHabito(String habito) {
        this.habito = habito;
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

    public void setUsuario(Usuario usuario) {
        synchronized (this) {
            this.usuario = usuario;
            usuarioID = usuario == null ? null : usuario.getId();
            usuario__resolvedKey = usuarioID;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.habito);
        dest.writeValue(this.usuarioID);
        dest.writeValue(this.usuario__resolvedKey);
    }

    private Habito(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.habito = in.readString();
        this.usuarioID = (Long) in.readValue(Long.class.getClassLoader());
        this.usuario__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Habito> CREATOR = new Parcelable.Creator<Habito>() {
        public Habito createFromParcel(Parcel source) {
            return new Habito(source);
        }

        public Habito[] newArray(int size) {
            return new Habito[size];
        }
    };

    @Override
    public String toString() {
        return habito;
    }
}