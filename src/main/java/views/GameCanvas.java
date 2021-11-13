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
import game.model.*;

 /**
 *
 * @author juanangel
 */
public class GameCanvas extends JPanel {
          
    int editCol, editRow;
    int canvasEdge = 400;
    int squareEdge = 20;
    boolean squareOn = true;
    
    ConcurrentLinkedQueue<IGameObject> gObjects = new ConcurrentLinkedQueue<>();
    
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

        try {
            IAWTGameView v = null;


            for (IGameObject gObj : gObjects) {
                if (gObj != null) {
                    if (gObj instanceof Blossom) {
                        v = new VNumberedBox(gObj, squareEdge, Color.pink, "Blossom");
                    }
                    else if (gObj instanceof Bee) {
                        v = new VNumberedBox(gObj, squareEdge, Color.yellow, "Bee");
                    }
                    else if (gObj instanceof Fly) {
                        v = new VNumberedBox(gObj, squareEdge, Color.green, "Fly");
                    }
                    else if (gObj instanceof Spider) {
                        v = new VNumberedCircle(gObj, squareEdge, Color.black, "Spider");
                    }
                    else if (gObj instanceof RidingHood) {
                        v = new VNumberedBox(gObj, squareEdge, Color.red, "RHood");
                    }


                    if (v != null)
                        v.draw(g);
                }
            }





        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
}

