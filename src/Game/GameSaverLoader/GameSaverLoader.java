package Game.GameSaverLoader;

import Game.Characters.Enemy;
import Game.Characters.FinalBoss;
import Game.Game;
import Game.Items.MedPack;
import Game.Items.Pistol;
import Game.Levels.GameLevel;
import Game.Levels.Level.LevelFour;
import Game.Levels.Level.LevelOne;
import Game.Levels.Level.LevelThree;
import Game.Levels.Level.LevelTwo;
import Game.Levels.RotPills.RotPill;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class GameSaverLoader {
    public static void save(GameLevel level) throws IOException {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmmss");
            String fileName = "saves/"+dtf.format(LocalDateTime.now())+".txt";
            FileWriter writer = null;
            try {
                writer = new FileWriter(fileName, true);
                if(level.getMainChar().getPistol()==null){
                    writer.write(level.getName()+"\n"+level.getMainChar().getName()+","+level.getMainChar().getHealth()+","+level.getMainChar().getPoints()+",0,"+
                            level.getMainChar().getPosition().x+","+level.getMainChar().getPosition().y+"\n");
                } else {
                    writer.write(level.getName()+"\n"+level.getMainChar().getName()+","+level.getMainChar().getHealth()+","+level.getMainChar().getPoints()+","
                            +level.getMainChar().getPistol().getAmmo()+","+
                            level.getMainChar().getPosition().x+","+level.getMainChar().getPosition().y+"\n");
                }

                List<DynamicBody> bodies = level.getDynamicBodies();
                for (int i=0;i<bodies.size();i++){
                    if (bodies.get(i) instanceof Enemy){
                        writer.write(bodies.get(i).getName()+","+((Enemy)bodies.get(i)).getHealth()+","+((Enemy)bodies.get(i)).getPistol().getAmmo()+","+bodies.get(i).getPosition().x+","+bodies.get(i).getPosition().y+"\n");
                    } else if (bodies.get(i) instanceof RotPill){
                        writer.write(bodies.get(i).getName()+","+((RotPill) bodies.get(i)).getHealth()+","+bodies.get(i).getAngularVelocity()+","+bodies.get(i).getPosition().x+","+bodies.get(i).getPosition().y+","+bodies.get(i).getLinearVelocity().x+","+bodies.get(i).getLinearVelocity().y+"\n");
                    } else if (bodies.get(i) instanceof FinalBoss){
                        writer.write(bodies.get(i).getName()+","+((FinalBoss) bodies.get(i)).getHealth()+","+((FinalBoss)bodies.get(i)).getPistol().getAmmo()+","+bodies.get(i).getPosition().x+","+bodies.get(i).getPosition().y+","+bodies.get(i).getLinearVelocity().x+"\n");
                    }
                }
                List<StaticBody> staticBodies = level.getStaticBodies();
                for(int i=0;i<staticBodies.size();i++){
                    if(staticBodies.get(i) instanceof Pistol || staticBodies.get(i) instanceof MedPack){
                        if(!staticBodies.get(i).getName().equals("PickedPistol"))
                        writer.write(staticBodies.get(i).getName()+","+staticBodies.get(i).getPosition().x+","+staticBodies.get(i).getPosition().y+"\n");
                    }
                }   
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }

    }
    public static void load(String fileName, Game game){
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String lvlName = reader.readLine();
            String line = reader.readLine();
            String[] tokens = line.split(",");
            int hp = Integer.parseInt(tokens[1]);
            int score = Integer.parseInt(tokens[2]);
            int ammo = Integer.parseInt(tokens[3]);
            float x = Float.parseFloat(tokens[4]);
            float y = Float.parseFloat(tokens[5]);

            line = reader.readLine();

            if(lvlName.equals("LevelOne") || lvlName.equals("LevelTwo") || lvlName.equals("LevelThree") || lvlName.equals("LevelFour")){
                game.changeLevel(lvlName,hp,score,ammo,new Vec2(x,y));
                while (line!=null){
                    tokens = line.split(",");
                    spawnThing(tokens,game.getLevel());
                    line=reader.readLine();
                }

            }
            finishLoading(game,lvlName);
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            try {
                if (reader!=null) reader.close();
                if (fr!=null)fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void spawnThing(String[] tokens, GameLevel level){
        int hp, ammo;
        float x,y;
        if (tokens[0].equals("Enemy")){
            hp = Integer.parseInt(tokens[1]);
            ammo = Integer.parseInt(tokens[2]);
            x = Float.parseFloat(tokens[3]);
            y = Float.parseFloat(tokens[4]);
            level.spawnEnemy(hp,ammo, new Vec2(x,y));
        } else if (tokens[0].equals("Pistol")){
            x = Float.parseFloat(tokens[1]);
            y = Float.parseFloat(tokens[2]);
            level.spawnItem(tokens[0],new Vec2(x,y));
        } else if (tokens[0].equals("MedPack")){
            x = Float.parseFloat(tokens[1]);
            y = Float.parseFloat(tokens[2]);
            level.spawnItem(tokens[0],new Vec2(x,y));
        } else if (tokens[0].equals("RotPill")){
            hp = Integer.parseInt(tokens[1]);
            float ang = Float.parseFloat(tokens[2]);
            x = Float.parseFloat(tokens[3]);
            y = Float.parseFloat(tokens[4]);
            float lx = Float.parseFloat(tokens[5]);
            float ly = Float.parseFloat(tokens[6]);
            level.addRotPill(hp,ang,new Vec2(x,y), new Vec2(lx,ly));
        } else if (tokens[0].equals("FinalBoss")){
            hp = Integer.parseInt(tokens[1]);
            ammo = Integer.parseInt(tokens[2]);
            x = Float.parseFloat(tokens[3]);
            y = Float.parseFloat(tokens[4]);
            float lx = Float.parseFloat(tokens[5]);
            ((LevelFour)level).setFinalBoss(new FinalBoss(level));
            ((LevelFour) level).getFinalBoss().setHealth(hp);
            ((LevelFour) level).getFinalBoss().getPistol().setAmmo(ammo);
            ((LevelFour) level).getFinalBoss().setPosition(new Vec2(x,y));
            ((LevelFour) level).getFinalBoss().setLinearVelocity(new Vec2(lx,0));
        }
    }
    public static void finishLoading(Game game, String lvl){
        game.frameSetter(game.getLevel());
        game.getLevel().setView(game.getView());
        game.getLevel().getView().setVMainChar(game.getLevel().getMainChar());
        if (lvl.equals("LevelOne")){
            ((LevelOne)game.getLevel()).addControls(game);
        } else if (lvl.equals("LevelTwo")){
            ((LevelTwo)game.getLevel()).addControls(game);
        } else if (lvl.equals("LevelThree")) {
            ((LevelThree) game.getLevel()).addControls(game);
        } else if (lvl.equals("LevelFour")) {
            ((LevelFour) game.getLevel()).addControls(game);
            game.getLevel().getView().setVFinalBoss(((LevelFour) game.getLevel()).getFinalBoss());
        }
        game.getLevel().start();
        game.getMenu().getPanel().setVisible(false);
    }
}
