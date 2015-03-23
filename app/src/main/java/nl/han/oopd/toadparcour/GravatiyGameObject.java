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
        if(!isTileOnderSpeler()){
            setDirectionSpeed(-180, 6.5);
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
