package Game.Timers;

import Game.Characters.Enemy;
import city.cs.engine.DynamicBody;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class L1EnemyMovements implements ActionListener {
    private ArrayList<Enemy> enemyList;
    public L1EnemyMovements(List<DynamicBody> dbl){
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
               }
                j++;
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!enemyList.isEmpty()){
            int i = (int) (Math.random() * (enemyList.size())) + 0;
            int r = (int) (Math.random() * (2) + 0);
            float x = (float)(Math.random()*3+0);
            System.out.println(r);
            if (r==0) enemyList.get(i).setLinearVelocity(new Vec2(x,-3));
            if (r==1) enemyList.get(i).setLinearVelocity(new Vec2(x,3));
        }






    }
}
