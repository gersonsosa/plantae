package edu.udistrital.plantae.logicadominio.datosespecimen;

/**
 * @author Mateus A., Sosa G.
 * @version 1.0
 * @created 08-Oct-2013 5:21:23 PM
 */
public class FabricaEspecimenSencillo extends FabricaEspecimen {

	public FabricaEspecimenSencillo(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Especimen getEspecimen(){
		return null;
	}

}