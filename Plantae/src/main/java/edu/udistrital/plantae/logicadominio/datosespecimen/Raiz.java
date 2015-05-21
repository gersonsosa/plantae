package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.RaizDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Raiz {

    private Long id;
    private String diametroDeLasRaices;
    private String diametroEnLaBase;
    private String formaDeLasEspinas;
    private String tamañoDeLasEspinas;
    private String descripcion;
    private Boolean raizArmada;
    private Long alturaDelCono;
    private Long colorDelConoID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient RaizDao myDao;

    private ColorEspecimen colorDelCono;
    private Long colorDelCono__resolvedKey;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRaizDao() : null;
    }

	public void finalize() throws Throwable {

	}

	public Raiz(){

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

    public String getDiametroDeLasRaices() {
        return diametroDeLasRaices;
    }

    public void setDiametroDeLasRaices(String diametroDeLasRaices) {
        this.diametroDeLasRaices = diametroDeLasRaices;
    }

    public String getDiametroEnLaBase() {
        return diametroEnLaBase;
    }

    public void setDiametroEnLaBase(String diametroEnLaBase) {
        this.diametroEnLaBase = diametroEnLaBase;
    }

    public String getFormaDeLasEspinas() {
        return formaDeLasEspinas;
    }

    public void setFormaDeLasEspinas(String formaDeLasEspinas) {
        this.formaDeLasEspinas = formaDeLasEspinas;
    }

    public String getTamañoDeLasEspinas() {
        return tamañoDeLasEspinas;
    }

    public void setTamañoDeLasEspinas(String tamañoDeLasEspinas) {
        this.tamañoDeLasEspinas = tamañoDeLasEspinas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getRaizArmada() {
        return raizArmada;
    }

    public void setRaizArmada(Boolean raizArmada) {
        this.raizArmada = raizArmada;
    }

    public Long getAlturaDelCono() {
        return alturaDelCono;
    }

    public void setAlturaDelCono(Long alturaDelCono) {
        this.alturaDelCono = alturaDelCono;
    }

    public Long getColorDelConoID() {
        return colorDelConoID;
    }

    public void setColorDelConoID(Long colorDelConoID) {
        this.colorDelConoID = colorDelConoID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDelCono() {
        Long __key = this.colorDelConoID;
        if (colorDelCono__resolvedKey == null || !colorDelCono__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDelConoNew = targetDao.load(__key);
            synchronized (this) {
                colorDelCono = colorDelConoNew;
            	colorDelCono__resolvedKey = __key;
            }
        }
        return colorDelCono;
    }

    public void setColorDelCono(ColorEspecimen colorDelCono) {
        synchronized (this) {
            this.colorDelCono = colorDelCono;
            colorDelConoID = colorDelCono == null ? null : colorDelCono.getId();
            colorDelCono__resolvedKey = colorDelConoID;
        }
    }

    public String aString() {
        String string = "";
        if (diametroDeLasRaices != null) {
            string = string + (string.equals("") ? "Diametro de las raices: ":", Diametro de las raices:") + diametroDeLasRaices;
        }
        if (diametroEnLaBase != null) {
            string = string + (string.equals("") ? "Diametro en la base: ":", Diametro en la base:") + diametroEnLaBase;
        }
        if (formaDeLasEspinas != null) {
            string = string + (string.equals("") ? "Forma de las espinas: ":", Forma de las espinas:") + formaDeLasEspinas;
        }
        if (tamañoDeLasEspinas != null) {
            string = string + (string.equals("") ? "Tamaño de las espinas: ":", Tamaño de las espinas:") + tamañoDeLasEspinas;
        }
        if (descripcion != null) {
            string = string + (string.equals("") ? "Descripcion: ":", Descripcion:") + descripcion;
        }
        if (raizArmada != null) {
            string = string + (string.equals("") ? "Raiz armada: ":", Raiz armada:") + raizArmada;
        }
        if (alturaDelCono != null) {
            string = string + (string.equals("") ? "Altura del cono: ":", Altura del cono:") + alturaDelCono;
        }
        if (colorDelCono != null) {
            string = string + (string.equals("") ? "Color del cono: ":", Color del cono:") + colorDelCono;
        }
        return string;
    }
}