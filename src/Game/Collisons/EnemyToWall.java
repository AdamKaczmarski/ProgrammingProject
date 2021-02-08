package Game.Collisons;

import Game.Characters.Enemy;
import Game.Levels.Walls.Wall;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class EnemyToWall implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody().getName().equals("Wall") ){
            //System.out.println("eneym collided with wall");
            Enemy enemy = (Enemy) e.getReportingBody();
            //if (enemy.getLinearVelocity().x<0 && enemy.getLinearVelocity().y<0) enemy.setLinearVelocity(new Vec2(-enemy.getLinearVelocity().x,-enemy.getLinearVelocity().y));
            //System.out.println(enemy.getLinearVelocity().x+" "+enemy.getLinearVelocity().y);
            enemy.setLinearVelocity(new Vec2(((float)Math.random()*5)+-5,((float)Math.random()*5)+-5));
            //System.out.println(enemy.getLinearVelocity().x+" "+enemy.getLinearVelocity().y);
        }
    }
}
