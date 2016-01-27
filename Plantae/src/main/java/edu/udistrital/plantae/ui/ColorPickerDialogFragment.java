package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorMunsell;

/**
 * Created by hghar on 12/16/14.
 */
public class ColorPickerDialogFragment extends DialogFragment{

    private ColorPicker picker;
    private OnColorSelectedListener onColorSelectedListener;
    private ColorMunsell colorMunsell;

    public interface OnColorSelectedListener {
        public void onColorSelected(String tag, ColorMunsell colorMunsell, int color);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onColorSelectedListener = (OnColorSelectedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnColorSelectedListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.fragment_color_picker_dialog, null);
        builder.setView(rootView)
                .setPositiveButton(R.string.set_color_action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onColorSelectedListener.onColorSelected(getTag(), colorMunsell, picker.getColor());
                    }
                });
        picker = (ColorPicker) rootView.findViewById(R.id.picker);
        OpacityBar opacityBar = (OpacityBar) rootView.findViewById(R.id.opacitybar);
        SaturationBar saturationBar = (SaturationBar) rootView.findViewById(R.id.saturationbar);
        ValueBar valueBar = (ValueBar) rootView.findViewById(R.id.valuebar);

        picker.addOpacityBar(opacityBar);
        picker.addSaturationBar(saturationBar);
        picker.addValueBar(valueBar);

        colorMunsell = new ColorMunsell(opacityBar.getOpacity(), valueBar.getColor(), saturationBar.getColor());

        opacityBar.setOnOpacityChangedListener(new OpacityBar.OnOpacityChangedListener() {
            @Override
            public void onOpacityChanged(int i) {
                colorMunsell.setHue(i);
            }
        });
        saturationBar.setOnSaturationChangedListener(new SaturationBar.OnSaturationChangedListener() {
            @Override
            public void onSaturationChanged(int i) {
                colorMunsell.setChroma(i);
            }
        });
        valueBar.setOnValueChangedListener(new ValueBar.OnValueChangedListener() {
            @Override
            public void onValueChanged(int i) {
                colorMunsell.setValue(i);
            }
        });

        return builder.create();
    }
}
