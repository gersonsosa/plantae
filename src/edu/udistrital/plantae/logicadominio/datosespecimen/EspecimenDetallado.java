package edu.udistrital.plantae.logicadominio.datosespecimen;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.logicadominio.recoleccion.Recoleccion;
import edu.udistrital.plantae.logicadominio.taxonomia.IdentidadTaxonomica;
import edu.udistrital.plantae.logicadominio.ubicacion.Localidad;
import edu.udistrital.plantae.persistencia.*;

import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class EspecimenDetallado implements Especimen {

    private Long id;
    private String numeroDeColeccion;
    private String abundancia;
    private String fenologia;
    private String descripcionEspecimen;
    private long alturaDeLaPlanta;
    private long dap;
    private long recoleccionID;
    private Long identidadTaxonomicaID;
    private Long habitoID;
    private Long habitatID;
    private Long localidadID;
    private Long raizID;
    private Long talloID;
    private Long inflorescenciaID;
    private Long frutoID;
    private Long florID;
    private Long hojaID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient EspecimenDao myDao;

    private Etiqueta etiqueta;
    private Habito habito;
    private Long habito__resolvedKey;

    private Habitat habitat;
    private Long habitat__resolvedKey;

    private Localidad localidad;
    private Long localidad__resolvedKey;

    private Inflorescencia inflorescencia;
    private Long inflorescencia__resolvedKey;

    private Hoja hoja;
    private Long hoja__resolvedKey;

    private Fruto fruto;
    private Long fruto__resolvedKey;

    private Tallo tallo;
    private Long tallo__resolvedKey;

    private Raiz raiz;
    private Long raiz__resolvedKey;

    private Flor flor;
    private Long flor__resolvedKey;

	private ColectorPrincipal colectorPrincipal;
	private Recoleccion recoleccion;
    private Long recoleccion__resolvedKey;

	private List<ColectorSecundario> colectoresSecundarios;
    private List<MuestraAsociada> muestrasAsociadas;
	private List<Fotografia> fotografias;
    private List<IdentidadTaxonomica> determinaciones;

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEspecimenDao() : null;
    }

	public EspecimenDetallado(){
	}

	public void finalize() throws Throwable {
	}

	/**
	 * 
	 * @param numeroDeColector
	 */
	public EspecimenDetallado(String numeroDeColector){
	}

	public void agregarTodosColectores(){

	}

    public Object clone(){
        return null;
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

    public String getNumeroDeColeccion() {
        return numeroDeColeccion;
    }

    public void setNumeroDeColeccion(String numeroDeColeccion) {
        this.numeroDeColeccion = numeroDeColeccion;
    }

    public String getAbundancia() {
        return abundancia;
    }

    public void setAbundancia(String abundancia) {
        this.abundancia = abundancia;
    }

    public String getFenologia() {
        return fenologia;
    }

    public void setFenologia(String fenologia) {
        this.fenologia = fenologia;
    }

    public String getDescripcionEspecimen() {
        return descripcionEspecimen;
    }

    public void setDescripcionEspecimen(String descripcionEspecimen) {
        this.descripcionEspecimen = descripcionEspecimen;
    }

    public long getAlturaDeLaPlanta() {
		return alturaDeLaPlanta;
	}

    public void setAlturaDeLaPlanta(Long alturaDeLaPlanta) {
        this.alturaDeLaPlanta = alturaDeLaPlanta;
	}

    public Long getDap() {
		return dap;
	}

    public void setDap(Long dap) {
        this.dap = dap;
	}

    public long getRecoleccionID() {
        return recoleccionID;
    }

    public void setRecoleccionID(long recoleccionID) {
        this.recoleccionID = recoleccionID;
    }

    public Long getIdentidadTaxonomicaID() {
        return identidadTaxonomicaID;
    }

    public void setIdentidadTaxonomicaID(Long identidadTaxonomicaID) {
        this.identidadTaxonomicaID = identidadTaxonomicaID;
    }

    public Long getHabitoID() {
        return habitoID;
    }

    public void setHabitoID(Long habitoID) {
        this.habitoID = habitoID;
    }

    public Long getHabitatID() {
        return habitatID;
    }

    public void setHabitatID(Long habitatID) {
        this.habitatID = habitatID;
    }

    public Long getLocalidadID() {
        return localidadID;
    }

    public void setLocalidadID(Long localidadID) {
        this.localidadID = localidadID;
    }

    public Long getRaizID() {
        return raizID;
    }

    public void setRaizID(Long raizID) {
        this.raizID = raizID;
    }

    public Long getTalloID() {
        return talloID;
    }

    public void setTalloID(Long talloID) {
        this.talloID = talloID;
    }

    public Long getInflorescenciaID() {
        return inflorescenciaID;
    }

    public void setInflorescenciaID(Long inflorescenciaID) {
        this.inflorescenciaID = inflorescenciaID;
    }

    public Long getFrutoID() {
        return frutoID;
    }

    public void setFrutoID(Long frutoID) {
        this.frutoID = frutoID;
    }

    public Long getFlorID() {
        return florID;
    }

    public void setFlorID(Long florID) {
        this.florID = florID;
    }

    public Long getHojaID() {
        return hojaID;
    }

    public void setHojaID(Long hojaID) {
        this.hojaID = hojaID;
    }

    /** To-one relationship, resolved on first access. */
    public Habito getHabito() {
        Long __key = this.habitoID;
        if (habito__resolvedKey == null || !habito__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HabitoDao targetDao = daoSession.getHabitoDao();
            Habito habitoNew = targetDao.load(__key);
            synchronized (this) {
                habito = habitoNew;
            	habito__resolvedKey = __key;
            }
        }
        return habito;
	}

	/**
	 *
	 * @param habito
	 */
    public void setHabito(Habito habito) {
        synchronized (this) {
            this.habito = habito;
            habitoID = habito == null ? null : habito.getId();
            habito__resolvedKey = habitoID;
        }
	}

    /** To-one relationship, resolved on first access. */
    public Habitat getHabitat() {
        Long __key = this.habitatID;
        if (habitat__resolvedKey == null || !habitat__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HabitatDao targetDao = daoSession.getHabitatDao();
            Habitat habitatNew = targetDao.load(__key);
            synchronized (this) {
                habitat = habitatNew;
            	habitat__resolvedKey = __key;
            }
        }
		return habitat;
	}

	/**
	 *
	 * @param habitat
	 */
    public void setHabitat(Habitat habitat) {
        synchronized (this) {
		    this.habitat = habitat;
            habitatID = habitat == null ? null : habitat.getId();
            habitat__resolvedKey = habitatID;
	    }
    }

    /** To-one relationship, resolved on first access. */
    public Localidad getLocalidad() {
        Long __key = this.localidadID;
        if (localidad__resolvedKey == null || !localidad__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LocalidadDao targetDao = daoSession.getLocalidadDao();
            Localidad localidadNew = targetDao.load(__key);
            synchronized (this) {
                localidad = localidadNew;
            	localidad__resolvedKey = __key;
            }
        }
		return localidad;
	}

	/**
	 *
	 * @param localidad
	 */
    public void setLocalidad(Localidad localidad) {
        synchronized (this) {
            this.localidad = localidad;
            localidadID = localidad == null ? null : localidad.getId();
            localidad__resolvedKey = localidadID;
        }
	}

    /** To-one relationship, resolved on first access. */
	public Inflorescencia getInflorescencia() {
        Long __key = this.inflorescenciaID;
        if (inflorescencia__resolvedKey == null || !inflorescencia__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            InflorescenciaDao targetDao = daoSession.getInflorescenciaDao();
            Inflorescencia inflorescenciaNew = targetDao.load(__key);
            synchronized (this) {
                inflorescencia = inflorescenciaNew;
            	inflorescencia__resolvedKey = __key;
            }
        }
        return inflorescencia;
	}

	public void setInflorescencia(Inflorescencia inflorescencia) {
        synchronized (this) {
		    this.inflorescencia = inflorescencia;
            inflorescenciaID = inflorescencia == null ? null : inflorescencia.getId();
            inflorescencia__resolvedKey = inflorescenciaID;
	    }
    }

    /** To-one relationship, resolved on first access. */
	public Hoja getHoja() {
        Long __key = this.hojaID;
        if (hoja__resolvedKey == null || !hoja__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HojaDao targetDao = daoSession.getHojaDao();
            Hoja hojaNew = targetDao.load(__key);
            synchronized (this) {
                hoja = hojaNew;
            	hoja__resolvedKey = __key;
            }
        }
		return hoja;
	}

	public void setHoja(Hoja hoja) {
        synchronized (this) {
		    this.hoja = hoja;
            hojaID = hoja == null ? null : hoja.getId();
            hoja__resolvedKey = hojaID;
	    }
    }

    /** To-one relationship, resolved on first access. */
	public Fruto getFruto() {
        Long __key = this.frutoID;
        if (fruto__resolvedKey == null || !fruto__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FrutoDao targetDao = daoSession.getFrutoDao();
            Fruto frutoNew = targetDao.load(__key);
            synchronized (this) {
                fruto = frutoNew;
            	fruto__resolvedKey = __key;
            }
        }
		return fruto;
	}

	public void setFruto(Fruto fruto) {
        synchronized (this) {
		    this.fruto = fruto;
            frutoID = fruto == null ? null : fruto.getId();
            fruto__resolvedKey = frutoID;
	    }
    }

    /** To-one relationship, resolved on first access. */
	public Tallo getTallo() {
        Long __key = this.talloID;
        if (tallo__resolvedKey == null || !tallo__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TalloDao targetDao = daoSession.getTalloDao();
            Tallo talloNew = targetDao.load(__key);
            synchronized (this) {
                tallo = talloNew;
            	tallo__resolvedKey = __key;
            }
        }
		return tallo;
	}

	public void setTallo(Tallo tallo) {
        synchronized (this) {
		    this.tallo = tallo;
            talloID = tallo == null ? null : tallo.getId();
            tallo__resolvedKey = talloID;
	    }
    }

    /** To-one relationship, resolved on first access. */
	public Raiz getRaiz() {
        Long __key = this.raizID;
        if (raiz__resolvedKey == null || !raiz__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RaizDao targetDao = daoSession.getRaizDao();
            Raiz raizNew = targetDao.load(__key);
            synchronized (this) {
                raiz = raizNew;
            	raiz__resolvedKey = __key;
            }
        }
		return raiz;
	}

	public void setRaiz(Raiz raiz) {
        synchronized (this) {
		    this.raiz = raiz;
            raizID = raiz == null ? null : raiz.getId();
            raiz__resolvedKey = raizID;
	    }
    }

    /** To-one relationship, resolved on first access. */
	public Flor getFlor() {
        Long __key = this.florID;
        if (flor__resolvedKey == null || !flor__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FlorDao targetDao = daoSession.getFlorDao();
            Flor florNew = targetDao.load(__key);
            synchronized (this) {
                flor = florNew;
            	flor__resolvedKey = __key;
            }
        }
		return flor;
	}

	public void setFlor(Flor flor) {
        synchronized (this) {
            this.flor = flor;
            florID = flor == null ? null : flor.getId();
            flor__resolvedKey = florID;
        }
	}

	public Etiqueta getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(Etiqueta etiqueta) {
		this.etiqueta = etiqueta;
	}

    /** To-one relationship, resolved on first access. */
	public Recoleccion getRecoleccion() {
        long __key = this.recoleccionID;
        if (recoleccion__resolvedKey == null || !recoleccion__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RecoleccionDao targetDao = daoSession.getRecoleccionDao();
            Recoleccion recoleccionNew = targetDao.load(__key);
            synchronized (this) {
                recoleccion = recoleccionNew;
            	recoleccion__resolvedKey = __key;
            }
        }
		return recoleccion;
	}

	public void setRecoleccion(Recoleccion recoleccion) {
        if (recoleccion == null) {
            throw new DaoException("To-one property 'recoleccionID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
		    this.recoleccion = recoleccion;
            recoleccionID = recoleccion.getId();
            recoleccion__resolvedKey = recoleccionID;
	    }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
	public List<ColectorSecundario> getColectoresSecundarios() {
        if (colectoresSecundarios == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColectorSecundarioDao targetDao = daoSession.getColectorSecundarioDao();
            List<ColectorSecundario> colectoresSecundariosNew = targetDao._queryEspecimen_ColectoresSecundarios(id);
            synchronized (this) {
                if(colectoresSecundarios == null) {
                    colectoresSecundarios = colectoresSecundariosNew;
                }
            }
        }
		return colectoresSecundarios;
	}

	public void setColectoresSecundarios(List<ColectorSecundario> colectoresSecundarios) {
		this.colectoresSecundarios = colectoresSecundarios;
	}

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<MuestraAsociada> getMuestrasAsociadas() {
        if (muestrasAsociadas == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MuestraAsociadaDao targetDao = daoSession.getMuestraAsociadaDao();
            List<MuestraAsociada> muestrasAsociadasNew = targetDao._queryEspecimen_MuestrasAsociadas(id);
            synchronized (this) {
                if(muestrasAsociadas == null) {
                    muestrasAsociadas = muestrasAsociadasNew;
                }
            }
        }
        return muestrasAsociadas;
	}

	public void setMuestraAsociada(List<MuestraAsociada> muestrasAsociadas) {
		this.muestrasAsociadas = muestrasAsociadas;
	}

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
	public List<Fotografia> getFotografias() {
        if (fotografias == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FotografiaDao targetDao = daoSession.getFotografiaDao();
            List<Fotografia> fotografiasNew = targetDao._queryEspecimen_Fotografias(id);
            synchronized (this) {
                if(fotografias == null) {
                    fotografias = fotografiasNew;
                }
            }
        }
		return fotografias;
	}

	public void setFotografias(List<Fotografia> fotografias) {
		this.fotografias = fotografias;
	}

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<IdentidadTaxonomica> getDeterminaciones() {
        if (determinaciones == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IdentidadTaxonomicaDao targetDao = daoSession.getIdentidadTaxonomicaDao();
            List<IdentidadTaxonomica> determinacionesNew = targetDao._queryEspecimen_Determinaciones(id);
            synchronized (this) {
                if(determinaciones == null) {
                    determinaciones = determinacionesNew;
                }
            }
        }
        return determinaciones;
    }

    public String generarNumeroDeColeccion(){
		return "";
	}

	/**
	 * 
	 * @param muestra
	 */
	public void agregarMuestraAsociada(MuestraAsociada muestra){

	}

	/**
	 * 
	 * @param colectorSecundario
	 */
	public void quitarColector(ColectorSecundario colectorSecundario){

	}

	/**
	 * 
	 * @param fotografía
	 */
	public void agregarFotografia(Fotografia fotografía){

	}

}