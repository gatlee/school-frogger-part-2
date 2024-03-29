import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * Player class that user controls
 */
public class Player extends Sprite implements Collidable {
    //Constants
    private static final String PLAYER_IMAGE_SRC = "assets/frog.png";
    private static final Integer SCREEN_WIDTH = App.SCREEN_WIDTH;
    private static final Integer SCREEN_HEIGHT = App.SCREEN_HEIGHT;
    private static final Integer STARTING_LIVES = 3;

    private float initialX;
    private float initialY;

    private boolean touchingHazard;
    private boolean touchingRidable;

    private Lives lives;


    /**
     * Player constructor
     * @param x initial x position
     * @param y initial y position
     */
    Player(float x, float y) throws SlickException {
        super(x, y, PLAYER_IMAGE_SRC);

        //Set distance to move by
        this.setSpeed(App.TILE_SIZE);
        this.initialX = x;
        this.initialY = y;
        this.lives = new Lives(STARTING_LIVES);
    }

    /**
     * Player constructor that copies other
     * @param other Another player sprite to copy
     */
    Player(Player other) throws SlickException {
        this(other.getX(), other.getY());

    }

    /*****************METHODS*****************/

    @Override
    public void update(Input input, int delta) {
        // How can this one method deal with different types of sprites?
        super.update(input, delta);
        if (this.touchingHazard && !this.touchingRidable) {
            this.killPlayer();
        }
        this.touchingHazard = false;
        this.touchingRidable = false;
    }

    @Override
    public void render() {
        super.render();
        lives.render();
    }

    @Override
    public boolean isAcceptableMovement(float x, float y) {
        return !(x - (this.getImageWidth() / 2f) < 0 ||
                x + (this.getImageWidth() / 2f) > SCREEN_WIDTH ||
                y - (this.getImageHeight() / 2f) < 0 ||
                y + (this.getImageWidth() / 2f) > SCREEN_HEIGHT);

    }

    @Override
    public void onCollision(Sprite other) {
        if (other instanceof Water || other instanceof Bus
        || other instanceof Bike || other instanceof RaceCar) {
            touchingHazard = true;
        }

        if (other.hasTag(Tags.RIDEABLE)) {
            touchingRidable = true;
        }

        if (other instanceof LifePowerUp && !((LifePowerUp) other).isConsumed()) {
            lives.incrementLives();

        }

        if (other instanceof Hole) {
            this.setXY(initialX, initialY);
        }

    }

    /**
     * Kill player event
     */
    public void killPlayer() {
        this.setXY(initialX, initialY);
        if (lives.getNumLives() <= 0) {
            App.exit();
        }

        this.lives.decrementLives();

    }


    @Override
    public boolean isIntersectingWith(Collidable other) {
        BoundingBox otherBoundingBox = other.getBoundingBox();
        return (getBoundingBox().intersects(otherBoundingBox));
    }


}
