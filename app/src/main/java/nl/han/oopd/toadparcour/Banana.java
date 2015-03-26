package nl.han.oopd.toadparcour;

/**
 * Creating bananas which the main character can pick up en can throw away to kill the monsters.
 * Also can he collect points when he pick up a banana.
 * @author Thomas Kool
 */
public class Banana extends Items {
    private int timeCounter = 0;
    private int timeCounterFast = 0;

    /**
     * Constructor, sets the sprite
     */
    public Banana(){
        setSprite("banaan", 8);
    }

    /**
     * makes sure the animation is called
     */
    @Override
    public void update(){
        super.update();
        this.turn();
    }

    /**
     * Loops through the animation
     */
    public void turn() {
        if(timeCounterFast % 4 == 0) {
            setFrameNumber(timeCounter % 8);
            timeCounter++;
        }
        timeCounterFast++;
    }

}
