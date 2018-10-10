import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Lives {
    private static final int X_POS = 24;
    private static final int Y_POS = 744;
    private static final int SPACE_BETWEEN_LIVES = 32;
    private static final String IMG_SRC = "assets/lives.png";
    private Image lifeImage;
    private int numLives;


    public Lives(int numLives) {
        this.numLives = numLives;
        try {
            lifeImage = new Image(IMG_SRC);
        } catch (SlickException e) {
            e.printStackTrace();
        }


    }

    public int getNumLives() {
        return numLives;
    }

    public void render() {
        for (int i = 0; i < numLives; i++) {
            lifeImage.drawCentered(X_POS + i*SPACE_BETWEEN_LIVES, Y_POS);
        }

    }

    public void decrementLives() {
        this.numLives--;
    }
}
