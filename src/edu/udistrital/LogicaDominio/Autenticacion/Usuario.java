package edu.udistrital.LogicaDominio.Autenticacion;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:24 PM
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

	public ArrayList getListaViajes(){
		return null;
	}

	public ArrayList getListaProyectos(){
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