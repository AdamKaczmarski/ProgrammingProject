package Game.Collisons;

import Game.Characters.FinalBoss;
import Game.Levels.Walls.Wall;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

public class FinalBossXMovement implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof StaticBody){
            switch (e.getOtherBody().getName()){
                case "LeftWall" :
                    ((FinalBoss)e.getReportingBody()).setLinearVelocity(new Vec2(3,0));
                    break;
                case "RightWall" :
                    ((FinalBoss)e.getReportingBody()).setLinearVelocity(new Vec2(-3,0));
                    break;
            }
        }

    }
}
