package Game.Sensors;

import Game.Characters.MainCharacter;
import Game.Game;
import Game.Levels.GameLevel;

import Game.Levels.Level.LevelOne;
import Game.Levels.Level.LevelTwo;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

public class SaveSensorListener implements SensorListener {
    private GameLevel level;
    private Game game;
    public SaveSensorListener(GameLevel level, Game game){
        this.level=level;
        this.game=game;
    }

    /**
     * This sensor is used to transfer player from one level to another, it checks his points and if the amount is admittable it transfers him to another level
     * @param e
     */
    @Override
    public void beginContact(SensorEvent e) {
        if(e.getContactBody().getName().equals("MainCharacter")){
            MainCharacter mc = (MainCharacter) e.getContactBody();
            if (this.level.getName().equals("LevelOne")) {
                if (mc.getPoints()>=50) {
                    level.getView().removeMouseListener( level.getMouse());
                    level.getView().removeKeyListener( level.getKeyboard());
                    this.game.changeLevel(level.getName(), mc);
                }
                else {
                    System.out.println("[SaveSensorListener] You need more points");
                }
            }
            if (this.level.getName().equals("LevelTwo")){
                if (mc.getPoints()>=120) {
                    this.game.changeLevel(level.getName(), mc);
                    level.getView().removeMouseListener( level.getMouse());
                    level.getView().removeKeyListener( level.getKeyboard());
                }
                else {
                    System.out.println("[SaveSensorListener] You need more points");
                }
            }
            if (this.level.getName().equals("LevelThree")) {
                this.game.changeLevel(this.level.getName(), mc);
            }
        }
    }

    @Override
    public void endContact(SensorEvent e) {

    }
}
