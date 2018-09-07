import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.HashMap;
import java.util.Map;
public class Sprite {
    private float x;
    private float y;
    private Image image;
    public static final int SCREEN_WIDTH = App.SCREEN_WIDTH;
    public static final int SCREEN_HEIGHT = App.SCREEN_HEIGHT;

    public Sprite(float x, float y) {
        this.setXY(x, y);
    }

    public Sprite(Sprite other) {
        this.setXY(other.getX(), other.getY());
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
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
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

}
