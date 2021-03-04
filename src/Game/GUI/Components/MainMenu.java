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
    private BgPanel bgPanel;
    private JPanel mainPanel;
    private JLabel title;
    private JSlider volume;
    private Image menuBg;
    public MainMenu(Game g){
        super();

        //this.getMainPanel().setOpaque(false);

        menuBg =  new ImageIcon("assets/gifs/BgPanel.gif").getImage();

        //System.out.println(title.getSize());
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
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("is this called");
        g.drawImage(menuBg,0,0,this);
    }

}
