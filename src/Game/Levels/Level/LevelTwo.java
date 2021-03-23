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
    private GameView L2View;
    private ArrayList<Wall> walls = new ArrayList(15);
    private ArrayList<Enemy> enemyList= new ArrayList(15);
    private MedPack medPack;
    private Timer timer;
    public LevelTwo(Game game, MainCharacter mc){
        super(game, game.getMusicVolume());
        setName("LevelTwo");
        getMainChar().setPosition(new Vec2(-21f,-16f));
        getMainChar().setPoints(mc.getPoints());
        getMainChar().setPistol(new Pistol(this,mc.getPistol().getAmmo()));
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
        //this.addStepListener(new EnemyShoot(enemyList,getMainChar(),0.7f));

        timer = new Timer(700,new EnShoot(enemyList,getMainChar()));
        timer.start();
    }

    /**
     * This contructor is used when user wants to jump straight to Level Two using the MainMenu
     * @param game
     */
    public LevelTwo(Game game){
        super(game,game.getMusicVolume());
        setName("LevelTwo");
        getMainChar().setPosition(new Vec2(-21f,-16f));
        getMainChar().setPistol(new Pistol(this,30));
        System.out.println("YOU'RE ON LEVEL TWO");
        this.addWalls();
        this.spawnEnemies();
        this.getSaveSen().getBody().setPosition(new Vec2(-22.5f,17f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this,game));
        medPack = new MedPack(this);
        medPack.setPosition(new Vec2(-14f,4f));
        addThemeSong("assets/sounds/theme2.wav");
        //this.addStepListener(new EnemyShoot(enemyList,getMainChar(),0.7f));

        timer = new Timer(700,new EnShoot(enemyList,getMainChar()));
        timer.start();
    }


    public GameView getL2View() {
        return L2View;
    }

    public void setL2View(GameView view) {
        this.L2View=view;
        this.getL2View().setVMainChar(getMainChar());
    }

    /**
     * This method adds Controls (KeyListener and MouseController) to the view of this level
     * @param mainChar it requires main character to control his actions - movement and shooting
     */
    public void addControls(MainCharacter mainChar, Game g){
        if (this.getL2View()!=null ) {
            L2View.addKeyListener(new MainCharacterKeyboardController(mainChar,g));
            L2View.addMouseListener(new MouseController(L2View,mainChar));
        }
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
