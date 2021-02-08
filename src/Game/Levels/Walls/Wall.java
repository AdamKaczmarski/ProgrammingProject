package Game.Levels.Walls;

import Game.Collisons.BulletToWall;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;

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
        wall.setName("Wall");
    }
}
