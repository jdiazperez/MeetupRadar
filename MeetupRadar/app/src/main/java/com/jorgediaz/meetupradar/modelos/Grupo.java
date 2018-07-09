package com.jorgediaz.meetupradar.modelos;


import com.jorgediaz.meetupradar.rest.Group;

public class Grupo {
    private String objectId;
    private int idMeetup;
    private String nombre;
    private String url;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Grupo() {
    }

    public Grupo(Group grupoRetrofit) {
        this.idMeetup = grupoRetrofit.getId();
        this.nombre = grupoRetrofit.getName();
        this.url = grupoRetrofit.getUrlname();
    }
}
