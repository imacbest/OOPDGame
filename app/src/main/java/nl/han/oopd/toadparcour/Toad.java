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
    private int bananas = 0;

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
    private boolean jump = false;
    private boolean jumping = false;
    private int start = 0;
    private int hoek = 0;

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
        super.gravity(-180);


        // collisions with objects
        ArrayList<GameObject> gebotst = getCollidedObjects();
        if (gebotst != null) {
            for (GameObject g : gebotst) {
                if(g instanceof Coin){
                    Coin coin = (Coin) g;
                    this.setCoins((this.getCoins()+1));
                    coin.die();
                }
            }
        }
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



        if (OnScreenButtons.buttonA) {
            setySpeed(-10);
            if(OnScreenButtons.buttonY) {
                setxSpeed(-5);
            } else if (OnScreenButtons.buttonX) {
                setxSpeed(5);
            }
        }

        if (OnScreenButtons.dPadDown
                && !buttonPressed) {
            //setDirectionSpeed(180, 8);
            //setFrameNumber(3);
        }
        if (OnScreenButtons.dPadRight
                || (MotionSensor.tiltRight && !buttonPressed)) {
            if (super.isTileOnderSpeler()) {
                setDirectionSpeed(90, 8);
                setFrameNumber(0);
            }
            if (OnScreenButtons.buttonA) { //|| (MotionSensor.tiltUp && !buttonPressed)
                if (super.isTileOnderSpeler()) {
                    jump = true;
                    start = this.getY();
                    hoek = 25;
                }
            }
        }
        if (OnScreenButtons.dPadLeft
                || (MotionSensor.tiltLeft && !buttonPressed)) {
            if (super.isTileOnderSpeler()) {
                setDirectionSpeed(270, 8);
                setFrameNumber(2);
            }
            if (OnScreenButtons.buttonA) { //|| (MotionSensor.tiltUp && !buttonPressed)
                if (super.isTileOnderSpeler()) {
                    jump = true;
                    start = this.getY();
                    hoek = 335;
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
            this.coins = coins;
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBananas(int bananas) {
        if (this.bananas + bananas <= 0) {
            this.bananas = 0;
        } else {
            this.bananas += bananas;
        }
    }



}
