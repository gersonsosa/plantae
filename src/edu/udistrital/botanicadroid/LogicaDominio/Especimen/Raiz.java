package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:58 p.m.
 */
public class Raiz {

	private long alturaDelCono;
	private String descripcion;
	private String diametroDeLasRaices;
	private String diametroEnLaBase;
	private String formaDeLasEspinas;
	private boolean raizArmada;
	private String tama�oDeLasEspinas;
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

	public String gettama�oDeLasEspinas(){
		return tama�oDeLasEspinas;
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
	public void settama�oDeLasEspinas(String newVal){
		tama�oDeLasEspinas = newVal;
	}

}