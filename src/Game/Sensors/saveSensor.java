package Game.Sensors;

import city.cs.engine.*;
import city.cs.engine.PolygonShape;
public class saveSensor extends Sensor {
    public saveSensor(World w){
        super(new StaticBody(w),new PolygonShape(3.85f,-3.94f, 3.86f,3.89f, -3.83f,3.94f, -3.86f,-3.9f));
        this.getBody().setAlwaysOutline(true);
        this.getBody().setName("saveSensor");
        //this.addSensorListener(new SaveSensorListener());
    }

}
