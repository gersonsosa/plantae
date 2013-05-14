package edu.udistrital.botanicadroid.LogicaDominio.Recoleccion;
import java.util.ArrayList;

import edu.udistrital.botanicadroid.LogicaDominio.Autenticacion.Usuario;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:13 a.m.
 */
public class ColectorPrincipal extends Usuario {

	private String numeroColeccionActual;
	private ColectorPrincipal colectorPrincipal;



	public void finalize() throws Throwable {
		super.finalize();
	}

	private ColectorPrincipal(){

	}

	public static ColectorPrincipal getColectorPrincipal(){
		return null;
	}

	public ArrayList<Viaje> getListaViajes(){
		return null;
	}

	public ArrayList<Proyecto> getListaProyectos(){
		return null;
	}

}