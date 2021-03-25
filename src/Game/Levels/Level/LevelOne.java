package Game.Levels.Level;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import Game.Collisons.BulletToCharacter;
import Game.Items.MedPack;
import Game.Sensors.SaveSensorListener;
import Game.Controls.MainCharacterKeyboardController;
import Game.Controls.MouseController;
import Game.Game;
import Game.HOC.GameView;
import Game.Items.Pistol;
import Game.Levels.GameLevel;
import Game.Levels.Walls.Wall;
import Game.StepListeners.EnemyShoot;
import Game.Timers.EnShoot;
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;


public class LevelOne extends GameLevel {
    private Pistol pistol;
    private ArrayList<Enemy> enemyList;
    private ArrayList<Wall> walls = new ArrayList(7);
    private Timer timer;
    public LevelOne(Game game){
        super(game,game.getMusicVolume(), game.getSfxVolume());
        setName("LevelOne");
        getMainChar().setPosition(new Vec2(0f,-8f));
        pistol = new Pistol(this);
        pistol.setPosition(new Vec2(0f,0f));
        enemyList = new ArrayList<>(5);
        addThemeSong("assets/sounds/theme1.wav");
        /* WALLS */
        this.addWalls();
        /* ENEMIES */
        this.spawnEnemies();

        this.getSaveSen().getBody().setPosition(new Vec2(21f,17f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this,game));
        timer = new Timer(1200,new EnShoot(this.getDynamicBodies(),getMainChar()));
        timer.start();
    }

    /**
     * Constructor used to load a game world from save file
     * @param game Object the level is going to be set in
     * @param health health of the MainCharacter
     * @param points the score of the MainCharacter
     * @param ammo ammo to be set for the pistol of the Main Character
     * @param pos position to spawn MainCharacter in
     */
    public LevelOne(Game game,int health,int points,int ammo,Vec2 pos){
        super(game,game.getMusicVolume(), game.getSfxVolume());
        this.addWalls();
        this.setName("LevelOne");
        addThemeSong("assets/sounds/theme1.wav");
        getMainChar().setHealth(health);
        getMainChar().setPoints(points);
        if(ammo>0){
            getMainChar().setPistol(new Pistol(this,ammo));
        }
        getMainChar().setPosition(pos);

        this.getSaveSen().getBody().setPosition(new Vec2(21f,17f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this,game));
        timer = new Timer(1200,new EnShoot(this.getDynamicBodies(),getMainChar()));
        timer.start();
    }

    /**
     *  This function spawns enemies in the world
     */
    private void spawnEnemies(){
        enemyList.add(new Enemy(this,new Vec2(-11f,3f)));
        enemyList.add(new Enemy(this,new Vec2(7f,3f)));
        enemyList.add(new Enemy(this,new Vec2(0f,14f)));
        enemyList.add(new Enemy(this,new Vec2(-6f,14f)));
        enemyList.add(new Enemy(this,new Vec2(5f,14f)));
        for (int i=0;i<enemyList.size();i++){

            enemyList.get(i).addCollisionListener(new BulletToCharacter(getMainChar()));
        }
    }

    /**
     * This function puts walls in the world
     */
    private void addWalls(){
        //Bottom Wall
        walls.add(new Wall(this,22.5f,4.5f,0,-15.5f));
        //Left Wall
        walls.add(new Wall(this,3f,15.5f,-19.5f,4.5f));
        //Right Wall
        walls.add(new Wall(this, 3f,12f,19.5f,1f));
        /*Top Wall This wall is invisible (it's outside the user view) to make top boundary for characters
        to not walk out and bullet to be destroyed when they exit the screen */
        walls.add(new Wall(this,22.5f,4.5f,0,24.5f));
        walls.add(new Wall(this,6f,1f,-11f,0f));
        walls.add(new Wall(this,6f,1f,11f,0f));
        walls.add(new Wall(this,8f,1.5f,0f,10f));
    }
    /**
     * This method adds Controls (KeyListener and MouseController) to the view of this level
     * @param g the Game object that the controls are going to be added in, it is needed to have full functionality in the game's world
     */
    public void addControls(Game g){
        this.setKeyboard(new MainCharacterKeyboardController(getMainChar(),g,this));
        this.setMouse(new MouseController(getView(),getMainChar()));
        this.getView().addMouseListener(this.getMouse());
        this.getView().addKeyListener(this.getKeyboard());
    }

}
