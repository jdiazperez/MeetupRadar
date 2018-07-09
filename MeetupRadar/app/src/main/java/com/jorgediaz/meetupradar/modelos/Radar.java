package com.jorgediaz.meetupradar.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Radar implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(objectId);
        parcel.writeString(nombre);
        parcel.writeDouble(latidud);
        parcel.writeDouble(longitud);
        parcel.writeInt(radio);
    }

    private void readFromParcel(Parcel in) {
        this.objectId = in.readString();
        this.nombre = in.readString();
        this.latidud = in.readDouble();
        this.longitud = in.readDouble();
        this.radio = in.readInt();

    }

    public Radar(Parcel in) {
        objectId = in.readString();
        nombre = in.readString();
        latidud = in.readDouble();
        longitud = in.readDouble();
        radio = in.readInt();
    }

    public static final Creator<Radar> CREATOR = new Creator<Radar>() {
        @Override
        public Radar createFromParcel(Parcel in) {
            return new Radar(in);
        }

        @Override
        public Radar[] newArray(int size) {
            return new Radar[size];
        }
    };
}
