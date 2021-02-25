package Game.Collisons;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import Game.Items.Bullet;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletToCharacter implements CollisionListener {
    private MainCharacter mainChar;
    public BulletToCharacter(){

    }
    public BulletToCharacter(MainCharacter mc){
        //System.out.println(mc);
        this.mainChar=mc;
       // System.out.println(this.mainChar);
    }
    public void addPoints(){
       getColMC().setPoints(getColMC().getPoints()+10);
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody().getName()!=null) {
            if (e.getReportingBody().getName().equals("Enemy") && e.getOtherBody().getName().equals("BulletMC")) {
                Bullet b = (Bullet) e.getOtherBody();
                Enemy enemy= (Enemy) e.getReportingBody();
                //System.out.println(enemy.getLinearVelocity());
                /*System.out.println(e.getOtherBody().getLinearVelocity());
                enemy.applyImpulse(e.getOtherBody().getLinearVelocity());*/
                enemy.setHealth(enemy.getHealth()-b.getDamage());
                e.getOtherBody().destroy();
                //System.out.println("[BulletToCharacter] Enemy hit by MC (HP:"+enemy.getHealth()+")");
                //System.out.println("Enemy's health: "+enemy.getHealth());
                //enemy.setLinearVelocity(new Vec2(0,0));
                if(enemy.getHealth()<=0){
                    enemy.destroy();
                    //System.out.println("[BulletToCharacter] Enemy killed by MC!");
                    this.addPoints();
                }

            }
            if (e.getReportingBody().getName().equals("MainCharacter") && e.getOtherBody().getName().equals("BulletEn")){
                Bullet b = (Bullet) e.getOtherBody();
                MainCharacter mainChar = (MainCharacter) e.getReportingBody();
                mainChar.setHealth(mainChar.getHealth()-b.getDamage());
                e.getOtherBody().destroy();
               // mainChar.setLinearVelocity(new Vec2(0,0));
                //System.out.println("[BulletToCharacter] You've been hit by an Enemy (HP:"+mainChar.getHealth()+")");
                if(mainChar.getHealth()<=0){
                    mainChar.destroy();
                    //System.out.println("[BulletToCharacter] gg wp");
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
