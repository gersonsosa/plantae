package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Raiz {

	private long alturaDelCono;
	private String diametroDeLasRaices;
	private String diametroEnLaBase;
	private String formaDeLasEspinas;
	private boolean raizArmada;
	private String tamañoDeLasEspinas;
	private String descripcion;
	public Color m_Color;



	public void finalize() throws Throwable {

	}

	public Raiz(){

	}

	public long getalturaDelCono(){
		return alturaDelCono;
	}

	public String getdescripcion(){
		return descripcion;
	}

	public String getdiametroDeLasRaices(){
		return diametroDeLasRaices;
	}

	public String getdiametroEnLaBase(){
		return diametroEnLaBase;
	}

	public String getformaDeLasEspinas(){
		return formaDeLasEspinas;
	}

	public String gettamañoDeLasEspinas(){
		return tamañoDeLasEspinas;
	}

	public boolean israizArmada(){
		return raizArmada;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setalturaDelCono(long newVal){
		alturaDelCono = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcion(String newVal){
		descripcion = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdiametroDeLasRaices(String newVal){
		diametroDeLasRaices = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdiametroEnLaBase(String newVal){
		diametroEnLaBase = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setformaDeLasEspinas(String newVal){
		formaDeLasEspinas = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setraizArmada(boolean newVal){
		raizArmada = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settamañoDeLasEspinas(String newVal){
		tamañoDeLasEspinas = newVal;
	}

}