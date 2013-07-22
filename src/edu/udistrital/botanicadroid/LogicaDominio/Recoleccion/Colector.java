package edu.udistrital.botanicadroid.LogicaDominio.Recoleccion;
import edu.udistrital.botanicadroid.LogicaDominio.Autenticacion.Persona;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class Colector extends Persona {

	private Persona persona;
	private int colectoresID;



	public void finalize() throws Throwable {
		super.finalize();
	}

	public Colector(){

	}

	public Persona getpersona(){
		return persona;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setpersona(Persona newVal){
		persona = newVal;
	}

	public int getcolectoresID(){
		return colectoresID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolectoresID(int newVal){
		colectoresID = newVal;
	}

}