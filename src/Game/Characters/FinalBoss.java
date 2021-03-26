package Game.Characters;

import Game.Collisons.BulletToFinalBoss;

import Game.Collisons.FinalBossXMovement;
import Game.Levels.GameLevel;
import Game.Items.Pistol;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class FinalBoss extends Walker {
    private static final float charHeight = 6f;
    private static final Shape bossShape = new PolygonShape(-1.52f, -2.77f, 1.37f, -2.76f, 1.65f, -0.85f, 1.11f, 1.73f, 0.34f, 2.51f, -0.45f, 2.54f, -1.27f, 1.75f, -1.79f, -0.86f);
    private static BodyImage bossImage = new BodyImage("assets/images/finalBoss.png", charHeight);
    private int health = 200;
    private Pistol pistol;

    public FinalBoss(GameLevel gameLevel) {
        super(gameLevel, bossShape);
        this.addImage(bossImage);
        this.setName("FinalBoss");
        this.setClipped(true);
        this.pistol = new Pistol(gameLevel, true);
        this.addCollisionListener(new BulletToFinalBoss());
        this.pistol = new Pistol(gameLevel, 100);
        this.addCollisionListener(new FinalBossXMovement());
    }

    public int getHealth() {
        return health;
    }

    public Pistol getPistol() {
        return pistol;
    }

    public void setHealth(int health) {
        this.health = health;
        bossHealthCheck();
    }

    public void setPistol(Pistol pistol) {
        this.pistol = pistol;
    }

    /**
     *
     */
    private void bossHealthCheck() {
        if (this.getHealth() <= 0) {
            ((GameLevel) this.getWorld()).getThemeSong().stop();
            this.destroy();
            try {
                SoundClip victoryMusic = new SoundClip("assets/sounds/victory.wav");
                victoryMusic.setVolume(((GameLevel) this.getWorld()).getMusicVolume());
                victoryMusic.play();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    public void bossShoot(Vec2 mainCharPos) {
        if (this.getPistol() != null && this.getPistol().getAmmo() > 0) {
            this.getPistol().shoot(((GameLevel) this.getWorld()), this, 6f,2f, mainCharPos, "BulletBoss");
        }
    }
}
