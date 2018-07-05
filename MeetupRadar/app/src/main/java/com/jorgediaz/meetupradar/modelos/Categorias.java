package com.jorgediaz.meetupradar.modelos;

public class Categorias {
    public final Categoria ARTES;
    public final Categoria LIBROS;
    public final Categoria NEGOCIOS;
    public final Categoria AUTO;
    public final Categoria COMUNIDAD;
    public final Categoria BAILE;
    public final Categoria EDUCACION;
    public final Categoria MODA;
    public final Categoria CONDICIONFISICA;
    public final Categoria COMIDA;
    public final Categoria JUEGOS;
    public final Categoria MOVIMIENTOS;
    public final Categoria BIENESTAR;
    public final Categoria ARTESANIA;
    public final Categoria IDIOMAS;
    public final Categoria LGTB;
    public final Categoria VIDA;
    public final Categoria PELICULAS;
    public final Categoria MUSICA;
    public final Categoria ESPIRITUALIDAD;
    public final Categoria NATURALEZA;
    public final Categoria PARANORMAL;
    public final Categoria FAMILIA;
    public final Categoria MASCOTAS;
    public final Categoria FOTOGRAFIA;
    public final Categoria RELIGION;
    public final Categoria CIENCIAFICCION;
    public final Categoria SOLTEROS;
    public final Categoria SOCIAL;
    public final Categoria DEPORTES;
    public final Categoria APOYO;
    public final Categoria TECNOLOGIA;
    public final Categoria ESCRITURA;


    private static Categorias instancia = new Categorias();

    private Categorias() {
        this.ARTES = new Categoria(1, "Arte y Cultura");
        this.LIBROS = new Categoria(18, "Libros");
        this.NEGOCIOS = new Categoria(2, "Carrera y Negocios");
        this.AUTO = new Categoria(3, "Autos y Motos");
        this.COMUNIDAD = new Categoria(4, "Comunidad y Medioambiente");
        this.BAILE = new Categoria(5, "Baile");
        this.EDUCACION = new Categoria(6, "Educación y Formación");
        this.MODA = new Categoria(8, "Moda y Belleza");
        this.CONDICIONFISICA = new Categoria(9, "Condición Física");
        this.COMIDA = new Categoria(10, "Comida y Bebida");
        this.JUEGOS = new Categoria(11, "Juegos");
        this.MOVIMIENTOS = new Categoria(13, "Movimientos y Política");
        this.BIENESTAR = new Categoria(14, "Salud y Bienestar");
        this.ARTESANIA = new Categoria(15, "Artesanía, Aficiones y Manualidades");
        this.IDIOMAS = new Categoria(16, "Idiomas e Identidad Cultural");
        this.LGTB = new Categoria(12, "LGTB");
        this.VIDA = new Categoria(17, "Estilo de Vida");
        this.PELICULAS = new Categoria(20, "Cine y Películas");
        this.MUSICA = new Categoria(21, "Música");
        this.ESPIRITUALIDAD = new Categoria(22, "New Age y Espiritualidad");
        this.NATURALEZA = new Categoria(23, "Naturaleza y Aventura");
        this.PARANORMAL = new Categoria(24, "Fenómenos Paranormales");
        this.FAMILIA = new Categoria(25, "Padres y Familia");
        this.MASCOTAS = new Categoria(26, "Mascotas y Animales");
        this.FOTOGRAFIA = new Categoria(27, "Fotografía");
        this.RELIGION = new Categoria(28, "Religión y Creencias");
        this.CIENCIAFICCION = new Categoria(29, "Ciencia Ficción y Fantasía");
        this.SOLTEROS = new Categoria(30, "Personas Solteras");
        this.SOCIAL = new Categoria(31, "Vida Social");
        this.DEPORTES = new Categoria(32, "Deportes y Tiempo Libre");
        this.APOYO = new Categoria(33, "Apoyo");
        this.TECNOLOGIA = new Categoria(34, "Tecnología");
        this.ESCRITURA = new Categoria(36, "Escritura");
    }

    public static Categorias getCategorias() {
        return instancia;
    }
}
