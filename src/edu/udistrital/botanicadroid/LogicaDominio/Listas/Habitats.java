package edu.udistrital.botanicadroid.LogicaDominio.Listas;
import edu.udistrital.botanicadroid.LogicaDominio.Especimen.Habitat;
import edu.udistrital.botanicadroid.LogicaDominio.java.util.Iterator;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:07 a.m.
 */
public class Habitats implements Iterator {

	private ArrayList<Habitat> data;
	private Enumeration eht;
	private Habitat nextHabitat;

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