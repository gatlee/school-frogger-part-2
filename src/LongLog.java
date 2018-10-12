import org.newdawn.slick.SlickException;

/**
 * Longer log that moves by itself
 */
public class LongLog extends Log{

    private static final String IMAGE_SRC = "assets/longlog.png";
    private static final float SPEED = 0.07f;

    /**
     * LongLog constructor
     * @param x initial x position
     * @param y initial y position
     * @param direction movement direction
     */
    public LongLog(float x, float y, String direction) throws SlickException {
        super(x, y, direction, IMAGE_SRC, SPEED);
        this.addTag(Tags.RIDEABLE);
    }
}
