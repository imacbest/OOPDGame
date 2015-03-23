package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;
import android.location.Location;

import java.util.List;

/**
 * Monster
 */
public abstract class Monster extends GravatiyGameObject implements IScore, ICollision{
    protected int timeCounter;
    protected MoveableGameObject target;

    private int moveSpeed;
    private int range;


    public Monster(MoveableGameObject target, int range){
        this.target = target;
        this.range = range;
    }

    public boolean isInRange(){
        int distance = (int)Math.sqrt((this.getX()-target.getX())*(this.getX()-target.getX()) + (this.getY()-target.getY())*(this.getY()-target.getY()));
        if(distance <= this.range){
            return true;
        }
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
