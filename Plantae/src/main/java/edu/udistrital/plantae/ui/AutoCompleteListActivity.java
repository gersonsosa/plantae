package edu.udistrital.plantae.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.taxonomia.EpitetoEspecifico;
import edu.udistrital.plantae.logicadominio.taxonomia.Genero;
import edu.udistrital.plantae.logicadominio.ubicacion.Departamento;
import edu.udistrital.plantae.logicadominio.ubicacion.Municipio;


public class AutoCompleteListActivity extends AppCompatActivity
        implements AutoCompleteListFragment.OnAutoCompleteListListener,
        EditValueDialogFragment.OnValueChangedListener{

    private Long usuarioId1;
    private AutoCompleteValueFragment autoCompleteValueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        usuarioId1 = getIntent().getLongExtra("usuarioId1", 0l);
        Fragment autoCompleteListFragment = AutoCompleteListFragment.newInstance(usuarioId1);
        fragmentManager.beginTransaction().add(R.id.auto_complete_fragment_container, autoCompleteListFragment, "autoCompleteList").commit();
    }

    @Override
    public void onAutoCompleteListSelected(String selectedList1) {
        autoCompleteValueFragment = AutoCompleteValueFragment.newInstance(selectedList1, usuarioId1);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.auto_complete_fragment_container,
                        autoCompleteValueFragment)
                .addToBackStack("valueFragment")
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (autoCompleteValueFragment != null) {
            autoCompleteValueFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onValueChanged(Long id, String classType, String newValue, String parentValue) {
        if (autoCompleteValueFragment != null) {
            if (id != 0l) {
                if (parentValue != null) {
                    if (classType.equals(Municipio.class.getName())) {
                        autoCompleteValueFragment.updateCounty(id, newValue, parentValue, null);
                    }else if (classType.equals(EpitetoEspecifico.class.getName())) {
                        autoCompleteValueFragment.updateSpecies(id, newValue, parentValue, null);
                    }else if (classType.equals(Departamento.class.getName())) {
                        autoCompleteValueFragment.updateState(id, newValue, parentValue);
                    }else if (classType.equals(Genero.class.getName())) {
                        autoCompleteValueFragment.updateGenus(id, newValue, parentValue);
                    }
                } else {
                    autoCompleteValueFragment.updateValue(id, classType, newValue);
                }
                Toast.makeText(this, getString(R.string.value) + " " + newValue + " " + getString(R.string.value_updated_successfully), Toast.LENGTH_LONG).show();
            } else {
                if (parentValue != null) {
                    if (classType.equals(Municipio.class.getName())) {
                        autoCompleteValueFragment.createCounty(newValue, parentValue, null);
                    }else if (classType.equals(EpitetoEspecifico.class.getName())) {
                        autoCompleteValueFragment.createSpecies(newValue, parentValue, null);
                    }else if (classType.equals(Departamento.class.getName())) {
                        autoCompleteValueFragment.createState(newValue, parentValue);
                    }else if (classType.equals(Genero.class.getName())) {
                        autoCompleteValueFragment.createGenus(newValue, parentValue);
                    }
                } else {
                    autoCompleteValueFragment.createValue(newValue);
                }
                Toast.makeText(this, getString(R.string.value) + " " + newValue + " " + getString(R.string.value_created_successfully), Toast.LENGTH_LONG).show();
                autoCompleteValueFragment.loadValues();
            }
        }
    }
}
