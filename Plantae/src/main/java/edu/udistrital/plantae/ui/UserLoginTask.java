package edu.udistrital.plantae.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.persistencia.ColectorPrincipalDao;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.PersonaDao;
import edu.udistrital.plantae.persistencia.UsuarioDao;

/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
public class UserLoginTask extends AsyncTask<String, Void, Boolean> {
	
	/**
	 * Dummy credentials
	 */
	public static final String[] DUMMY_CREDENTIALS = new String[]{
		"foo@example.com:hello", "bar@example.com:world"
	};
	
	private LoginActivity context;
	private UsuarioDao usuarioDao;
    private PersonaDao personaDao;
    private ColectorPrincipalDao colectorPrincipalDao;
    private ColectorPrincipal colectorPrincipal;
	
	public UserLoginTask(Context context) {
		this.context=(LoginActivity)context;
		usuarioDao = DataBaseHelper.getDataBaseHelper(context).getDaoSession().getUsuarioDao();
        personaDao = DataBaseHelper.getDataBaseHelper(context).getDaoSession().getPersonaDao();
        colectorPrincipalDao = DataBaseHelper.getDataBaseHelper(context).getDaoSession().getColectorPrincipalDao();
	}
	
	@Override
	protected Boolean doInBackground(String... credentials) {
        String mEmail=credentials[0];
        String mPassword=credentials[1];
		// Validate the user object password and email
		Usuario usuario = Usuario.validarDatosInicioSesion(mEmail, mPassword, usuarioDao);
		if (usuario == null){
			return false;
		}

        SharedPreferences preferences = context.getSharedPreferences("plantae_prefs", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("idUsuario", usuario.getId());
        editor.commit();

        Persona persona = personaDao.queryBuilder().where(PersonaDao.Properties.UsuarioID.eq(usuario.getId())).unique();
        colectorPrincipal = colectorPrincipalDao.queryBuilder().where(ColectorPrincipalDao.Properties.PersonaID.eq(persona.getId())).unique();

		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			return false;
		}
		
		for (String credential : DUMMY_CREDENTIALS) {
			String[] pieces = credential.split(":");
			if (pieces[0].equals(mEmail)){
				//Account exists, return true if the password matches.
				return pieces[1].equals(mPassword);
			}
		}*/
		return true;
	}

	@Override
	protected void onPostExecute(final Boolean success) {
		context.finishLoginTask(colectorPrincipal);
	}

	@Override
	protected void onCancelled() {
		context.showProgress(false);
	}
	
}
