package Game.Items;

import Game.Collisons.BulletToBullet;
import city.cs.engine.*;

public class Bullet extends DynamicBody {
    //private final static Shape bulletShape = new PolygonShape(-1.52f,-2.26f, 0.06f,-2.24f, 0.49f,-1.85f, 0.47f,1.29f, -0.34f,2.32f, -1.05f,2.31f, -1.89f,1.24f, -1.89f,-1.85f);
    //huge hitbox for testing reasons ^^^^
    private final static Shape bulletShape = new PolygonShape(-0.376f,-0.368f, -0.268f,-0.466f, -0.004f,-0.46f, 0.094f,-0.376f, 0.108f,0.242f, -0.06f,0.464f, -0.204f,0.466f, -0.374f,0.246f);
    private final static BodyImage bulletImage = new BodyImage("assets/images/bullet.png");
    private final static int damage = 10;

    public Bullet(World w){
        super(w,bulletShape);
        this.addImage(bulletImage);
        this.setAlwaysOutline(true);
        this.setBullet(true);
        this.setName("Bullet");
        this.setClipped(true);
        this.addCollisionListener(new BulletToBullet());
    }

    /**
     * This method return the amount of the damage the bullet can provide
     * @return damage value
     */
    public int getDamage() {
        return damage;
    }
}
