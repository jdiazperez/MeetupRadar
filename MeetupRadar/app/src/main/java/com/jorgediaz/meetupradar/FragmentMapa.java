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

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.jorgediaz.meetupradar.modelos.Categorias;
import com.jorgediaz.meetupradar.modelos.Evento;
import com.jorgediaz.meetupradar.modelos.Radar;

import java.util.Iterator;
import java.util.List;


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

        agregarWindowAdapter();
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

                            if (radarPersonal != null) {
                                radarPersonal.setLatidud(mLastKnownLocation.getLatitude());
                                radarPersonal.setLongitud(mLastKnownLocation.getLongitude());
                                getActivity().getIntent().putExtra("radarPersonal", radarPersonal);
                            }

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            obtenerEventosDeLaBD();

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

    private void obtenerEventosDeLaBD() {
        if (radarPersonal == null) {
            Snackbar.make(view, "Debes configurar la distancia de búsqueda del Radar Personal",
                    Snackbar.LENGTH_SHORT).show();
        } else if (categoriasSeleccionadas == null || categoriasSeleccionadas.size() == 0) {
            Snackbar.make(view, "Debes configurar las categorías del Radar Personal",
                    Snackbar.LENGTH_SHORT).show();
        } else {
            Iterator<Integer> iterator = categoriasSeleccionadas.iterator();
            String whereClause = "";
            while (iterator.hasNext()) {
                whereClause += "idCategoria = " + iterator.next() + " or ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 4);
            DataQueryBuilder queryBuilder = DataQueryBuilder.create();
            queryBuilder.setWhereClause(whereClause);

            Backendless.Data.of(Evento.class).find(queryBuilder, new AsyncCallback<List<Evento>>() {
                @Override
                public void handleResponse(List<Evento> response) {
                    float[] distanciaEntreEventoYRadar = new float[1];
                    Iterator<Evento> iterator = response.iterator();
                    while (iterator.hasNext()) {
                        Evento evento = iterator.next();
                        Location.distanceBetween(radarPersonal.getLatidud(), radarPersonal.getLongitud(),
                                evento.getLatitud(), evento.getLongitud(), distanciaEntreEventoYRadar);

                        if (distanciaEntreEventoYRadar[0] < radarPersonal.getRadio() * 1000) {
                            LatLng localizacionEvento = new LatLng(evento.getLatitud(), evento.getLongitud());
                            mMap.addMarker(new MarkerOptions()
                                    .position(localizacionEvento)
                                    .title(evento.getNombre())
                                    .snippet("Categoría: " + Categorias.getCategoriaPorId(evento.getIdCategoria()).getNombre())
                                    .icon(BitmapDescriptorFactory.defaultMarker(Categorias.getColorIconoMapa(evento.getIdCategoria()))));
                        }
                    }
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("obtenerEventosDeLaBD", "Error: " + fault.getMessage());
                }
            });

        }
    }

    private void agregarWindowAdapter() {
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.e("idMarker", marker.getId());
            }
        });
    }
}
