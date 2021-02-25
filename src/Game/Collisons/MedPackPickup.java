package Game.Collisons;

import Game.Characters.MainCharacter;
import Game.Items.Bullet;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class MedPackPickup implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Bullet){
            e.getReportingBody().destroy();
            e.getOtherBody().destroy();
        }
        if (e.getOtherBody().getName().equals("MainCharacter")){
            MainCharacter mainChar = (MainCharacter) e.getOtherBody();
            mainChar.setHealth(mainChar.getHealth()+25);
            e.getReportingBody().destroy();
        }
    }
}
