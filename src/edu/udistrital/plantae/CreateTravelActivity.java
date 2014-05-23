package edu.udistrital.plantae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.Proyecto;
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.persistencia.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerson Sosa on 4/11/14.
 */
public class CreateTravelActivity extends ActionBarActivity {

    private ViajeDao viajeDao;
    private ProyectoDao proyectoDao;
    private ColectorPrincipal colectorPrincipal;
    private Viaje viaje;
    private EditText nombreView;
    private Spinner proyectoSpinner;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_travel);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageButton buttonAddProject = (ImageButton) findViewById(R.id.add_proyecto);
        nombreView = (EditText) findViewById(R.id.viaje_nombre);
        proyectoSpinner = (Spinner) findViewById(R.id.viaje_proyecto);

        DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();
        viajeDao = daoSession.getViajeDao();

        // Load viaje id if exists in extras
        Long id = getIntent().getExtras().getLong("viaje", 0l);
        if (id != 0l) {
            viaje = viajeDao.loadDeep(id);
            nombreView.setText(viaje.getNombre());
        }

        int projectPosition = 0;

        // cargar proyectos al spinner
        proyectoDao = daoSession.getProyectoDao();
        List<Proyecto> proyectos = proyectoDao.loadAll();
        List<SpinnerItem> proyectoSpinnerItemList = new ArrayList<SpinnerItem>(proyectos.size());
        for (int i = 0; i < proyectos.size(); i++) {
            Proyecto next = proyectos.get(i);
            proyectoSpinnerItemList.add(new SpinnerItem(next.getId(), next.getNombre()));
            if (viaje != null) {
                projectPosition = i;
            }
        }
        proyectoSpinnerItemList.add(new SpinnerItem(0l,getString(R.string.select_project)));
        proyectoSpinner.setAdapter(new ArrayAdapter<SpinnerItem>(getApplicationContext(), R.layout.spinner_item, proyectoSpinnerItemList));

        if (projectPosition != 0){
            proyectoSpinner.setSelection(projectPosition);
        }

        // Cargar el colector principal
        colectorPrincipal = daoSession.getColectorPrincipalDao().queryBuilder().where(ColectorPrincipalDao.Properties.Id.eq(getIntent().getLongExtra("colectorPrincipal", 0l))).unique();

        // Agregar listeners a los botones
        buttonAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createProjectIntent = new Intent(getApplicationContext(), CreateProjectActivity.class);
                createProjectIntent.putExtra("colectorPrincipal", colectorPrincipal.getId());
                startActivity(createProjectIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_travel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveTravel();
                finish();
                return true;
            case R.id.action_discard:
                setResult(RESULT_CANCELED);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveTravel() {
        String nombre = nombreView.getText().toString().trim();
        SpinnerItem proyectoSpinnerItem = (SpinnerItem) proyectoSpinner.getSelectedItem();
        Proyecto proyecto;
        if (proyectoSpinnerItem.getTitle().equals(getString(R.string.select_project))){
            proyecto = new Proyecto(getString(R.string.default_project_name));
            proyectoDao.insert(proyecto);
        }else{
            proyecto = proyectoDao.load(proyectoSpinnerItem.getId());
        }
        if (viaje == null) {
            createTravel(nombre, proyecto);
        }else{
            updateTravel(nombre, proyecto);
        }
        setResult(RESULT_OK);
    }

    private void createTravel(String nombre, Proyecto proyecto){
        viaje = new Viaje();
        viaje.setNombre(nombre);
        viaje.setProyecto(proyecto);
        viaje.setColectorPrincipal(colectorPrincipal);
        viajeDao.insert(viaje);
    }

    private void updateTravel(String nombre, Proyecto proyecto) {
        viaje.setNombre(nombre);
        viaje.setProyecto(proyecto);
        viajeDao.update(viaje);
    }

}