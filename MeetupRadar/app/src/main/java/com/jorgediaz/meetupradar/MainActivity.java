package com.jorgediaz.meetupradar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.jorgediaz.meetupradar.modelos.Radar;
import com.jorgediaz.meetupradar.modelos.RadarEscuchaCategoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private String userId;
    private Radar radarPersonal;
    private ArrayList<Integer> categoriasSeleccionadas;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navview);

        obtenerRadarPersonal();

        TextView headerEmail = (TextView) navView.getHeaderView(0).findViewById(R.id.headerEmail);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.APPLICATION_ID), Context.MODE_PRIVATE);
        headerEmail.setText(sharedPref.getString("emailUsuario", "Usuario"));

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_mapa:
                                fragment = new FragmentMapa();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_radar_personal:
                                fragment = new FragmentRadarPersonal();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_radares:
                                fragment = new FragmentRadares();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_actualizar:
                                actualizarEventos();
                                break;
                            case R.id.menu_cerrar_sesion:
                                cerrarSesion();
                                break;
                        }

                        if (fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.frame_content, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(getString(R.string.app_name) + ": " + menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });
    }

    public void cargarMapaPrimeraVez() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, new FragmentMapa())
                .commit();
        getSupportActionBar().setTitle(getString(R.string.app_name) + ": Mapa");

        navView.getMenu().findItem(R.id.menu_mapa).setChecked(true);
    }

    public void cerrarSesion() {
        Backendless.UserService.logout(new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("logout", "Error: " + fault.getCode() + ": " + fault.getMessage());
            }
        });
    }

    public void actualizarEventos() {

    }

    private void obtenerRadarPersonal() {
        //userId = UserIdStorageFactory.instance().getStorage().get();
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.APPLICATION_ID), Context.MODE_PRIVATE);
        userId = sharedPref.getString("idUsuario", "0");

        String whereClause = "nombre = 'personal' and ownerId = '" + userId + "'";
        Log.e("obtenerRadarPersonal", whereClause);
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of(Radar.class).find(queryBuilder, new AsyncCallback<List<Radar>>() {
            @Override
            public void handleResponse(List<Radar> response) {
                if (response.size() > 0) {
                    radarPersonal = response.get(0);
                    Log.e("obtenerRadarPersonal", radarPersonal.toString());
                    getIntent().putExtra("radarPersonal", radarPersonal);
                    obtenerCategorias();
                } else {
                    cargarMapaPrimeraVez();
                    Log.e("obtenerRadarPersonal", "null");
                }


            }

            @Override
            public void handleFault(BackendlessFault fault) {
                cargarMapaPrimeraVez();
                Log.e("getRadar", "Error: " + fault.getCode() + ": " + fault.getMessage());
            }
        });
    }

    private void obtenerCategorias() {
        categoriasSeleccionadas = new ArrayList<>();
        String whereClause = "idRadar = '" + radarPersonal.getObjectId() + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of(RadarEscuchaCategoria.class).find(queryBuilder, new AsyncCallback<List<RadarEscuchaCategoria>>() {
            @Override
            public void handleResponse(List<RadarEscuchaCategoria> response) {
                if (response.size() > 0) {
                    Iterator<RadarEscuchaCategoria> iterator = response.iterator();
                    while (iterator.hasNext()) {
                        categoriasSeleccionadas.add(iterator.next().getIdCategoria());
                    }
                }
                getIntent().putIntegerArrayListExtra("categoriasSeleccionadas", categoriasSeleccionadas);
                cargarMapaPrimeraVez();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("obtenerCategorias", "Error: " + fault.getCode() + ": " + fault.getMessage());
            }
        });

    }
}
