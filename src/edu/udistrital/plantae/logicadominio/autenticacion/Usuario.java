package edu.udistrital.plantae.logicadominio.autenticacion;

import android.text.TextUtils;
import de.greenrobot.dao.DaoException;
import edu.udistrital.plantae.logicadominio.listasparametros.Colores;
import edu.udistrital.plantae.logicadominio.listasparametros.Habitats;
import edu.udistrital.plantae.logicadominio.listasparametros.Habitos;
import edu.udistrital.plantae.logicadominio.listasparametros.Usos;
import edu.udistrital.plantae.persistencia.*;
import edu.udistrital.plantae.persistencia.UsuarioDao.Properties;

import java.util.HashMap;
import java.util.Iterator;
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

    private Long coloresID;
    private Long habitatsID;
    private Long habitosID;
    private Long usosID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient UsuarioDao myDao;

    private Colores colores;
    private Long colores__resolvedKey;

    private Habitats habitats;
    private Long habitats__resolvedKey;

    private Habitos habitos;
    private Long habitos__resolvedKey;

    private Usos usos;
    private Long usos__resolvedKey;

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
	 * @param nombreUsuario
	 * @param contraseña
	 */
	private Usuario(String nombreUsuario, String contraseña){
		this.nombreUsuario=nombreUsuario;
		this.contraseña=contraseña;
		sesion = Sesion.iniciarSesion(this);
	}

	public static Usuario getUsuario(String nombreUsuario, String contraseña) {
		if (usuario == null){
			usuario = new Usuario(nombreUsuario, contraseña);
            usuario.setColores(null);
            usuario.setHabitats(null);
            usuario.setHabitos(null);
            usuario.setUsos(null);
		}
		// TODO Hacer que hale una excepción si el usuario ya existe?
		return usuario;
	}

	/**
	 * 
	 * @param datosRegistro
	 * @param usuarioDao
	 */
	public static HashMap<String, String> validarDatosRegistro(HashMap<String,String> datosRegistro, UsuarioDao usuarioDao){
		// TODO validar datos de un nuevo usuario
		String email = datosRegistro.get("email");
		String password = datosRegistro.get("password");
		String institution = datosRegistro.get("institution");
		String fullName = datosRegistro.get("fullname");
		HashMap<String, String> errores = new HashMap<String, String>();
		
		List<Usuario> usuarios = usuarioDao.loadAll();
		Iterator<Usuario> iterator = usuarios.iterator();
		while (iterator.hasNext()) {
			Usuario usuario = (Usuario) iterator.next();
			if (usuario.getNombreUsuario().equals(email)){
				errores.put("email", "taken");
			}
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
				// TODO Check username and password in database
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

    public Sesion getSesion() {
        return sesion;
    }

    public Long getColoresID() {
        return coloresID;
    }

    public void setColoresID(Long coloresID) {
        this.coloresID = coloresID;
    }

    public Long getHabitatsID() {
        return habitatsID;
    }

    public void setHabitatsID(Long habitatsID) {
        this.habitatsID = habitatsID;
    }

    public Long getHabitosID() {
        return habitosID;
    }

    public void setHabitosID(Long habitosID) {
        this.habitosID = habitosID;
    }

    public Long getUsosID() {
        return usosID;
    }

    public void setUsosID(Long usosID) {
        this.usosID = usosID;
    }

    /** To-one relationship, resolved on first access. */
    public Colores getColores() {
        Long __key = this.coloresID;
        if (colores__resolvedKey == null || !colores__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ColoresDao targetDao = daoSession.getColoresDao();
            Colores coloresNew = targetDao.load(__key);
            synchronized (this) {
                colores = coloresNew;
            	colores__resolvedKey = __key;
            }
        }
        return colores;
    }

    public void setColores(Colores colores) {
        synchronized (this) {
            this.colores = colores;
            coloresID = colores == null ? null : colores.getId();
            colores__resolvedKey = coloresID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Habitats getHabitats() {
        Long __key = this.habitatsID;
        if (habitats__resolvedKey == null || !habitats__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HabitatsDao targetDao = daoSession.getHabitatsDao();
            Habitats habitatsNew = targetDao.load(__key);
            synchronized (this) {
                habitats = habitatsNew;
            	habitats__resolvedKey = __key;
            }
        }
        return habitats;
    }

    public void setHabitats(Habitats habitats) {
        synchronized (this) {
            this.habitats = habitats;
            habitatsID = habitats == null ? null : habitats.getId();
            habitats__resolvedKey = habitatsID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Habitos getHabitos() {
        Long __key = this.habitosID;
        if (habitos__resolvedKey == null || !habitos__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HabitosDao targetDao = daoSession.getHabitosDao();
            Habitos habitosNew = targetDao.load(__key);
            synchronized (this) {
                habitos = habitosNew;
            	habitos__resolvedKey = __key;
            }
        }
        return habitos;
    }

    public void setHabitos(Habitos habitos) {
        synchronized (this) {
            this.habitos = habitos;
            habitosID = habitos == null ? null : habitos.getId();
            habitos__resolvedKey = habitosID;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Usos getUsos() {
        Long __key = this.usosID;
        if (usos__resolvedKey == null || !usos__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsosDao targetDao = daoSession.getUsosDao();
            Usos usosNew = targetDao.load(__key);
            synchronized (this) {
                usos = usosNew;
            	usos__resolvedKey = __key;
            }
        }
        return usos;
    }

    public void setUsos(Usos usos) {
        synchronized (this) {
            this.usos = usos;
            usosID = usos == null ? null : usos.getId();
            usos__resolvedKey = usosID;
        }
    }

	public Long getId(){
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}

}
