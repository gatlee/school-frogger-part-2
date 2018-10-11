import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Bike extends AutonomousSprite {

    public static final String IMAGE_SRC = "assets/bike.png";
    public static final float SPEED = 0.2f;
    private static final float LEFT_BOUND = 24f;
    private static final float RIGHT_BOUND = 1000f;

    public Bike(float x, float y, String direction) throws SlickException {
        super(IMAGE_SRC, x, y, direction, SPEED);
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
        if (this.getMovementDirection().equals(Sprite.RIGHT) && this.getX() > RIGHT_BOUND) {
            this.setMovementDirection(Sprite.LEFT);
        }
        if (this.getMovementDirection().equals(Sprite.LEFT) && this.getX() < LEFT_BOUND) {
            this.setMovementDirection(Sprite.RIGHT);
        }
    }
}
