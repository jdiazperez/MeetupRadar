package com.jorgediaz.meetupradar.modelos;

import weborb.service.MapToProperty;

public class Radar {
    @MapToProperty(property = "objectId")
    private String id;
    private String nombre;
    private double latitud;
    private double longitud;
    private int radio;
    @MapToProperty(property = "ownerId")
    private String idUsuario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public Radar(String nombre, double latitud, double longitud, int radio) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radio = radio;
    }

    public Radar() {
    }
}
