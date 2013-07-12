package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class ColorMunsell {

	private int hue;
	private int value;
	private int chroma;

	public void finalize() throws Throwable {

	}

	public Color getRGB(){
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

}