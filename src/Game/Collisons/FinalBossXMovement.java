package Game.Collisons;

import Game.Characters.FinalBoss;
import Game.Levels.Walls.Wall;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class FinalBossXMovement implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody().getName().equals("Wall")){
            System.out.println(e.getReportingBody().getLinearVelocity());
            ((FinalBoss)e.getReportingBody()).setLinearVelocity(new Vec2(e.getReportingBody().getLinearVelocity().x*-1,e.getReportingBody().getLinearVelocity().y));
        }
    }
}
