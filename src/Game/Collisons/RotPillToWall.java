package Game.Collisons;

import Game.Characters.MainCharacter;
import Game.Levels.GameLevel;
import Game.Levels.RotPills.RotPill;
import Game.Levels.Walls.Wall;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class RotPillToWall implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Wall && e.getReportingBody() instanceof RotPill){
            ((RotPill) e.getReportingBody()).setLinearVelocity(new Vec2(-e.getReportingBody().getLinearVelocity().x,0));
        }
        if (e.getOtherBody().getName().equals("MainCharacter")){
            MainCharacter mc =  (MainCharacter) e.getOtherBody();
            mc.setHealth(mc.getHealth());
            ((MainCharacter) e.getOtherBody()).setHealth(((MainCharacter) e.getOtherBody()).getHealth()-20);
            //((GameLevel)e.getOtherBody().getWorld()).getView().repaint();
        }
    }
}
