package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.FlorDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Flor implements Parcelable {

    private Long id;
    private String descripcion;
    private Long colorDeLaCorolaID;
    private Long colorDelCalizID;
    private Long colorDelGineceoID;
    private Long colorDeLosEstambresID;
    private Long colorDeLosEstigmasID;
    private Long colorDeLosPistiliodiosID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient FlorDao myDao;

    private ColorEspecimen colorDeLaCorola;
    private Long colorDeLaCorola__resolvedKey;

    private ColorEspecimen colorDelCaliz;
    private Long colorDelCaliz__resolvedKey;

    private ColorEspecimen colorDelGineceo;
    private Long colorDelGineceo__resolvedKey;

    private ColorEspecimen colorDeLosEstambres;
    private Long colorDeLosEstambres__resolvedKey;

    private ColorEspecimen colorDeLosEstigmas;
    private Long colorDeLosEstigmas__resolvedKey;

    private ColorEspecimen colorDeLosPistiliodios;
    private Long colorDeLosPistiliodios__resolvedKey;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFlorDao() : null;
    }


	public void finalize() throws Throwable {

	}

	public Flor(){

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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getColorDeLaCorolaID() {
        return colorDeLaCorolaID;
    }

    public void setColorDeLaCorolaID(Long colorDeLaCorolaID) {
        this.colorDeLaCorolaID = colorDeLaCorolaID;
    }

    public Long getColorDelCalizID() {
        return colorDelCalizID;
    }

    public void setColorDelCalizID(Long colorDelCalizID) {
        this.colorDelCalizID = colorDelCalizID;
    }

    public Long getColorDelGineceoID() {
        return colorDelGineceoID;
    }

    public void setColorDelGineceoID(Long colorDelGineceoID) {
        this.colorDelGineceoID = colorDelGineceoID;
    }

    public Long getColorDeLosEstambresID() {
        return colorDeLosEstambresID;
    }

    public void setColorDeLosEstambresID(Long colorDeLosEstambresID) {
        this.colorDeLosEstambresID = colorDeLosEstambresID;
    }

    public Long getColorDeLosEstigmasID() {
        return colorDeLosEstigmasID;
    }

    public void setColorDeLosEstigmasID(Long colorDeLosEstigmasID) {
        this.colorDeLosEstigmasID = colorDeLosEstigmasID;
    }

    public Long getColorDeLosPistiliodiosID() {
        return colorDeLosPistiliodiosID;
    }

    public void setColorDeLosPistiliodiosID(Long colorDeLosPistiliodiosID) {
        this.colorDeLosPistiliodiosID = colorDeLosPistiliodiosID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDeLaCorola() {
        Long __key = this.colorDeLaCorolaID;
        if (colorDeLaCorola__resolvedKey == null || !colorDeLaCorola__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDeLaCorolaNew = targetDao.load(__key);
            synchronized (this) {
                colorDeLaCorola = colorDeLaCorolaNew;
            	colorDeLaCorola__resolvedKey = __key;
            }
        }
		return colorDeLaCorola;
	}

    /**
     *
     * @param colorDeLaCorola
     */
    public void setColorDeLaCorola(ColorEspecimen colorDeLaCorola) {
        synchronized (this) {
            this.colorDeLaCorola = colorDeLaCorola;
            colorDeLaCorolaID = colorDeLaCorola == null ? null : colorDeLaCorola.getId();
            colorDeLaCorola__resolvedKey = colorDeLaCorolaID;
	    }
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelCaliz() {
        Long __key = this.colorDelCalizID;
        if (colorDelCaliz__resolvedKey == null || !colorDelCaliz__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelCalizNew = targetDao.load(__key);
            synchronized (this) {
                colorDelCaliz = colorDelCalizNew;
            	colorDelCaliz__resolvedKey = __key;
            }
        }
		return colorDelCaliz;
	}

    public void setColorDelCaliz(ColorEspecimen colorDelCaliz) {
        synchronized (this) {
            this.colorDelCaliz = colorDelCaliz;
            colorDelCalizID = colorDelCaliz == null ? null : colorDelCaliz.getId();
            colorDelCaliz__resolvedKey = colorDelCalizID;
	    }
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelGineceo() {
        Long __key = this.colorDelGineceoID;
        if (colorDelGineceo__resolvedKey == null || !colorDelGineceo__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelGineceoNew = targetDao.load(__key);
            synchronized (this) {
                colorDelGineceo = colorDelGineceoNew;
            	colorDelGineceo__resolvedKey = __key;
            }
        }
		return colorDelGineceo;
	}

    public void setColorDelGineceo(ColorEspecimen colorDelGineceo) {
        synchronized (this) {
            this.colorDelGineceo = colorDelGineceo;
            colorDelGineceoID = colorDelGineceo == null ? null : colorDelGineceo.getId();
            colorDelGineceo__resolvedKey = colorDelGineceoID;
	}
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDeLosEstambres() {
        Long __key = this.colorDeLosEstambresID;
        if (colorDeLosEstambres__resolvedKey == null || !colorDeLosEstambres__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDeLosEstambresNew = targetDao.load(__key);
            synchronized (this) {
                colorDeLosEstambres = colorDeLosEstambresNew;
            	colorDeLosEstambres__resolvedKey = __key;
            }
        }
		return colorDeLosEstambres;
	}

    public void setColorDeLosEstambres(ColorEspecimen colorDeLosEstambres) {
        synchronized (this) {
            this.colorDeLosEstambres = colorDeLosEstambres;
            colorDeLosEstambresID = colorDeLosEstambres == null ? null : colorDeLosEstambres.getId();
            colorDeLosEstambres__resolvedKey = colorDeLosEstambresID;
	    }
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDeLosEstigmas() {
        Long __key = this.colorDeLosEstigmasID;
        if (colorDeLosEstigmas__resolvedKey == null || !colorDeLosEstigmas__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDeLosEstigmasNew = targetDao.load(__key);
            synchronized (this) {
                colorDeLosEstigmas = colorDeLosEstigmasNew;
            	colorDeLosEstigmas__resolvedKey = __key;
            }
        }
		return colorDeLosEstigmas;
	}

    public void setColorDeLosEstigmas(ColorEspecimen colorDeLosEstigmas) {
        synchronized (this) {
            this.colorDeLosEstigmas = colorDeLosEstigmas;
            colorDeLosEstigmasID = colorDeLosEstigmas == null ? null : colorDeLosEstigmas.getId();
            colorDeLosEstigmas__resolvedKey = colorDeLosEstigmasID;
	    }
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDeLosPistiliodios() {
        Long __key = this.colorDeLosPistiliodiosID;
        if (colorDeLosPistiliodios__resolvedKey == null || !colorDeLosPistiliodios__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDeLosPistiliodiosNew = targetDao.load(__key);
            synchronized (this) {
                colorDeLosPistiliodios = colorDeLosPistiliodiosNew;
            	colorDeLosPistiliodios__resolvedKey = __key;
            }
        }
		return colorDeLosPistiliodios;
	}

    public void setColorDeLosPistiliodios(ColorEspecimen colorDeLosPistiliodios) {
        synchronized (this) {
            this.colorDeLosPistiliodios = colorDeLosPistiliodios;
            colorDeLosPistiliodiosID = colorDeLosPistiliodios == null ? null : colorDeLosPistiliodios.getId();
            colorDeLosPistiliodios__resolvedKey = colorDeLosPistiliodiosID;
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
        dest.writeValue(this.colorDeLaCorolaID);
        dest.writeValue(this.colorDelCalizID);
        dest.writeValue(this.colorDelGineceoID);
        dest.writeValue(this.colorDeLosEstambresID);
        dest.writeValue(this.colorDeLosEstigmasID);
        dest.writeValue(this.colorDeLosPistiliodiosID);
        dest.writeParcelable(this.colorDeLaCorola, 0);
        dest.writeValue(this.colorDeLaCorola__resolvedKey);
        dest.writeParcelable(this.colorDelCaliz, 0);
        dest.writeValue(this.colorDelCaliz__resolvedKey);
        dest.writeParcelable(this.colorDelGineceo, 0);
        dest.writeValue(this.colorDelGineceo__resolvedKey);
        dest.writeParcelable(this.colorDeLosEstambres, 0);
        dest.writeValue(this.colorDeLosEstambres__resolvedKey);
        dest.writeParcelable(this.colorDeLosEstigmas, 0);
        dest.writeValue(this.colorDeLosEstigmas__resolvedKey);
        dest.writeParcelable(this.colorDeLosPistiliodios, 0);
        dest.writeValue(this.colorDeLosPistiliodios__resolvedKey);
    }

    private Flor(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.descripcion = in.readString();
        this.colorDeLaCorolaID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelCalizID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelGineceoID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLosEstambresID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLosEstigmasID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLosPistiliodiosID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaCorola = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.colorDeLaCorola__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelCaliz = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.colorDelCaliz__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelGineceo = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.colorDelGineceo__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLosEstambres = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.colorDeLosEstambres__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLosEstigmas = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.colorDeLosEstigmas__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLosPistiliodios = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.colorDeLosPistiliodios__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Flor> CREATOR = new Parcelable.Creator<Flor>() {
        public Flor createFromParcel(Parcel source) {
            return new Flor(source);
        }

        public Flor[] newArray(int size) {
            return new Flor[size];
        }
    };
}