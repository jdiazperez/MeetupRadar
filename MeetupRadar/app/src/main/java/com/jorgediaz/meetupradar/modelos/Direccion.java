package com.jorgediaz.meetupradar.modelos;


import com.jorgediaz.meetupradar.rest.Venue;

public class Direccion {
    private String objectId;
    private int idMeetup;
    private String calle;
    private String ciudad;
    private String pais;
    private double latitud;
    private double longitud;
    private String nombre;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    public Direccion(Venue dirRetrofit) {
        this.idMeetup = dirRetrofit.getId();
        this.calle = dirRetrofit.getAddress1();
        this.ciudad = dirRetrofit.getCity();
        this.pais = dirRetrofit.getLocalizedCountryName();
        this.latitud = dirRetrofit.getLat();
        this.longitud = dirRetrofit.getLon();
        this.nombre = dirRetrofit.getName();
    }
}
