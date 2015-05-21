package edu.udistrital.plantae.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.udistrital.plantae.R;


public class AutoCompleteListActivity extends AppCompatActivity
        implements AutoCompleteListFragment.OnAutoCompleteListListener,
        AutoCompleteValueFragment.OnAutoCompleteValueListener,
        EditValueDialogFragment.OnValueChangedListener{

    private Long usuarioId1;
    private AutoCompleteValueFragment autoCompleteValueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.left);
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
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (autoCompleteValueFragment != null) {
            autoCompleteValueFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onValueChanged(Long id, String classType, String newValue) {
        if (autoCompleteValueFragment != null) {
            if (id != 0l) {
                autoCompleteValueFragment.updateValue(id, classType, newValue);
            } else {
                autoCompleteValueFragment.createValue(newValue);
            }
        }
    }
}
