/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;
import common.IGameObject;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Bee extends AbstractGameObject {

    private List<IGameObject> blossoms;
    public Bee(){}
    public Bee(Position position) {
        super(position);    
    }
    public Bee(Position position, int value){
        super(position, value, 1);
    }
    public Bee(Position position, int value, int life){
        super(position, value, life);
    }
    public Bee(JSONObject obj){
        super(obj);
    }
    public Bee(Bee bee) {
        if(bee!=null){
            position=new Position(bee.position);
            lifes=bee.lifes;
            value=bee.value;
        }
    }


    public void printBee(){
        System.out.println(this.toJSONObject());
    }


    @Override
    public Position moveToNextPosition() {
        ArrayList<IGameObject> blossom= (ArrayList<IGameObject>) iGameObjects.stream().filter(c -> c.getClass().getSimpleName().equals("Blossom")).collect(Collectors.toList());
        if(blossom.size()==0){
            moveDiagonal();
        }else{
            IGameObject closest=getClosest(position,blossom);
            approachTo(closest.getPosition());
        }
        return this.position;
    }



    private void moveDiagonal(){
        this.position.x+=1;
        this.position.y+=1;
    }
}
