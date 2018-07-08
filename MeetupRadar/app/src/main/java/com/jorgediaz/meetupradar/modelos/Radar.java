package com.jorgediaz.meetupradar.modelos;

public class Radar {
    private String objectId;
    private String nombre;
    private double latidud;
    private double longitud;
    private int radio;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatidud() {
        return latidud;
    }

    public void setLatidud(double latidud) {
        this.latidud = latidud;
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

    public Radar() {
    }

    public Radar(String nombre, double latidud, double longitud, int radio) {
        this.nombre = nombre;
        this.latidud = latidud;
        this.longitud = longitud;
        this.radio = radio;
    }

    @Override
    public String toString() {
        return "Radar{" +
                "objectId='" + objectId + '\'' +
                ", nombre='" + nombre + '\'' +
                ", latidud=" + latidud +
                ", longitud=" + longitud +
                ", radio=" + radio +
                '}';
    }
}
