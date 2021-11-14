package game.model;

import game.GameController;
import guis.PantallaGameOver;
import guis.PantallaPrincipal;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

import static game.factories.KeyBoard.DOWN_KEY;

public class Game {

    /**
     * Juego de La Caperucita Roja (Riding Hood)
     *
     * Consiste en recoger flores (Blossoms) de un tablero, el cual define un nivel, cuando el
     * tablero se completa (se recogen todas las flores) se pasa al siguiente tablero.
     *
     * Conforme va aumentando el nivel va aumentando la dificultad, añadiendo Elementos
     * hostiles (Bees, Flees, Spiders) que tienen un comportamiento activo y manipularan el tablero.
     *
     *
     * Bees: se mueven hacia la flor mas cercana y la recogen; si se cruzan con caperucita le resta
     * vida; y desaparece al llegar a un borde del tablero .
     * Aparecen en el segundo nivel (segundo tablero).
     *
     * Flees: se mueven de forma aleatoria por el tablero, y si se cruza con caperucita le resta
     * puntos y desaparece del tablero.
     * Aparecen en el primer nivel (primer tablero).
     *
     * Spiders: persiguen a caperucita y cuando se cruzan con ella le resta una vida a caperucita
     * y desaparece la Spider.
     * Aparecen en el tercer nivel (nivel BOSS).
     *
     * Los niveles se generan de forma infinita siguiendo el siguiente patron:
     * nivel1,nivel2,nivel3,nivel1,nivel2,nivel3,nivel1,nivel2,nivel3 etc
     *
     * Los Niveles se pueden almacenar y obtener en archivos .txt a partir de la clase FileUtilities u
     * obtener un ArrayList<Nivel> con varios niveles a partir de la clase NivelesFactory
     *
     *
     * En esta clase se almacena toda la informacion del juego y la logica del mismo.
     *
     */


    private final GameController gameController;
    private final PantallaPrincipal pantallaPrincipal;

    public final int STATE_RUNNING=123;
    public final int STATE_FINISH=291;
    public final int STATE_CONTINUE=91;
    public final int STATE_NEXT_LEVEL=1002;

    public final int LEVEL_RANDOM=213;
    public final int LEVEL_MEMORIA=21333;

    //Definicion de las constantes del teclado
    int lastKey = DOWN_KEY;

    //Variables del juego
    Nivel nivelActual;
    Nivel ultimoNivel;


    RidingHood ridingHood;
    int contadorNiveles=0;
    int contadorDificultad=1;
    String defaultGamePath = "src/main/resources/games/game.txt";
    String defaultScenarioPath="src/main/resources/games/def_scenario.txt";
    String defaultPath="src/main/resources/games/";
    PantallaPrincipal pantalla;
    PantallaGameOver pantallaGameOver;

    ArrayList<Nivel> nivelesDeArchivo;
    Iterator iteratorNivelesArchivo;


    /**
     * Unico constructor posible -
     *
     * @param gameController
     * @param pantallaPrincipal
     */
    public Game(GameController gameController, PantallaPrincipal pantallaPrincipal){
        this.gameController=gameController;
        this.pantallaPrincipal=pantallaPrincipal;





    }


    public void refrescarPantalla(){
        pantalla.pintarCanvas(nivelActual.getTableroItems());
        pantalla.getComportamientoAutomatico().setText(ridingHood.getEstadoControl());
    }

    /**
     * Cuando caperucita pierde todas sus vidas el juego se tiene que reiniciar y mostrar una pantalla de GAME OVER con las estadisticas de partida
     */
    public void restart(){

    }


    /**
     * Donde se procesa la logica de los objetos en las posiciones del tablero e interactuan entre ellos
     *
     * @return STATE_CONTINUE   -> estado normal, no ocurre nada anomalo
     *         STATE_NEXT_LEVEL -> estado sin blossoms, no quedan blossoms en el nivel, pasar a siguiente nivel
     */
    public int procesarCelda(){

        //TODO implementar procesar celda
        return 0;
    }




    public void nextIteration(){

    }


    public void setInnnerLimits(){


    }



    /**
     * Carga el siguiente tablero al grid
     *
     * @param indicaciones indica que tipo de tablero cargar
     *
     *
     */
    public void cargarSiguienteTablero(int indicaciones){
        switch (indicaciones){
            case LEVEL_MEMORIA -> {
                if(nivelesDeArchivo!=null && nivelesDeArchivo.size()!=0){

                }else{
                    System.out.println("No se ha seleccionado ningun archivo de tableros");
                }

                break;
            }
            case LEVEL_RANDOM -> {


                break;
            }
        }



    }




}
