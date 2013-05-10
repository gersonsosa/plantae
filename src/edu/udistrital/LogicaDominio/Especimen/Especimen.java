package edu.udistrital.LogicaDominio.Especimen;

import edu.udistrital.LogicaDominio.Recoleccion.Colector;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:21 PM
 */
public interface Especimen {

	public String generarNumeroDeColector();

	/**
	 * 
	 * @param colector
	 */
	public void quitarColector(Colector colector);

	public void agregarTodosColectores();

}