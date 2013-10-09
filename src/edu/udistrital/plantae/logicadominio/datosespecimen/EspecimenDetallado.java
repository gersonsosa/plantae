package edu.udistrital.plantae.logicadominio.datosespecimen;
import java.util.ArrayList;

import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.logicadominio.recoleccion.Recoleccion;
import edu.udistrital.plantae.logicadominio.ubicacion.Localidad;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class EspecimenDetallado implements Especimen {

	private String numeroDeColeccion;
	private long alturaDeLaPlanta;
	private long dap;
	private String abundancia;
	private String fenologia;
	private String descripcionEspecimen;
	private Habito habito;
	private Habitat habitat;
	private Localidad localidad;
	private Inflorescencia inflorecencia;
	private Hoja hoja;
	private Fruto fruto;
	private Tallo tallo;
	private Raiz raiz;
	private Flor flor;
	private Etiqueta etiqueta;
	private Recoleccion recolecta;
	private int especimendetalladoID;
	private ColectorPrincipal colectorPrincipal;
	private Recoleccion recoleccion;
	private ArrayList colectoresSecundarios;

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

	public String getdescripcionEspecimen(){
		return descripcionEspecimen;
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

	public Habito gethabito(){
		return habito;
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

	/**
	 * 
	 * @param newVal
	 */
	public void sethabito(Habito newVal){
		habito = newVal;
	}

	public Habitat gethabitat(){
		return habitat;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethabitat(Habitat newVal){
		habitat = newVal;
	}

	public Localidad getlocalidad(){
		return localidad;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setlocalidad(Localidad newVal){
		localidad = newVal;
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

	public int getespecimendetalladoID(){
		return especimendetalladoID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setespecimendetalladoID(int newVal){
		especimendetalladoID = newVal;
	}

	public String generarNumeroDeColeccion(){
		return "";
	}

	/**
	 * 
	 * @param muestra
	 */
	public void agregarMuestraAsociada(MuestraAsociada muestra){

	}

	/**
	 * 
	 * @param colectorSecundario
	 */
	public void quitarColector(ColectorSecundario colectorSecundario){

	}

}