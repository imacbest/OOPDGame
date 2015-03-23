package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;

import java.util.List;

/**
 * The main character of the game
 * @author Thomas & Max
 */
public class Toad extends GravatiyGameObject implements ICollision {

    private ToadParcour mygame;

    /**
     * The score of the main character
     */
    private int score;

    /**
     * The main character of the game
     * @author Thomas & Max
     */
    public Toad(ToadParcour mygame) {
        this.mygame = mygame;
        setSprite("fishframes", 4); // change fishframe to toad frams !!
        setFriction(1);

        setSpeed(0);
        setDirection(90);

        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void update(){
        super.update();
    }

    @Override
    public void collisionOccurred(List<TileCollision> collidedTiles) {

    }
}
