package edu.udistrital.LogicaDominio.Especimen;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:21 PM
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