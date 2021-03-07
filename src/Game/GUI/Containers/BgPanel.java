package Game.GUI.Containers;

import javax.swing.*;
import java.awt.*;

import Game.GUI.ActionListeners.QuitListener;
import Game.GUI.ActionListeners.ResumePauseMenuListener;
import Game.GUI.Components.Button;
import Game.Game;

public class BgPanel extends JPanel {
    private Image menuBg;
    private JLabel title;
    private Button play;
    private Button selectLevel;
    private Button soundMenu;
    private Button quit;
    private Game game;

    /**
     * This Panel is the menu that has all the buttons,
     * @param bg background that paints the panel's background
     * @param g game to call relevant methods
     * @param m menu to change Panels when switching to other menus
     * @param f font to set font for all the buttons
     * @param playPause it take "Play" or "Resume" unfortunately the buttons in Pause menu ("Resume") do not work
     *                  there's an issue with Focus that I will have to work on later
     */
    public BgPanel(Image bg, Game g, MainMenu m, Font f, String playPause){
        this.game =g;
        this.menuBg =  bg;
        this.setLayout(null); // null because I put all the components manually

        this.title = new JLabel();
        title.setText("The Game");
        title.setFont(f.deriveFont(f.getStyle() | Font.PLAIN,128)); //This is used to change the font's size
        title.setBounds(150,64,800,130);
        this.add(title);

        if(playPause.equals("Play")){
            this.play =  new Button("Play",300,250,300,40);
            this.play.addActionListener(e-> {
                g.changeLevel("MainMenu",null);
                this.setVisible(false);
            });
            this.add(play);
        } else if (playPause.equals("Resume")) {
            this.play =  new Button("Resume",300,250,300,40);
            this.play.addActionListener(e-> {
                //g.changeLevel("MainMenu",null);
                g.getLevel().start();
                this.setVisible(false);
            });
            this.add(play);
        }


        this.selectLevel =  new Button("Select Level",300,300,300,40);
        this.selectLevel.addActionListener(e->{
            m.setPanel(new SelectLevelMenu(bg,g,m,f,playPause));
            g.getFrame().add(m.getPanel(),BorderLayout.CENTER);
            this.setVisible(false);
        });
        this.add(selectLevel);

        this.soundMenu =  new Button("Adjust Sound",300,350,300,40);
        this.soundMenu.addActionListener(e->{
            m.setPanel(new SoundMenu(bg,g,m,f, playPause));
            g.getFrame().add(m.getPanel(),BorderLayout.CENTER);
            this.setVisible(false);
        });
        this.add(soundMenu);

        this.quit = new Button("Quit",300,450,300,40);
        quit.addActionListener(new QuitListener());
        this.add(quit);
        this.setFocusable(true);
        if (playPause.equals("Resume")){
            this.addKeyListener(new ResumePauseMenuListener(this));
        }

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBg,0,0,this);

    }

    /**
     * It is used to resume the play from Pause Menu
     */
    public void resumePlay(){
        System.out.println("Reusme");
        this.setVisible(false);
        game.getLevel().start();
    }
}
