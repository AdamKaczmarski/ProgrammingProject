package Game.Characters;

import Game.Collisons.BulletToCharacter;
import Game.Items.Bullet;
import Game.Items.Pistol;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class MainCharacter extends Walker {
    private static final float height = 3f;
    private static final BodyImage image = new BodyImage("assets/images/mainCharacterPlaceholder.png", height);;
    private static Shape charShape = new PolygonShape(0.149f,0.975f, 0.775f,0.193f, 0.772f,-0.099f, 0.401f,-0.928f,
            -0.36f,-0.922f, -0.719f,-0.025f, -0.725f,0.163f, -0.14f,0.972f);
    private int health = 100;
    private int points;
    private Pistol pistol; //add pistol on pickup
    private World w;

    public MainCharacter(World world){
        super(world,charShape);
        addImage(image);
        setAlwaysOutline(true);
        setClipped(true);
        this.health=100;
        this.points=0;
        this.pistol = null; //NULL FOR A WHILE BUT WILL BE INITIATED TO A PISTOLL OBJECT WHEN THE CHARACTER PICKS IT UP
        this.w=world;
        setName("MainCharacter");
        addCollisionListener(new BulletToCharacter());
    }
    /* ACCESSORS */

    /**
     * Returns the value of the health field of the object
     * @return the amount of health
     */
    public int getHealth() {
        return this.health;
    }
    /**
     *
     */
    public Pistol getPistol () {return this.pistol;}

    /**
     *
     * @return
     */
    public int getPoints(){return this.points;}
    public World getW() {
        return w;
    }

    public static float getHeight() {
        return height;
    }
    /* MUTATORS */

    /**
     * Sets the health of the object ( take positive and negative numbers)
     * Negative - if the object is shot
     * Positive - if the object picks up a MedPack
     * @param chHealth
     */
    public void setHealth(int chHealth) {
        this.health += chHealth;
    }
    /**
     * Set Pistol
     */
    public void setPistol(Pistol pistol) {
        this.pistol = pistol;
    }
    /**
     *
     */
    public void setPoints(int addPoints) {this.points += this.getPoints()+addPoints;}
    /**
     * Shoot
     */
    public void charShoot(){
        //System.out.println(this.getPistol().getPicked());
        if (this.getPistol()!=null && this.getPistol().getPicked()){
            this.pistol.shoot(getW(),this,getHeight());
        }
    }
    /* METHODS */

}
