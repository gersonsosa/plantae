package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Inflorescencia {

	private Color colorDeLaInflorecenciaEnFlor;
	private Color colorDeLaInflorecenciaEnFruto;
	private boolean inflorecenciaSolitaria;
	private String naturalezaDeLasBracteasPedunculares;
	private String naturalezaDelProfilo;
	private int numeroDeLasBracteasPedunculares;
	private int numeroDeRaquilas;
	private String posicionDeLasBracteasPedunculares;
	private String posicionDeLasInflorecencias;
	private String raquilas;
	private String raquis;
	private String tamañoDeLasBracteasPedunculares;
	private String tamañoDelPedunculo;
	private String tamañoDelProfilo;
	private String tamañoDelRaquis;
	private String tamañoDeRaquilas;
	private String descripcion;
	public Color m_Color;
	private int inflorecenciaID;



	public void finalize() throws Throwable {

	}

	public Inflorescencia(){

	}

	public Color getcolorDeLaInflorecenciaEnFlor(){
		return colorDeLaInflorecenciaEnFlor;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLaInflorecenciaEnFlor(Color newVal){
		colorDeLaInflorecenciaEnFlor = newVal;
	}

	public Color getcolorDeLaInflorecenciaEnFruto(){
		return colorDeLaInflorecenciaEnFruto;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDeLaInflorecenciaEnFruto(Color newVal){
		colorDeLaInflorecenciaEnFruto = newVal;
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

	public boolean isinflorecenciaSolitaria(){
		return inflorecenciaSolitaria;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setinflorecenciaSolitaria(boolean newVal){
		inflorecenciaSolitaria = newVal;
	}

	public String getnaturalezaDeLasBracteasPedunculares(){
		return naturalezaDeLasBracteasPedunculares;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnaturalezaDeLasBracteasPedunculares(String newVal){
		naturalezaDeLasBracteasPedunculares = newVal;
	}

	public String getnaturalezaDelProfilo(){
		return naturalezaDelProfilo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnaturalezaDelProfilo(String newVal){
		naturalezaDelProfilo = newVal;
	}

	public int getnumeroDeLasBracteasPedunculares(){
		return numeroDeLasBracteasPedunculares;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnumeroDeLasBracteasPedunculares(int newVal){
		numeroDeLasBracteasPedunculares = newVal;
	}

	public int getnumeroDeRaquilas(){
		return numeroDeRaquilas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnumeroDeRaquilas(int newVal){
		numeroDeRaquilas = newVal;
	}

	public String getposicionDeLasBracteasPedunculares(){
		return posicionDeLasBracteasPedunculares;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setposicionDeLasBracteasPedunculares(String newVal){
		posicionDeLasBracteasPedunculares = newVal;
	}

	public String getposicionDeLasInflorecencias(){
		return posicionDeLasInflorecencias;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setposicionDeLasInflorecencias(String newVal){
		posicionDeLasInflorecencias = newVal;
	}

	public String getraquilas(){
		return raquilas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setraquilas(String newVal){
		raquilas = newVal;
	}

	public String getraquis(){
		return raquis;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setraquis(String newVal){
		raquis = newVal;
	}

	public String gettamañoDeLasBracteasPedunculares(){
		return tamañoDeLasBracteasPedunculares;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settamañoDeLasBracteasPedunculares(String newVal){
		tamañoDeLasBracteasPedunculares = newVal;
	}

	public String gettamañoDelPedunculo(){
		return tamañoDelPedunculo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settamañoDelPedunculo(String newVal){
		tamañoDelPedunculo = newVal;
	}

	public String gettamañoDelProfilo(){
		return tamañoDelProfilo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settamañoDelProfilo(String newVal){
		tamañoDelProfilo = newVal;
	}

	public String gettamañoDelRaquis(){
		return tamañoDelRaquis;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settamañoDelRaquis(String newVal){
		tamañoDelRaquis = newVal;
	}

	public String gettamañoDeRaquilas(){
		return tamañoDeRaquilas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settamañoDeRaquilas(String newVal){
		tamañoDeRaquilas = newVal;
	}

	public int getinflorecenciaID(){
		return inflorecenciaID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setinflorecenciaID(int newVal){
		inflorecenciaID = newVal;
	}

}