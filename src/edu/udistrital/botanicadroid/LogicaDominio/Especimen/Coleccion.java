package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

import java.util.ArrayList;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:13 AM
 */
public class Coleccion {

	private String nombre;
	private ArrayList<Especimen> especimenes;
	private int coleccionID;

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

	public String getnombre(){
		return nombre;
	}

	public int getcoleccionID(){
		return coleccionID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcoleccionID(int newVal){
		coleccionID = newVal;
	}

}