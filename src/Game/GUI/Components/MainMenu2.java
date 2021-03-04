package Game.GUI.Components;

import Game.Game;

import javax.swing.*;

public class MainMenu2 {
    private BgPanel bgPanel;
    public MainMenu2(Game g){
        bgPanel = new BgPanel(new ImageIcon("assets/gifs/BgPanel.gif").getImage());
    }

    public BgPanel getBgPanel() {
        return bgPanel;
    }
}
