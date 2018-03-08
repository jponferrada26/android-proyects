package com.example.jponferrada.cuatroenraya;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {
    private Button btnOffline;
    private Button btnOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        btnOffline = (Button)findViewById(R.id.btn_offline);
        btnOnline = (Button)findViewById(R.id.btn_online);

        btnOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =
                        new Intent(Inicio.this, CuatroEnRaya.class);

                startActivity(intent);
            }
        });

        btnOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =
                        new Intent(Inicio.this, OnlineGamesActivity.class);

                startActivity(intent);
            }
        });
    }
}