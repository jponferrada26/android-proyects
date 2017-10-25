package com.example.jponferrada.proyecto8merienda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    ImageView imageVew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.mybutton);
        textView = (TextView) findViewById(R.id.texto);
        imageVew  = (ImageView)findViewById(R.id.image);

        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                textView.setText("Estoy lleno");
                imageVew.setImageResource(R.drawable.sinburguer);

            }
        });

    }





}
