package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Created by maxgroenendijk on 23-03-15.
 */
public class Turtle extends Monster {
    /**
     * Create a Monster
     *
     * @param target the MoveableGameObject to be chased
     */
    public Turtle(MoveableGameObject target) {
        super(target);
    }
}
