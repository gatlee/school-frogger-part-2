import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Grass extends Sprite {

    private static final String IMG_SRC = "assets/grass.png";
    /**
     * Grass constructor
     * @param x initial x position
     * @param y initial y position
     */
    public Grass(float x, float y) throws SlickException {
        super(x, y, IMG_SRC);
    }

}
