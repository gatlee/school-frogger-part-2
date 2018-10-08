import org.newdawn.slick.SlickException;

public class LongLog extends Log{

    public static final String IMAGE_SRC = "assets/longlog.png";
    public static final float SPEED = 0.07f;

    public LongLog(float x, float y, String direction) throws SlickException {
        super(x, y, direction, IMAGE_SRC, SPEED);
    }
}
