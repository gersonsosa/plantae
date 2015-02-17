package edu.udistrital.plantae.logicadominio.recoleccion;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.datosespecimen.EspecimenColectorSecundario;
import edu.udistrital.plantae.persistencia.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @updated 08-Oct-2013 3:41:26 PM
 */
public class ColectorSecundario extends Persona {

    private Long id;
    private long personaID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ColectorSecundarioDao myDao;

    private Persona persona;
    private Long persona__resolvedKey;

    private List<ViajeColectorSecundario> viajes;
    private List<EspecimenColectorSecundario> especimenes;


    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getColectorSecundarioDao() : null;
    }

	public void finalize() throws Throwable {
		super.finalize();
	}

	public ColectorSecundario(){

	}

    @Override
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public long getPersonaID() {
        return personaID;
    }

    public void setPersonaID(long personaID) {
        this.personaID = personaID;
    }

    /** To-one relationship, resolved on first access. */
    public Persona getPersona() {
        long __key = this.personaID;
        if (persona__resolvedKey == null || !persona__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonaDao targetDao = daoSession.getPersonaDao();
            Persona personaNew = targetDao.load(__key);
            synchronized (this) {
                persona = personaNew;
            	persona__resolvedKey = __key;
            }
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        if (persona == null) {
            throw new DaoException("To-one property 'personaID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.persona = persona;
            personaID = persona.getId();
            persona__resolvedKey = personaID;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ViajeColectorSecundario> getViajes() {
        if (viajes == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ViajeColectorSecundarioDao targetDao = daoSession.getViajeColectorSecundarioDao();
            List<ViajeColectorSecundario> viajesNew = targetDao._queryColectorSecundario_Viajes(id);
            synchronized (this) {
                if(viajes == null) {
                    viajes = viajesNew;
                }
            }
        }
        return viajes;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetViajes() {
        viajes = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<EspecimenColectorSecundario> getEspecimenes() {
        if (especimenes == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EspecimenColectorSecundarioDao targetDao = daoSession.getEspecimenColectorSecundarioDao();
            List<EspecimenColectorSecundario> especimenesNew = targetDao._queryColectorSecundario_Especimenes(id);
            synchronized (this) {
                if(especimenes == null) {
                    especimenes = especimenesNew;
                }
            }
        }
        return especimenes;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void setEspecimenes() {
        especimenes = new ArrayList<EspecimenColectorSecundario>();
    }

}
