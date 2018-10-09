import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Player extends Sprite implements Collidable {
    //Constants
    private static final String PLAYER_IMAGE_SRC = "assets/frog.png";
    private static final Integer SCREEN_WIDTH = App.SCREEN_WIDTH;
    private static final Integer SCREEN_HEIGHT = App.SCREEN_HEIGHT;

    private float initialX;
    private float initialY;

    private boolean touchingHazard;
    private boolean touchingRidable;

    /*****************CONSTRUCTORS*****************/
    Player(float x, float y) throws SlickException {
        super(x, y, PLAYER_IMAGE_SRC);

        //Set distance to move by
        this.setSpeed(App.TILE_SIZE);
        this.initialX = x;
        this.initialY = y;
    }

    Player (Player other) throws SlickException {
        this(other.getX(), other.getY());

    }

    /*****************METHODS*****************/

    public void update(Input input, int delta) {
        // How can this one method deal with different types of sprites?
        super.update(input, delta);
        if (this.touchingHazard && !this.touchingRidable) {
            this.killPlayer();
        }
        this.touchingHazard = false;
        this.touchingRidable = false;
    }

    //Returns false if any part of object is out of bounds
    public boolean isAcceptableMovement(float x, float y) {
        return !(x - (this.getImageWidth() / 2f) < 0 ||
                x + (this.getImageWidth() / 2f) > SCREEN_WIDTH ||
                y - (this.getImageHeight() / 2f) < 0 ||
                y + (this.getImageWidth() / 2f) > SCREEN_HEIGHT);

    }

    //Exits if collides with Water or Bus
    public void onCollision(Sprite other) {
        if (other instanceof Water || other instanceof Bus
        || other instanceof Bike || other instanceof RaceCar) {
            touchingHazard = true;
        }

        if (other.hasTag(Tags.RIDEABLE)) {
            touchingRidable = true;
        }

    }

    public void killPlayer() {
        this.setXY(initialX, initialY);
    }


    //Returns true if bounding boxes intersect
    public boolean isIntersectingWith(Collidable other) {
        BoundingBox otherBoundingBox = other.getBoundingBox();
        return (getBoundingBox().intersects(otherBoundingBox));
    }


}
