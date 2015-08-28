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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
public class InflorescenceInformationFragment extends Fragment {

    public static final int INFLORESCENCE_COLOR_CREATION_REQUEST = 73;
    public static final int INFLORESCENCE_COLOR_EDIT_REQUEST = 83;

    private ColorEspecimenDTO colorEnFlor;
    private ColorEspecimenDTO colorEnFruto;
    private ViewHolder viewHolder;
    private OnInflorescenceInformationChangedListener onInflorescenceInformationChangedListener;

    public interface OnInflorescenceInformationChangedListener {
        void onInflorescencePositionChanged(String inflorescencePosition);
        void onInflorescenceAloneChanged(boolean inflorescenceAlone);
        void onProphyllNatureChanged(String prophyllNature);
        void onProphyllSizeChanged(String prophyllSize);
        void onBractsNumberChanged(String bractsNumber);
        void onBractsPositionChanged(String bractsPosition);
        void onBractsSizeChanged(String bractsSize);
        void onBractsNatureChanged(String bractsNature);
        void onPeduncleSizeChanged(String peduncleSize);
        void onRachisSizeChanged(String rachisSize);
        void onRachillaeNumberChanged(String rachillaeNumber);
        void onRachillaeSizeChanged(String rachillaeSize);
        void onInflorescenceDescriptionChanged(String inflorescenceDescription);
        void onInFlowerColorChanged(ColorEspecimenDTO exocarpColor);
        void onInFruitColorChanged(ColorEspecimenDTO mesocarpColor);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onInflorescenceInformationChangedListener = (OnInflorescenceInformationChangedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement InflorescenceInformationFragment.OnInflorescenceInformationChangedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHolder = (ViewHolder) container.getTag(R.layout.fragment_inflorescence_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_inflorescence_information, container, false);
            retrieveViews(viewHolder);
            container.setTag(R.layout.fragment_inflorescence_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        Bundle fragmentsBundle = getArguments();
        colorEnFlor = fragmentsBundle.getParcelable("colorDeLaInflorescenciaEnFlor");
        colorEnFruto = fragmentsBundle.getParcelable("colorDeLaInflorescenciaEnFruto");
        String naturalezaDeLasBracteasPedunculares = fragmentsBundle.getString("naturalezaDeLasBracteasPedunculares");
        String naturalezaDelProfilo = fragmentsBundle.getString("naturalezaDelProfilo");
        String posicionDeLasBracteasPedunculares = fragmentsBundle.getString("posicionDeLasBracteasPedunculares");
        String posicionDeLasInflorescencias = fragmentsBundle.getString("posicionDeLasInflorescencias");
        String tamañoDeLasBracteasPedunculares = fragmentsBundle.getString("tamañoDeLasBracteasPedunculares");
        String tamañoDelPedunculo = fragmentsBundle.getString("tamañoDelPedunculo");
        String tamañoDelProfilo = fragmentsBundle.getString("tamañoDelProfilo");
        String tamañoDelRaquis = fragmentsBundle.getString("tamañoDelRaquis");
        String tamañoDeRaquilas = fragmentsBundle.getString("tamañoDeRaquilas");
        String inflorescenciaDescripcion = fragmentsBundle.getString("inflorescenciaDescripcion");
        Boolean inflorescenciaSolitaria = fragmentsBundle.getBoolean("inflorescenciaSolitaria");
        String numeroDeLasBracteasPedunculares = String.valueOf(fragmentsBundle.getInt("numeroDeLasBracteasPedunculares"));
        String numeroDeRaquilas = String.valueOf(fragmentsBundle.getInt("numeroDeRaquilas"));

        final String[] inflorescencePositionOptions = getResources().getStringArray(R.array.inflorescence_position);
        ArrayAdapter<String> inflorescencePositionAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                inflorescencePositionOptions);
        viewHolder.inflorescencePosition.setAdapter(inflorescencePositionAdapter);

        if (colorEnFlor != null) {
            viewHolder.inFlowerColorText.setText(colorEnFlor.getNombre());
            final ViewTreeObserver enFlorViewTreeObserver = viewHolder.inFlowerColor.getViewTreeObserver();
            enFlorViewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    setInFlowerColor(colorEnFlor.getColorRGB());
                    return true;
                }
            });
        }

        if (colorEnFruto != null) {
            viewHolder.inFruitColorText.setText(colorEnFruto.getNombre());
            final ViewTreeObserver enFrutoViewTreeObserver = viewHolder.inFruitColor.getViewTreeObserver();
            enFrutoViewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    setInFruitColor(colorEnFruto.getColorRGB());
                    return true;
                }
            });
        }

        if (naturalezaDeLasBracteasPedunculares != null) {
            viewHolder.bractsNature.setText(naturalezaDeLasBracteasPedunculares);
        }

        if (naturalezaDelProfilo != null) {
            viewHolder.prophyllNature.setText(naturalezaDelProfilo);
        }

        if (posicionDeLasBracteasPedunculares != null) {
            viewHolder.bractsPosition.setText(posicionDeLasBracteasPedunculares);
        }

        if (posicionDeLasInflorescencias != null) {
            viewHolder.inflorescencePosition.setSelection(Arrays.asList(inflorescencePositionOptions).indexOf(posicionDeLasInflorescencias));
        }

        if (tamañoDeLasBracteasPedunculares != null) {
            viewHolder.bractsSize.setText(tamañoDeLasBracteasPedunculares);
        }

        if (tamañoDelPedunculo != null) {
            viewHolder.peduncleSize.setText(tamañoDelPedunculo);
        }

        if (tamañoDelProfilo != null) {
            viewHolder.prophyllSize.setText(tamañoDelProfilo);
        }

        if (tamañoDelRaquis != null) {
            viewHolder.rachisSize.setText(tamañoDelRaquis);
        }

        if (tamañoDeRaquilas != null) {
            viewHolder.rachillaeSize.setText(tamañoDelRaquis);
        }

        if (inflorescenciaDescripcion != null) {
            viewHolder.inflorescenceDescription.setText(inflorescenciaDescripcion);
        }

        viewHolder.inflorescenceAlone.setChecked(inflorescenciaSolitaria);
        viewHolder.bractsNumber.setText(numeroDeLasBracteasPedunculares);
        viewHolder.rachillaeNumber.setText(numeroDeRaquilas);

        viewHolder.bractsNature.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onBractsNatureChanged(value);
            }
        });
        viewHolder.prophyllNature.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onProphyllNatureChanged(value);
            }
        });
        viewHolder.prophyllSize.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onProphyllSizeChanged(value);
            }
        });
        viewHolder.bractsPosition.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onBractsPositionChanged(value);
            }
        });
        viewHolder.inflorescencePosition.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onInflorescencePositionChanged(value);
            }
        });
        viewHolder.bractsSize.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onBractsSizeChanged(value);
            }
        });
        viewHolder.peduncleSize.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onPeduncleSizeChanged(value);
            }
        });
        viewHolder.rachisSize.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onRachisSizeChanged(value);
            }
        });
        viewHolder.rachillaeSize.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onRachillaeSizeChanged(value);
            }
        });
        viewHolder.inflorescenceDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onInflorescenceDescriptionChanged(value);
            }
        });
        viewHolder.inflorescenceAlone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onInflorescenceInformationChangedListener.onInflorescenceAloneChanged(isChecked);
            }
        });
        viewHolder.bractsNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onBractsNumberChanged(value);
            }
        });
        viewHolder.rachillaeNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = ((EditText) v).getText().toString();
                onInflorescenceInformationChangedListener.onRachillaeNumberChanged(value);
            }
        });

        viewHolder.inFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorEnFlor != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorEnFlor);
                    getActivity().startActivityForResult(editColorIntent, INFLORESCENCE_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Inflorescence Flower");
                    getActivity().startActivityForResult(createColorIntent, INFLORESCENCE_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.inFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorEnFruto != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorEnFruto);
                    getActivity().startActivityForResult(editColorIntent, INFLORESCENCE_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Inflorescence Fruit");
                    getActivity().startActivityForResult(createColorIntent, INFLORESCENCE_COLOR_CREATION_REQUEST);
                }
            }
        });
        return viewHolder.fragmentView;
    }

    private void retrieveViews(ViewHolder viewHolder) {
        viewHolder.inflorescencePosition = (Spinner) this.viewHolder.fragmentView.findViewById(R.id.inflorescence_position);
        viewHolder.inflorescenceAlone = (CheckBox) this.viewHolder.fragmentView.findViewById(R.id.inflorescence_alone);
        viewHolder.prophyllNature = (EditText) this.viewHolder.fragmentView.findViewById(R.id.prophyll_nature);
        viewHolder.prophyllSize = (EditText) this.viewHolder.fragmentView.findViewById(R.id.prophyll_size);
        viewHolder.bractsNumber = (EditText) this.viewHolder.fragmentView.findViewById(R.id.bracts_number);
        viewHolder.bractsPosition = (EditText) this.viewHolder.fragmentView.findViewById(R.id.bracts_position);
        viewHolder.bractsSize = (EditText) this.viewHolder.fragmentView.findViewById(R.id.bracts_size);
        viewHolder.bractsNature = (EditText) this.viewHolder.fragmentView.findViewById(R.id.bracts_nature);
        viewHolder.peduncleSize = (EditText) this.viewHolder.fragmentView.findViewById(R.id.peduncle_size);
        viewHolder.rachisSize = (EditText) this.viewHolder.fragmentView.findViewById(R.id.rachis_size);
        viewHolder.rachillaeNumber = (EditText) this.viewHolder.fragmentView.findViewById(R.id.rachillae_number);
        viewHolder.rachillaeSize = (EditText) this.viewHolder.fragmentView.findViewById(R.id.rachillae_size);
        viewHolder.inFlower = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.in_flower);
        viewHolder.inFlowerColorText = (EditText) this.viewHolder.fragmentView.findViewById(R.id.in_flower_color_text);
        viewHolder.inFlowerColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.in_flower_color);
        viewHolder.inFruit = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.in_fruit);
        viewHolder.inFruitColorText = (EditText) this.viewHolder.fragmentView.findViewById(R.id.in_fruit_color_text);
        viewHolder.inFruitColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.in_fruit_color);
        viewHolder.inflorescenceDescription = (EditText) this.viewHolder.fragmentView.findViewById(R.id.inflorescence_description);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == INFLORESCENCE_COLOR_CREATION_REQUEST || requestCode == INFLORESCENCE_COLOR_EDIT_REQUEST) && resultCode == Activity.RESULT_OK ) {
            ColorEspecimenDTO colorEspecimen = data.getParcelableExtra("colorEspecimen");
            switch (colorEspecimen.getOrganoDeLaPlanta()) {
                case "Inflorescence Flower":
                    viewHolder.inFlowerColorText.setText(colorEspecimen.getNombre());
                    setInFlowerColor(colorEspecimen.getColorRGB());
                    colorEnFlor = colorEspecimen;
                    onInflorescenceInformationChangedListener.onInFlowerColorChanged(colorEspecimen);
                    break;
                case "Inflorescence Fruit":
                    viewHolder.inFruitColorText.setText(colorEspecimen.getNombre());
                    setInFruitColor(colorEspecimen.getColorRGB());
                    colorEnFruto = colorEspecimen;
                    onInflorescenceInformationChangedListener.onInFruitColorChanged(colorEspecimen);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    static class ViewHolder{
        View fragmentView;
        Spinner inflorescencePosition;
        CheckBox inflorescenceAlone;
        EditText prophyllNature;
        EditText prophyllSize;
        EditText bractsNumber;
        EditText bractsPosition;
        EditText bractsSize;
        EditText bractsNature;
        EditText peduncleSize;
        EditText rachisSize;
        EditText rachillaeNumber;
        EditText rachillaeSize;
        LinearLayout inFlower;
        EditText inFlowerColorText;
        ImageView inFlowerColor;
        LinearLayout inFruit;
        EditText inFruitColorText;
        ImageView inFruitColor;
        EditText inflorescenceDescription;
    }

    public void setInFruitColor(int corollaColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.inFruitColor.getWidth(), viewHolder.inFruitColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(corollaColorRGB);
        viewHolder.inFruitColor.setImageBitmap(bitmapPlaceHolder);
    }

    public void setInFlowerColor(int stamenColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.inFlowerColor.getWidth(), viewHolder.inFlowerColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(stamenColorRGB);
        viewHolder.inFlowerColor.setImageBitmap(bitmapPlaceHolder);
    }
}