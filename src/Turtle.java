import org.newdawn.slick.SlickException;

public class Turtle extends AutonomousSprite {

    public static final String IMAGE_SRC = "assets/turtles.png";
    public static final float SPEED = 0.085f;

    public Turtle(float x, float y, String direction) throws SlickException {
        super(IMAGE_SRC, x, y, direction, SPEED);
    }
}
