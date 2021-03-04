package Game.StepListeners;

import Game.Characters.Enemy;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import Game.Characters.MainCharacter;
import java.util.ArrayList;

public class EnemyShoot implements StepListener {
    float steps =0f;
    private ArrayList<Enemy> enemyList;
    private MainCharacter mainChar;
    public EnemyShoot(ArrayList<Enemy> el,MainCharacter mc){
        this.enemyList=el;
        this.mainChar=mc;
    }
    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        steps=steps+stepEvent.getStep();
        //System.out.println((float)Math.round(steps*10f)/10f);
        if (steps>0.1 &&((float)Math.round(steps*10f)/10f)%1.5==0.00 ) {
                int i = (int) (Math.random() * (enemyList.size())) + 0;
            //System.out.println(i);
                enemyList.get(i).enemyShoot(mainChar.getPosition());
        }

    }
}
