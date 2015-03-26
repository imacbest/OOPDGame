package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;
import android.location.Location;
import android.util.Log;

import java.util.List;

/**
 * The class for all Monstercreatures
 * @author Thomas Kool & Max Groenendijk
 */
public abstract class Monster extends GravatiyGameObject implements IScore, ICollision{
    /**
     * counter
     */
    protected int timeCounter;
    /**
     * the target for the monster
     */
    protected MoveableGameObject target;
    /**
     * the speed at which the monster moves
     */
    protected int moveSpeed;

    /**
     * The range which the monster can see the target
     */
    private int range;

    /**
     * Constructor
     * @param target the target that the monster will attack (toad)
     * @param range the ranges at which the monster can spot its target
     * @param speed the speed the monster will move
     */
    public Monster(MoveableGameObject target, int range, int speed){
        this.target = target;
        this.range = range;
        this.moveSpeed = speed;
    }

    /**
     * checks if the monster is in range of the target, if so it will return true
     * @return boolean
     */
    public boolean isInRange(){
        int distance = (int)Math.sqrt((this.getX()-target.getX())*(this.getX()-target.getX()) + (this.getY()-target.getY())*(this.getY()-target.getY()));
        if(distance <= this.range) {
            return true;
        }
        return false;
    }

    /**
     * the function that makes the monster move
     * This function also makes the monster look the right way
     */
    public void move(){
        timeCounter++;
        if (timeCounter % 4 == 0) {
            if(isInRange()) {
                setSpeed(moveSpeed);
                this.moveTowardsAPoint(target.getCenterX(), this.getY());
            }else{
                setSpeed(0);
            }
        }
        if(getDirection() <= 180){
            setFrameNumber(1);
        }else{
            setFrameNumber(0);
        }
    };

    /**
     * Removes the monster from the game
     */
    public void die() {
        deleteThisGameObject();
    }

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
