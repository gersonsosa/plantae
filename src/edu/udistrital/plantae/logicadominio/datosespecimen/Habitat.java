package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Habitat implements Parcelable {

    private Long id;
    private String especiesAsociadas;
    private String sueloSustrato;
    private String vegetacion;

	public void finalize() throws Throwable {

	}

    public Habitat(String especiesAsociadas, String sueloSustrato, String vegetacion) {
        this.especiesAsociadas = especiesAsociadas;
        this.sueloSustrato = sueloSustrato;
        this.vegetacion = vegetacion;
    }

    public Habitat(){

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

    public String getEspeciesAsociadas() {
		return especiesAsociadas;
	}

    public void setEspeciesAsociadas(String especiesAsociadas) {
        this.especiesAsociadas = especiesAsociadas;
	}

    public String getSueloSustrato() {
		return sueloSustrato;
	}

    public void setSueloSustrato(String sueloSustrato) {
        this.sueloSustrato = sueloSustrato;
	}

    public String getVegetacion() {
		return vegetacion;
	}

    public void setVegetacion(String vegetacion) {
        this.vegetacion = vegetacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.especiesAsociadas);
        dest.writeString(this.sueloSustrato);
        dest.writeString(this.vegetacion);
    }

    private Habitat(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.especiesAsociadas = in.readString();
        this.sueloSustrato = in.readString();
        this.vegetacion = in.readString();
    }

    public static final Parcelable.Creator<Habitat> CREATOR = new Parcelable.Creator<Habitat>() {
        public Habitat createFromParcel(Parcel source) {
            return new Habitat(source);
        }

        public Habitat[] newArray(int size) {
            return new Habitat[size];
        }
    };
}