package edu.udistrital.botanicadroid.LogicaDominio.Recoleccion;
import java.util.ArrayList;

import edu.udistrital.botanicadroid.LogicaDominio.Autenticacion.Usuario;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:13 a.m.
 */
public class ColectorPrincipal extends Usuario {

	private String numeroColeccionActual;
	private static ColectorPrincipal colectorPrincipal;
	private int colectorPrincipalID;
	private ArrayList<Viaje> viajes;
	private ArrayList<Proyecto> proyectos;
	private ArrayList<Recoleccion> recolecciones;



	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param contraseña
	 * @param usuario
	 */
	private ColectorPrincipal(String contraseña, String usuario){
		super(contraseña, usuario);
	}

	/**
	 * 
	 * @param contraseña
	 * @param usuario
	 */
	public static ColectorPrincipal getColectorPrincipal(String contraseña, String usuario){
		if (colectorPrincipal == null) {
			colectorPrincipal=new ColectorPrincipal(usuario, contraseña);
		}
		return colectorPrincipal;
	}

	public ArrayList<Viaje> getListaViajes(){
		return viajes;
	}

	public ArrayList<Proyecto> getListaProyectos(){
		return proyectos;
	}

	public String getnumeroColeccionActual(){
		return numeroColeccionActual;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnumeroColeccionActual(String newVal){
		numeroColeccionActual = newVal;
	}

	public int getcolectorPrincipalID(){
		return colectorPrincipalID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolectorPrincipalID(int newVal){
		colectorPrincipalID = newVal;
	}

}