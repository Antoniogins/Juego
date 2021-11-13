/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.boxes;

import game.model.Bee;
import game.model.Blossom;
import game.model.Fly;
import common.IGameObject;
import game.model.RidingHood;
import game.model.Spider;
import java.awt.Color;
import common.IAWTGameView;
import common.IViewFactory;

/**
 *
 * @author juanangel
 */
public class BoxesFactory implements IViewFactory {
    
    public IAWTGameView getView(IGameObject gObj, int length) throws Exception {
        
        IAWTGameView view = new VNumberedBox(gObj, length);
                
        if (gObj instanceof Fly){
           view = new VNumberedBox(gObj, length, Color.gray, "Fly"); ; 
        }
        else if (gObj instanceof Bee){
           view = new VNumberedBox(gObj, length, Color.YELLOW, "Bee"); 
        }
        else if (gObj instanceof Spider){
           view = new VNumberedBox(gObj, length, Color.black, "Spider");
        }
        else if (gObj instanceof RidingHood){
           view = new VNumberedBox(gObj, length, Color.red, "Hood");
        } 
        else if (gObj instanceof Blossom){
            if (gObj.getValue() < 10){
                view = new VNumberedBox(gObj, length, Color.pink, "DLion");
            }
            else {
                view = new VNumberedBox(gObj, length, Color.GREEN, "Clover");
            }
        }
        return view; 
    }
    
}
