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
    protected void gravity(double kracht){
        if(!isTileOnderSpeler()){
            setDirectionSpeed(180, kracht);
        }

    }

    /**
     * Checkt if there is a tile underneath the player
     * @return boolean
     */
    public boolean isTileOnderSpeler(){
        return getTileOnPosition(getX(), getY()+getFrameHeight())!=null;
    }

    public boolean isTileBovenSpeler(){
        return getTileOnPosition(getX(), getY()+(getFrameHeight()+25))!=null;
    }
}
