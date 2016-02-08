package edu.udistrital.plantae.logicadominio.datosespecimen;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.FrutoDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Fruto {

    private Long id;
    private String consistenciaDelPericarpio;
    private String descripcion;
    private Long colorDelExocarpioID;
    private Long colorDelMesocarpioID;
    private Long colorDelExocarpioInmaduroID;
    private Long colorDelMesocarpioInmaduroID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient FrutoDao myDao;

    private ColorEspecimen colorDelMesocarpio;
    private Long colorDelMesocarpio__resolvedKey;

    private ColorEspecimen colorDelExocarpio;
    private Long colorDelExocarpio__resolvedKey;

    private ColorEspecimen colorDelMesocarpioInmaduro;
    private Long colorDelMesocarpioInmaduro__resolvedKey;

    private ColorEspecimen colorDelExocarpioInmaduro;
    private Long colorDelExocarpioInmaduro__resolvedKey;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFrutoDao() : null;
    }

	public void finalize() throws Throwable {

	}

	public Fruto(){

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

    public String getConsistenciaDelPericarpio() {
        return consistenciaDelPericarpio;
    }

    public void setConsistenciaDelPericarpio(String consistenciaDelPericarpio) {
        this.consistenciaDelPericarpio = consistenciaDelPericarpio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getColorDelExocarpioID() {
        return colorDelExocarpioID;
    }

    public void setColorDelExocarpioID(Long colorDelExocarpioID) {
        this.colorDelExocarpioID = colorDelExocarpioID;
    }

    public Long getColorDelMesocarpioID() {
        return colorDelMesocarpioID;
    }

    public void setColorDelMesocarpioID(Long colorDelMesocarpioID) {
        this.colorDelMesocarpioID = colorDelMesocarpioID;
    }

    public Long getColorDelExocarpioInmaduroID() {
        return colorDelExocarpioInmaduroID;
    }

    public void setColorDelExocarpioInmaduroID(Long colorDelExocarpioInmaduroID) {
        this.colorDelExocarpioInmaduroID = colorDelExocarpioInmaduroID;
    }

    public Long getColorDelMesocarpioInmaduroID() {
        return colorDelMesocarpioInmaduroID;
    }

    public void setColorDelMesocarpioInmaduroID(Long colorDelMesocarpioInmaduroID) {
        this.colorDelMesocarpioInmaduroID = colorDelMesocarpioInmaduroID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelMesocarpio() {
        Long __key = this.colorDelMesocarpioID;
        if (colorDelMesocarpio__resolvedKey == null || !colorDelMesocarpio__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelMesocarpioNew = targetDao.load(__key);
            synchronized (this) {
                colorDelMesocarpio = colorDelMesocarpioNew;
            	colorDelMesocarpio__resolvedKey = __key;
            }
        }
        return colorDelMesocarpio;
    }

    public void setColorDelMesocarpio(ColorEspecimen colorDelMesocarpio) {
        synchronized (this) {
            this.colorDelMesocarpio = colorDelMesocarpio;
            colorDelMesocarpioID = colorDelMesocarpio == null ? null : colorDelMesocarpio.getId();
            colorDelMesocarpio__resolvedKey = colorDelMesocarpioID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelExocarpio() {
        Long __key = this.colorDelExocarpioID;
        if (colorDelExocarpio__resolvedKey == null || !colorDelExocarpio__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelExocarpioNew = targetDao.load(__key);
            synchronized (this) {
                colorDelExocarpio = colorDelExocarpioNew;
            	colorDelExocarpio__resolvedKey = __key;
            }
        }
        return colorDelExocarpio;
    }

    public void setColorDelExocarpio(ColorEspecimen colorDelExocarpio) {
        synchronized (this) {
            this.colorDelExocarpio = colorDelExocarpio;
            colorDelExocarpioID = colorDelExocarpio == null ? null : colorDelExocarpio.getId();
            colorDelExocarpio__resolvedKey = colorDelExocarpioID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelMesocarpioInmaduro() {
        Long __key = this.colorDelMesocarpioInmaduroID;
        if (colorDelMesocarpioInmaduro__resolvedKey == null || !colorDelMesocarpioInmaduro__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelMesocarpioInmaduroNew = targetDao.load(__key);
            synchronized (this) {
                colorDelMesocarpioInmaduro = colorDelMesocarpioInmaduroNew;
            	colorDelMesocarpioInmaduro__resolvedKey = __key;
            }
        }
        return colorDelMesocarpioInmaduro;
    }

    public void setColorDelMesocarpioInmaduro(ColorEspecimen colorDelMesocarpioInmaduro) {
        synchronized (this) {
            this.colorDelMesocarpioInmaduro = colorDelMesocarpioInmaduro;
            colorDelMesocarpioInmaduroID = colorDelMesocarpioInmaduro == null ? null : colorDelMesocarpioInmaduro.getId();
            colorDelMesocarpioInmaduro__resolvedKey = colorDelMesocarpioInmaduroID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelExocarpioInmaduro() {
        Long __key = this.colorDelExocarpioInmaduroID;
        if (colorDelExocarpioInmaduro__resolvedKey == null || !colorDelExocarpioInmaduro__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelExocarpioInmaduroNew = targetDao.load(__key);
            synchronized (this) {
                colorDelExocarpioInmaduro = colorDelExocarpioInmaduroNew;
            	colorDelExocarpioInmaduro__resolvedKey = __key;
            }
        }
        return colorDelExocarpioInmaduro;
    }

    public void setColorDelExocarpioInmaduro(ColorEspecimen colorDelExocarpioInmaduro) {
        synchronized (this) {
            this.colorDelExocarpioInmaduro = colorDelExocarpioInmaduro;
            colorDelExocarpioInmaduroID = colorDelExocarpioInmaduro == null ? null : colorDelExocarpioInmaduro.getId();
            colorDelExocarpioInmaduro__resolvedKey = colorDelExocarpioInmaduroID;
        }
    }

    public String aString() {
        String string = "";
        if (consistenciaDelPericarpio != null) {
            string = string + consistenciaDelPericarpio;
        }
        if (descripcion != null) {
            string = string + (string.equals("") ? ", ":"") + descripcion;
        }
        if (getColorDelMesocarpio() != null) {
            string = string + (string.equals("") ? ", ":"") + colorDelMesocarpio.aString();
        }
        if (getColorDelExocarpio() != null) {
            string = string + (string.equals("") ? ", ":"") + colorDelExocarpio.aString();
        }
        if (getColorDelMesocarpioInmaduro() != null) {
            string = string + (string.equals("") ? ", ":"") + colorDelMesocarpioInmaduro.aString();
        }
        if (getColorDelExocarpioInmaduro() != null) {
            string = string + (string.equals("") ? ", ":"") + colorDelExocarpioInmaduro.aString();
        }
        return string;
    }
}
