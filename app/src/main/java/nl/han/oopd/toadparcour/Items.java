package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.GameObject;

/**
 * Creating the Items in the game
 * @author Thomas Kool & Max Groenendijk
 */
public class Items extends GameObject implements IScore {
    /**
     * points
     */
    int points = 0;

    /**
     * removes the instance from the game
     */
    public void remove(){
        deleteThisGameObject();
    }

}
