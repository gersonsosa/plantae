package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:10 a.m.
 */
public class MuestraAsociada {

	private String descripcion;
	private String metodoDeTratamiento;
	private Especimen especimen;



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