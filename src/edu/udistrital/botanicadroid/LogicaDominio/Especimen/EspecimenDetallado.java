package edu.udistrital.botanicadroid.LogicaDominio.Especimen;
import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Recolecta;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 10-may-2013 03:41:56 p.m.
 */
public class EspecimenDetallado implements Especimen {

	private String descripcionEspecimen;
	private Habito habito;
	private long alturaDeLaPlanta;
	private long dap;
	private String abundancia;
	private String fenologia;
	private Raiz raiz;
	private Tallo tallo;
	private Inflorescencia inflorescencia;
	private Fruto fruto;
	private Flor flor;
	private Hoja hoja;
	private String numeroDeColeccion;
	private Habitat habitat;
	private Recolecta recolecta;
	public Inflorescencia m_Inflorescencia;
	public Hoja m_Hoja;
	public Fruto m_Fruto;
	public Tallo m_Tallo;
	public Raiz m_Raiz;
	public Flor m_Flor;
	public Etiqueta m_Etiqueta;
	public Habito m_Habito;
	public Habitat m_Habitat;
	public Recolecta m_Recolecta;

	public EspecimenDetallado(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param numeroDeColector
	 */
	public void EspecimenDetallado(String numeroDeColector){

	}

	public String generarNumeroDeColector(){
		return "";
	}

	public String getdescripcionEspecimen(){
		return descripcionEspecimen;
	}

	/**
	 * 
	 * @param colector
	 */
	public void quitarColector(Colector colector){

	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcionEspecimen(String newVal){
		descripcionEspecimen = newVal;
	}

	public String gethabito(){
		return habito;
	}

	public void agregarTodosColectores(){

	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethabito(String newVal){
		habito = newVal;
	}

	public long getalturaDeLaPlanta(){
		return alturaDeLaPlanta;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setalturaDeLaPlanta(long newVal){
		alturaDeLaPlanta = newVal;
	}

	public long getdap(){
		return dap;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdap(long newVal){
		dap = newVal;
	}

	public String getabundancia(){
		return abundancia;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setabundancia(String newVal){
		abundancia = newVal;
	}

	public String getfenologia(){
		return fenologia;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfenologia(String newVal){
		fenologia = newVal;
	}

	public Object clone(){
		return null;
	}

}