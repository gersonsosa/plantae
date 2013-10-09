package edu.udistrital.plantae.logicadominio.datosespecimen;

import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public interface Especimen {

	public void agregarTodosColectores();

	public String generarNumeroDeColeccion();

	/**
	 * 
	 * @param colectorSecundario
	 */
	public void quitarColector(ColectorSecundario colectorSecundario);

	/**
	 * 
	 * @param muestraAsociada
	 */
	public void agregarMuestraAsociada(MuestraAsociada muestraAsociada);

}
