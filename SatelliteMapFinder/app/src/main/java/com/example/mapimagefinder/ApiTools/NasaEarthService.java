package com.example.mapimagefinder.ApiTools;

import com.example.mapimagefinder.Models.NasaEarthAsset;
import com.example.mapimagefinder.R;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaEarthService {

    //interfaz para definir las peticiones a la API.
    @GET("planetary/earth/assets")
    Call<NasaEarthAsset> getAsset(@Query("api_key") String apiKey, @Query("lat") float latitud, @Query("lon") float longitud, @Query("date") String fecha, @Query("dim") float dimension);

}
