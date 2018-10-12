import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Bulldozer extends AutonomousSprite {
    private static final String IMAGE_SRC = "assets/bulldozer.png";
    private static final float SPEED = 0.05f;
    private static final float FRONT_FUZZ = 3f;

    /**
     * Bulldozer constructor
     * @param x initial x position
     * @param y initial y position
     * @param direction movement direction
     */
    public Bulldozer(float x, float y, String direction) throws SlickException {
        super(IMAGE_SRC, x, y, direction, SPEED);
    }

    @Override
    public void onCollision(Sprite other) {
        if (other instanceof Player) {
            if (isCollidingWithFront(other)) {
                this.addChildSprite(other);
            }
            else {
                ((Player) other).killPlayer();
            }

        }
    }

    /**
     * Checks if other sprite is pressed against front of Bulldozer
     * @param other Sprite type being checked
     * @return True if against front. False otherwise
     */
    private boolean isCollidingWithFront(Sprite other) {
        //The "Front" depends on movement direction
        float collisionDepth = 0;

        if(this.getMovementDirection().equals(Sprite.LEFT)) {
            collisionDepth = other.getBoundingBox().getRight() - this.getBoundingBox().getLeft();

        } else {
            collisionDepth = this.getBoundingBox().getRight() - other.getBoundingBox().getLeft();

        }
        return collisionDepth < FRONT_FUZZ;

    }
}
