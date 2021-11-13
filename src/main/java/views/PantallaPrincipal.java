package views;

import game.Game;
import common.IGameObject;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PantallaPrincipal extends JFrame {
    ConcurrentLinkedQueue<IGameObject> iGameObjectConcurrentLinkedQueue;
    Game controladorDelJuego;

    //Paneles de vista
    public static final int CANVAS_WIDTH = 480;
    int boxSize = 40;
    int row, col;
    GameCanvas canvas;
    JPanel canvasFrame;
    JLabel panelEstadoCaperucita,
            panelPausa;
    JMenuBar barraMenu;
    JMenu menuFiles,
            menuTablero,
            menuVistas;
    JButton comportamientoAutomatico;
    JMenuItem cargarDefault,
            guardarDefault,
            cargarFiles,
            guardarFiles,
            cargarTablero,
            guardarTablero,
            vistaIconos,
            vistaCuadrados,
            vistaFigurasGeometricas,
            reiniciarJuego;
    JFileChooser fileChooserSave;
    JFileChooser fileChooserLoad;


    public PantallaPrincipal(Game game){
        super("Juego de la Caperucita");
        controladorDelJuego=game;

        //Creamos el panel donde se mostrara el juego en si (caperucita, flees, bees, etc)
        canvas=new GameCanvas(CANVAS_WIDTH,boxSize);
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH,CANVAS_WIDTH));
        canvas.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        canvasFrame=new JPanel();
        canvasFrame.setPreferredSize(new Dimension(CANVAS_WIDTH,CANVAS_WIDTH));
        canvasFrame.add(canvas);

        //Borde inferior con informacion sobre caperucita
        panelEstadoCaperucita=new JLabel();
        panelEstadoCaperucita.setHorizontalAlignment(SwingConstants.CENTER);
        panelEstadoCaperucita.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        //Items de la barra de menu
        cargarDefault=new JMenuItem("Cargar \"Partida por defecto\"");
        guardarDefault=new JMenuItem("Guardar partida a \"Partida por defecto\"");
        cargarFiles=new JMenuItem("Cargar desde archivo...");
        guardarFiles=new JMenuItem("Guardar en archivo...");
        cargarTablero=new JMenuItem("Cargar tablero...");
        guardarTablero=new JMenuItem("Guardar tablero...");
        comportamientoAutomatico=new JButton("Activar modo automatico");
        vistaCuadrados=new JMenuItem("Vista en cuadrados");
        vistaFigurasGeometricas=new JMenuItem("Vista en figuras geometricas");
        vistaIconos=new JMenuItem("Vista en iconos");
        panelPausa=new JLabel("PAUSE");
        reiniciarJuego=new JMenuItem("Reiniciar partida");
        fileChooserLoad=new JFileChooser();
        fileChooserSave=new JFileChooser();

        //Asignamos el actionListener a los botones e items
        cargarDefault.addActionListener(controladorDelJuego);
        guardarDefault.addActionListener(controladorDelJuego);
        cargarFiles.addActionListener(controladorDelJuego);
        guardarFiles.addActionListener(controladorDelJuego);
        cargarTablero.addActionListener(controladorDelJuego);
        guardarTablero.addActionListener(controladorDelJuego);
        comportamientoAutomatico.addActionListener(controladorDelJuego);
        vistaCuadrados.addActionListener(controladorDelJuego);
        vistaIconos.addActionListener(controladorDelJuego);
        vistaFigurasGeometricas.addActionListener(controladorDelJuego);
        fileChooserSave.addActionListener(controladorDelJuego);
        fileChooserLoad.addActionListener(controladorDelJuego);
        reiniciarJuego.addActionListener(controladorDelJuego);

        //Creamos cada uno de los menus e introducimos sus items
        menuFiles=new JMenu("Archivo");
        menuFiles.add(cargarFiles);
        menuFiles.add(guardarFiles);
        menuFiles.addSeparator();
        menuFiles.add(cargarDefault);
        menuFiles.add(guardarDefault);
        menuFiles.addSeparator();
        menuFiles.add(reiniciarJuego);

        menuTablero=new JMenu("Tablero");
        menuTablero.add(cargarTablero);
        menuTablero.add(guardarTablero);

        menuVistas=new JMenu("Vistas");
        menuVistas.add(vistaCuadrados);
        menuVistas.add(vistaIconos);
        menuVistas.add(vistaFigurasGeometricas);



        //Introducimos los menus en la barra de menus
        barraMenu =new JMenuBar();
        barraMenu.add(menuFiles);
        barraMenu.add(menuTablero);
        barraMenu.add(menuVistas);
        barraMenu.add(new JLabel("        "));
        barraMenu.add(panelPausa);
        barraMenu.add(new JLabel("        "));
        barraMenu.add(comportamientoAutomatico);
        barraMenu.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setJMenuBar(barraMenu);



        //Creacion de la ventana
        setLayout(new BorderLayout());
        setSize(CANVAS_WIDTH + 40, CANVAS_WIDTH + 100);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Añadimos el canvas y la barra de informacion a la ventana principal
        getContentPane().add(canvasFrame,BorderLayout.CENTER);
        getContentPane().add(panelEstadoCaperucita,BorderLayout.SOUTH);

        addKeyListener(controladorDelJuego);
        this.setFocusable(true);
    }



    public void pintarBarraMenuItemPAUSA(String texto){
        panelPausa.setText(texto);
    }

    public void pintarCanvas(ConcurrentLinkedQueue<IGameObject> inGameObjects){
        this.iGameObjectConcurrentLinkedQueue=inGameObjects;
        canvas.drawObjects(iGameObjectConcurrentLinkedQueue);
        requestFocusInWindow();
        //TODO pintar las cosas de color amarillo siempre es mejor jajsdhagjdvabsKGDlagfjhba
    }

    public void pintarPanelEstadoCaperucita(String texto){
        panelEstadoCaperucita.setText(texto);
    }


    public JButton getComportamientoAutomatico() {
        return comportamientoAutomatico;
    }
    public JMenuItem getCargarDefault() {
        return cargarDefault;
    }
    public JMenuItem getGuardarDefault() {
        return guardarDefault;
    }
    public JMenuItem getCargarFiles() {
        return cargarFiles;
    }
    public JMenuItem getGuardarFiles() {
        return guardarFiles;
    }
    public JMenuItem getCargarTablero() {
        return cargarTablero;
    }
    public JMenuItem getGuardarTablero() {
        return guardarTablero;
    }
    public JMenuItem getVistaIconos() {
        return vistaIconos;
    }
    public JMenuItem getVistaCuadrados() {
        return vistaCuadrados;
    }
    public JMenuItem getVistaFigurasGeometricas() {
        return vistaFigurasGeometricas;
    }
    public JFileChooser getFileChooserSave() {return fileChooserSave;}
    public JFileChooser getFileChooserLoad() {return fileChooserLoad;}
    public JMenuItem getReiniciarJuego() {return reiniciarJuego;
    }
}
