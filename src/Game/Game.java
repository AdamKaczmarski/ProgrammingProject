package Game;
// Imports
import Game.Characters.MainCharacter;
import Game.GUI.Components.BgPanel;
import Game.GUI.Components.MainMenu;
import Game.GUI.Components.MainMenu2;
import Game.HOC.GameView;
import Game.Levels.Level.LevelOne;
import Game.Levels.Level.LevelThree;
import Game.Levels.Level.LevelTwo;
import Game.Levels.GameLevel;

import javax.swing.*;
import java.awt.*;

public class Game {
    private GameLevel level;
    private GameView view;
    private JFrame frame;
    public Game(){
        frame = new JFrame("The Game");
        frame.setPreferredSize(new Dimension(900,800));
        frame.setName("MainMenu");
        //JFrame debugView = new DebugViewer(level,1000,1000);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        //BgPanel bg = new BgPanel();
        //MainMenu menu =  new MainMenu(this,frame);
        MainMenu2 menu = new MainMenu2(this);

        frame.add(menu.getBgPanel(), BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);


    }
    public void changeLevel(String currentLevel, MainCharacter mainChar){

        if (level!=null){
            level.stop();
            level.getThemeSong().stop();
        }
        if(currentLevel.equals("LevelFour")) {
            System.out.println("GG You've finished the game");
        }
        if (currentLevel.equals("MainMenu")){

            //
            level=new LevelOne(this);
            view = new GameView(level,900,800,level.getMainChar());
            //frame = new JFrame("The Game");
            // quit the application when the game window is closed
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setLocationByPlatform(true);
            // display the world in the window
            frame.add(view);
            // don't let the game window be resized
            frame.setResizable(false);
            // size the game window to fit the world view
            frame.pack();
            // make the window visible
            frame.setVisible(true);

            // view = new GameView(level,900,800,level.getMainChar());
            getFrame().add(view);
            view.setWorld(level);
            ((LevelOne)level).setGameView(view);
            ((LevelOne)level).addControls(level.getMainChar());
            level.start();
        }
        if (currentLevel.equals("LevelOne")){
            level=new LevelTwo(this,mainChar);
           // view = new GameView(level,900,800,level.getMainChar());
            view.setWorld(level);
            ((LevelTwo)level).setL2View(view);
            ((LevelTwo) level).getL2View().setBckgr(new ImageIcon("assets/images/background2.png").getImage());
            ((LevelTwo)level).addControls(level.getMainChar());
            level.start();
        }
        if (currentLevel.equals("LevelTwo")){

            System.out.println("[Game] moving to another lvl");
            level=new LevelThree(this,mainChar);
            view.setWorld(level);
            ((LevelThree)level).setL3View(view);
            ((LevelThree)level).addControls(level.getMainChar());
            level.start();
        }/*
        if (currentLevel.equals("LevelThree")){

            level=new LevelFour();
            view.setWorld(level);
            ((LevelFour)level).setL4View(view);
        }*/


    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        new Game();
    }
}
