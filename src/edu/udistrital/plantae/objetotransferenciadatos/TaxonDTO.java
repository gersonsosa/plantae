package edu.udistrital.plantae.objetotransferenciadatos;

import android.os.Parcel;
import android.os.Parcelable;
import edu.udistrital.plantae.logicadominio.taxonomia.*;

import java.util.List;

/**
 * Created by hghar on 9/29/14.
 */
public class TaxonDTO implements Parcelable {

    private Long id;
    private String especie;
    private String genero;
    private String familia;
    private String autor;
    private String nombreCientifico;
    private List<Uso> usos;
    private List<NombreComun> nombresComunes;

    public TaxonDTO() {
    }

    public TaxonDTO(Taxon taxon) {
        this.id = taxon.getId();
        if (taxon instanceof Familia) {
            familia = taxon.getNombre();
            autor = taxon.getAutor();
            nombreCientifico = familia;
            usos = taxon.getUsos();
            nombresComunes = taxon.getNombresComunes();
        } else if (taxon instanceof Genero) {
            genero = taxon.getNombre();
            familia = taxon.getTaxonPadre().getNombre();
            nombreCientifico = genero;
            autor = taxon.getAutor();
            usos = taxon.getUsos();
            nombresComunes = taxon.getNombresComunes();
        }else if (taxon instanceof EpitetoEspecifico) {
            especie = taxon.getNombre();
            genero = taxon.getTaxonPadre().getNombre();
            familia = taxon.getTaxonPadre().getTaxonPadre().getNombre();
            nombreCientifico = genero.concat(" ").concat(especie);
            autor = taxon.getAutor();
            usos = taxon.getUsos();
            nombresComunes = taxon.getNombresComunes();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public List<Uso> getUsos() {
        return usos;
    }

    public void setUsos(List<Uso> usos) {
        this.usos = usos;
    }

    public List<NombreComun> getNombresComunes() {
        return nombresComunes;
    }

    public void setNombresComunes(List<NombreComun> nombresComunes) {
        this.nombresComunes = nombresComunes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.especie);
        dest.writeString(this.genero);
        dest.writeString(this.familia);
        dest.writeString(this.autor);
        dest.writeString(this.nombreCientifico);
        dest.writeTypedList(usos);
        dest.writeTypedList(nombresComunes);
    }

    private TaxonDTO(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.especie = in.readString();
        this.genero = in.readString();
        this.familia = in.readString();
        this.autor = in.readString();
        this.nombreCientifico = in.readString();
        in.readTypedList(usos, Uso.CREATOR);
        in.readTypedList(nombresComunes, NombreComun.CREATOR);
    }

    public static final Parcelable.Creator<TaxonDTO> CREATOR = new Parcelable.Creator<TaxonDTO>() {
        public TaxonDTO createFromParcel(Parcel source) {
            return new TaxonDTO(source);
        }

        public TaxonDTO[] newArray(int size) {
            return new TaxonDTO[size];
        }
    };
}
