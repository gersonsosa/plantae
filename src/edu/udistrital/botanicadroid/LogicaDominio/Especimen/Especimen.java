package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:56 p.m.
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