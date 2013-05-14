package edu.udistrital.botanicadroid.LogicaDominio.Taxonomia;

import java.util.ArrayList;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:13 a.m.
 */
public abstract class Taxon {

	private String nombre;
	private ArrayList usos;
	private ArrayList nombresComunes;

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