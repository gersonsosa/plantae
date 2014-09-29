package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.ColorMunsellDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.UsuarioDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @updated 26-Ago-2014 6:35:38 PM
 */
public class ColorEspecimen implements Parcelable {

	private Long id;
	private String nombre;
	private String descripcion;
    private Integer colorRGB;
	private ColorMunsell colorMunsell;
    private Long colorMunsellID;
    private Long usuarioID;

    private Long colorMunsell__resolvedKey;

	/* greendao specific properties */
	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
    private transient ColorEspecimenDao myDao;

	public void finalize() throws Throwable {
	}

	public ColorEspecimen(){
    }

    private Usuario usuario;
    private Long usuario__resolvedKey;



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getColorEspecimenDao() : null;
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

    public Integer getColorRGB() {
        return colorRGB;
    }

    /**
     *
     * @param colorRGB
     */
    public void setColorRGB(Integer colorRGB) {
        this.colorRGB = colorRGB;
    }

    public Long getColorMunsellID() {
        return colorMunsellID;
    }

    public void setColorMunsellID(Long colorMunsellID) {
        this.colorMunsellID = colorMunsellID;
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorMunsell getColorMunsell() {
        Long __key = this.colorMunsellID;
        if (colorMunsell__resolvedKey == null || !colorMunsell__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorMunsellDao targetDao = daoSession.getColorMunsellDao();
            ColorMunsell colorMunsellNew = targetDao.load(__key);
            synchronized (this) {
                colorMunsell = colorMunsellNew;
            	colorMunsell__resolvedKey = __key;
            }
        }
        return colorMunsell;
	}

    public void setColorMunsell(ColorMunsell colorMunsell) {
        synchronized (this) {
            this.colorMunsell = colorMunsell;
            colorMunsellID = colorMunsell == null ? null : colorMunsell.getId();
            colorMunsell__resolvedKey = colorMunsellID;
        }
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
        dest.writeString(this.nombre);
        dest.writeString(this.descripcion);
        dest.writeValue(this.colorRGB);
        dest.writeParcelable(this.colorMunsell, 0);
        dest.writeValue(this.colorMunsellID);
        dest.writeValue(this.usuarioID);
        dest.writeValue(this.colorMunsell__resolvedKey);
        dest.writeValue(this.usuario__resolvedKey);
    }

    private ColorEspecimen(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.colorRGB = (Integer) in.readValue(Integer.class.getClassLoader());
        this.colorMunsell = in.readParcelable(ColorMunsell.class.getClassLoader());
        this.colorMunsellID = (Long) in.readValue(Long.class.getClassLoader());
        this.usuarioID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorMunsell__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.usuario__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<ColorEspecimen> CREATOR = new Parcelable.Creator<ColorEspecimen>() {
        public ColorEspecimen createFromParcel(Parcel source) {
            return new ColorEspecimen(source);
        }

        public ColorEspecimen[] newArray(int size) {
            return new ColorEspecimen[size];
        }
    };
}