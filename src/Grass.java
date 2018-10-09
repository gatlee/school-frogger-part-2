import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Grass extends Sprite {

    private static final String IMG_SRC = "assets/grass.png";
    /*****************CONSTRUCTORS*****************/
    public Grass(float x, float y) throws SlickException {
        super(x, y, IMG_SRC);
    }

}
