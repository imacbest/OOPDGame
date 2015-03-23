package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Adds gravaty to the game
 * @author Thomas & Max
 */
public class GravatiyGameObject extends MoveableGameObject {

    /**
     * Handles the gravity in the game
     *
     */
    protected void gravity(){
        // zwaartekracht
        if(!isTileOnderSpeler()){
            setDirectionSpeed(-180, 4.8);
        }

    }

    /**
     * Checkt if there is a tile underneath the player
     * @return boolean
     */
    private boolean isTileOnderSpeler(){
        return getTileOnPosition(getX(), getY()+getFrameHeight())!=null;
    }
}
