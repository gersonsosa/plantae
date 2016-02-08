package edu.udistrital.plantae.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.udistrital.plantae.R;

/**
 * Created by hghar on 6/25/14.
 */
public class PlantSampleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plant_sample, container, false);
    }
}