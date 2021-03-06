/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.rounded;

import common.IGameObject;
import game.model.Position;
import views.AbstractGameView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author juanangel
 */
public class VNumberedCircle extends AbstractGameView {
    
    Color myColor = Color.blue;
    String legend = new String();
    
    public VNumberedCircle(IGameObject mObject, int length) throws Exception{
        super(mObject, length); 
    }
    
    public VNumberedCircle(IGameObject mObject, int length, Color c) throws Exception{
        super(mObject, length); 
        myColor = c;
    }
    
    public VNumberedCircle(IGameObject mObject, int length, Color c, String legend) throws Exception{
        super(mObject, length); 
        myColor = c;
        this.legend = legend;
    }

    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        Position coord = objects.getPosition();
        
        Color c = g2.getColor();
        g2.setColor(myColor);    
        g2.setStroke(new BasicStroke(2));
        g2.fillOval(length*coord.getX(), length*coord.getY(), length, length);
        g2.setColor(Color.WHITE);  
        g2.drawString(legend, length*coord.getX()+6, length*coord.getY()+24);
        g2.setColor(c);
    }
}

