package edu.udistrital.botanicadroid.LogicaDominio.Listas;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import edu.udistrital.botanicadroid.LogicaDominio.Especimen.Habito;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:07 a.m.
 */
public class Habitos implements Iterator {

	private ArrayList<Habito> data;
	private Enumeration eh;
	private int nextHabito;

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