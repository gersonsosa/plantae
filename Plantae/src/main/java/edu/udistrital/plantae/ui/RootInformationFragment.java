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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;

/**
 * Created by hghar on 6/25/14.
 */
public class RootInformationFragment extends Fragment {
    public static final int ROOT_COLOR_CREATION_REQUEST = 52;
    public static final int ROOT_COLOR_EDIT_REQUEST = 62;
    private ColorEspecimenDTO colorDelCono;
    private ViewHolder viewHolder;
    private OnRootInformationChangedListener onRootInformationChangedListener;

    public interface OnRootInformationChangedListener {
        void onInBaseDiameterChanged(String inBaseDiameter);
        void onRootsDiameterChanged(String rootsDiameter);
        void onSpinesShapeChanged(String spinesShape);
        void onSpinesSizeChanged(String spinesSize);
        void onArmedChanged(boolean armed);
        void onConeHeightChanged(String coneHeight);
        void onConeColorChanged(ColorEspecimenDTO coneColor);
        void onRootsDescriptionChanged(String rootsDescription);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onRootInformationChangedListener = (OnRootInformationChangedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement RootInformationFragment.OnRootInformationChangedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHolder = (ViewHolder) container.getTag(R.layout.fragment_root_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_root_information, container, false);
            retrieveViews(viewHolder);
            container.setTag(R.layout.fragment_root_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        Bundle fragmentsBundle = getArguments();
        Long raizId = fragmentsBundle.getLong("raizId", 0l);
        String diametroDeLasRaices = fragmentsBundle.getString("diametroDeLasRaices");
        String diametroEnLaBase = fragmentsBundle.getString("diametroEnLaBase");
        String formaDeLasEspinas = fragmentsBundle.getString("formaDeLasEspinas");
        String tamañoDeLasEspinas = fragmentsBundle.getString("tamañoDeLasEspinas");
        String raizDescripcion = fragmentsBundle.getString("raizDescripcion");
        Boolean raizArmada = fragmentsBundle.getBoolean("raizArmada");
        Long alturaDelCono = fragmentsBundle.getLong("alturaDelCono", 0l);
        colorDelCono = fragmentsBundle.getParcelable("colorDelCono");

        // Load root information
        if (diametroDeLasRaices != null) {
            viewHolder.rootsDiameter.setText(diametroDeLasRaices);
        }
        if (diametroEnLaBase != null) {
            viewHolder.inBaseDiameter.setText(diametroEnLaBase);
        }
        if (formaDeLasEspinas != null) {
            viewHolder.spinesShape.setText(formaDeLasEspinas);
        }
        if (tamañoDeLasEspinas != null) {
            viewHolder.spinesSize.setText(tamañoDeLasEspinas);
        }
        if (raizDescripcion != null) {
            viewHolder.rootsDescription.setText(raizDescripcion);
        }
        viewHolder.armed.setChecked(raizArmada);
        if (alturaDelCono != 0l) {
            viewHolder.coneHeight.setText(alturaDelCono.toString());
        }
        if (colorDelCono != null) {
            viewHolder.coneColorText.setText(colorDelCono.getNombre());
            final ViewTreeObserver conoViewTreeObserver = viewHolder.coneColorImage.getViewTreeObserver();
            conoViewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    setConeColor(colorDelCono.getColorRGB());
                    return true;
                }
            });
        }

        viewHolder.rootsDiameter.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onRootInformationChangedListener.onRootsDiameterChanged(value);
                }
            }
        });

        viewHolder.inBaseDiameter.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onRootInformationChangedListener.onInBaseDiameterChanged(value);
                }
            }
        });

        viewHolder.spinesShape.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onRootInformationChangedListener.onSpinesShapeChanged(value);
                }
            }
        });
        viewHolder.spinesSize.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onRootInformationChangedListener.onSpinesSizeChanged(value);
                }
            }
        });
        viewHolder.rootsDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onRootInformationChangedListener.onRootsDescriptionChanged(value);
                }
            }
        });
        viewHolder.armed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onRootInformationChangedListener.onArmedChanged(isChecked);
            }
        });

        viewHolder.coneHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onRootInformationChangedListener.onConeHeightChanged(value);
                }
            }
        });

        viewHolder.coneColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDelCono != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDelCono);
                    getActivity().startActivityForResult(editColorIntent, ROOT_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Root Cone");
                    getActivity().startActivityForResult(createColorIntent, ROOT_COLOR_CREATION_REQUEST);
                }
            }
        });
        return viewHolder.fragmentView;
    }

    private void retrieveViews(ViewHolder viewHolder) {
        viewHolder.coneHeight = (EditText) this.viewHolder.fragmentView.findViewById(R.id.cone_height);
        viewHolder.inBaseDiameter = (EditText) this.viewHolder.fragmentView.findViewById(R.id.in_base_diameter);
        viewHolder.rootsDiameter = (EditText) this.viewHolder.fragmentView.findViewById(R.id.roots_diameter);
        viewHolder.armed = (CheckBox) this.viewHolder.fragmentView.findViewById(R.id.armed);
        viewHolder.spinesShape = (EditText) this.viewHolder.fragmentView.findViewById(R.id.spines_shape);
        viewHolder.spinesSize = (EditText) this.viewHolder.fragmentView.findViewById(R.id.spines_size);
        viewHolder.rootsDescription = (EditText) this.viewHolder.fragmentView.findViewById(R.id.roots_description);
        viewHolder.coneColor = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.cone_color);
        viewHolder.coneColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.cone_color_text);
        viewHolder.coneColorImage = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.cone_color_image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == ROOT_COLOR_CREATION_REQUEST || requestCode == ROOT_COLOR_EDIT_REQUEST) && resultCode == Activity.RESULT_OK ) {
            ColorEspecimenDTO colorEspecimen = data.getParcelableExtra("colorEspecimen");
            switch (colorEspecimen.getOrganoDeLaPlanta()) {
                case "Root Cone":
                    viewHolder.coneColorText.setText(colorEspecimen.getNombre());
                    setConeColor(colorEspecimen.getColorRGB());
                    colorDelCono = colorEspecimen;
                    onRootInformationChangedListener.onConeColorChanged(colorEspecimen);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    static class ViewHolder{
        View fragmentView;
        EditText coneHeight;
        EditText inBaseDiameter;
        EditText rootsDiameter;
        CheckBox armed;
        EditText spinesShape;
        EditText spinesSize;
        EditText rootsDescription;
        LinearLayout coneColor;
        TextView coneColorText;
        ImageView coneColorImage;
    }

    public void setConeColor(int coneColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.coneColorImage.getWidth(), viewHolder.coneColorImage.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(coneColorRGB);
        viewHolder.coneColorImage.setImageBitmap(bitmapPlaceHolder);
    }
}