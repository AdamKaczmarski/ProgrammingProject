package Game.Collisons;

import Game.Items.Bullet;
import Game.Levels.RotPills.RotPill;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletToRotPill implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof RotPill && e.getOtherBody() instanceof Bullet){
            if (((RotPill) e.getReportingBody()).getHealth()>0){
                ((RotPill) e.getReportingBody()).setHealth(((RotPill) e.getReportingBody()).getHealth()-((Bullet) e.getOtherBody()).getDamage());
                e.getOtherBody().destroy();
                if (((RotPill) e.getReportingBody()).getHealth()<=0) e.getReportingBody().destroy();
            }
        }
    }
}
