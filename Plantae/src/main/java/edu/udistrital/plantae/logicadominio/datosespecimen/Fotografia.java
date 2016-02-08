package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Fotografia implements Parcelable {

    private Long id;
    private String rutaArchivo;
    private String contexto;
    private Long especimenID;



	public void finalize() throws Throwable {

	}

	public Fotografia(){

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

    public String getRutaArchivo() {
		return rutaArchivo;
	}

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
	}

    public String getContexto() {
		return contexto;
	}

    public void setContexto(String contexto) {
        this.contexto = contexto;
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
        dest.writeString(this.rutaArchivo);
        dest.writeString(this.contexto);
        dest.writeValue(this.especimenID);
    }

    private Fotografia(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.rutaArchivo = in.readString();
        this.contexto = in.readString();
        this.especimenID = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Fotografia> CREATOR = new Parcelable.Creator<Fotografia>() {
        public Fotografia createFromParcel(Parcel source) {
            return new Fotografia(source);
        }

        public Fotografia[] newArray(int size) {
            return new Fotografia[size];
        }
    };
}
