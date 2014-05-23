package edu.udistrital.plantae.logicadominio.recoleccion;

import com.google.android.maps.MapView;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class Trayecto {

    private int id;
    private MapView mapa;



	public void finalize() throws Throwable {

	}

	public Trayecto(){

	}

	public MapView getMapa(){
		return mapa;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMapa(MapView newVal){
		mapa = newVal;
	}

	public int getId(){
		return id;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setId(int newVal){
		id = newVal;
	}

}