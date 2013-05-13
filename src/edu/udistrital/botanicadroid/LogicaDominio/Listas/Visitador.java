package edu.udistrital.botanicadroid.LogicaDominio.Listas;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:07 a.m.
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