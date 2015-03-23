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

    protected int moveSpeed;
    private int range;


    public Monster(MoveableGameObject target, int range, int speed){
        this.target = target;
        this.range = range;
        this.moveSpeed = speed;
    }

    public boolean isInRange(){
        int distance = (int)Math.sqrt((this.getX()-target.getX())*(this.getX()-target.getX()) + (this.getY()-target.getY())*(this.getY()-target.getY()));
        if(distance <= this.range){
            return true;
        }
        return false;
    }

    public void move(){
        timeCounter++;
        if (timeCounter % 4 == 0) {
            if(isInRange()) {
                setSpeed(moveSpeed);
                this.moveTowardsAPoint(target.getCenterX(), target.getCenterY());
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
