package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Raiz {

    private Long id;
    private long alturaDelCono;
    private String diametroDeLasRaices;
    private String diametroEnLaBase;
    private String formaDeLasEspinas;
    private boolean raizArmada;
    private String tamañoDeLasEspinas;
    private String descripcion;



	public void finalize() throws Throwable {

	}

	public Raiz(){

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

    public String getDiametroDeLasRaices() {
		return diametroDeLasRaices;
	}

    public void setDiametroDeLasRaices(String diametroDeLasRaices) {
        this.diametroDeLasRaices = diametroDeLasRaices;
    }

    public String getDiametroEnLaBase() {
		return diametroEnLaBase;
	}

    public void setDiametroEnLaBase(String diametroEnLaBase) {
        this.diametroEnLaBase = diametroEnLaBase;
    }

    public String getFormaDeLasEspinas() {
		return formaDeLasEspinas;
	}

    public void setFormaDeLasEspinas(String formaDeLasEspinas) {
        this.formaDeLasEspinas = formaDeLasEspinas;
    }

    public String getTamañoDeLasEspinas() {
		return tamañoDeLasEspinas;
	}

    public void setTamañoDeLasEspinas(String tamañoDeLasEspinas) {
        this.tamañoDeLasEspinas = tamañoDeLasEspinas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getRaizArmada() {
		return raizArmada;
	}

    public void setRaizArmada(boolean raizArmada) {
        this.raizArmada = raizArmada;
    }

    public long getAlturaDelCono() {
        return alturaDelCono;
    }

    public void setAlturaDelCono(long alturaDelCono) {
        this.alturaDelCono = alturaDelCono;
	}

}