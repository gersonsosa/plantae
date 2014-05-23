package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Habito {

    private Long id;
    private String habito;
    private Long habitosID;

	public Habito(){

	}

	public void finalize() throws Throwable {

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

    public String getHabito() {
		return habito;
	}

    public void setHabito(String habito) {
        this.habito = habito;
    }

    public Long getHabitosID() {
        return habitosID;
    }

    public void setHabitosID(Long habitosID) {
        this.habitosID = habitosID;
	}

}