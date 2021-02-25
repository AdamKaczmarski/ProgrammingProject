package Game.Collisons;

import Game.Characters.MainCharacter;
import Game.Game;
import Game.Levels.GameLevel;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

public class SaveSensorListener implements SensorListener {
    private GameLevel level;
    private Game game;
    public SaveSensorListener(GameLevel level, Game game){
        this.level=level;
        this.game=game;
    }
    @Override
    public void beginContact(SensorEvent e) {
        if(e.getContactBody().getName().equals("MainCharacter")){
            MainCharacter mc = (MainCharacter) e.getContactBody();
            //System.out.println("[SaveSensorListener] MC in the safezone");
            if (this.level.getName().equals("LevelOne")) {
                if (mc.getPoints()%50==0) this.game.changeLevel(level.getName(),mc);
                else System.out.println("[SaveSensorListener] You need more points");
            }
            if (this.level.getName().equals("LevelTwo")){
                if (mc.getPoints()%170==0) this.game.changeLevel(level.getName(),mc);
                else System.out.println("[SaveSensorListener] You need more points");
            }

            //this.game.changeLevel(level.getName(),mc);


        }
    }

    @Override
    public void endContact(SensorEvent e) {

    }
}
