package edu.udistrital.plantae.objetotransferenciadatos;

import java.util.Date;

/**
 * Created by hghar on 8/25/14.
 */
public class IdentidadTaxonomicaDTO {

    private Date fechaIdentificacion;
    private String tipo;
    private Long especimenID;
    private long taxonID;
    private Long personaID;

    public Date getFechaIdentificacion() {
        return fechaIdentificacion;
    }

    public void setFechaIdentificacion(Date fechaIdentificacion) {
        this.fechaIdentificacion = fechaIdentificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getEspecimenID() {
        return especimenID;
    }

    public void setEspecimenID(Long especimenID) {
        this.especimenID = especimenID;
    }

    public long getTaxonID() {
        return taxonID;
    }

    public void setTaxonID(long taxonID) {
        this.taxonID = taxonID;
    }

    public Long getPersonaID() {
        return personaID;
    }

    public void setPersonaID(Long personaID) {
        this.personaID = personaID;
    }
}
