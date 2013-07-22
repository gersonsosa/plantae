package edu.udistrital.botanicadroid.LogicaDominio.Taxonomia;
import edu.udistrital.botanicadroid.LogicaDominio.Ubicacion.Region;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class NombreComun {

	private String idioma;
	private Region region;
	private String nombre;
	private int nombreComunID;

	public NombreComun(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param nombre    nombre
	 */
	public NombreComun(String nombre){

	}

	public String getidioma(){
		return idioma;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidioma(String newVal){
		idioma = newVal;
	}

	public String getnombre(){
		return nombre;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
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

	public int getnombreComunID(){
		return nombreComunID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombreComunID(int newVal){
		nombreComunID = newVal;
	}

}