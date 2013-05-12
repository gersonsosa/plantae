package edu.udistrital.botanicadroid.LogicaDominio.Listas;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:58 p.m.
 */
public interface Visitador {

	/**
	 * 
	 * @param habitats
	 */
	public void visitar(Habitats habitats);

	/**
	 * 
	 * @param colores
	 */
	public void visitar(Colores colores);

	/**
	 * 
	 * @param habitos
	 */
	public void visitar(Habitos habitos);

}