import org.newdawn.slick.SlickException;

public class Racecar extends Vehicle{

    public static final String IMAGE_SRC = "assets/racecar.png";
    public static final float SPEED = 0.5f;

    public Racecar(float x, float y, String direction) throws SlickException {
        super(IMAGE_SRC, x, y, direction, SPEED);
    }
}
