package edu.udistrital.LogicaDominio.Listas;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:24 PM
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