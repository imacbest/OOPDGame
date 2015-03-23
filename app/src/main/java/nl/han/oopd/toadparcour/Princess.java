package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Created by maxgroenendijk on 23-03-15.
 */
public class Princess extends Monster {
    /**
     * Create a Monster
     *
     * @param target the MoveableGameObject to be chased
     */
    public Princess(MoveableGameObject target) {
        super(target, 50);
    }

    @Override
    public void move() {
    }

    @Override
    public void die() {

    }
}
