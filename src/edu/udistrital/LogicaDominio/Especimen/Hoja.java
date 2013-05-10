package edu.udistrital.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:22 PM
 */
public class Hoja {

	private String coberturaDelPeciolo;
	private Color colorDeLasHojas;
	private Color colorDelPeciolo;
	private String dispocicionDeLasPinnas;
	private String disposicionDeLasHojas;
	private String formaDelPeciolo;
	private String longuitudDelRaquis;
	private String naturalezaDeLaVaina;
	private String naturalezaDelLimbo;
	private String numeroDePinnas;
	private String numeroHojas;
	private String tamañoDeLasHojas;
	private String tamañoDelPeciolo;
	private String descripcion;
	public Color m_Color;



	public void finalize() throws Throwable {

	}

	public Hoja(){

	}

	public String getcoberturaDelPeciolo(){
		return coberturaDelPeciolo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcoberturaDelPeciolo(String newVal){
		coberturaDelPeciolo = newVal;
	}

	public Color getcolorDeLasHojas(){
		return colorDeLasHojas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLasHojas(Color newVal){
		colorDeLasHojas = newVal;
	}

	public Color getcolorDelPeciolo(){
		return colorDelPeciolo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelPeciolo(Color newVal){
		colorDelPeciolo = newVal;
	}

	public String getdescripcion(){
		return descripcion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcion(String newVal){
		descripcion = newVal;
	}

	public String getdispocicionDeLasPinnas(){
		return dispocicionDeLasPinnas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdispocicionDeLasPinnas(String newVal){
		dispocicionDeLasPinnas = newVal;
	}

	public String getdisposicionDeLasHojas(){
		return disposicionDeLasHojas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdisposicionDeLasHojas(String newVal){
		disposicionDeLasHojas = newVal;
	}

	public String getformaDelPeciolo(){
		return formaDelPeciolo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setformaDelPeciolo(String newVal){
		formaDelPeciolo = newVal;
	}

	public String getlonguitudDelRaquis(){
		return longuitudDelRaquis;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setlonguitudDelRaquis(String newVal){
		longuitudDelRaquis = newVal;
	}

	public String getnaturalezaDeLaVaina(){
		return naturalezaDeLaVaina;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnaturalezaDeLaVaina(String newVal){
		naturalezaDeLaVaina = newVal;
	}

	public String getnaturalezaDelLimbo(){
		return naturalezaDelLimbo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnaturalezaDelLimbo(String newVal){
		naturalezaDelLimbo = newVal;
	}

	public String getnumeroDePinnas(){
		return numeroDePinnas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnumeroDePinnas(String newVal){
		numeroDePinnas = newVal;
	}

	public String getnumeroHojas(){
		return numeroHojas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnumeroHojas(String newVal){
		numeroHojas = newVal;
	}

	public String gettamañoDeLasHojas(){
		return tamañoDeLasHojas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settamañoDeLasHojas(String newVal){
		tamañoDeLasHojas = newVal;
	}

	public String gettamañoDelPeciolo(){
		return tamañoDelPeciolo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settamañoDelPeciolo(String newVal){
		tamañoDelPeciolo = newVal;
	}

}