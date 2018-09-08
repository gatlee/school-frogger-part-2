import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Bus extends MovableSprite {
    public static final String BUS_IMAGE_SRC = "assets/bus.png";
    public static final float BUS_SPEED = 0.15f;


    private float initialXPosition;
    private float initialYPosition;
    private String movementDirection;

    /*****************CONSTRUCTORS*****************/
    public Bus(float x, float y, String direction) throws SlickException {
        //Set values
        super(BUS_IMAGE_SRC, x, y);
        this.setSpeed(BUS_SPEED);
        this.setMovementDirection(direction);

        //Error checking of initial location
        if (this.isOffScreen()) {
            throw new SlickException("ERROR: Bus Initialised Offscreen. " +
                    "Bus will infinitely reset in background. " +
                    "Create location elsewhere");
        }

        //Set initial location of bus to be reset to
        this.initialXPosition = x;
        this.initialYPosition = y;
    }

    public Bus(float x, float y) throws SlickException {
        //Set default direction as right if unspecified
        this(x, y, "right");
    }

    /*****************GETTERS AND SETTERS*****************/
    public void setInitialXPosition(float initialXPosition) {
        this.initialXPosition = initialXPosition;
    }

    public String getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(String movementDirection) {
        this.movementDirection = movementDirection;
    }


    /*****************ACTUAL METHODS*****************/
    public void update(Input input, int delta) {
        super.update(input, delta);
        this.move(getMovementDirection(), delta);

        if (this.isOffScreen()) {
            this.resetPositionToInitial();
        }
    }

    //Reset bus to starting location
    private void resetPositionToInitial() {
        this.setXY(this.initialXPosition, this.initialYPosition);
    }

    public void onCollision(Collidable other) {
        //Do Nothing
    }

}

