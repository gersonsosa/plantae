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
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.FenologiaDao;
import edu.udistrital.plantae.persistencia.HabitoDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hghar on 6/25/14.
 */
public class PlantAttributesFragment extends Fragment {

    private EspecimenDTO especimenDTO;
    private final Rect mTmpRect = new Rect();
    private int halfHeight;
    private TimeInterpolator ANIMATION_INTERPOLATOR = new DecelerateInterpolator();
    private int ANIMATION_DURATION = 400;
    private OnEditModeStarted onEditModeStarted;
    private ViewHolder viewHolder;
    private ColorsFragment colorsFragment;
    private AssociatedSamplesFragment associatedSamplesFragment;

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
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement CollectingInformationFragment.OnEditModeStarted");
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

        especimenDTO = getArguments().getParcelable("especimen");
        final DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();
        List<Habito> habitos = daoSession.getHabitoDao()._queryUsuario_Habitos(especimenDTO.getUsuarioId());
        ArrayAdapter<Habito> habitoArrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),android.R.layout.simple_dropdown_item_1line,habitos);
        viewHolder.habit.setAdapter(habitoArrayAdapter);
        EditText abundance = (EditText) viewHolder.fragmentView.findViewById(R.id.abundance);

        List<Fenologia> fenologias = daoSession.getFenologiaDao()._queryUsuario_Fenologias(especimenDTO.getUsuarioId());
        ArrayAdapter<Fenologia> fenologiaArrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, fenologias);
        viewHolder.fenology.setAdapter(fenologiaArrayAdapter);

        viewHolder.habit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((AutoCompleteTextView)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        Habito habito = daoSession.getHabitoDao().queryBuilder().where(HabitoDao.Properties.Habito.eq(value)).unique();
                        if (habito == null) {
                            especimenDTO.setHabito(new Habito(value));
                        } else {
                            especimenDTO.setHabito(habito);
                        }
                    }
                }
            }
        });
        viewHolder.height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = viewHolder.height.getText().toString();
                    int intValue = Integer.parseInt(value);
                    if (!TextUtils.isEmpty(value) && intValue < 120) {
                        viewHolder.heightSeekBar.setProgress(intValue);
                        especimenDTO.setDap(Long.parseLong(value));
                    }
                }
            }
        });
        viewHolder.DAP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = viewHolder.DAP.getText().toString();
                    int intValue = Integer.parseInt(value);
                    if (!TextUtils.isEmpty(value) && intValue < 250) {
                        viewHolder.DAPSeekBar.setProgress(intValue);
                        especimenDTO.setDap(Long.parseLong(value));
                    }
                }
            }
        });
        viewHolder.heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewHolder.height.setText(String.valueOf(progress));
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
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        abundance.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    especimenDTO.setAbundancia(((EditText) v).getText().toString());
                }
            }
        });
        viewHolder.fenology.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((AutoCompleteTextView)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        Fenologia fenologia = daoSession.getFenologiaDao().queryBuilder().where(FenologiaDao.Properties.Fenologia.eq(value)).unique();
                        if (fenologia == null) {
                            especimenDTO.setFenologia(new Fenologia(value));
                        } else {
                            especimenDTO.setFenologia(fenologia);
                        }
                    }
                }
            }
        });
        viewHolder.plantDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    especimenDTO.setDescripcionEspecimen(((EditText) v).getText().toString());
                }
            }
        });

        colorsFragment = new ColorsFragment();
        Bundle colorFragmentBundle = new Bundle(1);
        colorFragmentBundle.putParcelable("especimen", especimenDTO);
        colorsFragment.setArguments(colorFragmentBundle);
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
        Bundle associatedSamplesFragmentBundle = new Bundle(1);
        associatedSamplesFragmentBundle.putParcelableArrayList("muestrasAsociadas", (ArrayList<? extends Parcelable>) especimenDTO.getMuestrasAsociadas());
        associatedSamplesFragment.setArguments(associatedSamplesFragmentBundle);
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
        EditText plantDescription;
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