package edu.udistrital.botanicadroid.LogicaDominio.Listas;
import java.util.ArrayList;
import java.util.Enumeration;

import edu.udistrital.botanicadroid.LogicaDominio.Especimen.Color;
import java.util.Iterator;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Colores implements Iterator {

	private ArrayList<Color> data;
	private Enumeration ec;
	private Color nextColor;

	public Colores(){

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