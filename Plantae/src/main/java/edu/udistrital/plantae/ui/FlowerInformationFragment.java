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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;

/**
 * Created by hghar on 6/25/14.
 */
public class FlowerInformationFragment extends Fragment {

    public static final int FLOWER_COLOR_CREATION_REQUEST = 46;
    public static final int FLOWER_COLOR_EDIT_REQUEST = 36;

    private ViewHolder viewHolder;
    private OnFlowersInformationChangedListener onFlowersInformationChangedListener;
    private ColorEspecimenDTO colorDeLaCorola;
    private ColorEspecimenDTO colorDelCaliz;
    private ColorEspecimenDTO colorDelGineceo;
    private ColorEspecimenDTO colorDeLosEstambres;
    private ColorEspecimenDTO colorDeLosEstigmas;
    private ColorEspecimenDTO colorDeLosPistiliodios;

    public interface OnFlowersInformationChangedListener {
        public void onCalyxColorChanged(ColorEspecimenDTO calyxColor);
        public void onCorollaColorChanged(ColorEspecimenDTO corollaColor);
        public void onStamensColorChanged(ColorEspecimenDTO stamensColor);
        public void onPistiliodioColorChanged(ColorEspecimenDTO pistiliodioColor);
        public void onGineceoColorChanged(ColorEspecimenDTO gineceoColor);
        public void onStigmataColorChanged(ColorEspecimenDTO stigmataColor);
        public void onFlowersDescriptionChanged(String flowersDescription);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onFlowersInformationChangedListener = (OnFlowersInformationChangedListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement FlowerInformationFragment.OnFlowersInformationChangedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHolder = (ViewHolder) container.getTag(R.layout.fragment_flower_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_flower_information, container, false);
            retrieveViews(viewHolder);
            container.setTag(R.layout.fragment_flower_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        Bundle fragmentsBundle = getArguments();
        String florDescripcion = fragmentsBundle.getString("florDescripcion");
        colorDeLaCorola = fragmentsBundle.getParcelable("colorDeLaCorola");
        colorDelCaliz = fragmentsBundle.getParcelable("colorDelCaliz");
        colorDelGineceo = fragmentsBundle.getParcelable("colorDelGineceo");
        colorDeLosEstambres = fragmentsBundle.getParcelable("colorDeLosEstambres");
        colorDeLosEstigmas = fragmentsBundle.getParcelable("colorDeLosEstigmas");
        colorDeLosPistiliodios = fragmentsBundle.getParcelable("colorDeLosPistiliodios");

        EditText flowersDescription = (EditText) viewHolder.fragmentView.findViewById(R.id.flowers_description);
        // Load flower information
        if (florDescripcion != null) {
            flowersDescription.setText(florDescripcion);
        }
        final ViewTreeObserver corollaVto = viewHolder.corollaColor.getViewTreeObserver();
        corollaVto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                viewHolder.corollaColor.getViewTreeObserver().removeOnPreDrawListener(this);
                if (colorDeLaCorola != null) {
                    viewHolder.corollaColorText.setText(colorDeLaCorola.getNombre());
                    setCorollaColor(colorDeLaCorola.getColorRGB());
                }
                return true;
            }
        });
        final ViewTreeObserver calixVto = viewHolder.calyxColor.getViewTreeObserver();
        calixVto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                viewHolder.calyxColor.getViewTreeObserver().removeOnPreDrawListener(this);
                if (colorDelCaliz != null) {
                    viewHolder.calyxColorText.setText(colorDelCaliz.getNombre());
                    setCalyxColor(colorDelCaliz.getColorRGB());
                }
                return true;
            }
        });
        final ViewTreeObserver gineceoVto = viewHolder.gineceoColor.getViewTreeObserver();
        gineceoVto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                viewHolder.gineceoColor.getViewTreeObserver().removeOnPreDrawListener(this);
                if (colorDelGineceo != null) {
                    viewHolder.gineceoColorText.setText(colorDelGineceo.getNombre());
                    setGineceoColor(colorDelGineceo.getColorRGB());
                }
                return true;
            }
        });

        final ViewTreeObserver stamensVto = viewHolder.stamensColor.getViewTreeObserver();
        stamensVto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                viewHolder.stamensColor.getViewTreeObserver().removeOnPreDrawListener(this);
                if (colorDeLosEstambres != null) {
                    viewHolder.stamensColorText.setText(colorDeLosEstambres.getNombre());
                    setStamensColor(colorDeLosEstambres.getColorRGB());
                }
                return true;
            }
        });

        final ViewTreeObserver stigmataVto = viewHolder.stigmataColor.getViewTreeObserver();
        stigmataVto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                viewHolder.stigmataColor.getViewTreeObserver().removeOnPreDrawListener(this);
                if (colorDeLosEstigmas != null) {
                    viewHolder.stigmataColorText.setText(colorDeLosEstigmas.getNombre());
                    setStigmataColor(colorDeLosEstigmas.getColorRGB());
                }
                return true;
            }
        });

        final ViewTreeObserver pistiliodioVto = viewHolder.pistiliodioColor.getViewTreeObserver();
        pistiliodioVto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                viewHolder.pistiliodioColor.getViewTreeObserver().removeOnPreDrawListener(this);
                if (colorDeLosPistiliodios != null) {
                    viewHolder.pistiliodioColorText.setText(colorDeLosPistiliodios.getNombre());
                    setPistiliodioColor(colorDeLosPistiliodios.getColorRGB());
                }
                return true;
            }
        });

        flowersDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    onFlowersInformationChangedListener.onFlowersDescriptionChanged(value);
                }
            }
        });
        viewHolder.calyx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDelCaliz != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDelCaliz);
                    getActivity().startActivityForResult(editColorIntent, FLOWER_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Flower Calyx");
                    getActivity().startActivityForResult(createColorIntent, FLOWER_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.corolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDeLaCorola != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDeLaCorola);
                    getActivity().startActivityForResult(editColorIntent, FLOWER_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Flower Corolla");
                    getActivity().startActivityForResult(createColorIntent, FLOWER_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.stamens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDeLosEstambres != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDeLosEstambres);
                    getActivity().startActivityForResult(editColorIntent, FLOWER_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Flower Stamens");
                    getActivity().startActivityForResult(createColorIntent, FLOWER_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.pistiliodio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDeLosPistiliodios != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDeLosPistiliodios);
                    getActivity().startActivityForResult(editColorIntent, FLOWER_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Flower Pistiliodios");
                    getActivity().startActivityForResult(createColorIntent, FLOWER_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.gineceo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDelGineceo != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDelGineceo);
                    getActivity().startActivityForResult(editColorIntent, FLOWER_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Flower Gineceo");
                    getActivity().startActivityForResult(createColorIntent, FLOWER_COLOR_CREATION_REQUEST);
                }
            }
        });
        viewHolder.stigmata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorDeLosEstigmas != null) {
                    Intent editColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    editColorIntent.putExtra("color", colorDeLosEstigmas);
                    getActivity().startActivityForResult(editColorIntent, FLOWER_COLOR_EDIT_REQUEST);
                } else {
                    Intent createColorIntent = new Intent(getActivity().getApplicationContext(), CreateColorActivity.class);
                    createColorIntent.putExtra("plantOrgan", "Flower Stigmata");
                    getActivity().startActivityForResult(createColorIntent, FLOWER_COLOR_CREATION_REQUEST);
                }
            }
        });
        return viewHolder.fragmentView;
    }

    private void retrieveViews(ViewHolder viewHolder) {
        viewHolder.calyxColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.calyx_color);
        viewHolder.calyx = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.calyx);
        viewHolder.corollaColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.corolla_color);
        viewHolder.corolla = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.corolla);
        viewHolder.stamensColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.stamens_color);
        viewHolder.stamens = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.stamens);
        viewHolder.pistiliodioColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.pistiliodio_color);
        viewHolder.pistiliodio = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.pistiliodio);
        viewHolder.gineceoColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.gineceo_color);
        viewHolder.gineceo = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.gineceo);
        viewHolder.stigmataColor = (ImageView) this.viewHolder.fragmentView.findViewById(R.id.stigmata_color);
        viewHolder.stigmata = (LinearLayout) this.viewHolder.fragmentView.findViewById(R.id.stigmata);
        viewHolder.calyxColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.calyx_color_text);
        viewHolder.corollaColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.corolla_color_text);
        viewHolder.gineceoColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.gineceo_color_text);
        viewHolder.stamensColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.stamens_color_text);
        viewHolder.stigmataColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.stigmata_color_text);
        viewHolder.pistiliodioColorText = (TextView) this.viewHolder.fragmentView.findViewById(R.id.pistiliodio_color_text);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == FLOWER_COLOR_CREATION_REQUEST || requestCode == FLOWER_COLOR_EDIT_REQUEST) && resultCode == Activity.RESULT_OK ) {
            ColorEspecimenDTO colorEspecimen = data.getParcelableExtra("colorEspecimen");
            switch (colorEspecimen.getOrganoDeLaPlanta()) {
                case "Flower Corolla":
                    viewHolder.corollaColorText.setText(colorEspecimen.getNombre());
                    setCorollaColor(colorEspecimen.getColorRGB());
                    colorDeLaCorola = colorEspecimen;
                    onFlowersInformationChangedListener.onCorollaColorChanged(colorEspecimen);
                    break;
                case "Flower Calyx":
                    viewHolder.calyxColorText.setText(colorEspecimen.getNombre());
                    setCalyxColor(colorEspecimen.getColorRGB());
                    colorDelCaliz = colorEspecimen;
                    onFlowersInformationChangedListener.onCalyxColorChanged(colorEspecimen);
                    break;
                case "Flower Gineceo":
                    viewHolder.gineceoColorText.setText(colorEspecimen.getNombre());
                    setGineceoColor(colorEspecimen.getColorRGB());
                    colorDelGineceo = colorEspecimen;
                    onFlowersInformationChangedListener.onGineceoColorChanged(colorEspecimen);
                    break;
                case "Flower Stamens":
                    viewHolder.stamensColorText.setText(colorEspecimen.getNombre());
                    setStamensColor(colorEspecimen.getColorRGB());
                    colorDeLosEstambres = colorEspecimen;
                    onFlowersInformationChangedListener.onStamensColorChanged(colorEspecimen);
                    break;
                case "Flower Stigmata":
                    viewHolder.stigmataColorText.setText(colorEspecimen.getNombre());
                    setStigmataColor(colorEspecimen.getColorRGB());
                    colorDeLosEstigmas = colorEspecimen;
                    onFlowersInformationChangedListener.onStigmataColorChanged(colorEspecimen);
                    break;
                case "Flower Pistiliodios":
                    viewHolder.pistiliodioColorText.setText(colorEspecimen.getNombre());
                    setPistiliodioColor(colorEspecimen.getColorRGB());
                    colorDeLosPistiliodios = colorEspecimen;
                    onFlowersInformationChangedListener.onPistiliodioColorChanged(colorEspecimen);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    static class ViewHolder{
        View fragmentView;
        TextView calyxColorText;
        ImageView calyxColor;
        LinearLayout calyx;
        TextView corollaColorText;
        ImageView corollaColor;
        LinearLayout corolla;
        TextView gineceoColorText;
        ImageView stamensColor;
        LinearLayout stamens;
        TextView stamensColorText;
        ImageView pistiliodioColor;
        LinearLayout pistiliodio;
        TextView stigmataColorText;
        ImageView gineceoColor;
        LinearLayout gineceo;
        TextView pistiliodioColorText;
        ImageView stigmataColor;
        LinearLayout stigmata;
    }

    public void setCalyxColor(int calyxColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.calyxColor.getMeasuredWidth(), viewHolder.calyxColor.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(calyxColorRGB);
        viewHolder.calyxColor.setImageBitmap(bitmapPlaceHolder);
    }

    public void setCorollaColor(int corollaColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.corollaColor.getMeasuredWidth(), viewHolder.corollaColor.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(corollaColorRGB);
        viewHolder.corollaColor.setImageBitmap(bitmapPlaceHolder);
    }

    public void setStamensColor(int stamenColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.stamensColor.getMeasuredWidth(), viewHolder.stamensColor.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(stamenColorRGB);
        viewHolder.stamensColor.setImageBitmap(bitmapPlaceHolder);
    }
    public void setPistiliodioColor(int pistiliodioColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.pistiliodioColor.getMeasuredWidth(), viewHolder.pistiliodioColor.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(pistiliodioColorRGB);
        viewHolder.pistiliodioColor.setImageBitmap(bitmapPlaceHolder);
    }
    public void setGineceoColor(int gineceoColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.gineceoColor.getMeasuredWidth(), viewHolder.gineceoColor.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(gineceoColorRGB);
        viewHolder.gineceoColor.setImageBitmap(bitmapPlaceHolder);
    }
    public void setStigmataColor(int stigmataColorRGB) {
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.stigmataColor.getMeasuredWidth(), viewHolder.stigmataColor.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(stigmataColorRGB);
        viewHolder.stigmataColor.setImageBitmap(bitmapPlaceHolder);
    }

}