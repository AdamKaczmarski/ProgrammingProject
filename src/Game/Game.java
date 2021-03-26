package Game;
// Imports
import Game.Characters.MainCharacter;
import Game.GUI.Containers.MainMenu;
import Game.GameSaverLoader.GameSaverLoader;
import Game.HOC.GameView;
import Game.Levels.Level.LevelFour;
import Game.Levels.Level.LevelOne;
import Game.Levels.Level.LevelThree;
import Game.Levels.Level.LevelTwo;
import Game.Levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Game {
    private GameLevel level;
    private GameView view;
    private final JFrame frame;
    private final MainMenu menu;
    private Font font;
    private float sfxVolume=0.5f; //add it in every constructor that sets sound
    private float musicVolume=0.5f;//adjust constructor in gamelevel to change theme lvl menu
    public Game() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/STENCIL.TTF"));
        } catch (FontFormatException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        frame = new JFrame("The Game");
        frame.setPreferredSize(new Dimension(900, 800));
        frame.setName("MainMenu");
        //JFrame debugView = new DebugViewer(level,1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        menu = new MainMenu(this, font, "Play");
        frame.add(menu.getPanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        this.setMusicVolume(0.05f);
        this.setSfxVolume(0.1f);
    }

    /**
     * This method is used to transfer the player from one level to another or launch the Level from the Menu
     * @param currentLevel The level name the player is coming from eg. "LevelOne" will transfer to LevelTwo
     * @param mainChar MainCharacter object used to pass the statistics when moving between levels
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
            soundSetter(level);
            //((LevelOne)level).setGameView(view);
            level.setView(view);
            level.getView().setVMainChar(level.getMainChar());
            ((LevelOne)level).addControls(this);
            //((LevelOne)level).addControls(level.getMainChar(),this);
            level.start();
        }
        if (currentLevel.equals("LevelOne")){
            level=new LevelTwo(this,mainChar);
            view.setWorld(level);
            soundSetter(level);
            level.setView(view);
            level.getView().setVMainChar(level.getMainChar());
            level.getView().setBckgr(new ImageIcon("assets/images/background2.png").getImage());
            ((LevelTwo)level).addControls(this);
            level.start();
        }
        if (currentLevel.equals("LevelTwo")){
            level=new LevelThree(this,mainChar);
            view.setWorld(level);
            soundSetter(level);
            level.setView(view);
            level.getView().setVMainChar(level.getMainChar());
            ((LevelThree)level).addControls(this);
            level.start();
        }
        if (currentLevel.equals("LevelOneFromMenu")){
            level= new LevelTwo(this);
            frameSetter(level);
            soundSetter(level);
            level.setView(view);
            level.getView().setVMainChar(level.getMainChar());
            level.getView().setBckgr(new ImageIcon("assets/images/background2.png").getImage());
            ((LevelTwo)level).addControls(this);
            level.start();
        }
        if (currentLevel.equals("LevelTwoFromMenu")){
            level= new LevelThree(this);
            frameSetter(level);
            soundSetter(level);
            level.setView(view);
            level.getView().setVMainChar(level.getMainChar());
            ((LevelThree)level).addControls(this);
            level.start();
        }
        if (currentLevel.equals("LevelThree")){
            level=new LevelFour(this,mainChar);
            view.setWorld(level);
            soundSetter(level);
            level.setView(view);
            level.getView().setVMainChar(level.getMainChar());
            level.getView().setVFinalBoss(((LevelFour)level).getFinalBoss());
            ((LevelFour)level).addControls(this);
            level.start();
        }
        if(currentLevel.equals("LevelThreeFromMenu")){
            level=new LevelFour(this);
            frameSetter(level);
            soundSetter(level);
            level.setView(view);
            level.getView().setVMainChar(level.getMainChar());
            level.getView().setVFinalBoss(((LevelFour)level).getFinalBoss());
            ((LevelFour)level).addControls(this);
            level.start();
        }
    }

    /**
     *      *  This method is used to set the Level world when loaded from the save file
     * @param levelToLoad Level name that's to be loaded
     * @param health     health of the MainCharacter should be set to
     * @param points    score the player scored during the save
     * @param ammo  ammo of the MainCharacter
     * @param pos   the position of the MainCharacter
     */
    public void changeLevel(String levelToLoad, int health, int points, int ammo, Vec2 pos){
        if (levelToLoad.equals("LevelOne")){
            level=new LevelOne(this,health,points,ammo,pos);
            soundSetter(level);
        }
        if (levelToLoad.equals("LevelTwo")){
            level = new LevelTwo(this,health,points,ammo,pos);
            soundSetter(level);
        }
        if (levelToLoad.equals("LevelThree")){
            level = new LevelThree(this,health,points,ammo,pos);
            soundSetter(level);
        }
        if (levelToLoad.equals("LevelFour")){
            level=new LevelFour(this,health,points,ammo,pos);
            soundSetter(level);
        }
    }

    /**
     *
     * @return JFrame the program is set in
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     *
     * @return Menu object that is used
     */
    public MainMenu getMenu() {
        return menu;
    }

    /**
     *
     * @return Font that the whole program uses
     */
    public Font getFont() {
        return font;
    }
    /**
     *
     * @return float 0.0-1.0
     */
    public float getMusicVolume() {
        return musicVolume;
    }

    /**
     *
     * @return float 0.0-1.0
     */
    public float getSfxVolume() {
        return sfxVolume;
    }

    /**
     *
     * @return GameLevel object
     */
    public GameLevel getLevel() {
        return level;
    }

    /**
     *
     * @return GameView object
     */
    public GameView getView() {
        return view;
    }

    /**
     * Sets the music volume
     * @param musicVolume float from 0.0 to 1.0
     */
    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
       if(this.getLevel()!=null) this.getLevel().setMusicVolume(musicVolume);
    }
    /**
     * Sets the sfx volume
     * @param sfxVolume float from 0.0 to 1.0
     */
    public void setSfxVolume(float sfxVolume) {
        this.sfxVolume = sfxVolume;
        if(this.getLevel()!=null) this.getLevel().setSfxVolume(sfxVolume);
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

    /**
     * This method sets the sounds volumes for the generic class GameLevel and then every level can access them to adjust volumes in the classes they use
     * @param level level object that user is going to play in
     */
    public void soundSetter(GameLevel level){
        level.setMusicVolume(this.getMusicVolume());
        level.setSfxVolume(this.getSfxVolume());
    }

    /**
     *  passes the path of the file to GameSaverLoader
     * @param fileName the path of the file to load
     */
    public void loadSaveFile(String fileName){
        GameSaverLoader.load("saves/"+fileName, this);
    }

    public static void main(String[] args) {
        new Game();
    }
}
