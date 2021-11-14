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
public class FigureAraña extends AbstractGameView{

    
    public FigureAraña(IGameObject obj, int length) throws Exception {
        super(obj, length);
    }

    @Override
    public void draw(Graphics g) {

    Graphics2D g2 = (Graphics2D) g;
        
        Position coord = objects.getPosition();
        
        int x0 = coord.getX() * length;
        int y0 = coord.getY() * length;
        int delta = length/20;
        
        Color c = g2.getColor();
        g2.setStroke(new BasicStroke(1));
        
        // Head
        g2.setColor(Color.black);   
        g2.fillOval(x0+8*delta, y0+3*delta, 6*delta, 14*delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+3*delta, y0+6*delta, 16*delta, delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+3*delta, y0+8*delta, 16*delta, delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+3*delta, y0+10*delta, 16*delta, delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+3*delta, y0+12*delta, 16*delta, delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+4*delta, y0+3*delta, delta, 4*delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+17*delta, y0+3*delta, delta, 4*delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+17*delta, y0+12*delta, delta, 4*delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+4*delta, y0+12*delta, delta, 4*delta);
        
        g2.setColor(c);
}
}
