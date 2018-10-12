import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * Water tile
 */
public class Water extends Sprite implements Collidable {
    private static final String IMG_SRC = "assets/water.png";
    /**
     * Water constructor
     * @param x initial x position
     * @param y initial y position
     * @throws SlickException
     */
    public Water(float x, float y) throws SlickException {
        super(x, y, IMG_SRC);
    }

    /**
     * Do something on collision
     * @param other object collided with
     */
    public void onCollision(Collidable other) {
        //Do nothing
    }


}
