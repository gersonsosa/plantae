package edu.udistrital.plantae.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.recoleccion.Proyecto;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.ProyectoDao;

/**
 * Created by Gerson Sosa on 4/22/14.
 */
public class CreateProjectActivity extends ActionBarActivity {

    private ProyectoDao proyectoDao;
    private Proyecto proyecto;
    private EditText projectName;
    private EditText financialAgency;
    private EditText conventionNumber;
    private EditText executixAgency;
    private EditText collectingPermission;
    private EditText permissionNumber;
    private Long colectorPrincipalID;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_back);
        setSupportActionBar(toolbar);

        colectorPrincipalID = getIntent().getLongExtra("colectorPrincipal", 0l);

        proyectoDao = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession().getProyectoDao();

        projectName = (EditText) findViewById(R.id.project_name);
        financialAgency = (EditText) findViewById(R.id.financial_agency);
        conventionNumber = (EditText) findViewById(R.id.convention_number);
        executixAgency = (EditText) findViewById(R.id.executix_agency);
        collectingPermission = (EditText) findViewById(R.id.collecting_permission);
        permissionNumber = (EditText) findViewById(R.id.permission_number);

        long id = getIntent().getLongExtra("proyecto", 0l);
        if (id != 0l) {
            proyecto = proyectoDao.load(id);
            projectName.setText(proyecto.getNombre());
            financialAgency.setText(proyecto.getAgenciaFinanciera());
            conventionNumber.setText(proyecto.getNumeroConvenio());
            executixAgency.setText(proyecto.getAgenciaEjecutora());
            collectingPermission.setText(proyecto.getPermisoColeccion());
            permissionNumber.setText(proyecto.getNumeroPermiso());
        }

        projectName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                projectName.setError(null);
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
                if (saveProject()) {
                    setResult(RESULT_OK);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean saveProject() {
        String name = projectName.getText().toString();
        String financialAgencyText = financialAgency.getText().toString();
        String conventionNumberText = conventionNumber.getText().toString();
        String executrixAgencyText = executixAgency.getText().toString();
        String collectingPermissionText = collectingPermission.getText().toString();
        String permissionNumberText = permissionNumber.getText().toString();

        if (proyecto == null) {
            proyecto = new Proyecto(name);
        }
        if (!TextUtils.isEmpty(name)) {
            proyecto.setAgenciaFinanciera(financialAgencyText);
            proyecto.setNumeroConvenio(conventionNumberText);
            proyecto.setAgenciaEjecutora(executrixAgencyText);
            proyecto.setPermisoColeccion(collectingPermissionText);
            proyecto.setNumeroPermiso(permissionNumberText);
            proyecto.setColectorPrincipalID(colectorPrincipalID);

            proyectoDao.insert(proyecto);
            return true;
        }else{
            projectName.setError(getString(R.string.error_project_name_required));
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}