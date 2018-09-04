import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
    private Sprite player;
    private SpritesTiled topGrass;
	private SpritesTiled bottomGrass;
    private SpritesTiled water;
	private BusRow busRow1;

	public World() throws SlickException {
	    //Creates map and player sprites
		player = new Player("assets/frog.png", 512, 720);
		topGrass = new SpritesTiled("assets/grass.png",
				24, 384, 22,
				1 );
		bottomGrass = new SpritesTiled("assets/grass.png",
				24, 672, 22,
				1 );
		water = new SpritesTiled("assets/water.png",
				24, 96, 22,
				6 );

		busRow1 = new BusRow("right", 432, 48, 6.5f);
		

	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
		player.update(input, delta);
		busRow1.update(input, delta);
	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the game
		topGrass.render();
		bottomGrass.render();
		water.render();
		player.render();
		busRow1.render();
	}
}
