import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Bulldozer extends AutonomousSprite {
    public static final String IMAGE_SRC = "assets/bulldozer.png";
    public static final float SPEED = 0.05f;
    private static final float FRONT_FUZZ = 3f;

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
