package edu.udistrital.botanicadroid.LogicaDominio.Ubicacion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:57 p.m.
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
	 * @param nombre    nombre
	 */
	public Localidad(String nombre){

	}

	public String getnombre(){
		return nombre;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

	public double getlatitud(){
		return latitud;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void setlatitud(double newVal){
		latitud = newVal;
	}

	public double getlongitud(){
		return longitud;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void setlongitud(double newVal){
		longitud = newVal;
	}

	public String getdatum(){
		return datum;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void setdatum(String newVal){
		datum = newVal;
	}

	public double getaltitud(){
		return altitud;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void setaltitud(double newVal){
		altitud = newVal;
	}

}