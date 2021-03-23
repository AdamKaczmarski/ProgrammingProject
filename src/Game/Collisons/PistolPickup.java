package Game.Collisons;

import Game.Characters.MainCharacter;
import Game.Items.Pistol;
import Game.Levels.GameLevel;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class PistolPickup implements CollisionListener {
    /**
     * This method checks for collision between MainCharacter and Pistol. If the Character picks up the pistol the visual object is destroyed and another,
     * abstract one is linked to MainCharacter that will allow shooting
     */
    @Override
    public void collide(CollisionEvent e) {
        //if (e.getOtherBody().getName().equals("MainCharacter") && e.getReportingBody().getName().equals("Pistol")){
        if (e.getOtherBody() instanceof MainCharacter && e.getReportingBody() instanceof Pistol) {
            MainCharacter mainChar = (MainCharacter) e.getOtherBody();
            if(mainChar.getPistol()==null){
                e.getReportingBody().destroy();
                mainChar.setPistol(new Pistol(mainChar.getW(),true));
                mainChar.getPistol().setAmmo(30);
            } else {
                e.getReportingBody().destroy();
                mainChar.getPistol().setAmmo(mainChar.getPistol().getAmmo()+10);
            }

        }
    }
}
