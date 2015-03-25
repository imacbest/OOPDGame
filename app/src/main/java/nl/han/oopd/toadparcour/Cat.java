package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Created by maxgroenendijk on 23-03-15.
 */
public class Cat extends Monster {
    /**
     * Create a Monster
     *
     * @param target the MoveableGameObject to be chased
     */
    public Cat(MoveableGameObject target, int range, int speed) {
        super(target, range, speed);
        //setSprite("");
    }

    public void update(){
        super.update();
    }

    @Override
    public void move() {
    }

}
