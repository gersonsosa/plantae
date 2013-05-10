package edu.udistrital.LogicaDominio.Especimen;
import java.util.ArrayList;

import edu.udistrital.LogicaDominio.Recoleccion.Recolecta;
import edu.udistrital.LogicaDominio.Ubicacion.Localidad;
import edu.udistrital.LogicaDominio.Recoleccion.ColectorPrincipal;
import edu.udistrital.LogicaDominio.Recoleccion.Colector;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 09-May-2013 5:41:21 PM
 */
public class EspecimenSencillo implements Especimen {

	private String descripcionEspecimen;
	private Habito habito;
	private long alturaDeLaPlanta;
	private long dap;
	private String abundancia;
	private String fenologia;
	private Flor flor;
	private String numeroDeColeccion;
	private ArrayList colectores;
	private Habitat habitat;
	private Localidad localidad;
	private Recolecta recolecta;
	private ColectorPrincipal colectorPrincipal;
	public Etiqueta m_Etiqueta;
	public Recolecta m_Recolecta;
	public Habitat m_Habitat;
	public Localidad m_Localidad;
	public Habito m_Habito;
	public Flor m_Flor;

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

	public String gethabito(){
		return habito.gethabito();
	}

	public void agregarTodosColectores(){

	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethabito(String newVal){
		habito.sethabito(newVal);
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