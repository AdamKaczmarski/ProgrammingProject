package Game.Characters;

import Game.Collisons.BulletToCharacter;
import Game.Collisons.EnemyToWall;
import Game.Items.Pistol;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Enemy extends Walker {
    private static final float charHeight=4f;
    private static final Shape enemyShape = new PolygonShape(-0.69f,-1.55f, 0.23f,-1.5f, 1.35f,0.03f, 0.73f,0.68f, -0.04f,1.18f, -0.82f,0.86f, -1.18f,0.46f);
    private static BodyImage enemyImage = new BodyImage("assets/images/badGuy.jpg", charHeight);
    private int health = 20;
    Pistol Pistol;
    World w;
    public Enemy (World world, Vec2 vec){
        super(world,enemyShape);
        this.addImage(enemyImage);
        this.setAlwaysOutline(true);
        this.setName("Enemy");
        this.setClipped(true);
        this.addCollisionListener(new EnemyToWall());
        this.setPosition(vec);
        this.Pistol= new Pistol(world, true);
        this.w=world;

    }
    /* ACCESSORS */
    public int getHealth() {
        return health;
    }
    public Pistol getPistol(){return Pistol;}
    public World getW(){return w;}
/*    public float getCharHeight{return charHeight;}*/
    /* MUTATORS */

    public void setPistol(Game.Items.Pistol pistol) {
        Pistol = pistol;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    /**
     * This method calls for shoot method of the Pistol object.
     * This method won't work when the pistol is not picked up or is not initialized.
     * @param mainCharPos position of the Main Character that the bullet will be shot towards
     */
    public void enemyShoot(Vec2 mainCharPos){
        if (this.getPistol()!=null && this.getPistol().getAmmo()>0){
            //mouseDir here is the position of the maincharacter
            this.getPistol().shoot(this.getW(),this, charHeight,mainCharPos,"BulletEn");
        }
        //IF AMMO IS 0 MAKE ENEMY RUN QUICKLY TOWARDS THE MAIN CHARACTER AND EXPLODE(ADD NEW COLLISION)
    }

}
