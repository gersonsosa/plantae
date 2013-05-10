package edu.udistrital.LogicaDominio.Ubicacion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:22 PM
 */
public class Localidad extends Region {

	private String nombre;
	private double latitud;
	private double longitud;
	private String datum;
	private double altitud;
	private String descripcion;
	private String marcaDispositivo;
	private Region region;

	public Localidad(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre
	 */
	public Localidad(String nombre){

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

	public double getlatitud(){
		return latitud;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setlatitud(double newVal){
		latitud = newVal;
	}

	public double getlongitud(){
		return longitud;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setlongitud(double newVal){
		longitud = newVal;
	}

	public String getdatum(){
		return datum;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdatum(String newVal){
		datum = newVal;
	}

	public double getaltitud(){
		return altitud;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setaltitud(double newVal){
		altitud = newVal;
	}

}