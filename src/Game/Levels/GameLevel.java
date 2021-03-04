package Game.Levels;

import Game.Characters.MainCharacter;
import Game.Game;
import Game.HOC.GameView;

import Game.Sensors.saveSensor;
import city.cs.engine.SoundClip;
import city.cs.engine.World;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileWriter;
import java.io.IOException;

public class GameLevel extends World {
    private MainCharacter mainChar;
    private GameView view;
    private String name;
    private saveSensor saveSen;
    private SoundClip themeSong;
    public GameLevel(Game game){
        super();
        mainChar = new MainCharacter(this);
        this.setGravity(0f);
        saveSen=new saveSensor(this);
    }

    public MainCharacter getMainChar() {
        return mainChar;
    }

    public GameView getView() {
        return view;
    }

    public String getName() {
        return name;
    }

    public SoundClip getThemeSong(){return themeSong;}

    public saveSensor getSaveSen() {
        return saveSen;
    }

    public void setMainChar(MainCharacter mainChar) {
        this.mainChar = mainChar;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void setName(String n){
        this.name=n;
    }



    /**
     * This method creates a save file when main character enters a certain area which will move him to another level
     * Save file will contain every attribute that counts in the game
     * @param mainChar requires MainCharacter to access his attributes
     */
    public void createSaveFile(MainCharacter mainChar){
        try {
            //IT CREATES NEW FILE AND DOES OVERWRITE ALREADY CREATED FILE
            FileWriter writer = new FileWriter("save.txt");
            writer.write("mainChar health: "+ mainChar.getHealth());
            writer.write("\nmainChar points: "+mainChar.getPoints());
            //System.out.println(mainChar.getPistol());
            if(mainChar.getPistol()!=null) writer.write("\nmainCharPistol ammo: "+mainChar.getPistol().getAmmo());
            writer.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     *
     */
    public void addThemeSong(String path){
        try {
            themeSong = new SoundClip(path);
            themeSong.setVolume(0.05f);
            themeSong.play();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
