package edu.udistrital.plantae.logicadominio.taxonomia;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.EspecimenDao;
import edu.udistrital.plantae.persistencia.IdentidadTaxonomicaDao;
import edu.udistrital.plantae.persistencia.PersonaDao;
import edu.udistrital.plantae.persistencia.TaxonDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class IdentidadTaxonomica implements Parcelable {

    private Long id;
    private Date fechaIdentificacion;
    private String tipo;
    private Persona determinador;
    private Especimen especimen;
    private Taxon taxon;

    private Long especimenID;
    private long taxonID;

    private Long personaID;

	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient IdentidadTaxonomicaDao myDao;

    private Long determinador__resolvedKey;
    private Long especimen__resolvedKey;
    private Long taxon__resolvedKey;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getIdentidadTaxonomicaDao() : null;
    }

	public IdentidadTaxonomica(){
	}

    public void finalize() throws Throwable {
    }

    /**
     *
     * @param determinador
     * @param taxon
     */
    public IdentidadTaxonomica(Persona determinador, Taxon taxon){

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

	public Date getFechaIdentificacion() {
		return fechaIdentificacion;
	}

	/**
	 *
	 * @param fechaIdentificacion
	 */
	public void setFechaIdentificacion(Date fechaIdentificacion){
		this.fechaIdentificacion = fechaIdentificacion;
	}

    public String getTipo() {
		return tipo;
	}

    public void setTipo(String tipo) {
        this.tipo = tipo;
	}

    public Long getEspecimenID() {
        return especimenID;
    }

    public void setEspecimenID(Long especimenID) {
        this.especimenID = especimenID;
    }

    public long getTaxonID() {
        return taxonID;
    }

    public void setTaxonID(long taxonID) {
        this.taxonID = taxonID;
    }

    public Long getPersonaID() {
        return personaID;
    }

    public void setPersonaID(Long personaID) {
        this.personaID = personaID;
    }

    /** To-one relationship, resolved on first access. */
    public Persona getDeterminador() {
        Long __key = this.personaID;
        if (determinador__resolvedKey == null || !determinador__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonaDao targetDao = daoSession.getPersonaDao();
            Persona determinadorNew = targetDao.load(__key);
            synchronized (this) {
                determinador = determinadorNew;
            	determinador__resolvedKey = __key;
            }
        }
		return determinador;
	}

	/**
	 *
	 * @param determinador
	 */
    public void setDeterminador(Persona determinador) {
        synchronized (this) {
            this.determinador = determinador;
            personaID = determinador == null ? null : determinador.getId();
            determinador__resolvedKey = personaID;
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


	/**
	 *
	 * @param taxon
	 */
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeLong(fechaIdentificacion != null ? fechaIdentificacion.getTime() : -1);
        dest.writeString(this.tipo);
        dest.writeValue(this.especimenID);
        dest.writeLong(this.taxonID);
        dest.writeValue(this.personaID);
        dest.writeValue(this.determinador__resolvedKey);
        dest.writeValue(this.especimen__resolvedKey);
        dest.writeValue(this.taxon__resolvedKey);
    }

    private IdentidadTaxonomica(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        long tmpFechaIdentificacion = in.readLong();
        this.fechaIdentificacion = tmpFechaIdentificacion == -1 ? null : new Date(tmpFechaIdentificacion);
        this.tipo = in.readString();
        this.especimenID = (Long) in.readValue(Long.class.getClassLoader());
        this.taxonID = in.readLong();
        this.personaID = (Long) in.readValue(Long.class.getClassLoader());
        this.determinador__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.especimen__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
        this.taxon__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<IdentidadTaxonomica> CREATOR = new Parcelable.Creator<IdentidadTaxonomica>() {
        public IdentidadTaxonomica createFromParcel(Parcel source) {
            return new IdentidadTaxonomica(source);
        }

        public IdentidadTaxonomica[] newArray(int size) {
            return new IdentidadTaxonomica[size];
        }
    };

    public String aString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String string = "";
        if (taxon != null) {
            string = taxon.aString();
        }
        if (fechaIdentificacion != null) {
            string = string + (string.equals("") ? "" : ", ") + simpleDateFormat.format(fechaIdentificacion);
        }
        if (tipo != null) {
            string = string + (string.equals("") ? "" : ", ") + tipo;
        }
        if (determinador != null) {
            string = string + (string.equals("") ? "" : ", ") + determinador.aString();
        }
        return string;
    }

    @Override
    public String toString() {
        return "IdentidadTaxonomica{" +
                "id=" + id +
                ", fechaIdentificacion=" + fechaIdentificacion +
                ", tipo='" + tipo + '\'' +
                ", determinador=" + determinador +
                ", especimen=" + especimen +
                ", taxon=" + taxon +
                ", especimenID=" + especimenID +
                ", taxonID=" + taxonID +
                ", personaID=" + personaID +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", determinador__resolvedKey=" + determinador__resolvedKey +
                ", especimen__resolvedKey=" + especimen__resolvedKey +
                ", taxon__resolvedKey=" + taxon__resolvedKey +
                '}';
    }
}