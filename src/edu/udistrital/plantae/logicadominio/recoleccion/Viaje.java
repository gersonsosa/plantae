package edu.udistrital.plantae.logicadominio.recoleccion;
import java.util.ArrayList;

import com.google.android.maps.MapView;

import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.FabricaEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.FabricaPrototipadoEspecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class Viaje {

	private String nombre;
	private FabricaEspecimen fabricaEspecimen;
	private FabricaPrototipadoEspecimen fabricaPrototipadoEspecimen;
	private ColectorPrincipal colectorPrincipal;
	private Trayecto trayecto;
	private Proyecto proyecto;
	private int viajeID;
	private ArrayList<Recoleccion> recolecciones;
	private ArrayList<ColectorSecundario> colectoresSecundarios;

	public Viaje(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param colectores
	 * @param proyecto
	 * @param nombre    nombre
	 */
	public Viaje(ColectorSecundario[] colectores, Proyecto proyecto, String nombre){

	}

	/**
	 * 
	 * @param proyecto
	 * @param nombre    nombre
	 */
	public Viaje(Proyecto proyecto, String nombre){

	}

	public Trayecto reconstruirTrayecto(){
		return null;
	}

	private MapView construirMapa(){
		return null;
	}

	/**
	 * 
	 * @param apellido
	 * @param nombre    nombre
	 */
	public void agregarColector(String apellido, String nombre){

	}

	/**
	 * 
	 * @param colector    colector
	 */
	public void eliminarColector(ColectorSecundario colector){

	}

	public void agregarEspecimen(){

	}

	/**
	 * 
	 * @param metodoDeTratamiento
	 * @param especimen    especimen
	 */
	public void agregarMuestraAsociada(String metodoDeTratamiento, Especimen especimen){

	}

	public String getnombre(){
		return nombre;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

	public Proyecto getproyecto(){
		return proyecto;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setproyecto(Proyecto newVal){
		proyecto = newVal;
	}

	public ColectorPrincipal getcolectorPrincipal(){
		return colectorPrincipal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolectorPrincipal(ColectorPrincipal newVal){
		colectorPrincipal = newVal;
	}

	public Trayecto gettrayecto(){
		return trayecto;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settrayecto(Trayecto newVal){
		trayecto = newVal;
	}

	public int getviajeID(){
		return viajeID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setviajeID(int newVal){
		viajeID = newVal;
	}

}
