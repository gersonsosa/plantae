package edu.udistrital.LogicaDominio.Listas;
import edu.udistrital.LogicaDominio.Especimen.Color;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:20 PM
 */
public class Colores implements Iterator {

	private ArrayList<Color> data;
	private Enumeration ec;
	private Color nextColor;
	public Color m_Color;

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