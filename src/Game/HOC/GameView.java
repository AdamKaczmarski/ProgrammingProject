package Game.HOC;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private Image bckgr;
    public GameView(World w, int width, int height){
        super(w,width,height);
        bckgr =  new ImageIcon("assets/images/background1.png").getImage();

    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(bckgr,0,0,this);

    }
}
