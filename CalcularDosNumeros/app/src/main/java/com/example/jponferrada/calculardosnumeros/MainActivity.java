package com.example.jponferrada.calculardosnumeros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView numero1;
    TextView numero2;
    TextView textViewResultado;
    Button button;
    RadioButton radioSumar;
    RadioButton radioRestar;
    RadioButton radioMultiplicar;
    RadioButton radioDividir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero1 = (TextView) findViewById(R.id.editNumero1);
        numero2 = (TextView) findViewById(R.id.editNumero2);
        textViewResultado = (TextView) findViewById(R.id.resultado);
        button = (Button) findViewById(R.id.myButton);
        radioSumar = (RadioButton) findViewById(R.id.radioSumar);
        radioRestar = (RadioButton) findViewById(R.id.radioRestar);
        radioMultiplicar = (RadioButton) findViewById(R.id.radioMultiplicar);
        radioDividir = (RadioButton) findViewById(R.id.radioDividir);

        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if(numero1.getText().toString().equals("") || numero2.getText().toString().equals("")){
                    textViewResultado.setText("Resultado: "+ 0);
                }else{
                    double resultadoNumero1 = Double.valueOf(numero1.getText().toString());
                    double resultadoNumero2 = Double.valueOf(numero2.getText().toString());
                    double resultado;
                    if(radioSumar.isChecked()){
                        resultado = resultadoNumero1 + resultadoNumero2;
                    }else if(radioRestar.isChecked()){
                        resultado = resultadoNumero1 - resultadoNumero2;
                    }else if(radioMultiplicar.isChecked()){
                        resultado = resultadoNumero1 * resultadoNumero2;
                    }else{
                        resultado = resultadoNumero1 / resultadoNumero2;
                    }



                    textViewResultado.setText("Resultado: "+ resultado);
                }



            }
        });
    }

}
