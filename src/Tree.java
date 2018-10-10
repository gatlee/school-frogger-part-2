import org.newdawn.slick.SlickException;

public class Tree extends Sprite{
    private static final String IMG_SRC = "assets/tree.png";
    /*****************CONSTRUCTORS*****************/
    public Tree(float x, float y) throws SlickException {
        super(x, y, IMG_SRC);
        this.addTag(Tags.SOLID);
    }
}
