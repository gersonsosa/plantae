package edu.udistrital.LogicaDominio.Recoleccion;

import java.util.Date;

import edu.udistrital.LogicaDominio.Ubicacion.Localidad;
import edu.udistrital.LogicaDominio.Ubicacion.Region;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 11:37:51 PM
 */
public class Recolecta {

	private Date fechaInicial;
	private Date fechaFinal;
	private String metodoColeccion;
	private String estacionDelAño;



	public void finalize() throws Throwable {

	}

	public Recolecta(){

	}

	/**
	 * 
	 * @param nombre
	 * @param region
	 */
	public Localidad registrarUbicacionGeografica(String nombre, Region region){
		return null;
	}

}