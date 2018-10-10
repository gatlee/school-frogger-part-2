import org.newdawn.slick.SlickException;

public class LifePowerUp extends AutonomousSprite{
    private static final String IMG_SRC = "assets/extralife.png";
    private static final int SPEED = App.TILE_SIZE;

    public LifePowerUp(float x, float y, String direction) throws SlickException {
        super(IMG_SRC, x, y, direction, SPEED);
    }
}
