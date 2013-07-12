package edu.udistrital.botanicadroid.LogicaDominio.Recoleccion;

import java.util.Date;

import edu.udistrital.botanicadroid.LogicaDominio.Ubicacion.Localidad;
import edu.udistrital.botanicadroid.LogicaDominio.Ubicacion.Region;

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

}