import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.HashMap;
import java.util.Map;

public abstract class MovableSprite extends Sprite {

    private float speed;
    private Map<Integer, Runnable> keyMap = new HashMap<Integer, Runnable>();

    public MovableSprite(String imageSrc, float x, float y) throws SlickException {
        super(x, y);
        this.setImage(new Image(imageSrc));
        this.setSpeed(0);
        this.initialiseKeyBindings();

    }

    /*****************GETTERS AND SETTERS*****************/
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /*****************METHODS*****************/
    //Initialise keyMap Map with bindings
    public void initialiseKeyBindings() {}

    public void addKeymap(int key, Runnable function) {
        this.keyMap.put(key, function);
    }

    //MovableSprite is static by default so update function is empty
    public void update(Input input, int delta) {
        //Goes through keymap and runs value function if key is pressed down
        for (int key : keyMap.keySet()) {
            if (input.isKeyPressed(key)) {
                keyMap.get(key).run();
            }
        }
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
            case "left":
                nextX -= difference;
                break;
            case "right":
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
