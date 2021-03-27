package Game.Items;

import Game.Collisons.PistolPickup;
import Game.Levels.GameLevel;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Pistol extends StaticBody {
    private static  Shape pistolShape = new PolygonShape(0.105f,-0.435f, 0.459f,-0.447f, 0.666f,-0.135f, 0.543f,0.381f, 0.123f,0.468f, -0.585f,0.468f, -0.639f,0.102f);
    private static BodyImage pistolImage = new BodyImage("assets/images/pistol.png",1.5f);
    private int ammo;
    private boolean picked=false;
    private SoundClip shootSound;
    private SoundClip noAmmoSound;
    private float sfxVolumePistol=0.0f;
    /**
     * This constructor creates an abstract Pistol object which will allow the character to shoot.
     * It's created because when picked up (collided) the CollisionDetector calls for destroy().
     * @param gameLevel The world that the pistol object is created in
     * @param picked Sets picked value to true
     */
    public Pistol(GameLevel gameLevel, boolean picked){
        super(gameLevel);
        this.setName("PickedPistol");
        this.ammo=10;
        this.picked=true;
        this.sfxVolumePistol=gameLevel.getSfxVolume();
        try {
            shootSound= new SoundClip("assets/sounds/shoot.wav");
            noAmmoSound =  new SoundClip("assets/sounds/no_ammo.wav");
            if(gameLevel.getSfxVolume()>0.0) {
                shootSound.setVolume(gameLevel.getSfxVolume());
                noAmmoSound.setVolume(gameLevel.getSfxVolume());
            }
        }  catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    /**
     * Constructor created for creating new Pistol when new level is loaded. the ammo stat is going to be read from file.
     */
    public Pistol(GameLevel gameLevel,int ammo){ //add World world in the constructor and in super but when the pistol is going to be created on new level
        super(gameLevel);
        this.ammo=ammo;
        this.setName("PickedPistol");
        this.picked=true;
        this.sfxVolumePistol=gameLevel.getSfxVolume();
        try {
            shootSound= new SoundClip("assets/sounds/shoot.wav");
            noAmmoSound =  new SoundClip("assets/sounds/no_ammo.wav");
            if(gameLevel.getSfxVolume()>0){
                shootSound.setVolume(gameLevel.getSfxVolume());
                noAmmoSound.setVolume(gameLevel.getSfxVolume());
            }
        }  catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    /**
     * This constructor creates a visual Pistol object on the map
     * It gets destroyed after the collision (pick up) is detected
     * This constructs pistol object on the map, after picked it gets destroyed and abstract pistol gets created
     * @param level The world that the pistol object is created in
     */
    public Pistol (GameLevel level){
        super(level,pistolShape);
        addImage(pistolImage);
        setName("Pistol");
        addCollisionListener(new PistolPickup());
    }
    /**
     * Returns the amount of ammo
     * @return the amount of ammo
     */
    public int getAmmo() {
        return ammo;
    }

    /**
     * Returns the value of picked
     * @return Value of picked
     */
    public boolean getPicked(){
        return picked;
    }

    /**
     *
     * @return float from 0. to 1.0
     */
    public float getSfxVolumePistol() {
        return sfxVolumePistol;
    }

    /**
     *
     * @return The SoundClip that represents no ammo
     */
    public SoundClip getNoAmmoSound() {
        return noAmmoSound;
    }

    /**
     *
     * @return The SoundClip that represents shooting
     */
    public SoundClip getShootSound() {
        return shootSound;
    }

    /**
     * Set the ammo of a pistol [by finding ammo crate or killing an enemy]
     * @param addAmmo the amount of ammo that will be added to the ammo field
     */
    public void setAmmo(int addAmmo) {
        this.ammo=addAmmo;
    }

    public void updateVolume(float sfcVol){
        if(sfcVol>0){
            this.getNoAmmoSound().setVolume(sfcVol);
            this.getShootSound().setVolume(sfcVol);

        }
    }

    /**
     * Shooting method
     * @param bulletName specifies the name of the bullet that's being shot (Enemy Bullet, MC bullet etc)
     * @param charHeight the height of the character so that the bullet will be placed next to the character, not in it
     * @param db the walker object
     * @param mouseDir position of the click, to set the direction of the buller
     * @param level World object needed to create the bullet
     */
    public void shoot(GameLevel level, Walker db, float charHeight,float gap, Vec2 mouseDir, String bulletName){
        if (level.isRunning()) {
            if (this.getAmmo() > 0) {
                if(this.getSfxVolumePistol()>0.0) {
                    shootSound.stop();
                    shootSound.play();
                }

                Bullet b = new Bullet(level);
                b.setName(bulletName);
                if (mouseDir.x >= db.getPosition().x && mouseDir.y >= db.getPosition().y) {
                    if (mouseDir.y < (db.getPosition().y + charHeight / 2) && mouseDir.y > (db.getPosition().y)) {
                        b.setPosition(new Vec2(db.getPosition().x + gap, db.getPosition().y));
                        b.setLinearVelocity(new Vec2(mouseDir.x + gap, mouseDir.y - db.getPosition().y).mul(1.5f));
                    } else {
                        b.setPosition(new Vec2(db.getPosition().x + gap, db.getPosition().y + (charHeight / 2)));
                        b.setLinearVelocity(new Vec2(mouseDir.x - db.getPosition().x, mouseDir.y - db.getPosition().y).mul(1.5f));
                    }
                }
                if (mouseDir.x >= db.getPosition().x && mouseDir.y <= db.getPosition().y) {
                    if (mouseDir.y < (db.getPosition().y) && mouseDir.y > (db.getPosition().y - (charHeight / 2))) {
                        b.setPosition(new Vec2(db.getPosition().x + gap, db.getPosition().y));
                        b.setLinearVelocity(new Vec2(Math.abs(mouseDir.x), 0).mul(4f));
                    } else {
                        b.setPosition(new Vec2(db.getPosition().x + gap, db.getPosition().y - (charHeight / 2)));
                        b.setLinearVelocity(new Vec2(mouseDir.x - db.getPosition().x, mouseDir.y - db.getPosition().y).mul(1.5f));
                    }
                }
                if (mouseDir.x <= db.getPosition().x && mouseDir.y <= db.getPosition().y) {
                    if (mouseDir.y < (db.getPosition().y) && mouseDir.y > (db.getPosition().y - (charHeight / 2))) {
                        b.setPosition(new Vec2(db.getPosition().x - gap, db.getPosition().y));
                        b.setLinearVelocity(new Vec2(mouseDir.x-gap,mouseDir.y-db.getPosition().y).mul(1.5f));
                    } else {
                        b.setPosition(new Vec2(db.getPosition().x - gap, db.getPosition().y - (charHeight / 2)));
                        b.setLinearVelocity(new Vec2(mouseDir.x - db.getPosition().x, mouseDir.y - db.getPosition().y).mul(1.5f));
                    }

                }
                if (mouseDir.x <= db.getPosition().x && mouseDir.y >= db.getPosition().y) {
                    if (mouseDir.y < (db.getPosition().y + (charHeight / 2)) && mouseDir.y > (db.getPosition().y)) {
                        b.setPosition(new Vec2(db.getPosition().x - gap, db.getPosition().y));
                        b.setLinearVelocity(new Vec2(mouseDir.x + gap, 0).mul(1.5f));
                    } else {
                        b.setPosition(new Vec2(db.getPosition().x - gap, db.getPosition().y + (charHeight / 2)));
                        b.setLinearVelocity(new Vec2(mouseDir.x - db.getPosition().x, mouseDir.y - db.getPosition().y).mul(1.5f));
                    }

                }

                b.setAngleDegrees((float) Math.toDegrees(Math.atan2(mouseDir.y, mouseDir.x)) - 90); //USE THIS TO ROTATE THE BULLET OBJECT (shape and image)
                this.setAmmo(this.getAmmo() - 1);


            }
        }
        if(this.getAmmo()<=0) {
            if(this.getSfxVolumePistol()>0){
                noAmmoSound.stop();
                noAmmoSound.play();
            }

        }
        }


}
