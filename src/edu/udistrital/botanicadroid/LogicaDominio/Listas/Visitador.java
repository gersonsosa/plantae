package edu.udistrital.botanicadroid.LogicaDominio.Listas;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:39 PM
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

	/**
	 * 
	 * @param usos
	 */
	public void visitar(Usos usos);

}