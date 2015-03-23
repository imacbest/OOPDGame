package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;
import android.util.Log;

/**
 * Created by maxgroenendijk on 23-03-15.
 */
public class Turtle extends Monster {

    /**
     * Create a Monster
     *
     * @param target the MoveableGameObject to be chased
     */
    public Turtle(MoveableGameObject target, int range, int speed) {
        super(target, range, speed);
        setSprite("alien");
        this.timeCounter = 0;
    }

    @Override
    public void update(){
        super.update();
        this.move();
    }

    @Override
    public void die() {
        deleteThisGameObject();
    }
}
