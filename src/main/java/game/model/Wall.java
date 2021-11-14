package game.model;

import org.json.JSONObject;

public class Wall extends AbstractGameObject{

    public Wall(Position position, int i, int i1) {
        super(position,0,0);
    }

    public Wall(JSONObject jsonObject){super(jsonObject);}
    public Wall(Wall wall){
        super(new Position(wall.position), wall.value, wall.lifes);
    }
}
