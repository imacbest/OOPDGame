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
        super(target, 500);
        setSprite("alien");
        this.timeCounter = 0;
    }

    @Override
    public void update(){
        super.update();
        this.move();
    }
    @Override
    public void move() {
        timeCounter++;
        if (timeCounter % 4 == 0) {
            if(isInRange()) {
                this.moveTowardsAPoint(super.target.getCenterX(), super.target.getCenterY());
            }
        }
    }

    @Override
    public void die() {
        deleteThisGameObject();
    }
}
