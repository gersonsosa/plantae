package edu.udistrital.plantae.objetotransferenciadatos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hghar on 8/25/14.
 */
public class ColectorSecundarioDTO implements Parcelable {

    private String apellido;
    private String nombre;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.apellido);
        dest.writeString(this.nombre);
    }

    public ColectorSecundarioDTO() {
    }

    private ColectorSecundarioDTO(Parcel in) {
        this.apellido = in.readString();
        this.nombre = in.readString();
    }

    public static final Parcelable.Creator<ColectorSecundarioDTO> CREATOR = new Parcelable.Creator<ColectorSecundarioDTO>() {
        public ColectorSecundarioDTO createFromParcel(Parcel source) {
            return new ColectorSecundarioDTO(source);
        }

        public ColectorSecundarioDTO[] newArray(int size) {
            return new ColectorSecundarioDTO[size];
        }
    };
}
