import org.newdawn.slick.SlickException;

/**
 * Log that moves by itself
 */
public class Log extends AutonomousSprite {
    private static final String IMAGE_SRC = "assets/log.png";
    private static final float SPEED = 0.1f;

    /**
     * Log constructor
     * @param x initial x position
     * @param y initial y position
     * @param direction movement direction
     */
    public Log(float x, float y, String direction) throws SlickException {
        this(x, y, direction, IMAGE_SRC, SPEED);
    }

    /**
     * Log constructor with alternative image
     * @param x initial x position
     * @param y initial y position
     * @param direction movement direction
     * @param altImage alternative image
     * @param altSpeed alternative speed
     */
    public Log(float x, float y, String direction, String altImage, float altSpeed) throws SlickException {
        super(altImage, x, y, direction, altSpeed);
        this.addTag(Tags.RIDEABLE);
    }

    @Override
    public void onCollision(Sprite other) {
        this.addChildSprite(other);
    }
}
