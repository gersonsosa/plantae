package edu.udistrital.plantae.logicadominio.recoleccion;

import com.google.android.maps.MapView;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class Trayecto {

	private MapView mapa;
	private int trayectoID;



	public void finalize() throws Throwable {

	}

	public Trayecto(){

	}

	public MapView getmapa(){
		return mapa;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmapa(MapView newVal){
		mapa = newVal;
	}

	public int gettrayectoID(){
		return trayectoID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settrayectoID(int newVal){
		trayectoID = newVal;
	}

}