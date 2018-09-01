import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
    private Sprite player;
    private SpritesTiled grass;
    private SpritesTiled water;

	public World() throws SlickException {
		player = new Player("assets/frog.png", 512, 720);
		grass = new SpritesTiled("assets/grass.png",
				24, 384, 22,
				6 );
		water = new SpritesTiled("assets/water.png",
				24, 96, 22,
				6 );
	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
		player.update(input, delta);
	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the game
		grass.render();
		water.render();
		player.render();
	}
}
