package edu.udistrital.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 11:15:52 PM
 */
public class MuestraAsociada {

	private String descripcion;
	private String metodoDeTratamiento;
	private Especimen especimen;
	public Especimen m_Especimen;



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

}