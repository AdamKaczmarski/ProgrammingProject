package Game.Collisons;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import Game.Items.Bullet;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletToCharacter implements CollisionListener {
    private MainCharacter mainChar;
    public BulletToCharacter(MainCharacter mc){
        this.mainChar=mc;
    }
    public void addPoints(){
       getColMC().setPoints(getColMC().getPoints()+10);
       getColMC().getPistol().setAmmo(getColMC().getPistol().getAmmo()+1);
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody().getName()!=null) {
            if (e.getReportingBody().getName().equals("Enemy") && e.getOtherBody().getName().equals("BulletMC")) {
                Bullet b = (Bullet) e.getOtherBody();
                Enemy enemy= (Enemy) e.getReportingBody();
                enemy.setHealth(enemy.getHealth()-b.getDamage());
                e.getOtherBody().destroy();
                if(enemy.getHealth()<=0){
                    enemy.setPistol(null);
                    enemy.destroy();
                    this.addPoints();
                }

            }
            if (e.getReportingBody().getName().equals("MainCharacter") && e.getOtherBody().getName().equals("BulletEn")){
                Bullet b = (Bullet) e.getOtherBody();
                MainCharacter mainChar = (MainCharacter) e.getReportingBody();
                mainChar.setHealth(mainChar.getHealth()-b.getDamage());
                e.getOtherBody().destroy();
                if(mainChar.getHealth()<=0){
                    mainChar.setPistol(null);
                    mainChar.destroy();
                }

            }
            if (e.getReportingBody().getName().equals("Enemy") && e.getOtherBody().getName().equals("BulletEn")){
                e.getOtherBody().destroy();
            }
        }
    }
    public MainCharacter getColMC(){
        return mainChar;
    }
}
