package edu.udistrital.plantae.logicadominio.ubicacion;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Municipio extends Region {

	private List<Localidad> localidades;

	public Municipio(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre
	 */
	public Municipio(String nombre){
        super(nombre);
	}

	public List<Localidad> getLocalidades(){
		return localidades;
	}

	/**
	 * 
	 * @param localidades
	 */
	public void setLocalidades(List<Localidad> localidades){
		this.localidades = localidades;
	}

}