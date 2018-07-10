package com.jorgediaz.meetupradar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.jorgediaz.meetupradar.modelos.Direccion;
import com.jorgediaz.meetupradar.modelos.Evento;
import com.jorgediaz.meetupradar.modelos.Grupo;
import com.squareup.picasso.Picasso;

public class FragmentEvento extends Fragment {

    private Evento evento;
    private View view;

    public FragmentEvento() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_evento, container, false);

        Intent intent = getActivity().getIntent();
        String idMarker = intent.getStringExtra("markerSeleccionado");
        evento = intent.getParcelableExtra(idMarker);

        TextView textEvento = (TextView) view.findViewById(R.id.textNombreEvento);
        textEvento.setText(evento.getNombre());

        ImageView imagen = (ImageView) view.findViewById(R.id.imageEvento);
        if (evento.getFoto() != null && evento.getFoto() != "") {
            Picasso.get().load(evento.getFoto()).into(imagen);
        }

        TextView textURL = (TextView) view.findViewById(R.id.textDirURL);
        textURL.setText(evento.getUrl());

        TextView textNumPersonas = (TextView) view.findViewById(R.id.textNumPersonas);
        textNumPersonas.setText(String.valueOf(evento.getNumPersonasApuntadas()));

        TextView textFecha = (TextView) view.findViewById(R.id.textFecha);
        textFecha.setText(String.valueOf(evento.getFechaComienzo()));

        obtenerGrupo();

        obtenerDireccion();

        return view;
    }

    private void obtenerGrupo() {
        Backendless.Data.of(Grupo.class).findById(evento.getIdGrupo(), new AsyncCallback<Grupo>() {
            @Override
            public void handleResponse(Grupo response) {
                TextView textGrupo = (TextView) view.findViewById(R.id.textGrupo);
                textGrupo.setText(response.getNombre());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("obtenerGrupo", "Error");
            }
        });
    }

    private void obtenerDireccion() {
        Backendless.Data.of(Direccion.class).findById(evento.getIdDireccion(), new AsyncCallback<Direccion>() {
            @Override
            public void handleResponse(Direccion response) {
                TextView textNombreDireccion = (TextView) view.findViewById(R.id.textNombreDireccion);
                textNombreDireccion.setText(response.getNombre());
                TextView textDireccion = (TextView) view.findViewById(R.id.textDireccion);
                textDireccion.setText(response.getCalle() + ", " + response.getCiudad()
                        + " (" + response.getPais() + ")");
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("obtenerDireccion", "Error");

            }
        });
    }
}
