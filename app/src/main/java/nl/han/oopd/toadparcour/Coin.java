package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.input.OnScreenButton;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.objects.MoveableGameObject;
import android.util.Log;

/**
 * Creating the coins whicht the main character can pick up en collect points
 */
public class Coin extends Items {
    private int i = 0;
    public Coin() {
        //super(target, range, speed);
        setSprite("coin", 4);
        //this.timeCounter = 0;
    }

    @Override
    public void update(){
        super.update();
        this.turn();
    }

    //@Override
    public void die() {
        deleteThisGameObject();
    }


    public void turn() {
        setFrameNumber(i % 4);
    }
}

