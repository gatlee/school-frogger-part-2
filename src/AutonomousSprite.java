import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Sprite that moves on it's own
 */
public abstract class AutonomousSprite extends Sprite {
    private final float OFF_SCREEN_X_POS_LEFT;
    private final float OFF_SCREEN_X_POS_RIGHT;
    private String movementDirection;

    private ArrayList<Sprite> childSprites = new ArrayList<>();

    /**
     * Create autonomous sprite
     * @param imageSrc location of image
     * @param x initial x position
     * @param y initial y position
     * @param direction initial movement direction
     * @param speed speed of movement (pixels per ms)
     */
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

    /**
     * Gets movement direction
     * @return movement direction
     */
    public String getMovementDirection() {
        return movementDirection;
    }

    /**
     * Sets movement direction
     * @param movementDirection movement direction (String)
     */
    public void setMovementDirection(String movementDirection) {
        this.movementDirection = movementDirection;
    }

    /**
     * Adds child sprite to child sprites (child sprite moves with parent sprite)
     * @param child Sprite type
     */
    public void addChildSprite(Sprite child) {
        if (!this.childSprites.contains(child)) {
            this.childSprites.add(child);

        }
    }

    /*****************ACTUAL METHODS*****************/
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
        this.move(getMovementDirection(), delta);
        this.moveChildSprites(delta);

        if (this.isOffScreen()) {
            this.resetPosition();
        }
    }


    /**
     * Reset bus to other side of screen
     */
    private void resetPosition() {
        if (this.getMovementDirection().equals(LEFT)) {
            this.setX(OFF_SCREEN_X_POS_RIGHT);

        }
        else if (this.getMovementDirection().equals(RIGHT)) {
            this.setX(OFF_SCREEN_X_POS_LEFT);
        }

    }

    @Override
    public void onCollision(Sprite other) {
        if (other instanceof Player) {
            this.addChildSprite(other);
        }

    }

    /**
     * Moves child sprites with this sprite in same direction and at same speed
     * @param delta Delta between frames in ms
     */
    public void moveChildSprites(int delta) {
        //Prune child sprites that aren't colliding anymore
        childSprites.removeIf(s -> !this.isIntersectingWith(s));
        for (Sprite sprite: childSprites) {
            if (sprite instanceof Player) {
                sprite.move(this.getMovementDirection(), delta, this.getSpeed());
            }

        }
    }

    /**
     * Removes specified sprite.
     * @param sprite Sprite type
     */
    public void removeChild(Sprite sprite) {
        if (this.childSprites.contains(sprite)) {
            this.childSprites.remove(sprite);
        }
    }

    /**
     * Toggle movement direction from left to right
     */
    public void toggleMovementDirection() {
        if (this.getMovementDirection().equals(Sprite.RIGHT)) {
            this.setMovementDirection(Sprite.LEFT);
        } else if (this.getMovementDirection().equals(Sprite.LEFT)) {
            this.setMovementDirection(Sprite.RIGHT);
        }
    }
}
