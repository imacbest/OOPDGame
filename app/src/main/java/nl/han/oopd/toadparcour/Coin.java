package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.input.OnScreenButton;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Creating the coins whicht the main character can pick up en collect points
 */
public class Coin extends Items {

    public Coin() {
        //super(target, range, speed);
        setSprite("coin", 4);
        //this.timeCounter = 0;
    }

    @Override
    public void update(){
        super.update();
        this.turn();
        //this.move();
    }

    //@Override
    public void die() {
        deleteThisGameObject();
    }


    public void turn() {
        if(OnScreenButtons.buttonX) {
            setFrameNumber(2);
        } else {
            setFrameNumber(1);

        }
    }
}

