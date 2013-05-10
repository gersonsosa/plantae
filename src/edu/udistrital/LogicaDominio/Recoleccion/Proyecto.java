package edu.udistrital.LogicaDominio.Recoleccion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:23 PM
 */
public class Proyecto {

	private String nombre;
	private String agenciaFinanciera;
	private String agenciaEjecutora;
	private String numeroConvenio;
	private String permisoColeccion;
	private String numeroPermiso;
	private String emisorPermiso;

	public Proyecto(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param nombre
	 */
	public Proyecto(String nombre){

	}

	public String getagenciaFinanciera(){
		return agenciaFinanciera;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setagenciaFinanciera(String newVal){
		agenciaFinanciera = newVal;
	}

	public String getagenciaEjecutora(){
		return agenciaEjecutora;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setagenciaEjecutora(String newVal){
		agenciaEjecutora = newVal;
	}

	public String getnumeroConvenio(){
		return numeroConvenio;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnumeroConvenio(String newVal){
		numeroConvenio = newVal;
	}

	public String getpermisoColeccion(){
		return permisoColeccion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setpermisoColeccion(String newVal){
		permisoColeccion = newVal;
	}

	public String getnumeroPermiso(){
		return numeroPermiso;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnumeroPermiso(String newVal){
		numeroPermiso = newVal;
	}

	public String getemisorPermiso(){
		return emisorPermiso;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setemisorPermiso(String newVal){
		emisorPermiso = newVal;
	}

}