package Game.Controls;

import Game.Characters.MainCharacter;
import Game.GUI.Containers.BgPanel;
import Game.Game;
import Game.Levels.GameLevel;
import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainCharacterKeyboardController implements KeyListener {
    private static final int WALKING_SPEED_X = 8;
    private static final int WALKING_SPEED_Y = 8;
    private boolean gameInPlay;
    private MainCharacter mainChar;
    private Game game;
    public MainCharacterKeyboardController(MainCharacter mC, Game g, GameLevel level){
        this.mainChar=mC;
        this.game=g;
        this.gameInPlay=true;
    }

    /**
     * This method is used to be called within KeyListener to set the movement of the Main Character
     * It is created to allow the player to move his character diagonally and without any "breaks" when switching the keys or releasing them
     * It also checks for the direction on x-axis of the character to replace the Image with a proper graphic to keep the perception of moving.
     * @param dir number <1;6> which sets the direction x(1 - go left, 2 - go right)/y(3 - go up, 4 - go down); (5 - stop x-axis movement, 6- stop y-axis movement)
     */
    private void movementHandler(int dir){
        switch (dir) {
            case 1:
                mainChar.setLinearVelocity(new Vec2(-WALKING_SPEED_X, mainChar.getLinearVelocity().y));
                break;
            case 2:
                mainChar.setLinearVelocity(new Vec2(WALKING_SPEED_X, mainChar.getLinearVelocity().y));
                break;
            case 3:
                mainChar.setLinearVelocity(new Vec2(mainChar.getLinearVelocity().x, WALKING_SPEED_Y));
                break;
            case 4:
                mainChar.setLinearVelocity(new Vec2(mainChar.getLinearVelocity().x, -WALKING_SPEED_Y));
                break;
            case 5:
                mainChar.setLinearVelocity(new Vec2(0, mainChar.getLinearVelocity().y));
                break; //WHEN A OR D RELEASE
            case 6:
                mainChar.setLinearVelocity(new Vec2(mainChar.getLinearVelocity().x, 0));
                break; // W OR S RELEASED
        }
        if(mainChar.getLinearVelocity().x>0) {
            //Right Profile
            mainChar.removeAllImages();
            mainChar.swapFixtures(0);
            mainChar.addImage(new BodyImage("assets/gifs/mainCharLeft.gif", mainChar.getHeight()));
        } else {
            //Left Profile
            mainChar.removeAllImages();
            mainChar.swapFixtures(1);
            mainChar.addImage(new BodyImage("assets/gifs/mainCharRight.gif", mainChar.getHeight()));
        }
    }

    /**
     * This method is going to pause the game everytime an Escape key has beed pressed
     * It is also used to resume the game
     */
    public void pauseMenu(){
        game.getLevel().setGameInPlay(!game.getLevel().isGameInPlay());
        if (!game.getLevel().isGameInPlay()){
            game.getLevel().stop();
            game.getLevel().getThemeSong().pause();
            game.getLevel().getView().setVisible(false);
            game.getMenu().setPanel(new BgPanel(new ImageIcon("assets/gifs/BgPanel.gif").getImage(),game,game.getMenu(),game.getFont(),"Resume"));
            game.getFrame().add(game.getMenu().getPanel(), BorderLayout.CENTER);
            game.getFrame().pack();
            game.getFrame().setVisible(true);
            //game.getFrame().toFront();
            game.getFrame().requestFocus();
        }
    }
    // STARTS THE MOVEMENT
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_A: movementHandler(1); break;
            case KeyEvent.VK_D: movementHandler(2); break;
            case KeyEvent.VK_W: movementHandler(3); break;
            case KeyEvent.VK_S: movementHandler(4); break;
            case KeyEvent.VK_ESCAPE: pauseMenu(); break;
        }
    }
    // STOPS THE MOVEMENT
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_A:
            case KeyEvent.VK_D: movementHandler(5); break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_S: movementHandler(6);break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}

