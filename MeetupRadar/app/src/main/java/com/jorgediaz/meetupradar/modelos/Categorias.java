package com.jorgediaz.meetupradar.modelos;

import com.jorgediaz.meetupradar.R;

import java.util.HashMap;
import java.util.Map;

public class Categorias {

    private static final Map<Integer, Categoria> categoriasPorCheckBox = new HashMap<>();
    private static final Map<Integer, Categoria> categoriasPorId = new HashMap<>();

    private static Categorias instancia = new Categorias();

    private Categorias() {
        categoriasPorCheckBox.put(R.id.checkCategoria1, new Categoria(1, R.id.checkCategoria1, "Arte y Cultura"));
        categoriasPorCheckBox.put(R.id.checkCategoria2, new Categoria(2, R.id.checkCategoria2, "Carrera y Negocios"));
        categoriasPorCheckBox.put(R.id.checkCategoria3, new Categoria(3, R.id.checkCategoria3, "Autos y Motos"));
        categoriasPorCheckBox.put(R.id.checkCategoria4, new Categoria(4, R.id.checkCategoria4, "Comunidad y Medioambiente"));
        categoriasPorCheckBox.put(R.id.checkCategoria5, new Categoria(5, R.id.checkCategoria5, "Baile"));
        categoriasPorCheckBox.put(R.id.checkCategoria6, new Categoria(6, R.id.checkCategoria6, "Educación y Formación"));
        categoriasPorCheckBox.put(R.id.checkCategoria8, new Categoria(8, R.id.checkCategoria8, "Moda y Belleza"));
        categoriasPorCheckBox.put(R.id.checkCategoria9, new Categoria(9, R.id.checkCategoria9, "Condición Física"));
        categoriasPorCheckBox.put(R.id.checkCategoria10, new Categoria(10, R.id.checkCategoria10, "Comida y Bebida"));
        categoriasPorCheckBox.put(R.id.checkCategoria11, new Categoria(11, R.id.checkCategoria11, "Juegos"));
        categoriasPorCheckBox.put(R.id.checkCategoria12, new Categoria(12, R.id.checkCategoria12, "LGTB"));
        categoriasPorCheckBox.put(R.id.checkCategoria13, new Categoria(13, R.id.checkCategoria13, "Movimientos y Política"));
        categoriasPorCheckBox.put(R.id.checkCategoria14, new Categoria(14, R.id.checkCategoria14, "Salud y Bienestar"));
        categoriasPorCheckBox.put(R.id.checkCategoria15, new Categoria(15, R.id.checkCategoria15, "Artesanía, Aficiones y Manualidades"));
        categoriasPorCheckBox.put(R.id.checkCategoria16, new Categoria(16, R.id.checkCategoria16, "Idiomas e Identidad Cultural"));
        categoriasPorCheckBox.put(R.id.checkCategoria17, new Categoria(17, R.id.checkCategoria17, "Estilo de Vida"));
        categoriasPorCheckBox.put(R.id.checkCategoria18, new Categoria(18, R.id.checkCategoria18, "Libros"));
        categoriasPorCheckBox.put(R.id.checkCategoria20, new Categoria(20, R.id.checkCategoria20, "Cine y Películas"));
        categoriasPorCheckBox.put(R.id.checkCategoria21, new Categoria(21, R.id.checkCategoria21, "Música"));
        categoriasPorCheckBox.put(R.id.checkCategoria22, new Categoria(22, R.id.checkCategoria22, "New Age y Espiritualidad"));
        categoriasPorCheckBox.put(R.id.checkCategoria23, new Categoria(23, R.id.checkCategoria23, "Naturaleza y Aventura"));
        categoriasPorCheckBox.put(R.id.checkCategoria24, new Categoria(24, R.id.checkCategoria24, "Fenómenos Paranormales"));
        categoriasPorCheckBox.put(R.id.checkCategoria25, new Categoria(25, R.id.checkCategoria25, "Padres y Familia"));
        categoriasPorCheckBox.put(R.id.checkCategoria26, new Categoria(26, R.id.checkCategoria26, "Mascotas y Animales"));
        categoriasPorCheckBox.put(R.id.checkCategoria27, new Categoria(27, R.id.checkCategoria27, "Fotografía"));
        categoriasPorCheckBox.put(R.id.checkCategoria28, new Categoria(28, R.id.checkCategoria28, "Religión y Creencias"));
        categoriasPorCheckBox.put(R.id.checkCategoria30, new Categoria(30, R.id.checkCategoria30, "Personas Solteras"));
        categoriasPorCheckBox.put(R.id.checkCategoria31, new Categoria(31, R.id.checkCategoria31, "Vida Social"));
        categoriasPorCheckBox.put(R.id.checkCategoria32, new Categoria(32, R.id.checkCategoria32, "Deportes y Tiempo Libre"));
        categoriasPorCheckBox.put(R.id.checkCategoria33, new Categoria(33, R.id.checkCategoria33, "Apoyo"));
        categoriasPorCheckBox.put(R.id.checkCategoria36, new Categoria(36, R.id.checkCategoria36, "Escritura"));

        categoriasPorId.put(1, new Categoria(1, R.id.checkCategoria1, "Arte y Cultura"));
        categoriasPorId.put(2, new Categoria(2, R.id.checkCategoria2, "Carrera y Negocios"));
        categoriasPorId.put(3, new Categoria(3, R.id.checkCategoria3, "Autos y Motos"));
        categoriasPorId.put(4, new Categoria(4, R.id.checkCategoria4, "Comunidad y Medioambiente"));
        categoriasPorId.put(5, new Categoria(5, R.id.checkCategoria5, "Baile"));
        categoriasPorId.put(6, new Categoria(6, R.id.checkCategoria6, "Educación y Formación"));
        categoriasPorId.put(8, new Categoria(8, R.id.checkCategoria8, "Moda y Belleza"));
        categoriasPorId.put(9, new Categoria(9, R.id.checkCategoria9, "Condición Física"));
        categoriasPorId.put(10, new Categoria(10, R.id.checkCategoria10, "Comida y Bebida"));
        categoriasPorId.put(11, new Categoria(11, R.id.checkCategoria11, "Juegos"));
        categoriasPorId.put(12, new Categoria(12, R.id.checkCategoria12, "LGTB"));
        categoriasPorId.put(13, new Categoria(13, R.id.checkCategoria13, "Movimientos y Política"));
        categoriasPorId.put(14, new Categoria(14, R.id.checkCategoria14, "Salud y Bienestar"));
        categoriasPorId.put(15, new Categoria(15, R.id.checkCategoria15, "Artesanía, Aficiones y Manualidades"));
        categoriasPorId.put(16, new Categoria(16, R.id.checkCategoria16, "Idiomas e Identidad Cultural"));
        categoriasPorId.put(17, new Categoria(17, R.id.checkCategoria17, "Estilo de Vida"));
        categoriasPorId.put(18, new Categoria(18, R.id.checkCategoria18, "Libros"));
        categoriasPorId.put(20, new Categoria(20, R.id.checkCategoria20, "Cine y Películas"));
        categoriasPorId.put(21, new Categoria(21, R.id.checkCategoria21, "Música"));
        categoriasPorId.put(22, new Categoria(22, R.id.checkCategoria22, "New Age y Espiritualidad"));
        categoriasPorId.put(23, new Categoria(23, R.id.checkCategoria23, "Naturaleza y Aventura"));
        categoriasPorId.put(24, new Categoria(24, R.id.checkCategoria24, "Fenómenos Paranormales"));
        categoriasPorId.put(5, new Categoria(25, R.id.checkCategoria25, "Padres y Familia"));
        categoriasPorId.put(26, new Categoria(26, R.id.checkCategoria26, "Mascotas y Animales"));
        categoriasPorId.put(27, new Categoria(27, R.id.checkCategoria27, "Fotografía"));
        categoriasPorId.put(28, new Categoria(28, R.id.checkCategoria28, "Religión y Creencias"));
        categoriasPorId.put(30, new Categoria(30, R.id.checkCategoria30, "Personas Solteras"));
        categoriasPorId.put(31, new Categoria(31, R.id.checkCategoria31, "Vida Social"));
        categoriasPorId.put(32, new Categoria(32, R.id.checkCategoria32, "Deportes y Tiempo Libre"));
        categoriasPorId.put(33, new Categoria(33, R.id.checkCategoria33, "Apoyo"));
        categoriasPorId.put(36, new Categoria(36, R.id.checkCategoria36, "Escritura"));
    }

    public static Map<Integer, Categoria> getCategoriasPorCheckBox() {
        return categoriasPorCheckBox;
    }

    public static Map<Integer, Categoria> getCategoriasPorId() {
        return categoriasPorId;
    }

    public static Categoria getCategoriaPorId(int idCategoria) {
        return categoriasPorId.get(idCategoria);
    }
}
