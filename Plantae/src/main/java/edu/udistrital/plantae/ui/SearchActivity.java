package edu.udistrital.plantae.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.FTSEspecimenDao;

public class SearchActivity extends AppCompatActivity {

    private ColectorPrincipal colectorPrincipal;
    private DaoSession daoSession;
    private SpecimenListFragment specimenListFragment;
    private RelativeLayout searchHint, noResultsFound;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        retrieveViews();
        Long colectorPrincipalId = getIntent().getLongExtra("colectorPrincipal", 0l);
        daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();
        colectorPrincipal = daoSession.getColectorPrincipalDao().load(colectorPrincipalId);

        FragmentManager fragmentManager = getSupportFragmentManager();
        specimenListFragment = (SpecimenListFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (specimenListFragment == null) {
            specimenListFragment = new SpecimenListFragment();
            Bundle args = new Bundle();
            args.putLong("colectorPrincipal", colectorPrincipal.getId());
            specimenListFragment.setArguments(args);
            fragmentManager.beginTransaction().add(R.id.fragment_container, specimenListFragment).commit();
        }
    }

    private void retrieveViews() {
        searchHint = (RelativeLayout) findViewById(R.id.search_hint);
        noResultsFound = (RelativeLayout) findViewById(R.id.no_results_found);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            FTSEspecimenDao ftsEspecimenDao = new FTSEspecimenDao(daoSession);
            List<SpecimenListItem> specimenListItem = ftsEspecimenDao.query(query);
            if (specimenListItem == null) {
                noResultsFound.setVisibility(View.VISIBLE);
                searchHint.setVisibility(View.GONE);
                fragmentContainer.setVisibility(View.GONE);
            }else{
                searchHint.setVisibility(View.GONE);
                fragmentContainer.setVisibility(View.VISIBLE);
                Bundle arguments = new Bundle();
                arguments.putString("query", query);
                arguments.putParcelableArrayList("specimens", (ArrayList<? extends Parcelable>) specimenListItem);

                specimenListFragment.reloadFromArguments(arguments);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_activity_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
