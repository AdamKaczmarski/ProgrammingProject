package Game.Timers;

import Game.Characters.Enemy;
import Game.Characters.MainCharacter;
import city.cs.engine.DynamicBody;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EnShoot  implements ActionListener {
    private ArrayList<Enemy> enemyList;
    private List<DynamicBody> dbl;
    private MainCharacter mainChar;
    public EnShoot(List<DynamicBody> dbl, MainCharacter mc){
        int enemyCounter=0;
        for (int i=0;i<dbl.size();i++){
            if (dbl.get(i).getName().equals("Enemy")){
                enemyCounter++;
            }
        }
        enemyList=new ArrayList<>(enemyCounter);
        int j=0;
        for(int i=0;i<dbl.size();i++){
            if (dbl.get(i).getName().equals("Enemy")){
                enemyList.add(j,(Enemy)dbl.get(i));
                j++;
            }
        }
        this.mainChar=mc;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!enemyList.isEmpty()){
            int i = (int) (Math.random() * (enemyList.size())) + 0;
            enemyList.get(i).enemyShoot(mainChar.getPosition());
            //enemyList.get(i).setLinearVelocity(mainChar.getPosition());
        }

    }
}
