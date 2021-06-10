package Game.GUI.Containers;

import Game.GUI.Components.Button;
import Game.Game;

import javax.swing.*;
import java.awt.*;

public class SelectLevelMenu extends JPanel {
    private Button level1;
    private Button level2;
    private Button level3;
    private Button level4;
    private Button back;
    private Image menuBg;
    private JLabel title;
    public SelectLevelMenu(Image bg,Game g, MainMenu m, Font f, String playPause){
        this.title = new JLabel();
        title.setText("The Game");
        title.setFont(f.deriveFont(f.getStyle() | Font.PLAIN,128));
        title.setBounds(150,64,800,130);
        this.add(title);
        this.menuBg=bg;
        this.setLayout(null);
        this.level1=new Button("Level One",300,200,300,40);
        this.level2=new Button("Level Two",300,250,300,40);
        this.level3=new Button("Level Three",300,300,300,40);
        this.level4=new Button("Level Four",300,350,300,40);
        this.back=new Button("<<< GO BACK",300,450,300,40);
        this.add(level1);
        this.add(level2);
        this.add(level3);
        this.add(level4);
        this.add(back);
        level1.addActionListener(e-> {
            g.changeLevel("MainMenu",null);
            this.setVisible(false);
        });
        level2.addActionListener(e->{
            g.changeLevel("LevelOneFromMenu",null);
            this.setVisible(false);
        });
        level3.addActionListener(e->{
            g.changeLevel("LevelTwoFromMenu",null);
            this.setVisible(false);
        });
        level4.addActionListener(e->{
            g.changeLevel("LevelThreeFromMenu",null);
            this.setVisible(false);
        });
        back.addActionListener(e->{
            m.setPanel(new BgPanel(bg,g,m,f,playPause));
            g.getFrame().add(m.getPanel(),BorderLayout.CENTER);
            this.setVisible(false);
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBg,0,0,this);

    }
}
