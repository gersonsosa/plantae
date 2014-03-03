package edu.udistrital.plantae.logicadominio.recoleccion;
import java.util.ArrayList;

import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:13 a.m.
 */
public class ColectorPrincipal extends Persona {

	private String numeroColeccionActual;
	private int colectorPrincipalID;
	private ArrayList<Viaje> viajes;
	private ArrayList<Proyecto> proyectos;
	private ArrayList<Recoleccion> recolecciones;
	private int tipoCapturaDatos;
	private Persona persona;

	public void finalize() throws Throwable {
		super.finalize();
	}
	
	public ColectorPrincipal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param usuario
	 * @param contraseña
	 */
	public ColectorPrincipal(String usuario, String contraseña){
		super();
		persona = new Persona();
		persona.setusuario(Usuario.getUsuario(usuario, contraseña));
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}