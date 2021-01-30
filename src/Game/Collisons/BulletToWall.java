package Game.Collisons;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

public class BulletToWall implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody().getName().equals("Bullet") ){
            e.getOtherBody().destroy();

        }
    }
}
