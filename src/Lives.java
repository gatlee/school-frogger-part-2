import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Lives counter
 */
public class Lives {
    private static final int X_POS = 24;
    private static final int Y_POS = 744;
    private static final int SPACE_BETWEEN_LIVES = 32;
    private static final String IMG_SRC = "assets/lives.png";
    private Image lifeImage;
    private int numLives;


    /**
     * Lives Constructor
     * @param numLives initial number of lives
     */
    public Lives(int numLives) {
        this.numLives = numLives;
        try {
            lifeImage = new Image(IMG_SRC);
        } catch (SlickException e) {
            e.printStackTrace();
        }


    }

    /**
     * Returns number of lives
     * @return Current number of lives
     */
    public int getNumLives() {
        return numLives;
    }

    /**
     * Graphically display lives
     */
    public void render() {
        for (int i = 0; i < numLives; i++) {
            lifeImage.drawCentered(X_POS + i*SPACE_BETWEEN_LIVES, Y_POS);
        }

    }

    /**
     * Decrement number of lives by 1
     */
    public void decrementLives() {
        this.numLives--;
    }

    /**
     * Increment number of lives by 1
     */
    public void incrementLives() {
        this.numLives++;
    }
}
