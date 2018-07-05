package com.jorgediaz.meetupradar.modelos;

import java.util.HashMap;
import java.util.Map;

public class Categorias {

    public static final Map<Integer, String> categorias = new HashMap<>();

    private static Categorias instancia = new Categorias();

    private Categorias() {
        categorias.put(1, "Arte y Cultura");
        categorias.put(18, "Libros");
        categorias.put(2, "Carrera y Negocios");
        categorias.put(3, "Autos y Motos");
        categorias.put(4, "Comunidad y Medioambiente");
        categorias.put(5, "Baile");
        categorias.put(6, "Educación y Formación");
        categorias.put(8, "Moda y Belleza");
        categorias.put(9, "Condición Física");
        categorias.put(10, "Comida y Bebida");
        categorias.put(11, "Juegos");
        categorias.put(13, "Movimientos y Política");
        categorias.put(14, "Salud y Bienestar");
        categorias.put(15, "Artesanía, Aficiones y Manualidades");
        categorias.put(16, "Idiomas e Identidad Cultural");
        categorias.put(12, "LGTB");
        categorias.put(17, "Estilo de Vida");
        categorias.put(20, "Cine y Películas");
        categorias.put(21, "Música");
        categorias.put(22, "New Age y Espiritualidad");
        categorias.put(23, "Naturaleza y Aventura");
        categorias.put(24, "Fenómenos Paranormales");
        categorias.put(24, "Fenómenos Paranormales");
        categorias.put(25, "Padres y Familia");
        categorias.put(26, "Mascotas y Animales");
        categorias.put(27, "Fotografía");
        categorias.put(28, "Religión y Creencias");
        categorias.put(30, "Personas Solteras");
        categorias.put(31, "Vida Social");
        categorias.put(32, "Deportes y Tiempo Libre");
        categorias.put(33, "Apoyo");
        categorias.put(36, "Escritura");
    }

    public static Map<Integer, String> getCategorias() {
        return categorias;
    }
}
