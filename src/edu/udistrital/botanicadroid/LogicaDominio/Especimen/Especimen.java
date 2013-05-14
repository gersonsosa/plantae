package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Colector;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
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