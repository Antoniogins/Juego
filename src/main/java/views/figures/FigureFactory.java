/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.figures;
import common.*;
import game.model.*;


/**
 *
 * @author Felix
 */
//Seguimos el mismo patrón que en BoxesFactory y en IconsFactory, 
//pero ahora en vez de crear directamente la figura, hacemos una llamada a otras clases que meteremos en el paquete views.fig
public class FigureFactory implements IViewFactory {

    @Override
    public IAWTGameView getView(IGameObject gObj, int length) throws Exception {
        IAWTGameView view = null;
        if (gObj instanceof Fly){
           view = new views.figures.FigureFly(gObj, length);
        }
        else if (gObj instanceof Bee){
           view = new FigureBee(gObj, length);
        }  
        else if (gObj instanceof RidingHood){
           view = new views.figures.FigureCaperucita(gObj, length);
        } 
        else if (gObj instanceof Spider){
           view = new FigureAraña(gObj, length);
        }  
        else if (gObj instanceof Wall){
            view = new views.figures.FigureWall(gObj, length);
         }
        else if (gObj instanceof Blossom){
           if (gObj.getValue() < 10){
                view = new views.figures.FigureDandelion(gObj, length);
           }
           else {
                view = new FigureClover(gObj,  length);
           }
        }
            
        return view;
    }
    
    
}
