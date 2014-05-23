package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class MuestraAsociada {

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

}
