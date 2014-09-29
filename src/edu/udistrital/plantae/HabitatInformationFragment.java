package edu.udistrital.plantae;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habitat;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;

/**
 * Created by hghar on 6/25/14.
 */
public class HabitatInformationFragment extends Fragment {

    private EspecimenDTO especimenDTO;

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
        especimenDTO = getArguments().getParcelable("especimen");
        EditText vegetation = (EditText) viewHolder.fragmentView.findViewById(R.id.vegetation);
        EditText soilSubstratum = (EditText) viewHolder.fragmentView.findViewById(R.id.soil_substratum);
        EditText associatedSpecies = (EditText) viewHolder.fragmentView.findViewById(R.id.associated_species);
        vegetation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Habitat habitat = null;
                    String value = ((EditText)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        habitat = especimenDTO.getHabitat() != null ? especimenDTO.getHabitat() : new Habitat();
                        habitat.setVegetacion(value);
                        especimenDTO.setHabitat(habitat);
                    }
                }
            }
        });
        soilSubstratum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Habitat habitat = null;
                    String value = ((EditText)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        habitat = especimenDTO.getHabitat() != null ? especimenDTO.getHabitat() : new Habitat();
                        habitat.setSueloSustrato(value);
                        especimenDTO.setHabitat(habitat);
                    }
                }
            }
        });
        associatedSpecies.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Habitat habitat = null;
                    String value = ((EditText)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        habitat = especimenDTO.getHabitat() != null ? especimenDTO.getHabitat() : new Habitat();
                        habitat.setEspeciesAsociadas(value);
                        especimenDTO.setHabitat(habitat);
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