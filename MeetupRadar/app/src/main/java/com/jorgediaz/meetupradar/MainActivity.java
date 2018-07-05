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

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, new FragmentMapa())
                .commit();
        getSupportActionBar().setTitle(getString(R.string.app_name) + ": Mapa");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navview);

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
                            case R.id.menu_radares:
                                fragment = new FragmentRadares();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_actualizar:
                                actualizarRadares();
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

    public void actualizarRadares(){

    }
}
