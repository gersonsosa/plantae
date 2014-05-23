package edu.udistrital.plantae.logicadominio.taxonomia;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.TaxonDao;
import edu.udistrital.plantae.persistencia.UsoDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:39 PM
 */
public class Uso {

    private Long id;
    private String descripcion;
    private Long usosID;
    private long taxonID;

	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient UsoDao myDao;

    private Taxon taxon;
    private Long taxon__resolvedKey;



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUsoDao() : null;
    }

	public Uso(){
	}

	public void finalize() throws Throwable {
	}

	/**
	 *
	 * @param nombre
	 */
	public Uso(String nombre){
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

    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getUsosID() {
        return usosID;
    }

    public void setUsosID(Long usosID) {
        this.usosID = usosID;
    }

    public long getTaxonID() {
        return taxonID;
    }

    public void setTaxonID(long taxonID) {
        this.taxonID = taxonID;
    }

    /** To-one relationship, resolved on first access. */
    public Taxon getTaxon() {
        long __key = this.taxonID;
        if (taxon__resolvedKey == null || !taxon__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaxonDao targetDao = daoSession.getTaxonDao();
            Taxon taxonNew = targetDao.load(__key);
            synchronized (this) {
                taxon = taxonNew;
            	taxon__resolvedKey = __key;
            }
        }
        return taxon;
    }

    public void setTaxon(Taxon taxon) {
        if (taxon == null) {
            throw new DaoException("To-one property 'taxonID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.taxon = taxon;
            taxonID = taxon.getId();
            taxon__resolvedKey = taxonID;
        }
    }

}