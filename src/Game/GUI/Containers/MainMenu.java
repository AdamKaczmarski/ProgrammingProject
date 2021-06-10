package Game.GUI.Containers;

import Game.Game;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private JPanel panel;
    public MainMenu(Game g, Font font, String playPause){
        panel = new BgPanel(new ImageIcon("assets/gifs/BgPanel.gif").getImage(),g,this,font,playPause);
    }


    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
