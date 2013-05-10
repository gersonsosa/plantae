package edu.udistrital.LogicaDominio.Listas;
import java.util.ArrayList;
import java.util.Enumeration;

import edu.udistrital.LogicaDominio.Taxonomia.Uso;
import java.util.Iterator;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:24 PM
 */
public class Usos implements Iterator {

	private ArrayList<Uso> data;
	private Enumeration eu;
	private Uso nextUso;
	public Uso m_Uso;

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