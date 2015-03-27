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
 * @author Thomas Kool & Max Groenendijk
 */
public class Toad extends GravatiyGameObject implements ICollision {

    /**
     * instance of ToadParcour
     */
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
    private int score = 0;

    // ToDo: max wat doen deze variabelen? Javadoc toevoeg :)
    private int start = 0;
    private double kracht = 6.5;
    private boolean jump = false;
    private boolean fall = false;

    /**
     * The direction Toad is looking at, needed for the banana's
     */
    private double prevDirection = Constants.RIGHT;

    /**
     * last time you have thrown a banana
     */
    private long prevBanana = 0;

    /**
     * Main toad function
     * @param mygame reffrence to the ToadParcour instance
     */
    public Toad(ToadParcour mygame) {
        this.mygame = mygame;
        setSprite("toad", 4); // img of the character
        setFriction(1);

        setSpeed(0);
        setDirection(90);

        this.score = 0;
    }

    /**
     * Get the current score of the player
     * @return players score
     */
    public int getScore() {
        return score;
    }

    /**
     * update function
     * ToDo: clean up this update function (MAX)
     */
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
            setxSpeed(0);
        }
        if(OnScreenButtons.buttonX){
            throwBanana();
        }

        if (OnScreenButtons.buttonA && !jump && super.isTileOnderSpeler()) {
            jump = true;
        } else {
            kracht = 6.5;
        }

        if (OnScreenButtons.dPadRight) {
            if(super.isTileOnderSpeler()) {
                setxSpeed(8);
                //setySpeed(0);
                setFrameNumber(0);
            }
            }
        if (OnScreenButtons.dPadLeft) {
            if(super.isTileOnderSpeler()) {
                setxSpeed(-8);
                setFrameNumber(2);
            }
        }

        if(!isTileOnderSpeler() && !jump) {
            fall = true;
        } else if(isTileOnderSpeler()) {
            fall = false;
        }
    }

    /**
     * Function to throw a bannana, it add's a movebleGameObject
     */
    private void throwBanana() {
        if(System.currentTimeMillis() - prevBanana >= 750) {
            prevBanana = System.currentTimeMillis();
            if (getBananas() != 0) {
                setBananas(-1);
                mygame.addGameObject(new FlyingBanana(this, (int) getLookDirection()), (int) getCenterX(), (int) getCenterY() - 25);
                Log.d("Banana", "Banana added");
            }
        }
    }

    /**
     * Function to let Toad jump on a natural way. How higher he jumps, how lower the speed is. Wanneer Toad
     * onder een tile komt of hij hoger dan 300 px springt, stopt de sprong en gaat hij weer naar beneden.
     */
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


    /**
     * This function handles all collions with other objects (not tiles)
     */
    private void handleCollisions() {
        // collisions with objects
        ArrayList<GameObject> gebotst = getCollidedObjects();
        if (gebotst != null) {
            for (GameObject g : gebotst) {
                if(g instanceof Coin){
                    Coin coin = (Coin) g;
                    this.setCoins((this.getCoins()+1));
                    this.setScore(10);
                    coin.remove();
                }
                if(g instanceof Banana){
                    Banana banana = (Banana) g;
                    addBannana();
                    banana.remove();
                }
                if(g instanceof Monster){
                    Monster monster = (Monster) g;
                    if(fall) {
                        addMonster(monster);
                        monster.die();
                    }


                    }
                }
            }
        }


    /**
     * Adds a banana to toad's inventory
     */
    private void addBannana() {
        this.bananas++;
    }


    private void addMonster(Monster monster) {
        Log.d("monster", "=" + monster);
    }

    /**
     * handles all collisions with tiles
     * @param collidedTiles List of TileCollision holding all tile collisions in this move.
     *
     */

    @Override
    public void collisionOccurred(List<TileCollision> collidedTiles) {
        // Do we know for certain that the for-each loop goes through the list
        // front to end?
        // If not, we have to use a different iterator!

        for (TileCollision tc : collidedTiles){
            Log.d("Tile", "The type is: " + tc.theTile.getTileType());
            if (tc.theTile.getTileType() == 0){
                moveUpToTileSide(tc);
                setSpeed(0);
                break;
            }
            if(tc.theTile.getTileType() == 1 || tc.theTile.getTileType() == 6){
                this.setScore(-10);
                this.mygame.setPlayerGameOver(true);
                setySpeed(4);
                Log.d("Player", "Player is standing on lava");
                break;
            }

            if(tc.theTile.getTileType() == 3){
                mygame.setPlayerOnEndPoint(true);
                Log.d("Game", "Player is on endpoint");
                break;
            }else{
                mygame.setPlayerOnEndPoint(false);
                Log.d("Game", "Player is NOT endpoint");
                break;
            }
        }
    }

    /**
     * Get current amount of coins
     * @return coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * get current amount of bananas
     * @return bananas
     */
    public int getBananas() {
        return bananas;
    }

    /**
     * Let's you change the amount of coins, checks if the value stays above zero
     * @param coins
     */
    public void setCoins(int coins) {
        if(coins < 0){
            this.coins = 0;
        }else{
            this.coins += coins;
        }
    }

    /**
     * Let's you set the score, even below zero
     * @param score
     */
    public void setScore(int score) {
        this.score += (score * (int)ToadParcour.difficulty);
    }

    /**
     * Let's you set the amount of banana's toad is carrying, it also makes sure that the value stays above zero
     * @param bananas
     */
    public void setBananas(int bananas) {
        if (this.bananas + bananas <= 0) {
            this.bananas = 0;
        } else {
            this.bananas += bananas;
        }
    }


    /**
     * Functiont to determine at wich direction toad is looking
     * @return direction
     */
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
