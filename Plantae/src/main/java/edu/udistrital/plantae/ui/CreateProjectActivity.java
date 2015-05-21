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
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorPrincipal;
import edu.udistrital.plantae.logicadominio.recoleccion.Proyecto;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.ProyectoDao;

/**
 * Created by Gerson Sosa on 4/22/14.
 */
public class CreateProjectActivity extends ActionBarActivity {

    private ProyectoDao proyectoDao;
    private ColectorPrincipal colectorPrincipal;
    private Proyecto proyecto;
    private EditText projectName;
    private EditText financialAgency;
    private EditText agreementNumber;
    private EditText institutionProjectBased;
    private EditText collectingPermit;
    private EditText permitNumber;
    private EditText permitIssuer;
    private Long colectorPrincipalID;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.left);
        setSupportActionBar(toolbar);

        DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();
        colectorPrincipalID = getIntent().getLongExtra("colectorPrincipal", 0l);
        colectorPrincipal = daoSession.getColectorPrincipalDao().load(colectorPrincipalID);

        proyectoDao = daoSession.getProyectoDao();

        projectName = (EditText) findViewById(R.id.project_name);
        financialAgency = (EditText) findViewById(R.id.financial_agency);
        agreementNumber = (EditText) findViewById(R.id.agreement_number);
        institutionProjectBased = (EditText) findViewById(R.id.institution_where_project_is_based);
        collectingPermit = (EditText) findViewById(R.id.collecting_permit);
        permitNumber = (EditText) findViewById(R.id.permit_number);
        permitIssuer = (EditText) findViewById(R.id.permit_issuer);

        long id = getIntent().getLongExtra("proyecto", 0l);
        if (id != 0l) {
            proyecto = proyectoDao.load(id);
            projectName.setText(proyecto.getNombre());
            financialAgency.setText(proyecto.getAgenciaFinanciera());
            agreementNumber.setText(proyecto.getNumeroConvenio());
            institutionProjectBased.setText(proyecto.getAgenciaEjecutora());
            collectingPermit.setText(proyecto.getPermisoColeccion());
            permitNumber.setText(proyecto.getNumeroPermiso());
            permitIssuer.setText(proyecto.getEmisorPermiso());
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
        String agreementNumberText = agreementNumber.getText().toString();
        String institutionProjectBasedText = institutionProjectBased.getText().toString();
        String collectingPermitText = collectingPermit.getText().toString();
        String permitNumberText = permitNumber.getText().toString();
        String permitIssuerText = permitIssuer.getText().toString();

        if (!TextUtils.isEmpty(name)) {
            if (proyecto == null) {
                createProject(name, financialAgencyText, agreementNumberText, institutionProjectBasedText, collectingPermitText, permitNumberText, permitIssuerText);
            }else{
                proyecto.setNombre(name);
                proyecto.setAgenciaFinanciera(financialAgencyText);
                proyecto.setNumeroConvenio(agreementNumberText);
                proyecto.setAgenciaEjecutora(institutionProjectBasedText);
                proyecto.setPermisoColeccion(collectingPermitText);
                proyecto.setNumeroPermiso(permitNumberText);
                proyecto.setEmisorPermiso(permitIssuerText);
                proyecto.setColectorPrincipalID(colectorPrincipalID);
                proyectoDao.update(proyecto);
            }
            return true;
        }else{
            projectName.setError(getString(R.string.error_project_name_required));
        }
        return false;
    }

    private void createProject(String name, String financialAgencyText, String agreementNumberText, String institutionProjectBasedText, String collectingPermitText, String permitNumberText, String permitIssuerText) {
        proyecto = new Proyecto(name);
        proyecto.setAgenciaFinanciera(financialAgencyText);
        proyecto.setNumeroConvenio(agreementNumberText);
        proyecto.setAgenciaEjecutora(institutionProjectBasedText);
        proyecto.setPermisoColeccion(collectingPermitText);
        proyecto.setNumeroPermiso(permitNumberText);
        proyecto.setEmisorPermiso(permitIssuerText);
        proyecto.setColectorPrincipalID(colectorPrincipalID);
        proyectoDao.insert(proyecto);
        colectorPrincipal.getProyectos().add(proyecto);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}