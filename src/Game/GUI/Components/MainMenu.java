package Game.GUI.Components;

import Game.Game;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private JButton playButton;
    private JButton selectLevelButton;
    private JButton quitButton;
    private JPanel mainPanel;
    private JLabel title;
    private JSlider volume;

    public MainMenu(Game g){
        super();
        setBackground(Color.WHITE);

        System.out.println(title.getSize());
        //playButton.addActionListener(e-> System.out.println("Play"));
        selectLevelButton.addActionListener(e-> System.out.println("SelectLevel"));
        quitButton.addActionListener(e->System.exit(0));
        setPreferredSize(new Dimension(800, 900));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                g.changeLevel("MainMenu",null);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
