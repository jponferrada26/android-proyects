package com.example.jponferrada.practica2_Modifica_texto_evento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.jponferrada.segundoproyectoonclick.R.layout.activity_main);
        textView = (TextView) findViewById(com.example.jponferrada.segundoproyectoonclick.R.id.texto);
        button = (Button) findViewById(com.example.jponferrada.segundoproyectoonclick.R.id.myButton);
        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                textView.setText("Botón pulsado");
            }
        });

        button.setOnLongClickListener(new Button.OnLongClickListener(){
            public boolean onLongClick(View v){
                textView.setText("Pulsado demasiado tiempo");
                return true;
            }
        });


    }

}
