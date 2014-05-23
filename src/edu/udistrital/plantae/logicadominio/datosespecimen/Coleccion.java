package edu.udistrital.plantae.logicadominio.datosespecimen;

import java.util.ArrayList;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:13 AM
 */
public class Coleccion {

	private String nombre;
	private ArrayList<Especimen> especimenes;
	private int id;

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

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId(){
		return id;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setId(int newVal){
		id = newVal;
	}

}