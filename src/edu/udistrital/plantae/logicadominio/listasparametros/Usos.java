package edu.udistrital.plantae.logicadominio.listasparametros;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.taxonomia.Uso;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.UsoDao;
import edu.udistrital.plantae.persistencia.UsosDao;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:39 PM
 */
public class Usos implements Iterator {

    private Long id;
    private List<Uso> data;
    private Enumeration eu;
    private Uso nextUso;

	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient UsosDao myDao;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUsosDao() : null;
    }

	public Usos(){
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

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    private List<Uso> getData() {
        if (data == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsoDao targetDao = daoSession.getUsoDao();
            List<Uso> dataNew = targetDao._queryUsos_Data(id);
            synchronized (this) {
                if(data == null) {
                    data = dataNew;
                }
            }
        }
        return data;
    }

	public void finalize() throws Throwable {
	}

	public boolean hasNext(){
		return false;
	}

	public Object next(){
		return null;
	}

	public void remove(){

	}
}