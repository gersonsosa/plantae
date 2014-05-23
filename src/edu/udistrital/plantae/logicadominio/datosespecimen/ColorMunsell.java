package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class ColorMunsell {

    private Long id;
    private int hue;
    private int value;
    private int chroma;

	public void finalize() throws Throwable {

	}

	public ColorEspecimen getRGB(){
		return null;
	}

    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

	public int getHue(){
		return hue;
	}

	/**
	 *
	 * @param newVal
	 */
	public void setHue(int newVal){
		hue = newVal;
	}

	public int getValue(){
		return value;
	}

	/**
	 *
	 * @param newVal
	 */
	public void setValue(int newVal){
		value = newVal;
	}

	public int getChroma(){
		return chroma;
	}

	/**
	 *
	 * @param newVal
	 */
	public void setChroma(int newVal){
		chroma = newVal;
	}

}