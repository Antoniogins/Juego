/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.figures;

import common.IGameObject;
import game.model.*;
import views.AbstractGameView;
import java.awt.*;

/**
 *
 * @author Felix
 */
public class FigureCaperucita extends AbstractGameView {

    public FigureCaperucita(IGameObject obj, int length) throws Exception {
        super(obj, length);
    }
    
    
    public void draw(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        Position coord = objects.getPosition();
        
        int x0 = coord.getX() * length;
        int y0 = coord.getY() * length;
        int delta = length/20;
        
        Color c = g2.getColor();
        g2.setStroke(new BasicStroke(1));
        
        //Cabeza
        g2.setColor(Color.red);   
        g2.fillRect(x0+8*delta, y0+1*delta, 6*delta, 6*delta);
        g2.setColor(Color.white);   
        g2.fillOval(x0+8*delta, y0+1*delta, 6*delta, 6*delta);
        
        //Ojos
        g2.setColor(Color.black);   
        g2.fillOval(x0+9*delta, y0+2*delta, delta, delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+12*delta, y0+2*delta, delta, delta);
        
        //Boca
        g2.setColor(Color.black);
        g2.drawLine(x0+10*delta,y0+5*delta,x0+12*delta, y0+5*delta);

        //Cuerpo
        g2.setColor(Color.red);   
        g2.fillRect(x0+5*delta, y0+7*delta, 12*delta, 10*delta);
        
        //Piernas
        g2.setColor(Color.black);   
        g2.fillRect(x0+7*delta, y0+17*delta, 1*delta, 5*delta);
        g2.setColor(Color.black);   
        g2.fillRect(x0+14*delta, y0+17*delta, 1*delta, 5*delta);
        
        //Brazos
        g2.setColor(Color.black);
        g2.fillRect(x0+2*delta, y0+9*delta, 3*delta, 1*delta);
        g2.setColor(Color.black);   
        g2.fillRect(x0+17*delta, y0+9*delta, 3*delta, 1*delta);
           
           g2.setColor(c);
    }
}
