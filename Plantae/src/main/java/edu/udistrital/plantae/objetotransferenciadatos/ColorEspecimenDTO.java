package edu.udistrital.plantae.objetotransferenciadatos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hghar on 2/16/15.
 */
public class ColorEspecimenDTO implements Parcelable {

    private Long id;
    private String nombre;
    private String descripcion;
    private String organoDeLaPlanta;
    private Integer colorRGB;
    private Long colorMunsellId;
    private Integer hue;
    private Integer value;
    private Integer chroma;

    public ColorEspecimenDTO(Long id, String nombre, String descripcion,
                             String organoDeLaPlanta, Integer colorRGB,
                             Long colorMunsellId, Integer hue,
                             Integer value, Integer chroma) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.organoDeLaPlanta = organoDeLaPlanta;
        this.colorRGB = colorRGB;
        this.colorMunsellId = colorMunsellId;
        this.hue = hue;
        this.value = value;
        this.chroma = chroma;
    }

    public ColorEspecimenDTO() {
    }

    public ColorEspecimenDTO(String nombre, String descripcion,
                             String organoDeLaPlanta, Integer colorRGB,
                             Integer hue, Integer value, Integer chroma) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.organoDeLaPlanta = organoDeLaPlanta;
        this.colorRGB = colorRGB;
        this.hue = hue;
        this.value = value;
        this.chroma = chroma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOrganoDeLaPlanta() {
        return organoDeLaPlanta;
    }

    public void setOrganoDeLaPlanta(String organoDeLaPlanta) {
        this.organoDeLaPlanta = organoDeLaPlanta;
    }

    public Integer getColorRGB() {
        return colorRGB;
    }

    public void setColorRGB(Integer colorRGB) {
        this.colorRGB = colorRGB;
    }

    public Long getColorMunsellId() {
        return colorMunsellId;
    }

    public void setColorMunsellId(Long colorMunsellId) {
        this.colorMunsellId = colorMunsellId;
    }

    public Integer getHue() {
        return hue;
    }

    public void setHue(Integer hue) {
        this.hue = hue;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getChroma() {
        return chroma;
    }

    public void setChroma(Integer chroma) {
        this.chroma = chroma;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.descripcion);
        dest.writeString(this.organoDeLaPlanta);
        dest.writeValue(this.colorRGB);
        dest.writeValue(this.colorMunsellId);
        dest.writeValue(this.hue);
        dest.writeValue(this.value);
        dest.writeValue(this.chroma);
    }

    private ColorEspecimenDTO(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.organoDeLaPlanta = in.readString();
        this.colorRGB = (Integer) in.readValue(Integer.class.getClassLoader());
        this.colorMunsellId = (Long) in.readValue(Long.class.getClassLoader());
        this.hue = (Integer) in.readValue(Integer.class.getClassLoader());
        this.value = (Integer) in.readValue(Integer.class.getClassLoader());
        this.chroma = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ColorEspecimenDTO> CREATOR = new Parcelable.Creator<ColorEspecimenDTO>() {
        public ColorEspecimenDTO createFromParcel(Parcel source) {
            return new ColorEspecimenDTO(source);
        }

        public ColorEspecimenDTO[] newArray(int size) {
            return new ColorEspecimenDTO[size];
        }
    };
}
