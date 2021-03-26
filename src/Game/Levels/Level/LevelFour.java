package Game.Levels.Level;

import Game.Characters.Enemy;
import Game.Characters.FinalBoss;
import Game.Characters.MainCharacter;
import Game.Collisons.BulletToCharacter;
import Game.Controls.MainCharacterKeyboardController;
import Game.Controls.MouseController;
import Game.Game;
import Game.Items.Pistol;
import Game.Levels.GameLevel;
import Game.Levels.Walls.Wall;
import Game.Sensors.SaveSensorListener;
import Game.Timers.EnShoot;
import Game.Timers.FinalBossShoot;
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class LevelFour extends GameLevel {
    private ArrayList<Enemy> enemyList;
    private ArrayList<Wall> walls = new ArrayList(7);
    private Timer timer;
    private FinalBoss finalBoss;
    private SoundClip victoryMusic;

    /**
     * This construcor is used when user passes from Level Three to this one
     *
     * @param game
     * @param mc
     */
    public LevelFour(Game game, MainCharacter mc) {
        super(game, game.getMusicVolume(), game.getSfxVolume());
        setName("LevelFour");
        addThemeSong("assets/sounds/boss_fight.wav");
        getMainChar().setPosition(new Vec2(0f, -8f));
        getMainChar().setPoints(mc.getPoints());
        getMainChar().setPistol(mc.getPistol());
        getMainChar().getPistol().setAmmo(mc.getPistol().getAmmo());
        getMainChar().setHealth(mc.getHealth());
        mc.setPistol(null);
        this.addWalls();
        finalBoss = new FinalBoss(this);
        finalBoss.setPosition(new Vec2(0, 16f));
        finalBoss.setLinearVelocity(new Vec2(-3,0));
        timer = new Timer(1000, new FinalBossShoot(finalBoss,getMainChar()));
        timer.start();
        this.spawnItem("MedPack", new Vec2(10,-6));
        this.spawnItem("Pistol", new Vec2(-10,-6));

    }

    /**
     * This constructor is used when user loads level from Main Menu
     *
     * @param game
     */
    public LevelFour(Game game) {
        super(game, game.getMusicVolume(), game.getSfxVolume());
        setName("LevelFour");
        addThemeSong("assets/sounds/boss_fight.wav");
        getMainChar().setPosition(new Vec2(0f, -8f));
        getMainChar().setPistol(new Pistol(this, 20));
        this.addWalls();
        finalBoss = new FinalBoss(this);
        finalBoss.setPosition(new Vec2(0, 16f));
        finalBoss.setLinearVelocity(new Vec2(-3,0));
        timer = new Timer(1000, new FinalBossShoot(finalBoss,getMainChar()));
        timer.start();
        this.spawnItem("MedPack", new Vec2(10,-6));
        this.spawnItem("Pistol", new Vec2(-10,-6));

    }

    /**
     * Constructor used to load a game world from save file
     *
     * @param game   Object the level is going to be set in
     * @param health health of the MainCharacter
     * @param points the score of the MainCharacter
     * @param ammo   ammo to be set for the pistol of the Main Character
     * @param pos    position to spawn MainCharacter in
     */
    public LevelFour(Game game, int health, int points, int ammo, Vec2 pos) {
        super(game, game.getMusicVolume(), game.getSfxVolume());
        this.addWalls();
        this.setName("LevelFour");
        addThemeSong("assets/sounds/boss_fight.wav");
        getMainChar().setHealth(health);
        getMainChar().setPoints(points);
        if (ammo > 0) {
            getMainChar().setPistol(new Pistol(this, ammo));
        }
        getMainChar().setPosition(pos);
        timer = new Timer(1000, new FinalBossShoot(finalBoss,getMainChar()));
        timer.start();

    }

    /**
     * This method return FinalBoss Object
     *
     * @return FinalBoss object
     */
    public FinalBoss getFinalBoss() {
        return finalBoss;
    }

    public void setFinalBoss(FinalBoss finalBoss) {
        this.finalBoss = finalBoss;
    }

    /**
     * This function puts walls in the world
     */
    private void addWalls() {
        //Bottom Wall
        walls.add(new Wall(this, 22.5f, 4.5f, 0, -15.5f));
        //Left Wall
        walls.add(new Wall(this, 3f, 15.5f, -19.5f, 4.5f));
        //Right Wall
        walls.add(new Wall(this, 3f, 18f, 20f, 2f));
        /*Top Wall This wall is invisible (it's outside the user view) to make top boundary for characters
        to not walk out and bullet to be destroyed when they exit the screen */
        walls.add(new Wall(this, 22.5f, 4.5f, 0, 24.5f));
        walls.add(new Wall(this, 6f, 1f, -11f, 0f));
        walls.add(new Wall(this, 6f, 1f, 11f, 0f));
        walls.add(new Wall(this, 3f, 1.5f, 0f, 8f));
    }

    /**
     * This method adds Controls (KeyListener and MouseController) to the view of this level
     *
     * @param g the Game object that the controls are going to be added in, it is needed to have full functionality in the game's world
     */
    public void addControls(Game g) {
        this.setKeyboard(new MainCharacterKeyboardController(getMainChar(), g, this));
        this.setMouse(new MouseController(getView(), getMainChar()));
        this.getView().addMouseListener(this.getMouse());
        this.getView().addKeyListener(this.getKeyboard());
    }

}

