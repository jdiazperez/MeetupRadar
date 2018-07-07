package com.jorgediaz.meetupradar.modelos;

import weborb.service.MapToProperty;

public class RadarEscuchaCategoria {
    @MapToProperty(property = "objectId")
    private String id;
    private String idRadar;
    private int idCategoria;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRadar() {
        return idRadar;
    }

    public void setIdRadar(String idRadar) {
        this.idRadar = idRadar;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public RadarEscuchaCategoria() {
    }

    public RadarEscuchaCategoria(String idRadar, int idCategoria) {
        this.idRadar = idRadar;
        this.idCategoria = idCategoria;
    }
}
