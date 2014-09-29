package edu.udistrital.plantae;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.autenticacion.Sesion;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.persistencia.*;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Activity which displays a register screen to the user, offering registration as
 * well.
 */
public class RegisterActivity extends Activity {

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the register task to ensure we can cancel it if requested.
	 */
	private UserRegisterTask mRegisterTask = null;

	// Values for email and password at the time of the register attempt.
	private String mEmail;
	private String mPassword;
	private String mFullName;
	private String mInstitution;
	private String mFieldNumber;
    private int mCollectorDataType;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private EditText mFullNameView;
	private EditText mFieldNumberView;
	private EditText mInstitutionView;
    private CheckBox mCollectorDataTypeView;
	private View mRegisterFormView;
	private View mRegisterStatusView;
	private TextView mRegisterStatusMessageView;
	private ColectorPrincipalDao colectorPrincipalDao;
	private PersonaDao personaDao;
	private UsuarioDao usuarioDao;
    private DaoSession daoSession;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_register);
		setupActionBar();

		// Set up the register form.
		mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.register_email);
		mEmailView.setText(mEmail);

		mPasswordView = (EditText) findViewById(R.id.register_password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.register || id == EditorInfo.IME_NULL) {
							attemptRegister();
							return true;
						}
						return false;
					}
				});
		
		mFieldNumberView = (EditText) findViewById(R.id.collector_number);
		
		mFullNameView = (EditText) findViewById(R.id.fullname);
		
		mInstitutionView = (EditText) findViewById(R.id.institution);

        mCollectorDataTypeView = (CheckBox) findViewById(R.id.collector_data_type);

		mRegisterFormView = findViewById(R.id.register_form);
		mRegisterStatusView = findViewById(R.id.register_status);
		mRegisterStatusMessageView = (TextView) findViewById(R.id.register_status_message);

		findViewById(R.id.register_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptRegister();
					}
				});

        daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();
		colectorPrincipalDao = daoSession.getColectorPrincipalDao();
		personaDao = daoSession.getPersonaDao();
		usuarioDao = daoSession.getUsuarioDao();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			// TODO: If Settings has multiple levels, Up should navigate up
			// that hierarchy.
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the register form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual register attempt is made.
	 */
	public void attemptRegister() {
		if (mRegisterTask != null) {
			return;
		}
		View focusView = null;
		//boolean cancel;
		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);
		mFieldNumberView.setError(null);
		mInstitutionView.setError(null);
		mFullNameView.setError(null);

		// Store values at the time of the register attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();
		mFieldNumber = mFieldNumberView.getText().toString();
		mInstitution = mInstitutionView.getText().toString();
		mFullName = mFullNameView.getText().toString();
        mCollectorDataType = mCollectorDataTypeView.isChecked() ? 1 : 0;
		
		if (TextUtils.isEmpty(mEmail)){
			mEmailView.setError(getString(R.string.error_register_field_required));
			focusView = mEmailView;
			//cancel = true;
		}
		if (TextUtils.isEmpty(mPassword)){
			mPasswordView.setError(getString(R.string.error_register_field_required));
			focusView = mPasswordView;
			//cancel = true;
		}
		if (TextUtils.isEmpty(mFieldNumber)){
			mFieldNumberView.setError(getString(R.string.error_register_field_required));
			focusView = mFieldNumberView;
			//cancel = true;
		}
		if (TextUtils.isEmpty(mInstitution)){
			mInstitutionView.setError(getString(R.string.error_register_field_required));
			focusView = mInstitutionView;
			//cancel = true;
		}
		if (TextUtils.isEmpty(mFullName)){
			mFullNameView.setError(getString(R.string.error_register_field_required));
			focusView = mFullNameView;
			//cancel = true;
		}
        if (focusView != null) {
            focusView.requestFocus();
        }
        mRegisterStatusMessageView.setText(R.string.register_progress);
        showProgress(true);
        mRegisterTask = new UserRegisterTask();
        mRegisterTask.execute();
	}

    private void finishRegisterTask(HashMap<String, String> result){
        View focusView = null;
        if (!result.isEmpty()){
            for (Entry<String, String> entry : result.entrySet()) {
                if (entry.getKey().equals("password")) {
                    if (entry.getValue().equals("short")) {
                        mPasswordView.setError(getString(R.string.error_register_short_password));
                    } else if (entry.getValue().equals("simple")) {
                        mPasswordView.setError(getString(R.string.error_register_simple_password));
                    }
                    focusView = mPasswordView;
                }
                if (entry.getKey().equals("email")) {
                    if (entry.getValue().equals("empty")) {
                        mEmailView.setError(getString(R.string.error_field_required));
                    } else if (entry.getValue().equals("taken")) {
                        mEmailView.setError(getString(R.string.error_register_being_used_email));
                    } else {
                        mEmailView.setError(getString(R.string.error_invalid_email));
                    }
                    focusView = mEmailView;
                }
                if (entry.getKey().equals("fullname")) {
                    mFullNameView.setError(getString(R.string.error_register_incorrect_fullname));
                    focusView = mFullNameView;
                }
                if (entry.getKey().equals("fieldnumber")) {
                    mFieldNumberView.setError(getString(R.string.error_register_incorrect_fieldnumber));
                    focusView = mFieldNumberView;
                }
                if (entry.getKey().equals("institution")) {
                    mInstitutionView.setError(getString(R.string.error_register_incorrect_institution));
                    focusView = mInstitutionView;
                }
            }
            if (focusView != null) {
                focusView.requestFocus();
            }
        }
    }

	/**
	 * Shows the progress UI and hides the register form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mRegisterStatusView.setVisibility(View.VISIBLE);
			mRegisterStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mRegisterStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mRegisterFormView.setVisibility(View.VISIBLE);
			mRegisterFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mRegisterFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mRegisterStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous register task used to authenticate
	 * the user.
	 */
	public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private ColectorPrincipal colectorPrincipal;
        private Persona persona;
        private HashMap<String, String> errores;
        private Usuario usuario;

        @Override
		protected Boolean doInBackground(Void...params) {
            HashMap<String, String> datosRegistro = new HashMap<String, String>();
            datosRegistro.put("email", mEmail);
            datosRegistro.put("password", mPassword);
            datosRegistro.put("institution", mInstitution);
            datosRegistro.put("fullname", mFullName);
            colectorPrincipal = new ColectorPrincipal();
            colectorPrincipal.setNumeroColeccionActual(mFieldNumber);
            colectorPrincipal.setTipoCapturaDatos(mCollectorDataType);
            persona = new Persona();
            String[] names=TextUtils.split(mFullName, " ");
            if (names.length == 2){
                persona.setNombres(names[0]);
                persona.setApellidos(names[1]);
            } else {
                persona.setApellidos(datosRegistro.get("fullname"));
            }
            persona.setInstitucion(mInstitution);
            errores = Usuario.validarDatosRegistro(datosRegistro, usuarioDao);
            if (errores.isEmpty()){
                // Get ORM data access object and create the Colectorprincipal
                // Register the new account here.
                usuario = Usuario.getUsuario(mEmail, mPassword);
                daoSession.runInTx(new Runnable() {
                    @Override
                    public void run() {
                        usuarioDao.insert(usuario);
                        persona.setUsuario(usuario);
                        personaDao.insert(persona);
                        colectorPrincipal.setPersona(persona);
                        colectorPrincipalDao.insert(colectorPrincipal);
                    }
                });
                Sesion.iniciarSesion(usuario);
            }else{
                return false;
            }
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mRegisterTask = null;
			showProgress(false);

			if (success) {
				SharedPreferences preferences = getSharedPreferences("plantae_prefs", 0);
				SharedPreferences.Editor editor = preferences.edit();
		        editor.putLong("idUsuario", usuario.getId());
		        editor.commit();
				
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("colectorPrincipal", colectorPrincipal.getId());
				startActivity(intent);
				finish();
			}else{
                finishRegisterTask(errores);
            }
		}

		@Override
		protected void onCancelled() {
			mRegisterTask = null;
			showProgress(false);
		}
	}
}
