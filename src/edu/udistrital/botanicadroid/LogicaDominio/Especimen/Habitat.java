package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:57 p.m.
 */
public class Habitat {

	private String especiesAsociadas;
	private String sueloSustrato;
	private String vegetacion;



	public void finalize() throws Throwable {

	}

	public Habitat(){

	}

	public String getespeciesAsociadas(){
		return especiesAsociadas;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setespeciesAsociadas(String newVal){
		especiesAsociadas = newVal;
	}

	public String getsueloSustrato(){
		return sueloSustrato;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setsueloSustrato(String newVal){
		sueloSustrato = newVal;
	}

	public String getvegetacion(){
		return vegetacion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setvegetacion(String newVal){
		vegetacion = newVal;
	}

}