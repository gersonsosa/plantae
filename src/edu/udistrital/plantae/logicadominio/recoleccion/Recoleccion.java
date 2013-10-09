package edu.udistrital.plantae.logicadominio.recoleccion;

import java.util.Date;

import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.ubicacion.Localidad;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 25-Jun-2013 11:54:36 PM
 */
public class Recoleccion {

	private Date fechaInicial;
	private Date fechaFinal;
	private String metodoColeccion;
	private String estacionDelAño;
	private int recoleccionID;
	private Especimen especimen;
	private ColectorPrincipal colectorPrincipal;



	public void finalize() throws Throwable {

	}

	public Recoleccion(){

	}

	/**
	 * 
	 * @param nombre
	 * @param region
	 */
	public Localidad registrarUbicacionGeografica(String nombre, Region region){
		return null;
	}

	public Date getfechaInicial(){
		return fechaInicial;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfechaInicial(Date newVal){
		fechaInicial = newVal;
	}

	public Date getfechaFinal(){
		return fechaFinal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfechaFinal(Date newVal){
		fechaFinal = newVal;
	}

	public String getmetodoColeccion(){
		return metodoColeccion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmetodoColeccion(String newVal){
		metodoColeccion = newVal;
	}

	public String getestacionDelAño(){
		return estacionDelAño;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setestacionDelAño(String newVal){
		estacionDelAño = newVal;
	}

	public int getrecoleccionID(){
		return recoleccionID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setrecoleccionID(int newVal){
		recoleccionID = newVal;
	}

}