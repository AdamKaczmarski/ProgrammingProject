package Game.Controls;

import Game.HOC.GameView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Math;

public class MouseController implements MouseListener {
    private GameView view;
    public MouseController(GameView v){
        this.view=v;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        view.requestFocus();
        float angle = (float) (Math.atan2(e.getXOnScreen(),e.getYOnScreen()));
        angle= (float) Math.toDegrees(angle);
        System.out.println(angle);
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
