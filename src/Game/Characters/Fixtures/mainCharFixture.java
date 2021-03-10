package Game.Characters.Fixtures;

import Game.Characters.MainCharacter;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;

public class mainCharFixture extends SolidFixture {
    public mainCharFixture(MainCharacter mc, PolygonShape shape){
        super(mc,shape);
    }
}
