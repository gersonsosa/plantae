package edu.udistrital.plantae.logicadominio.listasparametros;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import edu.udistrital.plantae.logicadominio.taxonomia.Uso;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:39 PM
 */
public class Usos implements Iterator {

    private Long id;
    private List<Uso> data;
    private Enumeration eu;
    private Uso nextUso;

	public Usos(){
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