package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.HojaDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Hoja implements Parcelable {

    private Long id;
    private String coberturaDelPeciolo;
    private ColorEspecimen colorDeLasHojas;
    private ColorEspecimen colorDelPeciolo;
    private String dispocicionDeLasPinnas;
    private String disposicionDeLasHojas;
    private String formaDelPeciolo;
    private String longuitudDelRaquis;
    private String naturalezaDeLaVaina;
    private String naturalezaDelLimbo;
    private String numeroDePinnas;
    private String numeroHojas;
    private String tamañoDeLasHojas;
    private String tamañoDelPeciolo;
    private String descripcion;
    private Long colorDeLasHojasID;
    private Long colorDelPecioloID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient HojaDao myDao;

    private Long colorDeLasHojas__resolvedKey;
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

    public String getTamañoDeLasHojas() {
        return tamañoDeLasHojas;
    }

    public void setTamañoDeLasHojas(String tamañoDeLasHojas) {
        this.tamañoDeLasHojas = tamañoDeLasHojas;
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

    public Long getColorDeLasHojasID() {
        return colorDeLasHojasID;
    }

    public void setColorDeLasHojasID(Long colorDeLasHojasID) {
        this.colorDeLasHojasID = colorDeLasHojasID;
    }

    public Long getColorDelPecioloID() {
        return colorDelPecioloID;
    }

    public void setColorDelPecioloID(Long colorDelPecioloID) {
        this.colorDelPecioloID = colorDelPecioloID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorEspecimen getColorDeLasHojas() {
        Long __key = this.colorDeLasHojasID;
        if (colorDeLasHojas__resolvedKey == null || !colorDeLasHojas__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            ColorEspecimen colorDeLasHojasNew = targetDao.load(__key);
            synchronized (this) {
                colorDeLasHojas = colorDeLasHojasNew;
            	colorDeLasHojas__resolvedKey = __key;
            }
        }
		return colorDeLasHojas;
	}

    public void setColorDeLasHojas(ColorEspecimen colorDeLasHojas) {
        synchronized (this) {
            this.colorDeLasHojas = colorDeLasHojas;
            colorDeLasHojasID = colorDeLasHojas == null ? null : colorDeLasHojas.getId();
            colorDeLasHojas__resolvedKey = colorDeLasHojasID;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.coberturaDelPeciolo);
        dest.writeParcelable(this.colorDeLasHojas, 0);
        dest.writeParcelable(this.colorDelPeciolo, 0);
        dest.writeString(this.dispocicionDeLasPinnas);
        dest.writeString(this.disposicionDeLasHojas);
        dest.writeString(this.formaDelPeciolo);
        dest.writeString(this.longuitudDelRaquis);
        dest.writeString(this.naturalezaDeLaVaina);
        dest.writeString(this.naturalezaDelLimbo);
        dest.writeString(this.numeroDePinnas);
        dest.writeString(this.numeroHojas);
        dest.writeString(this.tamañoDeLasHojas);
        dest.writeString(this.tamañoDelPeciolo);
        dest.writeString(this.descripcion);
        dest.writeValue(this.colorDeLasHojasID);
        dest.writeValue(this.colorDelPecioloID);
        dest.writeValue(this.colorDeLasHojas__resolvedKey);
        dest.writeValue(this.colorDelPeciolo__resolvedKey);
    }

    private Hoja(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.coberturaDelPeciolo = in.readString();
        this.colorDeLasHojas = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.colorDelPeciolo = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.dispocicionDeLasPinnas = in.readString();
        this.disposicionDeLasHojas = in.readString();
        this.formaDelPeciolo = in.readString();
        this.longuitudDelRaquis = in.readString();
        this.naturalezaDeLaVaina = in.readString();
        this.naturalezaDelLimbo = in.readString();
        this.numeroDePinnas = in.readString();
        this.numeroHojas = in.readString();
        this.tamañoDeLasHojas = in.readString();
        this.tamañoDelPeciolo = in.readString();
        this.descripcion = in.readString();
        this.colorDeLasHojasID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelPecioloID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLasHojas__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelPeciolo__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Hoja> CREATOR = new Parcelable.Creator<Hoja>() {
        public Hoja createFromParcel(Parcel source) {
            return new Hoja(source);
        }

        public Hoja[] newArray(int size) {
            return new Hoja[size];
        }
    };
}