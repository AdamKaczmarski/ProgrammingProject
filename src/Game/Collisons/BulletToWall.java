package Game.Collisons;

import Game.Levels.Walls.Wall;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletToWall implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody().getName().equals("BulletEn") || e.getOtherBody().getName().equals("BulletMC") || e.getOtherBody().getName().equals("Bullet")){
            e.getOtherBody().destroy();
            System.out.println("[BulletToWall]Bullet hit the wall");
        }
    }
}
