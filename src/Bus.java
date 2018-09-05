import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Bus extends MovableSprite{
    public static final String BUS_IMAGE_SRC = "assets/bus.png";
    public static final float BUS_SPEED = 0.15f;


    private float initialXPosition;
    private float initialYPosition;


    private String movementDirection;

    public Bus(float x, float y, String direction) throws SlickException {
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

    public void setInitialXPosition(float initialXPosition) {
        this.initialXPosition = initialXPosition;
    }

    public String getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(String movementDirection) {
        this.movementDirection = movementDirection;
    }

    public Bus(float x, float y) throws SlickException {
        //Default bus direction is right
        this(x, y, "right");
    }

    public void update(Input input, int delta) {
        this.move(getMovementDirection(), delta);

        if (this.isOffScreen()) {
            this.resetPositionToInitial();
        }
    }

    //Reset bus to starting location
    private void resetPositionToInitial() {
        this.setXY(this.initialXPosition, this.initialYPosition);
    }
}

