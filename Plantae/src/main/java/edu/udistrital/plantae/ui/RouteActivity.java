package edu.udistrital.plantae.ui;

import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.LazyList;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.persistencia.DataBaseHelper;
import edu.udistrital.plantae.persistencia.EspecimenDao;

public class RouteActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Long viajeId;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trayecto);
        viajeId = getIntent().getLongExtra("viaje", 0l);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
            mapFragment.getMapAsync(this);
            mMap = mapFragment.getMap();
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        LoadSpecimensCoordinates loadSpecimensCoordinates = new LoadSpecimensCoordinates();
        loadSpecimensCoordinates.execute(viajeId);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setUpMap();
    }

    private class LoadSpecimensCoordinates extends AsyncTask<Long, Void, Boolean> {

        private List<MarkerOptions> markers;
        private List<PolylineOptions> polylines;
        private CameraUpdate cameraUpdate;

        @Override
        protected Boolean doInBackground(Long... params) {
            Long viajeId = params[0];
            EspecimenDao especimenDao = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession().getEspecimenDao();
            LazyList<Especimen> especimenLazyList = especimenDao.queryBuilder().where(EspecimenDao.Properties.ViajeID.eq(viajeId)).orderDesc(EspecimenDao.Properties.FechaInicial).listLazy();
            if (especimenLazyList.isEmpty()) {
                return false;
            }
            markers = new ArrayList<>(especimenLazyList.size());
            if (especimenLazyList.size() == 1) {
                Especimen especimen = especimenLazyList.get(0);
                LatLng latLng = new LatLng(especimen.getLocalidad().getLatitud(), especimen.getLocalidad().getLongitud());
                markers.add(new MarkerOptions()
                        .title(especimen.getNumeroDeColeccion())
                        .snippet(especimen.getLocalidad().getNombre())
                        .position(latLng));
                cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 13);
                return true;
            }
            LatLng latLngLast = null;
            polylines = new ArrayList<>();
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Especimen especimen:especimenLazyList) {
                LatLng latLng = new LatLng(especimen.getLocalidad().getLatitud(), especimen.getLocalidad().getLongitud());
                markers.add(new MarkerOptions()
                        .title(especimen.getNumeroDeColeccion())
                        .snippet(especimen.getLocalidad().getNombre())
                        .position(latLng));
                if (latLngLast != null) {
                    polylines.add(new PolylineOptions().geodesic(true).add(latLngLast, latLng).color(Color.RED));
                }
                builder.include(latLng);
                latLngLast = latLng;
            }
            cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), screenWidth, screenHeight, 50);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                for (MarkerOptions markerOptions:markers) {
                    mMap.addMarker(markerOptions);
                }
                for (PolylineOptions polylineOptions:polylines) {
                    mMap.addPolyline(polylineOptions);
                }
                mMap.animateCamera(cameraUpdate);
            }
        }
    }
}
