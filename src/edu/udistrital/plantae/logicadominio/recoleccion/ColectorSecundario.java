package edu.udistrital.plantae.logicadominio.recoleccion;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.persistencia.ColectorSecundarioDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.PersonaDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @updated 08-Oct-2013 3:41:26 PM
 */
public class ColectorSecundario extends Persona {

	private Long id;
    private Long viajeID;
    private Long especimenID;
    private long personaID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ColectorSecundarioDao myDao;

	private Persona persona;
    private Long persona__resolvedKey;

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
    public Long getId(){
        return id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(Long id){
        this.id = id;
    }

    public Long getViajeID() {
        return viajeID;
    }

    public void setViajeID(Long viajeID) {
        this.viajeID = viajeID;
    }

    public Long getEspecimenID() {
        return especimenID;
    }

    public void setEspecimenID(Long especimenID) {
        this.especimenID = especimenID;
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

}
