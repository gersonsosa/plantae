package edu.udistrital.botanicadroid.LogicaDominio.Autenticacion;

import java.util.ArrayList;
import java.util.HashMap;

import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Proyecto;
import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Viaje;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:10 a.m.
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