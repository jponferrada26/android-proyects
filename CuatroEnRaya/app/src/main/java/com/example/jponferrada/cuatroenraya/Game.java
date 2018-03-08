package com.example.jponferrada.cuatroenraya;

/**
 * Created by jponferrada on 8/11/17.
 */

public class Game {
    private int turno = 0;
    private static final int FILAS = 6;
    private static final int COLUMNAS = 7;
    private int[][] tablero = new int[FILAS][COLUMNAS];
    private static final String JUGADORGANADOR = "1111";
    private static final String MAQUINAGANADOR = "2222";
    private String ganador = "";

    public Game(int turno){
        iniciarTablero();
        cambiarTurno();
        setTurno(turno);
    }


    public int getTurno() {
        return turno;
    }

    int[][] getTablero() {
        return tablero;
    }

    void setTurno(int turno) {
        this.turno = turno;
    }


    private void iniciarTablero() {
        for (int i=0;i<tablero.length;i++){
            for (int j=0;j<tablero[0].length;j++){
                tablero[i][j] = 0;
            }
        }
    }

    boolean isVacio(int fila, int columna){
        if(tablero[fila][columna] == 0){
            return true;
        }
        return false;
    }

    /**
     * Cambiar de turno
     * @return Turno
     */
    int cambiarTurno(){
        /*if(getTurno() == 0){//Comprobar si es null el turno
            setTurno((int)(Math.round((Math.random()*(2-1)+1))));
        }
        */
        if(getTurno() == 1){
            setTurno(2);
            return getTurno();
        }else{
            setTurno(1);
            return getTurno();
        }

    }

    void insertarFicha(int fila, int columna,int turno){
        tablero[fila][columna] = turno;
    }

    boolean isGanado(int fila,int columna){
        if(comprobarFila(fila)){
            return true;
        }else if(comprobarColumna(columna)){
            return true;
        }else if(comprobarDiagonal(fila,columna)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Comprobar si han ganado la partida, comporbandolo con la fila
     * @param fila
     * @return
     */
    private boolean comprobarFila(int fila) {
        String cadena="";
        for (int i = 0; i < COLUMNAS; i++) {
            cadena += tablero[fila][i];
        }

        if(cadena.contains(JUGADORGANADOR)){
            setGanador("Usuario");
            return true;
        }else if(cadena.contains(MAQUINAGANADOR)){
            setGanador("Máquina");
            return true;
        }
        return false;
    }

    /**
     * Comprobar si han ganado la partida, comporbandolo con la columna
     * @param columna
     * @return
     */
    private boolean comprobarColumna(int columna) {
        String cadena="";
        for (int i = 0; i < FILAS; i++) {
            cadena += tablero[i][columna];

        }
        if(cadena.contains(JUGADORGANADOR)){
            setGanador("Usuario");
            return true;
        }else if(cadena.contains(MAQUINAGANADOR)){
            setGanador("Máquina");
            return true;
        }
        return false;
    }

    /**
     * Comprobar si han ganado la partida, comprobandolo con la fila y la columna para recorrer el tablero en diagonal
     * @param fila
     * @param columna
     * @return
     */
    private boolean comprobarDiagonal(int fila,int columna){
        String cadena = "";
        for (int i = fila, j = columna; i < FILAS && j >= 0; i++, j--)
            cadena += tablero[i][j];
        for (int i = fila-1, j = columna + 1; j < COLUMNAS && i >= 0; i--, j++)
            cadena = tablero[i][j] + cadena;

        if(cadena.contains(JUGADORGANADOR)){
            setGanador("Usuario");
            return true;
        }else if(cadena.contains(MAQUINAGANADOR)){
            setGanador("Máquina");
            return true;
        }
        return false;
    }

    private void setGanador(String ganador) {
        this.ganador = ganador;
    }

    String getGanador() {
        return ganador;
    }

    int[] getPulsacionMaquina(){
        int[] posicionPulsado = new int[2];
        posicionPulsado[0]=0;
        posicionPulsado[1]=0;
        String cadena = "";

        for (int i=0;i<FILAS;i++){
            for (int j=0;j<COLUMNAS;j++){
                cadena += tablero[i][j];
                if(cadena.contains("111")||cadena.contains("211") || cadena.contains("121")||cadena.contains("112")){
                    posicionPulsado[0] = (Math.min(i+1,FILAS));
                    posicionPulsado[1] = (Math.min(j+1,COLUMNAS));
                }
            }

        }
        return posicionPulsado;
    }

    String tableroToString() {
        String cadena = "";
        for (int i=0;i<FILAS;i++){
            for (int j=0;j<COLUMNAS;j++){
                cadena += tablero[i][j];
            }

        }
        return cadena;
    }

    void stringTotablero(String cadena) {
        int count=0;
        for (int i=0;i<FILAS;i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = Integer.parseInt(String.valueOf(cadena.charAt(count++)));
            }

        }
    }
}
