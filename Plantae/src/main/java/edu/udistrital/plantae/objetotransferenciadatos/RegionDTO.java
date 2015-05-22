package edu.udistrital.plantae.objetotransferenciadatos;

import android.os.Parcel;
import android.os.Parcelable;

import edu.udistrital.plantae.logicadominio.ubicacion.Departamento;
import edu.udistrital.plantae.logicadominio.ubicacion.Municipio;
import edu.udistrital.plantae.logicadominio.ubicacion.Pais;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;

/**
 * Created by hghar on 9/23/14.
 */
public class RegionDTO implements Parcelable {

    private Long id;
    private String municipio;
    private String departamento;
    private String pais;

    public RegionDTO(Region region) {
        this.id = region.getId();
        if (region instanceof Pais) {
            pais = region.getNombre();
        }else if (region instanceof Departamento) {
            departamento = region.getNombre();
            pais = region.getRegionPadre().getNombre();
        }else if (region instanceof Municipio) {
            municipio = region.getNombre();
            departamento = region.getRegionPadre().getNombre();
            pais = region.getRegionPadre().getRegionPadre().getNombre();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.municipio);
        dest.writeString(this.departamento);
        dest.writeString(this.pais);
    }

    public RegionDTO() {
    }

    private RegionDTO(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.municipio = in.readString();
        this.departamento = in.readString();
        this.pais = in.readString();
    }

    public static final Parcelable.Creator<RegionDTO> CREATOR = new Parcelable.Creator<RegionDTO>() {
        public RegionDTO createFromParcel(Parcel source) {
            return new RegionDTO(source);
        }

        public RegionDTO[] newArray(int size) {
            return new RegionDTO[size];
        }
    };
}
