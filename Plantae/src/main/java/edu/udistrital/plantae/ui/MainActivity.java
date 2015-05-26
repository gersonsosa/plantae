package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.autenticacion.Sesion;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.persistencia.ColectorPrincipalDao;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.PersonaDao;

public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
		
	public static final String PREFS_NAME = "plantae_prefs";
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private ColectorPrincipal colectorPrincipal;
    private List<Viaje> viajes;

	/**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private View selectTravel;


    private static final int CREATE_TRAVEL_REQUEST = 1;
    private FrameLayout fragmentContainer;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
        Long idUsuarioLoggedIn = checkLogin(preferences);
        if (idUsuarioLoggedIn.equals(0l)){
            Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(loginIntent);
            finish();
            return;
        }else{
            // Se comprueba que no se este llamando esta actividad desde otra actividad
            // Se carga el usuario en base al identificador almacenado en el Intent con el que se inicio la actividad
            DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();
            colectorPrincipal = daoSession.getColectorPrincipalDao().queryBuilder().where(ColectorPrincipalDao.Properties.Id.eq(getIntent().getLongExtra("colectorPrincipal", 0l))).unique();
            if (colectorPrincipal == null) {
                // [Inicio applicación] Es la primera vez que se carga el colector principal en esta ejecución
                // Cargar la persona y el colector principal en base al identificador del archivo de preferencias
                Persona persona = daoSession.getPersonaDao().queryBuilder().where(PersonaDao.Properties.UsuarioID.eq(idUsuarioLoggedIn)).unique();
                if (persona != null) {
                    colectorPrincipal = daoSession.getColectorPrincipalDao().queryBuilder().where(ColectorPrincipalDao.Properties.PersonaID.eq(persona.getId())).unique();
                }else{
                    logout();
                }
            }
        }
        viajes = colectorPrincipal.getViajes();
        // Llama implícitamente el método OnCreate y OnCreateView de Nav Drawer
        setContentView(R.layout.activity_main);
        Bundle navDrawerArgs = new Bundle();
        navDrawerArgs.putLong("colectorPrincipal", colectorPrincipal.getId());
        getIntent().putExtras(navDrawerArgs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                fragmentManager.findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), viajes);

        retrieveViews();
        // Cargar la lista de especímenes del primer viaje de la lista (Si existe)
        Fragment fragment;
        if (viajes.size() > 0) {
            Viaje viaje = viajes.iterator().next();
            mNavigationDrawerFragment.selectViaje(viaje);
            if (savedInstanceState != null) {
                fragment = fragmentManager.findFragmentByTag("specimenList");
            }else{
                fragment = new SpecimenListFragment();
                Bundle args = new Bundle();
                args.putLong("viaje", viaje.getId());
                args.putLong("colectorPrincipal", colectorPrincipal.getId());
                fragment.setArguments(args);
            }
            updateContent(fragment, "specimenList");
        }else{
            mNavigationDrawerFragment.selectItem(3);
            if (savedInstanceState != null) {
                fragment = fragmentManager.findFragmentByTag("viajeList");
            } else {
                fragment = new TripListFragment();
                Bundle args = new Bundle();
                args.putLong("colectorPrincipal", colectorPrincipal.getId());
                fragment.setArguments(args);
            }
            updateContent(fragment, "viajeList");
        }
        servicesConnected();
	}

    private void retrieveViews() {
        selectTravel = findViewById(R.id.select_travel);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateNavDrawerList();
    }

    public void updateNavDrawerList(){
        mNavigationDrawerFragment.fillOrUpdateNavDrawerList();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

	private Long checkLogin(SharedPreferences preferences) {
		 return preferences.getLong("idUsuario", 0l);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    case R.id.log_out_action:
            logout();
	        return true;
        case R.id.action_autocomplete_lists:
            Intent autoCompleteIntent = new Intent(getApplicationContext(), AutoCompleteListActivity.class);
            autoCompleteIntent.putExtra("usuarioId1", colectorPrincipal.getPersona().getUsuarioID());
            startActivity(autoCompleteIntent);
            return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

    private void logout() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("idUsuario", 0l);
        editor.apply();
        Usuario.destruirUsuario();
        Sesion.cerrarSesion();
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    /**
     * Cambia el fragmento principal de la actividad.
     * @param fragment fragmento el cual se va a mostrar
     * @param fragmentTag tag del fragmento que se va a mostrar
     */
    private void updateContent(Fragment fragment, String fragmentTag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragmentTag).commit();
        selectTravel.setVisibility(View.GONE);
        if (fragmentContainer.getVisibility() == View.INVISIBLE) {
            fragmentContainer.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Actualiza el contenido de la actividad teniendo en cuenta
     * la cuenta de viajes en el menú de navegación.
     * @param count Número de elementos en el menú de navegación
     */
    private void updateContent(int count){
        FragmentManager fragmentManager = getSupportFragmentManager();
        if ((count - 5) == 0){
            selectTravel.setVisibility(View.GONE);
            if (fragmentManager.findFragmentByTag("specimenList") != null){
                fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("specimenList")).commitAllowingStateLoss();
            }
            if (fragmentManager.findFragmentByTag("viajeList") != null){
                fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("viajeList")).commitAllowingStateLoss();
            }
        }else if ((count-5) > 0){
            if (mNavigationDrawerFragment.getmDrawerListView().getSelectedItemPosition() == AdapterView.INVALID_POSITION){
                if (fragmentManager.findFragmentByTag("specimenList") != null){
                    fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("specimenList")).commitAllowingStateLoss();
                }
                if (fragmentManager.findFragmentByTag("viajeList") != null){
                    fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("viajeList")).commitAllowingStateLoss();
                }
                selectTravel.setVisibility(View.VISIBLE);
                fragmentContainer.setVisibility(View.INVISIBLE);
            }else{
                selectTravel.setVisibility(View.GONE);
            }
        }
    }

    public static class ErrorDialogFragment extends DialogFragment {
        // Global field to contain the error dialog
        private Dialog mDialog;
        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }
        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CREATE_TRAVEL_REQUEST:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        updateNavDrawerList();
                        updateContent(mNavigationDrawerFragment.getmDrawerListView().getCount());
                        break;
                }
                break;
            case CONNECTION_FAILURE_RESOLUTION_REQUEST :
                /*
                 * If the result code is Activity.RESULT_OK, try
                 * to connect again
                 */
                switch (resultCode) {
                    case Activity.RESULT_OK :
                        /*
                        * Try the request again
                        */
                    break;
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean servicesConnected() {
        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.
                        isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates",
                    "Google Play services is available.");
            // Continue
            return true;
            // Google Play services was not available for some reason.
            // resultCode holds the error code.
        } else {
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                    resultCode,
                    this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                ErrorDialogFragment errorFragment =
                        new ErrorDialogFragment();
                // Set the dialog in the DialogFragment
                errorFragment.setDialog(errorDialog);
                // Show the error dialog in the DialogFragment
                errorFragment.show(getSupportFragmentManager(),
                        "Location Updates");
            }
        }
        return false;
    }

    @Override
	public void onNavigationDrawerItemSelected(int position) {
		Fragment fragment;
        if (mNavigationDrawerFragment != null) {
            ListView navDrawerListView = mNavigationDrawerFragment.getmDrawerListView();
            NavigationDrawerItem navigationDrawerItem = (NavigationDrawerItem) navDrawerListView.getItemAtPosition(position);
            if (navigationDrawerItem != null ){
                if (navigationDrawerItem.getName().equals(getString(R.string.add_new_travels))){
                    // Mostrar pantalla que indique que no existen viajes creados
                    mNavigationDrawerFragment.getmDrawerListView().setItemChecked(position, false);
                    Intent createTravelIntent = new Intent(getApplicationContext(), CreateTripActivity.class);
                    createTravelIntent.putExtra("colectorPrincipal", colectorPrincipal.getId());
                    startActivityForResult(createTravelIntent, CREATE_TRAVEL_REQUEST);
                }else if (navigationDrawerItem.getName().equals(getString(R.string.manage_travels))){
                    // Replace fragment manage travels
                    fragment = new TripListFragment();
                    Bundle args = new Bundle();
                    args.putLong("colectorPrincipal", colectorPrincipal.getId());
                    fragment.setArguments(args);
                    updateContent(fragment, "viajeList");
                }else if (navigationDrawerItem.getName().equals(getString(R.string.autocomplete_lists))){
                    Intent autoCompleteIntent = new Intent(getApplicationContext(), AutoCompleteListActivity.class);
                    autoCompleteIntent.putExtra("usuarioId1", colectorPrincipal.getPersona().getUsuarioID());
                    startActivity(autoCompleteIntent);
                }else{
                    fragment = new SpecimenListFragment();
                    Bundle args = new Bundle();
                        args.putLong("viaje", navigationDrawerItem.getId());
                    args.putLong("colectorPrincipal", colectorPrincipal.getId());
                    fragment.setArguments(args);
                    updateContent(fragment, "specimenList");
                }
            }
        }
	}

}
