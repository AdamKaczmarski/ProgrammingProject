package Game.Items;

import Game.Collisons.MedPackPickup;
import city.cs.engine.*;

public class MedPack extends StaticBody {
    private static Shape medShape = new PolygonShape(-0.377f,-0.295f, 0.384f,-0.299f, 0.505f,-0.171f, 0.501f,0.242f, 0.384f,0.37f, -0.38f,0.362f, -0.494f,0.256f, -0.505f,-0.182f);
    private static BodyImage medImage = new BodyImage("assets/images/medPack.png",1.5f);
    public MedPack(World w){
        super(w,medShape);
        this.addImage(medImage);
        this.addCollisionListener(new MedPackPickup());
    }
}
