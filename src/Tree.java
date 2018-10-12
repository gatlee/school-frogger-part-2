import org.newdawn.slick.SlickException;

/**
 * Tree object
 */
public class Tree extends Sprite{
    private static final String IMG_SRC = "assets/tree.png";
    /**
     * Tree constructor
     * @param x initial x position
     * @param y initial y position
     */
    public Tree(float x, float y) throws SlickException {
        super(x, y, IMG_SRC);
        this.addTag(Tags.SOLID);
    }
}
