package edu.udistrital.plantae.ui;

import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.*;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fenologia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habito;
import edu.udistrital.plantae.logicadominio.datosespecimen.MuestraAsociada;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.FenologiaDao;
import edu.udistrital.plantae.persistencia.HabitoDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hghar on 6/25/14.
 */
public class PlantAttributesFragment extends Fragment {

    private final Rect mTmpRect = new Rect();
    private int halfHeight;
    private TimeInterpolator ANIMATION_INTERPOLATOR = new DecelerateInterpolator();
    private int ANIMATION_DURATION = 400;
    private OnEditModeStarted onEditModeStarted;
    private ViewHolder viewHolder;
    private ColorsFragment colorsFragment;
    private AssociatedSamplesFragment associatedSamplesFragment;
    private OnPlantAttributesChangedListener onPlantAttributesChangedListener;

    public interface OnPlantAttributesChangedListener {
        public void onHabitChanged(String habit);
        public void onAlturaDeLaPlantaChanged(int alturaDeLaPlanta);
        public void onDapChanged(int dap);
        public void onAbundanciaChanged(String abundancia);
        public void onFenologiaChanged(String fenologia);
        public void onDescripcionEspecimenChanged(String descripcionEspecimen);
    }

    public interface OnEditModeStarted {
        public void onAssociaedSamplesEditModeStarted();
        public void onColorsEditModeStarted();
        public void onEditModeFinished();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onEditModeStarted = (OnEditModeStarted) activity;
            onPlantAttributesChangedListener = (OnPlantAttributesChangedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement PlantAttributesFragment.OnEditModeStarted and PlantAttributesFragment.OnPlantAttributesChangedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHolder = (ViewHolder) container.getTag(R.layout.fragment_plant_attributes_fragment);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_plant_attributes_fragment, container, false);
            retrieveViews(viewHolder);
            container.setTag(R.layout.fragment_plant_attributes_fragment, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }

        viewHolder.fragmentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                halfHeight = viewHolder.fragmentView.getHeight() / 2;
                viewHolder.editModePlantAttrib.setTranslationY(halfHeight);
                viewHolder.editModePlantAttrib.setAlpha(0f);
            }
        });

        Bundle fragmentsBundle = getArguments();

        Long usuarioId = fragmentsBundle.getLong("usuarioId", 0l);

        Long alturaDeLaPlanta = fragmentsBundle.getLong("alturaDeLaPlanta", 0l);
        Long dap = fragmentsBundle.getLong("dap", 0l);
        String abundancia = fragmentsBundle.getString("abundancia");
        String fenologia = fragmentsBundle.getString("fenologia");
        String descripcionEspecimen = fragmentsBundle.getString("descripcionEspecimen");
        String habito = fragmentsBundle.getString("habito");

        viewHolder.habit.setText(habito);
        viewHolder.fenology.setText(fenologia);
        if (alturaDeLaPlanta > 0l) {
            viewHolder.heightSeekBar.setProgress(alturaDeLaPlanta.intValue());
            viewHolder.height.setText(alturaDeLaPlanta.toString());
        }
        if (dap > 0l) {
            viewHolder.DAPSeekBar.setProgress(dap.intValue());
            viewHolder.DAP.setText(dap.toString());
        }
        viewHolder.abundance.setText(abundancia);
        viewHolder.plantDescription.setText(descripcionEspecimen);

        final DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();
        List<Habito> habitos = daoSession.getHabitoDao()._queryUsuario_Habitos(usuarioId);
        ArrayAdapter<Habito> habitoArrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),android.R.layout.simple_dropdown_item_1line,habitos);
        viewHolder.habit.setAdapter(habitoArrayAdapter);

        List<Fenologia> fenologias = daoSession.getFenologiaDao()._queryUsuario_Fenologias(usuarioId);
        ArrayAdapter<Fenologia> fenologiaArrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, fenologias);
        viewHolder.fenology.setAdapter(fenologiaArrayAdapter);

        viewHolder.habit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((AutoCompleteTextView)v).getText().toString();
                    onPlantAttributesChangedListener.onHabitChanged(value);
                }
            }
        });
        viewHolder.height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = viewHolder.height.getText().toString();
                    int intValue = 0;
                    try {
                        intValue = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        viewHolder.DAP.setError(getActivity().getString(R.string.error_number_format));
                    }
                    if (intValue < 120) {
                        viewHolder.heightSeekBar.setProgress(intValue);
                        onPlantAttributesChangedListener.onAlturaDeLaPlantaChanged(intValue);
                    }
                }
            }
        });
        viewHolder.DAP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = viewHolder.DAP.getText().toString();
                    int intValue = 0;
                    try {
                        intValue = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        viewHolder.DAP.setError(getActivity().getString(R.string.error_number_format));
                    }
                    if (intValue < 250) {
                        viewHolder.DAPSeekBar.setProgress(intValue);
                        onPlantAttributesChangedListener.onDapChanged(intValue);
                    }
                }
            }
        });
        viewHolder.heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewHolder.height.setText(String.valueOf(progress));
                onPlantAttributesChangedListener.onAlturaDeLaPlantaChanged(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        viewHolder.DAPSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewHolder.DAP.setText(String.valueOf(progress));
                onPlantAttributesChangedListener.onDapChanged(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        viewHolder.abundance.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onPlantAttributesChangedListener.onAbundanciaChanged(((EditText) v).getText().toString());
                }
            }
        });
        viewHolder.fenology.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((AutoCompleteTextView)v).getText().toString();
                    onPlantAttributesChangedListener.onFenologiaChanged(value);
                }
            }
        });
        viewHolder.plantDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onPlantAttributesChangedListener.onDescripcionEspecimenChanged(((EditText) v).getText().toString());
                }
            }
        });

        List<ColorEspecimenDTO> colores = fragmentsBundle.getParcelableArrayList("colores");

        StringBuilder stringBuilder = new StringBuilder();
        if (colores.size() == 0) {
            viewHolder.colorsList.setText(getString(R.string.no_colors));
        }else if (colores.size() > 0) {
            Iterator<ColorEspecimenDTO> iterator = colores.iterator();
            for (int i = 0; i < colores.size(); i++) {
                ColorEspecimenDTO colorEspecimenDTO = iterator.next();
                if (i > 0) {
                    if (i == colores.size() - 1) {
                        stringBuilder.append(" ").append(getString(R.string.and)).append(" ");
                    }else {
                        stringBuilder.append(getString(R.string.comma)).append(" ");
                    }
                }
                stringBuilder.append(colorEspecimenDTO.getDescripcion()).append(" ").append(colorEspecimenDTO.getNombre());
            }
            viewHolder.colorsList.setText(stringBuilder.toString());
        }

        colorsFragment = new ColorsFragment();
        colorsFragment.setArguments(fragmentsBundle);
        viewHolder.colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Plantae","colors click event");
                if (!colorsFragment.isAdded()) {
                    viewHolder.normalModePlantAttrib.fullScroll(View.FOCUS_UP);
                    getChildFragmentManager().beginTransaction().replace(R.id.edit_mode_fragment_container_plant_attrib, colorsFragment, "colorsList").commit();
                    focusOn(viewHolder.colors, viewHolder.formContainerPlantAttrib, true);
                    fadeOutToBottom(viewHolder.associatedSamples, true);
                    slideInToTop(viewHolder.editModePlantAttrib, true);
                    viewHolder.editModePlantAttrib.setVisibility(View.VISIBLE);
                    onEditModeStarted.onColorsEditModeStarted();
                }
            }
        });

        associatedSamplesFragment = new AssociatedSamplesFragment();
        associatedSamplesFragment.setArguments(fragmentsBundle);
        viewHolder.associatedSamples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Plantae","associated sameple click event");
                if (!associatedSamplesFragment.isAdded()) {
                    viewHolder.normalModePlantAttrib.fullScroll(View.FOCUS_UP);
                    getChildFragmentManager().beginTransaction().replace(R.id.edit_mode_fragment_container_plant_attrib, associatedSamplesFragment, "associatedSampleList").commit();
                    focusOn(viewHolder.associatedSamples, viewHolder.formContainerPlantAttrib, true);
                    slideInToTop(viewHolder.editModePlantAttrib, true);
                    viewHolder.editModePlantAttrib.setVisibility(View.VISIBLE);
                    onEditModeStarted.onAssociaedSamplesEditModeStarted();
                }
            }
        });
        return viewHolder.fragmentView;
    }

    private void focusOn(View v, View movableView, boolean animated) {

        v.getDrawingRect(mTmpRect);
        viewHolder.formContainerPlantAttrib.offsetDescendantRectToMyCoords(v, mTmpRect);

        movableView.animate().
                translationY(-mTmpRect.top).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(movableView)).
                start();
    }

    private void unFocus(View v, View movableView, boolean animated) {
        movableView.animate().
                translationY(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(movableView)).
                start();
    }

    private void fadeOutToBottom(View v, boolean animated) {
        v.animate().
                translationYBy(halfHeight).
                alpha(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(v)).
                start();
    }

    private void fadeInToTop(View v, boolean animated) {
        v.animate().
                translationYBy(-halfHeight).
                alpha(1).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(v)).
                start();
    }

    private void slideInToTop(View v, boolean animated) {
        v.animate().
                translationY(0).
                alpha(1).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setListener(new LayerEnablingAnimatorListener(v)).
                setInterpolator(ANIMATION_INTERPOLATOR);
    }

    private void slideOutToBottom(View v, boolean animated) {
        v.animate().
                translationY(halfHeight * 2).
                alpha(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setListener(new LayerEnablingAnimatorListener(v)).
                setInterpolator(ANIMATION_INTERPOLATOR);
    }

    private void stickTo(View v, View viewToStickTo, boolean animated) {
        v.getDrawingRect(mTmpRect);
        viewHolder.formContainerPlantAttrib.offsetDescendantRectToMyCoords(v, mTmpRect);

        v.animate().
                translationY(viewToStickTo.getHeight() - mTmpRect.top).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                start();
    }

    private void unstickFrom(View v, View viewToStickTo, boolean animated) {
        v.animate().
                translationY(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(viewToStickTo)).
                start();
    }

    private void retrieveViews(ViewHolder viewHolder) {
        viewHolder.normalModePlantAttrib = (ScrollView) viewHolder.fragmentView.findViewById(R.id.normal_mode_plant_attrib);
        viewHolder.formContainerPlantAttrib = (RelativeLayout) viewHolder.fragmentView.findViewById(R.id.form_container_plant_attrib);
        viewHolder.plantAttributes = (RelativeLayout) viewHolder.fragmentView.findViewById(R.id.plant_attributes);
        viewHolder.abundance = (EditText) viewHolder.fragmentView.findViewById(R.id.abundance);
        viewHolder.colors = (RelativeLayout) viewHolder.fragmentView.findViewById(R.id.specimen_colors);
        viewHolder.associatedSamples = (RelativeLayout) viewHolder.fragmentView.findViewById(R.id.associated_samples);
        viewHolder.habit = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.habit);
        viewHolder.height = (EditText) viewHolder.fragmentView.findViewById(R.id.height);
        viewHolder.DAP = (EditText) viewHolder.fragmentView.findViewById(R.id.DAP);
        viewHolder.heightSeekBar = (SeekBar) viewHolder.fragmentView.findViewById(R.id.height_seekbar);
        viewHolder.DAPSeekBar = (SeekBar) viewHolder.fragmentView.findViewById(R.id.DAP_seekbar);
        viewHolder.fenology = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.fenology);
        viewHolder.plantDescription = (EditText) viewHolder.fragmentView.findViewById(R.id.plant_description);
        viewHolder.editModePlantAttrib = (FrameLayout) viewHolder.fragmentView.findViewById(R.id.edit_mode_plant_attrib);
        viewHolder.editModeFragmentContainerPlantAttrib = (FrameLayout) viewHolder.fragmentView.findViewById(R.id.edit_mode_fragment_container_plant_attrib);
        viewHolder.colorsList = (TextView) viewHolder.fragmentView.findViewById(R.id.colors_list);
        viewHolder.associatedSampleList = (TextView) viewHolder.fragmentView.findViewById(R.id.associated_sample_list);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        associatedSamplesFragment.onActivityResult(requestCode, resultCode, data);
        colorsFragment.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    static class ViewHolder{
        View fragmentView;
        ScrollView normalModePlantAttrib;
        RelativeLayout formContainerPlantAttrib,plantAttributes, colors, associatedSamples;
        FrameLayout editModePlantAttrib, editModeFragmentContainerPlantAttrib;
        AutoCompleteTextView habit;
        EditText height;
        EditText DAP;
        SeekBar heightSeekBar;
        SeekBar DAPSeekBar;
        AutoCompleteTextView fenology;
        EditText abundance, plantDescription;
        TextView colorsList, associatedSampleList;
    }

    public void hideColorsList() {
        slideOutToBottom(viewHolder.editModePlantAttrib, true);
        fadeInToTop(viewHolder.associatedSamples, true);
        unFocus(viewHolder.colors, viewHolder.formContainerPlantAttrib, true);
        viewHolder.editModePlantAttrib.setVisibility(View.GONE);
        getChildFragmentManager().beginTransaction().remove(colorsFragment).commit();
        viewHolder.normalModePlantAttrib.fullScroll(View.FOCUS_DOWN);
        onEditModeStarted.onEditModeFinished();
    }

    public void hideAssociatedSamplesList() {
        slideOutToBottom(viewHolder.editModePlantAttrib, true);
        unFocus(viewHolder.associatedSamples, viewHolder.formContainerPlantAttrib, true);
        viewHolder.editModePlantAttrib.setVisibility(View.GONE);
        getChildFragmentManager().beginTransaction().remove(associatedSamplesFragment).commit();
        viewHolder.normalModePlantAttrib.fullScroll(View.FOCUS_DOWN);
        onEditModeStarted.onEditModeFinished();
    }

    public void updateAssociatedSamplesText(String newValue) {
        viewHolder.associatedSampleList.setText(newValue);
    }
    public void updateColorsText(String colors) {
        viewHolder.colorsList.setText(colors);
    }
}