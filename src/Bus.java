import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Bus extends Vehicle {
    public static final String BUS_IMAGE_SRC = "assets/bus.png";
    public static final float BUS_SPEED = 0.15f;


    /*****************CONSTRUCTORS*****************/
    public Bus(float x, float y, String direction) throws SlickException {
        //Set values
        super(BUS_IMAGE_SRC, x, y);
        this.setSpeed(BUS_SPEED);
        this.setMovementDirection(direction);

        //Error checking of initial location
        if (this.isOffScreen()) {
            throw new SlickException("ERROR: Bus Initialised Offscreen. " +
                    "Bus will infinitely reset in background. " +
                    "Create location elsewhere");
        }
    }
}

