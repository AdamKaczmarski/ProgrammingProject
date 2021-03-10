package Game.Controls;

import Game.Characters.MainCharacter;
import Game.GUI.Containers.BgPanel;
import Game.Game;
import Game.Levels.Level.LevelOne;
import city.cs.engine.Body;
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
    public MainCharacterKeyboardController(MainCharacter mC, Game g){
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
            //mainChar.addImage(new BodyImage("assets/images/mainCharacterPlaceholderRight.png",mainChar.getHeight()));
            mainChar.removeAllImages();
            mainChar.swapFixtures(0);
            mainChar.addImage(new BodyImage("assets/gifs/mainCharLeft.gif", mainChar.getHeight()));


        } else {
            //Left Profile
            //mainChar.addImage(new BodyImage("assets/images/mainCharacterPlaceholderLeft.png",mainChar.getHeight()));
            mainChar.removeAllImages();
            mainChar.swapFixtures(1);
            //mainChar.setCharShape(-0.76f,-1.92f, 1.4f,-1.91f, 1.53f,0.02f, 1.14f,1.9f, 0.08f,1.44f, -0.79f,1.02f, -1.46f,-0.3f);
            mainChar.addImage(new BodyImage("assets/gifs/mainCharRight.gif", mainChar.getHeight()));


        }
    }

    /**
     * This method is going to pause the game everytime an Escape key has beed pressed
     * It is also used to resume the game
     */
    private void pauseMenu(){
        System.out.println(gameInPlay);
        if (gameInPlay ==false){
            game.getLevel().stop();
            game.getLevel().getThemeSong().stop();
            ((LevelOne)game.getLevel()).getL1View().setFocusable(false);
            game.getMenu().setPanel(new BgPanel(new ImageIcon("assets/gifs/BgPanel.gif").getImage(),game,game.getMenu(),game.getFont(),"Resume"));
            game.getFrame().add(game.getMenu().getPanel(), BorderLayout.CENTER);
            game.getFrame().pack();
            game.getFrame().setVisible(true);
            //game.getMenu().getPanel().requestFocus();
            //game.getMenu().getPanel().grabFocus();
            //game.getMenu().getPanel().setVisible(true);
            //game.getMenu().getPanel().setFocusable(true);
        } else {
            game.getMenu().getPanel().setVisible(false);
            game.getLevel().start();
            game.getLevel().getThemeSong().resume();
        }

        /*game.getFrame().add(new BgPanel(new ImageIcon("assets/gifs/BgPanel.gif").getImage(),game,game.getMenu(),game.getFont(),"Resume"), BorderLayout.CENTER);
        //game.getFrame().pack();
        game.getFrame().setVisible(true);*/

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
            case KeyEvent.VK_ESCAPE: pauseMenu(); gameInPlay=!gameInPlay; break;
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

