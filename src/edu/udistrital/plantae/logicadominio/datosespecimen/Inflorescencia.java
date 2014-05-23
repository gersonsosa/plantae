package edu.udistrital.plantae.logicadominio.datosespecimen;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.InflorescenciaDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Inflorescencia {

    private Long id;
    private ColorEspecimen colorDeLaInflorescenciaEnFlor;
    private ColorEspecimen colorDeLaInflorescenciaEnFruto;
    private boolean inflorescenciaSolitaria;
    private String naturalezaDeLasBracteasPedunculares;
    private String naturalezaDelProfilo;
    private int numeroDeLasBracteasPedunculares;
    private int numeroDeRaquilas;
    private String posicionDeLasBracteasPedunculares;
    private String posicionDeLasInflorescencias;
    private String raquilas;
    private String raquis;
    private String tamañoDeLasBracteasPedunculares;
    private String tamañoDelPedunculo;
    private String tamañoDelProfilo;
    private String tamañoDelRaquis;
    private String tamañoDeRaquilas;
    private String descripcion;

    private Long colorDeLaInflorescenciaEnFlorID;
    private Long colorDeLaInflorescenciaEnFrutoID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient InflorescenciaDao myDao;

    private Long colorDeLaInflorescenciaEnFlor__resolvedKey;

    private Long colorDeLaInflorescenciaEnFruto__resolvedKey;



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getInflorescenciaDao() : null;
    }

	public void finalize() throws Throwable {
	}

	public Inflorescencia(){
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

    public String getNaturalezaDeLasBracteasPedunculares() {
		return naturalezaDeLasBracteasPedunculares;
	}

    public void setNaturalezaDeLasBracteasPedunculares(String naturalezaDeLasBracteasPedunculares) {
        this.naturalezaDeLasBracteasPedunculares = naturalezaDeLasBracteasPedunculares;
	}

    public String getNaturalezaDelProfilo() {
		return naturalezaDelProfilo;
	}

    public void setNaturalezaDelProfilo(String naturalezaDelProfilo) {
        this.naturalezaDelProfilo = naturalezaDelProfilo;
	}

    public String getPosicionDeLasBracteasPedunculares() {
		return posicionDeLasBracteasPedunculares;
	}

    public void setPosicionDeLasBracteasPedunculares(String posicionDeLasBracteasPedunculares) {
        this.posicionDeLasBracteasPedunculares = posicionDeLasBracteasPedunculares;
	}

    public String getPosicionDeLasInflorescencias() {
		return posicionDeLasInflorescencias;
	}

    public void setPosicionDeLasInflorescencias(String posicionDeLasInflorescencias) {
        this.posicionDeLasInflorescencias = posicionDeLasInflorescencias;
	}

    public String getRaquilas() {
		return raquilas;
	}

    public void setRaquilas(String raquilas) {
        this.raquilas = raquilas;
	}

    public String getRaquis() {
		return raquis;
	}

    public void setRaquis(String raquis) {
        this.raquis = raquis;
	}

    public String getTamañoDeLasBracteasPedunculares() {
		return tamañoDeLasBracteasPedunculares;
	}

    public void setTamañoDeLasBracteasPedunculares(String tamañoDeLasBracteasPedunculares) {
        this.tamañoDeLasBracteasPedunculares = tamañoDeLasBracteasPedunculares;
	}

    public String getTamañoDelPedunculo() {
		return tamañoDelPedunculo;
	}

    public void setTamañoDelPedunculo(String tamañoDelPedunculo) {
        this.tamañoDelPedunculo = tamañoDelPedunculo;
	}

    public String getTamañoDelProfilo() {
		return tamañoDelProfilo;
	}

    public void setTamañoDelProfilo(String tamañoDelProfilo) {
        this.tamañoDelProfilo = tamañoDelProfilo;
	}

    public String getTamañoDelRaquis() {
		return tamañoDelRaquis;
	}

    public void setTamañoDelRaquis(String tamañoDelRaquis) {
        this.tamañoDelRaquis = tamañoDelRaquis;
	}

    public String getTamañoDeRaquilas() {
		return tamañoDeRaquilas;
	}

    public void setTamañoDeRaquilas(String tamañoDeRaquilas) {
        this.tamañoDeRaquilas = tamañoDeRaquilas;
	}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getInflorescenciaSolitaria() {
        return inflorescenciaSolitaria;
    }

    public void setInflorescenciaSolitaria(boolean inflorescenciaSolitaria) {
        this.inflorescenciaSolitaria = inflorescenciaSolitaria;
    }

    public int getNumeroDeLasBracteasPedunculares() {
        return numeroDeLasBracteasPedunculares;
    }

    public void setNumeroDeLasBracteasPedunculares(int numeroDeLasBracteasPedunculares) {
        this.numeroDeLasBracteasPedunculares = numeroDeLasBracteasPedunculares;
    }

    public int getNumeroDeRaquilas() {
        return numeroDeRaquilas;
    }

    public void setNumeroDeRaquilas(int numeroDeRaquilas) {
        this.numeroDeRaquilas = numeroDeRaquilas;
    }

    public Long getColorDeLaInflorescenciaEnFlorID() {
        return colorDeLaInflorescenciaEnFlorID;
    }

    public void setColorDeLaInflorescenciaEnFlorID(Long colorDeLaInflorescenciaEnFlorID) {
        this.colorDeLaInflorescenciaEnFlorID = colorDeLaInflorescenciaEnFlorID;
    }

    public Long getColorDeLaInflorescenciaEnFrutoID() {
        return colorDeLaInflorescenciaEnFrutoID;
    }

    public void setColorDeLaInflorescenciaEnFrutoID(Long colorDeLaInflorescenciaEnFrutoID) {
        this.colorDeLaInflorescenciaEnFrutoID = colorDeLaInflorescenciaEnFrutoID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDeLaInflorescenciaEnFlor() {
        Long __key = this.colorDeLaInflorescenciaEnFlorID;
        if (colorDeLaInflorescenciaEnFlor__resolvedKey == null || !colorDeLaInflorescenciaEnFlor__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDeLaInflorescenciaEnFlorNew = targetDao.load(__key);
            synchronized (this) {
                colorDeLaInflorescenciaEnFlor = colorDeLaInflorescenciaEnFlorNew;
            	colorDeLaInflorescenciaEnFlor__resolvedKey = __key;
            }
        }
        return colorDeLaInflorescenciaEnFlor;
    }

    public void setColorDeLaInflorescenciaEnFlor(ColorEspecimen colorDeLaInflorescenciaEnFlor) {
        synchronized (this) {
            this.colorDeLaInflorescenciaEnFlor = colorDeLaInflorescenciaEnFlor;
            colorDeLaInflorescenciaEnFlorID = colorDeLaInflorescenciaEnFlor == null ? null : colorDeLaInflorescenciaEnFlor.getId();
            colorDeLaInflorescenciaEnFlor__resolvedKey = colorDeLaInflorescenciaEnFlorID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDeLaInflorescenciaEnFruto() {
        Long __key = this.colorDeLaInflorescenciaEnFrutoID;
        if (colorDeLaInflorescenciaEnFruto__resolvedKey == null || !colorDeLaInflorescenciaEnFruto__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDeLaInflorescenciaEnFrutoNew = targetDao.load(__key);
            synchronized (this) {
                colorDeLaInflorescenciaEnFruto = colorDeLaInflorescenciaEnFrutoNew;
            	colorDeLaInflorescenciaEnFruto__resolvedKey = __key;
            }
        }
        return colorDeLaInflorescenciaEnFruto;
    }

    public void setColorDeLaInflorescenciaEnFruto(ColorEspecimen colorDeLaInflorescenciaEnFruto) {
        synchronized (this) {
            this.colorDeLaInflorescenciaEnFruto = colorDeLaInflorescenciaEnFruto;
            colorDeLaInflorescenciaEnFrutoID = colorDeLaInflorescenciaEnFruto == null ? null : colorDeLaInflorescenciaEnFruto.getId();
            colorDeLaInflorescenciaEnFruto__resolvedKey = colorDeLaInflorescenciaEnFrutoID;
        }
    }

}