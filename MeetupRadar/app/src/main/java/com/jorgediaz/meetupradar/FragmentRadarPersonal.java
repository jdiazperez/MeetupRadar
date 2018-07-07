package com.jorgediaz.meetupradar;

import android.os.Bundle;
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
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.persistence.local.UserIdStorageFactory;
import com.jorgediaz.meetupradar.modelos.Categoria;
import com.jorgediaz.meetupradar.modelos.Categorias;
import com.jorgediaz.meetupradar.modelos.Radar;
import com.jorgediaz.meetupradar.modelos.RadarEscuchaCategoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FragmentRadarPersonal extends Fragment {

    private View view;
    private int valorRadio;
    ArrayList<Integer> categoriasSeleccionadas;
    Radar radarPersonal;
    private String userId;

    public FragmentRadarPersonal() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_radar_personal, container, false);

        obtenerRadarPersonal();
        controlarSeekBarRadio();
        eventosBotones();
        eventosCheckBoxes();

        return view;
    }

    private void eventosCheckBoxes() {
        categoriasSeleccionadas = new ArrayList<>();
        final Map<Integer, Categoria> categorias = Categorias.getCategorias();
        for (Integer checkBoxId : categorias.keySet()) {
            CheckBox checkBoxCategoria = (CheckBox) view.findViewById(checkBoxId);
            checkBoxCategoria.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int idCategoria = categorias.get(compoundButton.getId()).getIdMeetup();
                    if (b) {
                        categoriasSeleccionadas.add(idCategoria);
                    } else {
                        categoriasSeleccionadas.remove(idCategoria);
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
                cargarFragmentMapa();
            }
        });
    }

    private void guardarRadarPersonal() {
        if (radarPersonal == null) {
            radarPersonal = new Radar("personal", 0, 0, valorRadio);
        } else {
            radarPersonal.setRadio(valorRadio);
        }
        Backendless.Data.of(Radar.class).save(radarPersonal, new AsyncCallback<Radar>() {
            @Override
            public void handleResponse(Radar response) {
                Log.e("guardarRadar", "Exito");
                radarPersonal = response;
                guardarCategorias();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("guardarRadar", "Error: " + fault.getCode() + ": " + fault.getMessage());
            }
        });

    }

    private void guardarCategorias() {
        Iterator<Integer> iterator = categoriasSeleccionadas.iterator();
        while (iterator.hasNext()) {
            RadarEscuchaCategoria escucha = new RadarEscuchaCategoria(radarPersonal.getId(), iterator.next());

            Backendless.Data.of(RadarEscuchaCategoria.class).save(escucha, new AsyncCallback<RadarEscuchaCategoria>() {
                @Override
                public void handleResponse(RadarEscuchaCategoria response) {
                    Log.e("guardarCategoria", "Exito");
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("guardarCategoria", "Error: " + fault.getCode() + ": " + fault.getMessage());
                }
            });
        }
    }

    private void obtenerRadarPersonal() {
        userId = UserIdStorageFactory.instance().getStorage().get();
        String whereClause = "nombre = 'personal' and ownerId = '" + userId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of(Radar.class).find(queryBuilder, new AsyncCallback<List<Radar>>() {
            @Override
            public void handleResponse(List<Radar> response) {
                radarPersonal = response.get(0);
                actualizarValoresRadarEnPantalla();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                radarPersonal = null;
                Log.e("getRadar", "Error: " + fault.getCode() + ": " + fault.getMessage());
            }
        });
    }

    private void actualizarValoresRadarEnPantalla() {
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBarRadio);
        seekBar.setProgress(radarPersonal.getRadio());
        TextView seekBarValor = (TextView) view.findViewById(R.id.textRadioValor);
        seekBarValor.setText(String.valueOf(radarPersonal.getRadio()) + " km");
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
