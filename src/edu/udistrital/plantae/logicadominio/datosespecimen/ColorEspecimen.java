package edu.udistrital.plantae.logicadominio.datosespecimen;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.persistencia.ColectorPrincipalDao;
import edu.udistrital.plantae.persistencia.ColorMunsellDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import android.graphics.Color;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @updated 08-Oct-2013 5:27:38 PM
 */
public class ColorEspecimen {

	private Long id;
	private String nombre;
	private String descripcion;
	private Color colorRGB;
	private ColorMunsell colorMunsell;
    private Long colorMunsellID;
    private Long coloresID;

    private Long colorMunsell__resolvedKey;

	/* greendao specific properties */
	/** Used to resolve relations */
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	private transient ColectorPrincipalDao myDao;

	public void finalize() throws Throwable {

	}

	public ColorEspecimen(){

	}

    public Color getColorRGB(){
        return colorRGB;
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

	/**
	 *
	 * @param newVal
	 */
	public void setColorRGB(Color newVal){
		colorRGB = newVal;
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

    public Long getColorMunsellID() {
        return colorMunsellID;
    }

    public void setColorMunsellID(Long colorMunsellID) {
        this.colorMunsellID = colorMunsellID;
    }

    public Long getColoresID() {
        return coloresID;
    }

    public void setColoresID(Long coloresID) {
        this.coloresID = coloresID;
    }

    /** To-one relationship, resolved on first access. */
    public ColorMunsell getColorMunsell() {
        Long __key = this.colorMunsellID;
        if (colorMunsell__resolvedKey == null || !colorMunsell__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorMunsellDao targetDao = daoSession.getColorMunsellDao();
            ColorMunsell colorMunsellNew = targetDao.load(__key);
            synchronized (this) {
                colorMunsell = colorMunsellNew;
            	colorMunsell__resolvedKey = __key;
            }
        }
        return colorMunsell;
	}

    public void setColorMunsell(ColorMunsell colorMunsell) {
        synchronized (this) {
            this.colorMunsell = colorMunsell;
            colorMunsellID = colorMunsell == null ? null : colorMunsell.getId();
            colorMunsell__resolvedKey = colorMunsellID;
        }
    }
	
	/** called by internal mechanisms, do not call yourself. */
	public void __setDaoSession(DaoSession daoSession) {
		// TODO Auto-generated method stub
		this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getColectorPrincipalDao() : null;
	}

}