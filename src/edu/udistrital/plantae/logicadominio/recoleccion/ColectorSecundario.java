package edu.udistrital.plantae.logicadominio.recoleccion;

import edu.udistrital.plantae.logicadominio.autenticacion.Persona;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @updated 08-Oct-2013 3:41:26 PM
 */
public class ColectorSecundario extends Persona {

	private int colectorID;



	public void finalize() throws Throwable {
		super.finalize();
	}

	public ColectorSecundario(){

	}

	public int getcolectorID(){
		return colectorID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolectorID(int newVal){
		colectorID = newVal;
	}

}
