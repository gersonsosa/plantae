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
public class Flor {

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
    public String toString() {
        return "Flower: " +
                colorDeLaCorola +
                ", " + colorDelCaliz +
                ", " + colorDelGineceo +
                ", " + colorDeLosEstambres +
                ", " + colorDeLosEstigmas +
                ", " + colorDeLosPistiliodios +
                ", " + descripcion;
    }
}