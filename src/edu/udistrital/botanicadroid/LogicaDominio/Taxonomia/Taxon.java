package edu.udistrital.botanicadroid.LogicaDominio.Taxonomia;

import java.util.ArrayList;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public abstract class Taxon {

	private String nombre;
	private ArrayList usos;
	private ArrayList nombresComunes;
	private int taxonID;

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

	public int gettaxonID(){
		return taxonID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settaxonID(int newVal){
		taxonID = newVal;
	}

}