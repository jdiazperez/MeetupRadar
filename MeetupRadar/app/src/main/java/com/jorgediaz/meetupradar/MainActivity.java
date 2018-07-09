package com.jorgediaz.meetupradar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import com.jorgediaz.meetupradar.modelos.Direccion;
import com.jorgediaz.meetupradar.modelos.Evento;
import com.jorgediaz.meetupradar.modelos.Grupo;
import com.jorgediaz.meetupradar.modelos.Radar;
import com.jorgediaz.meetupradar.modelos.RadarEscuchaCategoria;
import com.jorgediaz.meetupradar.rest.Event;
import com.jorgediaz.meetupradar.rest.MeetupService;
import com.jorgediaz.meetupradar.rest.ResultEventos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        String whereClause = "idRadar = '" + radarPersonal.getObjectId() + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of(RadarEscuchaCategoria.class).find(queryBuilder, new AsyncCallback<List<RadarEscuchaCategoria>>() {
            @Override
            public void handleResponse(List<RadarEscuchaCategoria> response) {
                if (response.size() > 0) {
                    categoriasSeleccionadas = new ArrayList<>();
                    Iterator<RadarEscuchaCategoria> iterator = response.iterator();
                    while (iterator.hasNext()) {
                        categoriasSeleccionadas.add(iterator.next().getIdCategoria());
                    }
                    getIntent().putIntegerArrayListExtra("categoriasSeleccionadas", categoriasSeleccionadas);
                }
                cargarMapaPrimeraVez();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("obtenerCategorias", "Error: " + fault.getCode() + ": " + fault.getMessage());
                cargarMapaPrimeraVez();
            }
        });

    }

    public void actualizarEventos() {
        radarPersonal = getIntent().getParcelableExtra("radarPersonal");
        categoriasSeleccionadas = getIntent().getIntegerArrayListExtra("categoriasSeleccionadas");
        getEventos();
    }

    private void getEventos() {
        if (radarPersonal == null) {
            Snackbar.make(drawerLayout, "Debes configurar la distancia de búsqueda del Radar Personal",
                    Snackbar.LENGTH_SHORT).show();
        } else if (categoriasSeleccionadas == null || categoriasSeleccionadas.size() == 0) {
            Snackbar.make(drawerLayout, "Debes configurar las categorías del Radar Personal",
                    Snackbar.LENGTH_SHORT).show();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.meetup.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MeetupService service = retrofit.create(MeetupService.class);

            String queryCategorias = android.text.TextUtils.join(",", categoriasSeleccionadas);

            service.getEventos(queryCategorias, radarPersonal.getLatidud(), radarPersonal.getLongitud(), radarPersonal.getRadio() * 0.621).enqueue(new Callback<ResultEventos>() {
                @Override
                public void onResponse(Call<ResultEventos> call, retrofit2.Response<ResultEventos> response) {

                    if (response.body() != null) {
                        for (Event item : response.body().getResults()) {
                            Log.e("evento", item.toString());
                            if (item.getVenue() != null) {
                                Direccion dir = new Direccion(item.getVenue());
                                Grupo grupo = new Grupo(item.getGroup());
                                Evento evento = new Evento(item);
                                guardarDireccionEnBD(dir, grupo, evento);
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

    private void guardarDireccionEnBD(final Direccion direccion, final Grupo grupo, final Evento evento) {
        Backendless.Persistence.save(direccion, new AsyncCallback<Direccion>() {
            @Override
            public void handleResponse(Direccion response) {
                Log.e("guardarDirección", response.getNombre());
                evento.setIdDireccion(response.getObjectId());
                guardarGrupoEnBD(grupo, evento);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("guardarEvento", "Error: " + fault.getMessage()
                        + " Direccion: " + direccion.getNombre()
                        + " Grupo: " + grupo.getNombre()
                        + " Evento: " + evento.getNombre());

            }
        });
    }

    private void guardarGrupoEnBD(final Grupo grupo, final Evento evento) {
        Backendless.Persistence.save(grupo, new AsyncCallback<Grupo>() {
            @Override
            public void handleResponse(Grupo response) {
                Log.e("guardarGrupo", response.getNombre());
                evento.setIdGrupo(response.getObjectId());
                guardarEventoEnBD(evento);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("guardarGrupo", "Error: " + fault.getMessage()
                        + " Grupo: " + grupo.getNombre()
                        + " Evento: " + evento.getNombre());
            }
        });
    }

    private void guardarEventoEnBD(final Evento evento) {
        Backendless.Persistence.save(evento, new AsyncCallback<Evento>() {
            @Override
            public void handleResponse(Evento response) {
                Log.e("guardarEvento", response.getNombre());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("guardarEvento", "Error: " + fault.getMessage()
                        + " Evento: " + evento.getNombre());
            }
        });
    }

}
