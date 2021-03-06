package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;
import android.util.Log;

/**
 * Adds gravaty to the game
 * @author Thomas & Max
 */
public class GravatiyGameObject extends MoveableGameObject {
    /**
     * Handles the gravity in the game
     *
     */
    protected void gravity(double kracht){
        if(!isTileOnderSpeler()){
            //setySpeed(kracht);
            setDirectionSpeed(180, kracht);
        }

    }

    /**
     * Checkt if there is a tile underneath the player
     * @return boolean
     */
    public boolean isTileOnderSpeler(){
        if (getTileOnPosition(getX()-2, getY()+getFrameHeight())!=null) {
            return true;
        } else if (getTileOnPosition(getX()+getFrameWidth(), getY()+getFrameHeight())!=null) {
            return true;
        }
        return false;
    }

    /**
     * Checkt if there is a tile above the player
     * @return boolean
     */
    public boolean isTileBovenSpeler(){
        if (getTileOnPosition(getX(), getY()-getFrameHeight())!=null) {
            return true;
        } else if (getTileOnPosition(getX()+getFrameWidth(), getY()-getFrameHeight())!=null) {
            return true;
        }
            return false;
    }
}
