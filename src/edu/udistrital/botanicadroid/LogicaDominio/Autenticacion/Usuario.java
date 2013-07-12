package edu.udistrital.botanicadroid.LogicaDominio.Autenticacion;
import java.util.ArrayList;
import java.util.HashMap;

import android.text.TextUtils;
import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Proyecto;
import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Viaje;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 19-May-2013 11:50:35 PM
 */
public class Usuario extends Persona {

	private String nombreUsuario;
	private String contraseña;
	private int tipoCapturaDatos;
	private HashMap<String, String> erroresCredenciales;
	private Sesion sesion;

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param contraseña
	 * @param usuario
	 */
	public Usuario(String contraseña, String usuario){
		nombreUsuario=usuario;
		this.contraseña=contraseña;
	}

	/**
	 * 
	 * @param datosRegistro
	 */
	public static void registrarUsuario(HashMap<String,String> datosRegistro){

	}

	/**
	 * 
	 * @param datosRegistro
	 */
	private static ArrayList validarDatosRegistro(HashMap<String,String> datosRegistro){
		// TODO validar datos de un nuevo usuario
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

	/**
	 * 
	 * @param contraseña
	 * @param usuario
	 */
	public Sesion validarDatosInicioSesion(){
		// Variable initialization
		boolean cancel = false;
		erroresCredenciales = new HashMap<String, String>();
		if (TextUtils.isEmpty(contraseña)) {
			// Add to the HashMap the error code.
			erroresCredenciales.put("mPassword", "La contraseña esta vacia");
			// Put the cancel flag to true.
			cancel = true;
		} else if (contraseña.length() < 4) {
			// Add to the HashMap the error code.
			erroresCredenciales.put("mPassword", "La contraseña es muy corta");
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(nombreUsuario)) {
			// Add to the HashMap the error code.
			erroresCredenciales.put("mEmail", "El usuario esta vacio");
			cancel = true;
		} else if (!nombreUsuario.contains("@")) {
			// Add to the HashMap the error code.
			erroresCredenciales.put("mEmail", "El usuario debe contener @");
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login, return null
			return null;
		} else {
			// TODO Check username and password in database
			
			sesion = Sesion.iniciarSesion(this);
			return sesion;
		}
	}

	public HashMap<String, String> geterroresCredenciales(){
		return erroresCredenciales;
	}

}