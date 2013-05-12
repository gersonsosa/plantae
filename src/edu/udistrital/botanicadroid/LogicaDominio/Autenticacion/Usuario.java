package edu.udistrital.botanicadroid.LogicaDominio.Autenticacion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:58 p.m.
 */
public class Usuario extends Persona {

	private String nombreUsuario;
	private String contraseña;
	private int tipoCapturaDatos;

	public Usuario(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param datosRegistro
	 */
	public Usuario(HashMap datosRegistro){

	}

	/**
	 * 
	 * @param datosRegistro
	 */
	public static final void registrarUsuario(HashMap datosRegistro){

	}

	/**
	 * 
	 * @param datosRegistro
	 */
	private static final ArrayList validarDatosRegistro(HashMap datosRegistro){
		return null;
	}

	public ArrayList<Viaje> getListaViajes(){
		return null;
	}

	public ArrayList<Proyecto> getListaProyectos(){
		return null;
	}

	public int gettipoCapturaDatos(){
		return tipoCapturaDatos;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settipoCapturaDatos(int newVal){
		tipoCapturaDatos = newVal;
	}

}