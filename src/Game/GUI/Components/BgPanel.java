package Game.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class BgPanel extends JPanel {
    Image menuBg;
    public BgPanel(Image bg){
        this.menuBg =  bg;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBg,0,0,this);
    }
}
