package edu.udistrital.LogicaDominio.Ubicacion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-May-2013 12:07:22 AM
 */
public class Pais extends Region {

	private Region departamentos;

	public Pais(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre
	 */
	public Pais(String nombre){

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