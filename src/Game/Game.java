package Game;
// Imports
import Game.HOC.GameView;
import Game.Levels.LevelOne;
import city.cs.engine.*;
import Game.HOC.Frame;

import javax.swing.*;


public class Game {
    //private World world;
    private LevelOne world;
    private GameView view;
    public Game(){
        //World runs the UI.MainMenu
        //world = new MainMenu();

        //FOR MILESTONE 1 DEVELOPMENT
        world = new LevelOne();
        //UserView
        world.setGravity(0f);
        System.out.println(world.getGravity());
        view = new GameView(world,900,800);
        // JFrame
        final Frame frame = new Frame(view);
        JFrame debugView = new DebugViewer(world,500,500);


        world.start();
    }
    /* GETTERS */
    public World getWorld() {
        return world;
    }

    public UserView getView() {
        return view;
    }

    public static void main(String[] args) {
        new Game();
    }
}
