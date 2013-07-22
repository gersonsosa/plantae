package edu.udistrital.botanicadroid.LogicaDominio.Autenticacion;


/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 19-May-2013 11:50:34 PM
 */
public class Persona {

	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String institucion;
	private int personaID;

	public Persona(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param apellidos
	 * @param nombres
	 */
	public Persona(String apellidos, String nombres){

	}

	public String getnombres(){
		return nombres;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombres(String newVal){
		nombres = newVal;
	}

	public String getapellidos(){
		return apellidos;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setapellidos(String newVal){
		apellidos = newVal;
	}

	public String getdireccion(){
		return direccion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdireccion(String newVal){
		direccion = newVal;
	}

	public String gettelefono(){
		return telefono;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settelefono(String newVal){
		telefono = newVal;
	}

	public String getinstitucion(){
		return institucion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setinstitucion(String newVal){
		institucion = newVal;
	}

	public int getpersonaID(){
		return personaID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setpersonaID(int newVal){
		personaID = newVal;
	}

}