package Game.Characters;

import Game.Collisons.BulletToCharacter;
import Game.Items.Pistol;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;


public class MainCharacter extends Walker {
    private BodyImage imagePic;
    private static final Shape charShape = new PolygonShape(-0.71f,-1.85f, 0.44f,-1.85f, 1.57f,1.04f, 0.8f,1.66f, -0.15f,1.94f, -1.51f,1.28f, -0.89f,-1.18f);
    private final float height=4f;
    private int health;
    private int points;
    private Pistol pistol; //add pistol on pickup
    private World w;

    public MainCharacter(World world){
        super(world,charShape);
        this.imagePic = new BodyImage("assets/images/mainCharacterPlaceholderRight.png", height);
        //this.imagePic = new BodyImage("assets/images/mainCharLeft.png", height);
        this.addImage(imagePic);
        //this.setAlwaysOutline(true);
        this.setClipped(true);
        this.health=100;
        this.points=0;
        this.w=world;

        this.setName("MainCharacter");
        this.addCollisionListener(new BulletToCharacter(this));
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
     * Returns the reference to pistol object
     * @return Pistol object
     */
    public Pistol getPistol () {return this.pistol;}

    /**
     * Returns the amount of points
     * @return int points
     */
    public int getPoints(){return this.points;}

    /**
     * Returns the World that the character is created in
     * @return World object
     */
    public World getW() {
        return w;
    }

    /**
     * Returns the height of the character
     * @return float height
     */
    public float getHeight() {
        return height;
    }
    /**
     * Returns the image of the character
     * @return  BodyImage object
     */
    public BodyImage getImagePic() {
        return imagePic;
    }
    /* MUTATORS */

    /**
     * Sets the health of the object ( take positive and negative numbers)
     * Negative - if the object is shot
     * Positive - if the object picks up a MedPack
     * @param chHealth
     */
    public void setHealth(int chHealth) {
        this.health = chHealth;
        this.healthCheck();

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
    public void setPoints(int addPoints) {this.points = addPoints;}
    /**
     * Set image of the character
     * @param imagePic a new BodyImage object
     */
    public void setImagePic(BodyImage imagePic) {
        this.imagePic = imagePic;
    }

    /**
     * This method calls for shoot method of the Pistol object.
     * This method won't work when the pistol is not picked up or is not initialized.
     * @param mouseDir clicked mouse position to send the bullet in right way
     */
    public void charShoot(Vec2 mouseDir){
        if (this.getPistol()!=null && this.getPistol().getPicked()){
            this.pistol.shoot(getW(),this,getHeight(),mouseDir,"BulletMC");
        }
    }

    public void healthCheck(){
        if(this.getHealth()<=0){
            System.out.println("You died!");
            this.destroy();
        }
    }
}
