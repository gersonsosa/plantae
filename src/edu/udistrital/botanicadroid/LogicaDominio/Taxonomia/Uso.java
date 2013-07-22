package edu.udistrital.botanicadroid.LogicaDominio.Taxonomia;

import edu.udistrital.botanicadroid.LogicaDominio.Ubicacion.Region;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:39 PM
 */
public class Uso {

	private Region region;
	private String descripcion;
	private int usoID;

	public Uso(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param nombre
	 */
	public Uso(String nombre){

	}

	public Region getregion(){
		return region;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setregion(Region newVal){
		region = newVal;
	}

	public String getdescripcion(){
		return descripcion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcion(String newVal){
		descripcion = newVal;
	}

	public int getusoID(){
		return usoID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setusoID(int newVal){
		usoID = newVal;
	}

}