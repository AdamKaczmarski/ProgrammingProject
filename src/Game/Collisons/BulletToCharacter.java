package Game.Collisons;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import Game.Items.Bullet;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import com.sun.tools.javac.Main;
import org.jbox2d.common.Vec2;

public class BulletToCharacter implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody().getName()!=null) {
            if (e.getReportingBody().getName().equals("Enemy") && e.getOtherBody().getName().equals("Bullet")) {
                Bullet b = (Bullet) e.getOtherBody();
                Enemy enemy= (Enemy) e.getReportingBody();
                enemy.setHealth(enemy.getHealth()-b.getDamage());
                e.getOtherBody().destroy();
                //System.out.println("Enemy's health: "+enemy.getHealth());
                enemy.setLinearVelocity(new Vec2(0,0));
                if(enemy.getHealth()<=0){
                    enemy.destroy();
                }

            }
            if (e.getReportingBody().getName().equals("MainCharacter") && e.getOtherBody().getName().equals("Bullet")){
                Bullet b = (Bullet) e.getOtherBody();
                MainCharacter mainChar = (MainCharacter) e.getReportingBody();
                mainChar.setHealth(mainChar.getHealth()-b.getDamage());
                e.getOtherBody().destroy();
                mainChar.setLinearVelocity(new Vec2(0,0));
                if(mainChar.getHealth()<=0){
                    mainChar.destroy();
                }

            }
        }
    }
}
