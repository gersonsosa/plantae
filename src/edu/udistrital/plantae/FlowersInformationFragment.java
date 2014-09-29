package edu.udistrital.plantae;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorMunsell;
import edu.udistrital.plantae.logicadominio.datosespecimen.Flor;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;

/**
 * Created by hghar on 6/25/14.
 */
public class FlowersInformationFragment extends Fragment {

    private EspecimenDTO especimenDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewHolder viewHolder = (ViewHolder) container.getTag(R.layout.fragment_flowers_information);
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
        ImageView calyxColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.calyx_color);
        ImageView corollaColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.corolla_color);
        ImageView stamensColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.stamens_color);
        ImageView pistiliodioColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.pistiliodio_color);
        ImageView gineceoColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.gineceo_color);
        ImageView stigmataColor = (ImageView) viewHolder.fragmentView.findViewById(R.id.stigmata_color);

        final EditText calyxColorText = (EditText) viewHolder.fragmentView.findViewById(R.id.calyx_color_text);
        EditText corollaColorText = (EditText) viewHolder.fragmentView.findViewById(R.id.corolla_color_text);
        EditText stamensColorText = (EditText) viewHolder.fragmentView.findViewById(R.id.stamens_color_text);
        EditText pistiliodioColorText = (EditText) viewHolder.fragmentView.findViewById(R.id.pistiliodio_color_text);
        EditText gineceoColorText = (EditText) viewHolder.fragmentView.findViewById(R.id.gineceo_color_text);
        EditText stigmataColorText = (EditText) viewHolder.fragmentView.findViewById(R.id.stigmata_color_text);

        EditText flowersDescription = (EditText) viewHolder.fragmentView.findViewById(R.id.flowers_description);
        final Flor flor = new Flor();
        calyxColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Capture color
                ColorEspecimen colorEspecimen =  new ColorEspecimen();
                colorEspecimen.setColorMunsell(new ColorMunsell(5, 5, 5));
                String value = calyxColorText.getText().toString();
                if (!TextUtils.isEmpty(value)) {
                    colorEspecimen.setDescripcion(value);
                }
                flor.setColorDelCaliz(colorEspecimen);
                return false;
            }
        });
        corollaColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        stamensColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        pistiliodioColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        gineceoColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        stigmataColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        flowersDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        flor.setDescripcion(value);
                    }
                }
            }
        });
        return viewHolder.fragmentView;
    }

    static class ViewHolder{
        View fragmentView;
    }
}