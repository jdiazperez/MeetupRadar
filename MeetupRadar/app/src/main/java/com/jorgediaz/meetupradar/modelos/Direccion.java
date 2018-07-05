package com.jorgediaz.meetupradar.modelos;


import com.jorgediaz.meetupradar.rest.Venue;

import weborb.service.MapToProperty;

public class Direccion {
    @MapToProperty(property = "objectId")
    private String id;
    private int idMeetup;
    private String calle;
    private String ciudad;
    private String pais;
    private double latitud;
    private double longitud;
    private String nombre;

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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion() {
    }

    public Direccion(Venue venue) {
        this.idMeetup = venue.getId();
        this.calle = venue.getAddress1();
        this.ciudad = venue.getCity();
        this.pais = venue.getLocalizedCountryName();
        this.latitud = venue.getLat();
        this.longitud = venue.getLon();
        this.nombre = venue.getName();
    }
}
