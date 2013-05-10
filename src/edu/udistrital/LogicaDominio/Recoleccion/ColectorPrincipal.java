package edu.udistrital.LogicaDominio.Recoleccion;
import java.util.ArrayList;

import edu.udistrital.LogicaDominio.Autenticacion.Usuario;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 11:34:01 PM
 */
public class ColectorPrincipal extends Usuario {

	private ColectorPrincipal colectorPrincipal;
	private String numeroColeccionActual;
	public ColectorPrincipal m_ColectorPrincipal;



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