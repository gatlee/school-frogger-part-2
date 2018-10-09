import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class AutonomousSprite extends Sprite {
    private final float OFF_SCREEN_X_POS_LEFT;
    private final float OFF_SCREEN_X_POS_RIGHT;
    private float initialXPosition;
    private float initialYPosition;
    private String movementDirection;

    public AutonomousSprite(String imageSrc, float x, float y, String direction, float speed) throws SlickException {
        super(x, y, imageSrc);
        this.setSpeed(speed);
        this.setMovementDirection(direction);
        if (this.isOffScreen()) {
            throw new SlickException("ERROR: Bus Initialised Offscreen. " +
                    "Bus will infinitely reset in background. " +
                    "Create location elsewhere");
        }
        OFF_SCREEN_X_POS_LEFT = - (this.getImageWidth()/2f);
        OFF_SCREEN_X_POS_RIGHT = App.SCREEN_WIDTH + (this.getImageWidth()/2f);
    }

    /*****************GETTERS AND SETTERS*****************/
    public float getInitialYPosition() {
        return initialYPosition;
    }

    public void setInitialYPosition(float initialYPosition) {
        this.initialYPosition = initialYPosition;
    }

    public void setInitialXPosition(float initialXPosition) {
        this.initialXPosition = initialXPosition;
    }

    public float getInitialXPosition() {
        return initialXPosition;
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
            this.resetPosition();
        }
    }

    //Reset bus to starting location
    private void resetPosition() {
        if (this.getMovementDirection().equals(LEFT)) {
            this.setX(OFF_SCREEN_X_POS_RIGHT);

        }
        else if (this.getMovementDirection().equals(RIGHT)) {
            this.setX(OFF_SCREEN_X_POS_LEFT);
        }

    }

    public void onCollision(Collidable other) {
        //Do Nothing
    }
}
