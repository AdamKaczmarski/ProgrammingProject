package Game.Levels.Level;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import Game.Collisons.BulletToCharacter;
import Game.Items.Pistol;
import Game.Sensors.SaveSensorListener;
import Game.Controls.MainCharacterKeyboardController;
import Game.Controls.MouseController;
import Game.Game;
import Game.HOC.GameView;
import Game.Items.MedPack;
import Game.Levels.GameLevel;
import Game.Levels.Walls.Wall;
import Game.StepListeners.EnemyShoot;
import Game.Timers.EnShoot;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.util.ArrayList;

public class LevelTwo extends GameLevel {
    private ArrayList<Wall> walls = new ArrayList(15);
    private ArrayList<Enemy> enemyList= new ArrayList(15);
    private MedPack medPack;
    private Timer timer;
    public LevelTwo(Game game, MainCharacter mc){
        super(game, game.getMusicVolume(), game.getSfxVolume());
        setName("LevelTwo");
        getMainChar().setPosition(new Vec2(-21f,-16f));
        getMainChar().setPoints(mc.getPoints());
        if (mc.getPistol()!=null){
            getMainChar().setPistol(new Pistol(this,mc.getPistol().getAmmo()));
        }

        mc.setPistol(null);
        getMainChar().setHealth(mc.getHealth());
        System.out.println("YOU'RE ON LEVEL TWO");
        this.addWalls();
        this.spawnEnemies();
        this.getSaveSen().getBody().setPosition(new Vec2(-22.5f,17f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this,game));
        medPack = new MedPack(this);
        medPack.setPosition(new Vec2(-14f,4f));
        addThemeSong("assets/sounds/theme2.wav");
        timer = new Timer(700,new EnShoot(this.getDynamicBodies(),getMainChar()));
        timer.start();

    }



    /**
     * This contructor is used when user wants to jump straight to Level Two using the MainMenu
     * @param game
     */
    public LevelTwo(Game game){
        super(game,game.getMusicVolume(), game.getSfxVolume());
        setName("LevelTwo");
        getMainChar().setPosition(new Vec2(-21f,-16f));
        getMainChar().setPistol(new Pistol(this,30));
        this.addWalls();
        this.spawnEnemies();
        this.getSaveSen().getBody().setPosition(new Vec2(-22.5f,17f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this,game));
        medPack = new MedPack(this);
        medPack.setPosition(new Vec2(-14f,4f));
        addThemeSong("assets/sounds/theme2.wav");
        timer = new Timer(700,new EnShoot(this.getDynamicBodies(),getMainChar()));
        timer.start();
    }

    public LevelTwo(Game game,int health,int points,int ammo,Vec2 pos){
        super(game,game.getMusicVolume(), game.getSfxVolume());
        this.addWalls();
        this.setName("LevelTwo");
        addThemeSong("assets/sounds/theme2.wav");
        getMainChar().setHealth(health);
        getMainChar().setPoints(points);
        if(ammo>0){
            getMainChar().setPistol(new Pistol(this,ammo));
        }
        getMainChar().setPosition(pos);

        this.getSaveSen().getBody().setPosition(new Vec2(-22.5f,17f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this,game));
        timer = new Timer(1200,new EnShoot(this.getDynamicBodies(),getMainChar()));
        timer.start();
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
    public void addWalls(){
        //Bottom Wall
        walls.add(new Wall(this,22.5f,4.5f,8f,-20.5f));
        //Right Wall
        walls.add(new Wall(this,3f,18f,24.5f,2f));
        //Left Wall
        walls.add(new Wall(this, 3f,12f,-21.5f,1f));
        /*Top Wall This wall is invisible (it's outside the user view) to make top boundary for characters
        to not walk out and bullet to be destroyed when they exit the screen */
        walls.add(new Wall(this,22.5f,4.5f,0,24.5f));
        //Middle wall
        walls.add(new Wall (this,16f,1f,-2.5f,0f));
        //First pillar
        walls.add(new Wall (this, 2f,2f,-14f,-9f));
        //Second pillar
        walls.add(new Wall (this,2f,2f,-6f,-12f));
        //Third
        walls.add(new Wall (this,2f,2f,-1,-8f));
        //Fourth
        walls.add(new Wall (this, 2f,2f, 8f,-6f));
        //5th
        walls.add(new Wall (this,2f,2f,14f,4f));
        //6th
        walls.add(new Wall(this,2f,2f,8f,8f));
        //7th
        walls.add(new Wall (this, 2f,2f,-1f,5f));
        //8th
        walls.add(new Wall(this,2f,2f,8f,18f));
        //9th
        walls.add(new Wall (this,2f,2f,-6f,12f));
        //10th
        walls.add(new Wall (this,2f,2f,-14f,9f));
    }
    public void spawnEnemies(){
        enemyList.add(new Enemy(this,new Vec2(-14f,-5f)));
        enemyList.add(new Enemy(this,new Vec2(-12f,-5f)));
        enemyList.add(new Enemy(this, new Vec2(-4f,-12f)));
        enemyList.add(new Enemy(this,new Vec2(3f,-8f)));
        enemyList.add(new Enemy(this,new Vec2(12f,-7f)));
        enemyList.add(new Enemy(this,new Vec2(15f,-7f)));
        enemyList.add(new Enemy(this, new Vec2(14f,8f)));
        enemyList.add(new Enemy(this, new Vec2(14f,13f)));
        enemyList.add(new Enemy(this, new Vec2(-5f, 6f)));
        enemyList.add(new Enemy(this, new Vec2(4f,18f)));
        enemyList.add(new Enemy(this, new Vec2(-8f,5f)));
        enemyList.add(new Enemy(this, new Vec2(-17f,16f)));
        for (int i=0;i<enemyList.size();i++){
            enemyList.get(i).setLinearVelocity(new Vec2(0,0));
            enemyList.get(i).addCollisionListener(new BulletToCharacter(this.getMainChar()));
        }
    }

}
