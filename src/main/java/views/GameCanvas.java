 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JPanel;

import common.IAWTGameView;
import common.IGameObject;
import common.IViewFactory;
import game.model.*;
import views.boxes.BoxesFactory;
import views.figures.FigureFactory;
import views.icons.IconsFactory;

 /**
  * @author Antonio Gines Buendia Lopez y Felix Conesa Garcia
  *
  */
public class GameCanvas extends JPanel {
          
    int editCol, editRow;
    int canvasEdge = 400;
    int squareEdge = 20;
    boolean squareOn = true;

    public static final int VISTA_SQUARE=128;
    public static final int VISTA_FIGURES =256;
    public static final int VISTA_ICONOS=512;
    
    ConcurrentLinkedQueue<IGameObject> gObjects = new ConcurrentLinkedQueue<>();
    IViewFactory iViewFactory=null;

    public GameCanvas(){}
    
    public GameCanvas(int canvasEdge, int squareEdge){
        this.squareEdge = squareEdge;
        this.canvasEdge = canvasEdge;
    }
    
    public void setSquareEdge(int squareEdge){
        this.squareEdge = squareEdge;
        repaint();
    }
    
    
    public void drawObjects(ConcurrentLinkedQueue<IGameObject> gObjects){
        if (gObjects != null){
            this.gObjects = gObjects;
        }
        repaint();
    }
    
    public void refresh(){
        repaint();
    }    
    
    private void drawGrid(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.lightGray);
        int nLines = canvasEdge/squareEdge;

        for (int i = 1; i < nLines; i++){
            g.drawLine(i*squareEdge, 0, i*squareEdge, canvasEdge);
            g.drawLine(0, i*squareEdge, canvasEdge, i*squareEdge);
        }   
        g.setColor(c);
    }  
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        drawGrid(g);

//        try {
//            IAWTGameView gameView = null;
//
//
//            for (IGameObject gObj : gObjects) {
//                if (gObj != null) {
//                    if (gObj instanceof Blossom) {
//                        gameView = new VNumberedBox(gObj, squareEdge, Color.pink, "Blossom");
//                    }
//                    else if (gObj instanceof Bee) {
//                        gameView = new VNumberedBox(gObj, squareEdge, Color.yellow, "Bee");
//                    }
//                    else if (gObj instanceof Fly) {
//                        gameView = new VNumberedBox(gObj, squareEdge, Color.green, "Fly");
//                    }
//                    else if (gObj instanceof Spider) {
//                        gameView = new VNumberedCircle(gObj, squareEdge, Color.black, "Spider");
//                    }
//                    else if (gObj instanceof RidingHood) {
//                        gameView = new VNumberedBox(gObj, squareEdge, Color.red, "RHood");
//                    }
//
//
//                    if (gameView != null)
//                        gameView.draw(g);
//                }
//            }
//
//
//
//
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        for (IGameObject gObj : gObjects) {
            if (gObj != null) {
                IAWTGameView view;
                try {
                    view = AbstractGameView.getView(gObj, squareEdge, iViewFactory);
                    view.draw(g);
                } catch (Exception ex) {
                }
            }
        }

    }


     public void setVistas(int identificador){
        switch (identificador){
            case VISTA_ICONOS -> {
                iViewFactory=new IconsFactory();
                break;
            }
            case VISTA_FIGURES -> {
                iViewFactory=new FigureFactory();
                break;
            }
            case VISTA_SQUARE -> {
                iViewFactory=new BoxesFactory();
                break;
            }
        }
    }
}

