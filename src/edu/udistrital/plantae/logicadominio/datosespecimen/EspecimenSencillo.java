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
public class EspecimenSencillo implements Especimen {

	private String numeroDeColeccion;
	private long alturaDeLaPlanta;
	private long dap;
	private String abundancia;
	private String fenologia;
	private String descripcionEspecimen;
	private Habito habito;
	private Habitat habitat;
	private Localidad localidad;
	private Etiqueta etiqueta;
	private Recoleccion recolecta;
	private ColectorPrincipal colectorPrincipal;
	private Flor flor;
	private int especimensencilloID;
	private int especimenSencilloID;
	private Recoleccion recoleccion;
	private ArrayList<ColectorSecundario> colectoresSecundarios;
	private ArrayList<MuestraAsociada> muestraAsociada;
	private ArrayList<Fotografia> fotografias;

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

	public void agregarMuestraAsociada(){

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

	public Habito gethabito(){
		return habito;
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

	public int getespecimensencilloID(){
		return especimensencilloID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setespecimensencilloID(int newVal){
		especimensencilloID = newVal;
	}

	public String generarNumeroDeColeccion(){
		return "";
	}

	/**
	 * 
	 * @param colectorSecundario
	 */
	public void quitarColector(ColectorSecundario colectorSecundario){

	}

	/**
	 * 
	 * @param muestra
	 */
	public void agregarMuestraAsociada(MuestraAsociada muestra){

	}

	public int getespecimenSencilloID(){
		return especimenSencilloID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setespecimenSencilloID(int newVal){
		especimenSencilloID = newVal;
	}

	/**
	 * 
	 * @param fotografía
	 */
	public void agregarFotografia(Fotografia fotografía){

	}

}