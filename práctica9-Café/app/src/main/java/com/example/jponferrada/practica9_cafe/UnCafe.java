package com.example.jponferrada.practica9_cafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UnCafe extends AppCompatActivity {
    TextView numberCaffe;
    Button buttonMas;
    Button buttonMenos;
    int numeroCafes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_cafe);
        numberCaffe = (TextView) findViewById(R.id.numCaffe);
        buttonMas = (Button) findViewById(R.id.buttonMas);
        buttonMenos = (Button) findViewById(R.id.buttonMenos);
        numeroCafes = 0;
        actualizarContador();

        buttonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masCaffe();
                actualizarContador();

            }
        });

        buttonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menosCoffe();
                actualizarContador();
            }
        });

    }

    /**
     * Actualizar el textVew con el contador de cafés
     */
    private void actualizarContador(){
        this.numberCaffe.setText(String.valueOf(this.numeroCafes));
    }

    /**
     * Añadir mas café
     */
    private void masCaffe(){
        this.numeroCafes++;
    }

    /**
     * Descontar café
     */
    private void menosCoffe(){
        if(this.numeroCafes>0){
            this.numeroCafes--;
        }
    }

}
