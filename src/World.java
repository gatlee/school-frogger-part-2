import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
    private Sprite player;
	private SpriteCollection buses;

	public World() throws SlickException {
		//Initialise Entities
		player = new Player(512, 720);
		buses = new SpriteCollection(EntityGenerator.getSpriteListFromFile("assets/levels/1.lvl"));



	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
		player.update(input, delta);
		buses.update(input, delta);
		checkCollision(player, buses);
	}

	public void render(Graphics g) {
		// Draw all of the sprites in the game
		buses.render();
		player.render();
	}

	//Checks collisions with others
	public static void checkCollision(Sprite a, Collidable b) {
		if (a.isIntersectingWith(b)) {
			//Activate their onCollisionEvents
			a.onCollision(b);
			b.onCollision(a);
		}
	}

	public static void checkCollision(Sprite a, SpriteCollection b) {
		//Iterate through sprite collection and check their collision
		for (Sprite sprite : b.getSprites()) {
			checkCollision(a, sprite);
		}
	}
}
