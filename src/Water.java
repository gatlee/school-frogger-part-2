import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Water extends Sprite implements Collidable {
    private static final String IMG_SRC = "assets/water.png";
    /*****************CONSTRUCTORS*****************/
    public Water(float x, float y) throws SlickException {
        super(x, y, IMG_SRC);
    }

    public void onCollision(Collidable other) {
        //Do nothing
    }

    //Returns true if collision
    public boolean isIntersectingWith(Collidable other) {
        BoundingBox otherBoundingBox = other.getBoundingBox();
        return (getBoundingBox().intersects(otherBoundingBox));
    }
}
