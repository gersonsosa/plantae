package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.persistencia.ColectorSecundarioDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.EspecimenColectorSecundarioDao;
import edu.udistrital.plantae.persistencia.EspecimenDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 20-nov-2014 06:42:12 a.m.
 */
public class EspecimenColectorSecundario implements Parcelable {

    private Long id;
    private Long especimenID;
    private Long colectorSecundarioID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient EspecimenColectorSecundarioDao myDao;

    private ColectorSecundario colectorSecundario;
    private Long colectorSecundario__resolvedKey;

    private Especimen especimen;
    private Long especimen__resolvedKey;



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEspecimenColectorSecundarioDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEspecimenID() {
        return especimenID;
    }

    public void setEspecimenID(Long especimenID) {
        this.especimenID = especimenID;
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
    public Especimen getEspecimen() {
        Long __key = this.especimenID;
        if (especimen__resolvedKey == null || !especimen__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EspecimenDao targetDao = daoSession.getEspecimenDao();
            Especimen especimenNew = targetDao.load(__key);
            synchronized (this) {
                especimen = especimenNew;
            	especimen__resolvedKey = __key;
            }
        }
        return especimen;
    }

    public void setEspecimen(Especimen especimen) {
        synchronized (this) {
            this.especimen = especimen;
            especimenID = especimen == null ? null : especimen.getId();
            especimen__resolvedKey = especimenID;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.especimenID);
        dest.writeValue(this.colectorSecundarioID);
        dest.writeValue(this.colectorSecundario__resolvedKey);
        dest.writeValue(this.especimen__resolvedKey);
    }

    public EspecimenColectorSecundario() {
    }

    private EspecimenColectorSecundario(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.especimenID = (Long) in.readValue(Long.class.getClassLoader());
        this.colectorSecundarioID = (Long) in.readValue(Long.class.getClassLoader());
        this.colectorSecundario__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.especimen__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<EspecimenColectorSecundario> CREATOR = new Parcelable.Creator<EspecimenColectorSecundario>() {
        public EspecimenColectorSecundario createFromParcel(Parcel source) {
            return new EspecimenColectorSecundario(source);
        }

        public EspecimenColectorSecundario[] newArray(int size) {
            return new EspecimenColectorSecundario[size];
        }
    };
}
