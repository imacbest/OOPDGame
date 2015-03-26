package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Princess monster
 * @author Thomas Kool & Max Groenendijk
 */
public class Princess extends Monster {
    /**
     * Create a Monster
     *
     * @param target the MoveableGameObject to be chased
     */
    public Princess(MoveableGameObject target, int range, int speed) {
        super(target, range, speed);
        setSprite("princess", 2);
        this.timeCounter = 0;
    }

    @Override
    public void update(){
        super.update();
        this.move();
    }


}
