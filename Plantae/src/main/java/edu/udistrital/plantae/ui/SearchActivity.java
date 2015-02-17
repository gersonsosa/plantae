package edu.udistrital.plantae.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.FTSEspecimenDao;
import edu.udistrital.plantae.ui.adapter.SpecimenListItemAdapter;

public class SearchActivity extends ActionBarActivity implements View.OnClickListener{

    private ColectorPrincipal colectorPrincipal;
    private DaoSession daoSession;
    private ListView resultListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_back);
        toolbar.setLogo(R.drawable.plantae);
        setSupportActionBar(toolbar);

        retrieveViews();

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Long colectorPrincipalId = getIntent().getLongExtra("colectorPrincipal", 0l);
        daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();
        colectorPrincipal = daoSession.getColectorPrincipalDao().load(colectorPrincipalId);
    }

    private void retrieveViews() {
        resultListView = (ListView) findViewById(R.id.result_specimens);
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
                specimenListItem = new ArrayList<>();
            }
            resultListView.setAdapter(new SpecimenListItemAdapter(getApplicationContext(), specimenListItem, this, getResources()));
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

    @Override
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
    }

    @Override
    public void onClick(View v) {

    }
}
