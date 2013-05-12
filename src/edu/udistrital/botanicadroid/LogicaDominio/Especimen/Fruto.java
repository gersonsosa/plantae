package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:57 p.m.
 */
public class Fruto {

	private Color colorDelEndocarpio;
	private Color colorDelExcarpio;
	private String consistenciaDelPericarpio;
	private String descripcion;
	public Color m_Color;



	public void finalize() throws Throwable {

	}

	public Fruto(){

	}

	public Color getcolorDelEndocarpio(){
		return colorDelEndocarpio;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelEndocarpio(Color newVal){
		colorDelEndocarpio = newVal;
	}

	public Color getcolorDelExcarpio(){
		return colorDelExcarpio;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelExcarpio(Color newVal){
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

}