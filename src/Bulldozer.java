import org.newdawn.slick.SlickException;

public class Bulldozer extends AutonomousSprite {
    public static final String IMAGE_SRC = "assets/bulldozer.png";
    public static final float SPEED = 0.05f;

    public Bulldozer(float x, float y, String direction) throws SlickException {
        super(IMAGE_SRC, x, y, direction, SPEED);
    }
}
