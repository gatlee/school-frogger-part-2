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
	private BusRows busRows;

	public World() throws SlickException {
	    //Creates map and player sprites

		//Utility class to generate tiles of sprites in grid fashion
        SpriteTiler tiler = new SpriteTiler();

		player = new Player(512, 720);

        topGrass = new SpriteCollection(
                tiler.generateSpriteList(new Grass(), 24, 384, 22,
                        1));
        bottomGrass = new SpriteCollection(
                tiler.generateSpriteList(new Grass(),
				24, 672, 22,
                        1));
        water = new SpriteCollection(
                tiler.generateSpriteList(new Water(),
				24, 96, 22,
                        6));

		busRows = new BusRows();



	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
		player.update(input, delta);
		busRows.update(input, delta);
	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the game
		topGrass.render();
		bottomGrass.render();
		water.render();
		player.render();
		busRows.render();
	}
}
