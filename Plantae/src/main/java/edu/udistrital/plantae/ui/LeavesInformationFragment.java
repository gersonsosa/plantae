package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.Arrays;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;

/**
 * Created by hghar on 6/25/14.
 */
public class LeavesInformationFragment extends Fragment {

    public static final int LEAVES_COLOR_CREATION_REQUEST = 71;
    public static final int LEAVES_COLOR_EDIT_REQUEST = 81;

    private ColorEspecimenDTO colorDeLaVaina;
    private ColorEspecimenDTO colorDelPeciolo;
    private ViewHolder viewHolder;
    private OnLeavesInformationChangedListener onLeavesInformationChangedListener;

    public interface OnLeavesInformationChangedListener {
        void onRachisLengthChanged(String rachisLength);
        void onPinnaeArrangementChanged(String pinnaeArrangement);
        void onLeafArrangementChanged(String leafArrangement);
        void onPetioleSizeChanged(String petioleSize);
        void onPetioleFormChanged(String petioleForm);
        void onSheathNatureChanged(String sheathNature);
        void onPetioleCoverageChanged(String petioleCoverage);
        void onLimboNatureChanged(String limboNature);
        void onPinnaeNumberChanged(String pinnaeNumber);
        void onLeavesDescriptionChanged(String leavesDespcription);
        void onLeavesNumberChanged(String leavesNumber);
        void onSheathSizeChanged(String sheathSize);
        void onPetioleColorChanged(ColorEspecimenDTO petioleColor);
        void onSheathColorChanged(ColorEspecimenDTO sheathColor);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onLeavesInformationChangedListener = (OnLeavesInformationChangedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement LeavesInformationFragment.OnLeavesInformationChangedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHolder = (ViewHolder) container.getTag(R.layout.fragment_leaves_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_leaves_information, container, false);
            retrieveViews(viewHolder);
            container.setTag(R.layout.fragment_leaves_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        Bundle fragmentsBundle = getArguments();
        colorDeLaVaina = fragmentsBundle.getParcelable("colorDeLaVaina");
        colorDelPeciolo = fragmentsBundle.getParcelable("colorDelPeciolo");

        String coberturaDelPeciolo = fragmentsBundle.getString("coberturaDelPeciolo");
        String dispocicionDeLasPinnas = fragmentsBundle.getString("dispocicionDeLasPinnas");
        String disposicionDeLasHojas = fragmentsBundle.getString("disposicionDeLasHojas");
        String formaDelPeciolo = fragmentsBundle.getString("formaDelPeciolo");
        String longuitudDelRaquis = fragmentsBundle.getString("longuitudDelRaquis");
        String naturalezaDeLaVaina = fragmentsBundle.getString("naturalezaDeLaVaina");
        String naturalezaDelLimbo = fragmentsBundle.getString("naturalezaDelLimbo");
        String numeroDePinnas = fragmentsBundle.getString("numeroDePinnas");
        String numeroHojas = fragmentsBundle.getString("numeroHojas");
        String tamañoDeLaVaina = fragmentsBundle.getString("tamañoDeLaVaina");
        String tamañoDelPeciolo = fragmentsBundle.getString("tamañoDelPeciolo");
        String hojaDescripcion = fragmentsBundle.getString("hojaDescripcion");

        final String[] leavesArragementOptions = getResources().getStringArray(R.array.leaves_arrangement);
        ArrayAdapter<String> leavesArrangementAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                leavesArragementOptions);
        viewHolder.leafArrangement.setAdapter(leavesArrangementAdapter);

        final String[] sheathNatureOptions = getResources().getStringArray(R.array.sheath_nature);
        ArrayAdapter<String> sheathNatureAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                sheathNatureOptions);
        viewHolder.sheathNature.setAdapter(sheathNatureAdapter);
        final String[] petioleCoverageOptions = getResources().getStringArray(R.array.petiole_coverage);
        ArrayAdapter<String> petioleCoverageAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                petioleCoverageOptions);
        viewHolder.petioleCoverage.setAdapter(petioleCoverageAdapter);

        final String[] limboNatureOptions = getResources().getStringArray(R.array.limbo_nature);
        ArrayAdapter<String> limboNatureAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                limboNatureOptions);
        viewHolder.limboNature.setAdapter(limboNatureAdapter);
        if (colorDeLaVaina != null) {
            viewHolder.sheathColorText.setText(colorDeLaVaina.getNombre());
            final ViewTreeObserver vainaViewTreeObserver = viewHolder.sheathColor.getViewTreeObserver();
            vainaViewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    setSheathColor(colorDeLaVaina.getColorRGB());
                    return true;
                }
            });
        }

        if (colorDelPeciolo != null) {
            viewHolder.petioleColorText.setText(colorDelPeciolo.getNombre());
            final ViewTreeObserver pecioloViewTreeObserver = viewHolder.petioleColor.getViewTreeObserver();
            pecioloViewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    setPetioleColor(colorDelPeciolo.getColorRGB());
                    return true;
                }
            });
        }

        if (coberturaDelPeciolo != null) {
            viewHolder.petioleCoverage.setSelection(Arrays.asList(petioleCoverageOptions).indexOf(coberturaDelPeciolo));
        }

        if (dispocicionDeLasPinnas != null) {
            viewHolder.pinnaeArrangement.setText(dispocicionDeLasPinnas);
        }

        if (disposicionDeLasHojas != null) {
            viewHolder.leafArrangement.setSelection(Arrays.asList(leavesArragementOptions).indexOf(disposicionDeLasHojas));
        }

        if (formaDelPeciolo != null) {
            viewHolder.petioleForm.setText(formaDelPeciolo);
        }

        if (longuitudDelRaquis != null) {
            viewHolder.rachisLength.setText(longuitudDelRaquis);
        }

        if (naturalezaDeLaVaina != null) {
            viewHolder.sheathNature.setSelection(Arrays.asList(sheathNatureOptions).indexOf(naturalezaDeLaVaina));
        }

        if (naturalezaDelLimbo != null) {
            viewHolder.limboNature.setSelection(Arrays.asList(limboNatureOptions).indexOf(naturalezaDelLimbo));
        }

        if (numeroDePinnas != null) {
            viewHolder.pinnaeNumber.setText(numeroDePinnas);
        }

        if (numeroHojas != null) {
            viewHolder.leavesNumber.setText(numeroHojas);
        }

        if (tamañoDeLaVaina != null) {
            viewHolder.sheathSize.setText(tamañoDeLaVaina);
        }

        if (tamañoDelPeciolo != null) {
            viewHolder.petioleSize.setText(tamañoDelPeciolo);
        }

        if (hojaDescripcion != null) {
            viewHolder.leavesDescription.setText(hojaDescripcion);
        }

        viewHolder.petioleCoverage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onPetioleCoverageChanged(value);
            }
        });
        viewHolder.pinnaeArrangement.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onPinnaeArrangementChanged(value);
            }
        });
        viewHolder.leafArrangement.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onLeafArrangementChanged(value);
            }
        });
        viewHolder.petioleForm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onPetioleFormChanged(value);
            }
        });
        viewHolder.rachisLength.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onRachisLengthChanged(value);
            }
        });
        viewHolder.sheathNature.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onSheathNatureChanged(value);
            }
        });
        viewHolder.limboNature.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onLimboNatureChanged(value);
            }
        });
        viewHolder.pinnaeNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onPinnaeNumberChanged(value);
            }
        });
        viewHolder.leavesNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onLeavesNumberChanged(value);
            }
        });
        viewHolder.sheathSize.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onSheathSizeChanged(value);
            }
        });
        viewHolder.petioleSize.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onPetioleSizeChanged(value);
            }
        });
        viewHolder.leavesDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onLeavesInformationChangedListener.onLeavesDescriptionChanged(value);
            }
        });

        viewHolder.sheath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDeLaVaina != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDeLaVaina);
                    getActivity().startActivityForResult(editColorIntent,   LEAVES_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Leaves Sheath");
                    getActivity().startActivityForResult(createColorIntent,     LEAVES_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.petiole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDelPeciolo != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDelPeciolo);
                    getActivity().startActivityForResult(editColorIntent,   LEAVES_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Leaves Petiole");
                    getActivity().startActivityForResult(createColorIntent,     LEAVES_COLOR_CREATION_REQUEST);
                }
            }
        });
        return viewHolder.fragmentView;
    }

    private void retrieveViews(ViewHolder viewHolder) {
        viewHolder.leavesNumber = (EditText) this.viewHolder.fragmentView.findViewById(R.id.leaves_number);
        viewHolder.leafArrangement = (Spinner) this.viewHolder.fragmentView.findViewById(R.id.leaf_arrangement);
        viewHolder.sheathNature = (Spinner) this.viewHolder.fragmentView.findViewById(R.id.sheath_nature);
        viewHolder.sheathSize = (EditText) this.viewHolder.fragmentView.findViewById(R.id.sheath_size);
        viewHolder.limboNature = (Spinner) this.viewHolder.fragmentView.findViewById(R.id.limbo_nature);
        viewHolder.rachisLength = (EditText) this.viewHolder.fragmentView.findViewById(R.id.rachis_length);
        viewHolder.petioleSize = (EditText) this.viewHolder.fragmentView.findViewById(R.id.petiole_size);
        viewHolder.petioleForm = (EditText) this.viewHolder.fragmentView.findViewById(R.id.petiole_form);
        viewHolder.petioleCoverage = (Spinner) this.viewHolder.fragmentView.findViewById(R.id.petiole_coverage);
        viewHolder.pinnaeNumber = (EditText) this.viewHolder.fragmentView.findViewById(R.id.pinnae_number);
        viewHolder.pinnaeArrangement = (EditText) this.viewHolder.fragmentView.findViewById(R.id.pinnae_arrangement);
        viewHolder.leavesDescription = (EditText) this.viewHolder.fragmentView.findViewById(R.id.stem_description);
        viewHolder.sheath = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.sheath_color);
        viewHolder.sheathColorText = (EditText) this.viewHolder.fragmentView.findViewById(R.id.sheath_color_text);
        viewHolder.sheathColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.sheath_color_view);
        viewHolder.petiole = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.petiole_color);
        viewHolder.petioleColorText = (EditText) this.viewHolder.fragmentView.findViewById(R.id.petiole_color_text);
        viewHolder.petioleColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.petiole_color_image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == LEAVES_COLOR_CREATION_REQUEST || requestCode == LEAVES_COLOR_EDIT_REQUEST) && resultCode == Activity.RESULT_OK ) {
            ColorEspecimenDTO colorEspecimen = data.getParcelableExtra("colorEspecimen");
            switch (colorEspecimen.getOrganoDeLaPlanta()) {
                case "Leaves Sheath":
                    viewHolder.sheathColorText.setText(colorEspecimen.getNombre());
                    setSheathColor(colorEspecimen.getColorRGB());
                    colorDeLaVaina = colorEspecimen;
                    onLeavesInformationChangedListener.onSheathColorChanged(colorEspecimen);
                    break;
                case "Leaves Petiole":
                    viewHolder.petioleColorText.setText(colorEspecimen.getNombre());
                    setPetioleColor(colorEspecimen.getColorRGB());
                    colorDelPeciolo = colorEspecimen;
                    onLeavesInformationChangedListener.onPetioleColorChanged(colorEspecimen);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    static class ViewHolder{
        View fragmentView;
        EditText leavesNumber;
        Spinner leafArrangement;
        Spinner sheathNature;
        EditText sheathSize;
        Spinner limboNature;
        EditText rachisLength;
        EditText petioleSize;
        EditText petioleForm;
        Spinner petioleCoverage;
        EditText pinnaeNumber;
        EditText pinnaeArrangement;
        EditText leavesDescription;
        LinearLayout sheath;
        EditText sheathColorText;
        ImageView sheathColor;
        LinearLayout petiole;
        EditText petioleColorText;
        ImageView petioleColor;
    }

    public void setSheathColor(int sheathColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.sheathColor.getWidth(), viewHolder.sheathColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(sheathColorRGB);
        viewHolder.sheathColor.setImageBitmap(bitmapPlaceHolder);
    }

    public void setPetioleColor(int petioleColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.petioleColor.getWidth(), viewHolder.petioleColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(petioleColorRGB);
        viewHolder.petioleColor.setImageBitmap(bitmapPlaceHolder);
    }
}