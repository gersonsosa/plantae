package edu.udistrital.plantae.logicadominio.taxonomia;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Familia extends Taxon {

	private List<Genero> generos;

	public Familia(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre
	 */
	public Familia(String nombre){
        super(nombre);
	}

	public List<Genero> getGeneros(){
		return generos;
	}

	/**
	 * 
	 * @param generos
	 */
	public void setGeneros(List<Genero> generos){
		this.generos = generos;
	}

}