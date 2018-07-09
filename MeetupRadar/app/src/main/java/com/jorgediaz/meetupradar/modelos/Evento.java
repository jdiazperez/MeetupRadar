package com.jorgediaz.meetupradar.modelos;


import com.jorgediaz.meetupradar.rest.Event;

public class Evento {
    private String objectId;
    private String idMeetup;
    private String nombre;
    private String descripcion;
    private double distanciaDesdePtoBusqueda;
    private int duracion;
    private String url;
    private int numPersonasApuntadas;
    private String foto;
    private String estado;
    private long horaComienzo;
    private Direccion direccion;
    private Grupo grupo;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getIdMeetup() {
        return idMeetup;
    }

    public void setIdMeetup(String idMeetup) {
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

    public Evento(Event eventoRetrofit, Direccion direccion, Grupo grupo) {
        this.idMeetup = eventoRetrofit.getId();
        this.nombre = eventoRetrofit.getName();
        this.descripcion = eventoRetrofit.getDescription();
        this.distanciaDesdePtoBusqueda = eventoRetrofit.getDistance();
        this.duracion = eventoRetrofit.getDuration();
        this.url = eventoRetrofit.getEventUrl();
        this.numPersonasApuntadas = eventoRetrofit.getYesRsvpCount();
        this.foto = eventoRetrofit.getPhotoUrl();
        this.estado = eventoRetrofit.getStatus();
        this.horaComienzo = eventoRetrofit.getTime();
        this.direccion = direccion;
        this.grupo = grupo;
    }
}
