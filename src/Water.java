import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Water extends Sprite implements Collidable {
    public Water(float x, float y) throws SlickException {
        super(x, y);
        Image image = new Image("assets/water.png");
        this.setImage(image);
    }

    public Water() throws SlickException {
        this(0f, 0f);
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
