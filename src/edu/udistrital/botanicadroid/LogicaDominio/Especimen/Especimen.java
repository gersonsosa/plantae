package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Colector;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public interface Especimen {

	public String generarNumeroDeColector();

	public void agregarTodosColectores();

	/**
	 * 
	 * @param colector
	 */
	public void quitarColector(Colector colector);

	public void agregarMuestraAsociada();

}