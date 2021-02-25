package Game;
// Imports
import Game.Characters.MainCharacter;
import Game.HOC.GameView;
import Game.Levels.Level.LevelOne;
import Game.Levels.Level.LevelThree;
import Game.Levels.Level.LevelTwo;
import city.cs.engine.*;
import Game.HOC.Frame;
import Game.Levels.GameLevel;

import javax.swing.*;

public class Game {
    private GameLevel level;
    private GameView view;
    public Game(){
        //World runs the UI.MainMenu
        //world = new MainMenu();
        level = new LevelOne(this);

        //GameView
        view = new GameView(level,900,800);
        ((LevelOne)level).setGameView(view); //Adds the view to LevelOne class to make it accessible for objects
        ((LevelOne)level).addControls(level.getMainChar()); //adds controls Mouse and Keyboard (which require view) to to control the MainCharacter,
        //it cannot be called withing the constructor, since the view is still null then

        // JFrame
        final Frame frame = new Frame(view);
        //JFrame debugView = new DebugViewer(level,1000,1000);
        level.start();

    }
    public void changeLevel(String currentLevel, MainCharacter mainChar){
        level.stop();
        if(currentLevel.equals("LevelFour")) {
            System.out.println("GG You've finished the game");
        }
        if (currentLevel.equals("LevelOne")){
            level=new LevelTwo(this,mainChar);
            view.setWorld(level);
            ((LevelTwo)level).setL2View(view);
            ((LevelTwo)level).addControls(((LevelTwo)level).getMainChar());
            level.start();
        }
        if (currentLevel.equals("LevelTwo")){
            System.out.println("[Game] moving to another lvl");
            level=new LevelThree(this,mainChar);
            view.setWorld(level);
            ((LevelThree)level).setL3View(view);
            ((LevelThree)level).addControls(((LevelThree)level).getMainChar());
            level.start();
        }/*
        if (currentLevel.equals("LevelThree")){
            level=new LevelFour();
            view.setWorld(level);
            ((LevelFour)level).setL4View(view);
        }*/


    }


    public static void main(String[] args) {
        new Game();
    }
}
