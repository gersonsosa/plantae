package edu.udistrital.LogicaDominio.Recoleccion;
import java.util.ArrayList;

import com.google.android.maps.MapView;

import edu.udistrital.LogicaDominio.Especimen.Especimen;
import edu.udistrital.LogicaDominio.Especimen.FabricaEspecimen;
import edu.udistrital.LogicaDominio.Especimen.FabricaPrototipadoEspecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 11:49:26 PM
 */
public class Viaje {

	private String nombre;
	private ArrayList<Colector> colectores;
	private Trayecto trayecto;
	private Proyecto proyecto;
	private FabricaEspecimen fabricaEspecimen;
	private FabricaPrototipadoEspecimen fabricaPrototipadoEspecimen;
	private ColectorPrincipal colectorPrincipal;

	public Viaje(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param colectores
	 * @param proyecto
	 * @param nombre
	 */
	public Viaje(Colector[] colectores, Proyecto proyecto, String nombre){

	}

	/**
	 * 
	 * @param proyecto
	 * @param nombre
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
	 * @param nombre
	 */
	public void agregarColector(String apellido, String nombre){

	}

	/**
	 * 
	 * @param colector
	 */
	public void eliminarColector(Colector colector){

	}

	public void agregarEspecimen(){

	}

	/**
	 * 
	 * @param metodoDeTratamiento
	 * @param especimen
	 */
	public void agregarMuestraAsociada(String metodoDeTratamiento, Especimen especimen){

	}

}