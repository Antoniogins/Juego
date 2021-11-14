package views.figures;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import common.IGameObject;
import game.model.*;
import views.AbstractGameView;
import java.awt.*;

/**
 *
 * @author Felix
 */
public class FigureBee extends AbstractGameView {

    public FigureBee(IGameObject obj, int length) throws Exception {
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
        
        //Cuerpo y cabeza
        g2.setColor(Color.yellow);   
        g2.fillOval(x0+8*delta, y0+4*delta, 6*delta, 6*delta);
        g2.setColor(Color.black);   
        g2.drawOval(x0+8*delta, y0+4*delta, 6*delta, 6*delta);
        g2.setColor(Color.yellow);   
        g2.fillOval(x0+7*delta, y0+8*delta, 8*delta, 12*delta);
        
        //Ojos
        g2.setColor(Color.black);           
        g2.fillOval(x0+9*delta, y0+5*delta, delta, delta);
        g2.setColor(Color.black);   
        g2.fillOval(x0+12*delta, y0+5*delta, delta, delta);
        
        //Alas
        g2.setColor(Color.gray);   
        g2.fillOval(x0+14*delta, y0+11*delta, 6*delta, 3*delta);
        g2.setColor(Color.gray); 
        g2.fillOval(x0+2*delta, y0+11*delta, 6*delta, 3*delta);
        g2.setColor(Color.black);   
        g2.drawOval(x0+14*delta, y0+11*delta, 6*delta, 3*delta);
        g2.setColor(Color.black);   
        g2.drawOval(x0+2*delta, y0+11*delta, 6*delta, 3*delta);
        
        //Lineas abeja
        g2.setColor(Color.black);
        g2.drawLine(x0+9*delta, y0+11*delta, x0+13*delta, y0+11*delta);
        g2.setColor(Color.black);
        g2.drawLine(x0+9*delta, y0+13*delta, x0+13*delta, y0+13*delta);
        g2.setColor(Color.black);
        g2.drawLine(x0+9*delta, y0+15*delta, x0+13*delta, y0+15*delta);
        g2.setColor(Color.black);
        g2.drawLine(x0+10*delta, y0+17*delta, x0+12*delta, y0+17*delta);
    
        
        g2.setColor(c);
    }

    
}
