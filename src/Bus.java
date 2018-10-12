import org.newdawn.slick.SlickException;

public class Bus extends AutonomousSprite {
    private static final String BUS_IMAGE_SRC = "assets/bus.png";
    private static final float BUS_SPEED = 0.15f;

    /**
     * Bus Constructor
     * @param x initial x position
     * @param y initial y position
     * @param direction movement direction
     */
    public Bus(float x, float y, String direction) throws SlickException {
        //Set values
        super(BUS_IMAGE_SRC, x, y, direction, BUS_SPEED);
    }
}

