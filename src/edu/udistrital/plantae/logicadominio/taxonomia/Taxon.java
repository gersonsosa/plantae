package edu.udistrital.plantae.logicadominio.taxonomia;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.NombreComunDao;
import edu.udistrital.plantae.persistencia.TaxonDao;
import edu.udistrital.plantae.persistencia.UsoDao;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public abstract class Taxon {

    private Long id;
    private String nombre;
    private List<Uso> usos;
    private List<NombreComun> nombresComunes;
	private Taxon taxonPadre;
	private String autor;
	private String nombreCientifico;
	
	/* greendao specific properties */
	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient TaxonDao myDao;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaxonDao() : null;
    }

	public Taxon(){
	}

	public void finalize() throws Throwable {
	}

    public Taxon getTaxonPadre(){
        return taxonPadre;
    }

    public void setTaxonPadre(Taxon taxonPadre) {
        this.taxonPadre = taxonPadre;
    }

    /**
	 * 
	 * @param nombre
	 */
	public Taxon(String nombre){
	}

    public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
    public void setId(Long id) {
		this.id = id;
	}

	public String getNombre(){
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

    public String getAutor() {
        return autor;
    }

    /**
     *
     * @param autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    /**
     *
     * @param nombreCientifico
     */
    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<NombreComun> getNombresComunes() {
        if (nombresComunes == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NombreComunDao targetDao = daoSession.getNombreComunDao();
            List<NombreComun> nombresComunesNew = targetDao._queryTaxon_NombresComunes(id);
            synchronized (this) {
                if(nombresComunes == null) {
                    nombresComunes = nombresComunesNew;
                }
            }
        }
        return nombresComunes;
    }

    /**
     *
     * @param nombresComunes
     */
    public void setNombresComunes(List<NombreComun> nombresComunes){
        this.nombresComunes = nombresComunes;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Uso> getUsos() {
        if (usos == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsoDao targetDao = daoSession.getUsoDao();
            List<Uso> usosNew = targetDao._queryTaxon_Usos(id);
            synchronized (this) {
                if(usos == null) {
                    usos = usosNew;
                }
            }
        }
        return usos;
    }

    /**
     *
     * @param usos
     */
    public void setUsos(List<Uso> usos){
        this.usos = usos;
    }

}