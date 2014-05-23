package edu.udistrital.plantae.logicadominio.datosespecimen;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.TalloDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Tallo {

    private Long id;
    private String alturaDelTallo;
    private ColorEspecimen colorDelTallo;
    private boolean desnudoCubierto;
    private String diametroDelTallo;
    private String disposicionDeLasEspinas;
    private boolean entrenudosConspicuos;
    private boolean espinas;
    private String formaDelTallo;
    private String longitudEntrenudos;
    private String naturalezaDelTallo;
    private String descripcion;
    private Long colorDelTalloID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient TalloDao myDao;

    private Long colorDelTallo__resolvedKey;



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTalloDao() : null;
    }

	public void finalize() throws Throwable {

	}

	public Tallo(){

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

    public String getAlturaDelTallo() {
		return alturaDelTallo;
	}

    public void setAlturaDelTallo(String alturaDelTallo) {
        this.alturaDelTallo = alturaDelTallo;
	}

    public String getDiametroDelTallo() {
		return diametroDelTallo;
	}

    public void setDiametroDelTallo(String diametroDelTallo) {
        this.diametroDelTallo = diametroDelTallo;
	}

    public String getDisposicionDeLasEspinas() {
		return disposicionDeLasEspinas;
	}

    public void setDisposicionDeLasEspinas(String disposicionDeLasEspinas) {
        this.disposicionDeLasEspinas = disposicionDeLasEspinas;
	}

    public String getFormaDelTallo() {
		return formaDelTallo;
	}

    public void setFormaDelTallo(String formaDelTallo) {
        this.formaDelTallo = formaDelTallo;
	}

    public String getLongitudEntrenudos() {
		return longitudEntrenudos;
	}

    public void setLongitudEntrenudos(String longitudEntrenudos) {
        this.longitudEntrenudos = longitudEntrenudos;
	}

    public String getNaturalezaDelTallo() {
		return naturalezaDelTallo;
	}

    public void setNaturalezaDelTallo(String naturalezaDelTallo) {
        this.naturalezaDelTallo = naturalezaDelTallo;
	}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getDesnudoCubierto() {
        return desnudoCubierto;
    }

    public void setDesnudoCubierto(boolean desnudoCubierto) {
        this.desnudoCubierto = desnudoCubierto;
    }

    public boolean getEntrenudosConspicuos() {
        return entrenudosConspicuos;
    }

    public void setEntrenudosConspicuos(boolean entrenudosConspicuos) {
        this.entrenudosConspicuos = entrenudosConspicuos;
    }

    public boolean getEspinas() {
        return espinas;
    }

    public void setEspinas(boolean espinas) {
        this.espinas = espinas;
    }

    public Long getColorDelTalloID() {
        return colorDelTalloID;
    }

    public void setColorDelTalloID(Long colorDelTalloID) {
        this.colorDelTalloID = colorDelTalloID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelTallo() {
        Long __key = this.colorDelTalloID;
        if (colorDelTallo__resolvedKey == null || !colorDelTallo__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelTalloNew = targetDao.load(__key);
            synchronized (this) {
                colorDelTallo = colorDelTalloNew;
            	colorDelTallo__resolvedKey = __key;
            }
        }
        return colorDelTallo;
    }

    /**
     *
     * @param colorDelTallo
     */
    public void setColorDelTallo(ColorEspecimen colorDelTallo) {
        synchronized (this) {
            this.colorDelTallo = colorDelTallo;
            colorDelTalloID = colorDelTallo == null ? null : colorDelTallo.getId();
            colorDelTallo__resolvedKey = colorDelTalloID;
        }
    }

}