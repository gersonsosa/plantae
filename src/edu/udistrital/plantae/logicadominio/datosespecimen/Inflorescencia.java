package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.InflorescenciaDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Inflorescencia implements Parcelable {

    private Long id;
    private ColorEspecimen colorDeLaInflorescenciaEnFlor;
    private ColorEspecimen colorDeLaInflorescenciaEnFruto;
    private String naturalezaDeLasBracteasPedunculares;
    private String naturalezaDelProfilo;
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
    private Boolean inflorescenciaSolitaria;
    private Integer numeroDeLasBracteasPedunculares;
    private Integer numeroDeRaquilas;
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

    public Boolean getInflorescenciaSolitaria() {
        return inflorescenciaSolitaria;
    }

    public void setInflorescenciaSolitaria(Boolean inflorescenciaSolitaria) {
        this.inflorescenciaSolitaria = inflorescenciaSolitaria;
    }

    public Integer getNumeroDeLasBracteasPedunculares() {
        return numeroDeLasBracteasPedunculares;
    }

    public void setNumeroDeLasBracteasPedunculares(Integer numeroDeLasBracteasPedunculares) {
        this.numeroDeLasBracteasPedunculares = numeroDeLasBracteasPedunculares;
    }

    public Integer getNumeroDeRaquilas() {
        return numeroDeRaquilas;
    }

    public void setNumeroDeRaquilas(Integer numeroDeRaquilas) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeParcelable(this.colorDeLaInflorescenciaEnFlor, 0);
        dest.writeParcelable(this.colorDeLaInflorescenciaEnFruto, 0);
        dest.writeByte(inflorescenciaSolitaria ? (byte) 1 : (byte) 0);
        dest.writeString(this.naturalezaDeLasBracteasPedunculares);
        dest.writeString(this.naturalezaDelProfilo);
        dest.writeInt(this.numeroDeLasBracteasPedunculares);
        dest.writeInt(this.numeroDeRaquilas);
        dest.writeString(this.posicionDeLasBracteasPedunculares);
        dest.writeString(this.posicionDeLasInflorescencias);
        dest.writeString(this.raquilas);
        dest.writeString(this.raquis);
        dest.writeString(this.tamañoDeLasBracteasPedunculares);
        dest.writeString(this.tamañoDelPedunculo);
        dest.writeString(this.tamañoDelProfilo);
        dest.writeString(this.tamañoDelRaquis);
        dest.writeString(this.tamañoDeRaquilas);
        dest.writeString(this.descripcion);
        dest.writeValue(this.colorDeLaInflorescenciaEnFlorID);
        dest.writeValue(this.colorDeLaInflorescenciaEnFrutoID);
        dest.writeValue(this.colorDeLaInflorescenciaEnFlor__resolvedKey);
        dest.writeValue(this.colorDeLaInflorescenciaEnFruto__resolvedKey);
    }

    private Inflorescencia(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaInflorescenciaEnFlor = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.colorDeLaInflorescenciaEnFruto = in.readParcelable(ColorEspecimen.class.getClassLoader());
        this.inflorescenciaSolitaria = in.readByte() != 0;
        this.naturalezaDeLasBracteasPedunculares = in.readString();
        this.naturalezaDelProfilo = in.readString();
        this.numeroDeLasBracteasPedunculares = in.readInt();
        this.numeroDeRaquilas = in.readInt();
        this.posicionDeLasBracteasPedunculares = in.readString();
        this.posicionDeLasInflorescencias = in.readString();
        this.raquilas = in.readString();
        this.raquis = in.readString();
        this.tamañoDeLasBracteasPedunculares = in.readString();
        this.tamañoDelPedunculo = in.readString();
        this.tamañoDelProfilo = in.readString();
        this.tamañoDelRaquis = in.readString();
        this.tamañoDeRaquilas = in.readString();
        this.descripcion = in.readString();
        this.colorDeLaInflorescenciaEnFlorID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaInflorescenciaEnFrutoID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaInflorescenciaEnFlor__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaInflorescenciaEnFruto__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Inflorescencia> CREATOR = new Parcelable.Creator<Inflorescencia>() {
        public Inflorescencia createFromParcel(Parcel source) {
            return new Inflorescencia(source);
        }

        public Inflorescencia[] newArray(int size) {
            return new Inflorescencia[size];
        }
    };
}