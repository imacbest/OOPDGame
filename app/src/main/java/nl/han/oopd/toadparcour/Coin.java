package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.GameObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Creating the coins whicht the main character can pick up en collect points
 */
public class Coin extends Items {
    private int timeCounter = 0;
    private int timeCounterFast = 0;

    public Coin() {
        setSprite("coin", 4);
    }

    @Override
    public void update(){
        super.update();
        this.turn();
    }

    public void remove() {
        deleteThisGameObject();
    }


    public void turn() {
        if(timeCounterFast % 4 == 0) {
            setFrameNumber(timeCounter % 4);
            timeCounter++;
        }
        timeCounterFast++;
    }

}

