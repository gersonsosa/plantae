package edu.udistrital.plantae.logicadominio.datosespecimen;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.HojaDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Hoja {

    private Long id;
    private String coberturaDelPeciolo;
    private String dispocicionDeLasPinnas;
    private String disposicionDeLasHojas;
    private String formaDelPeciolo;
    private String longuitudDelRaquis;
    private String naturalezaDeLaVaina;
    private String naturalezaDelLimbo;
    private String numeroDePinnas;
    private String numeroHojas;
    private String tamañoDeLaVaina;
    private String tamañoDelPeciolo;
    private String descripcion;
    private Long colorDeLaVainaID;
    private Long colorDelPecioloID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient HojaDao myDao;

    private ColorEspecimen colorDeLaVaina;
    private Long colorDeLaVaina__resolvedKey;

    private ColorEspecimen colorDelPeciolo;
    private Long colorDelPeciolo__resolvedKey;



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHojaDao() : null;
    }

	public void finalize() throws Throwable {

	}

	public Hoja(){

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

    public String getCoberturaDelPeciolo() {
        return coberturaDelPeciolo;
    }

    public void setCoberturaDelPeciolo(String coberturaDelPeciolo) {
        this.coberturaDelPeciolo = coberturaDelPeciolo;
    }

    public String getDispocicionDeLasPinnas() {
        return dispocicionDeLasPinnas;
    }

    public void setDispocicionDeLasPinnas(String dispocicionDeLasPinnas) {
        this.dispocicionDeLasPinnas = dispocicionDeLasPinnas;
    }

    public String getDisposicionDeLasHojas() {
        return disposicionDeLasHojas;
    }

    public void setDisposicionDeLasHojas(String disposicionDeLasHojas) {
        this.disposicionDeLasHojas = disposicionDeLasHojas;
    }

    public String getFormaDelPeciolo() {
        return formaDelPeciolo;
    }

    public void setFormaDelPeciolo(String formaDelPeciolo) {
        this.formaDelPeciolo = formaDelPeciolo;
    }

    public String getLonguitudDelRaquis() {
        return longuitudDelRaquis;
    }

    public void setLonguitudDelRaquis(String longuitudDelRaquis) {
        this.longuitudDelRaquis = longuitudDelRaquis;
    }

    public String getNaturalezaDeLaVaina() {
        return naturalezaDeLaVaina;
    }

    public void setNaturalezaDeLaVaina(String naturalezaDeLaVaina) {
        this.naturalezaDeLaVaina = naturalezaDeLaVaina;
    }

    public String getNaturalezaDelLimbo() {
        return naturalezaDelLimbo;
    }

    public void setNaturalezaDelLimbo(String naturalezaDelLimbo) {
        this.naturalezaDelLimbo = naturalezaDelLimbo;
    }

    public String getNumeroDePinnas() {
        return numeroDePinnas;
    }

    public void setNumeroDePinnas(String numeroDePinnas) {
        this.numeroDePinnas = numeroDePinnas;
    }

    public String getNumeroHojas() {
        return numeroHojas;
    }

    public void setNumeroHojas(String numeroHojas) {
        this.numeroHojas = numeroHojas;
    }

    public String getTamañoDeLaVaina() {
        return tamañoDeLaVaina;
    }

    public void setTamañoDeLaVaina(String tamañoDeLaVaina) {
        this.tamañoDeLaVaina = tamañoDeLaVaina;
    }

    public String getTamañoDelPeciolo() {
        return tamañoDelPeciolo;
    }

    public void setTamañoDelPeciolo(String tamañoDelPeciolo) {
        this.tamañoDelPeciolo = tamañoDelPeciolo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getColorDeLaVainaID() {
        return colorDeLaVainaID;
    }

    public void setColorDeLaVainaID(Long colorDeLaVainaID) {
        this.colorDeLaVainaID = colorDeLaVainaID;
    }

    public Long getColorDelPecioloID() {
        return colorDelPecioloID;
    }

    public void setColorDelPecioloID(Long colorDelPecioloID) {
        this.colorDelPecioloID = colorDelPecioloID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDeLaVaina() {
        Long __key = this.colorDeLaVainaID;
        if (colorDeLaVaina__resolvedKey == null || !colorDeLaVaina__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDeLaVainaNew = targetDao.load(__key);
            synchronized (this) {
                colorDeLaVaina = colorDeLaVainaNew;
            	colorDeLaVaina__resolvedKey = __key;
            }
        }
        return colorDeLaVaina;
    }

    public void setColorDeLaVaina(ColorEspecimen colorDeLaVaina) {
        synchronized (this) {
            this.colorDeLaVaina = colorDeLaVaina;
            colorDeLaVainaID = colorDeLaVaina == null ? null : colorDeLaVaina.getId();
            colorDeLaVaina__resolvedKey = colorDeLaVainaID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelPeciolo() {
        Long __key = this.colorDelPecioloID;
        if (colorDelPeciolo__resolvedKey == null || !colorDelPeciolo__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelPecioloNew = targetDao.load(__key);
            synchronized (this) {
                colorDelPeciolo = colorDelPecioloNew;
            	colorDelPeciolo__resolvedKey = __key;
            }
        }
        return colorDelPeciolo;
    }

    public void setColorDelPeciolo(ColorEspecimen colorDelPeciolo) {
        synchronized (this) {
            this.colorDelPeciolo = colorDelPeciolo;
            colorDelPecioloID = colorDelPeciolo == null ? null : colorDelPeciolo.getId();
            colorDelPeciolo__resolvedKey = colorDelPecioloID;
        }
    }

    public String aString() {
        String string = "";
        if (coberturaDelPeciolo != null) {
            string = string + (string.equals("") ? "Cobertura del peciolo: ":", Cobertura del peciolo: ") + coberturaDelPeciolo;
        }
        if (dispocicionDeLasPinnas != null) {
            string = string + (string.equals("") ? "Dispocicion de las pinnas: ":", Dispocicion de las pinnas: ") + dispocicionDeLasPinnas;
        }
        if (disposicionDeLasHojas != null) {
            string = string + (string.equals("") ? "Disposicion de las hojas: ":", Disposicion de las hojas: ") + disposicionDeLasHojas;
        }
        if (formaDelPeciolo != null) {
            string = string + (string.equals("") ? "Forma del peciolo: ":", Forma del peciolo: ") + formaDelPeciolo;
        }
        if (longuitudDelRaquis != null) {
            string = string + (string.equals("") ? "Longuitud del raquis: ":", Longuitud del raquis: ") + longuitudDelRaquis;
        }
        if (naturalezaDeLaVaina != null) {
            string = string + (string.equals("") ? "Naturaleza de la vaina: ":", Naturaleza de la vaina: ") + naturalezaDeLaVaina;
        }
        if (naturalezaDelLimbo != null) {
            string = string + (string.equals("") ? "Naturaleza del limbo: ":", Naturaleza del limbo: ") + naturalezaDelLimbo;
        }
        if (numeroDePinnas != null) {
            string = string + (string.equals("") ? "Numero de pinnas: ":", Numero de pinnas: ") + numeroDePinnas;
        }
        if (numeroHojas != null) {
            string = string + (string.equals("") ? "Numero hojas: ":", Numero hojas: ") + numeroHojas;
        }
        if (tamañoDeLaVaina != null) {
            string = string + (string.equals("") ? "Tamaño de la vaina: ":", Tamaño de la vaina: ") + tamañoDeLaVaina;
        }
        if (tamañoDelPeciolo != null) {
            string = string + (string.equals("") ? "Tamaño del peciolo: ":", Tamaño del peciolo: ") + tamañoDelPeciolo;
        }
        if (descripcion != null) {
            string = string + (string.equals("") ? "Descripcion: ":", Descripcion: ") + descripcion;
        }
        if (colorDeLaVaina != null) {
            string = string + (string.equals("") ? "Color de la vaina: ":", Color de la vaina: ") + colorDeLaVaina.aString();
        }
        if (colorDelPeciolo != null) {
            string = string + (string.equals("") ? "Color del peciolo: ":", Color del peciolo: ") + colorDelPeciolo.aString();
        }
        return string;
    }
}