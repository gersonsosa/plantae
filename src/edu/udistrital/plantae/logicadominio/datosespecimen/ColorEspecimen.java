package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.graphics.Color;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @updated 08-Oct-2013 5:27:38 PM
 */
public class ColorEspecimen {

	private int colorEspecimenID;
	private String nombre;
	private String descripcion;
	private Color colorRGB;
	private ColorMunsell colorMunsell;



	public void finalize() throws Throwable {

	}

	public ColorEspecimen(){

	}

	public String getnombre(){
		return nombre;
	}

	public Color getcolorRGB(){
		return colorRGB;
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
	public void setcolorRGB(Color newVal){
		colorRGB = newVal;
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

	public int getcolorID(){
		return colorEspecimenID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorID(int newVal){
		colorEspecimenID = newVal;
	}

	public ColorMunsell getcolorMunsell(){
		return colorMunsell;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorMunsell(ColorMunsell newVal){
		colorMunsell = newVal;
	}

}