package Game.HOC;

import Game.Characters.FinalBoss;
import Game.Characters.MainCharacter;
import Game.Levels.GameLevel;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private Image bckgr;
    private Image hpImage;
    private Image hpBoss;
    private final  Image ammoImage;
    private MainCharacter mainChar;
    private FinalBoss finalBoss;

    /**
     * Contructs the gameplay view
     * @param w World the GameView is going to show
     * @param width The width needed for super
     * @param height The height needed for super
     * @param mc MainCharacter object to show his stats
     */
    public GameView(World w, int width, int height, MainCharacter mc){
        super(w,width,height);
        this.mainChar=mc;
        bckgr =  new ImageIcon("assets/images/background1_2.png").getImage();
        hpImage = new ImageIcon("assets/images/hp74.png").getImage();
        ammoImage = new ImageIcon("assets/images/ammo.png").getImage();
        hpBoss = new ImageIcon("assets/images/bossHealthBars/health_20.png").getImage();
    }


    @Override
    public void paintBackground(Graphics2D g) {
        g.drawImage(bckgr,0,0,this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        //Color textColor = new Color(139, 69, 19);
        Color t = Color.BLACK;
        g.setFont(new Font("Stencil", Font.PLAIN, 32));
        if(this.mainChar.getHealth()==100){
            g.setColor(t);
            g.drawImage(hpImage,5,680,this);
         } else if (this.mainChar.getHealth()>=80){
            hpImage = new ImageIcon("assets/images/hp7480.png").getImage();
            g.setColor(t);
            g.drawImage(hpImage,5,680,this);
        } else if (this.mainChar.getHealth()>=60) {
            hpImage = new ImageIcon("assets/images/hp7460.png").getImage();
            g.setColor(t);
            g.drawImage(hpImage,5,680,this);
        }else if (this.mainChar.getHealth()>=50) {
            hpImage = new ImageIcon("assets/images/hp7450.png").getImage();
            g.setColor(t);
            g.drawImage(hpImage,5,680,this);
        }
        else if (this.mainChar.getHealth()>=40) {
            hpImage = new ImageIcon("assets/images/hp7440.png").getImage();
            g.setColor(t);
            g.drawImage(hpImage,5,680,this);
        }
        else if (this.mainChar.getHealth()<40) {
            hpImage = new ImageIcon("assets/images/hp7420.png").getImage();
            g.setColor(t);
            g.drawImage(hpImage,5,680,this);
        }else if (this.mainChar.getHealth()<=0) {
            hpImage = new ImageIcon("assets/images/hp740.png").getImage();
            g.setColor(t);
            g.drawImage(hpImage,5,680,this);
        }
        if (this.mainChar.getHealth()<=30){
            g.setColor(Color.RED);
            if (this.mainChar.getHealth()==0){
                g.drawString(""+this.mainChar.getHealth(),33, 728);
            } else {
                g.drawString(""+this.mainChar.getHealth(),25, 728);
            }
            g.setColor(t);
        } else {
            if(this.mainChar.getHealth()==100) {
                g.drawString("" + this.mainChar.getHealth(), 15, 728);
            } else if(this.mainChar.getHealth()>0) {
                g.drawString("" + this.mainChar.getHealth(), 25, 728);
            }
        }

        if(this.mainChar.getPistol()!=null){
            if(this.mainChar.getPistol().getAmmo()>9) {
                g.drawImage(ammoImage,736,680,this);
                Font f = g.getFont();
                g.setFont(f.deriveFont(f.getStyle() | Font.PLAIN,50)); //This is used to change the font's size
                g.setColor(t);
                g.drawString(""+this.mainChar.getPistol().getAmmo(),742,734);
                g.setFont(f);
            } else if (this.mainChar.getPistol().getAmmo()>0){
                g.drawImage(ammoImage,736,680,this);
                Font f = g.getFont();
                g.setFont(f.deriveFont(f.getStyle() | Font.PLAIN,50)); //This is used to change the font's size
                g.setColor(t);
                g.drawString(""+this.mainChar.getPistol().getAmmo(),756,734);
            } else {
                Font f = g.getFont();
                g.setFont(f.deriveFont(f.getStyle() | Font.PLAIN,50)); //This is used to change the font's size
                g.drawImage(ammoImage,736,680,this);
                g.setColor(Color.RED);
                g.drawString(""+this.mainChar.getPistol().getAmmo(),756,734);
                g.setColor(t);
                g.setFont(f);
            }
        }
        g.setColor(t);
        g.setFont(new Font("Stencil", Font.PLAIN, 32));
        if (((GameLevel)mainChar.getWorld()).getName().equals("LevelOne")) {
            if (mainChar.getPoints()>=50) g.setColor(new Color(19, 110, 31));
        } else if (((GameLevel)mainChar.getWorld()).getName().equals("LevelTwo")){
            if (mainChar.getPoints()>=120) g.setColor(new Color(19, 110, 31));
        } else {
            g.setColor(t);
        }
        g.setFont(new Font("Stencil", Font.PLAIN, 32));
        g.drawString("SCORE: "+this.mainChar.getPoints(),350,746);

        if(this.finalBoss!=null){
            hpBoss = new ImageIcon("assets/images/bossHealthBars/health_"+finalBoss.getHealth()/10+".png").getImage();
            g.drawImage(hpBoss,360,8,this);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Sets the bckgr field
     * @param bckgr an Image object
     */
    public void setBckgr(Image bckgr) {
        this.bckgr = bckgr;
    }
    /**
     * Sets MainCharacter object to be accessible to view his stats
     * @param mainChar mainCharacter object
     */
    public void setVMainChar(MainCharacter mainChar) {
        this.mainChar = mainChar;
    }

    /**
     * Sets finalBoss object to be accessible to view his stats
     * @param finalBoss finalBoss Object
     */
    public void setVFinalBoss(FinalBoss finalBoss){
        this.finalBoss=finalBoss;
    }

}
