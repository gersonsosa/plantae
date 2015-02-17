package edu.udistrital.plantae.logicadominio.listasparametros;

import edu.udistrital.plantae.logicadominio.datosespecimen.Habito;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Habitos implements Iterator {

    private Long id;
    private List<Habito> data;
    private Enumeration eh;
    private int nextHabito;

	public Habitos(){
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

	public void finalize() throws Throwable {
	}

	public boolean hasNext(){
		return false;
	}

	public Object next(){
		return null;
	}

	public void remove(){

	}
}