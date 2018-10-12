import org.newdawn.slick.SlickException;

public class Hole extends Sprite implements Collidable {

    private static final String IMG_SRC = "assets/frog.png";

    private boolean isFilled;

    /**
     * Hole constructor
     * @param x x position
     * @param y y position
     * @throws SlickException
     */
    public Hole(float x, float y) throws SlickException {
        super(x, y, IMG_SRC);
        this.isFilled = false;
    }

    @Override
    public void render() {
        if (this.isFilled) {
            super.render();
        }
    }

    @Override
    public void onCollision(Sprite other) {
        if (other instanceof Player) {
            this.isFilled = true;
        }
    }

    /**
     * Checks if hole has been filled
     * @return true or false if it has been filled
     */
    public boolean isFilled() {
        return isFilled;
    }

}
