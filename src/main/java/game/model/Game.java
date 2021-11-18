package game.model;

import common.IGameObject;
import game.GameController;
import game.factories.GameFileManager;
import game.factories.NivelesFactory;
import guis.PantallaGameOver;
import guis.PantallaPrincipal;

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
    private final PantallaPrincipal pantalla;

    public static final int STATE_RUNNING=123;
    public static final int STATE_FINISH=291;
    public static final int STATE_CONTINUE=91;
    public static final int STATE_NEXT_LEVEL=1002;

    public static final int LEVEL_RANDOM=213;
    public static final int LEVEL_MEMORIA=21333;

    public static final int TABLERO_UNICO=2131;
    public static final int TABLERO_ARRAY=21333;

    //Definicion de las constantes del teclado
    int lastKey = DOWN_KEY;

    //Variables del juego
    Nivel nivelActual;
    Nivel ultimoNivel;

    RidingHood ridingHood;
    int contadorNiveles=0;
    int contadorDificultad=1;

    ArrayList<Nivel> nivelesDeArchivo;
    Iterator iteratorNivelesArchivo;
    ArrayList<Nivel> nivelesJugados;
    int indicaciones=LEVEL_RANDOM;

    //Paneles de vista (posible reemplazable con diferente box size)
    public static final int CANVAS_WIDTH = 480;
    int boxSize = 40;


    //TODO implementar contadores estadisticos
    //Contadores para almacenar las estadisticas
    private int bees_cruzados;
    private int flees_muertos;
    private int spiders_muertos;
    private int blossoms_recogidos;
    private int cantidadDeVecesQueTeHasCruzadoConUnMuro;


    /**
     * Unico constructor posible -
     *
     * @param gameController
     * @param pantallaPrincipal
     */
    public Game(GameController gameController, PantallaPrincipal pantallaPrincipal){
        this.gameController=gameController;
        this.pantalla=pantallaPrincipal;

        //Inicializacion del juego
        nivelesJugados=new ArrayList<>();
        nivelActual=new Nivel();
        ridingHood=new RidingHood(new Position(0,0),1,1,nivelActual);
        nivelActual.setRidingHood(ridingHood);
        pantalla.pintarPanelEstadoCaperucita(ridingHood.toString());

        cargarSiguienteTablero();

    }


    public void refrescarPantalla(){
        pantalla.pintarCanvas(nivelActual.getTableroItems());
        pantalla.getComportamientoAutomatico().setText(ridingHood.getEstadoControl());
        pantalla.pintarPanelEstadoCaperucita(ridingHood.toString());
    }

    /**
     * Cuando caperucita pierde todas sus vidas el juego se tiene que reiniciar y mostrar una pantalla de GAME OVER con las estadisticas de partida
     */
    public void restart(){
        contadorDificultad=0;
        contadorNiveles=0;
        indicaciones=LEVEL_RANDOM;
        nivelActual=new Nivel(ridingHood=new RidingHood(new Position(0,0),1,1));
        ridingHood= nivelActual.getRidingHood();
        ultimoNivel=new Nivel(nivelActual);
        nivelesJugados.clear();
        nivelesJugados.add(ultimoNivel);
        cargarSiguienteTablero();
        gameController.restart();
    }


    /**
     * Donde se procesa la logica de los objetos en las posiciones del tablero e interactuan entre ellos
     *
     * @return STATE_CONTINUE   -> estado normal, no ocurre nada anomalo
     *         STATE_NEXT_LEVEL -> estado sin blossoms, no quedan blossoms en el nivel, pasar a siguiente nivel
     */
    public int procesarTablero(){
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
                    case "Wall" -> {
                        break;
                    }

                }



                refrescarPantalla();
                int numeroBlossom=nivelActual.getBlossomsArrayList().size();
                if (numeroBlossom==0) {
                    return STATE_FINISH;
                }
            }
        }
        return STATE_CONTINUE;
    }


    /**
     * Este metodo realiza la siguiente iteraccion del tablero: mueve objetos, comprueba estados, etc
     *
     */
    public void nextIteration(){
        //Guardamos la posicion de caperucita por si encuentra un obstaculo
        Position old=new Position(ridingHood.getPosition());

        //Caperucita se mueve
        nivelActual.getRidingHood().setNivel(nivelActual);
        nivelActual.getRidingHood().moveToNextPosition();

        if(insideWall(ridingHood,nivelActual.getWallsArrayList())){
            ridingHood.setPosition(old);
            //TODO comportamiento automatico evitar obstaculos
        }

        //Movemos el resto de elementos activos (Bees, Flees y Spiders)
        for(IGameObject igo:nivelActual.getActiveObjectsWithoutRidingHood()){
            igo.setIGameObjects(nivelActual.getTableroItems());
            igo.moveToNextPosition();
        }

        //Comprobamos si los elementos del tablero se han salido del tablero
        checkInnerLimmits();

        //Comprobamos si ha finalizado el nivel (no quedan flores por recoger)
        if (procesarTablero()==STATE_FINISH) {
            if(contadorNiveles==3){
                contadorDificultad++;
                contadorNiveles=Nivel.GAME_FLY_LEVEL;
            }else {
                contadorNiveles++;
            }
            nivelActual.getRidingHood().incLifes(1);
            cargarSiguienteTablero();
        }

        //Comprobamos si caperucita tiene mas de 1 vida, si tiene 0 o menos se acaba el juego
        if(nivelActual.getRidingHood().getLifes()<=0){
            restart();
            //TODO mostrar por pantalla datos de la partida y un boton para reiniciar desde partida predefinida o partida totalmente nueva
        }

    }


    /**
     * Este metodo comprueba si caperucita se encuentra con un obstaculo
     *
     * @param ridingHood
     * @param walls
     * @return
     */
    private boolean insideWall(RidingHood ridingHood, ArrayList<Wall> walls){
        for(Wall wall:walls){
            if(ridingHood.getPosition().isEqual(wall.getPosition())){
                return true;
            }
        }
        return false;
    }


    /**
     * Comprueba si los objetos del juego se encuentran dentro de los limites del tablero o si se han encontrado con un obstaculo
     * corrigiendo su posicion si es necesario
     *
     */
    public void checkInnerLimmits(){
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
     * Carga el siguiente tablero al grid
     *
     */
    public void cargarSiguienteTablero(){

        switch (indicaciones){
            case LEVEL_MEMORIA -> {
                if(nivelesDeArchivo!=null){
                    if(iteratorNivelesArchivo.hasNext()){
                        nivelActual=(Nivel) iteratorNivelesArchivo.next();
                        nivelActual.getTableroItems().removeIf(c->c instanceof RidingHood);
                        Nivel.setToBaseLevel(nivelActual);
                        Nivel.createHostileObjects(contadorNiveles,contadorDificultad,nivelActual);
                        nivelActual.addElement(ridingHood);
                    }else{
                        Nivel.createLevelBase(contadorDificultad, nivelActual);
                        Nivel.createHostileObjects(contadorNiveles,contadorDificultad,nivelActual);
                        nivelActual.addElement(ridingHood);
                    }
                }else{
                    System.out.println("No se ha seleccionado ningun archivo de tableros");
                }

                break;
            }
            case LEVEL_RANDOM -> {
                nivelActual=new Nivel();
                Nivel.createLevelBase(contadorDificultad, nivelActual);
                Nivel.createHostileObjects(contadorNiveles,contadorDificultad,nivelActual);
                nivelActual.addElement(ridingHood);
                break;
            }
        }

        //Guardamos el nivel actual a ultimo nivel y al array de niveles jugados
        ultimoNivel=new Nivel(nivelActual);
        nivelesJugados.add(ultimoNivel);

    }

    /**
     * Con este metodo los niveles generados seran a partir de memoria
     */
    public void generarTablerosDesdeArchivo(){
        indicaciones=LEVEL_MEMORIA;
    }

    /**
     * Con este metodo los niveles generados seran random
     */
    public void generarTablerosRandom(){
        indicaciones=LEVEL_RANDOM;
    }





    public void cambiarComportamientoCaperucita(){
        ridingHood.cambiarModoControl();
        pantalla.getComportamientoAutomatico().setText(ridingHood.getEstadoControl());
    }

    public void guardarPartidaArchivo(String filePath){
        GameFileManager.guardarPartida(ultimoNivel,contadorNiveles,contadorDificultad,filePath);
    }

    public void cargarPartidaArchivo(String filePath){
        Object[] objects=GameFileManager.cargarPartida(filePath);
        nivelActual=(Nivel) objects[0];
        ridingHood=nivelActual.getRidingHood();
        ridingHood.turnManual();
        contadorNiveles=(Integer)objects[1];
        contadorDificultad=(Integer) objects[2];
        ultimoNivel=new Nivel(nivelActual);
        generarTablerosRandom();

    }

    public void cargarTablero(String filePath, int tipoDeCargaDeTablero){
        switch (tipoDeCargaDeTablero){
            case TABLERO_ARRAY -> {
                nivelesDeArchivo= NivelesFactory.obtenerTableros(filePath);
                iteratorNivelesArchivo=nivelesDeArchivo.iterator();
                generarTablerosDesdeArchivo();
                break;
            }
            case TABLERO_UNICO -> {
                nivelActual=GameFileManager.cargarTablero(filePath);
                nivelActual.addElement(ridingHood);
                ultimoNivel=new Nivel(nivelActual);
                generarTablerosRandom();
            }

        }
        indicaciones=LEVEL_MEMORIA;
    }

    public void guardarTablero(String filePath, int tipoDeCargaDeTablero){
        switch (tipoDeCargaDeTablero){
            case TABLERO_ARRAY -> {
                NivelesFactory.guardarArrayDeTableros(nivelesJugados,filePath);
                break;
            }
            case TABLERO_UNICO -> {
                GameFileManager.guardarTablero(ultimoNivel,filePath);
            }

        }
    }

    public void setLastKey(int lastKey){
        this.lastKey=lastKey;
        if(nivelActual.getRidingHood()!=null){
            nivelActual.getRidingHood().setDirection(lastKey);
            pantalla.getComportamientoAutomatico().setText(nivelActual.getRidingHood().getEstadoControl());
        }
    }



}
