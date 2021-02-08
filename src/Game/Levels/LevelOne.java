package Game.Levels;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import Game.Controls.MainCharacterKeyboardController;
import Game.Controls.MouseController;
import Game.HOC.GameView;
import Game.Items.Pistol;
import Game.Levels.Walls.Wall;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class LevelOne extends World {
    private GameView L1view;
    private MainCharacter mainChar;
    private Pistol pistol;
    private ArrayList<Enemy> enemyList;
    public LevelOne(){
        super();
        //Main Character
        mainChar = new MainCharacter(this);
        mainChar.setPosition(new Vec2(-3f,0f));

        enemyList = new ArrayList<>(5);


        //Pistol
        pistol = new Pistol(this);
        pistol.setPosition(new Vec2(-5f,0f));
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
            float y=mainChar.getPosition().y+((float)Math.random()*12+8);
            float x= (float)Math.random()*37+-18;
            //Enemy e = new Enemy(this,new Vec2(x,y));;
            enemyList.add(new Enemy(this,new Vec2(x,y)));
            //MAKE THIS TO BE SET EVERY 3 STEPS(1.5seconds) OF THE GAME RUNNING
            enemyList.get(i).setLinearVelocity(new Vec2((mainChar.getPosition().x-x)/9,(mainChar.getPosition().y-y)/9));
            enemyList.get(i).enemyShoot(mainChar.getPosition());
        }
        //createSaveFile(mainChar); //IF CHARACTER MOVES TO A CERTAIN AREA RUN THIS METHOD
    }
    /* GETTERS */
    public MainCharacter getMC (){ return mainChar; }
    public GameView getL1View(){ return L1view; }
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
            L1view.addMouseListener(new MouseController(L1view,mainChar));
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
