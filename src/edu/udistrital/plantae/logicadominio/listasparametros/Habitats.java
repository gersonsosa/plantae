package edu.udistrital.plantae.logicadominio.listasparametros;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import edu.udistrital.plantae.logicadominio.datosespecimen.Habitat;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Habitats implements Iterator {

	private ArrayList<Habitat> data;
	private Enumeration eht;
	private Habitat nextHabitat;
	private int habitatsID;

	public Habitats(){

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

	public int gethabitatsID(){
		return habitatsID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethabitatsID(int newVal){
		habitatsID = newVal;
	}

}