package Game.GameSaverLoader;

import Game.Characters.Enemy;
import Game.Items.MedPack;
import Game.Items.Pistol;
import Game.Levels.GameLevel;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class GameSaverLoader {
    public static void save(GameLevel level) throws IOException {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            String fileName = "saves/"+dtf.format(LocalDateTime.now())+".txt";
            FileWriter writer = null;
            try {
                writer = new FileWriter(fileName, true);
                if(level.getMainChar().getPistol()==null){
                    writer.write(level.getName()+",\n"+level.getMainChar().getName()+","+level.getMainChar().getHealth()+","+level.getMainChar().getPoints()+",0,"+
                            level.getMainChar().getPosition()+"\n");
                } else {
                    writer.write(level.getName()+",\n"+level.getMainChar().getName()+","+level.getMainChar().getHealth()+","+level.getMainChar().getPoints()+","
                            +level.getMainChar().getPistol().getAmmo()+","+
                            level.getMainChar().getPosition()+"\n");
                }

                List<DynamicBody> bodies = level.getDynamicBodies();
                for (int i=0;i<bodies.size();i++){
                    if (level.getDynamicBodies().get(i) instanceof Enemy){
                        writer.write("Enemy,"+((Enemy)bodies.get(i)).getHealth()+","+((Enemy)bodies.get(i)).getPistol().getAmmo()+","+bodies.get(i).getPosition()+"\n");
                    }
                }
                List<StaticBody> staticBodies = level.getStaticBodies();
                for(int i=0;i<staticBodies.size();i++){
                    if(staticBodies.get(i) instanceof Pistol || staticBodies.get(i) instanceof MedPack){
                        writer.write(staticBodies.get(i).getName()+","+staticBodies.get(i).getPosition()+"\n");
                    }
                }   
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }

    }
    public static void load(){

    }
}
