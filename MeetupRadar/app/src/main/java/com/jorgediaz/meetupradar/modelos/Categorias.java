package com.jorgediaz.meetupradar.modelos;

import com.jorgediaz.meetupradar.R;

import java.util.HashMap;
import java.util.Map;

public class Categorias {

    private static final Map<Integer, Categoria> categorias = new HashMap<>();

    private static Categorias instancia = new Categorias();

    private Categorias() {
        categorias.put(R.id.checkCategoria1, new Categoria(1, "Arte y Cultura"));
        categorias.put(R.id.checkCategoria2, new Categoria(2, "Carrera y Negocios"));
        categorias.put(R.id.checkCategoria3, new Categoria(3, "Autos y Motos"));
        categorias.put(R.id.checkCategoria4, new Categoria(4, "Comunidad y Medioambiente"));
        categorias.put(R.id.checkCategoria5, new Categoria(5, "Baile"));
        categorias.put(R.id.checkCategoria6, new Categoria(6, "Educación y Formación"));
        categorias.put(R.id.checkCategoria8, new Categoria(8, "Moda y Belleza"));
        categorias.put(R.id.checkCategoria9, new Categoria(9, "Condición Física"));
        categorias.put(R.id.checkCategoria10, new Categoria(10, "Comida y Bebida"));
        categorias.put(R.id.checkCategoria11, new Categoria(11, "Juegos"));
        categorias.put(R.id.checkCategoria12, new Categoria(12, "LGTB"));
        categorias.put(R.id.checkCategoria13, new Categoria(13, "Movimientos y Política"));
        categorias.put(R.id.checkCategoria14, new Categoria(14, "Salud y Bienestar"));
        categorias.put(R.id.checkCategoria15, new Categoria(15, "Artesanía, Aficiones y Manualidades"));
        categorias.put(R.id.checkCategoria16, new Categoria(16, "Idiomas e Identidad Cultural"));
        categorias.put(R.id.checkCategoria17, new Categoria(17, "Estilo de Vida"));
        categorias.put(R.id.checkCategoria18, new Categoria(18, "Libros"));
        categorias.put(R.id.checkCategoria20, new Categoria(20, "Cine y Películas"));
        categorias.put(R.id.checkCategoria21, new Categoria(21, "Música"));
        categorias.put(R.id.checkCategoria22, new Categoria(22, "New Age y Espiritualidad"));
        categorias.put(R.id.checkCategoria23, new Categoria(23, "Naturaleza y Aventura"));
        categorias.put(R.id.checkCategoria24, new Categoria(24, "Fenómenos Paranormales"));
        categorias.put(R.id.checkCategoria25, new Categoria(25, "Padres y Familia"));
        categorias.put(R.id.checkCategoria26, new Categoria(26, "Mascotas y Animales"));
        categorias.put(R.id.checkCategoria27, new Categoria(27, "Fotografía"));
        categorias.put(R.id.checkCategoria28, new Categoria(28, "Religión y Creencias"));
        categorias.put(R.id.checkCategoria30, new Categoria(30, "Personas Solteras"));
        categorias.put(R.id.checkCategoria31, new Categoria(31, "Vida Social"));
        categorias.put(R.id.checkCategoria32, new Categoria(32, "Deportes y Tiempo Libre"));
        categorias.put(R.id.checkCategoria33, new Categoria(33, "Apoyo"));
        categorias.put(R.id.checkCategoria36, new Categoria(36, "Escritura"));
    }

    public static Map<Integer, Categoria> getCategorias() {
        return categorias;
    }
}
