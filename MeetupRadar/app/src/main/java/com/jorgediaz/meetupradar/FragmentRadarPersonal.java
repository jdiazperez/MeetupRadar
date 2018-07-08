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
    private ArrayList<Integer> categoriasSeleccionadas;
    private Radar radarPersonal;
    private String userId;

    public FragmentRadarPersonal() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_radar_personal, container, false);
        categoriasSeleccionadas = new ArrayList<>();
        obtenerRadarPersonal();
        controlarSeekBarRadio();
        eventosBotones();

        return view;
    }

    private void obtenerRadarPersonal() {
        userId = UserIdStorageFactory.instance().getStorage().get();

        String whereClause = "nombre = 'personal' and ownerId = '" + userId + "'";
        Log.e("obtenerRadarPersonal", whereClause);
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of(Radar.class).find(queryBuilder, new AsyncCallback<List<Radar>>() {
            @Override
            public void handleResponse(List<Radar> response) {
                if (response.size() > 0) {
                    radarPersonal = response.get(0);
                    actualizarValoresRadarEnPantalla();
                } else {
                    radarPersonal = new Radar("personal", 0, 0, 0);
                }
                Log.e("obtenerRadarPersonal", radarPersonal.toString());

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                radarPersonal = new Radar("personal", 0, 0, 0);
                Log.e("getRadar", "Error: " + fault.getCode() + ": " + fault.getMessage());
            }
        });
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
                        Log.e("onCheckChange, checked", String.valueOf(idCategoria));
                        Log.e("onCheckChange, tamArray", String.valueOf(categoriasSeleccionadas.size()));
                    } else {
                        categoriasSeleccionadas.remove((Integer) idCategoria);
                        Log.e("onCheckChange, uncheck", String.valueOf(idCategoria));
                        Log.e("onCheckChange, tamArray", String.valueOf(categoriasSeleccionadas.size()));
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
        radarPersonal.setRadio(valorRadio);
        Log.e("guardarRadarPersonal 1", radarPersonal.toString());

        Backendless.Persistence.save(radarPersonal, new AsyncCallback<Radar>() {
            @Override
            public void handleResponse(Radar response) {
                Log.e("guardarRadarPersonal 2", "Exito");
                radarPersonal = response;
                Log.e("guardarRadarPersonal 2", radarPersonal.toString());
                guardarCategorias();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("guardarRadar", "Error: " + fault.getCode() + ": " + fault.getMessage());
            }
        });

    }

    private void guardarCategorias() {
        eliminarCategorias();
        Iterator<Integer> iterator = categoriasSeleccionadas.iterator();
        Log.e("guardarCategorias", String.valueOf(categoriasSeleccionadas.size()));
        while (iterator.hasNext()) {
            RadarEscuchaCategoria escucha = new RadarEscuchaCategoria(radarPersonal.getObjectId(), iterator.next());

            Backendless.Persistence.save(escucha, new AsyncCallback<RadarEscuchaCategoria>() {
                @Override
                public void handleResponse(RadarEscuchaCategoria response) {
                    Log.e("guardarCategoria, Exito", String.valueOf(response.getIdCategoria()));
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("guardarCategoria", "Error: " + fault.getCode() + ": " + fault.getMessage());
                }
            });
        }
    }


    private void actualizarValoresRadarEnPantalla() {
        valorRadio = radarPersonal.getRadio();
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBarRadio);
        seekBar.setProgress(radarPersonal.getRadio());
        TextView seekBarValor = (TextView) view.findViewById(R.id.textRadioValor);
        seekBarValor.setText(String.valueOf(radarPersonal.getRadio()) + " km");

        obtenerCategorias();
    }

    private void obtenerCategorias() {
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
                actualizarCheckBoxesEnPantalla();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("obtenerCategorias", "Error: " + fault.getCode() + ": " + fault.getMessage());
            }
        });

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

    private void eliminarCategorias() {
        String whereClause = "idRadar = '" + radarPersonal.getObjectId() + "'";
        Backendless.Data.of(RadarEscuchaCategoria.class).remove(whereClause, new AsyncCallback<Integer>() {
            @Override
            public void handleResponse(Integer response) {
                Log.e("eliminarCategorias", "Exito");
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("eliminarCategorias", "Error: " + fault.getCode() + ": " + fault.getMessage());
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
