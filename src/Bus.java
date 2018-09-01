import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Bus extends MovableSprite{
    public static final String BUS_IMAGE_SRC = "assets/bus.png";
    public static final float BUS_SPEED = 0.15f;
    public Bus(float x, float y) throws SlickException {
        super(BUS_IMAGE_SRC, x, y);
        this.setSpeed(BUS_SPEED);
    }
    public void update(Input input, int delta) {
        float difference = this.getX() + this.getSpeed() * delta;
        this.setX(difference);
    }
}
