package edu.udistrital.botanicadroid.LogicaDominio.Autenticacion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 19-May-2013 11:50:34 PM
 */
public class Sesion {

	private static Sesion sesion;

	/**
	 * 
	 */
	private Sesion(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	/**
	 * 
	 * @param usuario    nombreUsuario
	 */
	public static Sesion iniciarSesion(Usuario usuario){
		if (sesion == null) {
			sesion=new Sesion();
		}
		return sesion;
	}

	public boolean cerrarSesion(){
		sesion = null;
		return true;
	}

}