package com.jorgediaz.meetupradar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserTokenStorageFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private String email;
    private EditText editPassword;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Backendless.setUrl(getString(R.string.SERVER_URL));
        Backendless.initApp(getApplicationContext(), getString(R.string.APPLICATION_ID), getString(R.string.API_KEY));

        entrarAutomaticamente();
    }

    private void entrarAutomaticamente() {
        final String userToken = UserTokenStorageFactory.instance().getStorage().get();
        if (userToken != null && !userToken.equals("")) {
            AsyncCallback<Boolean> isValidLoginCallBack = new AsyncCallback<Boolean>() {
                @Override
                public void handleResponse(Boolean response) {
                    mostrarMainActivity();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("entrarAuto", "Error: " + fault.getCode() + ": " + fault.getMessage());
                }
            };
            Backendless.UserService.isValidLogin(isValidLoginCallBack);
        } else {
            getSupportActionBar().setTitle(getString(R.string.app_name) + ": Login");
            setContentView(R.layout.activity_login);
            Log.e("entrarAuto", "El usertoken no está guardado");
        }
    }

    public void mostrarMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void registrarse(View v) {
        if (comprobarFormularioLogin()) {
            BackendlessUser user = new BackendlessUser();
            user.setProperty("email", email);
            user.setProperty("password", password);
            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response) {
                    Snackbar.make(findViewById(R.id.layoutLogin), "Usuario creado correctamente", Snackbar.LENGTH_LONG).show();

                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Snackbar.make(findViewById(R.id.layoutLogin), "No se ha podido crear el usuario", Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    public boolean comprobarFormularioLogin() {
        boolean correcto = false;
        editEmail = (EditText) findViewById(R.id.editEmail);
        email = editEmail.getText().toString();
        editPassword = (EditText) findViewById(R.id.editPassword);
        password = editPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            editEmail.setError("Debes introducir un correo electrónico");
        } else if (TextUtils.isEmpty(password)) {
            editPassword.setError("Debes introducir una password");
        } else {
            correcto = true;
        }
        return correcto;
    }

    public void entrar(View v) {
        if (comprobarFormularioLogin()) {
            Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response) {
                    SharedPreferences sharedPref = getSharedPreferences(
                            getString(R.string.APPLICATION_ID), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("emailUsuario", response.getEmail());
                    editor.commit();

                    mostrarMainActivity();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Snackbar.make(findViewById(R.id.layoutLogin), "No se ha podido iniciar sesión", Snackbar.LENGTH_LONG).show();
                }
            }, ((CheckBox) findViewById(R.id.checkBoxEntrarAuto)).isChecked());
        }
    }
}
