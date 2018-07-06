package com.jorgediaz.meetupradar;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

        insertarChecksCategorias();
        controlarSeekBarRadio();

        return view;
    }

    private void insertarChecksCategorias() {
        ConstraintLayout constraint = view.findViewById(R.id.constraintRadarPersonal);
        View textCategorias = constraint.findViewById(R.id.textCategorias);
        int[] location = new int[2];
        textCategorias.getLocationInWindow(location);
        CheckBox checkCategoria = new CheckBox(constraint.getContext());
        checkCategoria.setX(location[0]);
        checkCategoria.setY(location[1] + 1);
        checkCategoria.setText("Prueba 1");
        CheckBox checkCategoria2 = new CheckBox(constraint.getContext());
        checkCategoria2.setText("Prueba 2");
        constraint.addView(checkCategoria);
        constraint.addView(checkCategoria2);
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
}
