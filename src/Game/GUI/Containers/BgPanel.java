package Game.GUI.Containers;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

import Game.Controls.MainCharacterKeyboardController;
import Game.GUI.ActionListeners.QuitListener;
import Game.GUI.ActionListeners.ResumePauseMenuListener;
import Game.GUI.Components.Button;
import Game.Game;
import Game.Levels.Level.LevelOne;

public class BgPanel extends JPanel {
    private Image menuBg;
    private JLabel title;
    private Button play;
    private Button save;
    private Button load;
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
                g.getLevel().setGameInPlay(true);
                g.getLevel().start();
                g.getLevel().getThemeSong().resume();
                g.getLevel().getView().setVisible(true);
                this.setVisible(false);
            });
            this.add(play);
        }
        if(playPause.equals("Resume")){
            this.save = new Button("Save",300,300,300,40);
            this.save.addActionListener(e->{
                game.getLevel().createSaveFile();
            });
            this.add(save);
        }


        this.load = new Button("Load",300,350,300,40);
        this.load.addActionListener(e->{
            JFileChooser chooser = new JFileChooser();
            String dir = System.getProperty("user.dir")+"/saves";
            System.out.println(dir);
            File dirFile = new File(dir);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Only txt files", "txt");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(dirFile);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                game.loadSaveFile(chooser.getSelectedFile().getName());
            }
        });
        this.add(load);

        this.selectLevel =  new Button("Select Level",300,400,300,40);
        this.selectLevel.addActionListener(e->{
            m.setPanel(new SelectLevelMenu(bg,g,m,f,playPause));
            g.getFrame().add(m.getPanel(),BorderLayout.CENTER);
            this.setVisible(false);
        });
        this.add(selectLevel);

        this.soundMenu =  new Button("Adjust Sound",300,450,300,40);
        this.soundMenu.addActionListener(e->{
            m.setPanel(new SoundMenu(bg,g,m,f, playPause));
            g.getFrame().add(m.getPanel(),BorderLayout.CENTER);
            this.setVisible(false);
        });
        this.add(soundMenu);

        this.quit = new Button("Quit",300,500,300,40);
        quit.addActionListener(new QuitListener());
        this.add(quit);
        this.setFocusable(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBg,0,0,this);

    }

}
