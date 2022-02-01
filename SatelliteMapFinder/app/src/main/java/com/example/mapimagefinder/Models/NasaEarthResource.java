package com.example.mapimagefinder.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto del modelo preparado para ser parseado de un Json con Gson
 */
public class NasaEarthResource {

    @SerializedName("dataset")
    private String dataSet;

    @SerializedName("planet")
    private String planeta;

    public NasaEarthResource(String dataSet, String planeta) {
        this.dataSet = dataSet;
        this.planeta = planeta;
    }

    public String getDataSet() {
        return dataSet;
    }

    public String getPlaneta() {
        return planeta;
    }

    @Override
    public String toString() {
        return "NasaEarthResource{" +
                "dataSet='" + dataSet + '\'' +
                ", planeta='" + planeta + '\'' +
                '}';
    }
}
