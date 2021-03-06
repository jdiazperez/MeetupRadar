package com.jorgediaz.meetupradar.modelos;


import android.os.Parcel;
import android.os.Parcelable;

import com.jorgediaz.meetupradar.rest.Event;

import java.util.Date;

public class Evento implements Parcelable {
    private String objectId;
    private String idMeetup;
    private String nombre;
    private double distanciaDesdePtoBusqueda;
    private int duracion;
    private String url;
    private int numPersonasApuntadas;
    private String foto;
    private String estado;
    private Date fechaComienzo;
    private String idDireccion;
    private String idGrupo;
    private int idCategoria;
    private double latitud;
    private double longitud;


    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getIdMeetup() {
        return idMeetup;
    }

    public void setIdMeetup(String idMeetup) {
        this.idMeetup = idMeetup;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDistanciaDesdePtoBusqueda() {
        return distanciaDesdePtoBusqueda;
    }

    public void setDistanciaDesdePtoBusqueda(double distanciaDesdePtoBusqueda) {
        this.distanciaDesdePtoBusqueda = distanciaDesdePtoBusqueda;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumPersonasApuntadas() {
        return numPersonasApuntadas;
    }

    public void setNumPersonasApuntadas(int numPersonasApuntadas) {
        this.numPersonasApuntadas = numPersonasApuntadas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public String getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(String idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

    public Evento() {
    }

    public Evento(Event eventoRetrofit) {
        this.idMeetup = eventoRetrofit.getId();
        this.nombre = eventoRetrofit.getName();
        this.distanciaDesdePtoBusqueda = eventoRetrofit.getDistance();
        this.duracion = eventoRetrofit.getDuration();
        this.url = eventoRetrofit.getEventUrl();
        this.numPersonasApuntadas = eventoRetrofit.getYesRsvpCount();
        this.foto = eventoRetrofit.getPhotoUrl();
        this.estado = eventoRetrofit.getStatus();
        this.fechaComienzo = new Date(eventoRetrofit.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(objectId);
        parcel.writeString(idMeetup);
        parcel.writeString(nombre);
        parcel.writeDouble(distanciaDesdePtoBusqueda);
        parcel.writeInt(duracion);
        parcel.writeString(url);
        parcel.writeInt(numPersonasApuntadas);
        parcel.writeString(foto);
        parcel.writeString(estado);
        parcel.writeString(idDireccion);
        parcel.writeString(idGrupo);
        parcel.writeInt(idCategoria);
        parcel.writeDouble(latitud);
        parcel.writeDouble(longitud);
    }

    public Evento(Parcel in) {
        objectId = in.readString();
        idMeetup = in.readString();
        nombre = in.readString();
        distanciaDesdePtoBusqueda = in.readDouble();
        duracion = in.readInt();
        url = in.readString();
        numPersonasApuntadas = in.readInt();
        foto = in.readString();
        estado = in.readString();
        idDireccion = in.readString();
        idGrupo = in.readString();
        idCategoria = in.readInt();
        latitud = in.readDouble();
        longitud = in.readDouble();
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };


}
