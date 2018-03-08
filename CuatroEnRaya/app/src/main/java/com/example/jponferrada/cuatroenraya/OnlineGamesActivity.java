package com.example.jponferrada.cuatroenraya;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class OnlineGamesActivity extends AppCompatActivity {
    private Button buttonNewPartida;
    private RequestQueue queue;
    private ArrayList<String> partida;
    private static final String URLSERVER = "http://192.168.115.85";
    private ListView partidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_games);
        queue = VolleySingleton.getInstance(this).getmRequestQueue();
        partidas = (ListView) findViewById(R.id.ListViewPartidas);
        buttonNewPartida = (Button) findViewById(R.id.bNewPartida);
        mostrarJuegos();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mostrarJuegos();
            }
        },3000,5000);

        buttonNewPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = URLSERVER+"/CuatroEnRaya/start.php";
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                            DocumentBuilder db = dbf.newDocumentBuilder();
                            InputSource is = new InputSource();
                            is.setCharacterStream(new StringReader(response));
                            Document doc = db.parse(is);
                            NodeList nodes = doc.getElementsByTagName("game");
                            String idPartidaAJuegar = "";
                            for (int i = 0;i< nodes.getLength(); i++){
                                Element element = (Element) nodes.item(i);
                                idPartidaAJuegar = element.getAttribute("id");
                            }
                            Intent intent = new Intent(OnlineGamesActivity.this,CuatroEnRaya.class);
                            Bundle b = new Bundle();
                            b.putString("ID",idPartidaAJuegar);
                            b.putString("TURNO","1");

                            intent.putExtras(b);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Context context = getApplicationContext();
                        CharSequence text = "No se puede conectar con el servidor.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });

                queue.add(request);
            }
        });



    }
    private void mostrarJuegos(){
        partida = new ArrayList<String>();
        String url = URLSERVER+"/CuatroEnRaya/games.php";
        StringRequest request = new StringRequest(url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(response));
                    Document doc = db.parse(is);
                    NodeList nodes = doc.getElementsByTagName("game");
                    for (int i = 0;i< nodes.getLength(); i++){
                        Element element = (Element) nodes.item(i);
                        partida.add("Partida" + element.getAttribute("id")+"\n");
                    }
                    ArrayAdapter myAdapter = new ArrayAdapter(getApplicationContext(),R.layout.layout_partida,R.id.itemPartida,partida);
                    partidas.setAdapter(myAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context = getApplicationContext();
                CharSequence text = "No se pueden cargar las partidas";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        queue.add(request);
    }




}
