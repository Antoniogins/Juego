/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;
import org.json.JSONObject;
public class Blossom extends AbstractGameObject {
     
    
    public Blossom(){}
    public Blossom(Position position){
        super(position);
    }
    public Blossom(Position position, int value){
        super(position, value, 1);
    }
    public Blossom(Position position, int value, int life){
        super(position, value, life);
    }
    public Blossom(JSONObject obj){
        super(obj);
    }
    public Blossom (Blossom bl) {
        if(bl!=null){
            position=new Position(bl.position);
            lifes=bl.lifes;
            value=bl.value;
        }

    }
    
    public void printBlossom(){
        System.out.println(this.toJSONObject());
    }
    
}
