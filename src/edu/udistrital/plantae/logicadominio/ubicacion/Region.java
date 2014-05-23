package edu.udistrital.plantae.logicadominio.ubicacion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public abstract class Region {

    private Long id;
    private String nombre;
    private String nombreCompleto;
    private Region regionPadre;

	public Region(){
	}

	public void finalize() throws Throwable {
	}

    public Region getRegionPadre() {
        return regionPadre;
    }

    public void setRegionPadre(Region regionPadre) {
        this.regionPadre = regionPadre;
    }

    /**
     *
     * @param nombre
     */
    public Region(String nombre){
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

	public String getNombre(){
		return nombre;
	}

	/**
	 *
	 * @param nombre
	 */
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

}