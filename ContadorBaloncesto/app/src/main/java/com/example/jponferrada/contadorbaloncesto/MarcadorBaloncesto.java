package com.example.jponferrada.contadorbaloncesto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.FragmentTransaction.TRANSIT_ENTER_MASK;

public class MarcadorBaloncesto extends AppCompatActivity {
    TextView contadorVisitante;
    TextView contadorLocal;
    Button buttonComprobarpartido;
    Button buttonMas1Visitante;
    Button buttonMas2Visitante;
    Button buttonMas3Visitante;
    Button buttonMas1Local;
    Button buttonMas2Local;
    Button buttonMas3Local;
    Button bmenosV;
    Button bmenosL;
    Button bReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcador_baloncesto);
        contadorVisitante = (TextView) findViewById(R.id.ContadorVisitante);
        contadorLocal = (TextView) findViewById(R.id.ContadorLocal);
        buttonComprobarpartido = (Button) findViewById(R.id.bComprobarpartido);
        buttonMas1Visitante = (Button) findViewById(R.id.bmas1V);
        buttonMas2Visitante = (Button) findViewById(R.id.bmas2V);
        buttonMas3Visitante = (Button) findViewById(R.id.bmas3V);
        buttonMas1Local = (Button) findViewById(R.id.bmas1L);
        buttonMas2Local = (Button) findViewById(R.id.bmas2L);
        buttonMas3Local = (Button) findViewById(R.id.bmas3L);
        bmenosV = (Button) findViewById(R.id.bmenosV);
        bmenosL = (Button) findViewById(R.id.bmenosL);
        bReset = (Button) findViewById(R.id.bReset);


        buttonMas1Visitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePointsVis(1);
            }
        });

        buttonMas2Visitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePointsVis(2);
            }
        });

        buttonMas3Visitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePointsVis(3);
            }
        });

        buttonMas1Local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePointsLoc(1);
            }
        });

        buttonMas2Local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePointsLoc(2);
            }
        });

        buttonMas3Local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePointsLoc(3);
            }
        });
        bmenosV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.valueOf(contadorVisitante.getText().toString())>0)
                    contadorVisitante.setText(String.valueOf(Integer.valueOf(contadorVisitante.getText().toString())-1));
            }
        });
        bmenosL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.valueOf(contadorLocal.getText().toString())>0)
                    contadorLocal.setText(String.valueOf(Integer.valueOf(contadorLocal.getText().toString())-1));
            }
        });

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("¿Estás seguro que quieres empezar otro partido (se borraran los contadores)?");

                builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                contadorVisitante.setText("0");
                                contadorLocal.setText("0");
                            }
                        });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog dialog = builder.create();
                builder.show();


            }
        });



        buttonComprobarpartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = getGanador();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });



    }

    private String getGanador(){
        int contadorL = Integer.valueOf(contadorLocal.getText().toString());
        int contadorV = Integer.valueOf(contadorVisitante.getText().toString());

        if(contadorL>contadorV)
            return "Está ganando el equipo local";
        else if(contadorL<contadorV)
            return "Está ganando el equipo visitante";
        else
            return "Van empate";

    }


    /**
     *  Sumar puntos al contador visitante
     * @param asumar
     */
    private void scorePointsVis(int asumar){
        this.contadorVisitante.setText(String.valueOf(Integer.valueOf(this.contadorVisitante.getText().toString())+asumar));
    }

    /**
     *  Sumar puntos al contador local
     * @param asumar
     */
    private void scorePointsLoc(int asumar){
        this.contadorLocal.setText(String.valueOf(Integer.valueOf(this.contadorLocal.getText().toString())+asumar));
    }
}
