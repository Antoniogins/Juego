/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import org.json.JSONObject;

import java.util.stream.Collectors;

public class Spider extends AbstractGameObject{
    public Spider(){}
    public Spider(Position position) {
        super(position);    
    }
    public Spider(Position position, int value){
        super(position, value, 1);
    }
    public Spider(Position position, int value, int life){
        super(position, value, life);
    }
    public Spider(JSONObject obj){
        super(obj);
    }
    public Spider(Spider spider) {
        if(spider!=null){
            position=new Position(spider.position);
            lifes=spider.lifes;
            value=spider.value;
        }
    }
    
    public void printSpider(){
        System.out.println(this.toJSONObject());
    }

    @Override
    public Position moveToNextPosition() {
        RidingHood ridingHood=(RidingHood) iGameObjects.stream().filter(c-> c instanceof RidingHood).limit(1).collect(Collectors.toList()).toArray()[0];
        approachTo(ridingHood.position);
        return position;
    }
}

