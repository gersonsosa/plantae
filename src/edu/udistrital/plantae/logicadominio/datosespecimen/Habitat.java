package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Habitat {

    private Long id;
    private String especiesAsociadas;
    private String sueloSustrato;
    private String vegetacion;
    private Long habitatsID;



	public void finalize() throws Throwable {

	}

	public Habitat(){

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

    public String getEspeciesAsociadas() {
		return especiesAsociadas;
	}

    public void setEspeciesAsociadas(String especiesAsociadas) {
        this.especiesAsociadas = especiesAsociadas;
	}

    public String getSueloSustrato() {
		return sueloSustrato;
	}

    public void setSueloSustrato(String sueloSustrato) {
        this.sueloSustrato = sueloSustrato;
	}

    public String getVegetacion() {
		return vegetacion;
	}

    public void setVegetacion(String vegetacion) {
        this.vegetacion = vegetacion;
    }

    public Long getHabitatsID() {
        return habitatsID;
    }

    public void setHabitatsID(Long habitatsID) {
        this.habitatsID = habitatsID;
	}

}