import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Grass extends Sprite {

    /*****************CONSTRUCTORS*****************/
    public Grass(float x, float y) throws SlickException {
        super(x, y);
        Image image = new Image("assets/grass.png");
        this.setImage(image);
    }

}
