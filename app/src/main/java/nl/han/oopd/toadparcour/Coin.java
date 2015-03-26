package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.GameObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Creating the coins whicht the main character can pick up en collect points
 * @author Thomas Kool
 */
public class Coin extends Items {
    /**
     * counters for the animation, two are needed for the perfect timing :)
     */
    private int timeCounter = 0;
    private int timeCounterFast = 0;

    /**
     * Constructor, sets the sprite
     */
    public Coin() {
        setSprite("coin", 4);
    }

    @Override
    public void update(){
        super.update();
        this.turn();
    }

    /**
     * makes the perfect turning animation possible :D
     */
    public void turn() {
        if(timeCounterFast % 4 == 0) {
            setFrameNumber(timeCounter % 4);
            timeCounter++;
        }
        timeCounterFast++;
    }

}

