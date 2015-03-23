package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;

import java.util.List;

/**
 * Monster
 */
public abstract class Monster extends GravatiyGameObject implements IScore, ICollision{
    private int timeCounter;
    private MoveableGameObject target;

    private int moveSpeed;
    private int range;


    public Monster(MoveableGameObject target){
        this.target = target;
    }

    public boolean isInRange(){
        return false;
    }

    public abstract void move();

    public abstract void die();

    @Override
    public void  update(){
        super.update();
    }




    /**
     * Tile collision: monster bounces off all tiles, so use first collision
     *
     * @see android.gameengine.icadroids.objects.collisions.ICollision#collisionOccurred(java.util.List)
     */
    @Override
    public void collisionOccurred(List<TileCollision> collidedTiles) {
        bounce(collidedTiles.get(0));
    }



}
