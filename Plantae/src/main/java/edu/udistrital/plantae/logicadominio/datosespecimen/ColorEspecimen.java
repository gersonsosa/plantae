package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.graphics.Color;

import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.persistencia.ColorEspecimenDao;
import edu.udistrital.plantae.persistencia.ColorMunsellDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.EspecimenDao;
import edu.udistrital.plantae.persistencia.UsuarioDao;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @updated 26-Ago-2014 6:35:38 PM
 */
public class ColorEspecimen {

    private Long id;
    private String nombre;
    private String descripcion;
    private String organoDeLaPlanta;
    private Integer colorRGB;
    private Long colorMunsellID;
    private Long usuarioID;
    private Long especimenID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

	/** Used for active entity operations. */
    private transient ColorEspecimenDao myDao;

    private ColorMunsell colorMunsell;
    private Long colorMunsell__resolvedKey;

    private Especimen especimen;
    private Long especimen__resolvedKey;

    private Usuario usuario;
    private Long usuario__resolvedKey;

	public void finalize() throws Throwable {
	}

	public ColorEspecimen(){
    }



    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getColorEspecimenDao() : null;
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

    public String getOrganoDeLaPlanta() {
        return organoDeLaPlanta;
    }

    public void setOrganoDeLaPlanta(String organoDeLaPlanta) {
        this.organoDeLaPlanta = organoDeLaPlanta;
    }

    public Integer getColorRGB() {
        return colorRGB;
    }

    /**
     *
     * @param colorRGB
     */
    public void setColorRGB(Integer colorRGB) {
        this.colorRGB = colorRGB;
    }

    public Long getColorMunsellID() {
        return colorMunsellID;
    }

    public void setColorMunsellID(Long colorMunsellID) {
        this.colorMunsellID = colorMunsellID;
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Long getEspecimenID() {
        return especimenID;
    }

    public void setEspecimenID(Long especimenID) {
        this.especimenID = especimenID;
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
    public Usuario getUsuario() {
        Long __key = this.usuarioID;
        if (usuario__resolvedKey == null || !usuario__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsuarioDao targetDao = daoSession.getUsuarioDao();
            Usuario usuarioNew = targetDao.load(__key);
            synchronized (this) {
                usuario = usuarioNew;
            	usuario__resolvedKey = __key;
            }
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        synchronized (this) {
            this.usuario = usuario;
            usuarioID = usuario == null ? null : usuario.getId();
            usuario__resolvedKey = usuarioID;
        }
    }

    public String getColorName() {
        String colorName = "";
        String hexColor = String.format("#%06X", (0xFFFFFF & colorRGB));
        String[] commonColorNames = {"red", "blue", "green", "black", "white", "gray", "cyan", "magenta",
                "yellow", "lightgray", "darkgray", "grey", "lightgrey", "darkgrey",
                "aqua", "fuchsia", "lime", "maroon", "navy", "olive", "purple",
                "silver", "teal"};
        for (String commonColorname : commonColorNames) {
            if (Color.parseColor(commonColorname) == colorRGB) {
                colorName = commonColorname;
            }
        }
        return colorName;
    }

    public String aString() {
        String string = "";
        if (nombre != null) {
            string = string + (string.equals("") ? "":", ") + nombre;
        }
        if (descripcion != null) {
            string = string + (string.equals("") ? "":", ") + descripcion;
        }
        return string;
    }

    @Override
    public String toString() {
        return "ColorEspecimen{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", organoDeLaPlanta='" + organoDeLaPlanta + '\'' +
                ", colorRGB=" + colorRGB +
                ", colorMunsellID=" + colorMunsellID +
                ", usuarioID=" + usuarioID +
                ", especimenID=" + especimenID +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", colorMunsell=" + colorMunsell +
                ", colorMunsell__resolvedKey=" + colorMunsell__resolvedKey +
                ", especimen=" + especimen +
                ", especimen__resolvedKey=" + especimen__resolvedKey +
                ", usuario=" + usuario +
                ", usuario__resolvedKey=" + usuario__resolvedKey +
                '}';
    }
}