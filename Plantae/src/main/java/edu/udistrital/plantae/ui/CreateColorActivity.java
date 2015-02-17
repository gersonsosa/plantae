package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

import java.util.Arrays;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;

public class CreateColorActivity extends ActionBarActivity {

    private ColorPicker picker;
    private ColorEspecimenDTO colorEspecimen;
    private OpacityBar opacityBar;
    private SaturationBar saturationBar;
    private ValueBar valueBar;
    private EditText colorNameEditText;
    private EditText colorDescriptionEditText;
    private AutoCompleteTextView plantOrgan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_color);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_back);
        setSupportActionBar(toolbar);

        retrieveViews();

        final String[] PLANT_ORGANS = getResources().getStringArray(R.array.plant_organs);
        plantOrgan.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.simple_autocomplete_item, PLANT_ORGANS));
        plantOrgan.setThreshold(1);
        plantOrgan.setValidator(new AutoCompleteTextView.Validator() {
            @Override
            public boolean isValid(CharSequence text) {
                Arrays.sort(PLANT_ORGANS);
                return Arrays.binarySearch(PLANT_ORGANS, text.toString()) > 0;
            }

            @Override
            public CharSequence fixText(CharSequence invalidText) {
                return "";
            }
        });
        plantOrgan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.v("Test", "Focus changed");
                if (v.getId() == R.id.plant_organ && !hasFocus) {
                    Log.v("Test", "Performing validation");
                    ((AutoCompleteTextView)v).performValidation();
                }
            }
        });

        colorEspecimen = getIntent().getParcelableExtra("color");
        if (colorEspecimen != null) {
            // Edit color
            colorNameEditText.setText(colorEspecimen.getNombre());
            colorDescriptionEditText.setText(colorEspecimen.getDescripcion());
            picker.setColor(colorEspecimen.getColorRGB());
        }else{
            colorEspecimen = new ColorEspecimenDTO();
            colorEspecimen.setHue(opacityBar.getOpacity());
            colorEspecimen.setValue(valueBar.getColor());
            colorEspecimen.setChroma(saturationBar.getColor());
        }

        opacityBar.setOnOpacityChangedListener(new OpacityBar.OnOpacityChangedListener() {
            @Override
            public void onOpacityChanged(int i) {
                colorEspecimen.setHue(i);
            }
        });
        saturationBar.setOnSaturationChangedListener(new SaturationBar.OnSaturationChangedListener() {
            @Override
            public void onSaturationChanged(int i) {
                colorEspecimen.setChroma(i);
            }
        });
        valueBar.setOnValueChangedListener(new ValueBar.OnValueChangedListener() {
            @Override
            public void onValueChanged(int i) {
                colorEspecimen.setValue(i);
            }
        });
    }

    private void retrieveViews() {
        colorNameEditText = (EditText) findViewById(R.id.color_name);
        colorDescriptionEditText = (EditText) findViewById(R.id.color_description);
        plantOrgan = (AutoCompleteTextView) findViewById(R.id.plant_organ);
        picker = (ColorPicker) findViewById(R.id.picker);
        opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
        saturationBar = (SaturationBar) findViewById(R.id.saturationbar);
        valueBar = (ValueBar) findViewById(R.id.valuebar);

        picker.addOpacityBar(opacityBar);
        picker.addSaturationBar(saturationBar);
        picker.addValueBar(valueBar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            // Save the color
            colorEspecimen.setNombre(TextUtils.isEmpty(colorNameEditText.getText())? null : colorNameEditText.getText().toString());
            colorEspecimen.setDescripcion(TextUtils.isEmpty(colorDescriptionEditText.getText()) ? null : colorDescriptionEditText.getText().toString());
            colorEspecimen.setColorRGB(picker.getColor());
            String plantOrganText = TextUtils.isEmpty(plantOrgan.getText()) ? "especimen" : plantOrgan.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("colorEspecimen", colorEspecimen);
            resultIntent.putExtra("plantOrgan", plantOrganText);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
