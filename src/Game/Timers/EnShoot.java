package Game.Timers;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EnShoot  implements ActionListener {
    private ArrayList<Enemy> enemyList;
    private MainCharacter mainChar;
    public EnShoot(ArrayList<Enemy> el, MainCharacter mc){
        this.enemyList=el;
        this.mainChar=mc;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
       // System.out.println("pew");
        int i = (int) (Math.random() * (enemyList.size())) + 0;
        enemyList.get(i).enemyShoot(mainChar.getPosition());
    }
}
