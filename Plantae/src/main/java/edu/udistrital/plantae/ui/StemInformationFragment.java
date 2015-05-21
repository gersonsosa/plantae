package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;

/**
 * Created by hghar on 6/25/14.
 */
public class StemInformationFragment extends Fragment {
    public static final int STEM_COLOR_CREATION_REQUEST = 53;
    public static final int STEM_COLOR_EDIT_REQUEST = 63;
    private ColorEspecimenDTO colorDelTallo;
    private ViewHolder viewHolder;
    private OnStemInformationChangedListener onStemInformationChangedListener;

    public interface OnStemInformationChangedListener {
        public void onStemHeightChanged(String stemHeight);
        public void onStemNatureChanged(String stemNature);
        public void onStemDiameterChanged(String stemDiameter);
        public void onStemFormChanged(String stemForm);
        public void onInternodesLengthChanged(String internodesLength);
        public void onConspicuousInternodesChanged(boolean conspicuousInternodes);
        public void onStemNakedChanged(boolean stemNaked);
        public void onStemCoveredChanged(boolean stemCovered);
        public void onThornsChanged(boolean thorns);
        public void onThornArrangementChanged(String thornArrangement);
        public void onStemColorChanged(ColorEspecimenDTO stemColor);
        public void onStemDescriptionChanged(String stemDescription);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onStemInformationChangedListener = (OnStemInformationChangedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement StemInformationFragment.OnStemInformationChangedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHolder = (ViewHolder) container.getTag(R.layout.fragment_stem_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_stem_information, container, false);
            retrieveViews(viewHolder);
            container.setTag(R.layout.fragment_stem_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        Bundle fragmentsBundle = getArguments();
        Long talloId = fragmentsBundle.getLong("talloId", 0l);
        String alturaDelTallo = fragmentsBundle.getString("alturaDelTallo");
        String diametroDelTallo = fragmentsBundle.getString("diametroDelTallo");
        String disposicionDeLasEspinas = fragmentsBundle.getString("disposicionDeLasEspinas");
        String formaDelTallo = fragmentsBundle.getString("formaDelTallo");
        String longitudEntrenudos = fragmentsBundle.getString("longitudEntrenudos");
        String naturalezaDelTallo = fragmentsBundle.getString("naturalezaDelTallo");
        String talloDescripcion = fragmentsBundle.getString("talloDescripcion");
        Boolean desnudoCubierto = fragmentsBundle.getBoolean("desnudoCubierto");
        Boolean entrenudosConspicuos = fragmentsBundle.getBoolean("entrenudosConspicuos");
        Boolean espinas = fragmentsBundle.getBoolean("espinas");
        colorDelTallo = fragmentsBundle.getParcelable("colorDelTallo");

        final String[] stemNatureOptions = getResources().getStringArray(R.array.stem_nature);
        ArrayAdapter<String> stemNatureAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                stemNatureOptions);
        viewHolder.stemNature.setAdapter(stemNatureAdapter);

        final String[] stemFormOptions = getResources().getStringArray(R.array.stem_form);
        ArrayAdapter<String> stemFormAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                stemFormOptions);
        viewHolder.stemForm.setAdapter(stemFormAdapter);

        final String[] thornArrangementOptions = getResources().getStringArray(R.array.thorn_arrangement);
        ArrayAdapter<String> thornArrangementAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                thornArrangementOptions);
        viewHolder.thornArrangement.setAdapter(thornArrangementAdapter);

        // Load stem information
        if (alturaDelTallo != null) {
            viewHolder.stemHeight.setText(alturaDelTallo);
        }
        if (diametroDelTallo != null) {
            viewHolder.stemDiameter.setText(diametroDelTallo);
        }
        if (disposicionDeLasEspinas != null) {
            viewHolder.thornArrangement.setSelection(Arrays.asList(thornArrangementOptions).indexOf(disposicionDeLasEspinas));
        }
        if (formaDelTallo != null) {
            viewHolder.stemForm.setSelection(Arrays.asList(stemFormOptions).indexOf(formaDelTallo));
        }
        if (longitudEntrenudos != null) {
            viewHolder.internodesLength.setText(longitudEntrenudos);
        }
        if (naturalezaDelTallo != null) {
            viewHolder.stemNature.setSelection(Arrays.asList(stemNatureOptions).indexOf(naturalezaDelTallo));
        }
        if (talloDescripcion != null) {
            viewHolder.stemDescription.setText(talloDescripcion);
        }
        if (desnudoCubierto != null) {
            viewHolder.stemNaked.setChecked(desnudoCubierto);
        }
        if (entrenudosConspicuos != null) {
            viewHolder.conspicuousInternodes.setChecked(entrenudosConspicuos);
        }
        if (espinas != null) {
            viewHolder.thorns.setChecked(espinas);
        }
        if (colorDelTallo != null) {
            viewHolder.stemColorText.setText(colorDelTallo.getNombre());
            setStemColor(colorDelTallo.getColorRGB());
        }

        viewHolder.stemNature.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onStemInformationChangedListener.onStemNatureChanged(value);
                }
            }
        });

        viewHolder.stemHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onStemInformationChangedListener.onStemHeightChanged(value);
                }
            }
        });

        viewHolder.stemDiameter.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onStemInformationChangedListener.onStemDiameterChanged(value);
                }
            }
        });
        viewHolder.stemForm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onStemInformationChangedListener.onStemFormChanged(value);
                }
            }
        });
        viewHolder.internodesLength.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onStemInformationChangedListener.onInternodesLengthChanged(value);
                }
            }
        });
        viewHolder.conspicuousInternodes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onStemInformationChangedListener.onConspicuousInternodesChanged(isChecked);
            }
        });

        viewHolder.stemNaked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onStemInformationChangedListener.onStemNakedChanged(isChecked);
                onStemInformationChangedListener.onStemCoveredChanged(!isChecked);
                viewHolder.stemCovered.setChecked(!isChecked);
            }
        });

        viewHolder.stemCovered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onStemInformationChangedListener.onStemCoveredChanged(isChecked);
                onStemInformationChangedListener.onStemNakedChanged(!isChecked);
                viewHolder.stemNaked.setChecked(!isChecked);
            }
        });

        viewHolder.thorns.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onStemInformationChangedListener.onThornsChanged(isChecked);
            }
        });
        viewHolder.thornArrangement.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onStemInformationChangedListener.onThornArrangementChanged(value);
                }
            }
        });
        viewHolder.stemDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onStemInformationChangedListener.onStemDescriptionChanged(value);
                }
            }
        });
        viewHolder.stemColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDelTallo != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDelTallo);
                    getActivity().startActivityForResult(editColorIntent, STEM_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Stem");
                    getActivity().startActivityForResult(createColorIntent, STEM_COLOR_CREATION_REQUEST);
                }
            }
        });
        return viewHolder.fragmentView;
    }

    private void retrieveViews(ViewHolder viewHolder) {
        viewHolder.stemNature = (Spinner) this.viewHolder.fragmentView.findViewById(R.id.stem_nature);
        viewHolder.stemHeight = (EditText) this.viewHolder.fragmentView.findViewById(R.id.stem_height);
        viewHolder.stemDiameter = (EditText) this.viewHolder.fragmentView.findViewById(R.id.stem_diameter);
        viewHolder.stemForm = (Spinner) this.viewHolder.fragmentView.findViewById(R.id.stem_form);
        viewHolder.internodesLength = (EditText) this.viewHolder.fragmentView.findViewById(R.id.internodes_length);
        viewHolder.conspicuousInternodes = (CheckBox) this.viewHolder.fragmentView.findViewById(R.id.conspicuous_internodes);
        viewHolder.stemNaked = (CheckBox) this.viewHolder.fragmentView.findViewById(R.id.stem_naked);
        viewHolder.stemCovered = (CheckBox) this.viewHolder.fragmentView.findViewById(R.id.stem_covered);
        viewHolder.thorns = (CheckBox) this.viewHolder.fragmentView.findViewById(R.id.thorns);
        viewHolder.thornArrangement = (Spinner) this.viewHolder.fragmentView.findViewById(R.id.thorn_arrangement);
        viewHolder.stemDescription = (EditText) this.viewHolder.fragmentView.findViewById(R.id.stem_description);
        viewHolder.stemColor = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.stem_color);
        viewHolder.stemColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.stem_color_text);
        viewHolder.stemColorImage = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.stem_color_view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == STEM_COLOR_CREATION_REQUEST || requestCode == STEM_COLOR_EDIT_REQUEST) && resultCode == Activity.RESULT_OK ) {
            ColorEspecimenDTO colorEspecimen = data.getParcelableExtra("colorEspecimen");
            switch (colorEspecimen.getOrganoDeLaPlanta()) {
                case "Stem":
                    viewHolder.stemColorText.setText(colorEspecimen.getNombre());
                    setStemColor(colorEspecimen.getColorRGB());
                    onStemInformationChangedListener.onStemColorChanged(colorEspecimen);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    static class ViewHolder{
        View fragmentView;
        Spinner stemNature;
        EditText stemHeight;
        EditText stemDiameter;
        Spinner stemForm;
        EditText internodesLength;
        CheckBox conspicuousInternodes;
        CheckBox stemNaked;
        CheckBox stemCovered;
        CheckBox thorns;
        Spinner thornArrangement;
        EditText stemDescription;
        LinearLayout stemColor;
        TextView stemColorText;
        ImageView stemColorImage;
    }

    public void setStemColor(int stemColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.stemColorImage.getWidth(), viewHolder.stemColorImage.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(stemColorRGB);
        viewHolder.stemColorImage.setImageBitmap(bitmapPlaceHolder);
    }
}