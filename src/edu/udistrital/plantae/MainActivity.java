package edu.udistrital.plantae;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.autenticacion.Sesion;
import edu.udistrital.plantae.logicadominio.autenticacion.Usuario;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.persistencia.ColectorPrincipalDao;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.PersonaDao;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
		
	public static final String PREFS_NAME = "plantae_prefs";

    private ColectorPrincipal colectorPrincipal;

	/**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private View noTravels;
    private View selectTravel;
    private NavigationDrawerItem navigationDrawerItem;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
        Long idUsuarioLoggedIn = checkLogin(preferences);
        if (idUsuarioLoggedIn.equals(0l)){
            Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(loginIntent);
            finish();
            return;
        }else{
            // Load Usuario logged in if no extra in intent
            colectorPrincipal = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession().getColectorPrincipalDao().queryBuilder().where(ColectorPrincipalDao.Properties.Id.eq(getIntent().getLongExtra("colectorPrincipal", 0l))).unique();
            if (colectorPrincipal == null) {
                // Load usuario from databased based on id saved in preferences
                Persona persona = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession().getPersonaDao().queryBuilder().where(PersonaDao.Properties.UsuarioID.eq(idUsuarioLoggedIn)).unique();
                colectorPrincipal = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession().getColectorPrincipalDao().queryBuilder().where(ColectorPrincipalDao.Properties.PersonaID.eq(persona.getId())).unique();
            }
        }
        Bundle navDrawerArgs = new Bundle();
        navDrawerArgs.putLong("colectorPrincipal", colectorPrincipal.getId());
        getIntent().putExtras(navDrawerArgs);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                fragmentManager.findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        noTravels = findViewById(R.id.no_travels);
        selectTravel = findViewById(R.id.select_travel);
        // Load the selected travel specimens in main content
        Fragment fragment;
        ListView navDrawerListView = mNavigationDrawerFragment.getmDrawerListView();
        navigationDrawerItem = (NavigationDrawerItem) navDrawerListView.getItemAtPosition(1);
        if (navigationDrawerItem != null && navigationDrawerItem.getId() != null) {
            mNavigationDrawerFragment.selectItem(1);
            fragment = new SpecimenListFragment();
            Bundle args = new Bundle();
            args.putLong("viaje", navigationDrawerItem.getId());
            fragment.setArguments(args);
            fragmentManager.beginTransaction().replace(R.id.container, fragment, "specimenList").commit();
        }else if (navigationDrawerItem.getName().equals(getString(R.string.add_new_travels))){
            mNavigationDrawerFragment.getmDrawerListView().setItemChecked(0, false);
            noTravels.setVisibility(View.VISIBLE);
            selectTravel.setVisibility(View.GONE);
        }
	}

    @Override
    protected void onRestart() {
        super.onRestart();
        updateNavDrawerList();
    }

    public void updateNavDrawerList(){
        mNavigationDrawerFragment.fillOrUpdateNavDrawerList(colectorPrincipal.getId());
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
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
	    	SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = preferences.edit();
	        editor.putLong("idUsuario", 0l);
	        editor.commit();
            Usuario.destruirUsuario();
            Sesion.cerrarSesion();
	        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
	        startActivity(loginIntent);
	        finish();
	        return true;
        case R.id.action_settings:
            Intent settingsIntent = new Intent(getApplicationContext(), SettingsAcivity.class);
            startActivity(settingsIntent);
            finish();
            return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

    /**
     * Actualiza el contenido de la actividad teniendo en cuenta
     * la cuenta de viajes en el menú de navegación y si se requiere
     * cambiar un fragmento por otro.
     * @param count Número de elementos en el menú de navegación
     * @param fragment fragmento el cual se va a mostrar
     * @param fragmentTag tag del fragmento que se va a mostrar
     */
    private void updateContent(int count, Fragment fragment, String fragmentTag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.container, fragment, fragmentTag).commit();
            noTravels.setVisibility(View.GONE);
            selectTravel.setVisibility(View.GONE);
        } else if (count == 1){
            selectTravel.setVisibility(View.GONE);
            noTravels.setVisibility(View.VISIBLE);
            if (fragmentManager.findFragmentByTag("specimenList") != null){
                fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("specimenList")).commit();
            }
        }else if (count > 1){
            noTravels.setVisibility(View.GONE);
            if (mNavigationDrawerFragment.getmDrawerListView().getSelectedItemPosition() == AdapterView.INVALID_POSITION){
                if (fragmentManager.findFragmentByTag("specimenList") != null){
                    fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("specimenList")).commit();
                }
                selectTravel.setVisibility(View.VISIBLE);
            }else{
                selectTravel.setVisibility(View.GONE);
            }
        }
    }

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		Fragment fragment;
        //FragmentManager fragmentManager = getSupportFragmentManager();
        if (mNavigationDrawerFragment != null) {
            ListView navDrawerListView = mNavigationDrawerFragment.getmDrawerListView();
            navigationDrawerItem = (NavigationDrawerItem) navDrawerListView.getItemAtPosition(position);
            if (navigationDrawerItem != null && navigationDrawerItem.getId() != null) {
                fragment = new SpecimenListFragment();
                Bundle args = new Bundle();
                args.putLong("viaje", navigationDrawerItem.getId());
                fragment.setArguments(args);
                updateContent(navDrawerListView.getCount(), fragment, "specimenList");
            }else{
                if (position == navDrawerListView.getCount()-4){
                    // Mostrar pantalla que indique que no existen viajes creados
                    mNavigationDrawerFragment.getmDrawerListView().setItemChecked(position, false);
                    Intent createTravelIntent = new Intent(getApplicationContext(), CreateTravelActivity.class);
                    createTravelIntent.putExtra("colectorPrincipal", colectorPrincipal.getId());
                    startActivity(createTravelIntent);
                    updateContent(navDrawerListView.getCount(), null, null);
                }
                if (position == navDrawerListView.getCount()-2){
                    // Replace fragment manage travels
                    fragment = new TravelListFragment();
                    Bundle args = new Bundle();
                    args.putLong("colectorPrincipal", colectorPrincipal.getId());
                    fragment.setArguments(args);
                    updateContent(position, fragment, "viajeList");
                }
                if (position == navDrawerListView.getCount()-1){
                    // Start settings activity
                    Intent settingsIntent = new Intent(getApplicationContext(), TravelListFragment.class);
                    settingsIntent.putExtra("colectorPrincipal", colectorPrincipal.getId());
                    startActivity(settingsIntent);
                }
            }
        }
	}

}
