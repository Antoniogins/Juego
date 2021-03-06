package views.icons;

import common.IGameObject;
import game.model.Position;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import views.AbstractGameView;


public class VIcon extends AbstractGameView {
	
    private BufferedImage image;
    
    public VIcon(IGameObject mObject, String imgFile, int l) throws Exception {
       super(mObject, l);       
       image = ImageIO.read(new File(imgFile));
    }

    @Override
    public void draw(Graphics g) {       
        Position coord = objects.getPosition();
	g.drawImage(image, length * coord.getX(), length * coord.getY(), length, length, null);		
    }
}
