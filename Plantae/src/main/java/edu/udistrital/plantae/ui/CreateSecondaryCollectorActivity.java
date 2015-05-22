package edu.udistrital.plantae.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.persistencia.ColectorSecundarioDao;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.PersonaDao;

/**
 * Created by hghar on 11/18/14.
 */
public class CreateSecondaryCollectorActivity extends AppCompatActivity {
    private ColectorSecundarioDao colectorSecundarioDao;
    private PersonaDao personaDao;
    private ColectorSecundario colectorSecundario;
    private EditText secondaryCollectorName;
    private EditText secondaryCollectorLastName;
    private EditText secondaryCollectorInstitution;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_secondary_collector);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        setSupportActionBar(toolbar);

        colectorSecundarioDao = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession().getColectorSecundarioDao();
        personaDao = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession().getPersonaDao();

        secondaryCollectorName = (EditText) findViewById(R.id.secondary_collector_names);
        secondaryCollectorLastName = (EditText) findViewById(R.id.secondary_collector_lastname);
        secondaryCollectorInstitution = (EditText) findViewById(R.id.secondary_collector_institution);

        long id = getIntent().getLongExtra("colectorSecundario", 0l);
        if (id != 0l) {
            colectorSecundario = colectorSecundarioDao.load(id);
            secondaryCollectorName.setText(colectorSecundario.getNombres());
            secondaryCollectorLastName.setText(colectorSecundario.getApellidos());
            secondaryCollectorInstitution.setText(colectorSecundario.getInstitucion());
        }

        secondaryCollectorName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                secondaryCollectorName.setError(null);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if (saveSecondaryCollector()) {
                    setResult(RESULT_OK);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean saveSecondaryCollector() {
        String secondaryCollectorNames = secondaryCollectorName.getText().toString();
        String secondaryCollectorLastNameText = secondaryCollectorLastName.getText().toString();
        String secondaryCollectorInstitutionText = secondaryCollectorInstitution.getText().toString();

        if (colectorSecundario == null) {
            colectorSecundario = new ColectorSecundario();
        }
        if (!TextUtils.isEmpty(secondaryCollectorNames)) {
            Persona persona = new Persona(secondaryCollectorLastNameText, secondaryCollectorNames);
            persona.setInstitucion(secondaryCollectorInstitutionText);
            personaDao.insert(persona);
            colectorSecundario.setPersona(persona);
            colectorSecundarioDao.insert(colectorSecundario);
            return true;
        }else{
            secondaryCollectorName.setError(getString(R.string.error_collector_name_required));
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}