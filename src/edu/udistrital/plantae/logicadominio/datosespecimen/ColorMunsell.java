package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class ColorMunsell {

	private int hue;
	private int value;
	private int chroma;
	private int colorMunsellID;

	public void finalize() throws Throwable {

	}

	public ColorEspecimen getRGB(){
		return null;
	}

	public int gethue(){
		return hue;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethue(int newVal){
		hue = newVal;
	}

	public int getvalue(){
		return value;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setvalue(int newVal){
		value = newVal;
	}

	public int getchroma(){
		return chroma;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setchroma(int newVal){
		chroma = newVal;
	}

	public int getcolorMunsellID(){
		return colorMunsellID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolorMunsellID(int newVal){
		colorMunsellID = newVal;
	}

}