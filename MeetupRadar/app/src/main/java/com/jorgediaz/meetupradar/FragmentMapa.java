package com.jorgediaz.meetupradar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.jorgediaz.meetupradar.modelos.Radar;
import com.jorgediaz.meetupradar.rest.Event;
import com.jorgediaz.meetupradar.rest.MeetupService;
import com.jorgediaz.meetupradar.rest.ResultEventos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentMapa extends Fragment implements OnMapReadyCallback {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private GoogleMap mMap;

    // PlaceDetectionClient provides quick access to the device's current place
    private PlaceDetectionClient mPlaceDetectionClient;

    // LocationServices interface is the main entry point for Android location services
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private boolean mLocationPermissionGranted;
    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;
    private CameraPosition mCameraPosition;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;

    // Key values for storing activity state
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    //Valores RadarPersonal
    private Radar radarPersonal;
    private List<Integer> categoriasSeleccionadas;

    //Vista
    private View view;

    public FragmentMapa() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        Intent intent = getActivity().getIntent();
        radarPersonal = intent.getParcelableExtra("radarPersonal");
        categoriasSeleccionadas = intent.getIntegerArrayListExtra("categoriasSeleccionadas");

        view = inflater.inflate(R.layout.fragment_mapa, container, false);

        mPlaceDetectionClient = Places.getPlaceDetectionClient(getActivity());
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e("onMapReady", "onMapReady");
        mMap = googleMap;

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
    }

    private void getLocationPermission() {
        // Checks whether the user has granted fine location permission. If not, it requests it
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            updateLocationUI();
        } else {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        //  Enable the My Location layer and the related control on the map

        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                // Get the current location of the device and set the position of the map.
                getDeviceLocation();
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }

        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    private void getDeviceLocation() {
        // Use the fused location provider to find the device's last-known location,
        // then use that location to position the map
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            getEventos();

                        } else {
                            Log.e("EjemploMapas", "Current location is null. Using defaults.");
                            Log.e("EjemploMapas", "Exception: " + task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Save the state when the activity pauses
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    private void getEventos() {
        if (radarPersonal == null) {
            Log.e("getEventos", "radarPersonal == null");
            Snackbar.make(view, "Debes configurar el Radar Personal para ver los eventos", Snackbar.LENGTH_INDEFINITE).show();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.meetup.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MeetupService service = retrofit.create(MeetupService.class);

            String queryCategorias = android.text.TextUtils.join(",", categoriasSeleccionadas);

            service.getEventos(queryCategorias, mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude(), radarPersonal.getRadio() * 0.621).enqueue(new Callback<ResultEventos>() {
                @Override
                public void onResponse(Call<ResultEventos> call, retrofit2.Response<ResultEventos> response) {

                    if (response.body() != null) {
                        for (Event item : response.body().getResults()) {
                            Log.e("evento", item.toString());
                            if (item.getVenue() != null) {
                                LatLng localizacionEvento = new LatLng(item.getVenue().getLat(), item.getVenue().getLon());
                                mMap.addMarker(new MarkerOptions().position(localizacionEvento).title(item.getName()));
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResultEventos> call, Throwable t) {
                    Log.e("errorMsg", t.toString());
                }
            });
        }
    }
}
