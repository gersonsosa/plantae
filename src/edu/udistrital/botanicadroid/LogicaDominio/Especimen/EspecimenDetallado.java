package edu.udistrital.botanicadroid.LogicaDominio.Especimen;
import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Recolecta;
import edu.udistrital.botanicadroid.LogicaDominio.Ubicacion.Localidad;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:12 a.m.
 */
public class EspecimenDetallado implements Especimen {

	private String descripcionEspecimen;
	private long alturaDeLaPlanta;
	private Habito habito;
	private long dap;
	private String abundancia;
	private String fenologia;
	private String numeroDeColeccion;
	private Inflorescencia inflorecencia;
	private Hoja hoja;
	private Fruto fruto;
	private Tallo tallo;
	private Raiz raiz;
	private Flor flor;
	private Etiqueta etiqueta;
	private Habitat habitat;
	private Recolecta recolecta;
	private Localidad localidad;

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

	public void agregarTodosColectores(){

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