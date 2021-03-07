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
    private float stepDiv;
    public EnemyShoot(ArrayList<Enemy> el, MainCharacter mc, float v){
        this.enemyList=el;
        this.mainChar=mc;
        this.stepDiv=v;
    }
    @Override
    public void preStep(StepEvent stepEvent) {

    }

    /**
     * This method counts the steps of the simulation. The enemy will shoot towards MainCharacter
     * Every time step count % stepDiv == 0
     * @param stepEvent
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        steps=steps+stepEvent.getStep();
        //System.out.println((float)Math.round(steps*10f)/10f);
        if (steps>0.1 &&((float)Math.round(steps*10f)/10f)%stepDiv==0.00 ) {
                int i = (int) (Math.random() * (enemyList.size())) + 0;
                steps+=0.1;
            //System.out.println(i);
                enemyList.get(i).enemyShoot(mainChar.getPosition());
        }

    }
}
