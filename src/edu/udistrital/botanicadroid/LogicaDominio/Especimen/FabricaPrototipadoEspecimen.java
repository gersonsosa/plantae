package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

import java.util.ArrayList;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class FabricaPrototipadoEspecimen {

	private ArrayList<Especimen> especimenDetallado;
	private ArrayList<Especimen> especimenSencillo;

	public FabricaPrototipadoEspecimen(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param especimenDetallado
	 * @param especimenSencillo
	 */
	public FabricaPrototipadoEspecimen(EspecimenDetallado especimenDetallado, EspecimenSencillo especimenSencillo){

	}

	public EspecimenDetallado getEspecimenDetallado(){
		return null;
	}

	public EspecimenSencillo getEspecimenSencillo(){
		return null;
	}

}