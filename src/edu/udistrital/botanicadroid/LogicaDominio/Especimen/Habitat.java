package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Habitat {

	private String especiesAsociadas;
	private String sueloSustrato;
	private String vegetacion;
	private int habitatID;



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

	public int gethabitatID(){
		return habitatID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethabitatID(int newVal){
		habitatID = newVal;
	}

}