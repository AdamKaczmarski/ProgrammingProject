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
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;


public class LevelOne extends GameLevel {
    private GameView L1view;
    //private MainCharacter mainChar;
    private Pistol pistol;
    private ArrayList<Enemy> enemyList;
    private SoundClip theme1;
    public LevelOne(Game game){
        super(game);
        setName("LevelOne");
        getMainChar().setPosition(new Vec2(-6f,0f));
        pistol = new Pistol(this);
        pistol.setPosition(new Vec2(-4f,2f));
        enemyList = new ArrayList<>(5);
        addThemeSong("assets/sounds/theme1.wav");

        /* /////////////////////////// */
        /* WALLS */
        //Bottom Wall
        Wall w1 = new Wall(this,22.5f,4.5f,0,-15.5f);
        //Left Wall
        Wall w2 = new Wall(this,3f,15.5f,-19.5f,4.5f);
        //Right Wall
        Wall w3 =  new Wall(this, 3f,12f,19.5f,1f);
        /*Top Wall This wall is invisible (it's outside the user view) to make top boundary for characters
        to not walk out and bullet to be destroyed when they exit the screen */
        Wall w4 =  new Wall(this,22.5f,4.5f,0,24.5f);
        /* /////////////////////////// */
        /* ENEMIES */
        for (int i=0;i<5;i++){
            float y=getMainChar().getPosition().y+((float)Math.random()*12+8);
            float x= (float)Math.random()*37+-18;
            //Enemy e = new Enemy(this,new Vec2(x,y));;
            enemyList.add(new Enemy(this,new Vec2(x,y)));
            //MAKE THIS TO BE SET EVERY 3 STEPS(1.5seconds) OF THE GAME RUNNING
            enemyList.get(i).setLinearVelocity(new Vec2((getMainChar().getPosition().x-x)/9,(getMainChar().getPosition().y-y)/9));
            //enemyList.get(i).enemyShoot(getMainChar().getPosition());
            enemyList.get(i).addCollisionListener(new BulletToCharacter(getMainChar()));
        }

        this.getSaveSen().getBody().setPosition(new Vec2(21f,17f));
        this.getSaveSen().addSensorListener(new SaveSensorListener(this,game));
        this.addStepListener(new EnemyShoot(enemyList,getMainChar()));
    }
    /* GETTERS */
    public GameView getL1View(){ return L1view; }


    /* SETTERS */
    public void setGameView(GameView v){
        this.L1view=v;
        this.getL1View().setVMainChar(getMainChar());
    }

    /**
     * This method adds Controls (KeyListener and MouseController) to the view of this level
     * @param mainChar it requires main character to control his actions - movement and shooting
     */
    public void addControls(MainCharacter mainChar){
        if (this.getL1View()!=null ) {
            L1view.addKeyListener(new MainCharacterKeyboardController(mainChar));
            L1view.addMouseListener(new MouseController(L1view,mainChar));
        }
    }

}
