package Game.GUI.ActionListeners;

import Game.GUI.Containers.BgPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ResumePauseMenuListener implements KeyListener {
    private BgPanel bg;
    public ResumePauseMenuListener(BgPanel bg){
        this.bg=bg;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
            System.out.println("Esca");
        }
    }
}
