package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Fruto {

	private ColorEspecimen colorDelEndocarpio;
	private ColorEspecimen colorDelExcarpio;
	private String consistenciaDelPericarpio;
	private String descripcion;
	public ColorEspecimen m_Color;
	private int frutoID;



	public void finalize() throws Throwable {

	}

	public Fruto(){

	}

	public ColorEspecimen getcolorDelEndocarpio(){
		return colorDelEndocarpio;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelEndocarpio(ColorEspecimen newVal){
		colorDelEndocarpio = newVal;
	}

	public ColorEspecimen getcolorDelExcarpio(){
		return colorDelExcarpio;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelExcarpio(ColorEspecimen newVal){
		colorDelExcarpio = newVal;
	}

	public String getconsistenciaDelPericarpio(){
		return consistenciaDelPericarpio;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setconsistenciaDelPericarpio(String newVal){
		consistenciaDelPericarpio = newVal;
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

	public int getfrutoID(){
		return frutoID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfrutoID(int newVal){
		frutoID = newVal;
	}

}