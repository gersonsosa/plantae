package edu.udistrital.LogicaDominio.Taxonomia;

import java.util.ArrayList;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-May-2013 12:02:24 AM
 */
public abstract class Taxon {

	private String nombre;
	private ArrayList nombresComunes;
	private ArrayList usos;

	public Taxon(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param nombre
	 */
	public Taxon(String nombre){

	}

	/**
	 * 
	 * @param taxon
	 */
	public void agregarTaxon(Taxon taxon){

	}

	/**
	 * 
	 * @param taxonID
	 */
	public void getTaxonHijo(int taxonID){

	}

}