package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.ColectorPrincipal;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class FabricaEspecimen {
	private static FabricaEspecimen fabricaEspecimen;
	private ColectorPrincipal colectorPrincipal;

	public void finalize() throws Throwable {

	}

	private FabricaEspecimen(){

	}

	public Especimen getEspecimen(){
		if (colectorPrincipal.gettipoCapturaDatos()==1){
			return new EspecimenSencillo(colectorPrincipal.getnumeroColeccionActual());
		}
		return new EspecimenDetallado();
	}

	public static FabricaEspecimen getFabricaEspecimen(){
		if (fabricaEspecimen == null) {
			fabricaEspecimen=new FabricaEspecimen();
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