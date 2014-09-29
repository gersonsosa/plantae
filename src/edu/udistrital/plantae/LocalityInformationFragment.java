package edu.udistrital.plantae;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.RegionDTO;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.RegionDao;

import java.util.List;

/**
 * Created by hghar on 6/25/14.
 */
public class LocalityInformationFragment extends Fragment {
    private EspecimenDTO especimenDTO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewHolder viewHolder = (ViewHolder) container.getTag(R.layout.fragment_locality_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_locality_information, container, false);
            container.setTag(R.layout.fragment_locality_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        especimenDTO = getArguments().getParcelable("especimen");
        final DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();
        EditText localityName = (EditText) viewHolder.fragmentView.findViewById(R.id.locality_name);
        localityName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    especimenDTO.setLocalidadNombre(((EditText)v).getText().toString());
                }
            }
        });
        final AutoCompleteTextView county = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.county);
        final AutoCompleteTextView state = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.state);
        final AutoCompleteTextView country = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.country);
        final List<Region> countys = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("municipio")).list();
        ArrayAdapter<Region> regionArrayAdapter = new ArrayAdapter<Region>(getActivity(),android.R.layout.simple_dropdown_item_1line,countys);
        List<Region> states = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("departamento")).list();
        ArrayAdapter<Region> statesArrayAdapter = new ArrayAdapter<Region>(getActivity(),android.R.layout.simple_dropdown_item_1line,states);
        List<Region> countrys = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("pais")).list();
        ArrayAdapter<Region> countrysArrayAdapter = new ArrayAdapter<Region>(getActivity(),android.R.layout.simple_dropdown_item_1line,countrys);
        county.setAdapter(regionArrayAdapter);
        state.setAdapter(statesArrayAdapter);
        country.setAdapter(countrysArrayAdapter);
        county.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Region itemAtPosition = (Region) parent.getItemAtPosition(position);
                if (itemAtPosition != null) {
                    RegionDTO regionDTO = new RegionDTO(itemAtPosition);
                    state.setText(itemAtPosition.getRegionPadre().getNombre());
                    country.setText(itemAtPosition.getRegionPadre().getRegionPadre().getNombre());
                    especimenDTO.setRegion(regionDTO);
                    state.requestFocus();
                }
            }
        });
        county.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    RegionDTO regionDTO = null;
                    // No selecciono ninguna opción de autocompletado
                    String name = ((AutoCompleteTextView)v).getText().toString();
                    if (!TextUtils.isEmpty(name)) {
                        if (especimenDTO.getRegion() != null) {
                            regionDTO = especimenDTO.getRegion();
                        } else {
                            regionDTO = new RegionDTO();
                        }
                        Region municipio = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Municipio.eq(name)).unique();
                        if (municipio != null) {
                            state.setText(municipio.getRegionPadre().getNombre());
                            country.setText(municipio.getRegionPadre().getRegionPadre().getNombre());
                            regionDTO.setId(municipio.getId());
                            regionDTO.setDepartamento(municipio.getRegionPadre().getNombre());
                            regionDTO.setPais(municipio.getRegionPadre().getRegionPadre().getNombre());
                        }
                        regionDTO.setMunicipio(name);
                    }
                    especimenDTO.setRegion(regionDTO);
                }
            }
        });
        state.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    RegionDTO regionDTO = null;
                    String name = ((AutoCompleteTextView)v).getText().toString();
                    if (!TextUtils.isEmpty(name)) {
                        if (especimenDTO.getRegion() != null) {
                            regionDTO = especimenDTO.getRegion();
                        } else {
                            regionDTO = new RegionDTO();
                        }

                        Region departamento = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("departamento")).where(RegionDao.Properties.Departamento.eq(name)).unique();
                        if (departamento != null) {
                            List<Region> countys = daoSession.getRegionDao()
                                    .queryBuilder()
                                    .where(RegionDao.Properties.Departamento.eq(departamento.getNombre()))
                                    .where(RegionDao.Properties.Rango.eq("municipio")).list();
                            county.setAdapter(new ArrayAdapter<Region>(getActivity().getApplicationContext(),android.R.layout.simple_dropdown_item_1line,countys));
                            country.setText(departamento.getRegionPadre().getNombre());
                            regionDTO.setPais(departamento.getRegionPadre().getNombre());
                            regionDTO.setId(departamento.getId());
                        }
                        regionDTO.setDepartamento(name);
                        especimenDTO.setRegion(regionDTO);
                    }
                }
            }
        });
        country.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    RegionDTO regionDTO = null;
                    String name = ((AutoCompleteTextView)v).getText().toString();
                    if (!TextUtils.isEmpty(name)) {
                        if (especimenDTO.getRegion() != null) {
                            regionDTO = especimenDTO.getRegion();
                        } else {
                            regionDTO = new RegionDTO();
                        }

                        Region pais = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("pais")).where(RegionDao.Properties.Pais.eq(name)).unique();
                        if (pais != null) {
                            List<Region> states = daoSession.getRegionDao()
                                    .queryBuilder()
                                    .where(RegionDao.Properties.Departamento.eq(pais.getNombre()))
                                    .where(RegionDao.Properties.Rango.eq("departamento")).list();
                            state.setAdapter(new ArrayAdapter<Region>(getActivity().getApplicationContext(),android.R.layout.simple_dropdown_item_1line,states));
                            regionDTO.setId(pais.getId());
                        }
                        regionDTO.setPais(name);
                        especimenDTO.setRegion(regionDTO);
                    }
                }
            }
        });
        EditText min_elevation = (EditText) viewHolder.fragmentView.findViewById(R.id.min_elevation);
        min_elevation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String value = ((EditText)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        especimenDTO.setAltitudMinima(Double.parseDouble(value));
                    }
                }
            }
        });
        EditText max_elevation = (EditText) viewHolder.fragmentView.findViewById(R.id.max_elevation);
        max_elevation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String value = ((EditText)v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        especimenDTO.setAltitudMaxima(Double.parseDouble(value));
                    }
                }
            }
        });
        EditText description = (EditText) viewHolder.fragmentView.findViewById(R.id.locality_description);
        description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    especimenDTO.setLocalidadDescripcion(((EditText)v).getText().toString());
                }
            }
        });
        return viewHolder.fragmentView;
    }

    static class ViewHolder{
        View fragmentView;
    }
}
