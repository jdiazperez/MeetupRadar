package com.jorgediaz.meetupradar.modelos;

public class RadarEscuchaCategoria {
    private String objectId;
    private String idRadar;
    private int idCategoria;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    @Override
    public String toString() {
        return "RadarEscuchaCategoria{" +
                "objectId='" + objectId + '\'' +
                ", idRadar='" + idRadar + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
