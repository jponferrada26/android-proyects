package com.example.jponferrada.cuatroenraya;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CuatroEnRaya extends AppCompatActivity {
    private int[][] arrayId = {{R.id.boton00, R.id.boton01, R.id.boton02, R.id.boton03, R.id.boton04, R.id.boton05, R.id.boton06},
            {R.id.boton10, R.id.boton11, R.id.boton12, R.id.boton13, R.id.boton14, R.id.boton15, R.id.boton16},
            {R.id.boton20, R.id.boton21, R.id.boton22, R.id.boton23, R.id.boton24, R.id.boton25, R.id.boton26},
            {R.id.boton30, R.id.boton31, R.id.boton32, R.id.boton33, R.id.boton34, R.id.boton35, R.id.boton36},
            {R.id.boton40, R.id.boton41, R.id.boton42, R.id.boton43, R.id.boton44, R.id.boton45, R.id.boton46},
            {R.id.boton50, R.id.boton51, R.id.boton52, R.id.boton53, R.id.boton54, R.id.boton55, R.id.boton56}};
    private Game game;
    private View view;
    private RequestQueue queue;
    private String idPartidaOnline = "";
    private String idTurnoOnline = "";
    private static final String URLSERVIDOR = "http://192.168.115.244";
    private int turnoTemporal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuatro_en_raya);
        queue = VolleySingleton.getInstance(this).getmRequestQueue();
        Intent intent = getIntent();

        if(!intent.hasExtra("ID")){
            int turno = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("empiezaPartida","1"));
            game = new Game(turno);
            view = new View(getApplicationContext());
            if(game.getTurno() == 2) {
                //Forzar pulsacion Maquina
                view.setId(arrayId[0][(int) Math.round(Math.random() * (6 - 0))]);
                pulsarFicha(view);
            }
        }else{
            this.idPartidaOnline = intent.getStringExtra("ID");
            this.idTurnoOnline = intent.getStringExtra("TURNO");
            game = new Game(Integer.valueOf(idTurnoOnline));
        }

    }


    private void setTurnoOnline(){
        String url = URLSERVIDOR + "/CuatroEnRaya/changeTurno.php?idPartida="+this.idPartidaOnline+"&nuevoTurno="+((getTurnoOnline() == 1)?2:1);
        StringRequest request = new StringRequest(url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                game.setTurno(((getTurnoOnline() == 1)?2:1));

                Context context = getApplicationContext();
                CharSequence text = "Cambio de turno a "+game.getTurno();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context = getApplicationContext();
                CharSequence text = "Error al cambiar de turno";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        queue.add(request);
    }

    private int getTurnoOnline(){
        String url = URLSERVIDOR + "/CuatroEnRaya/getTurno.php?id="+this.idPartidaOnline;
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
                    Element element = (Element) nodes.item(0);
                    turnoTemporal = Integer.parseInt(element.getAttribute("turno"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context = getApplicationContext();
                CharSequence text = "No se ha podido obtener turno.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        queue.add(request);
        Log.d("turno",""+turnoTemporal);
        return turnoTemporal;
    }

    @Override
    protected void onResume() {
        if(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("musicaFondo",true))
            Music.playMusic(CuatroEnRaya.this,R.raw.musicimotion);
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("musicaFondo",true))
            Music.pauseMusic();
        super.onPause();
    }

    private int[] getPosicionPulsado(int id){
        int[] posicion  =  new int [2];
        for (int i=0;i<arrayId.length;i++){
            for (int j=0;j<arrayId[0].length;j++){
                if(id == arrayId[i][j]){
                    posicion[0] = i;
                    posicion[1] = j;
                    return posicion;
                }
            }
        }
        return posicion;
    }

    private void mostrarDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getWindow().getDecorView().getContext());
        builder.setMessage(msg);

        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.exit(0);
            }
        });
        builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        builder.show();
    }

    private void comprobarSiHaGanado(int fila, int columna){
        if(game.isGanado(fila,columna)){
            mostrarDialog("Ha ganado "+game.getGanador());
        }
    }

    public void pulsarFicha(View v) {
        if(!getIntent().hasExtra("ID")) {
            int[] posicionPulsado = getPosicionPulsado(v.getId());//posiciones del hueco pulsado
            int idHuecoVacio = 0;//id del hueco vacío.
            int filaFichaVacia = 0;//fila de la ficha que está vacía
            for (int i = arrayId.length - 1; i >= 0; i--) {//recorre desde abajo hasta arriba el tablero pero sólo la columna del que ha pulsado.
                if (game.isVacio(i, posicionPulsado[1])) {//comprobar si está vacío el hueco del tablero
                    idHuecoVacio = arrayId[i][posicionPulsado[1]];
                    filaFichaVacia = i;
                    break;
                }
            }

            ImageView image;
            switch (game.getTurno()) {//turno
                case 1:
                    if (game.isVacio(filaFichaVacia, posicionPulsado[1])) {//comprobar si el hueco de la ficha está vacía
                        image = (ImageView) findViewById(idHuecoVacio);
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                            image.setImageResource(R.drawable.circlegreenland);
                        else
                            image.setImageResource(R.drawable.circlegreen);
                        game.cambiarTurno();
                        game.insertarFicha(filaFichaVacia, posicionPulsado[1], 1);
                        comprobarSiHaGanado(filaFichaVacia, posicionPulsado[1]);

                        //Forzar pulsacion Maquina
                        view.setId(arrayId[0][(int) Math.round(Math.random() * (6 - 0))]);
                        pulsarFicha(view);

                    }
                    break;
                case 2:
                    if (game.isVacio(filaFichaVacia, posicionPulsado[1])) {//comprobar si el hueco de la ficha está vacía
                        image = (ImageView) findViewById(idHuecoVacio);
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                            image.setImageResource(R.drawable.circleredland);
                        else
                            image.setImageResource(R.drawable.circlered);

                        game.cambiarTurno();
                        game.insertarFicha(filaFichaVacia, posicionPulsado[1], 2);//insertar en el array del juego, el turno que tiene que le ha insertado al ficha.
                        comprobarSiHaGanado(filaFichaVacia, posicionPulsado[1]);
                    }
                    break;

            }
        }else{
            Context context1 = getApplicationContext();
            CharSequence text1 = "El turno es"+ getTurnoOnline()+" y el otro es "+game.getTurno();
            int duration1 = Toast.LENGTH_SHORT;

            Toast toast1 = Toast.makeText(context1, text1, duration1);
            toast1.show();

            if(getTurnoOnline() == game.getTurno()){
                setTurnoOnline();
            }else{
                Context context = getApplicationContext();
                CharSequence text = "No es tu turno";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        }


    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("tablero",game.tableroToString());
        savedInstanceState.putInt("turno",game.getTurno());

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        game.setTurno(savedInstanceState.getInt("turno"));
        game.stringTotablero(savedInstanceState.getString("tablero"));
        drawTablero();
    }

    private void drawTablero() {
        ImageView image;
        for (int i=0;i<game.getTablero().length;i++){
            for (int j=0;j<game.getTablero()[0].length;j++){
                image = (ImageView) findViewById(arrayId[i][j]);
                if (game.getTablero()[i][j]==1){
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                        image.setImageResource(R.drawable.circlegreenland);
                    else
                        image.setImageResource(R.drawable.circlegreen);

                }else if(game.getTablero()[i][j]==2){
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                        image.setImageResource(R.drawable.circleredland);
                    else
                        image.setImageResource(R.drawable.circlered);
                }else{
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                        image.setImageResource(R.drawable.circlewhiteland);
                    else
                        image.setImageResource(R.drawable.circlewhite);
                }

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem opcionMenu) {
        Intent sendIntent;
        switch (opcionMenu.getItemId()){
            case R.id.acercaDe:
                startActivity(new Intent(CuatroEnRaya.this, AcercaDe.class));
                break;
            case R.id.enviarMensaje:
                sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hola soy la App Cuatro en raya");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.ajustes:
                startActivity(new Intent(CuatroEnRaya.this, AjustesPreference.class));
                break;
        }
        return true;
    }

}
