import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.HashMap;
import java.util.Map;
public class Sprite {
    private float x;
    private float y;
    private Image image;

    public Sprite(String imageSrc, float x, float y) throws SlickException {
        this.setXY(x, y);
        this.image = new Image(imageSrc);
    }
    /*****************GETTERS AND SETTERS*****************/
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

    public float getLeft() {
        return (this.x - this.image.getWidth()/2);
    }
    public float getTop() {
        return (this.y - this.image.getHeight()/2);
    }


    //Sprite is non-moving so no need to update
    public void update(Input input, int delta) {
    }

    public void render() {
        image.draw(this.getLeft(), this.getTop());

    }
}
