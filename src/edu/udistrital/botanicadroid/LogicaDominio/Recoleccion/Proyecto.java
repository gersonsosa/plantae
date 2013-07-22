package edu.udistrital.botanicadroid.LogicaDominio.Recoleccion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class Proyecto {

	private String nombre;
	private String agenciaFinanciera;
	private String agenciaEjecutora;
	private String numeroConvenio;
	private String permisoColeccion;
	private String numeroPermiso;
	private String emisorPermiso;
	private int proyectoID;

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

	public String getnombre(){
		return nombre;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

	public int getproyectoID(){
		return proyectoID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setproyectoID(int newVal){
		proyectoID = newVal;
	}

}
