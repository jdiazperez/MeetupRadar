package com.jorgediaz.meetupradar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorgediaz.meetupradar.modelos.Evento;

public class FragmentEvento extends Fragment {

    private Evento evento;

    public FragmentEvento() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_evento, container, false);

        Intent intent = getActivity().getIntent();
        String idMarker = intent.getStringExtra("markerSeleccionado");
        evento = intent.getParcelableExtra(idMarker);

        TextView textEvento = (TextView) view.findViewById(R.id.textEvento);
        textEvento.setText(evento.getNombre());

        return view;
    }
}
