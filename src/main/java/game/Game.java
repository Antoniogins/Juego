package game;


import common.IGameObject;
import game.factories.FileUtilities;
import game.model.*;
import views.PantallaPrincipal;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import static game.factories.KeyBoard.*;

/**
 * @author Antonio Gines Buendia Lopez
 *
 *
 */
public class Game implements KeyListener, ActionListener {

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
     */



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
    PantallaPrincipal pantalla;

    // Timer
    Timer timer;
    int tick = 200;

    //Paneles de vista
    public static final int CANVAS_WIDTH = 480;
    int boxSize = 40;




    /**
     * Inicializacion del Juego:
     * Se crean las vistas, tablero y manejadoresDeEventos
     *
     * @throws Exception
     */
    public Game() throws Exception{

        //Inicializacion del juego
        nivelActual=new Nivel();
        ridingHood=new RidingHood(new Position(0,0),1,1,nivelActual);
        nivelActual.addElement(ridingHood);

        //Creamos una pantalla principal
        pantalla=new PantallaPrincipal(this);
        pantalla.pintarPanelEstadoCaperucita(ridingHood.toString());







        //Asignamos los manejadores de eventos que controlaran el comportamiento del juego
        timer = new Timer(tick, this);

        cargarSiguienteTablero(0);



    }




    /**
     * Manejador de eventos producidos por la interfaz grafica y por el Timer
     * Se ejecutara cada tick del timer y cada vez que interactuemos con la interfaz
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //TODO implementar requisitos eventos
        if(actionEvent.getSource()==pantalla.getReiniciarJuego()) {
            restart();
            System.out.println("actionEvent->reiniciar()");

        }
        else if(actionEvent.getSource()==pantalla.getComportamientoAutomatico()){
            ridingHood.cambiarModoControl();
            pantalla.getComportamientoAutomatico().setText(ridingHood.getModoControlString());
            System.out.println("actionEvent->comportamientoAutomatico");

        }


        else if(actionEvent.getSource()==pantalla.getGuardarDefault()){
            //Guardamos la partida actual
            GameFileManager.guardarPartida(ultimoNivel,contadorNiveles,contadorDificultad,defaultGamePath);
//            NivelesFactory.guardarArrayDeTableros(nivelesCargados,defaultGamePath);
            System.out.println("actionEvent->guardarDefault");

        }
        else if(actionEvent.getSource()==pantalla.getCargarDefault()){
            //Cargamos desde el archivo por defecto de partida el ultimo tablero con caperucita y sus puntuaciones, vida, etc
            Object[] objects=GameFileManager.cargarPartida(defaultGamePath);
            nivelActual=(Nivel) objects[0];
            ridingHood= nivelActual.getRidingHood();
            contadorNiveles=(Integer)objects[1];
            contadorDificultad=(Integer) objects[2];


//            nivelesCargadosDeMemoria=NivelesFactory.obtenerTableros(defaultGamePath);
//            nivelActual=nivelesCargadosDeMemoria.get(nivelesCargadosDeMemoria.size()-1);
//            ridingHood=nivelActual.getRidingHood();
            System.out.println("actionEvent->cargarDefault");







        }
        else if(actionEvent.getSource()==pantalla.getGuardarFiles()){
            pantalla.getFileChooserSave().showSaveDialog(pantalla.getGuardarFiles());
            File file=pantalla.getFileChooserSave().getSelectedFile();
            FileUtilities.writeConcurrentListToFile(nivelActual.getTableroItems(),defaultGamePath);
            System.out.println("File Path:"+file.getPath()+file.getName());
            System.out.println("actionEvent->guardarFiles");

        }
        else if(actionEvent.getSource()==pantalla.getCargarFiles()) {

            pantalla.getFileChooserLoad().showOpenDialog(pantalla.getCargarFiles());
            File file=pantalla.getFileChooserLoad().getSelectedFile();
            ConcurrentLinkedQueue<IGameObject> queue=FileUtilities.readConcurrentListFromFile(file.getPath());
            System.out.println("File Path:"+file.getPath()+file.getName());

            System.out.println("actionEvent->cargarFiles");

        }
        else if(actionEvent.getSource()==pantalla.getFileChooserSave()) {

            System.out.println("actionEvent->getFileChooserSave");

        }
        else if(actionEvent.getSource()==pantalla.getFileChooserLoad()) {

            System.out.println("actionEvent->getFileChooserLoad");









        }
        else if(actionEvent.getSource()== pantalla.getGuardarTablero()){
            //Guardamos los elementos del tablero actual al archivo por defecto
            GameFileManager.guardarTablero(ultimoNivel,defaultScenarioPath);
//            FileUtilities.writeConcurrentListToFile(ultimoNivel.getTableroItems(),defaultScenarioPath);
            System.out.println("actionEvent->guardarTablero");

        }
        else if(actionEvent.getSource()==pantalla.getCargarTablero()){
            //Cargamos los elementos del tablero y sustituimos caperucita que habia en el tablero por caperucita que tenemos en el juego
            nivelActual=GameFileManager.cargarTablero(defaultScenarioPath);
            nivelActual.setRidingHood(ridingHood);
            ultimoNivel=new Nivel(nivelActual);


//
//            nivelActual=new Nivel();
//            nivelActual.setTableroItems(FileUtilities.readConcurrentListFromFile(defaultScenarioPath));
//            nivelActual.setRidingHood(ridingHood);
            System.out.println("actionEvent->cargarTablero");

        }
        else if(actionEvent.getSource()==pantalla.getVistaCuadrados()){
            System.out.println("actionEvent->vistaCuadrados");

        }
        else if(actionEvent.getSource()==pantalla.getVistaFigurasGeometricas()){
            System.out.println("actionEvent->vistaFigurasGeometricas");

        }
        else if(actionEvent.getSource()==pantalla.getVistaIconos()){
            System.out.println("actionEvent->vistaIconos");

        }
        else if(actionEvent.getSource()==timer){
            //Ejecutamos el proceso que controla la logica del juego
            timerEventProcess();
        }


        //Se ejecuta cada vez que ocurre un evento
        //TODO pposible comportamiento de dificultad-> los objetos activos se mueven cada x ticks de timer excepto caperucita-> al pasar los niveles
        //la velocidad a la que se mueven





        //Actualizamos la vista con los objetos a cada iteraccion
        pantalla.pintarCanvas(nivelActual.getTableroItems());
        pantalla.pintarPanelEstadoCaperucita(nivelActual.getRidingHood().toString());

    }


    /**
     * Cuando caperucita pierde todas sus vidas el juego se tiene que reiniciar y mostrar una pantalla de GAME OVER con las estadisticas de partida
     */
    private void restart(){
        timer.stop();
        contadorDificultad=0;
        contadorNiveles=0;
        nivelActual=new Nivel(ridingHood=new RidingHood(new Position(0,0),1,1));
        ridingHood= nivelActual.getRidingHood();
        ultimoNivel=new Nivel(nivelActual);
        cargarSiguienteTablero(0);
    }


    private void timerEventProcess(){
        //Caperucita se mueve
        nivelActual.getRidingHood().setNivel(nivelActual);
        nivelActual.getRidingHood().moveToNextPosition();

        //Movemos el resto de elementos activos (Bees, Flees y Spiders9
        for(IGameObject igo:nivelActual.getActiveObjectsWithoutRidingHood()){
            igo.setIGameObjects(nivelActual.getTableroItems());
            igo.moveToNextPosition();
        }

        //Comprobamos si los elementos del tablero se han salido del tablero
        setInLimits();

        //Comprobamos si ha finalizado el nivel (no quedan flores por recoger)
        if (processCell() == 1) {
            if(contadorNiveles==3){
                nivelActual.getRidingHood().incLifes(1);
                contadorDificultad++;
                cargarSiguienteTablero(contadorNiveles=1);
            }else {
                contadorNiveles++;
                nivelActual.getRidingHood().incLifes(1);
                cargarSiguienteTablero(contadorNiveles);
            }
        }

        //Comprobamos si caperucita tiene mas de 1 vida, si tiene 0 o menos se acaba el juego
        if(ridingHood.getLifes()<=0){
            restart();
            //TODO mostrar por pantalla datos de la partida y un boton para reiniciar desde partida predefinida o partida totalmente nueva
        }

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
        if(nivelActual.getRidingHood()!=null){
            nivelActual.getRidingHood().setDirection(lastKey);
        }
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
            restart();
        }
    }














    /**
     * Comprueba los elementos que estan en los bordes.
     *
     * Si caperucita sale del tablero corrige su posicion
     * Si Bee sale del tablero lo elimina
     * Si Spider sale del tablero corrige su posicion
     * Si Fly sale del tablero corrige su posicion
     *
     */
    private void setInLimits() {
        int lastBox = (CANVAS_WIDTH / boxSize) - 1;

        for(IGameObject iGameObjectTemporal:nivelActual.getTableroItems()){
            if(iGameObjectTemporal!=null){
                Position position=iGameObjectTemporal.getPosition();
                if(iGameObjectTemporal instanceof Bee){
                    if(position.getX()<0 || position.getY()<0 || position.getX()>lastBox || position.getY()>lastBox){
                        nivelActual.getTableroItems().remove(iGameObjectTemporal);
                    }
                }else{
                    if(position.getX()<0){
                        position.setX(0);
                    }else if(position.getX()>lastBox){
                        position.setX(lastBox);
                    }else if(position.getY()<0){
                        position.setY(0);
                    }else if(position.getY()>lastBox){
                        position.setY(lastBox);
                    }
                }
            }

        }
    }





    /**
     * Donde se procesa la logica de los objetos en las posiciones del tablero e interactuan entre ellos
     *
     * @return 0 -> estado normal, no ocurre nada anomalo
     *         1 -> estado sin blossoms, no quedan blossoms en el nivel, pasar a siguiente nivel
     */
    private int processCell() {
        //Primero realizamos la accion de caperucita->si se encuentra en la misma posicion que un blossom lo recoge
        if (nivelActual.getTableroItems() != null) {
            for (IGameObject iGameObject : nivelActual.getTableroItems()) {
                String clase=iGameObject.getClass().getSimpleName();
                switch (clase){
                    case "Blossom"->{
                        //Primero comprobamos si el blossom se encuentra en la misma celda que caperucita
                        if (iGameObject.getPosition().isEqual(ridingHood.getPosition())) {

                            //Si el blossom esta en la misma celda que caperucita -> sumamos el valor de blossom al de caperucita, eliminamos el blossom
                            //y devolvemos a caperucita la nueva lista de objetos en el tablero (actualizada sin el blossom que acabamos de eliminar)
                            ridingHood.setValue(ridingHood.getValue() + iGameObject.getValue());
                            nivelActual.removeElement(iGameObject);

                        }
                        break;
                    }
                    case "Spider"->{
                        //Si la araña y caperucita se encuentran en la misma celda la araña le quita vidas a caperucitda y araña desaparece
                        if(iGameObject.getPosition().isEqual(ridingHood.getPosition())){
                            ridingHood.incLifes(-1);
                            nivelActual.removeElement(iGameObject);


                            break;
                        }
                    }
                    case "Bee"->{

                        //Comprobamos si la abeja se encuentra en la misma celda que un blossom
                        for(IGameObject blossomIted:nivelActual.getBlossomsArrayList()){
                            if(blossomIted.getPosition().isEqual(iGameObject.getPosition())){
                                nivelActual.getTableroItems().remove(blossomIted);
                            }
                        }


                        //Comprobamos si la abeja se encuentra en la misma posicion que caperucita
                        if(iGameObject.getPosition().isEqual(ridingHood.getPosition())){
                            //SI la abeja se encuentra en la misma posicion que caperucita le resta su valor a caperucita
                            ridingHood.setValue(ridingHood.getValue()-iGameObject.getValue());
                        }


                        break;
                    }
                    case "Fly"->{
                        //Si la mosca se encuentra en la misma posicion que caperucita le resta puntos a caperucita y la mosca desaparece
                        if(iGameObject.getPosition().isEqual(ridingHood.getPosition())){
                            ridingHood.setValue(ridingHood.getValue()-iGameObject.getValue());
                            nivelActual.getTableroItems().remove(iGameObject);
                        }
                    }

                }



                refrescarPantalla();
                int numeroBlossom=nivelActual.getBlossomsArrayList().size();
                if (numeroBlossom==0) {
                    return 1;
                }
            }
        }
        return 0;
    }


    private void refrescarPantalla(){
        pantalla.pintarCanvas(nivelActual.getTableroItems());
        pantalla.getComportamientoAutomatico().setText(ridingHood.getModoControlString());
    }







    /**
     * Carga el siguiente tablero al grid
     *
     * @param i indica cual de los tableros predefinidos cargar
     *        0 -> tablero inicial aleatorio de Blossoms
     *        1 -> tablero con 4 Blossoms y Flees
     *        2 -> tablero con 4 Blossoms y Bees
     *        3 -> tablero con 4 Blossoms y Spiders
     */
    private void cargarSiguienteTablero(int i) {
        //TODO implementar requisitos de niveles


        nivelActual=Nivel.generarNivelRandom(contadorNiveles,contadorDificultad,ridingHood);
        ultimoNivel=new Nivel(nivelActual);


//        if(escenariosDeMemoria){
//            if(iteratorNivelesCargadosDeMemoria.hasNext()){
//                nivelActual=(Nivel) iteratorNivelesCargadosDeMemoria.next();
//            }else{
//                //TODO mostrar una ventana pop-up ->se han acabado los escenarios cargados desde memoria-> generar nuevos escenarios aleatorios?
//            }
//        }else{
//            nivelActual=Nivel.generarNivelRandom(contadorNiveles,contadorDificultad,ridingHood);
//            nivelesCargados.add(new Nivel(nivelActual));
//        }
        refrescarPantalla();
    }







    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

}
