package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Flor {

	private Color colorDeLaCorola;
	private Color colorDelCaliz;
	private Color colorDelGineceo;
	private Color colorDeLosEstambres;
	private Color colorDeLosEstigmas;
	private Color colorDeLosPistiliodios;
	private Color descripcion;
	public Color m_Color;



	public void finalize() throws Throwable {

	}

	public Flor(){

	}

	public Color getcolorDeLaCorola(){
		return colorDeLaCorola;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLaCorola(Color newVal){
		colorDeLaCorola = newVal;
	}

	public Color getcolorDelCaliz(){
		return colorDelCaliz;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelCaliz(Color newVal){
		colorDelCaliz = newVal;
	}

	public Color getcolorDelGineceo(){
		return colorDelGineceo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelGineceo(Color newVal){
		colorDelGineceo = newVal;
	}

	public Color getcolorDeLosEstambres(){
		return colorDeLosEstambres;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLosEstambres(Color newVal){
		colorDeLosEstambres = newVal;
	}

	public Color getcolorDeLosEstigmas(){
		return colorDeLosEstigmas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLosEstigmas(Color newVal){
		colorDeLosEstigmas = newVal;
	}

	public Color getcolorDeLosPistiliodios(){
		return colorDeLosPistiliodios;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLosPistiliodios(Color newVal){
		colorDeLosPistiliodios = newVal;
	}

	public Color getdescripcion(){
		return descripcion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcion(Color newVal){
		descripcion = newVal;
	}

}