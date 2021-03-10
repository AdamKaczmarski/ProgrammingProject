package Game.Characters;

import Game.Characters.Fixtures.mainCharFixture;
import Game.Collisons.BulletToCharacter;
import Game.Items.Pistol;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;


public class MainCharacter extends Walker {
    private BodyImage imagePic;
    private static Shape charShape = new PolygonShape(-0.76f,-1.92f, 1.4f,-1.91f, 1.53f,0.02f, 1.14f,1.9f, 0.08f,1.44f, -0.79f,1.02f, -1.46f,-0.3f);
    //private mainCharFixture fixtureRight = new mainCharFixture(this,new PolygonShape(-0.76f,-1.92f, 1.4f,-1.91f, 1.53f,0.02f, 1.14f,1.9f, 0.08f,1.44f, -0.79f,1.02f, -1.46f,-0.3f));
    private mainCharFixture fixture;

    private final float height=4f;
    private int health;
    private int points;
    private Pistol pistol; //add pistol on pickup
    private World w;

    public MainCharacter(World world){
        super(world);
        this.fixture= new mainCharFixture(this,new PolygonShape(-0.76f,-1.92f, 1.4f,-1.91f, 1.53f,0.02f, 1.14f,1.9f, 0.08f,1.44f, -0.79f,1.02f, -1.46f,-0.3f));
        //this.imagePic = new BodyImage("assets/images/mainCharacterPlaceholderRight.png", height);
        this.imagePic = new BodyImage("assets/gifs/mainCharRight.gif", height);
        this.addImage(imagePic);
        this.setAlwaysOutline(true);
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
    public mainCharFixture getFixture() {
        return fixture;
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

    public void setFixture(mainCharFixture fixture) {
        this.fixture = fixture;
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

    public void setCharShape(Shape charShape) {
        MainCharacter.charShape = charShape;
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

    /**
     * This method sets the correct Fixtures to the MainCharacter object depending on the direction it's going.
     * @param dir a parameter that indicates the direction the character is going 0 - going to the left (right profile) 1 - goign to the right (left profile)
     */
    public void swapFixtures(int dir){
        switch (dir){
            //right prof
            case 0: {
                this.getFixture().destroy();
                this.setFixture(new mainCharFixture(this, new PolygonShape(0.61f, -1.95f, -1.43f, -1.92f, -1.56f, -0.08f, -1.1f, 1.94f, 0.73f, 1.17f, 1.48f, -0.34f)));
                break;
            }

            //left prof
            case 1: {
                this.getFixture().destroy();
                this.setFixture(new mainCharFixture(this,new PolygonShape(-0.76f, -1.92f, 1.4f, -1.91f, 1.53f, 0.02f, 1.14f, 1.9f, 0.08f, 1.44f, -0.79f, 1.02f, -1.46f, -0.3f) ));
                break;
            }
        }
    }
}
