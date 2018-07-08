package com.jorgediaz.meetupradar.modelos;

public class Categoria {
    private int idMeetup;
    private int idCheckBox;
    private String nombre;

    public int getIdMeetup() {
        return idMeetup;
    }

    public void setIdMeetup(int idMeetup) {
        this.idMeetup = idMeetup;
    }

    public int getIdCheckBox() {
        return idCheckBox;
    }

    public void setIdCheckBox(int idCheckBox) {
        this.idCheckBox = idCheckBox;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Categoria(int idMeetup, int idCheckBox, String nombre) {
        this.idMeetup = idMeetup;
        this.idCheckBox = idCheckBox;
        this.nombre = nombre;
    }
}
