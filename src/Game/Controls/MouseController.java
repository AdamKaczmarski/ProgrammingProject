package Game.Controls;

import Game.Characters.MainCharacter;
import Game.HOC.GameView;
import jdk.swing.interop.SwingInterOpUtils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

public class MouseController implements MouseListener {
    private GameView view;
    private MainCharacter mainChar;
    public MouseController(GameView v, MainCharacter mc){
        this.view=v;
        this.mainChar=mc;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1) {
            mainChar.charShoot(view.viewToWorld(new Point2D.Float(e.getX(), e.getY())));
        } else if (e.getButton()==MouseEvent.BUTTON3){
            mainChar.meleeAttack();
        }
        view.requestFocus();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
