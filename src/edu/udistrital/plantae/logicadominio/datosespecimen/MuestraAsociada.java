package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:15 AM
 */
public class MuestraAsociada {

	private String descripcion;
	private String metodoDeTratamiento;
	private int muestraAsociadaID;



	public void finalize() throws Throwable {

	}

	public MuestraAsociada(){

	}

	public String getmetodoDeTratamiento(){
		return metodoDeTratamiento;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmetodoDeTratamiento(String newVal){
		metodoDeTratamiento = newVal;
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

	public int getmuestraAsociadaID(){
		return muestraAsociadaID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmuestraAsociadaID(int newVal){
		muestraAsociadaID = newVal;
	}

}
