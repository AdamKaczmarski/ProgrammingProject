package Game.Collisons;

import Game.Items.Bullet;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletToBullet implements CollisionListener {
    @Override
    public void collide(CollisionEvent e){
        if (e.getReportingBody().getName()!=null && e.getOtherBody().getName()!=null){
            if (e.getReportingBody() instanceof Bullet && e.getOtherBody()instanceof Bullet){
                e.getOtherBody().destroy();
                e.getReportingBody().destroy();
                //System.out.println("Bullet destroyed another Bullet");
            }
        }

    }
}
