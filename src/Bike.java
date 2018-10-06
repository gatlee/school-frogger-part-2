import org.newdawn.slick.SlickException;

public class Bike extends Vehicle{

    public static final String IMAGE_SRC = "assets/bike.png";
    public static final float SPEED = 0.2f;

    public Bike(float x, float y, String direction) throws SlickException {
        super(IMAGE_SRC, x, y, direction, SPEED);
    }
}
