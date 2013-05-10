package edu.udistrital.LogicaDominio.Listas;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import edu.udistrital.LogicaDominio.Especimen.Habito;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:22 PM
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