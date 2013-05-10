package edu.udistrital.LogicaDominio.Listas;
import edu.udistrital.LogicaDominio.Especimen.Habitat;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:22 PM
 */
public class Habitats implements Iterator {

	private ArrayList<Habitat> data;
	private Enumeration eht;
	private Habitat nextHabitat;
	public Habitat m_Habitat;

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

}