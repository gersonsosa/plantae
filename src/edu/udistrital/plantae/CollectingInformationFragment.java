package edu.udistrital.plantae;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;

/**
 * Created by Gerson Sosa on 6/5/14.
 */
public class CollectingInformationFragment extends Fragment {

    private EspecimenDTO especimenDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewHolder viewHolder = (ViewHolder) container.getTag(R.layout.fragment_collecting_information);
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_collecting_information, container, false);
            container.setTag(R.layout.fragment_collecting_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        especimenDTO = getArguments().getParcelable("especimen");
        TextView collectionDate = (TextView) viewHolder.fragmentView.findViewById(R.id.collection_date);
        collectionDate.setText(especimenDTO.getFechaInicial().toString());
        EditText collectorNumber = (EditText) viewHolder.fragmentView.findViewById(R.id.collector_number);
        collectorNumber.setText(especimenDTO.getNumeroDeColeccion());
        EditText collectionMethod = (EditText) viewHolder.fragmentView.findViewById(R.id.collection_method);
        collectionMethod.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    especimenDTO.setMetodoColeccion(((EditText)v).getText().toString());
                }
            }
        });
        EditText collectionStation = (EditText) viewHolder.fragmentView.findViewById(R.id.collection_station);
        collectionStation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    especimenDTO.setEstacionDelAño(((EditText)v).getText().toString());
                }
            }
        });
        return viewHolder.fragmentView;
    }

    static class ViewHolder{
        View fragmentView;
    }
}