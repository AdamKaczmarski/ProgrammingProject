package Game.Levels.RotPills;

import Game.Collisons.BulletToRotPill;
import Game.Collisons.RotPillToWall;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class RotPill extends Walker {
    private static Shape pilShape =  new BoxShape(2f,2f);
    private int health;
    public RotPill(World w){
        super(w,pilShape);
        this.setAngularVelocity(3f);
        this.addCollisionListener(new RotPillToWall());
        this.addCollisionListener(new BulletToRotPill());
        this.setName("RotPill");
        this.health=30;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
