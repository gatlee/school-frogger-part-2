import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.HashMap;
import java.util.Map;

public abstract class MovableSprite extends Sprite {
    public static final String RIGHT = "right";
    public static final String LEFT = "left";
    private float speed;
    private Map<Integer, Runnable> keyMap = new HashMap<Integer, Runnable>();

    /*****************CONSTRUCTORS*****************/
    public MovableSprite(String imageSrc, float x, float y) throws SlickException {
        super(x, y);
        this.setImage(new Image(imageSrc));
        this.setSpeed(0);

    }

    /*****************GETTERS AND SETTERS*****************/
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /*****************METHODS*****************/
    public void update(Input input, int delta) {
        this.updateBoundingBox();
    }

    //Move sprite based on speed
    public void move(String direction, int delta) {
        float difference = this.getSpeed() * delta;
        float nextY = this.getY();
        float nextX = this.getX();
        switch (direction) {
            case "down":
                nextY += difference;
                break;
            case "up":
                nextY -= difference;
                break;
            case LEFT:
                nextX -= difference;
                break;
            case RIGHT:
                nextX += difference;
                break;
        }

        //Checks if isAcceptableMovement is satisfied before moving to new position
        if (this.isAcceptableMovement(nextX, nextY)) {
            this.setXY(nextX, nextY);
        }

    }

    public boolean isAcceptableMovement(float x, float y) {
        return true;
    }

    //Move in direction based on speed if no delta specified
    public void move(String direction) {
        this.move(direction, 1);
    }

    public void contactSprite(MovableSprite other) {
        // Should be called when one sprite makes contact with another.
    }


}
