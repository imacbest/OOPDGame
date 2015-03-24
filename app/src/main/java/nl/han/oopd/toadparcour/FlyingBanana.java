package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;

import java.util.ArrayList;
import java.util.List;

/**
 * Created the flying bananas, where the main character can kill the monsters with
 */
public class FlyingBanana extends GravatiyGameObject implements ICollision {
    // ToDo: test this class

    public FlyingBanana() {

        setSprite("trowbanana");
    }

    public void update(){
        super.update();
        handleCollisions();
    }

    public void move(){


        // ToDo: flying banana's
    }

    public void remove(){
        deleteThisGameObject();
    }

    private void handleCollisions() {
        // collisions with objects
        ArrayList<GameObject> gebotst = getCollidedObjects();
        if (gebotst != null) {
            for (GameObject g : gebotst) {
                if(g instanceof Monster){
                    Monster monster = (Monster) g;
                    monster.die();
                }
            }
        }
    }

    @Override
    public void collisionOccurred(List<TileCollision> collidedTiles) {
        // Do we know for certain that the for-each loop goes through the list
        // front to end?
        // If not, we have to use a different iterator!
        for (TileCollision tc : collidedTiles){
            this.remove();
        }
    }

}
