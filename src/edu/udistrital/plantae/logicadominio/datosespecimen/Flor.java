package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Flor {

	private ColorEspecimen colorDeLaCorola;
	private ColorEspecimen colorDelCaliz;
	private ColorEspecimen colorDelGineceo;
	private ColorEspecimen colorDeLosEstambres;
	private ColorEspecimen colorDeLosEstigmas;
	private ColorEspecimen colorDeLosPistiliodios;
	private ColorEspecimen descripcion;
	private int florID;



	public void finalize() throws Throwable {

	}

	public Flor(){

	}

	public ColorEspecimen getcolorDeLaCorola(){
		return colorDeLaCorola;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLaCorola(ColorEspecimen newVal){
		colorDeLaCorola = newVal;
	}

	public ColorEspecimen getcolorDelCaliz(){
		return colorDelCaliz;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelCaliz(ColorEspecimen newVal){
		colorDelCaliz = newVal;
	}

	public ColorEspecimen getcolorDelGineceo(){
		return colorDelGineceo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelGineceo(ColorEspecimen newVal){
		colorDelGineceo = newVal;
	}

	public ColorEspecimen getcolorDeLosEstambres(){
		return colorDeLosEstambres;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLosEstambres(ColorEspecimen newVal){
		colorDeLosEstambres = newVal;
	}

	public ColorEspecimen getcolorDeLosEstigmas(){
		return colorDeLosEstigmas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLosEstigmas(ColorEspecimen newVal){
		colorDeLosEstigmas = newVal;
	}

	public ColorEspecimen getcolorDeLosPistiliodios(){
		return colorDeLosPistiliodios;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLosPistiliodios(ColorEspecimen newVal){
		colorDeLosPistiliodios = newVal;
	}

	public ColorEspecimen getdescripcion(){
		return descripcion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcion(ColorEspecimen newVal){
		descripcion = newVal;
	}

	public int getflorID(){
		return florID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setflorID(int newVal){
		florID = newVal;
	}

}