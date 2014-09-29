package edu.udistrital.plantae.logicadominio.taxonomia;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class EpitetoEspecifico extends Taxon {

	public EpitetoEspecifico(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre
	 */
	public EpitetoEspecifico(String nombre){
        super(nombre);
	}

}