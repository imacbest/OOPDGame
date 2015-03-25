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

    public boolean isTileOnderSpelerLinks(){
        boolean result = getTileOnPosition(getX()+getFrameWidth(), getY()+getFrameHeight())!=null;
        Log.d("Tile" + getX(), "Links = " + result);
        return result;
                //getTileOnPosition(getX()-5, getY()+getFrameHeight())!=null;
    }

    public boolean isTileOnderSpelerRechts(){
        return getTileOnPosition(getX()-getFrameWidth(), getY()+getFrameHeight())!=null;
    }

    public boolean isTileBovenSpeler(){
        return getTileOnPosition(getX(), getY()-getFrameHeight())!=null;
    }

    public boolean isTileLinksSpeler(){
        return getTileOnPosition(getX()+2, getX() + getFrameWidth())!=null;
    }

    public boolean isTileRechtsSpeler(){
        return getTileOnPosition(getX()-2, getX() - getFrameWidth())!=null;
    }

}
