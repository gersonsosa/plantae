package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class Habito {

	private String habito;
	private int habitoID;

	public Habito(){

	}

	public void finalize() throws Throwable {

	}

	public String gethabito(){
		return habito;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethabito(String newVal){
		habito = newVal;
	}

	public int gethabitoID(){
		return habitoID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethabitoID(int newVal){
		habitoID = newVal;
	}

}