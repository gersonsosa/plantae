package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class Tallo {

	private String alturaDelTallo;
	private Color colorDelTallo;
	private boolean desnudoCubierto;
	private String diametroDelTallo;
	private String disposicionDeLasEspinas;
	private boolean entrenudosConspicuos;
	private boolean espinas;
	private String formaDelTallo;
	private String longitudEntrenudos;
	private String naturalezaDelTallo;
	private String descripcion;
	public Color m_Color;
	private int talloID;



	public void finalize() throws Throwable {

	}

	public Tallo(){

	}

	public String getalturaDelTallo(){
		return alturaDelTallo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setalturaDelTallo(String newVal){
		alturaDelTallo = newVal;
	}

	public Color getcolorDelTallo(){
		return colorDelTallo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorDelTallo(Color newVal){
		colorDelTallo = newVal;
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

	public boolean isdesnudoCubierto(){
		return desnudoCubierto;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdesnudoCubierto(boolean newVal){
		desnudoCubierto = newVal;
	}

	public String getdiametroDelTallo(){
		return diametroDelTallo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdiametroDelTallo(String newVal){
		diametroDelTallo = newVal;
	}

	public String getdisposicionDeLasEspinas(){
		return disposicionDeLasEspinas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdisposicionDeLasEspinas(String newVal){
		disposicionDeLasEspinas = newVal;
	}

	public boolean isentrenudosConspicuos(){
		return entrenudosConspicuos;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setentrenudosConspicuos(boolean newVal){
		entrenudosConspicuos = newVal;
	}

	public boolean isespinas(){
		return espinas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setespinas(boolean newVal){
		espinas = newVal;
	}

	public String getformaDelTallo(){
		return formaDelTallo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setformaDelTallo(String newVal){
		formaDelTallo = newVal;
	}

	public String getlongitudEntrenudos(){
		return longitudEntrenudos;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setlongitudEntrenudos(String newVal){
		longitudEntrenudos = newVal;
	}

	public String getnaturalezaDelTallo(){
		return naturalezaDelTallo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnaturalezaDelTallo(String newVal){
		naturalezaDelTallo = newVal;
	}

	public int gettalloID(){
		return talloID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settalloID(int newVal){
		talloID = newVal;
	}

}