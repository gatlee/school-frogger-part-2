import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
    private Sprite player;
	public World() throws SlickException {
		// Perform initialisation logic
		player = new Player("assets/frog.png", 0, 0);
	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
		player.update(input, delta);
	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the game
		player.render();
	}
}
