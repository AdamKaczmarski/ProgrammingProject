package Game.Collisons;

import Game.Characters.MainCharacter;
import Game.Items.Bullet;
import Game.Items.MedPack;
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
            if (mainChar.getHealth()+25>100) mainChar.setHealth(100);
            mainChar.setHealth(mainChar.getHealth()+((MedPack)e.getReportingBody()).getAmount());

            System.out.println(mainChar.getHealth());
            e.getReportingBody().destroy();
        }
    }
}
