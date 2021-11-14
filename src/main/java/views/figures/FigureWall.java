/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.figures;

import common.*;
import game.model.*;
import views.AbstractGameView;

import java.awt.*;

/**
 *
 * @author Felix
 */
public class FigureWall extends AbstractGameView {
    Color color = Color.ORANGE;
    public FigureWall(IGameObject obj, int length) throws Exception {
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
          


          //Contorno muro
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+2*delta, 12*delta, 18*delta);
          
          //Ladrillos. Fila 1
          g2.setColor(Color.orange);
          g2.fillRect(x0+5*delta, y0+2*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+2*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+9*delta, y0+2*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+9*delta, y0+2*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+13*delta, y0+2*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+13*delta, y0+2*delta, 4*delta, 2*delta);
          
          //Ladrillos. Fila 2
          g2.setColor(Color.orange);
          g2.fillRect(x0+5*delta, y0+4*delta, 2*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+4*delta, 2*delta, 2*delta);

          g2.setColor(Color.orange);
          g2.fillRect(x0+7*delta, y0+4*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+7*delta, y0+4*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+11*delta, y0+4*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+11*delta, y0+4*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+15*delta, y0+4*delta, 2*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+15*delta, y0+4*delta, 2*delta, 2*delta);
          
          //Ladrillos. Fila 3
          g2.setColor(Color.orange);
          g2.fillRect(x0+5*delta, y0+6*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+6*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+9*delta, y0+6*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+9*delta, y0+6*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+13*delta, y0+6*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+13*delta, y0+6*delta, 4*delta, 2*delta);
          
          //Ladrillos. Fila 4
          g2.setColor(Color.orange);
          g2.fillRect(x0+5*delta, y0+8*delta, 2*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+8*delta, 2*delta, 2*delta);

          g2.setColor(Color.orange);
          g2.fillRect(x0+7*delta, y0+8*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+7*delta, y0+8*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+11*delta, y0+8*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+11*delta, y0+8*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+15*delta, y0+8*delta, 2*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+15*delta, y0+8*delta, 2*delta, 2*delta);
          
          //Ladrillos. Fila 5
          g2.setColor(Color.orange);
          g2.fillRect(x0+5*delta, y0+10*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+10*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+9*delta, y0+10*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+9*delta, y0+10*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+13*delta, y0+10*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+13*delta, y0+10*delta, 4*delta, 2*delta);
          
          //Ladrillos. Fila 6
          g2.setColor(Color.orange);
          g2.fillRect(x0+5*delta, y0+12*delta, 2*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+12*delta, 2*delta, 2*delta);

          g2.setColor(Color.orange);
          g2.fillRect(x0+7*delta, y0+12*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+7*delta, y0+12*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+11*delta, y0+12*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+11*delta, y0+12*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+15*delta, y0+12*delta, 2*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+15*delta, y0+12*delta, 2*delta, 2*delta);
          
          //Ladrillos. Fila 7
          g2.setColor(Color.orange);
          g2.fillRect(x0+5*delta, y0+14*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+14*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+9*delta, y0+14*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+9*delta, y0+14*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+13*delta, y0+14*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+13*delta, y0+14*delta, 4*delta, 2*delta);
          
          //Ladrillos. Fila 8
          g2.setColor(Color.orange);
          g2.fillRect(x0+5*delta, y0+16*delta, 2*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+16*delta, 2*delta, 2*delta);

          g2.setColor(Color.orange);
          g2.fillRect(x0+7*delta, y0+16*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+7*delta, y0+16*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+11*delta, y0+16*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+11*delta, y0+16*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+15*delta, y0+16*delta, 2*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+15*delta, y0+16*delta, 2*delta, 2*delta);
          
          //Ladrillos. Fila 9
          g2.setColor(Color.orange);
          g2.fillRect(x0+5*delta, y0+18*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+5*delta, y0+18*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+9*delta, y0+18*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+9*delta, y0+18*delta, 4*delta, 2*delta);
          
          g2.setColor(Color.orange);
          g2.fillRect(x0+13*delta, y0+18*delta, 4*delta, 2*delta);
          g2.setColor(Color.black);
          g2.drawRect(x0+13*delta, y0+18*delta, 4*delta, 2*delta);
          
          g2.setColor(c);
    
    }
}
