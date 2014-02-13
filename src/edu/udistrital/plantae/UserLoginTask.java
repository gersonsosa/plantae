package edu.udistrital.plantae;

import java.sql.SQLException;

import android.content.Context;
import android.os.AsyncTask;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.persistencia.DatabaseHelper;
import edu.udistrital.plantae.persistencia.DatabaseManager;

/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
public class UserLoginTask extends AsyncTask<String, Void, Boolean> {
	
	private LoginActivity context;
	private String mEmail;
	private String mPassword;
	
	public UserLoginTask(Context context) {
		// TODO Auto-generated constructor stub
		this.context=(LoginActivity)context;
	}
	
	@Override
	protected Boolean doInBackground(String... credentials) {
		mEmail=credentials[0];
		mPassword=credentials[1];
		// TODO: attempt authentication against a network service.

		// Simulate network access.
		DatabaseHelper databaseHelper=DatabaseManager.getHelper(context.getApplicationContext());
		try {
			Dao<Usuario, String> usuarioDao=databaseHelper.getUsuarioDao();
			QueryBuilder<Usuario, String> usuarioQueryBuilder = usuarioDao.queryBuilder();
			usuarioQueryBuilder.where().eq("nombreUsuario", mEmail);
//				Dao<Persona, String> personaDao=databaseHelper.getPersonaDao();
//				usuarioQueryBuilder.leftJoin(personaDao.queryBuilder());
//				Dao<ColectorPrincipal, String> colectorPrincipalDao=databaseHelper.getColectorPrincipalDao();
//				usuarioQueryBuilder.leftJoin(colectorPrincipalDao.queryBuilder());
			PreparedQuery<Usuario> query=usuarioQueryBuilder.prepare();
			Usuario usuario=usuarioDao.queryForFirst(query);
			if (usuario!=null&&usuario.getcontraseña().equals(mPassword)){
				// Verifies the password and user name.
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	protected void onPostExecute(final Boolean success) {
		//mAuthTask = null;
		context.finishLoginTask(success);

		/*if (success) {
			SharedPreferences preferences = context.getSharedPreferences("plantae_prefs", 0);
			SharedPreferences.Editor editor = preferences.edit();
	        editor.putBoolean("isLoggedIn", true);
	        editor.commit();
	        Intent intent = new Intent(context,MainActivity.class);
	        context.startActivity(intent);
			context.finish();
		} else {
			mPasswordView.setError(getString(R.string.error_incorrect_password));
			/mPasswordView.requestFocus();
		}*/
	}

	@Override
	protected void onCancelled() {
		//mAuthTask = null;
		context.showProgress(false);
	}
	
}
