package edu.udistrital.botanicadroid.LogicaDominio.Listas;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import edu.udistrital.botanicadroid.LogicaDominio.Especimen.Habito;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Habitos implements Iterator {

	private ArrayList<Habito> data;
	private Enumeration eh;
	private int nextHabito;
	private int habitosID;

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

	public int gethabitosID(){
		return habitosID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethabitosID(int newVal){
		habitosID = newVal;
	}

}