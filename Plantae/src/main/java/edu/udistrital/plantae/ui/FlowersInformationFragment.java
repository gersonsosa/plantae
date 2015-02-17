package edu.udistrital.plantae.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.objetotransferenciadatos.ColorEspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;

/**
 * Created by hghar on 6/25/14.
 */
public class FlowersInformationFragment extends Fragment {

    private EspecimenDTO especimenDTO;
    private ViewHolder viewHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHolder = (ViewHolder) container.getTag(R.layout.fragment_flowers_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_flowers_information, container, false);
            container.setTag(R.layout.fragment_flowers_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        especimenDTO = getArguments().getParcelable("especimen");
        viewHolder.calyxColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.calyx_color);
        viewHolder.corollaColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.corolla_color);
        viewHolder.stamensColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.stamens_color);
        viewHolder.pistiliodioColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.pistiliodio_color);
        viewHolder.gineceoColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.gineceo_color);
        viewHolder.stigmataColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.stigmata_color);

        final TextView calyxColorText = (TextView) viewHolder.fragmentView.findViewById(R.id.calyx_color_text);
        final TextView corollaColorText = (TextView) viewHolder.fragmentView.findViewById(R.id.corolla_color_text);
        final TextView gineceoColorText = (TextView) viewHolder.fragmentView.findViewById(R.id.gineceo_color_text);
        final TextView stamensColorText = (TextView) viewHolder.fragmentView.findViewById(R.id.stamens_color_text);
        final TextView stigmataColorText = (TextView) viewHolder.fragmentView.findViewById(R.id.stigmata_color_text);
        final TextView pistiliodioColorText = (TextView) viewHolder.fragmentView.findViewById(R.id.pistiliodio_color_text);

        EditText flowersDescription = (EditText) viewHolder.fragmentView.findViewById(R.id.flowers_description);
        if (especimenDTO.getFlorId() != null){
            // Load flower information
            if (especimenDTO.getColorDeLaCorola() != null) {
                corollaColorText.setText(especimenDTO.getColorDeLaCorola().getNombre());
                setCorollaColor(especimenDTO.getColorDeLaCorola());
            }
            if (especimenDTO.getColorDelCaliz() != null) {
                calyxColorText.setText(especimenDTO.getColorDelCaliz().getNombre());
                setCalyxColor(especimenDTO.getColorDelCaliz());
            }
            if (especimenDTO.getColorDelGineceo() != null) {
                gineceoColorText.setText(especimenDTO.getColorDelGineceo().getNombre());
                setGineceoColor(especimenDTO.getColorDelGineceo());
            }
            if (especimenDTO.getColorDeLosEstambres() != null) {
                stamensColorText.setText(especimenDTO.getColorDeLosEstambres().getNombre());
                setStamensColor(especimenDTO.getColorDeLosEstambres());
            }
            if (especimenDTO.getColorDeLosEstigmas() != null) {
                stigmataColorText.setText(especimenDTO.getColorDeLosEstigmas().getNombre());
                setStigmataColor(especimenDTO.getColorDeLosEstigmas());
            }
            if (especimenDTO.getColorDeLosPistiliodios() != null) {
                pistiliodioColorText.setText(especimenDTO.getColorDeLosPistiliodios().getNombre());
                setPistiliodioColor(especimenDTO.getColorDeLosPistiliodios());
            }
        }

        flowersDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        especimenDTO.setFlorDescripcion(value);
                    }
                }
            }
        });
        return viewHolder.fragmentView;
    }

    static class ViewHolder{
        View fragmentView;
        ImageView calyxColor;
        ImageView corollaColor;
        ImageView stamensColor;
        ImageView pistiliodioColor;
        ImageView gineceoColor;
        ImageView stigmataColor;
    }

    public void setCalyxColor(ColorEspecimenDTO calyxColor) {
        especimenDTO.setColorDelCaliz(calyxColor);
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.calyxColor.getWidth(), viewHolder.calyxColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(calyxColor.getColorRGB());
        viewHolder.calyxColor.setImageBitmap(bitmapPlaceHolder);
    }

    public void setCorollaColor(ColorEspecimenDTO corollaColor) {
        especimenDTO.setColorDeLaCorola(corollaColor);
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.corollaColor.getWidth(), viewHolder.corollaColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(corollaColor.getColorRGB());
        viewHolder.corollaColor.setImageBitmap(bitmapPlaceHolder);
    }

    public void setStamensColor(ColorEspecimenDTO stamenColor) {
        especimenDTO.setColorDeLosEstambres(stamenColor);
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.stamensColor.getWidth(), viewHolder.stamensColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(stamenColor.getColorRGB());
        viewHolder.stamensColor.setImageBitmap(bitmapPlaceHolder);
    }
    public void setPistiliodioColor(ColorEspecimenDTO pistiliodioColor) {
        especimenDTO.setColorDeLosPistiliodios(pistiliodioColor);
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.pistiliodioColor.getWidth(), viewHolder.pistiliodioColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(pistiliodioColor.getColorRGB());
        viewHolder.pistiliodioColor.setImageBitmap(bitmapPlaceHolder);
    }
    public void setGineceoColor(ColorEspecimenDTO gineceoColor) {
        especimenDTO.setColorDelGineceo(gineceoColor);
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.gineceoColor.getWidth(), viewHolder.gineceoColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(gineceoColor.getColorRGB());
        viewHolder.gineceoColor.setImageBitmap(bitmapPlaceHolder);
    }
    public void setStigmataColor(ColorEspecimenDTO stigmataColor) {
        especimenDTO.setColorDeLosEstigmas(stigmataColor);
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(viewHolder.stigmataColor.getWidth(), viewHolder.stigmataColor.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(stigmataColor.getColorRGB());
        viewHolder.stigmataColor.setImageBitmap(bitmapPlaceHolder);
    }

}