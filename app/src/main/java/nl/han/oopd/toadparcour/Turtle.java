package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;
import android.util.Log;

/**
 * Created by maxgroenendijk on 23-03-15.
 * @author Thomas & Max
 */
public class Turtle extends Monster {

    /**
     * Create a Monster
     *
     * @param target the MoveableGameObject to be chased
     */
    public Turtle(MoveableGameObject target, int range, int speed) {
        super(target, range, speed);
        setSprite("turtle", 2);
        this.timeCounter = 0;
    }

    @Override
    public void update(){
        super.update();
        this.move();
    }

}
