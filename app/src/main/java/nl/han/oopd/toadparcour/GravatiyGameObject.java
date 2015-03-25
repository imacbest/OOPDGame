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
        if (getTileOnPosition(getX(), getY()+getFrameHeight())!=null) {
            return true;
//        } else if (getTileOnPosition(getX()+getFrameWidth(), getY()+getFrameHeight())!=null) {
//            return true;
        } else if (getTileOnPosition(getX()-getFrameWidth(), getY()+getFrameHeight())!=null) {
            return true;
        } else {
            return false;
        }
//        return getTileOnPosition(getX(), getY()+getFrameHeight())!=null;
    }

//    public boolean isTileOnderSpelerLinks(){
//        return getTileOnPosition(getX()+getFrameWidth(), getY()+getFrameHeight())!=null;
//    }
//
//    public boolean isTileOnderSpelerRechts(){
//        return getTileOnPosition(getX()-getFrameWidth(), getY()+getFrameHeight())!=null;
//    }

    public boolean isTileBovenSpeler(){
        if (getTileOnPosition(getX(), getY()-getFrameHeight())!=null) {
            return true;

//        } else if (getTileOnPosition(getX()+getFrameWidth(), getY()-getFrameHeight())!=null) {
//            return true;
        } else if (getTileOnPosition(getX()-(getFrameWidth()*2), getY()-getFrameHeight())!=null) {
            return true;
        } else {
            return false;
        }
    }

//    public boolean isTileLinksSpeler(){
//        return getTileOnPosition(getX()+2, getX() + getFrameWidth())!=null;
//    }
//
//    public boolean isTileRechtsSpeler(){
//        return getTileOnPosition(getX()-2, getX() - getFrameWidth())!=null;
//    }

}
