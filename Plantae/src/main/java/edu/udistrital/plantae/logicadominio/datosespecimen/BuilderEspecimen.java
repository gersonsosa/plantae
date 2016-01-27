package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public abstract class BuilderEspecimen {

	public void finalize() throws Throwable {

	}

    public abstract void build();

	public abstract Especimen getEspecimen();
}