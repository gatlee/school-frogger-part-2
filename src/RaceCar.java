import org.newdawn.slick.SlickException;

/**
 * RaceCar class
 */
public class RaceCar extends AutonomousSprite {

    private static final String IMAGE_SRC = "assets/racecar.png";
    private static final float SPEED = 0.5f;


    /**
     * Racecar constructor
     * @param x initial x position
     * @param y initial y position
     * @param direction initial movement direction
     */
    public RaceCar(float x, float y, String direction) throws SlickException {
        super(IMAGE_SRC, x, y, direction, SPEED);
    }
}
