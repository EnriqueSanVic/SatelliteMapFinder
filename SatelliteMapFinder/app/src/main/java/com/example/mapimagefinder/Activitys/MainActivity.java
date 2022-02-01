package com.example.mapimagefinder.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapimagefinder.Handlers.MainHandler;
import com.example.mapimagefinder.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //UI Elementos
    private EditText inLatitud;
    private EditText inLongitud;
    private EditText inDimension;

    private DatePicker inCalendario;

    private Button btnBuscar;

    private ImageView vistaimagen;

    private TextView setNasaId;
    private TextView setFecha;
    private TextView setDataSet;
    private TextView setPlaneta;

    //handler
    private MainHandler controlador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlador = new MainHandler(this);

        //se pone el icono en menubar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.satellite_icono);

        recogerElementos();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void recogerElementos() {

        //se configuran los elementos
        inLatitud = findViewById(R.id.inLatitud);
        inLongitud = findViewById(R.id.inLongitud);
        inDimension = findViewById(R.id.inDimension);

        inCalendario = findViewById(R.id.inCalendario);

        btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(controlador);

        vistaimagen = findViewById(R.id.vistaImagen);

        setNasaId = findViewById(R.id.setNasaId);
        setFecha = findViewById(R.id.setFecha);
        setDataSet = findViewById(R.id.setDataSet);
        setPlaneta = findViewById(R.id.setPlaneta);


    }

    //getters y setters
    public void setNasaId(String nasaId){
        setNasaId.setText(nasaId);
    }

    public void setFecha(String fecha){
        setFecha.setText(fecha);
    }

    public void setDataSet(String dataSet){
        setDataSet.setText(dataSet);
    }

    public void setPlaneta(String planeta){
        setPlaneta.setText(planeta);
    }

    public String getLatitud(){
        return inLatitud.getText().toString();
    }

    public String getLongitud(){
        return inLongitud.getText().toString();
    }

    public String getDimension(){
        return inDimension.getText().toString();
    }

    public String getFecha(){

        return (inCalendario.getYear() + 1) + "-"
                + inCalendario.getMonth() + "-"
                + inCalendario.getDayOfMonth();
    }

    public void setImage(String url){
        /*
        * Se usa la librería Picasso para facilitar el seteo de la imagen en el ImageView
        * */
        Picasso.get()
                .load(url)
                .fit()
                .into(vistaimagen);
    }

    //Toasts

    public void mensajeErrorConexion(){
        Toast.makeText(this, "Error de conexión.", Toast.LENGTH_SHORT).show();
    }

    public void mensajeNoHayImagen(){
        Toast.makeText(this, "No hay imágenes en la fecha escogida de dicha posición en el periodo de 30 días posteriores al escogido.", Toast.LENGTH_SHORT).show();
    }

    public void mensajeParametrosIncorrectos(){
        Toast.makeText(this, "Los párametros introducidos o el formato de los mismos no es correcto.", Toast.LENGTH_SHORT).show();
    }

}