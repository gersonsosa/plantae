package edu.udistrital.botanicadroid.LogicaDominio.Ubicacion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
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
	private int localidadID;

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

	public String getmarcaDispositivo(){
		return marcaDispositivo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmarcaDispositivo(String newVal){
		marcaDispositivo = newVal;
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

	public int getlocalidadID(){
		return localidadID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setlocalidadID(int newVal){
		localidadID = newVal;
	}

}