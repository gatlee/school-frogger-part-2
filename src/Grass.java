import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Grass extends Sprite {

    public Grass(float x, float y) throws SlickException {
        super(x, y);
        Image image = new Image("assets/grass.png");
        this.setImage(image);
    }

    public Grass() throws SlickException {
        this(0f, 0f);
    }


}
