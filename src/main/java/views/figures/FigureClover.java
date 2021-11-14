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
public class FigureClover extends AbstractGameView{

    public FigureClover(IGameObject obj, int length) throws Exception {
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
        
        //Centro de flor
           g2.setColor(Color.green);
           g2.fillOval(x0+7*delta, y0+5*delta, 7*delta, 7*delta);

           g2.setColor(Color.white);
           g2.fillOval(x0+9*delta, y0+7*delta, 3*delta, 3*delta);
           
           //Petalos
           g2.setColor(Color.green);
           g2.fillOval(x0+3*delta, y0+7*delta, 4*delta,3*delta);

           
           g2.setColor(Color.green);
           g2.fillOval(x0+14*delta, y0+7*delta, 4*delta, 3*delta);

           
           g2.setColor(Color.green);
           g2.fillOval(x0+9*delta, y0+12*delta, 3*delta, 4*delta);

           
           g2.setColor(Color.green);
           g2.fillOval(x0+9*delta, y0+1*delta, 3*delta, 4*delta);

           
           g2.setColor(Color.green);
           g2.fillRect(x0+10*delta,y0+12*delta,delta,8*delta);
           
           g2.setColor(c);
    }

    
}
