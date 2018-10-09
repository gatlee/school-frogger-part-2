import org.newdawn.slick.SlickException;

public class Log extends AutonomousSprite {
    public static final String IMAGE_SRC = "assets/log.png";
    public static final float SPEED = 0.1f;

    public Log(float x, float y, String direction) throws SlickException {
        this(x, y, direction, IMAGE_SRC, SPEED);
    }

    public Log(float x, float y, String direction, String altImage, float altSpeed) throws SlickException {
        super(altImage, x, y, direction, altSpeed);
        this.addTag(Tags.RIDEABLE);
    }
}
