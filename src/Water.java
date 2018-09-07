import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Water extends Sprite {
    public Water(float x, float y) throws SlickException {
        super(x, y);
        Image image = new Image("assets/water.png");
        this.setImage(image);
    }

    public Water() throws SlickException {
        this(0f, 0f);
    }
}
