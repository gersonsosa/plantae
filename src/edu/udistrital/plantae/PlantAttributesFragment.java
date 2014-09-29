package edu.udistrital.plantae;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.NumberPicker;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fenologia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habito;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.FenologiaDao;
import edu.udistrital.plantae.persistencia.HabitoDao;

import java.util.List;

/**
 * Created by hghar on 6/25/14.
 */
public class PlantAttributesFragment extends Fragment {

    private EspecimenDTO especimenDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewHolder viewHolder = (ViewHolder) container.getTag(R.layout.fragment_plant_attributes_fragment);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_plant_attributes_fragment, container, false);
            container.setTag(R.layout.fragment_plant_attributes_fragment, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        especimenDTO = getArguments().getParcelable("especimen");
        AutoCompleteTextView habit = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.habit);
        final DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();
        List<Habito> habitos = daoSession.getHabitoDao()._queryUsuario_Habitos(especimenDTO.getUsuarioId());
        ArrayAdapter<Habito> habitoArrayAdapter = new ArrayAdapter<Habito>(getActivity().getApplicationContext(),android.R.layout.simple_dropdown_item_1line,habitos);
        habit.setAdapter(habitoArrayAdapter);

        NumberPicker height = (NumberPicker) viewHolder.fragmentView.findViewById(R.id.height);
        height.setMinValue(0);
        height.setMaxValue(200);
        height.setWrapSelectorWheel(false);

        NumberPicker DAP = (NumberPicker) viewHolder.fragmentView.findViewById(R.id.DAP);
        DAP.setMinValue(0);
        DAP.setMaxValue(200);
        DAP.setWrapSelectorWheel(false);
        EditText abundance = (EditText) viewHolder.fragmentView.findViewById(R.id.abundance);

        AutoCompleteTextView fenology = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.fenology);
        List<Fenologia> fenologias = daoSession.getFenologiaDao()._queryUsuario_Fenologias(especimenDTO.getUsuarioId());
        ArrayAdapter<Fenologia> fenologiaArrayAdapter = new ArrayAdapter<Fenologia>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, fenologias);
        fenology.setAdapter(fenologiaArrayAdapter);

        EditText localityDescription = (EditText) viewHolder.fragmentView.findViewById(R.id.plant_description);
        habit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        height.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (newVal != 0){
                    especimenDTO.setAlturaDeLaPlanta(newVal);
                }
            }
        });
        DAP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (newVal != 0){
                    especimenDTO.setDap(newVal);
                }
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
        fenology.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        localityDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    especimenDTO.setDescripcionEspecimen(((EditText) v).getText().toString());
                }
            }
        });
        return viewHolder.fragmentView;
    }

    static class ViewHolder{
        View fragmentView;
    }
}