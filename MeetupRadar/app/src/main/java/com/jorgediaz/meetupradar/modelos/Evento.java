package com.jorgediaz.meetupradar.modelos;


import weborb.service.MapToProperty;

public class Evento {
    @MapToProperty(property = "objectId")
    private String id;
    private int idMeetup;
    private String nombre;
    private String descripcion;
    private double distanciaDesdePtoBusqueda;
    private int duracion;
    private String url;
    private int cuota;
    private int numPersonasApuntadas;
    private String foto;
    private String estado;
    private long horaComienzo;
    private int maxPersonas;
    private Direccion direccion;
    private Grupo grupo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdMeetup() {
        return idMeetup;
    }

    public void setIdMeetup(int idMeetup) {
        this.idMeetup = idMeetup;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDistanciaDesdePtoBusqueda() {
        return distanciaDesdePtoBusqueda;
    }

    public void setDistanciaDesdePtoBusqueda(double distanciaDesdePtoBusqueda) {
        this.distanciaDesdePtoBusqueda = distanciaDesdePtoBusqueda;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public int getNumPersonasApuntadas() {
        return numPersonasApuntadas;
    }

    public void setNumPersonasApuntadas(int numPersonasApuntadas) {
        this.numPersonasApuntadas = numPersonasApuntadas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(long horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public int getMaxPersonas() {
        return maxPersonas;
    }

    public void setMaxPersonas(int maxPersonas) {
        this.maxPersonas = maxPersonas;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Evento() {
    }
}
