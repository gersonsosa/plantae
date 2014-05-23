package edu.udistrital.plantae.logicadominio.listasparametros;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habitat;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.HabitatDao;
import edu.udistrital.plantae.persistencia.HabitatsDao;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Habitats implements Iterator {

    private Long id;
    private List<Habitat> data;
    private Enumeration eht;
    private Habitat nextHabitat;

	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient HabitatsDao myDao;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHabitatsDao() : null;
    }

	public Habitats(){
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
    private List<Habitat> getData() {
        if (data == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HabitatDao targetDao = daoSession.getHabitatDao();
            List<Habitat> dataNew = targetDao._queryHabitats_Data(id);
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