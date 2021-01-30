package Game.Levels;

import Game.Characters.*;
import Game.Collisons.BulletToWall;
import Game.Items.Pistol;
import Game.Levels.Walls.Wall;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import java.awt.*;

import static java.awt.Transparency.TRANSLUCENT;

public class LevelOne extends World {
    private final BulletToWall wallCollision = new BulletToWall();

    //private static Wall w1;
    public LevelOne(){
        super();
        //Main Character
        MainCharacter mainChar = new MainCharacter(this);
        mainChar.startWalking(-2);
        //Enemy
        Enemy enemy1 = new Enemy(this);
        enemy1.setPosition(new Vec2(8f,2f));
        //Pistol
        Pistol pistol = new Pistol(this);
        pistol.setPosition(new Vec2(-5f,0f));
        Pistol pistol2 = new Pistol(this);
        pistol2.setPosition(new Vec2(-8f,0f));

        // WALLS
        //Bottom Wall
        Wall w1 = new Wall(this,22.5f,4.5f,0,-15.5f);
        //Left Wall
        Wall w2 = new Wall(this,3f,15.5f,-19.5f,4.5f);
        //Right Wall
        Wall w3 =  new Wall(this, 3f,12f,19.5f,1f);
        Shape sideWallShape = new BoxShape(3f,12f);
        //Top Wall This wall is invisible (it's outside the user view) to make top boundary for characters to not walk out and bullet to be destroyed when they exit the screen
        Wall w4 =  new Wall(this,22.5f,4.5f,0,24.5f);
        // WALLS
        createSaveFile(mainChar); //IF CHARACTER MOVES TO A CERTAIN AREA RUN THIS METHOD

    }

    /* WHEN USER STEPS ON EXIT AREA (AND KILLS ALL ENEMIES??) MAKE A SAVE FILE THAT WILL DUMP HIS STATS INTO TXT TO BE LOADED IN ANOTHER LVL */
    public void createSaveFile(MainCharacter mainChar){
        try {
            System.out.println("dupa");
            //IT CREATES NEW FILE AND DOES OVERWRITE ALREADY CREATED FILE
            FileWriter writer = new FileWriter("save.txt");
            writer.write("mainChar health: "+ mainChar.getHealth());
            writer.write("\nmainChar points: "+mainChar.getPoints());
            System.out.println(mainChar.getPistol());
            if(mainChar.getPistol()!=null) writer.write("\nmainCharPistol ammo: "+mainChar.getPistol().getAmmo());
            writer.close();
        } catch (IOException e){
            System.out.println(e);
        }


    }


}
