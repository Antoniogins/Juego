/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import org.json.JSONObject;
public class Fly extends AbstractGameObject{
    
    public Fly(){}
    public Fly(Position position) {
        super(position);    
    }
    public Fly(Position position, int value){
        super(position, value, 1);
    }
    public Fly(Position position, int value, int life){
        super(position, value, life);
    }
    public Fly(JSONObject obj){
        super(obj);
    }
    public Fly(Fly fly) {
        if(fly!=null){
            position=new Position(fly.position);
            lifes=fly.lifes;
            value=fly.value;
        }
    }
    
    public void printFly(){
        System.out.println(this.toJSONObject());
    }

    @Override
    public Position moveToNextPosition() {
        //TODO todavia por implementar
        this.position.x+=(int) (Math.random()*3)-1;
        this.position.y+=(int) (Math.random()*3)-1;
        return position;
    }
}
