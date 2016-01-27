package edu.udistrital.plantae.logicadominio.autenticacion;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 19-May-2013 11:50:34 PM
 */
public class Sesion{

	private static Sesion sesion;

	private Sesion(){

	}

    /**
     *
     * @throws Throwable
     */
	public void finalize()
	  throws Throwable{

	}

    /**
     * Método estático que retorna la única ejemplificación de la sesión.
     * @param usuario
     * @return La única ejemplificación de la sesión
     */
	public static Sesion iniciarSesion(Usuario usuario){
		if (sesion == null) {
			sesion=new Sesion();
		}
		return sesion;
	}

	public static boolean cerrarSesion(){
		sesion = null;
		return true;
	}

}