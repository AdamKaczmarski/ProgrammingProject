package Game;
// Imports
import Game.HOC.GameView;
import Game.Levels.LevelOne;
import city.cs.engine.*;
import Game.HOC.Frame;

public class Game {
    //private World world;
    private World world;
    private GameView view;
    public Game(){
        //World runs the UI.MainMenu
        //world = new MainMenu();
        /*
        * COMMENTS FOR MILESTONE 1
        * THE GRAPHICS ARE PLACEHOLDERS
        * I MOSTLY FOCUSED ON THE MECHANICS OF THE GAME (COLLISIONS, SHOOTING ETC)
        * THEY GREY BOXES WILL BE REPLACES BY SOME GRAPHIC (EG. JUNKYARD ETC)
        * TO BE DONE LIST CAN BE FOUND IN notes.txt
        * */
        //FOR MILESTONE 1 DEVELOPMENT
        world = new LevelOne();
        world.setGravity(0f);
        //GameView
        view = new GameView(world,900,800);
        ((LevelOne)world).setGameView(view); //Adds the view to LevelOne class to make it accessible for objects
        ((LevelOne)world).addControls(((LevelOne)world).getMC()); //adds controls Mouse and Keyboard (which require view) to to control the MainCharacter,
        //it cannot be called withing the constructor, since the view is still null then

        // JFrame
        final Frame frame = new Frame(view);
        //JFrame debugView = new DebugViewer(world,1000,1000);
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
