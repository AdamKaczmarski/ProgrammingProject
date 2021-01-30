package Game.Collisons;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletToBullet implements CollisionListener {
    @Override
    public void collide(CollisionEvent e){
        if (e.getReportingBody().getName()!=null && e.getOtherBody().getName()!=null){
            if (e.getReportingBody().getName().equals("Bullet") && e.getOtherBody().getName().equals("Bullet")){
                e.getOtherBody().destroy();
                e.getReportingBody().destroy();
            }
        }

    }
}
