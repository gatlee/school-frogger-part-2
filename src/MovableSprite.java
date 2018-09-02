import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.HashMap;
import java.util.Map;

public class MovableSprite extends Sprite {

	private float speed;
	private Map<Integer, Runnable> keyMap = new HashMap<Integer, Runnable>();

	public MovableSprite(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
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
        for (int key: keyMap.keySet()) {
            if (input.isKeyPressed(key)) {
                keyMap.get(key).run();
			}
		}
	}

	//Move sprite based on speed
	public void move(String direction, int delta) {
		float difference = this.getSpeed() * delta;
		switch (direction) {
			case "down":
				this.setY(this.getY() + difference);
				break;
			case "up":
				this.setY(this.getY() - difference);
				break;
			case "left":
				this.setX(this.getX() - difference);
				break;
			case "right":
				this.setX(this.getX() + difference);
				break;
		}

	}

	//Move in direction based on speed if no delta specified
	public void move(String direction) {
		this.move(direction, 1);
	}
	public void contactSprite(MovableSprite other) {
		// Should be called when one sprite makes contact with another. 
	}




}
