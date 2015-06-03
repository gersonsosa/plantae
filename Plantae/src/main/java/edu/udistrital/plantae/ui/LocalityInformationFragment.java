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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.ubicacion.Region;
import edu.udistrital.plantae.persistencia.DaoSession;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.RegionDao;
import edu.udistrital.plantae.utils.Devices;

/**
 * Created by hghar on 6/25/14.
 */
public class LocalityInformationFragment extends Fragment {

    private EditText localityNameEditText;
    private EditText longitudeEditText;
    private EditText latitudeEditText;
    private EditText maxElevationEditText;
    private EditText minElevationEditText;
    private EditText datumEditText;
    private EditText deviceBrandEditText;
    private EditText descriptionEditText;
    private ProgressBar progressBarCoordinates;
    private AutoCompleteTextView countyAutoCompleteTextView;
    private AutoCompleteTextView stateAutoCompleteTextView;
    private AutoCompleteTextView countryAutoCompleteTextView;
    private LocalityFragmentLoadTask localityFragmentLoadTask;
    private ImageButton updateLocalityButton;
    private ImageButton editLocalityButton;
    private DaoSession daoSession;
    private OnLocationUpdateRequest onLocationUpdateRequest;
    private OnLocalityChangeListener onLocalityChangeListener;
    private boolean isEditable;

    public interface OnLocationUpdateRequest {
        void onLocationUpdateRequested();
    }

    public interface OnLocalityChangeListener {
        void onLocalityNameChanged(String localityName);
        void onCountyChanged(String county);
        void onStateChanged(String state);
        void onCountryChanged( String country);
        void onMinAltitudeChanged(double minAltidude);
        void onMaxAltitudeChanged(double maxAltitude);
        void onLatitudeChanged(double lat);
        void onLongitudeChanged(double lon);
        void onDatumChanged(String datum);
        void onDeviceBrandChanged(String deviceBrand);
        void onLocalityDescriptionChanged(String localityDescription);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onLocationUpdateRequest = (OnLocationUpdateRequest) activity;
            onLocalityChangeListener = (OnLocalityChangeListener) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement LocalityInformationFragment.OnLocationUpdateRequest and LocalityInformationFragment.OnLocalityChangeListener");
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
        Bundle arguments = getArguments();
        Long localidadId = arguments.getLong("localidadId");
        String localidadNombre = arguments.getString("localidadNombre");
        Double latitud = arguments.getDouble("latitud");
        latitud = latitud.equals(Double.valueOf(0.0d)) ? null : latitud;
        Double longitud = arguments.getDouble("longitud");
        longitud = longitud.equals(Double.valueOf(0.0d)) ? null : longitud;
        String datum = arguments.getString("datum");
        Double altitudMinima = arguments.getDouble("altitudMinima");
        altitudMinima = altitudMinima.equals(Double.valueOf(0.0d)) ? null : altitudMinima;
        Double altitudMaxima = arguments.getDouble("altitudMaxima");
        altitudMaxima = altitudMaxima.equals(Double.valueOf(0.0d)) ? null : altitudMaxima;
        String localidadDescripcion = arguments.getString("localidadDescripcion");
        String marcaDispositivo = arguments.getString("marcaDispositivo");
        String municipio = arguments.getString("municipio");
        String departamento = arguments.getString("departamento");
        String pais = arguments.getString("pais");

        if (marcaDispositivo == null || TextUtils.isEmpty(marcaDispositivo)) {
            marcaDispositivo = Devices.getDeviceName();
        }

        localityNameEditText.setText(localidadNombre);
        latitudeEditText.setText(latitud == null ? "":latitud.toString());
        longitudeEditText.setText(longitud  == null ? "":longitud.toString());
        minElevationEditText.setText(altitudMinima == null ? "":altitudMinima.toString());
        maxElevationEditText.setText(altitudMaxima == null ? "":altitudMaxima.toString());
        datumEditText.setText(datum);
        deviceBrandEditText.setText(marcaDispositivo);
        descriptionEditText.setText(localidadDescripcion);
        countyAutoCompleteTextView.setText(municipio);
        stateAutoCompleteTextView.setText(departamento);
        countryAutoCompleteTextView.setText(pais);

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
                    onLocalityChangeListener.onLocalityNameChanged(((EditText) v).getText().toString());
                }
            }
        });

        countyAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // No selecciono ninguna opción de autocompletado
                    String name = ((AutoCompleteTextView) v).getText().toString();
                    if (!TextUtils.isEmpty(name)) {
                        onLocalityChangeListener.onCountyChanged(name);
                    }
                }
            }
        });
        stateAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String name = ((AutoCompleteTextView) v).getText().toString();
                    onLocalityChangeListener.onStateChanged(name);
                }
            }
        });
        countryAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String name = ((AutoCompleteTextView) v).getText().toString();
                    onLocalityChangeListener.onCountryChanged(name);
                }
            }
        });

        minElevationEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        onLocalityChangeListener.onMinAltitudeChanged(Double.parseDouble(value));
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
                        onLocalityChangeListener.onMaxAltitudeChanged(Double.parseDouble(value));
                    }
                }
            }
        });

        longitudeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        onLocalityChangeListener.onLongitudeChanged(Double.parseDouble(value));
                    }
                }
            }
        });

        latitudeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = ((EditText) v).getText().toString();
                    if (!TextUtils.isEmpty(value)) {
                        onLocalityChangeListener.onLatitudeChanged(Double.parseDouble(value));
                    }
                }
            }
        });

        datumEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onLocalityChangeListener.onDatumChanged(((EditText) v).getText().toString());
                }
            }
        });

        deviceBrandEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onLocalityChangeListener.onDeviceBrandChanged(((EditText) v).getText().toString());
                }
            }
        });

        descriptionEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onLocalityChangeListener.onLocalityDescriptionChanged(((EditText) v).getText().toString());
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
        localityFragmentLoadTask.execute(pais, departamento);
        return viewHolder.fragmentView;
    }

    public void updateLocation(Location location) {
        if (minElevationEditText != null && maxElevationEditText != null && latitudeEditText != null && longitudeEditText != null) {
            minElevationEditText.setText(Double.toString(location.getAltitude()));
            onLocalityChangeListener.onMinAltitudeChanged(location.getAltitude());
            location.getAccuracy();
            maxElevationEditText.setText(Double.toString(location.getAltitude()));
            onLocalityChangeListener.onMaxAltitudeChanged(location.getAltitude());
            latitudeEditText.setText(Double.toString(location.getLatitude()));
            onLocalityChangeListener.onLatitudeChanged(location.getLatitude());
            longitudeEditText.setText(Double.toString(location.getLongitude()));
            onLocalityChangeListener.onLongitudeChanged(location.getLongitude());
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

    public void updateRegion(String pais, String departamento, boolean clearCounty) {
        if (clearCounty) {
            countyAutoCompleteTextView.setText("");
        }
        localityFragmentLoadTask = new LocalityFragmentLoadTask();
        localityFragmentLoadTask.execute(pais, departamento);
    }

    private void retrieveViews(ViewHolder viewHolder){
        localityNameEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.locality_name);
        countyAutoCompleteTextView = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.county);
        stateAutoCompleteTextView = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.state);
        countryAutoCompleteTextView = (AutoCompleteTextView) viewHolder.fragmentView.findViewById(R.id.country);
        minElevationEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.min_elevation);
        maxElevationEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.max_elevation);
        datumEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.locality_datum);
        deviceBrandEditText = (EditText) viewHolder.fragmentView.findViewById(R.id.locality_device_brand);
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

    public class LocalityFragmentLoadTask extends AsyncTask<String, Void, Boolean> {

        private ArrayAdapter<Region> countyArrayAdapter;
        private ArrayAdapter<Region> statesArrayAdapter;
        private ArrayAdapter<Region> countrysArrayAdapter;
        private String country;
        private String state;

        @Override
        protected Boolean doInBackground(String...params) {
            if (params != null && params.length > 0) {
                country = params[0];
                state = params[1];
            }
            if (country == null && state == null) {
                List<Region> countys = daoSession.getRegionDao()
                        .queryBuilder()
                        .where(RegionDao.Properties.Rango.eq("municipio"))
                        .list();
                countyArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, countys);
                List<Region> states = daoSession.getRegionDao()
                        .queryBuilder()
                        .where(RegionDao.Properties.Rango.eq("departamento"))
                        .list();
                statesArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, states);
                List<Region> countrys = daoSession.getRegionDao()
                        .queryBuilder()
                        .where(RegionDao.Properties.Rango.eq("pais"))
                        .list();
                countrysArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, countrys);
            }else if (country != null && state == null) {
                List<Region> states = daoSession.getRegionDao()
                        .queryBuilder()
                        .where(RegionDao.Properties.Departamento.eq(country))
                        .where(RegionDao.Properties.Rango.eq("departamento"))
                        .list();
                statesArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, states);
            }else{
                List<Region> countys = daoSession.getRegionDao()
                        .queryBuilder()
                        .where(RegionDao.Properties.Departamento.eq(state))
                        .where(RegionDao.Properties.Rango.eq("municipio"))
                        .list();
                countyArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, countys);
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (countyArrayAdapter != null) {
                countyAutoCompleteTextView.setAdapter(countyArrayAdapter);
            }
            if (statesArrayAdapter != null) {
                stateAutoCompleteTextView.setAdapter(statesArrayAdapter);
            }
            if (countrysArrayAdapter != null) {
                countryAutoCompleteTextView.setAdapter(countrysArrayAdapter);
            }
            if (!countryAutoCompleteTextView.getText().toString().equals(country)) {
                countryAutoCompleteTextView.setText(country);
            }
            if (!stateAutoCompleteTextView.getText().toString().equals(state)) {
                stateAutoCompleteTextView.setText(state);
            }

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
                Log.e("Plantae",
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
                Log.e("Plantae", errorString);
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
                if (result[0] != null) {
                    onLocalityChangeListener.onCountyChanged(result[0]);
                }
                stateAutoCompleteTextView.setText(result[1]);
                if (result[1] != null) {
                    onLocalityChangeListener.onStateChanged(result[1]);
                }
                countryAutoCompleteTextView.setText(result[2]);
                if (result[2] != null) {
                    onLocalityChangeListener.onCountryChanged(result[2]);
                }
            }
        }
    }
}
