package Game.GUI.Containers;

import javax.swing.*;
import java.awt.*;

import Game.GUI.ActionListeners.QuitListener;
import Game.GUI.Components.Button;
import Game.Game;

public class BgPanel extends JPanel {
    private Image menuBg;
    private JLabel title;
    private Button play;
    private Button selectLevel;
    private Button soundMenu;
    private Button quit;
    public BgPanel(Image bg, Game g, MainMenu m, Font f){
        this.menuBg =  bg;
        this.setLayout(null);
        this.title = new JLabel();
        title.setText("The Game");
        title.setFont(f.deriveFont(f.getStyle() | Font.PLAIN,128));

        title.setBounds(150,64,800,130);
        this.add(title);

        this.play =  new Button("Play",300,250,300,40);
        this.play.addActionListener(e-> {
            g.changeLevel("MainMenu",null);
            this.setVisible(false);
        });
        this.add(play);

        this.selectLevel =  new Button("Select Level",300,300,300,40);
        this.selectLevel.addActionListener(e->{
            m.setPanel(new SelectLevelMenu(bg,g,m,f));
            g.getFrame().add(m.getPanel(),BorderLayout.CENTER);
            this.setVisible(false);
        });
        this.add(selectLevel);

        this.soundMenu =  new Button("Adjust Sound",300,350,300,40);
        this.soundMenu.addActionListener(e->{
            m.setPanel(new SoundMenu(bg,g,m,f));
            g.getFrame().add(m.getPanel(),BorderLayout.CENTER);
            this.setVisible(false);
        });
        this.add(soundMenu);

        this.quit = new Button("Quit",300,450,300,40);
        quit.addActionListener(new QuitListener());
        this.add(quit);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBg,0,0,this);

    }
}
