package com.jorgediaz.meetupradar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragmentRadarPersonal extends Fragment {

    private View view;

    public FragmentRadarPersonal() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_radar_personal, container, false);

        controlarSeekBarRadio();
        eventosBotones();


        return view;
    }

    private void eventosBotones() {
        Button btnCancelar = (Button) view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarFragmentMapa();
            }
        });
        Button btnGuardar = (Button) view.findViewById(R.id.btnGuardarRadarPersonal);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarFragmentMapa();
            }
        });
    }

    private void cargarFragmentMapa() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, new FragmentMapa())
                .commit();
        ((AppCompatActivity) getActivity()).setTitle(getString(R.string.app_name) + ": Mapa");
    }

    private void controlarSeekBarRadio() {
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBarRadio);
        final TextView seekBarValor = (TextView) view.findViewById(R.id.textRadioValor);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarValor.setText(String.valueOf(i) + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void cancelar(View v) {

    }

    public void guardarRadar(View v) {

    }
}
