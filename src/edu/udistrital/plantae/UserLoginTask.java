package edu.udistrital.plantae;

import android.content.Context;
import android.os.AsyncTask;

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
		try {
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
