package edu.udistrital.plantae.logicadominio.recoleccion;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColectorSecundarioDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.ViajeColectorSecundarioDao;
import edu.udistrital.plantae.persistencia.ViajeDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 20-nov-2014 06:41:12 a.m.
 */
public class ViajeColectorSecundario {

    private Long id;
    private Long viajeID;
    private Long colectorSecundarioID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ViajeColectorSecundarioDao myDao;

    private ColectorSecundario colectorSecundario;
    private Long colectorSecundario__resolvedKey;

    private Viaje viaje;
    private Long viaje__resolvedKey;



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getViajeColectorSecundarioDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViajeID() {
        return viajeID;
    }

    public void setViajeID(Long viajeID) {
        this.viajeID = viajeID;
    }

    public Long getColectorSecundarioID() {
        return colectorSecundarioID;
    }

    public void setColectorSecundarioID(Long colectorSecundarioID) {
        this.colectorSecundarioID = colectorSecundarioID;
    }

    /** To-one relationship, resolved on first access. */
    public ColectorSecundario getColectorSecundario() {
        Long __key = this.colectorSecundarioID;
        if (colectorSecundario__resolvedKey == null || !colectorSecundario__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColectorSecundarioDao targetDao = daoSession.getColectorSecundarioDao();
            ColectorSecundario colectorSecundarioNew = targetDao.load(__key);
            synchronized (this) {
                colectorSecundario = colectorSecundarioNew;
            	colectorSecundario__resolvedKey = __key;
            }
        }
        return colectorSecundario;
    }

    public void setColectorSecundario(ColectorSecundario colectorSecundario) {
        synchronized (this) {
            this.colectorSecundario = colectorSecundario;
            colectorSecundarioID = colectorSecundario == null ? null : colectorSecundario.getId();
            colectorSecundario__resolvedKey = colectorSecundarioID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Viaje getViaje() {
        Long __key = this.viajeID;
        if (viaje__resolvedKey == null || !viaje__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ViajeDao targetDao = daoSession.getViajeDao();
            Viaje viajeNew = targetDao.load(__key);
            synchronized (this) {
                viaje = viajeNew;
            	viaje__resolvedKey = __key;
            }
        }
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        synchronized (this) {
            this.viaje = viaje;
            viajeID = viaje == null ? null : viaje.getId();
            viaje__resolvedKey = viajeID;
        }
    }

}
