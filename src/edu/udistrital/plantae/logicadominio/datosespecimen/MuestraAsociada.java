package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class MuestraAsociada implements Parcelable {

    private Long id;
    private String descripcion;
    private String metodoDeTratamiento;
    private Long especimenID;



	public void finalize() throws Throwable {
	}

	public MuestraAsociada(){
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMetodoDeTratamiento() {
		return metodoDeTratamiento;
	}

    public void setMetodoDeTratamiento(String metodoDeTratamiento) {
        this.metodoDeTratamiento = metodoDeTratamiento;
    }

    public Long getEspecimenID() {
        return especimenID;
    }

    public void setEspecimenID(Long especimenID) {
        this.especimenID = especimenID;
	}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.descripcion);
        dest.writeString(this.metodoDeTratamiento);
        dest.writeValue(this.especimenID);
    }

    private MuestraAsociada(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.descripcion = in.readString();
        this.metodoDeTratamiento = in.readString();
        this.especimenID = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<MuestraAsociada> CREATOR = new Parcelable.Creator<MuestraAsociada>() {
        public MuestraAsociada createFromParcel(Parcel source) {
            return new MuestraAsociada(source);
        }

        public MuestraAsociada[] newArray(int size) {
            return new MuestraAsociada[size];
        }
    };
}
