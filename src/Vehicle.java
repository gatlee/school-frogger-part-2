import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Vehicle extends MovableSprite {
    private float initialXPosition;
    private float initialYPosition;
    private String movementDirection;

    public Vehicle(String imageSrc, float x, float y, String direction, float speed) throws SlickException {
        super(imageSrc, x, y);
        this.setSpeed(speed);
        this.setMovementDirection(direction);
        if (this.isOffScreen()) {
            throw new SlickException("ERROR: Bus Initialised Offscreen. " +
                    "Bus will infinitely reset in background. " +
                    "Create location elsewhere");
        }
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
