package guis;

import game.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaGameOver extends JFrame {

    //Paneles de vista
    public static final int CANVAS_WIDTH = 480;
    int boxSize = 40;
    int row, col;

    JLabel panelInformacion;
    JButton reiniciarJuego;

    public PantallaGameOver(GameController gameController){
        super("GAME OVER");

        panelInformacion=new JLabel("Panel de informacion de estadisticas");
        reiniciarJuego=new JButton("Reiniciar Juego");
        reiniciarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setLayout(new GridLayout(2,1));
        setSize(CANVAS_WIDTH + 40, CANVAS_WIDTH + 100);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }


    public JButton getReiniciarJuego(){

        return reiniciarJuego;
    }

    public void setStats(int estadisticas){
        //TODO obtener las dferentes estadisticas de bichos matados, blossoms recogidos, etc
        panelInformacion.setText("Estadisticas finales: "+estadisticas+" puntos");
    }

}
