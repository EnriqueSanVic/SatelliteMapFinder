package com.example.mapimagefinder.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto del modelo preparado para ser parseado de un Json con Gson
 */
public class NasaEarthAsset {

    @SerializedName("date")
    private String fecha;

    @SerializedName("id")
    private String id;

    @SerializedName("resource")
    private NasaEarthResource fuenteDatos;

    @SerializedName("service_version")
    private String versionServicio;

    @SerializedName("url")
    private String urlImagen;

    public NasaEarthAsset(String fecha, String id, NasaEarthResource fuenteDatos, String versionServicio, String urlImagen) {
        this.fecha = fecha;
        this.id = id;
        this.fuenteDatos = fuenteDatos;
        this.versionServicio = versionServicio;
        this.urlImagen = urlImagen;
    }


    public String getFecha() {
        return fecha;
    }

    public String getId() {
        return id;
    }

    public NasaEarthResource getFuenteDatos() {
        return fuenteDatos;
    }

    public String getVersionServicio() {
        return versionServicio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    @Override
    public String toString() {
        return "NasaEarthAsset{" +
                "fecha='" + fecha + '\'' +
                ", id='" + id + '\'' +
                ", fuenteDatos=" + fuenteDatos +
                ", versionServicio='" + versionServicio + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                '}';
    }
}
