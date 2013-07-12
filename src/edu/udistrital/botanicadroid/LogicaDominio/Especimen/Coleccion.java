package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

import java.util.ArrayList;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:13 AM
 */
public class Coleccion {

	private String nombre;
	private ArrayList especimenes;

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param nombre
	 */
	public Coleccion(String nombre){

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}