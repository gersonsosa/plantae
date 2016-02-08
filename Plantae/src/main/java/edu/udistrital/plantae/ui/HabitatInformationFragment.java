package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import edu.udistrital.plantae.R;

/**
 * Created by hghar on 6/25/14.
 */
public class HabitatInformationFragment extends Fragment {

    private OnHabitatChangedListener onHabitatChangedListener;

    public interface OnHabitatChangedListener {
        public void onVegetationChanged(String vegetation);
        public void onSoilSubstratumChanged(String soilSubstratum);
        public void onAssociatedSpeciesChanged(String associatedSpecies);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onHabitatChangedListener = (OnHabitatChangedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement HabitatInformationFragment.OnHabitatChangedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewHolder viewHolder = (ViewHolder) container.getTag(R.layout.fragment_habitat_information);
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_habitat_information, container, false);
            container.setTag(R.layout.fragment_habitat_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }

        Bundle fragmentsBundle = getArguments();

        String especiasAsociadas = fragmentsBundle.getString("especiesAsociadas");
        String sueloSustrato = fragmentsBundle.getString("sueloSustrato");
        String vegetacion = fragmentsBundle.getString("vegetacion");

        EditText vegetation = (EditText) viewHolder.fragmentView.findViewById(R.id.vegetation);
        EditText soilSubstratum = (EditText) viewHolder.fragmentView.findViewById(R.id.soil_substratum);
        EditText associatedSpecies = (EditText) viewHolder.fragmentView.findViewById(R.id.associated_species);

        vegetation.setText(vegetacion);
        soilSubstratum.setText(sueloSustrato);
        associatedSpecies.setText(especiasAsociadas);
        vegetation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        onHabitatChangedListener.onVegetationChanged(value);
                    }
                }
            }
        });
        soilSubstratum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        onHabitatChangedListener.onSoilSubstratumChanged(value);
                    }
                }
            }
        });
        associatedSpecies.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        onHabitatChangedListener.onAssociatedSpeciesChanged(value);
                    }
                }
            }
        });
        return viewHolder.fragmentView;
    }

    static class ViewHolder{
        View fragmentView;
    }
}