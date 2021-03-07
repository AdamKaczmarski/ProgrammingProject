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
            //System.out.println("[SaveSensorListener] MC in the safezone");
            if (this.level.getName().equals("LevelOne")) {
                if (mc.getPoints()%50==0) {
                    ((LevelOne)level).getL1View().removeMouseListener( level.getMouse());
                    ((LevelOne)level).getL1View().removeKeyListener( level.getKeyboard());
                    this.game.changeLevel(level.getName(), mc);
                }
                else {
                    System.out.println("[SaveSensorListener] You need more points");
                    this.game.changeLevel("LevelOne", mc);
                }
            }
            if (this.level.getName().equals("LevelTwo")){
                if (mc.getPoints()%100==0) {
                    this.game.changeLevel(level.getName(), mc);
                    ((LevelTwo)level).getL2View().removeMouseListener( level.getMouse());
                    ((LevelTwo)level).getL2View().removeKeyListener( level.getKeyboard());
                }
                else {
                    System.out.println("[SaveSensorListener] You need more points");
                    this.game.changeLevel("LevelTwo",mc);
                }
            }
            if (this.level.getName().equals("LevelThree")){
                this.game.changeLevel(this.level.getName(),null);
            }

            //this.game.changeLevel("LevelTwo",mc);


        }
    }

    @Override
    public void endContact(SensorEvent e) {

    }
}
