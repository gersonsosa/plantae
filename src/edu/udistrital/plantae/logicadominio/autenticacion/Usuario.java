package edu.udistrital.plantae.logicadominio.autenticacion;

import android.text.TextUtils;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fenologia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habito;
import edu.udistrital.plantae.logicadominio.taxonomia.Taxon;
import edu.udistrital.plantae.logicadominio.taxonomia.Uso;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;
import edu.udistrital.plantae.persistencia.*;
import edu.udistrital.plantae.persistencia.UsuarioDao.Properties;

import java.util.HashMap;
import java.util.List;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 19-May-2013 11:50:35 PM
 */
public class Usuario {

    private Long id;
    private String nombreUsuario;
    private String contraseña;
    private static Integer[] erroresCredenciales;
    private Sesion sesion;
    private static Usuario usuario;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient UsuarioDao myDao;

    private List<ColorEspecimen> coloresEspecimen;
    private List<Fenologia> fenologias;
    private List<Habito> habitos;
    private List<Uso> usos;
    private List<Taxon> taxones;
    private List<Region> regiones;


    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUsuarioDao() : null;
    }

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombreUsuario nombre de usuario
	 * @param contraseña contraseña
	 */
	private Usuario(String nombreUsuario, String contraseña){
		this.nombreUsuario=nombreUsuario;
		this.contraseña=contraseña;
		sesion = Sesion.iniciarSesion(this);
	}

	public static Usuario getUsuario(String nombreUsuario, String contraseña) {
		if (usuario == null){
			usuario = new Usuario(nombreUsuario, contraseña);
		}
		// TODO Hacer que hale una excepción si el usuario ya existe?
		return usuario;
	}

	/**
	 * 
	 * @param datosRegistro Map con los datos que el usuario ingresó
	 * @param usuarioDao Data access object de usuario
	 */
	public static HashMap<String, String> validarDatosRegistro(HashMap<String,String> datosRegistro, UsuarioDao usuarioDao){
		// validar datos de un nuevo usuario
		String email = datosRegistro.get("email");
		String password = datosRegistro.get("password");
		String institution = datosRegistro.get("institution");
		String fullName = datosRegistro.get("fullname");
		HashMap<String, String> errores = new HashMap<String, String>();
		
		// No cargar todos los usuarios sino hacer la consulta directamente en la tabla
        Usuario usuarioExistente = usuarioDao.queryBuilder().where(Properties.NombreUsuario.eq(email)).unique();
        if (usuarioExistente != null) {
            errores.put("email", "taken");
        }
		
		if (!email.matches("^[a-zA-Z0-9]+[@]{1}[a-zA-Z.]+$")){
			errores.put("email", "invalid");
		}
		
		if (password.length()<6){
			errores.put("password", "short");
		} else if (password.matches("^[a-zA-Z0-9 ]+$")){
			errores.put("password", "simple");
		}
		if (!fullName.matches("[a-zA-Z ]+")){
			errores.put("fullname", "chars");
		}
		/*if (!fieldNumber.matches("[a-zA-Z]*[0-9]+")){
			errores.put("fieldnumber", "chars");
		}*/
		if (!institution.matches("^[a-zA-Z0-9 ]+$")){
			errores.put("institution", "chars");
		}
		return errores;
	}

	/**
	 * 
	 * @param contraseña
	 * @param nombreUsuario
     * @param usuarioDao Data access object de usuario
	 */
	public static Usuario validarDatosInicioSesion(String nombreUsuario, String contraseña, UsuarioDao usuarioDao){
		if (usuario == null){
			boolean cancel = false;
			erroresCredenciales = new Integer[2];
			if (TextUtils.isEmpty(contraseña)) {
				// La contraseña es obligatoria
				erroresCredenciales[0]=1;
				// Put the cancel flag to true.
				cancel = true;
			} else if (contraseña.length() < 4) {
				// La contraseña es obligatoria
                erroresCredenciales[0]=2;
				cancel = true;
			}

			// Check for a valid email address.
			if (TextUtils.isEmpty(nombreUsuario)) {
				// El nombre de usuario es obligatorio
				erroresCredenciales[1]=3;
				cancel = true;
			} else if (!nombreUsuario.contains("@")) {
				// El email es invalido
				erroresCredenciales[1]=4;
				cancel = true;
			}

			if (!cancel) {
				// Check username and password in database
				usuario = usuarioDao.queryBuilder().where(Properties.NombreUsuario.eq(nombreUsuario)).unique();
				if (usuario != null && usuario.getContraseña().equals(contraseña)){
					return usuario;
				}else{
                    // La contraseña o el nombre de usuario son erroneos
					erroresCredenciales[0]=5;
					usuario = null;
				}
			}
		}
		// There was an error; don't attempt login, return null
		return null;
	}

    public static void destruirUsuario(){
        usuario = null;
    }

	public static Integer[] getErroresCredenciales(){
		return erroresCredenciales;
	}

    public Sesion getSesion() {
        return sesion;
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

    public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 *
	 * @param nombreUsuario
	 */
    public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

    public String getContraseña() {
        return contraseña;
    }

    /**
     *
     * @param contraseña
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ColorEspecimen> getColoresEspecimen() {
        if (coloresEspecimen == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColorEspecimenDao targetDao = daoSession.getColorEspecimenDao();
            List<ColorEspecimen> coloresEspecimenNew = targetDao._queryUsuario_ColoresEspecimen(id);
            synchronized (this) {
                if(coloresEspecimen == null) {
                    coloresEspecimen = coloresEspecimenNew;
                }
            }
        }
        return coloresEspecimen;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetColoresEspecimen() {
        coloresEspecimen = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Fenologia> getFenologias() {
        if (fenologias == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FenologiaDao targetDao = daoSession.getFenologiaDao();
            List<Fenologia> fenologiasNew = targetDao._queryUsuario_Fenologias(id);
            synchronized (this) {
                if(fenologias == null) {
                    fenologias = fenologiasNew;
                }
            }
        }
        return fenologias;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetFenologias() {
        fenologias = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Habito> getHabitos() {
        if (habitos == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HabitoDao targetDao = daoSession.getHabitoDao();
            List<Habito> habitosNew = targetDao._queryUsuario_Habitos(id);
            synchronized (this) {
                if(habitos == null) {
                    habitos = habitosNew;
                }
            }
        }
        return habitos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetHabitos() {
        habitos = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Uso> getUsos() {
        if (usos == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsoDao targetDao = daoSession.getUsoDao();
            List<Uso> usosNew = targetDao._queryUsuario_Usos(id);
            synchronized (this) {
                if(usos == null) {
                    usos = usosNew;
                }
            }
        }
        return usos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetUsos() {
        usos = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Taxon> getTaxones() {
        if (taxones == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaxonDao targetDao = daoSession.getTaxonDao();
            List<Taxon> taxonesNew = targetDao._queryUsuario_Taxones(id);
            synchronized (this) {
                if(taxones == null) {
                    taxones = taxonesNew;
                }
            }
        }
        return taxones;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetTaxones() {
        taxones = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Region> getRegiones() {
        if (regiones == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RegionDao targetDao = daoSession.getRegionDao();
            List<Region> regionesNew = targetDao._queryUsuario_Regiones(id);
            synchronized (this) {
                if(regiones == null) {
                    regiones = regionesNew;
                }
            }
        }
        return regiones;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetRegiones() {
        regiones = null;
    }

}
