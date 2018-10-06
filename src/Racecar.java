import org.newdawn.slick.SlickException;

public class Racecar extends Vehicle{

    public static final String RACE_CAR_IMAGE_SRC = "assets/racecar.png";
    public static final float RACE_CAR_SPEED = 0.5f;

    public Racecar(float x, float y, String direction) throws SlickException {
        super(RACE_CAR_IMAGE_SRC, x, y, direction, RACE_CAR_SPEED);
    }
}
