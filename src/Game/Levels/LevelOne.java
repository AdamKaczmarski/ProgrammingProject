package Game.Levels;

import Game.Characters.*;
import Game.Collisons.BulletToWall;
import Game.Controls.MouseController;
import Game.Game;
import Game.HOC.GameView;
import Game.Items.Pistol;
import Game.Levels.Walls.Wall;
import city.cs.engine.*;
import city.cs.engine.Shape;
import com.sun.tools.javac.Main;
import org.jbox2d.common.Vec2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Game.Controls.MainCharacterKeyboardController;


import java.awt.*;

import static java.awt.Transparency.TRANSLUCENT;

public class LevelOne extends World {
    private GameView L1view;
    private MainCharacter mainChar;
    private Enemy enemy1;
    private Pistol pistol;
    private Pistol pistol2; //testing reasons

    //private static Wall w1;
    public LevelOne(){
        super();
        //Main Character
        mainChar = new MainCharacter(this);
        //mainChar.startWalking(-2);
        //Enemy
        enemy1 = new Enemy(this);
        enemy1.setPosition(new Vec2(8f,2f));
        //Pistol
        pistol = new Pistol(this);
        pistol.setPosition(new Vec2(-5f,0f));
        /* FOR TESTING */
        pistol2 = new Pistol(this);
        pistol2.setPosition(new Vec2(-8f,0f));
        /* /////////////////////////// */

        /* WALLS */
        //Bottom Wall
        Wall w1 = new Wall(this,22.5f,4.5f,0,-15.5f);
        //Left Wall
        Wall w2 = new Wall(this,3f,15.5f,-19.5f,4.5f);
        //Right Wall
        Wall w3 =  new Wall(this, 3f,12f,19.5f,1f);
        Shape sideWallShape = new BoxShape(3f,12f);
        /*Top Wall This wall is invisible (it's outside the user view) to make top boundary for characters
        to not walk out and bullet to be destroyed when they exit the screen */
        Wall w4 =  new Wall(this,22.5f,4.5f,0,24.5f);
        /* /////////////////////////// */
        //createSaveFile(mainChar); //IF CHARACTER MOVES TO A CERTAIN AREA RUN THIS METHOD
    }
    /* GETTERS */
    public MainCharacter getMC (){ return mainChar; }
    public GameView getL1View(){ return L1view; }
    public Enemy getEnemy1() { return enemy1; }
    /* SETTERS */
    public void setGameView(GameView v){
        this.L1view=v;
    }

    /**
     * This method adds Controls (KeyListener and MouseController) to the view of this level
     * @param mainChar it requires main character to control his actions - movement and shooting
     */
    public void addControls(MainCharacter mainChar){
        if (this.getL1View()!=null ) {
            L1view.addKeyListener(new MainCharacterKeyboardController(mainChar));
            L1view.addMouseListener(new MouseController(L1view));
        }
    }
    /**
     * This method creates a save file when main character enters a certain area which will move him to another level
     * Save file will contain every attribute that counts in the game
     * @param mainChar requires MainCharacter to access his attributes
     */
    public void createSaveFile(MainCharacter mainChar){
        try {
            //IT CREATES NEW FILE AND DOES OVERWRITE ALREADY CREATED FILE
            FileWriter writer = new FileWriter("save.txt");
            writer.write("mainChar health: "+ mainChar.getHealth());
            writer.write("\nmainChar points: "+mainChar.getPoints());
            //System.out.println(mainChar.getPistol());
            if(mainChar.getPistol()!=null) writer.write("\nmainCharPistol ammo: "+mainChar.getPistol().getAmmo());
            writer.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
