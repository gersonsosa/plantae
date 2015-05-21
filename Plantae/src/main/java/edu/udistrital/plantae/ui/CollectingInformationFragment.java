package edu.udistrital.plantae.ui;

import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.EspecimenColectorSecundario;
import edu.udistrital.plantae.logicadominio.recoleccion.ColectorSecundario;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;

/**
 * Created by Gerson Sosa on 6/5/14.
 */
public class CollectingInformationFragment extends Fragment {

    private ViewHolder viewHolder;
    private SecondaryCollectorsListFragment fragmentSecondaryCollectosList;
    private final Rect mTmpRect = new Rect();
    private int halfHeight;
    private TimeInterpolator ANIMATION_INTERPOLATOR = new DecelerateInterpolator();
    private int ANIMATION_DURATION = 400;
    private OnEditModeStarted onEditModeStarted;
    private String secondaryCollectorsNames;
    private OnCollectingInformationUpdated onCollectingInformationUpdated;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onEditModeStarted = (OnEditModeStarted) activity;
            onCollectingInformationUpdated = (OnCollectingInformationUpdated) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement CollectingInformationFragment.OnEditModeStarted And CollectingInformationFragment.OnCollectingInformationUpdated");
        }
    }

    public interface OnCollectingInformationUpdated {
        public void onCollectorNumberUpdated(String collectorNumber);
        public void onCollectionMethodUpdated(String collectionMethod);
        public void onStationUpdated(String station);
    }

    public interface OnEditModeStarted {
        public void onSecondaryCollectorsEditModeStarted();
        public void onEditModeFinished();
    }

    public SecondaryCollectorsListFragment getFragmentSecondaryCollectosList() {
        return fragmentSecondaryCollectosList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHolder = (ViewHolder) container.getTag(R.layout.fragment_collecting_information);
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            retrieveViews(inflater, container, viewHolder);
            container.setTag(R.layout.fragment_collecting_information, viewHolder);
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
                viewHolder.editModeCollectingInfo.setTranslationY(halfHeight);
                viewHolder.editModeCollectingInfo.setAlpha(0f);
            }
        });

        Bundle arguments = getArguments();
        String numeroDeColeccion = arguments.getString("numeroDeColeccion");
        String metodoColeccion = arguments.getString("metodoColeccion");
        String estacionDelAño = arguments.getString("estacionDelAño");
        Date fechaInicial =  new Date(arguments.getLong("fechaInicial", 0l));
        if (TextUtils.isEmpty(secondaryCollectorsNames)) {
            secondaryCollectorsNames = getArguments().getString("secondaryCollectorsNames");
        }
        viewHolder.collectionDate.setText(SimpleDateFormat.getDateInstance().format(fechaInicial));
        viewHolder.collectorNumber.setText(numeroDeColeccion);
        viewHolder.collectionMethod.setText(metodoColeccion);
        viewHolder.collectionStation.setText(estacionDelAño);
        viewHolder.specimenSecondaryCollectors.setText(secondaryCollectorsNames);
        viewHolder.collectorNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onCollectingInformationUpdated.onCollectorNumberUpdated(((EditText) v).getText().toString());
                }
            }
        });
        viewHolder.collectionMethod.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onCollectingInformationUpdated.onCollectionMethodUpdated(((EditText) v).getText().toString());
                }
            }
        });
        viewHolder.collectionStation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    onCollectingInformationUpdated.onStationUpdated(((EditText) v).getText().toString());
                }
            }
        });
        fragmentSecondaryCollectosList = new SecondaryCollectorsListFragment();
        fragmentSecondaryCollectosList.setArguments(getArguments());
        viewHolder.secondaryCollectorsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.edit_mode_fragment_container_collecting_info, fragmentSecondaryCollectosList, "secondaryCollectorsList").commit();
                focusOn(viewHolder.secondaryCollectorsCard, viewHolder.formContainerCollectingInfo, true);
                slideInToTop(viewHolder.editModeCollectingInfo, true);
                viewHolder.editModeCollectingInfo.setVisibility(View.VISIBLE);
                onEditModeStarted.onSecondaryCollectorsEditModeStarted();
            }
        });
        return viewHolder.fragmentView;
    }

    private void retrieveViews(LayoutInflater inflater, ViewGroup container, ViewHolder viewHolder) {
        viewHolder.fragmentView = inflater.inflate(R.layout.fragment_collecting_information, container, false);
        viewHolder.editModeCollectingInfo = (FrameLayout) viewHolder.fragmentView.findViewById(R.id.edit_mode_collecting_info);
        viewHolder.editModeFragmentContainerCollectingInfo = (FrameLayout) viewHolder.fragmentView.findViewById(R.id.edit_mode_fragment_container_collecting_info);
        viewHolder.formContainerCollectingInfo = (RelativeLayout) viewHolder.fragmentView.findViewById(R.id.form_container_collecting_info);
        viewHolder.secondaryCollectorsCard = (RelativeLayout) viewHolder.fragmentView.findViewById(R.id.secondary_collectors_card);
        viewHolder.collectingInformationCard = (RelativeLayout) viewHolder.fragmentView.findViewById(R.id.collecting_information_card);
        viewHolder.collectionDate = (TextView) viewHolder.fragmentView.findViewById(R.id.collection_date);
        viewHolder.collectorNumber = (EditText) viewHolder.fragmentView.findViewById(R.id.collector_number);
        viewHolder.collectionMethod = (EditText) viewHolder.fragmentView.findViewById(R.id.collection_method);
        viewHolder.collectionStation = (EditText) viewHolder.fragmentView.findViewById(R.id.collection_station);
        viewHolder.specimenSecondaryCollectors = (TextView) viewHolder.fragmentView.findViewById(R.id.specimen_secondary_collectors_normal_mode);
    }

    public void updateSecodaryCollectorsText(String newValue) {
        viewHolder.specimenSecondaryCollectors.setText(newValue);
    }

    private void focusOn(View v, View movableView, boolean animated) {

        v.getDrawingRect(mTmpRect);
        viewHolder.formContainerCollectingInfo.offsetDescendantRectToMyCoords(v, mTmpRect);

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
        viewHolder.formContainerCollectingInfo.offsetDescendantRectToMyCoords(v, mTmpRect);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        fragmentSecondaryCollectosList.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void hideSecondaryCollectorList() {
        slideOutToBottom(viewHolder.editModeCollectingInfo, true);
        unFocus(viewHolder.secondaryCollectorsCard, viewHolder.formContainerCollectingInfo, true);
        viewHolder.editModeCollectingInfo.setVisibility(View.GONE);
        getChildFragmentManager().beginTransaction().remove(fragmentSecondaryCollectosList).commit();
        onEditModeStarted.onEditModeFinished();
        //getActivity().invalidateOptionsMenu();
    }

    public boolean inEditMode() {
        return fragmentSecondaryCollectosList.isVisible();
    }

    static class ViewHolder{
        View fragmentView;
        FrameLayout editModeCollectingInfo, editModeFragmentContainerCollectingInfo;
        RelativeLayout formContainerCollectingInfo, secondaryCollectorsCard, collectingInformationCard;
        TextView collectionDate;
        EditText collectorNumber;
        EditText collectionMethod;
        EditText collectionStation;
        TextView specimenSecondaryCollectors;
    }
}