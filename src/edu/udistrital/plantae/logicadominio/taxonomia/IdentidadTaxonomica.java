package edu.udistrital.plantae.logicadominio.taxonomia;
import java.util.Date;

import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class IdentidadTaxonomica {

	private Date fechaIdentificacion;
	private String tipo;
	private Persona determinador;
	private Taxon taxon;
	private Especimen especimen;
	private int identidadtaxonomicaID;

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

	public Date getfechaIdentificacion(){
		return fechaIdentificacion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfechaIdentificacion(Date newVal){
		fechaIdentificacion = newVal;
	}

	public String gettipo(){
		return tipo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settipo(String newVal){
		tipo = newVal;
	}

	public Persona getdeterminador(){
		return determinador;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdeterminador(Persona newVal){
		determinador = newVal;
	}

	public int getidentidadtaxonomicaID(){
		return identidadtaxonomicaID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidentidadtaxonomicaID(int newVal){
		identidadtaxonomicaID = newVal;
	}

}