package edu.udistrital.plantae.logicadominio.datosespecimen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.udistrital.plantae.logicadominio.taxonomia.IdentidadTaxonomica;

/**
 * @author Mateus A., Sosa G.
 * @version 1.0
 * @created 08-Oct-2013 5:21:23 PM
 */
public class BuilderEspecimenSencillo extends BuilderEspecimen {

    private Especimen especimen;

    private String numeroDeColeccion;
    private String abundancia;
    private String descripcionEspecimen;
    private Long alturaDeLaPlanta;
    private Long dap;
    private Date fechaInicial;
    private Date fechaFinal;
    private String metodoColeccion;
    private String estacionDelAño;
    private Long habitoID;
    private Long habitatID;
    private Long fenologiaID;
    private Long localidadID;
    private long viajeID;
    private long colectorPrincipalID;
    private Long florID;
    private String tipo;

    private List<EspecimenColectorSecundario> colectoresSecundarios;
    private List<MuestraAsociada> muestrasAsociadas;
    private List<ColorEspecimen> colores;
    private List<Fotografia> fotografias;
    private IdentidadTaxonomica identidadTaxonomica;

	public BuilderEspecimenSencillo(String numeroDeColeccion, long viajeID, long colectorPrincipalID){
        this.numeroDeColeccion = numeroDeColeccion;
        this.viajeID = viajeID;
        this.colectorPrincipalID = colectorPrincipalID;
	}

    public BuilderEspecimenSencillo alturaDeLaPlanta(Long alturaDeLaPlanta) {
        this.alturaDeLaPlanta = alturaDeLaPlanta;
        return this;
    }

    public BuilderEspecimenSencillo dap(Long dap) {
        this.dap = dap;
        return this;
    }

    public BuilderEspecimenSencillo fechaInicial(Date fechaInicial){
        this.fechaInicial = fechaInicial;
        return this;
    }
    public BuilderEspecimenSencillo fechaFinal(Date fechaFinal){
        this.fechaFinal = fechaFinal;
        return this;
    }
    public BuilderEspecimenSencillo metodoColeccion(String metodoColeccion){
        this.metodoColeccion = metodoColeccion;
        return this;
    }
    public BuilderEspecimenSencillo estacionDelAño(String estacionDelAño){
        this.estacionDelAño = estacionDelAño;
        return this;
    }

    public BuilderEspecimenSencillo habitoID(Long habitoID){
        this.habitoID = habitoID;
        return this;
    }

    public BuilderEspecimenSencillo habitatID(Long habitatID){
        this.habitatID = habitatID;
        return this;
    }

    public BuilderEspecimenSencillo fenologiaID(Long fenologiaID){
        this.fenologiaID = fenologiaID;
        return this;
    }

    public BuilderEspecimenSencillo abundancia(String abundancia) {
        this.abundancia = abundancia;
        return this;
    }
    public BuilderEspecimenSencillo descripcionEspecimen(String descripcionEspecimen) {
        this.descripcionEspecimen = descripcionEspecimen;
        return this;
    }

    public BuilderEspecimenSencillo localidadID(Long localidadID){
        this.localidadID = localidadID;
        return this;
    }

    public BuilderEspecimenSencillo florID(Long florID){
        this.florID = florID;
        return this;
    }

    public BuilderEspecimenSencillo colectoresSecundarios(List<EspecimenColectorSecundario> colectoresSecundarios){
        this.colectoresSecundarios = colectoresSecundarios;
        return this;
    }
    public BuilderEspecimenSencillo muestrasAsociadas(List<MuestraAsociada> muestrasAsociadas){
        this.muestrasAsociadas = muestrasAsociadas;
        return this;
    }
    public BuilderEspecimenSencillo colores(List<ColorEspecimen> colores) {
        this.colores = colores;
        return this;
    }
    public BuilderEspecimenSencillo fotografias(List<Fotografia> fotografias){
        this.fotografias = fotografias;
        return this;
    }
    public BuilderEspecimenSencillo identidadTaxonomica(IdentidadTaxonomica identidadTaxonomica){
        this.identidadTaxonomica = identidadTaxonomica;
        return this;
    }

    public void finalize() throws Throwable {
		super.finalize();
	}

    /**
     * Construye un objeto {@link edu.udistrital.plantae.logicadominio.datosespecimen.Especimen} Especimen en base a los atributos de la clase.
     */
    public void build() {
        especimen = new Especimen(numeroDeColeccion, viajeID, colectorPrincipalID);
        especimen.setFechaInicial(fechaInicial);
        especimen.setFechaFinal(new Date(System.currentTimeMillis()));
        especimen.setMetodoColeccion(metodoColeccion);
        especimen.setEstacionDelAño(estacionDelAño);
        especimen.setColectoresSecundarios(colectoresSecundarios);
        especimen.setLocalidadID(localidadID);
        if (identidadTaxonomica != null) {
            List<IdentidadTaxonomica> determinaciones = new ArrayList<IdentidadTaxonomica>(1);
            determinaciones.add(identidadTaxonomica);
            especimen.setDeterminaciones(determinaciones);
        }
        especimen.setHabitatID(habitatID);
        especimen.setHabitoID(habitoID);
        especimen.setFenologiaID(fenologiaID);
        especimen.setAlturaDeLaPlanta(alturaDeLaPlanta);
        especimen.setDap(dap);
        especimen.setAbundancia(abundancia);
        especimen.setDescripcionEspecimen(descripcionEspecimen);
        especimen.setColores(colores);
        especimen.setMuestrasAsociadas(muestrasAsociadas);
        especimen.setFotografias(fotografias);
        especimen.setFlorID(florID);
        especimen.setTipo("ES");
    }

    /**
     * Solo debe ser llamado despues de llamar a {@link #build()}
     * @return el especimen construido
     */
    public Especimen getEspecimen(){
		return especimen;
	}

}