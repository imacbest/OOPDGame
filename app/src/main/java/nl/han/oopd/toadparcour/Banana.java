package nl.han.oopd.toadparcour;

/**
 * Creating bananas which the main character can pick up en can throw away to kill the monsters.
 * Also can he collect points when he pick up a banana.
 */
public class Banana extends Items {
    private int timeCounter = 0;
    private int timeCounterFast = 0;

    public Banana(){
        setSprite("banaan", 8);
    }

    @Override
    public void update(){
        super.update();
        this.turn();
    }

    public void turn() {
        if(timeCounterFast % 4 == 0) {
            setFrameNumber(timeCounter % 8);
            timeCounter++;
        }
        timeCounterFast++;
    }
}
