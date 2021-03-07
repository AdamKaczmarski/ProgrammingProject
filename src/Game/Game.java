package Game;
// Imports
import Game.Characters.MainCharacter;
import Game.GUI.Containers.MainMenu;
import Game.HOC.GameView;
import Game.Levels.Level.LevelOne;
import Game.Levels.Level.LevelThree;
import Game.Levels.Level.LevelTwo;
import Game.Levels.GameLevel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Game {
    private GameLevel level;
    private GameView view;
    private JFrame frame;
    private MainMenu menu;
    private Font font;
    private float sfxVolume; //add it in every contructor that sets sound
    private float musicVolume;//adjust constructor in gamelevel to change theme lvl menu
    public Game(){
        try {
            font=Font.createFont(Font.TRUETYPE_FONT,new File("assets/fonts/STENCIL.TTF"));
        } catch (FontFormatException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        frame = new JFrame("The Game");
        frame.setPreferredSize(new Dimension(900,800));
        frame.setName("MainMenu");
        //JFrame debugView = new DebugViewer(level,1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        menu = new MainMenu(this,font,"Play");
        frame.add(menu.getPanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        this.setSfxVolume(0.69f);
        this.setMusicVolume(0.05f);

    }

    /**
     *
     * @param currentLevel
     * @param mainChar
     */
    public void changeLevel(String currentLevel, MainCharacter mainChar){
        if (level!=null){
            level.stop();
            level.getThemeSong().stop();
        }
        if(currentLevel.equals("LevelFour")) {
            System.out.println("GG You've finished the game");
        }
        if (currentLevel.equals("MainMenu")){
            level=new LevelOne(this);
            frameSetter(level);
            ((LevelOne)level).setGameView(view);
            ((LevelOne)level).addControls(level.getMainChar(),this);
            level.start();
        }
        if (currentLevel.equals("LevelOne")){
            level=new LevelTwo(this,mainChar);
           // view = new GameView(level,900,800,level.getMainChar());
            view.setWorld(level);
            ((LevelTwo)level).setL2View(view);
            ((LevelTwo) level).getL2View().setBckgr(new ImageIcon("assets/images/background2.png").getImage());
            ((LevelTwo)level).addControls(level.getMainChar(),this);
            level.start();
        }
        if (currentLevel.equals("LevelTwo")){

            System.out.println("[Game] moving to another lvl");
            level=new LevelThree(this,mainChar);
            view.setWorld(level);
            ((LevelThree)level).setL3View(view);
            ((LevelThree)level).addControls(level.getMainChar(),this);
            level.start();
        }
        if (currentLevel.equals("LevelOneFromMenu")){
            level=new LevelTwo(this);
            frameSetter(level);
            ((LevelTwo)level).setL2View(view);
            ((LevelTwo) level).getL2View().setBckgr(new ImageIcon("assets/images/background2.png").getImage());
            ((LevelTwo)level).addControls(level.getMainChar(),this);
            level.start();
        }
        if (currentLevel.equals("LevelTwoFromMenu")){
            level=new LevelThree(this);
            frameSetter(level);
            ((LevelThree)level).setL3View(view);
            ((LevelThree)level).addControls(level.getMainChar(),this);
            level.start();
        }
        if (currentLevel.equals("LevelThree")){
            System.out.println("You have finished the Milestone 2 Gameplay");
            /*level=new LevelFour();
            view.setWorld(level);
            ((LevelFour)level).setL4View(view);*/
        }



    }

    public JFrame getFrame() {
        return frame;
    }

    public MainMenu getMenu() {
        return menu;
    }

    public Font getFont() {
        return font;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public float getSfxVolume() {
        return sfxVolume;
    }

    public GameLevel getLevel() {
        return level;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public void setSfxVolume(float sfxVolume) {
        this.sfxVolume = sfxVolume;
    }

    /**
     * This method sets the level for view and the sets frame's view
     * @param level the GameLevel object the frame should set to
     */
    public void frameSetter(GameLevel level){
        view = new GameView(level,900,800,level.getMainChar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.add(view);
        view.setWorld(level);
    }

    public static void main(String[] args) {
        new Game();
    }
}
