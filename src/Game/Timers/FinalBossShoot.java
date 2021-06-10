package Game.Timers;

import Game.Characters.FinalBoss;
import Game.Characters.MainCharacter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalBossShoot implements ActionListener {
    private FinalBoss finalBoss;
    private MainCharacter mc;
    public FinalBossShoot(FinalBoss boss, MainCharacter mc){
        this.finalBoss=boss;
        this.mc=mc;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (finalBoss!=null){
            finalBoss.bossShoot(mc.getPosition());
        }
    }
}
