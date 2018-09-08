import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import utilities.BoundingBox;


public abstract class Sprite implements Collidable {
    private float x;
    private float y;
    private Image image;
    public static final int SCREEN_WIDTH = App.SCREEN_WIDTH;
    public static final int SCREEN_HEIGHT = App.SCREEN_HEIGHT;


    private BoundingBox boundingBox;

    public Sprite(float x, float y) {
        this.boundingBox = new BoundingBox(x, y, App.TILE_SIZE, App.TILE_SIZE);
        this.setXY(x, y);
        this.updateBoundingBox();
    }

    public Sprite(Sprite other) {
        this(other.getX(), other.getY());
        this.setImage(other.getImage());
    }
    /*****************GETTERS AND SETTERS*****************/
    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        this.boundingBox.setX(x);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        this.boundingBox.setY(y);
    }

    public void setXY(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public int getImageWidth() {
        return this.image.getWidth();
    }

    public int getImageHeight() {
        return this.image.getHeight();
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }


    /*****************REGULAR METHODS*****************/
    //Sprite is non-moving so no need to update
    public void update(Input input, int delta) {
    }

    public void render() {
        image.drawCentered(this.getX(), this.getY());

    }

    //Calculates if sprite image exceeds limits of screen
    public boolean isOffScreen() {
        float x = this.getX();
        float y = this.getY();
        int widthBuffer = this.getImageWidth() / 2;
        int heightBuffer = this.getImageHeight() / 2;
        return (x + widthBuffer < 0 || x - widthBuffer > SCREEN_WIDTH ||
                y + heightBuffer < 0 || y - heightBuffer > SCREEN_HEIGHT);

    }

    public void updateBoundingBox() {
        this.boundingBox.setX(this.getX());
        this.boundingBox.setY(this.getY());
    }

    public void onCollision(Collidable other) {
        //Do nothing
    }

    public boolean isIntersectingWith(Collidable other) {
        BoundingBox otherBoundingBox = other.getBoundingBox();
        return (getBoundingBox().intersects(otherBoundingBox));

    }
}
