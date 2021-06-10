package Game.Collisons;

import Game.Characters.FinalBoss;
import Game.Items.Bullet;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletToFinalBoss implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof FinalBoss && e.getOtherBody()instanceof Bullet){
            ((FinalBoss) e.getReportingBody()).setHealth(((FinalBoss) e.getReportingBody()).getHealth()-((Bullet) e.getOtherBody()).getDamage());
            e.getOtherBody().destroy();
        }
    }
}
