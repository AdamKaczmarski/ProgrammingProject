package Game.Levels.Level;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import Game.Controls.MainCharacterKeyboardController;
import Game.Controls.MouseController;
import Game.Game;
import Game.HOC.GameView;
import Game.Levels.GameLevel;
import Game.Levels.Walls.Wall;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

public class LevelThree extends GameLevel {
    private GameView L3View;
    private ArrayList<Wall> walls = new ArrayList(15);
    private ArrayList<Enemy> enemyList= new ArrayList(15);

    public LevelThree(Game game, MainCharacter mc) {
        super(game);
        setName("LevelThree");
        getMainChar().setPosition(new Vec2(-21f,-16f));
        getMainChar().setPoints(mc.getPoints());
        getMainChar().setPistol(mc.getPistol());
        getMainChar().setHealth(mc.getHealth());
        System.out.println("YOU'RE ON LEVEL THREE");
    }

    public GameView getL3View() {
        return L3View;
    }

    public void setL3View(GameView view) {
        this.L3View=view;
    }
    /**
     * This method adds Controls (KeyListener and MouseController) to the view of this level
     * @param mainChar it requires main character to control his actions - movement and shooting
     */
    public void addControls(MainCharacter mainChar){
        if (this.getL3View()!=null ) {
            L3View.addKeyListener(new MainCharacterKeyboardController(mainChar));
            L3View.addMouseListener(new MouseController(L3View,mainChar));
        }
    }
}
