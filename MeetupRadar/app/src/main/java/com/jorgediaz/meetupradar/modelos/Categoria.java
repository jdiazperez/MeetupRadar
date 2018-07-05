package com.jorgediaz.meetupradar.modelos;

public class Categoria {
    private int idMeetup;
    private String nombre;

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

    public Categoria() {
    }

    public Categoria(int idMeetup, String nombre) {
        this.idMeetup = idMeetup;
        this.nombre = nombre;
    }
}
