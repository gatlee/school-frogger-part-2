import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
    public final static String GRASS_IMAGE_SRC = "assets/grass.png";
    public final static String WATER_IMAGE_SRC = "assets/water.png";
    private Sprite player;
    private SpriteCollection topGrass;
    private SpriteCollection bottomGrass;
    private SpriteCollection water;
	private SpriteCollection buses;
	private SpriteCollection background;

	public World() throws SlickException {
	    //Creates map and player sprites

		//Utility class to generate tiles of sprites in grid fashion

		player = new Player(512, 720);

		buses = new SpriteCollection(BusRows.generateBusRows());
		background = new SpriteCollection(Background.generateBackgroundObjectsList());



	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
		player.update(input, delta);
		buses.update(input, delta);
		checkCollision(player, buses);
		checkCollision(player, background);
	}


	public static void checkCollision(Sprite a, Collidable b) {
		if (a.isIntersectingWith(b)) {
			a.onCollision(b);
			b.onCollision(a);
		}
	}

	public static void checkCollision(Sprite a, SpriteCollection b) {
		for (Sprite sprite : b.getSprites()) {
			checkCollision(a, sprite);
		}
	}

	public void render(Graphics g) {
		// Draw all of the sprites in the game
		background.render();
		buses.render();
		player.render();
	}
}
