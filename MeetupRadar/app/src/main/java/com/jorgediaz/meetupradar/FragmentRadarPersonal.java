package com.jorgediaz.meetupradar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.jorgediaz.meetupradar.modelos.Categoria;
import com.jorgediaz.meetupradar.modelos.Categorias;
import com.jorgediaz.meetupradar.modelos.Radar;
import com.jorgediaz.meetupradar.modelos.RadarEscuchaCategoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class FragmentRadarPersonal extends Fragment {

    private View view;
    private int valorRadio;
    private ArrayList<Integer> categoriasSeleccionadas;
    private Radar radarPersonal;
    private String userId;
    private Intent intent;

    public FragmentRadarPersonal() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_radar_personal, container, false);
        obtenerRadarPersonal();
        controlarSeekBarRadio();
        eventosBotones();

        return view;
    }

    private void obtenerRadarPersonal() {
        intent = getActivity().getIntent();
        radarPersonal = intent.getParcelableExtra("radarPersonal");
        if (radarPersonal == null) {
            radarPersonal = new Radar("personal", 0, 0, 0);
            categoriasSeleccionadas = new ArrayList<>();
            eventosCheckBoxes();
        } else {
            categoriasSeleccionadas = intent.getIntegerArrayListExtra("categoriasSeleccionadas");
            if (categoriasSeleccionadas == null) {
                categoriasSeleccionadas = new ArrayList<>();
                actualizarRadio();
                eventosCheckBoxes();
            } else {
                actualizarValoresRadarEnPantalla();
            }
        }
    }

    private void eventosCheckBoxes() {
        final Map<Integer, Categoria> categorias = Categorias.getCategoriasPorCheckBox();
        for (Integer checkBoxId : categorias.keySet()) {
            CheckBox checkBoxCategoria = (CheckBox) view.findViewById(checkBoxId);
            checkBoxCategoria.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int idCategoria = categorias.get(compoundButton.getId()).getIdMeetup();
                    if (b) {
                        categoriasSeleccionadas.add(idCategoria);
                    } else {
                        categoriasSeleccionadas.remove((Integer) idCategoria);
                    }
                }
            });
        }
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
                guardarRadarPersonal();
            }
        });
    }

    private void guardarRadarPersonal() {
        radarPersonal.setRadio(valorRadio);

        Backendless.Persistence.save(radarPersonal, new AsyncCallback<Radar>() {
            @Override
            public void handleResponse(Radar response) {
                radarPersonal = response;
                intent.putExtra("radarPersonal", radarPersonal);
                guardarCategorias();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("guardarRadar", "Error: " + fault.getCode() + ": " + fault.getMessage());
            }
        });

    }

    private void guardarCategorias() {
        intent.putIntegerArrayListExtra("categoriasSeleccionadas", categoriasSeleccionadas);
        cargarFragmentMapa();
        eliminarAntiguasCategoriasEnBD();
    }

    private void actualizarValoresRadarEnPantalla() {
        actualizarRadio();
        actualizarCheckBoxesEnPantalla();
    }

    private void actualizarRadio() {
        valorRadio = radarPersonal.getRadio();
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBarRadio);
        seekBar.setProgress(radarPersonal.getRadio());
        TextView seekBarValor = (TextView) view.findViewById(R.id.textRadioValor);
        seekBarValor.setText(String.valueOf(radarPersonal.getRadio()) + " km");
    }

    private void actualizarCheckBoxesEnPantalla() {
        Iterator<Integer> iterator = categoriasSeleccionadas.iterator();
        while (iterator.hasNext()) {
            Categoria categoria = Categorias.getCategoriaPorId(iterator.next());
            CheckBox checkBoxCategoria = (CheckBox) view.findViewById(categoria.getIdCheckBox());
            checkBoxCategoria.setChecked(true);

        }
        eventosCheckBoxes();

    }

    private void eliminarAntiguasCategoriasEnBD() {
        String whereClause = "idRadar = '" + radarPersonal.getObjectId() + "'";
        Backendless.Data.of(RadarEscuchaCategoria.class).remove(whereClause, new AsyncCallback<Integer>() {
            @Override
            public void handleResponse(Integer response) {
                guardarNuevasCategoriasEnBD();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("eliminarCategorias", "Error: " + fault.getMessage());
            }
        });
    }

    private void guardarNuevasCategoriasEnBD() {
        Iterator<Integer> iterator = categoriasSeleccionadas.iterator();
        while (iterator.hasNext()) {
            RadarEscuchaCategoria escucha = new RadarEscuchaCategoria(radarPersonal.getObjectId(), iterator.next());

            Backendless.Persistence.save(escucha, new AsyncCallback<RadarEscuchaCategoria>() {
                @Override
                public void handleResponse(RadarEscuchaCategoria response) {
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("guardarCategoria", "Error: " + fault.getCode() + ": " + fault.getMessage());
                }
            });
        }
    }

    private void cargarFragmentMapa() {
        NavigationView navView = (NavigationView) getActivity().findViewById(R.id.navview);
        navView.getMenu().findItem(R.id.menu_mapa).setChecked(true);

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
                valorRadio = i;
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
