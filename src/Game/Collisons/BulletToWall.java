package Game.Collisons;

import Game.Items.Bullet;
import Game.Levels.GameLevel;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletToWall implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Bullet){
            if(((GameLevel)e.getOtherBody().getWorld()).getSfxVolume()>0){
                ((Bullet) e.getOtherBody()).getBlank().play();
            }
            e.getOtherBody().destroy();
        }
    }
}
