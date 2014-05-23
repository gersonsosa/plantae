package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Fotografia {

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

}
