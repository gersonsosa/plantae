package edu.udistrital.botanicadroid.LogicaDominio.Recoleccion;
import edu.udistrital.botanicadroid.LogicaDominio.Autenticacion.Usuario;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:56 p.m.
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