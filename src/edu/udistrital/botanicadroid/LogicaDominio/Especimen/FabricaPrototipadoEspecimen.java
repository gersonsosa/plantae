package edu.udistrital.botanicadroid.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:57 p.m.
 */
public class FabricaPrototipadoEspecimen {

	private EspecimenSencillo especimenSencillo;
	private EspecimenDetallado especimenDetallado;
	public EspecimenDetallado m_EspecimenDetallado;
	public EspecimenSencillo m_EspecimenSencillo;

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