package edu.udistrital.plantae.logicadominio.taxonomia;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Genero extends Taxon {

	private List<EpitetoEspecifico> especies;

	public Genero(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre
	 */
	public Genero(String nombre){

	}

	public List<EpitetoEspecifico> getEspecies(){
		return especies;
	}

	/**
	 * 
	 * @param especies
	 */
	public void setEspecies(List<EpitetoEspecifico> especies){
		this.especies = especies;
	}

}