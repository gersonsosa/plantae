package edu.udistrital.botanicadroid.LogicaDominio.Autenticacion;
import edu.udistrital.botanicadroid.LogicaDominio.LoginFilter.UsernameFilterGeneric;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:10 a.m.
 */
public class Sesion {

	private Sesion sesion;
	private Usuario usuario;



	public void finalize() throws Throwable {

	}

	/**
	 * 
	 */
	private Sesion(){

	}

	/**
	 * 
	 * @param contraseña
	 * @param nombreUsuario
	 */
	public static Sesion iniciarSesion(String contraseña, String nombreUsuario){
		return null;
	}

	public boolean cerrarSesion(){
		return false;
	}

	/**
	 * 
	 * @param contraseña
	 * @param nombreUsuario
	 */
	private static Usuario validarDatosInicioSesion(String contraseña, String nombreUsuario){
		return null;
	}

}