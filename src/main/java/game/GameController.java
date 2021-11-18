package game;


import common.IGameObject;
import game.factories.GameFileManager;
import game.model.*;
import guis.PantallaGameOver;
import guis.PantallaPrincipal;
import views.GameCanvas;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static game.factories.KeyBoard.*;

/**
 * Controlador del juego caperucita
 *
 * Es el encargado de recibir eventos y controlar las acciones del juego.
 * Crea las pantallas necesarias.
 *
 * @author Antonio Gines Buendia Lopez
 */
public class GameController implements KeyListener, ActionListener {

    //Definimos el juego
    Game game;

    //Definicion de las constantes del teclado
    int lastKey = DOWN_KEY;

    //Definimos las rutas por defecto
    String defaultGamePath = "src/main/resources/games/game.txt";
    String defaultScenarioPath="src/main/resources/games/def_scenario.txt";
    String defaultPath="src/main/resources/games/";
    PantallaPrincipal pantalla;

    // Timer
    Timer timer;
    int tick = 200;





    /**
     * Inicializacion del Juego:
     * Se crean las vistas, tablero y manejadoresDeEventos
     *
     * @throws Exception
     */
    public GameController() throws Exception{
        //Creamos una pantalla principal
        pantalla=new PantallaPrincipal(this);
        pantalla.setGameView(GameCanvas.VISTA_SQUARE);

        //Creamos el modelo del juego
        game=new Game(this,pantalla);

        //Creamos un nuevo reloj que generara eventos temporales
        timer = new Timer(tick, this);

    }




    /**
     * Manejador de eventos producidos por la interfaz grafica y por el Timer
     * Se ejecutara cada tick del timer y cada vez que interactuemos con la interfaz
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==pantalla.getReiniciarJuego()) {
            game.restart();
            System.out.println("actionEvent->reiniciar()");

        }
        else if(actionEvent.getSource()==pantalla.getComportamientoAutomatico()){
            game.cambiarComportamientoCaperucita();
            System.out.println("actionEvent->comportamientoAutomatico");

        }
        else if(actionEvent.getSource()==pantalla.getGuardarDefault()){
            //Guardamos la partida actual
            game.guardarPartidaArchivo(defaultGamePath);
            System.out.println("actionEvent->guardarDefault");

        }
        else if(actionEvent.getSource()==pantalla.getCargarDefault()){
            //Cargamos desde el archivo por defecto de partida el ultimo tablero con caperucita y sus puntuaciones, vida, etc
            timer.stop();
            game.cargarPartidaArchivo(defaultGamePath);
            System.out.println("actionEvent->cargarDefault");
        }
        else if(actionEvent.getSource()==pantalla.getGuardarFiles()){
            //Guardamos la partida actual en un archivo que seleccionaremos desde una ventana JFileChoose
            JFileChooser fileChooser=new JFileChooser(defaultPath);
            int response=fileChooser.showSaveDialog(pantalla);
            if(response==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                String filePath=file.getPath();
                game.guardarPartidaArchivo(filePath);
                System.out.println("File Path: \""+filePath+"\"");
            }
            System.out.println("actionEvent->guardarFiles");

        }
        else if(actionEvent.getSource()==pantalla.getCargarFiles()) {
            //Cargamos una partida almacenada en un archivo que seleccionamos desde una ventana JFileChoose
            timer.stop();
            JFileChooser fileChooser=new JFileChooser(defaultPath);
            int response=fileChooser.showOpenDialog(pantalla);
            if(response==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                String filePath=file.getPath();
                game.cargarPartidaArchivo(filePath);
                System.out.println("File Path: \""+filePath+"\"");
            }
            System.out.println("actionEvent->cargarFiles");
        }
        else if(actionEvent.getSource()== pantalla.getGuardarTablero()){
            //Guardamos los elementos del tablero actual al archivo por defecto
            game.guardarTablero(defaultScenarioPath,Game.TABLERO_UNICO);
            System.out.println("actionEvent->guardarTablero");

        }
        else if(actionEvent.getSource()==pantalla.getCargarTablero()){
            //Cargamos los elementos del tablero y sustituimos caperucita que habia en el tablero por caperucita que tenemos en el juego
            timer.stop();
            game.cargarTablero(defaultScenarioPath,Game.TABLERO_UNICO);
            System.out.println("actionEvent->cargarTablero");
        }
        else if(actionEvent.getSource()==pantalla.getGuardarTableroArray()){
            //Guardamos la partida actual en un archivo que seleccionaremos desde una ventana JFileChoose
            JFileChooser fileChooser=new JFileChooser(defaultPath);
            int response=fileChooser.showSaveDialog(pantalla);
            if(response==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                String filePath=file.getPath();
                game.guardarTablero(filePath,Game.TABLERO_ARRAY);
                System.out.println("File Path: \""+filePath+"\"");
            }
            System.out.println("actionEvent->guardarFiles");

        }
        else if(actionEvent.getSource()==pantalla.getCargarTableroArray()) {
            //Cargamos una partida almacenada en un archivo que seleccionamos desde una ventana JFileChoose
            timer.stop();
            JFileChooser fileChooser=new JFileChooser(defaultPath);
            int response=fileChooser.showOpenDialog(pantalla);
            if(response==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                String filePath=file.getPath();
                game.guardarTablero(filePath,Game.TABLERO_ARRAY);
                System.out.println("File Path: \""+filePath+"\"");
            }
            System.out.println("actionEvent->cargarFiles");

        }
        else if(actionEvent.getSource()==pantalla.getVistaCuadrados()){
            pantalla.setGameView(GameCanvas.VISTA_SQUARE);
            System.out.println("actionEvent->vistaCuadrados");

        }
        else if(actionEvent.getSource()==pantalla.getVistaFigurasGeometricas()){
            pantalla.setGameView(GameCanvas.VISTA_FIGURES);
            System.out.println("actionEvent->vistaFigurasGeometricas");

        }
        else if(actionEvent.getSource()==pantalla.getVistaIconos()){
            pantalla.setGameView(GameCanvas.VISTA_ICONOS);
            System.out.println("actionEvent->vistaIconos");

        }
        else if(actionEvent.getSource()==timer){
            //Ejecutamos el proceso que controla la logica del juego
            game.nextIteration();
        }
        //Refrescamos la pantalla cada vez que se interactua con el juego o cambia alguno de sus componenetes
        game.refrescarPantalla();

    }

    /**
     * Manejador de los eventos al pulsar teclas que no son caracteres ASCII (teclas ASCII -> keyTyped())
     * En especifico, cada vez que pulsamos la tecla ESPACIO se pausa/reanuda el timer
     *
     * @param keyEvent evento que se produce al pulsar una tecla
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {

        lastKey=keyEvent.getKeyCode();


        if(lastKey==SPACE_KEY){
            if(timer.isRunning()){
                timer.stop();
                pantalla.pintarBarraMenuItemPAUSA("PAUSE");
                System.out.println("actionEvent->keyPressed(SPACE_KEY)->turnToPause");
            } else {
                timer.start();
                pantalla.pintarBarraMenuItemPAUSA("RUNNING");
                System.out.println("actionEvent->keyPressed(SPACE_KEY)->turnToRun");
            }
        }else if(lastKey==R_KEY){
            game.restart();
        }else{
            game.setLastKey(lastKey);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }


    public void restart(){
        timer.stop();

    }
}
