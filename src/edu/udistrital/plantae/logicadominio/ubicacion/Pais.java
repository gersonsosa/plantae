package edu.udistrital.plantae.logicadominio.ubicacion;

import java.util.ArrayList;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Pais extends Region {

	private ArrayList<Departamento> departamentos;

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