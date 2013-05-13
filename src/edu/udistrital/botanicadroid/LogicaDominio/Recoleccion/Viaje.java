package edu.udistrital.botanicadroid.LogicaDominio.Recoleccion;
import edu.udistrital.botanicadroid.LogicaDominio.Especimen.FabricaEspecimen;
import edu.udistrital.botanicadroid.LogicaDominio.Especimen.FabricaPrototipadoEspecimen;
import edu.udistrital.botanicadroid.LogicaDominio.Especimen.Especimen;

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
	private ArrayList colectores;
	private Proyecto proyecto;

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
	public Viaje(Colector[] colectores, Proyecto proyecto, String nombre){

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

	private Map construirMapa(){
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
	public void eliminarColector(Colector colector){

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

}