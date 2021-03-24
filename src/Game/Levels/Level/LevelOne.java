package Game.Levels.Level;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import Game.Collisons.BulletToCharacter;
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
    private ArrayList<Wall> walls = new ArrayList(4);
    private Timer timer;
    public LevelOne(Game game){
        super(game,game.getMusicVolume(), game.getSfxVolume());
        setName("LevelOne");
        getMainChar().setPosition(new Vec2(-6f,0f));
        pistol = new Pistol(this);
        pistol.setPosition(new Vec2(-4f,2f));
        enemyList = new ArrayList<>(5);
        addThemeSong("assets/sounds/theme1.wav");

        /* WALLS */
        this.addWalls();
        /* ENEMIES */
        this.spawnEnemies();

        this.getSaveSen().getBody().setPosition(new Vec2(21f,17f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this,game));

        //this.addStepListener(new EnemyShoot(enemyList,getMainChar(),1.5f)); OLD USAGE OF STEP LISTENER TO MAKE ENEMY SHOOT

        timer = new Timer(1200,new EnShoot(enemyList,getMainChar()));
        timer.start();

    }

    public void spawnEnemies(){
        for (int i=0;i<5;i++){
            float y=getMainChar().getPosition().y+((float)Math.random()*12+8);
            float x= (float)Math.random()*37+-18;
            enemyList.add(new Enemy(this,new Vec2(x,y)));
            enemyList.get(i).setLinearVelocity(new Vec2((getMainChar().getPosition().x-x)/9,(getMainChar().getPosition().y-y)/9));
            enemyList.get(i).addCollisionListener(new BulletToCharacter(getMainChar()));
        }
    }
    public void addWalls(){
        //Bottom Wall
        walls.add(new Wall(this,22.5f,4.5f,0,-15.5f));
        //Left Wall
        walls.add(new Wall(this,3f,15.5f,-19.5f,4.5f));
        //Right Wall
        walls.add(new Wall(this, 3f,12f,19.5f,1f));
        /*Top Wall This wall is invisible (it's outside the user view) to make top boundary for characters
        to not walk out and bullet to be destroyed when they exit the screen */
        walls.add(new Wall(this,22.5f,4.5f,0,24.5f));
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
