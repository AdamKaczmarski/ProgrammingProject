package Game.Levels;

import Game.Characters.Enemy;
import Game.Characters.FinalBoss;
import Game.Characters.MainCharacter;
import Game.Collisons.BulletToCharacter;
import Game.Controls.MouseController;
import Game.Game;
import Game.GameSaverLoader.GameSaverLoader;
import Game.HOC.GameView;

import Game.Items.MedPack;
import Game.Items.Pistol;
import Game.Levels.RotPills.RotPill;
import Game.Sensors.saveSensor;
import city.cs.engine.SoundClip;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameLevel extends World {
    private MainCharacter mainChar;
    private GameView view;
    private String name;
    private saveSensor saveSen;
    private SoundClip themeSong;
    private KeyListener keyboard;
    private MouseController mouse;
    private float musicVolume;
    private float sfxVolume;
    private boolean gameInPlay;

    public GameLevel(Game game, float musicVol,float sfxVol){
        super();
        this.musicVolume=musicVol;
        this.sfxVolume=sfxVol;
        mainChar = new MainCharacter(this);
        this.gameInPlay=true;
        this.setGravity(0f);
        this.saveSen=new saveSensor(this);

    }

    /**
     *
     * @return MainCharacter object
     */
    public MainCharacter getMainChar() {
        return mainChar;
    }

    /**
     *
     * @return GameView object
     */
    public GameView getView() {
        return view;
    }

    /**
     *
     * @return name of the level
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return SoundClip object, which is the theme song
     */
    public SoundClip getThemeSong(){return themeSong;}

    /**
     *
     * @return saveSensor object
     */
    public saveSensor getSaveSen() {
        return saveSen;
    }

    /**
     *
     * @return MouseController
     */
    public MouseController getMouse() {
        return mouse;
    }

    /**
     *
     * @return KeyListener
     */
    public KeyListener getKeyboard() {
        return keyboard;
    }

    /**
     *
     * @return float from 0.0  to 1.0
     */
    public float getMusicVolume() {
        return musicVolume;
    }

    /**
     *
     * @return float from 0.0  to 1.0
     */
    public float getSfxVolume() {
        return sfxVolume;
    }

    /**
     *
     * @return true if the game's simulation is running, false if it's stopeed
     */
    public boolean isGameInPlay() {
        return gameInPlay;
    }

    /**
     * Sets MainCharacter object
     * @param mainChar MainCharacter object
     */
    public void setMainChar(MainCharacter mainChar) {
        this.mainChar = mainChar;
    }

    /**
     *
     * @param view GameView Object
     */
    public void setView(GameView view) {
        this.view = view;
    }

    /**
     * Name of the level
     * @param n level's name
     */
    public void setName(String n){
        this.name=n;
    }

    /**
     *
     * @param keyboard KeyListener
     */
    public void setKeyboard(KeyListener keyboard) {
        this.keyboard = keyboard;
    }

    /**
     *
     * @param mouse MouseController
     */
    public void setMouse(MouseController mouse) {
        this.mouse = mouse;
    }
    /**
     * Music volume
     * @param sfxVolume float from 0.0 to 1.0
     */
    public void setSfxVolume(float sfxVolume) {
        this.sfxVolume = sfxVolume;
        if(getMainChar().getPistol() != null) getMainChar().getPistol().updateVolume(sfxVolume);
    }

    /**
     * Music volume
     * @param musicVolume float from 0.0 to 1.0
     */
    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
        if(musicVolume>0.0){
            this.getThemeSong().setVolume(musicVolume);
        } else {
            this.getThemeSong().stop();
        }
    }

    /**
     *
     * @param gameInPlay boolean true if the game is started, false if stopped
     *
     */
    public void setGameInPlay(boolean gameInPlay) {
        this.gameInPlay = gameInPlay;
    }


    /**
     * This method creates a save file when main character enters a certain area which will move him to another level
     * Save file will contain every attribute that counts in the game
     */
    public void createSaveFile() {
        try{
            GameSaverLoader.save(this);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    /**
     * Method used to spawn a single enemy.
     */
    public void spawnEnemy(int health, int ammo, Vec2 position){
        Enemy E = new Enemy(this,position);
        float y=getMainChar().getPosition().y+((float)Math.random()*12+8);
        float x= (float)Math.random()*37+-18;
        E.setLinearVelocity(new Vec2((getMainChar().getPosition().x-x)/9,(getMainChar().getPosition().y-y)/9));
        E.addCollisionListener(new BulletToCharacter(getMainChar()));
        E.setHealth(health);
        if (ammo>0) E.setPistol(new Pistol(this,ammo));
    }

    /**
     * Spawns the item in the level
     * @param item String that represents the name of an item it's either "MedPack" or "Pistol"
     * @param itemPos Vec2 position of an item
     */
    public void spawnItem(String item,Vec2 itemPos){
        if(item.equals("MedPack")){
            MedPack m = new MedPack(this);
            m.setPosition(itemPos);
        } else if (item.equals("Pistol")){
            Pistol p = new Pistol(this);
            p.setPosition(itemPos);
        }
    }

    /**
     * This function adds rotating Pillars to the level
     * @param health health of the pillar
     * @param aV angular velocity the pillar should have
     * @param pos Vec2 position of the pillar
     * @param lSpeed Linear Speed of the pillar
     */
    public void addRotPill(int health,float aV, Vec2 pos, Vec2 lSpeed){
        RotPill rp =  new RotPill(this);
        rp.setHealth(health);
        rp.setPosition(pos);
        rp.setAngularVelocity(aV);
        rp.setLinearVelocity(lSpeed);
    }
    /**
     * Adds theme song to the level, it's used in the contructor of every level
     */
    public void addThemeSong(String path){
        try {
            themeSong = new SoundClip(path);
            if(this.getMusicVolume()>0.0){
                themeSong.setVolume(this.getMusicVolume());
                themeSong.play();
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

}
