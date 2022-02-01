package com.example.mapimagefinder.Handlers;


import android.view.View;

import com.example.mapimagefinder.Activitys.MainActivity;
import com.example.mapimagefinder.ApiTools.API;
import com.example.mapimagefinder.ApiTools.NasaEarthService;
import com.example.mapimagefinder.Models.NasaEarthAsset;
import com.example.mapimagefinder.R;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainHandler implements View.OnClickListener {

    //vista
    private MainActivity vista;

    public MainHandler(MainActivity vista) {
        this.vista = vista;
    }

    /**
     * Escuchador del onClick de la vista.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnBuscar:
                buscar();
                break;
        }

    }

    /**
     * Metodo para verificar los parametros de entrada realizar
     * la busqueda con la api si son correctos.
     */
    private void buscar() {

        float latitud=0, longitud=0, dimension=1;
        boolean parametrosCorrectos = true;

        try {
            latitud = Float.valueOf(vista.getLatitud());
            longitud = Float.valueOf(vista.getLongitud());
            dimension = Float.valueOf(vista.getDimension());
        }catch(NumberFormatException ex){
            parametrosCorrectos = false;
        }

        String fecha = vista.getFecha();

        if(parametrosCorrectos){
            hacerPeticion(latitud, longitud, fecha, dimension);
        }else{

        }


    }


    /**
     * Método para hacer la petición a la API NAsa Earth
     * @param latitud coordenada latitud (formato xx.xxxxxx)
     * @param longitud coordenada longitud (formato xx.xxxxxx)
     * @param fecha fecha en la que se quiere realizar la búsqueda (formato yyyy-MM-dd)
     * @param dimension dimensión del alto y ancho de la imagen expresada en grados (formato x.xx)
     */
    private void hacerPeticion(float latitud, float longitud, String fecha, float dimension){

        //se crea el objeto interfaz del servicio con Retrofit
        NasaEarthService servicio =
                API.getAPI(vista.getResources().getString(R.string.nasa_base_url))
                        .create(NasaEarthService.class);

        //se configura la petición
        Call<NasaEarthAsset> peticion = servicio.getAsset(
                vista.getResources().getString(R.string.api_key),
                latitud,
                longitud,
                fecha,
                dimension
        );

        //se lanza la petición y se escucha la respuesta.
        peticion.enqueue(new Callback<NasaEarthAsset>() {
            //método para la respuesta correcta
            @Override
            public void onResponse(Call<NasaEarthAsset> call, Response<NasaEarthAsset> response) {

                /*
                Si el cuerpo de la petición parseado a json con el objeto NasaEarthAsset existe
                 es decir que la estructura de la espuesta es la esperada, se setean los campos en el activity.
                */

                if(response.body() != null){
                    setearElementos(response.body());

                /*
                Si la respuesta no es la esperada significa que el servidor no ha encontrado una imagen satelital que cumpla
                con los parámetros de la consulta y se manda un mensaje de imagen no encontrada en la interfaz de usuario.
                * */
                }else{
                    vista.mensajeNoHayImagen();
                }

            }

            //método para la respuesta fallida
            @Override
            public void onFailure(Call<NasaEarthAsset> call, Throwable t) {
                //si falla la respuesta se manda a la interfaz de usuario un mensaje de error de conexión.
                vista.mensajeErrorConexion();
            }
        });

    }

    private void setearElementos(NasaEarthAsset asset){

        //primero la imagen por que siendo una url que tiene que ser procesada asincronamente por picaso hay que empezar cuanto antes
        vista.setImage(asset.getUrlImagen());

        vista.setNasaId(asset.getId());

        vista.setFecha(asset.getFecha());

        vista.setDataSet(asset.getFuenteDatos().getDataSet());

        vista.setPlaneta(asset.getFuenteDatos().getPlaneta());

    }
}
