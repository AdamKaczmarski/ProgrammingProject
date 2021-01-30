package Game.Characters;

import Game.Collisons.BulletToCharacter;
import city.cs.engine.*;

public class Enemy extends Walker {
    private static final Shape enemyShape = new PolygonShape(0.149f,0.975f, 0.775f,0.193f, 0.772f,-0.099f, 0.401f,-0.928f,
            -0.36f,-0.922f, -0.719f,-0.025f, -0.725f,0.163f, -0.14f,0.972f);
    private static BodyImage enemyImage = new BodyImage("assets/images/badGuy.jpg", 3f);
    private int health = 20;
    public Enemy (World world){
        super(world,enemyShape);
        addImage(enemyImage);
        setAlwaysOutline(true);
        setName("Enemy");
        setClipped(true);
        addCollisionListener(new BulletToCharacter());
    }
    /* ACCESSORS */

    public int getHealth() {
        return health;
    }
    /* MUTATORS */

    public void setHealth(int health) {
        this.health = health;
    }
}
