package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.objetotransferenciadatos.RegionDTO;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.RegionDao;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by hghar on 6/25/14.
 */
public class LocalityInformationFragment extends Fragment {
    private EspecimenDTO especimenDTO;
    private EditText descriptionEditText;
    private EditText maxElevationEditText;
    private EditText minElevationEditText;
    private EditText localityNameEditText;
    private EditText longitudeEditText;
    private EditText latitudeEditText;
    private ProgressBar progressBarCoordinates;
    private AutoCompleteTextView countyAutoCompleteTextView;
    private AutoCompleteTextView stateAutoCompleteTextView;
    private AutoCompleteTextView countryAutoCompleteTextView;
    private LocalityFragmentLoadTask localityFragmentLoadTask;
    private ImageButton updateLocalityButton;
    private ImageButton editLocalityButton;
    private DaoSession daoSession;
    private OnLocationUpdateRequest onLocationUpdateRequest;
    private boolean isEditable;

    public interface OnLocationUpdateRequest {
        public void onLocationUpdateRequested();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onLocationUpdateRequest = (OnLocationUpdateRequest) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement LocalityInformationFragment.OnLocationUpdateRequest");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewHolder viewHolder = (ViewHolder) container.getTag(R.layout.fragment_locality_information);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.fragmentView = inflater.inflate(R.layout.fragment_locality_information, container, false);
            retrieveViews(viewHolder);
            container.setTag(R.layout.fragment_locality_information, viewHolder);
        }else{
            if (viewHolder.fragmentView.getParent() != null) {
                ((ViewGroup) viewHolder.fragmentView.getParent()).removeView(viewHolder.fragmentView);
            }
        }
        especimenDTO = getArguments().getParcelable("especimen");
        daoSession = DataBaseHelper.getDataBaseHelper(getActivity().getApplicationContext()).getDaoSession();

        maxElevationEditText.setFocusableInTouchMode(isEditable);
        maxElevationEditText.setFocusable(isEditable);
        minElevationEditText.setFocusableInTouchMode(isEditable);
        minElevationEditText.setFocusable(isEditable);
        longitudeEditText.setFocusableInTouchMode(isEditable);
        longitudeEditText.setFocusable(isEditable);
        latitudeEditText.setFocusableInTouchMode(isEditable);
        latitudeEditText.setFocusable(isEditable);
        countyAutoCompleteTextView.setFocusableInTouchMode(isEditable);
        countyAutoCompleteTextView.setFocusable(isEditable);
        stateAutoCompleteTextView.setFocusableInTouchMode(isEditable);
        stateAutoCompleteTextView.setFocusable(isEditable);
        countryAutoCompleteTextView.setFocusableInTouchMode(isEditable);
        countryAutoCompleteTextView.setFocusable(isEditable);

        localityNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    especimenDTO.setLocalidadNombre(((EditText) v).getText().toString());
                }
            }
        });
        countyAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Region itemAtPosition = (Region) parent.getItemAtPosition(position);
                if (itemAtPosition != null) {
                    RegionDTO regionDTO = new RegionDTO(itemAtPosition);
                    stateAutoCompleteTextView.setText(itemAtPosition.getRegionPadre().getNombre());
                    countryAutoCompleteTextView.setText(itemAtPosition.getRegionPadre().getRegionPadre().getNombre());
                    especimenDTO.setRegion(regionDTO);
                    stateAutoCompleteTextView.requestFocus();
                }
            }
        });

        countyAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    RegionDTO regionDTO = null;
                    // No selecciono ninguna opción de autocompletado
                    String name = ((AutoCompleteTextView) v).getText().toString();
                    if (!TextUtils.isEmpty(name)) {
                        if (especimenDTO.getRegion() != null) {
                            regionDTO = especimenDTO.getRegion();
                        } else {
                            regionDTO = new RegionDTO();
                        }
                        Region municipio = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Municipio.eq(name)).unique();
                        if (municipio != null) {
                            stateAutoCompleteTextView.setText(municipio.getRegionPadre().getNombre());
                            countryAutoCompleteTextView.setText(municipio.getRegionPadre().getRegionPadre().getNombre());
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
        stateAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    RegionDTO regionDTO;
                    String name = ((AutoCompleteTextView) v).getText().toString();
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
                            countyAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, countys));
                            countryAutoCompleteTextView.setText(departamento.getRegionPadre().getNombre());
                            regionDTO.setPais(departamento.getRegionPadre().getNombre());
                            regionDTO.setId(departamento.getId());
                        }
                        regionDTO.setDepartamento(name);
                        especimenDTO.setRegion(regionDTO);
                    }
                }
            }
        });
        countryAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    RegionDTO regionDTO;
                    String name = ((AutoCompleteTextView) v).getText().toString();
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
                            stateAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, states));
                            regionDTO.setId(pais.getId());
                        }
                        regionDTO.setPais(name);
                        especimenDTO.setRegion(regionDTO);
                    }
                }
            }
        });

        minElevationEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        especimenDTO.setAltitudMinima(Double.parseDouble(value));
                    }
                }
            }
        });
        maxElevationEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        especimenDTO.setAltitudMaxima(Double.parseDouble(value));
                    }
                }
            }
        });
        descriptionEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    especimenDTO.setLocalidadDescripcion(((EditText) v).getText().toString());
                }
            }
        });
        updateLocalityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLocationUpdateRequest.onLocationUpdateRequested();
            }
        });
        editLocalityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEditable = !isEditable;
                maxElevationEditText.setFocusableInTouchMode(isEditable);
                maxElevationEditText.setFocusable(isEditable);
                minElevationEditText.setFocusableInTouchMode(isEditable);
                minElevationEditText.setFocusable(isEditable);
                longitudeEditText.setFocusableInTouchMode(isEditable);
                longitudeEditText.setFocusable(isEditable);
                latitudeEditText.setFocusableInTouchMode(isEditable);
                latitudeEditText.setFocusable(isEditable);
                countyAutoCompleteTextView.setFocusableInTouchMode(isEditable);
                countyAutoCompleteTextView.setFocusable(isEditable);
                stateAutoCompleteTextView.setFocusableInTouchMode(isEditable);
                stateAutoCompleteTextView.setFocusable(isEditable);
                countryAutoCompleteTextView.setFocusableInTouchMode(isEditable);
                countryAutoCompleteTextView.setFocusable(isEditable);
            }
        });
        localityFragmentLoadTask = new LocalityFragmentLoadTask();
        localityFragmentLoadTask.execute();
        return viewHolder.fragmentView;
    }

    public void updateLocation(Location location) {
        if (minElevationEditText != null && maxElevationEditText != null && latitudeEditText != null && longitudeEditText != null) {
            minElevationEditText.setText(Double.toString(location.getAltitude()));
            location.getAccuracy();
            especimenDTO.setAltitudMinima(location.getAltitude());
            maxElevationEditText.setText(Double.toString(location.getAltitude()));
            especimenDTO.setAltitudMaxima(location.getAltitude());
            latitudeEditText.setText(Double.toString(location.getLatitude()));
            especimenDTO.setLatitud(location.getLatitude());
            longitudeEditText.setText(Double.toString(location.getLongitude()));
            especimenDTO.setLongitud(location.getLongitude());
            // Ensure that a Geocoder services is available
            if (Build.VERSION.SDK_INT >=
                    Build.VERSION_CODES.GINGERBREAD
                    &&
                    Geocoder.isPresent()) {
                // Show the activity indicator
                progressBarCoordinates.setVisibility(View.VISIBLE);
            /*
             * Reverse geocoding is long-running and synchronous.
             * Run it on a background thread.
             * Pass the current location to the background task.
             * When the task finishes,
             * onPostExecute() displays the address.
             */
                (new DetermineLocationTask()).execute(location);
            }
        }
    }

    private void setAdapters(ArrayAdapter<Region> countyArrayAdapter, ArrayAdapter<Region> statesArrayAdapter, ArrayAdapter<Region> countrysArrayAdapter) {
        countyAutoCompleteTextView.setAdapter(countyArrayAdapter);
        stateAutoCompleteTextView.setAdapter(statesArrayAdapter);
        countryAutoCompleteTextView.setAdapter(countrysArrayAdapter);
    }

    private void retrieveViews(ViewHolder viewHolder){
        localityNameEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.locality_name);
        countyAutoCompleteTextView = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.county);
        stateAutoCompleteTextView = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.state);
        countryAutoCompleteTextView = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.country);
        minElevationEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.min_elevation);
        maxElevationEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.max_elevation);
        descriptionEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.locality_description);
        latitudeEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.latitude);
        longitudeEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.longitude);
        progressBarCoordinates = (ProgressBar) viewHolder.fragmentView.findViewById(R.id.progress_bar_coordinates);
        updateLocalityButton = (ImageButton) viewHolder.fragmentView.findViewById(R.id.update_locality_button);
        editLocalityButton = (ImageButton) viewHolder.fragmentView.findViewById(R.id.edit_locality_button);
    }

    static class ViewHolder{
        View fragmentView;
    }

    public class LocalityFragmentLoadTask extends AsyncTask<Void, Void, Boolean> {

        private ArrayAdapter<Region> countyArrayAdapter;
        private ArrayAdapter<Region> statesArrayAdapter;
        private ArrayAdapter<Region> countrysArrayAdapter;

        @Override
        protected Boolean doInBackground(Void...params) {
            List<Region> countys = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("municipio")).list();
            countyArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,countys);
            List<Region> states = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("departamento")).list();
            statesArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,states);
            List<Region> countrys = daoSession.getRegionDao().queryBuilder().where(RegionDao.Properties.Rango.eq("pais")).list();
            countrysArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,countrys);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            setAdapters(countyArrayAdapter, statesArrayAdapter, countrysArrayAdapter);
        }

        @Override
        protected void onCancelled() {
            localityFragmentLoadTask = null;
        }
    }

    public class DetermineLocationTask extends AsyncTask<Location, Void, String[]> {

        @Override
        protected String[] doInBackground(Location...params) {
            Geocoder geocoder =
                    new Geocoder(getActivity(), Locale.getDefault());
            // Get the current location from the input parameter list
            Location loc = params[0];
            // Create a list to contain the result address
            List<Address> addresses;
            try {
                /*
                 * Return 1 address.
                 */
                addresses = geocoder.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
            } catch (IOException e1) {
                Log.e("LocalityInformationFragment",
                        "IO Exception in getFromLocation()");
                e1.printStackTrace();
                return null;
            } catch (IllegalArgumentException e2) {
                // Error message to post in the log
                String errorString = "Illegal arguments " +
                        Double.toString(loc.getLatitude()) +
                        " , " +
                        Double.toString(loc.getLongitude()) +
                        " passed to address service";
                Log.e("LocalityInformationFragment", errorString);
                e2.printStackTrace();
                return null;
            }
            // If the reverse geocode returned an address
            if (addresses != null && addresses.size() > 0) {
                // Get the first address
                Address address = addresses.get(0);
                String[] result = new String[3];
                result[0] = address.getLocality();
                result[1] = address.getAdminArea();
                result[2] = address.getCountryName();
                // Return the text
                return result;
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final String[] result) {
            progressBarCoordinates.setVisibility(View.INVISIBLE);
            if (result != null) {
                countyAutoCompleteTextView.setText(result[0]);
                stateAutoCompleteTextView.setText(result[1]);
                countryAutoCompleteTextView.setText(result[2]);
                if (especimenDTO != null) {
                    RegionDTO regionDTO = new RegionDTO();
                    regionDTO.setMunicipio(result[0]);
                    regionDTO.setDepartamento(result[1]);
                    regionDTO.setPais(result[2]);
                    especimenDTO.setRegion(regionDTO);
                }
            }
        }
    }
}
