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
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fruto;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;

/**
 * Created by hghar on 6/25/14.
 */
public class FruitInformationFragment extends Fragment {

    public static final int FRUIT_COLOR_CREATION_REQUEST = 56;
    public static final int FRUIT_COLOR_EDIT_REQUEST = 66;
    private ColorEspecimenDTO colorDelExocarpio;
    private ColorEspecimenDTO colorDelMesocarpio;
    private ColorEspecimenDTO colorDelMesocarpioInmaduro;
    private ColorEspecimenDTO colorDelExocarpioInmaduro;
    private ViewHolder viewHolder;
    private OnFruitInformationChangedListener onFruitInformationChangedListener;

    public interface OnFruitInformationChangedListener {
    	public void onPericarpConsistencyChanged(String pericarp);
        public void onExocarpColorChanged(ColorEspecimenDTO exocarpColor);
        public void onMesocarpColorChanged(ColorEspecimenDTO mesocarpColor);
        public void onExocarpImmatureColorChanged(ColorEspecimenDTO exocarpImmatureColor);
        public void onMesocarpImmatureColorChanged(ColorEspecimenDTO mesocarpImmatureColor);
        public void onFruitDescriptionChanged(String fruitDescription);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onFruitInformationChangedListener = (OnFruitInformationChangedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement FruitInformationFragment.OnFruitInformationChangedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	viewHolder = (ViewHolder) container.getTag(R.layout.fragment_fruits_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_fruits_information, container, false);
            retrieveViews(viewHolder);
            container.setTag(R.layout.fragment_fruits_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        Bundle fragmentsBundle = getArguments();
        String frutoDescripcion = fragmentsBundle.getString("frutoDescripcion");
        String consistenciaDelPericarpio = fragmentsBundle.getString("consistenciaDelPericarpio");
        colorDelExocarpio = fragmentsBundle.getParcelable("colorDelExocarpio");
        colorDelMesocarpio = fragmentsBundle.getParcelable("colorDelMesocarpio");
        colorDelExocarpioInmaduro = fragmentsBundle.getParcelable("colorDelExocarpioInmaduro");
        colorDelMesocarpioInmaduro = fragmentsBundle.getParcelable("colorDelMesocarpioInmaduro");

        // Load fruit information
        if (consistenciaDelPericarpio != null) {
            viewHolder.pericarpTextView.setText(consistenciaDelPericarpio);
        }
        if (frutoDescripcion != null) {
            viewHolder.fruitsDescription.setText(frutoDescripcion);
        }
        if (colorDelExocarpio != null) {
            viewHolder.exocarpColorText.setText(colorDelExocarpio.getNombre());
            setExocarpColor(colorDelExocarpio.getColorRGB());
        }
        if (colorDelMesocarpio != null) {
            viewHolder.mesocarpColorText.setText(colorDelMesocarpio.getNombre());
            setMesocarpColor(colorDelMesocarpio.getColorRGB());
        }
        if (colorDelMesocarpioInmaduro != null) {
            viewHolder.mesocarpImmatureColorText.setText(colorDelMesocarpioInmaduro.getNombre());
            setMesocarpImmatureColor(colorDelMesocarpioInmaduro.getColorRGB());
        }
        if (colorDelExocarpioInmaduro != null) {
            viewHolder.exocarpImmatureColorText.setText(colorDelExocarpioInmaduro.getNombre());
            setExocarpImmatureColor(colorDelExocarpioInmaduro.getColorRGB());
        }

        final DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();
        List<Fruto> fruits = daoSession.getFrutoDao().loadAll();
        List<String> pericarpConsistencyValues = new ArrayList<>();
        for (Fruto fruto:fruits) {
            pericarpConsistencyValues.add(fruto.getConsistenciaDelPericarpio());
        }
        pericarpConsistencyValues.add(getString(R.string.pericarp_dry));
        pericarpConsistencyValues.add(getString(R.string.pericarp_fleshy));
        pericarpConsistencyValues.add(getString(R.string.pericarp_irritating));
        ArrayAdapter<String> pericarpArrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),android.R.layout.simple_dropdown_item_1line, pericarpConsistencyValues);
        viewHolder.pericarpTextView.setAdapter(pericarpArrayAdapter);

        viewHolder.pericarpTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onFruitInformationChangedListener.onPericarpConsistencyChanged(value);
                }
            }
        });

        viewHolder.fruitsDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onFruitInformationChangedListener.onFruitDescriptionChanged(value);
                }
            }
        });
        viewHolder.exocarp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDelExocarpio != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDelExocarpio);
                    getActivity().startActivityForResult(editColorIntent, FRUIT_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Fruit Excarp");
                    getActivity().startActivityForResult(createColorIntent, FRUIT_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.mesocarp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDelMesocarpio != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDelMesocarpio);
                    getActivity().startActivityForResult(editColorIntent, FRUIT_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Fruit Mesocarp");
                    getActivity().startActivityForResult(createColorIntent, FRUIT_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.exocarpImmature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDelExocarpioInmaduro != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDelExocarpioInmaduro);
                    getActivity().startActivityForResult(editColorIntent, FRUIT_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Fruit Inmature Excarp");
                    getActivity().startActivityForResult(createColorIntent, FRUIT_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.mesocarpImmature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDelMesocarpioInmaduro != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDelMesocarpioInmaduro);
                    getActivity().startActivityForResult(editColorIntent, FRUIT_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Flower Gineceo");
                    getActivity().startActivityForResult(createColorIntent, FRUIT_COLOR_CREATION_REQUEST);
                }
            }
        });
        return viewHolder.fragmentView;
    }

    private void retrieveViews(ViewHolder viewHolder) {
        viewHolder.pericarpTextView = (AutoCompleteTextView) this.viewHolder.fragmentView.findViewById(R.id.pericarp);
        viewHolder.exocarpColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.exocarp_color_text);
        viewHolder.exocarpColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.exocarp_color);
        viewHolder.exocarp = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.exocarp);
        viewHolder.mesocarpColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.mesocarp_color_text);
        viewHolder.mesocarpColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.mesocarp_color);
        viewHolder.mesocarp = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.mesocarp);
        viewHolder.exocarpImmatureColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.exocarp_immature_color_text);
        viewHolder.exocarpImmatureColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.exocarp_immature_color);
        viewHolder.exocarpImmature = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.exocarp_immature);
        viewHolder.mesocarpImmatureColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.mesocarp_immature_color_text);
        viewHolder.mesocarpImmatureColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.mesocarp_immature_color);
        viewHolder.mesocarpImmature = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.mesocarp_immature);
        viewHolder.fruitsDescription = (EditText) this.viewHolder.fragmentView.findViewById(R.id.fruits_description);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == FRUIT_COLOR_CREATION_REQUEST || requestCode == FRUIT_COLOR_EDIT_REQUEST) && resultCode == Activity.RESULT_OK ) {
            ColorEspecimenDTO colorEspecimen = data.getParcelableExtra("colorEspecimen");
            switch (colorEspecimen.getOrganoDeLaPlanta()) {
                case "Fruit Endocarp":
                    viewHolder.mesocarpColorText.setText(colorEspecimen.getNombre());
                    setMesocarpColor(colorEspecimen.getColorRGB());
                    onFruitInformationChangedListener.onExocarpColorChanged(colorEspecimen);
                    break;
                case "Fruit Excarp":
                    viewHolder.exocarpColorText.setText(colorEspecimen.getNombre());
                    setExocarpColor(colorEspecimen.getColorRGB());
                    onFruitInformationChangedListener.onMesocarpColorChanged(colorEspecimen);
                    break;
                case "Fruit Inmature Endocarp":
                    viewHolder.mesocarpImmatureColorText.setText(colorEspecimen.getNombre());
                    setMesocarpImmatureColor(colorEspecimen.getColorRGB());
                    onFruitInformationChangedListener.onExocarpImmatureColorChanged(colorEspecimen);
                    break;
                case "Fruit Inmature Excarp":
                    viewHolder.exocarpImmatureColorText.setText(colorEspecimen.getNombre());
                    setExocarpImmatureColor(colorEspecimen.getColorRGB());
                    onFruitInformationChangedListener.onMesocarpImmatureColorChanged(colorEspecimen);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    static class ViewHolder{
        View fragmentView;
        AutoCompleteTextView pericarpTextView;
        TextView exocarpColorText;
        ImageView exocarpColor;
        LinearLayout exocarp;
        TextView mesocarpColorText;
        ImageView mesocarpColor;
        LinearLayout mesocarp;
        TextView exocarpImmatureColorText;
        ImageView exocarpImmatureColor;
        LinearLayout exocarpImmature;
        TextView mesocarpImmatureColorText;
        ImageView mesocarpImmatureColor;
        LinearLayout mesocarpImmature;
        EditText fruitsDescription;
    }

    public void setExocarpColor(int corollaColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.exocarpColor.getWidth(), viewHolder.exocarpColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(corollaColorRGB);
        viewHolder.exocarpColor.setImageBitmap(bitmapPlaceHolder);
    }

    public void setMesocarpColor(int stamenColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.mesocarpColor.getWidth(), viewHolder.mesocarpColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(stamenColorRGB);
        viewHolder.mesocarpColor.setImageBitmap(bitmapPlaceHolder);
    }
    public void setExocarpImmatureColor(int pistiliodioColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.exocarpImmatureColor.getWidth(), viewHolder.exocarpImmatureColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(pistiliodioColorRGB);
        viewHolder.exocarpImmatureColor.setImageBitmap(bitmapPlaceHolder);
    }
    public void setMesocarpImmatureColor(int gineceoColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.mesocarpImmatureColor.getWidth(), viewHolder.mesocarpImmatureColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(gineceoColorRGB);
        viewHolder.mesocarpImmatureColor.setImageBitmap(bitmapPlaceHolder);
    }
}