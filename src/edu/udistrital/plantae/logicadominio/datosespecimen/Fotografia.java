package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Fotografia {

	private String rutaArchivo;
	private String contexto;
	private int fotografiaID;



	public void finalize() throws Throwable {

	}

	public Fotografia(){

	}

	public String getrutaArchivo(){
		return rutaArchivo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setrutaArchivo(String newVal){
		rutaArchivo = newVal;
	}

	public String getcontexto(){
		return contexto;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcontexto(String newVal){
		contexto = newVal;
	}

	public int getfotografiaID(){
		return fotografiaID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfotografiaID(int newVal){
		fotografiaID = newVal;
	}

}
