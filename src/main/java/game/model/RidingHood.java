/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import common.IGameObject;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import static game.factories.KeyBoard.*;

public class RidingHood extends AbstractGameObject {

    public boolean modoAutomatico=false;
    Nivel nivel;
    int dX, dY;

    public RidingHood(Position position) {
        super(position);
    }
    public RidingHood(Position position, int value, int life ) {
        super(position, value, life);
    }
    public RidingHood(JSONObject jObj) {
        super(jObj);    
    }
    public RidingHood(Position position, int value, int life, Nivel nivel) {
        super(position, value, life);
        this.nivel=nivel;
    }
    public RidingHood(RidingHood ri) {
        if(ri!=null){
            position=new Position(ri.position);
            lifes=ri.lifes;
            value=ri.value;
            nivel=ri.getNivel();
        }
    }
    
    /**
     * Cada vez que se invoca se dirige hacia el siguiente blossom, 
     * moviéndose una posición en x y otra en y.
     *
     * @return posición en la que se encuentra después de ejecutarse el
     * método.
     */
    @Override
    public Position moveToNextPosition(){
        if(modoAutomatico){
            dX=0;
            dY=0;
            Blossom closest=(Blossom) getClosest(this.position, nivel.getBlossomsArrayList());


            System.out.println("This is the closest position to approach: "+closest.getPosition());
            approachTo(closest.position);
        }else{
            this.position.x+=dX;
            this.position.y+=dY;
        }
        return position;       
    }
    
    public void moveRigth(){ dY = 0; dX = 1; }
    public void moveLeft(){  dY = 0; dX = -1; }
    public void moveUp(){    dY = -1; dX = 0; }
    public void moveDown(){  dY = 1; dX = 0; }
    public Nivel getNivel() {return nivel;}
    public void setNivel(Nivel nivel) {this.nivel = nivel;}


    /**
     * Alterna el modo de control de caperucita
     *
     * @return un String para mostrar en pantalla un texto para cambiar de estado
     */
    public void cambiarModoControl(){
        if(modoAutomatico){
            modoAutomatico=false;
        }else{
            modoAutomatico=true;
            dX=0; dY=0;
        }
    }
    public void turnManual(){
        this.modoAutomatico=false;
    }

    public String getEstadoControl(){
        return (modoAutomatico)?"Desactivar modo automatico":"Activar modo automatico";
    }



    /**
     * Cambiamos la direccion en la que se mueve caperucita
     * @param lastKey
     */
    public void setDirection(int lastKey) {
        modoAutomatico=false;
        switch (lastKey) {
            case UP_KEY:
                moveUp();
                break;
            case DOWN_KEY:
                moveDown();
                break;
            case RIGTH_KEY:
                moveRigth();
                break;
            case LEFT_KEY:
                moveLeft();
                break;
        }


    }




}
