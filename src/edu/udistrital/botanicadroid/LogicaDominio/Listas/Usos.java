package edu.udistrital.botanicadroid.LogicaDominio.Listas;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import edu.udistrital.botanicadroid.LogicaDominio.Taxonomia.Uso;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:07 a.m.
 */
public class Usos implements Iterator {

	private ArrayList<Uso> data;
	private Enumeration eu;
	private Uso nextUso;

	public Usos(){

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