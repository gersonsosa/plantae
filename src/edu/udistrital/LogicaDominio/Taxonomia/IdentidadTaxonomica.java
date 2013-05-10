package edu.udistrital.LogicaDominio.Taxonomia;
import java.util.Date;

import edu.udistrital.LogicaDominio.Autenticacion.Persona;
import edu.udistrital.LogicaDominio.Especimen.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:22 PM
 */
public class IdentidadTaxonomica {

	private Date fechaIdentificacion;
	private String tipo;
	private Persona determinador;
	private Taxon taxon;
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