package Game.HOC;

import Game.Characters.MainCharacter;
import city.cs.engine.UserView;
import city.cs.engine.World;
import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private Image bckgr;
    private MainCharacter mainChar;
    public GameView(World w, int width, int height, MainCharacter mc){
        super(w,width,height);
        this.mainChar=mc;
        bckgr =  new ImageIcon("assets/images/background1.png").getImage();
    }
    public GameView(World w, int width, int height){
        super(w,width,height);
    }

    @Override
    public void paintBackground(Graphics2D g) {
        g.drawImage(bckgr,0,0,this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        g.setFont(new Font("Stencil",Font.PLAIN,32));
        if (this.getVMainChar().getHealth()<=30){
            g.setColor(Color.RED);
            g.drawString("HP: "+this.getVMainChar().getHealth(),5,746);
            g.setColor(new Color(139,69,19));
        } else {
            g.setColor(new Color(139,69,19));
            g.drawString("HP: "+this.getVMainChar().getHealth(),5,746);
        }

        if(this.getVMainChar().getPistol()!=null){
            if(this.getVMainChar().getPistol().getAmmo()>0) {
                g.setColor(new Color(139,69,19));
                g.drawString("AMMO: "+this.getVMainChar().getPistol().getAmmo(),736,746);
            } else {
                g.setColor(Color.RED);
                g.drawString("AMMO: "+this.getVMainChar().getPistol().getAmmo(),736,746);
                g.setColor(new Color(139,69,19));
            }
        }
        g.setColor(new Color(139,69,19));
        g.drawString("SCORE: "+this.getVMainChar().getPoints(),350,746);

    }

    public void setBckgr(Image bckgr) {
        this.bckgr = bckgr;
    }

    public MainCharacter getVMainChar() {
        return mainChar;
    }

    public void setVMainChar(MainCharacter mainChar) {
        this.mainChar = mainChar;
    }
}
