package edu.udistrital.botanicadroid.LogicaDominio.Listas;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import edu.udistrital.botanicadroid.LogicaDominio.Taxonomia.Uso;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:39 PM
 */
public class Usos implements Iterator {

	private ArrayList<Uso> data;
	private Enumeration eu;
	private Uso nextUso;
	private int usosID;

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

	public int getusosID(){
		return usosID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setusosID(int newVal){
		usosID = newVal;
	}

}