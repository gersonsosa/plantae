package edu.udistrital.plantae.logicadominio.datosespecimen;

import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public abstract class FabricaEspecimen {
	private static FabricaEspecimen fabricaEspecimen;
	private static ColectorPrincipal colectorPrincipal;

	public void finalize() throws Throwable {

	}

	protected FabricaEspecimen(){

	}

	public abstract Especimen getEspecimen();

	public static FabricaEspecimen getFabricaEspecimen(){
		if (fabricaEspecimen == null) {
			if (colectorPrincipal.gettipoCapturaDatos() == 1){
				fabricaEspecimen = new FabricaEspecimenDetallado();
			} else {
				fabricaEspecimen =  new FabricaEspecimenSencillo();
			}
		}
		return fabricaEspecimen;
	}

	public ColectorPrincipal getcolectorPrincipal(){
		return colectorPrincipal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcolectorPrincipal(ColectorPrincipal newVal){
		colectorPrincipal = newVal;
	}

}