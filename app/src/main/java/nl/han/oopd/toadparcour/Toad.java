package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.input.MotionSensor;
import android.gameengine.icadroids.input.OnScreenButton;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * The main character of the game
 * @author Thomas & Max
 */
public class Toad extends GravatiyGameObject implements ICollision {

    private ToadParcour mygame;

    /**
     * Number of bananas that toad is carrying
     */
    private int bananas = 100;

    /**
     * Number of coins that toad have collected
     */
    private int coins = 0;

    /**
     * The score of the main character
     */
    private int score;

    /**
     * The main character of the game
     * @author Thomas & Max
     */
    private int start = 0;
    private double kracht = 6.5;
    private boolean jump = false;
    private boolean fall = false;
    private double prevDirection = Constants.RIGHT;

    /**
     * Main toad function
     * @param mygame verwijzing naar hoofdclass
     */
    public Toad(ToadParcour mygame) {
        this.mygame = mygame;
        setSprite("toad", 4); // img of the character
        setFriction(1);

        setSpeed(0);
        setDirection(90);

        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void update() {
        super.update();
        super.gravity(kracht);
        handleCollisions();
        jumping();

        // Handle input. Both on screen buttons and tilting are supported.
        // Buttons take precedence.
        boolean buttonPressed = false;
        if (OnScreenButtons.dPadUp || OnScreenButtons.dPadDown
                || OnScreenButtons.dPadLeft || OnScreenButtons.dPadRight
                || OnScreenButtons.buttonA) {
            buttonPressed = true;
        } else if (super.isTileOnderSpeler()) {
            buttonPressed = false;
            setDirectionSpeed(0, 0);
        }

        if(OnScreenButtons.buttonX){
            throwBanana();
        }



        if (OnScreenButtons.buttonA && !jump && super.isTileOnderSpeler()) {
            jump = true;
        } else {
            kracht = 6.5;
        }

        //super.isTileOnderSpelerLinks();
        if (OnScreenButtons.dPadDown) {

        }
        if (OnScreenButtons.dPadUp) {

        }
        if (OnScreenButtons.dPadRight) {
            if(super.isTileOnderSpeler()) {
                setxSpeed(8);
                setySpeed(0);
                setFrameNumber(0);
            }
            }
            if (OnScreenButtons.dPadLeft) {
                if(super.isTileOnderSpeler()) {
                    setxSpeed(-8);
                    setFrameNumber(2);
                }
            }



    }

    private void throwBanana() {
        if (getBananas() != 0) {
            setBananas(-1);
            mygame.addGameObject(new FlyingBanana(this, getLookDirection()), (int) getCenterX(), (int) getCenterY());
            Log.d("Banana", "Banana added");
        }
    }


    private void jumping() {
        if (jump) {
            kracht = 0;
            if (super.isTileOnderSpeler()) {
                start = getY();
            }
            if (start - getY() <= 50) {
                setySpeed(-30);
            } else if (start - getY() <= 90) {
                setySpeed(-25);
            } else if (start - getY() <= 140) {
                setySpeed(-20);
            } else if (start - getY() <= 180) {
                setySpeed(-15);
            } else if (start - getY() <= 220) {
                setySpeed(-10);
            } else if (start - getY() <= 280) {
                setySpeed(-5);
                jump = false;
                fall = true;
                start = getY();
            }
            if (OnScreenButtons.dPadLeft) {
                setFrameNumber(2);
                setxSpeed(-5);
            } else if (OnScreenButtons.dPadRight) {
                setFrameNumber(0);
                setxSpeed(5);
            }
            if (super.isTileBovenSpeler()) {
                jump = false;
                fall = true;
                start = getY();
            }
        }



        if (fall) {
            kracht = 0;
            if (getY() - start >= 10) {
                setySpeed(15);
            } else if (getY() - start >= 90) {
                setySpeed(20);
            }
            if (OnScreenButtons.dPadLeft) {
                setFrameNumber(2);
                setxSpeed(-5);
            } else if (OnScreenButtons.dPadRight) {
                setFrameNumber(0);
                setxSpeed(5);
            }
            if (super.isTileOnderSpeler()) {
                fall = false;
            }
        }
    }




    private void handleCollisions() {
        // collisions with objects
        ArrayList<GameObject> gebotst = getCollidedObjects();
        if (gebotst != null) {
            for (GameObject g : gebotst) {
                if(g instanceof Coin){
                    Coin coin = (Coin) g;
                    this.setCoins((this.getCoins()+1));
                    coin.remove();
                }
                if(g instanceof Banana){
                    Banana banana = (Banana) g;
                    addBannana();
                    banana.remove();
                }
            }
        }
    }

    private void addBannana() {
        this.bananas++;
    }


    @Override
    public void collisionOccurred(List<TileCollision> collidedTiles) {
        // Do we know for certain that the for-each loop goes through the list
        // front to end?
        // If not, we have to use a different iterator!
        for (TileCollision tc : collidedTiles){
            if (tc.theTile.getTileType() == 0){
                moveUpToTileSide(tc);
                setSpeed(0);
                break;
            }
        }
    }

    public int getCoins() {
        return coins;
    }

    public int getBananas() {
        return bananas;
    }

    public void setCoins(int coins) {
        if(coins < 0){
            this.coins = 0;
        }else{
            this.coins += coins;
        }
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void setBananas(int bananas) {
        if (this.bananas + bananas <= 0) {
            this.bananas = 0;
        } else {
            this.bananas += bananas;
        }
    }



    public double getLookDirection() {
        if(getX() < getPrevX()){
            Log.d("Directions", "links");
            this.prevDirection = Constants.LEFT;
            return Constants.LEFT;
        }else if(getX() > getPrevX()){
            Log.d("Directions", "rechts");
            this.prevDirection = Constants.RIGHT;
            return Constants.RIGHT;
        }else{
            return prevDirection;
        }

    }
}
