package edu.udistrital.botanicadroid.LogicaDominio.Listas;
import edu.udistrital.botanicadroid.LogicaDominio.Especimen.Habitat;
import edu.udistrital.botanicadroid.LogicaDominio.java.util.Iterator;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:57 p.m.
 */
public class Habitats implements Iterator {

	private ArrayList<Habitat> data;
	private Enumeration eht;
	private Habitat nextHabitat;
	public Habitat m_Habitat;



	public void finalize() throws Throwable {

	}

	public Habitats(){

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