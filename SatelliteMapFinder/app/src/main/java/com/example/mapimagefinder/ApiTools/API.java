package com.example.mapimagefinder.ApiTools;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    /**
     * Metodo estático para crear un objeto Retrofit para gestionar la conexión con una API.
     * @param baseUrl base de la url de la API.
     * @return Objeto Retrofit para manejar las peticiones y respuestas de la API.
     */

    public static Retrofit getAPI(String baseUrl){

        Retrofit.Builder constructor = new Retrofit.Builder();

        Retrofit api = constructor.baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return api;
    }

}
