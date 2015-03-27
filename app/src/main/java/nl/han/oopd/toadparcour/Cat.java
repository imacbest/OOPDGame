package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * @author Thomas Kool & Max Groenendijk
 */
public class Cat extends Monster {
    /**
     * Create a Monster
     *
     * @param target the MoveableGameObject to be chased
     */
    public Cat(MoveableGameObject target, int range, int speed) {
        super(target, range, speed);
        setSprite("cat", 2);
        timeCounter = 0;
    }

    public void update(){
        super.update();
    }

    @Override
    public void move() {
    }

}
