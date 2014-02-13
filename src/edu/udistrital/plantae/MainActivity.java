package edu.udistrital.plantae;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
		
	public static final String PREFS_NAME = "plantae_prefs";
	Editor editor;
	public MenuItem mLogOutView;
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	/**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
		//SharedPreferences preferences = getPreferences(0);
		if (!checkLogin(preferences)){
			Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(loginIntent);
			finish();
		}
		
		
		mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

	}
	
	public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
	
	public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_projects);
                break;
            case 2:
                mTitle = getString(R.string.title_travels);
                break;
            case 3:
                mTitle = getString(R.string.title_specimens);
                break;
        }
    }

	private boolean checkLogin(SharedPreferences preferences) {
		// TODO Auto-generated method stub
		 return preferences.getBoolean("isLoggedIn", false);
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
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
	    case R.id.log_out_action:
	    	SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = preferences.edit();
	        editor.putBoolean("isLoggedIn", false);
	        editor.commit();
	        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
	        startActivity(loginIntent);
	        finish();
	        return true;
	    case R.id.action_add:
	    	
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// TODO Auto-generated method stub
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new ProyectoListFragment();
			break;
		case 1:
			fragment = new ViajeListFragment();
			break;
		case 2:
			fragment = new EspecimenListFragment();
		default:
			break;
		}
        if (fragment == null){
        	fragment = new ProyectoListFragment();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

	}
	
	

}
