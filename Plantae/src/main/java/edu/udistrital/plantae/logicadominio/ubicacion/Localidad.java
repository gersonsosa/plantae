package edu.udistrital.plantae.logicadominio.ubicacion;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.LocalidadDao;
import edu.udistrital.plantae.persistencia.RegionDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 11:33:38 PM
 */
public class Localidad {

    private Long id;
    private String nombre;
    private String datum;
    private String descripcion;
    private String marcaDispositivo;
    private Double latitud;
    private Double longitud;
    private Double altitudMinima;
    private Double altitudMaxima;
    private long regionID;

	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient LocalidadDao myDao;

    private Region region;
    private Long region__resolvedKey;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLocalidadDao() : null;
    }

	public Localidad(){
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre    nombre
	 */
	public Localidad(String nombre){
        this.nombre = nombre;
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

    public String getNombre() {
		return nombre;
	}

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
	}

    public String getDatum() {
		return datum;
	}

    /**
     *
     * @param datum
     */
    public void setDatum(String datum) {
        this.datum = datum;
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

    public String getMarcaDispositivo() {
        return marcaDispositivo;
    }

    /**
     *
     * @param marcaDispositivo
     */
    public void setMarcaDispositivo(String marcaDispositivo) {
        this.marcaDispositivo = marcaDispositivo;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getAltitudMinima() {
        return altitudMinima;
    }

    public void setAltitudMinima(Double altitudMinima) {
        this.altitudMinima = altitudMinima;
    }

    public Double getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(Double altitudMaxima) {
        this.altitudMaxima = altitudMaxima;
    }

    public long getRegionID() {
        return regionID;
    }

    public void setRegionID(long regionID) {
        this.regionID = regionID;
    }

    /** To-one relationship, resolved on first access. */
    public Region getRegion() {
        long __key = this.regionID;
        if (region__resolvedKey == null || !region__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RegionDao targetDao = daoSession.getRegionDao();
            Region regionNew = targetDao.load(__key);
            synchronized (this) {
                region = regionNew;
            	region__resolvedKey = __key;
            }
        }
		return region;
	}

	/**
	 *
	 * @param region
	 */
    public void setRegion(Region region) {
        if (region == null) {
            throw new DaoException("To-one property 'regionID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.region = region;
            regionID = region.getId();
            region__resolvedKey = regionID;
        }
    }

    @Override
    public String toString() {
        return nombre +
                (getRegion() != null ? ", " + region : "") +
                ", latitud: " + latitud +
                ", longitud: " + longitud +
                (descripcion != null ? ", " + descripcion : "");
    }
}