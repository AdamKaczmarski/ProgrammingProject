package Game.Collisons;

import Game.Characters.MainCharacter;
import Game.Items.Pistol;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import com.sun.tools.javac.Main;

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
            Pistol pistol = (Pistol) e.getReportingBody();
            if(mainChar.getPistol()==null){
                e.getReportingBody().destroy();
                mainChar.setPistol(new Pistol(mainChar.getW(),true));
            } else {
                e.getReportingBody().destroy();
                mainChar.getPistol().setAmmo(mainChar.getPistol().getAmmo()+5);
            }
            mainChar.getPistol().setAmmo(30);
            mainChar.charShoot(); //JUST FOR TESTING
            //System.out.println(mainChar.getPistol().getAmmo());
        }
    }
}
