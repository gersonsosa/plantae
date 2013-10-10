package edu.udistrital.plantae.logicadominio.autenticacion;
import java.util.HashMap;

import android.text.TextUtils;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 19-May-2013 11:50:35 PM
 */
public class Usuario {

	private String nombreUsuario;
	private String contraseña;
	private int tipoCapturaDatos;
	private HashMap<String, String> erroresCredenciales;
	private Sesion sesion;
	private int usuarioID;
	private static Usuario usuario;

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombreUsuario
	 * @param contraseña
	 */
	private Usuario(String nombreUsuario, String contraseña){
		this.nombreUsuario=nombreUsuario;
		this.contraseña=contraseña;
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
	 * @param datosRegistro    datosRegistro
	 */
	public static HashMap<String, String> registrarUsuario(HashMap<String, String> datosRegistro){
		Persona persona = new Persona();
		persona.setinstitucion(datosRegistro.get("institution"));
		String[] names=TextUtils.split(datosRegistro.get("fullname"), " ");
		if (names.length == 2){
			persona.setnombres(names[0]);
			persona.setapellidos(names[1]);
		} else {
			persona.setapellidos(datosRegistro.get("fullname"));
		}
		HashMap<String, String> errores = validarDatosRegistro(datosRegistro);
		if (errores.isEmpty()){
			// TODO: Save Persona, Usuario And ColectorPrincipal
			Sesion.iniciarSesion(usuario);
		}
		return errores;
	}

	/**
	 * 
	 * @param datosRegistro    datosRegistro
	 */
	private static HashMap<String, String> validarDatosRegistro(HashMap<String, String> datosRegistro){
		return null;
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

	public String getcontraseña(){
		return contraseña;
	}

	public String getnombreUsuario(){
		return nombreUsuario;
	}

	/**
	 * 
	 * @param contraseña
	 * @param nombreUsuario
	 */
	public static Usuario getUsuario(String nombreUsuario, String contraseña){
		if (usuario == null){
			usuario = new Usuario(nombreUsuario, contraseña);
		}
		return usuario;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombreUsuario(String newVal){
		nombreUsuario = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcontraseña(String newVal){
		contraseña = newVal;
	}
	
	public int getUsuarioID() {
		return usuarioID;
	}

	/**
	 * 
	 * @param usuarioID
	 */
	public void setUsuarioID(int usuarioID) {
		this.usuarioID = usuarioID;
	}

}
