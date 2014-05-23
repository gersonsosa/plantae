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
    private ColorEspecimen colorDelEndocarpio;
    private ColorEspecimen colorDelExocarpio;
    private String consistenciaDelPericarpio;
    private String descripcion;
    private Long colorDelEndocarpioID;
    private Long colorDelExocarpioID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient FrutoDao myDao;

    private Long colorDelEndocarpio__resolvedKey;

    private Long colorDelExocarpio__resolvedKey;



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

    public Long getColorDelEndocarpioID() {
        return colorDelEndocarpioID;
    }

    public void setColorDelEndocarpioID(Long colorDelEndocarpioID) {
        this.colorDelEndocarpioID = colorDelEndocarpioID;
    }

    public Long getColorDelExocarpioID() {
        return colorDelExocarpioID;
    }

    public void setColorDelExocarpioID(Long colorDelExocarpioID) {
        this.colorDelExocarpioID = colorDelExocarpioID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelEndocarpio() {
        Long __key = this.colorDelEndocarpioID;
        if (colorDelEndocarpio__resolvedKey == null || !colorDelEndocarpio__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelEndocarpioNew = targetDao.load(__key);
            synchronized (this) {
                colorDelEndocarpio = colorDelEndocarpioNew;
            	colorDelEndocarpio__resolvedKey = __key;
            }
        }
		return colorDelEndocarpio;
	}

    public void setColorDelEndocarpio(ColorEspecimen colorDelEndocarpio) {
        synchronized (this) {
            this.colorDelEndocarpio = colorDelEndocarpio;
            colorDelEndocarpioID = colorDelEndocarpio == null ? null : colorDelEndocarpio.getId();
            colorDelEndocarpio__resolvedKey = colorDelEndocarpioID;
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

}