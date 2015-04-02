package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created the flying bananas, where the main character can kill the monsters with
 * @author Thomas Kool
 */
public class FlyingBanana extends GravatiyGameObject implements ICollision {
    /**
     * counters needed for the animation
     */
    private int timeCounter = 0;
    private int timeCounterFast = 0;
    /**
     * instance of toad
     */
    private Toad toad;

    /**
     * Constructor
     * @param toad
     * @param direction
     */
    public FlyingBanana(Toad toad, double direction) {
        this.toad = toad;
        setSprite("bananatrown", 4);
        setDirectionSpeed(direction, Constants.BANANASPEED);
    }

    public void update(){
        super.update();
        turn();
        handleCollisions();
    }

    /**
     * makes the banana turn
     */
    public void turn() {
        if(timeCounterFast % 2 == 0) {
            setFrameNumber(timeCounter % 4);
            timeCounter++;
        }
        timeCounterFast++;
    }

    /**
     * removes the banana from the game
     */
    public void remove(){
        deleteThisGameObject();
    }

    /**
     * handles any collisions with monsters or other objects
     */
    private void handleCollisions() {
        // collisions with objects
        ArrayList<GameObject> gebotst = getCollidedObjects();
        if (gebotst != null) {
            for (GameObject g : gebotst) {
                if(g instanceof Monster){
                    ((Monster) g).die();
                    this.remove();
                    toad.setScore(10);
                    Log.d("Monster", "Monster destroyed");
                }
            }
        }
    }

    /**
     * handles any collisions with tiles
     * @param collidedTiles List of TileCollision holding all tile collisions in this move.
     *
     */
    @Override
    public void collisionOccurred(List<TileCollision> collidedTiles) {
        for (TileCollision tc : collidedTiles){
            this.remove();
        }
    }

}
