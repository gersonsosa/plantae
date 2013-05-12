package edu.udistrital.botanicadroid.LogicaDominio.Listas;
import edu.udistrital.botanicadroid.LogicaDominio.Especimen.Color;
import edu.udistrital.botanicadroid.LogicaDominio.java.util.Iterator;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:56 p.m.
 */
public class Colores implements Iterator {

	private ArrayList<Color> data;
	private Enumeration ec;
	private Color nextColor;
	public Color m_Color;

	public Colores(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

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