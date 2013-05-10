package edu.udistrital.LogicaDominio.Ubicacion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:23 PM
 */
public abstract class Region {

	private String nombre;

	public Region(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param nombre
	 */
	public Region(String nombre){

	}

	/**
	 * 
	 * @param region
	 */
	public void agregarRegion(Region region){

	}

	/**
	 * 
	 * @param region
	 */
	public Region getRegionHijo(int region){
		return null;
	}

}