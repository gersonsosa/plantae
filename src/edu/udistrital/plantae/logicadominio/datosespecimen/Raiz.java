package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Raiz implements Parcelable {

    private Long id;
    private String diametroDeLasRaices;
    private String diametroEnLaBase;
    private String formaDeLasEspinas;
    private String tamañoDeLasEspinas;
    private String descripcion;
    private Boolean raizArmada;
    private Long alturaDelCono;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.diametroDeLasRaices);
        dest.writeString(this.diametroEnLaBase);
        dest.writeString(this.formaDeLasEspinas);
        dest.writeString(this.tamañoDeLasEspinas);
        dest.writeString(this.descripcion);
        dest.writeValue(this.raizArmada);
        dest.writeValue(this.alturaDelCono);
    }

    private Raiz(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.diametroDeLasRaices = in.readString();
        this.diametroEnLaBase = in.readString();
        this.formaDeLasEspinas = in.readString();
        this.tamañoDeLasEspinas = in.readString();
        this.descripcion = in.readString();
        this.raizArmada = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.alturaDelCono = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Raiz> CREATOR = new Parcelable.Creator<Raiz>() {
        public Raiz createFromParcel(Parcel source) {
            return new Raiz(source);
        }

        public Raiz[] newArray(int size) {
            return new Raiz[size];
        }
    };
}