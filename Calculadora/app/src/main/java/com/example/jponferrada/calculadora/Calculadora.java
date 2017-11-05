package com.example.jponferrada.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity {
    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button bdiv;
    private Button bmulti;
    private Button bmenos;
    private Button bdel;
    private Button bcoma;
    private Button bmas;
    private Button bmasmenos;
    private Button bequals;
    private TextView resultado;
    private boolean isSelectbMas = false;
    private boolean isSelectbMenos = false;
    private boolean isSelectbMulti = false;
    private boolean isSelectbDiv = false;
    private boolean isSelectEquals = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b0 = (Button) findViewById(R.id.b0);
        bdiv = (Button) findViewById(R.id.bdiv);
        bmulti = (Button) findViewById(R.id.bmulti);
        bmenos = (Button) findViewById(R.id.bmenos);
        bdel = (Button) findViewById(R.id.bdel);
        bcoma = (Button) findViewById(R.id.bcoma);
        bmas = (Button) findViewById(R.id.bmas);
        bmasmenos = (Button) findViewById(R.id.bmasmenos);
        bequals = (Button) findViewById(R.id.bequals);
        resultado = (TextView) findViewById(R.id.resultado);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResultado("0");


            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResultado("1");

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResultado("2");


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addResultado("3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResultado("4");

            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResultado("5");

            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResultado("6");

            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResultado("7");

            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResultado("8");

            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResultado("9");

            }
        });

        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(isSelectbMas || isSelectbMenos || isSelectbMulti) && resultado.getText().toString().length() > 0) {
                    if(isCalculo(resultado.getText().toString())){
                        resultado.setText(String.valueOf(calcularResultado()));
                        addResultado("/");
                    }else{
                        if(!isSelectbMulti){
                            addResultado("/");
                            isSelectbDiv = true;
                        }

                    }
                }

            }
        });

        bmulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(isSelectbMas || isSelectbMenos || isSelectbDiv) && resultado.getText().toString().length() > 0) {
                    if(isCalculo(resultado.getText().toString())){
                        resultado.setText(String.valueOf(calcularResultado()));
                        addResultado("*");
                    }else{
                        if(!isSelectbMulti){
                            addResultado("*");
                            isSelectbMulti = true;
                        }

                    }
                }



            }
        });

        bmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(isSelectbMas || isSelectbMulti || isSelectbDiv) && resultado.getText().toString().length() > 0) {
                    if(isCalculo(resultado.getText().toString())){
                        resultado.setText(String.valueOf(calcularResultado()));
                        addResultado("-");
                    }else{
                        if(!isSelectbMenos){
                            addResultado("-");
                            isSelectbMenos = true;
                        }

                    }
                }
            }
        });

        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCaracter();
                if (isNumeroLimpio(resultado.getText().toString()))
                    pushEquals();
            }
        });

        bdel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                resultado.setText("");
                if (isNumeroLimpio(resultado.getText().toString()))
                    pushEquals();
                return false;
            }
        });

        bcoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resultado.getText().toString().length() > 0) {
                    addResultado(".");
                }

            }
        });

        bmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(isSelectbMenos || isSelectbMulti || isSelectbDiv) && resultado.getText().toString().length() > 0) {
                    if(isCalculo(resultado.getText().toString())){
                        resultado.setText(String.valueOf(calcularResultado()));
                        addResultado("+");
                    }else{
                        if(!isSelectbMas){
                            addResultado("+");
                            isSelectbMas = true;
                        }

                    }

                }


            }
        });

        bmasmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addResultado("-");
            }
        });

        bequals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setText(String.valueOf(calcularResultado()));
                pushEquals();
                isSelectEquals = true;
            }
        });


    }

    /*
     * Poner todos los botones a false.
     */
    private void pushEquals() {
        isSelectbMas = false;
        isSelectbMenos = false;
        isSelectbMulti = false;
        isSelectbDiv = false;
    }


    private boolean isCalculo(String calculo) {
        return calculo.matches("([-]?\\d\\.?)+([\\*]|[\\+]|[-]|[\\/])([-]?\\d\\.?)+");
    }

    private void addResultado(String numeroadd) {
        this.resultado.setText(this.resultado.getText().toString() + numeroadd);
    }

    private Double calcularResultado() {
        try{
            if (isCalculo(resultado.getText().toString())) {
                double numero1 = 0;
                double numero2 = 0;
                if(isResta(resultado.getText().toString()) && resultado.getText().toString().matches("([-](\\d+)[-][-](\\d+))")){// (-numero- -numero)
                    numero1 = Double.valueOf(resultado.getText().toString().split("([-](?=[-]\\d+))")[0]);
                    numero2 = Double.valueOf(resultado.getText().toString().split("([-](?=[-]\\d+))")[1]);
                }else if(isResta(resultado.getText().toString()) && resultado.getText().toString().matches("([-](\\d+)[-](\\d+))")){// (-numero - numero)
                    numero1 = Double.valueOf(resultado.getText().toString().split("(([-])(?=\\d+$))")[0]);
                    numero2 = Double.valueOf(resultado.getText().toString().split("(([-])(?=\\d+$))")[1]);
                }else if(isResta(resultado.getText().toString()) && resultado.getText().toString().matches("(\\d+[-][-](\\d+))")){
                    numero1 = Double.valueOf(resultado.getText().toString().split("([-](?=[-]\\d+))")[0]);
                    numero2 = Double.valueOf(resultado.getText().toString().split("([-](?=[-]\\d+))")[1]);
                }else{
                    numero1 = Double.valueOf(resultado.getText().toString().split("[\\*]|[\\+]|[-]|[\\/]")[0]);
                    numero2 = Double.valueOf(resultado.getText().toString().split("[\\*]|[\\+]|[-]|[\\/]")[1]);

                }
                if (isSuma(resultado.getText().toString()))
                    return numero1 + numero2;
                else if (isResta(resultado.getText().toString()))
                    return numero1 - numero2;
                else if (isMultiplicacion(resultado.getText().toString()))
                    return numero1 * numero2;
                else if (isDivision(resultado.getText().toString()))
                    return numero1 / numero2;
            }
            return Double.valueOf(resultado.getText().toString().split("[\\*]|[\\+]|[-]|[\\/]")[0]);
        }catch(Exception e){
            return 0.0;
        }
    }

    private boolean isNumeroLimpio(String calculo) {
        return calculo.matches("-(\\d\\.?)+|(\\d\\.?)+|\\w");
    }

    private boolean isSuma(String calculo) {
        return calculo.matches("([-]?\\d\\.?)+([\\+])([-]?\\d\\.?)+");
    }

    private boolean isResta(String calculo) {
        return calculo.matches("([-]?\\d\\.?)+([-])([-]?\\d\\.?)+");
    }

    private boolean isMultiplicacion(String calculo) {
        return calculo.matches("([-]?\\d\\.?)+([\\*])([-]?\\d\\.?)+");
    }

    private boolean isDivision(String calculo) {
        return calculo.matches("([-]?\\d\\.?)+([\\/])([-]?\\d\\.?)+");
    }

    private void deleteCaracter() {
        if (resultado.getText().toString().length() > 0) {
            resultado.setText(resultado.getText().toString().substring(0, resultado.getText().toString().length() - 1));
        }
    }


}
