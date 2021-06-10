package Game.Characters.Fixtures;

import Game.Characters.MainCharacter;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;

public class mainCharFixture extends SolidFixture {
    /**
     * This class is used to change the MainCharacters shape in terms of his movement
     * @param mc MainCharacter object
     * @param shape PolygonShape object
     */
    public mainCharFixture(MainCharacter mc, PolygonShape shape){
        super(mc,shape);
    }
}
