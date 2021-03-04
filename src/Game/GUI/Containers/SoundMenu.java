package Game.GUI.Containers;

import Game.GUI.Components.Button;
import Game.Game;

import javax.swing.*;
import java.awt.*;

public class SoundMenu extends JPanel {
    private Image menuBg;
    private JLabel title;
    private JLabel sfx;
    private JLabel music;
    private JSlider sfcVol;
    private JSlider musicVol;
    private JButton back;
    public SoundMenu(Image bg, Game g, MainMenu m, Font f){
        this.menuBg =  bg;
        this.setLayout(null);
        this.title = new JLabel();
        title.setText("The Game");
        title.setFont(f.deriveFont(f.getStyle() | Font.PLAIN,128));
        title.setBounds(150,64,800,130);
        this.add(title);

        sfx = new JLabel();
        sfx.setText("SFX Volume");
        sfx.setFont(f.deriveFont(f.getStyle() | Font.PLAIN,32));
        sfx.setBounds(360,200,300,40);
        this.add(sfx);

        sfcVol =  new JSlider();
        sfcVol.setBounds(300,250,300,40);
        sfcVol.setOpaque(false);
        sfcVol.setValue((int)(g.getSfxVolume()*100));
        this.add(sfcVol);

        music = new JLabel();
        music.setText("Music Volume");
        music.setFont(f.deriveFont(f.getStyle() | Font.PLAIN,32));
        music.setBounds(340,300,350,40);
        this.add(music);

        musicVol = new JSlider();
        musicVol.setBounds(300,350,300,40);
        musicVol.setOpaque(false);
        musicVol.setValue((int)(g.getMusicVolume()*100));
        this.add(musicVol);

        this.back=new Button("<<< GO BACK",300,500,300,40);
        back.addActionListener(e->{
            m.setPanel(new BgPanel(bg,g,m,f));
            g.getFrame().add(m.getPanel(),BorderLayout.CENTER);
            this.setVisible(false);
        });
        this.add(back);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBg,0,0,this);

    }
}
