package edu.udistrital.botanicadroid.LogicaDominio.Taxonomia;
import edu.udistrital.botanicadroid.LogicaDominio.Autenticacion.Persona;
import edu.udistrital.botanicadroid.LogicaDominio.Especimen.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:13 a.m.
 */
public class IdentidadTaxonomica {

	private Date fechaIdentificacion;
	private String tipo;
	private Taxon taxon;
	private Persona determinador;
	private Especimen especimen;

	public IdentidadTaxonomica(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param determinador
	 * @param taxon
	 */
	public IdentidadTaxonomica(Persona determinador, Taxon taxon){

	}

}