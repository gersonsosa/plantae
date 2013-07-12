package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:13 AM
 */
public class Color {

	private String nombre;
	private String descripcion;
	private Color colorRGB;
	private ColorMunsell colorMunsell;



	public void finalize() throws Throwable {

	}

	public Color(){

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

}