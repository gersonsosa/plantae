package edu.udistrital.plantae.logicadominio.ubicacion;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Departamento extends Region {

	private List<Municipio> municipios;

	public Departamento(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre
	 */
	public Departamento(String nombre){

	}

	public List<Municipio> getMunicipios(){
		return municipios;
	}

	/**
	 * 
	 * @param municipios
	 */
	public void setMunicipios(List<Municipio> municipios){
		this.municipios = municipios;
	}

}