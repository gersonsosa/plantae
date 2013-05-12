package edu.udistrital.botanicadroid.LogicaDominio.Listas;
import edu.udistrital.botanicadroid.LogicaDominio.java.util.Iterator;
import edu.udistrital.botanicadroid.LogicaDominio.Especimen.Habito;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:57 p.m.
 */
public class Habitos implements Iterator {

	private ArrayList<Habito> data;
	private Enumeration eh;
	private int nextHabito;
	public Habito m_Habito;

	public Habitos(){

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