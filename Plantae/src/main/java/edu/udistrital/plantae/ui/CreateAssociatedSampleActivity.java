package edu.udistrital.plantae.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.MuestraAsociada;

/**
 * Created by hghar on 12/4/14.
 */
public class CreateAssociatedSampleActivity extends AppCompatActivity {

    private MuestraAsociada muestraAsociada;
    private EditText associatedSampleDescription, associatedSampleTreatmentMethod;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_associated_sample);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        setSupportActionBar(toolbar);

        retrieveViews();

        muestraAsociada = getIntent().getParcelableExtra("muestraAsociada");
        if (muestraAsociada != null) {
            associatedSampleDescription.setText(muestraAsociada.getDescripcion());
            associatedSampleTreatmentMethod.setText(muestraAsociada.getMetodoDeTratamiento());
        }
    }

    private void retrieveViews() {
        associatedSampleDescription = (EditText) findViewById(R.id.associated_sample_description);
        associatedSampleTreatmentMethod = (EditText) findViewById(R.id.associated_sample_treatment_method);
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
                if (saveAssociatedSample()) {
                    Intent resultIntent = getIntent().putExtra("muestraAsociada", muestraAsociada);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean saveAssociatedSample() {
        String secondaryCollectorNames = associatedSampleDescription.getText().toString();
        String secondaryCollectorLastNameText = associatedSampleTreatmentMethod.getText().toString();

        if (muestraAsociada == null) {
            muestraAsociada = new MuestraAsociada();
        }
        if (!TextUtils.isEmpty(secondaryCollectorNames)) {
            muestraAsociada.setDescripcion(secondaryCollectorNames);
            muestraAsociada.setMetodoDeTratamiento(secondaryCollectorLastNameText);
            return true;
        }else{
            associatedSampleDescription.setError(getString(R.string.error_description_required));
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}