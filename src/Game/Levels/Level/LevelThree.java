package Game.Levels.Level;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import Game.Controls.MainCharacterKeyboardController;
import Game.Controls.MouseController;
import Game.Game;
import Game.HOC.GameView;
import Game.Items.Pistol;
import Game.Levels.GameLevel;
import Game.Levels.RotPills.RotPill;
import Game.Levels.Walls.Wall;
import Game.Sensors.SaveSensorListener;
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class LevelThree extends GameLevel {
    private GameView L3View;
    private ArrayList<Wall> walls = new ArrayList(15);
    private ArrayList<Enemy> enemyList = new ArrayList(15);
    private ArrayList<RotPill> rotPillList =new ArrayList(5);
    private SoundClip theme3;
    public LevelThree(Game game, MainCharacter mc) {
        super(game, game.getMusicVolume(), game.getSfxVolume());
        setName("LevelThree");
        addThemeSong("assets/sounds/theme3.wav");
        getMainChar().setPosition(new Vec2(21f, 17f));
        getMainChar().setPoints(mc.getPoints());
        getMainChar().setPistol(mc.getPistol());
        getMainChar().setHealth(mc.getHealth());
        mc.setPistol(null);
        this.getSaveSen().getBody().setPosition(new Vec2(-21f, -16f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this, game));
        Pistol pistol = new Pistol(this);
        pistol.setPosition(new Vec2(-20f,17f));
        this.addWalls();
        this.addRotPills();
    }

    /**
     * This contructor is used when user wants to jump straight to Level Three using the MainMenu
     */
    public LevelThree(Game game) {
        super(game,game.getMusicVolume(), game.getSfxVolume());
        setName("LevelThree");
        addThemeSong("assets/sounds/theme3.wav");
        getMainChar().setPosition(new Vec2(21f, 17f));
        getMainChar().setPistol(new Pistol(this,20));
        this.getSaveSen().getBody().setPosition(new Vec2(-21f, -16f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this, game));
        Pistol pistol = new Pistol(this);
        pistol.setPosition(new Vec2(-20f,17f));
        this.addWalls();
        this.addRotPills();
        this.getSaveSen().getBody().setPosition(new Vec2(-21f,-16f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this,game));
    }

    /**
     * This method adds Controls (KeyListener and MouseController) to the view of this level
     *
     * @param g the Game object that the controls are going to be added in, it is needed to have full functionality in the game's world
     */
    public void addControls(Game g){

        this.setKeyboard(new MainCharacterKeyboardController(getMainChar(),g,this));
        this.setMouse(new MouseController(getView(),getMainChar()));
        this.getView().addMouseListener(this.getMouse());
        this.getView().addKeyListener(this.getKeyboard());
    }

    public void addWalls() {
        walls.add(new Wall(this, 22.5f, 4.5f, 8f, -20.5f));
        //Right Wall
        walls.add(new Wall(this, 3f, 15f, 21.5f, -2f));
        //Left Wall
        walls.add(new Wall(this, 3f, 12f, -20.5f, 2f));
        /*Top Wall This wall is invisible (it's outside the user view) to m ake top boundary for characters
        to not walk out and bullet to be destroyed when they exit the screen */
        walls.add(new Wall(this, 22.5f, 4.5f, 0, 24.5f));
        walls.add(new Wall(this,1f,5f,-24,17f));
    }

    public void spawnEnemies() {

    }
    public void addRotPills(){
        for (int i=0;i<5;i++){
            rotPillList.add(new RotPill(this));
            rotPillList.get(i).setAngularVelocity(rotPillList.get(i).getAngularVelocity()+(i/5));
        }
        //rotPillList.get(1).setPosition(new Vec2(0,0));

    }
}
