package edu.udistrital.plantae.logicadominio.listasparametros;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import android.graphics.Color;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Colores implements Iterator {

	private ArrayList<Color> data;
	private Enumeration ec;
	private Color nextColor;
	private int coloresID;

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

	public int getcoloresID(){
		return coloresID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcoloresID(int newVal){
		coloresID = newVal;
	}

}