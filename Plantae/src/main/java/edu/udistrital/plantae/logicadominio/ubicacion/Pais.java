package edu.udistrital.plantae.logicadominio.ubicacion;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Pais extends Region {

	private List<Departamento> departamentos;

	public Pais(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre
	 */
	public Pais(String nombre){
        super(nombre);
	}

	public List<Departamento> getDepartamentos(){
		return departamentos;
	}

	/**
	 * 
	 * @param departamentos
	 */
	public void setDepartamentos(List<Departamento> departamentos){
		this.departamentos = departamentos;
	}

}