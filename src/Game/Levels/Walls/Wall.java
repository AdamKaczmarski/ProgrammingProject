package Game.Levels.Walls;

import Game.Collisons.BulletToWall;
import Game.Levels.LevelOne;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;
//float boxWidth, float boxHeight, float x, float y

public class Wall extends StaticBody {
        private static Shape wallShape;
        private static StaticBody wall;
    public Wall (World w,float width, float height, float x, float y){
        super(w);
        wallShape = new BoxShape(width,height);
        wall =  new StaticBody(w,wallShape);
        wall.setPosition(new Vec2(x,y));
        wall.setLineColor(new Color(1,1,1,0));
        wall.addCollisionListener(new BulletToWall());
    }
    /*
    public void setPosition(float x, float y){
        wall.setPosition(new Vec2(0,-11.5f));
    }
    */




}
