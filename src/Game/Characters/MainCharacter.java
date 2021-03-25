package Game.Characters;

import Game.Characters.Fixtures.mainCharFixture;
import Game.Collisons.BulletToCharacter;
import Game.Items.Pistol;
import Game.Levels.GameLevel;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainCharacter extends Walker {
    private BodyImage imagePic;
    private static Shape charShape = new PolygonShape(-0.76f,-1.92f, 1.4f,-1.91f, 1.53f,0.02f, 1.14f,1.9f, 0.08f,1.44f, -0.79f,1.02f, -1.46f,-0.3f);
    //private mainCharFixture fixtureRight = new mainCharFixture(this,new PolygonShape(-0.76f,-1.92f, 1.4f,-1.91f, 1.53f,0.02f, 1.14f,1.9f, 0.08f,1.44f, -0.79f,1.02f, -1.46f,-0.3f));
    private mainCharFixture fixture;

    private final float height=4f;
    private int health;
    private int points;
    private Pistol pistol;
    private Graphics2D graphics;
    private SoundClip gameOver;
    public MainCharacter(GameLevel gameLevel){
        super(gameLevel);

        this.fixture= new mainCharFixture(this,new PolygonShape(-0.76f,-1.92f, 1.4f,-1.91f, 1.53f,0.02f, 1.14f,1.9f, 0.08f,1.44f, -0.79f,1.02f, -1.46f,-0.3f));
        //this.imagePic = new BodyImage("assets/images/mainCharacterPlaceholderRight.png", height);
        this.imagePic = new BodyImage("assets/gifs/mainCharRight.gif", height);
        this.addImage(imagePic);
        this.setClipped(true);
        this.health=100;
        this.points=0;
        this.setName("MainCharacter");
        this.addCollisionListener(new BulletToCharacter(this));
        try {
            gameOver = new SoundClip("assets/sounds/game_over.wav");
            gameOver.setVolume(gameLevel.getMusicVolume());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
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
    /**
     *
     */
    public Graphics2D getGraphics() {
        return graphics;
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
     * Changes the fixture of the character depending on his movement direction (left/right)
     * @param fixture
     */
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
    /**
     *
     */
    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    /**
     * This method calls for shoot method of the Pistol object.
     * This method won't work when the pistol is not picked up or is not initialized.
     * @param mouseDir clicked mouse position to send the bullet in right way
     */
    public void charShoot(Vec2 mouseDir){
        if (this.getPistol()!=null && this.getPistol().getPicked()){
            this.pistol.shoot(((GameLevel)this.getWorld()),this,getHeight(),mouseDir,"BulletMC");
        }
    }

    public void meleeAttack(){
        List<DynamicBody> bodies = this.getWorld().getDynamicBodies();
        for(int i=0;i<bodies.size();i++){
           if(bodies.get(i) instanceof Enemy){
               if (Math.abs(this.getPosition().x-bodies.get(i).getPosition().x)<4 && Math.abs(this.getPosition().y-bodies.get(i).getPosition().y)<4){
                  /* Image ex = new ImageIcon("assets/gifs/explosion.gif").getImage();
                   this.getGraphics().
                   this.getGraphics().drawImage(ex,200,200,((GameLevel)this.getWorld()).getView());*/
                   ((Enemy) bodies.get(i)).setHealth(((Enemy) bodies.get(i)).getHealth()-10);
                   if(((Enemy) bodies.get(i)).getHealth()<=0){
                       ((Enemy) bodies.get(i)).setPistol(null);
                       bodies.get(i).destroy();
                       this.setPoints(this.getPoints()+10);
                       this.getPistol().setAmmo(this.getPistol().getAmmo()+2);
                   }

               }
           } else if (bodies.get(i) instanceof FinalBoss){
               if (Math.abs(this.getPosition().x-bodies.get(i).getPosition().x)<4 && Math.abs(this.getPosition().y-bodies.get(i).getPosition().y)<4){
                    ((FinalBoss) bodies.get(i)).setHealth(((FinalBoss) bodies.get(i)).getHealth()-10);
                   if(((FinalBoss) bodies.get(i)).getHealth()<=0){
                       ((FinalBoss) bodies.get(i)).setPistol(null);
                       bodies.get(i).destroy();
                       this.setPoints(this.getPoints()+100);
                   }
               }
        }
    }}

    /**
     *
     */
    public void healthCheck(){
        if(this.getHealth()<=0){
            System.out.println("You died!");
            ((GameLevel)this.getWorld()).getThemeSong().stop();

            gameOver.play();
            this.destroy();
            this.getWorld().stop();
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
