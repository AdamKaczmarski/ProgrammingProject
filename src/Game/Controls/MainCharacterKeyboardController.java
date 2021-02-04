package Game.Controls;

import Game.Characters.MainCharacter;
import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainCharacterKeyboardController implements KeyListener {
    private static final int WALKING_SPEED_X = 4;
    private static final int WALKING_SPEED_Y = 4;
    private MainCharacter mainChar;
    public MainCharacterKeyboardController(MainCharacter mC){
        this.mainChar=mC;
    }
    public void movementHandler(int dir){
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
            mainChar.addImage(new BodyImage("assets/images/mainCharacterPlaceholderRight.png", mainChar.getHeight()));

        } else {
            //Left Profile
            mainChar.addImage(new BodyImage("assets/images/mainCharacterPlaceholderLeft.png", mainChar.getHeight()));
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

