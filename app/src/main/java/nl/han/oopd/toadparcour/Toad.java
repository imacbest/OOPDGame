package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.input.MotionSensor;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;

import java.util.ArrayList;
import java.util.List;

/**
 * The main character of the game
 * @author Thomas & Max
 */
public class Toad extends GravatiyGameObject implements ICollision {

    private ToadParcour mygame;

    /**
     * Number of bananas that toad is carrying
     */
    private int bananas = 0;

    /**
     * The score of the main character
     */
    private int score;

    /**
     * The main character of the game
     * @author Thomas & Max
     */
    public Toad(ToadParcour mygame) {
        this.mygame = mygame;
        setSprite("toad", 4); // change fishframe to toad frams !!
        setFriction(1);

        setSpeed(0);
        setDirection(90);

        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void update(){
        super.update();

        super.gravity();

        // collisions with objects
        ArrayList<GameObject> gebotst = getCollidedObjects();
        if (gebotst != null) {
            for (GameObject g : gebotst) {
                // handel collisions
            }
        }
        // Handle input. Both on screen buttons and tilting are supported.
        // Buttons take precedence.
        boolean buttonPressed = false;
        if (OnScreenButtons.dPadUp || OnScreenButtons.dPadDown
                || OnScreenButtons.dPadLeft || OnScreenButtons.dPadRight) {
            buttonPressed = true;
        }

        if (OnScreenButtons.dPadUp || (MotionSensor.tiltUp && !buttonPressed)) {
            setDirectionSpeed(0, 8);
            setFrameNumber(1);
        }
        if (OnScreenButtons.dPadDown
                || (MotionSensor.tiltDown && !buttonPressed)){
            //setDirectionSpeed(180, 8);
            //setFrameNumber(3);
        }
        if (OnScreenButtons.dPadRight
                || (MotionSensor.tiltRight && !buttonPressed)){
            setDirectionSpeed(90, 8);
            setFrameNumber(0);
        }
        if (OnScreenButtons.dPadLeft
                || (MotionSensor.tiltLeft && !buttonPressed)){
            setDirectionSpeed(270, 8);
            setFrameNumber(2);
        }
    }

    @Override
    public void collisionOccurred(List<TileCollision> collidedTiles) {
        // Do we know for certain that the for-each loop goes through the list
        // front to end?
        // If not, we have to use a different iterator!
        for (TileCollision tc : collidedTiles)
        {
            if (tc.theTile.getTileType() == 0)
            {
                moveUpToTileSide(tc);
                setSpeed(0);
                break;
            }
        }
    }
}
