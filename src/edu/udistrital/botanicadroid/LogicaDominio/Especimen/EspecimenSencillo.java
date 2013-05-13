package edu.udistrital.botanicadroid.LogicaDominio.Especimen;
import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Recolecta;
import edu.udistrital.botanicadroid.LogicaDominio.Ubicacion.Localidad;
import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.ColectorPrincipal;
import edu.udistrital.botanicadroid.LogicaDominio.Recoleccion.Colector;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 13-may-2013 01:24:11 a.m.
 */
public class EspecimenSencillo implements Especimen {

	private String descripcionEspecimen;
	private long alturaDeLaPlanta;
	private long dap;
	private String abundancia;
	private String fenologia;
	private String numeroDeColeccion;
	private Etiqueta etiqueta;
	private Recolecta recolecta;
	private Habitat habitat;
	private Localidad localidad;
	private ColectorPrincipal colectorPrincipal;
	private ArrayList colectores;
	private ArrayList habito;
	private Flor flor;

	public EspecimenSencillo(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param numeroDeColector
	 */
	public EspecimenSencillo(String numeroDeColector){

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

	public String getnumeroDeColeccion(){
		return numeroDeColeccion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnumeroDeColeccion(String newVal){
		numeroDeColeccion = newVal;
	}

	public Object clone(){
		return null;
	}

}