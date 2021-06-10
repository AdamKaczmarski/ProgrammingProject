package Game.Characters;

import Game.Collisons.EnemyToWall;
import Game.Items.Pistol;
import Game.Levels.GameLevel;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Enemy extends Walker {
    private static final float charHeight=4f;
    private static final Shape enemyShape = new PolygonShape(-0.69f,-1.35f, 0.13f,-1.29f, 1.29f,0.26f, 0.58f,1.01f, 0.14f,1.37f, -0.71f,1.32f, -1.3f,0.65f);
    private static BodyImage enemyImage = new BodyImage("assets/images/badGuy.png", charHeight);
    private int health = 20;
    Pistol Pistol;

    /**
     * Creates the Enemy object
     * @param gameLevel the world the Enemy is going to be set in
     * @param vec the position where to spawn the enemy
     */
    public Enemy (GameLevel gameLevel, Vec2 vec){
        super(gameLevel,enemyShape);
        this.addImage(enemyImage);
        this.setName("Enemy");
        this.setClipped(true);
        this.addCollisionListener(new EnemyToWall());
        this.setPosition(vec);
        this.Pistol= new Pistol(gameLevel, true);
    }
    /* ACCESSORS */

    /**
     *
     * @return the amount of health on the object
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @return Pistol object
     */
    public Pistol getPistol(){return Pistol;}
    /* MUTATORS */

    /**
     *
     * @param pistol Pistol Object
     */
    public void setPistol(Pistol pistol) {
        Pistol = pistol;
    }

    /**
     *
     * @param health new amount of health
     */
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
            this.getPistol().shoot(((GameLevel)this.getWorld()),this, charHeight,0.85f,mainCharPos,"BulletEn");
        }
    }

}
