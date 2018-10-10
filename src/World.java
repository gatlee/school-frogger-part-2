import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
    private Player player;
	private SpriteCollection buses;

	public World() throws SlickException {
		//Initialise Entities
		player = new Player(512, 720);
		buses = new SpriteCollection(EntityGenerator.getSpriteListFromFile("assets/levels/1.lvl"));
		buses.addAll(EntityGenerator.generateHoles());

	}
	
	public void update(Input input, int delta) throws SlickException {
		// Update all of the sprites in the game
		player.update(input, delta);
		buses.update(input, delta);
		checkCollision(player, buses);
		handleInputs(input, delta);
		checkHoles();
	}

	public void render(Graphics g) {
		// Draw all of the sprites in the game
		buses.render();
		player.render();
	}

	//Checks collisions with others
	private static void checkCollision(Sprite a, Sprite b) {
		if (a.isIntersectingWith(b)) {
			//Activate their onCollisionEvents
			a.onCollision(b);
			b.onCollision(a);
		}
	}

	private static void checkCollision(Sprite a, SpriteCollection b) {
		//Iterate through sprite collection and check their collision
		for (Sprite sprite : b.getSprites()) {
			checkCollision(a, sprite);
		}
	}

	private void checkHoles() {
		//Check if all holes are filled
		if (this.buses.getSprites().stream()
				.filter(sprite -> sprite instanceof Hole)
				.allMatch(s -> ((Hole) s).isFilled())) {
		    App.exit();
		}

	}

	private void handleInputs(Input input, int delta) throws SlickException {
	    String direction = "none";
		if (input.isKeyPressed(Input.KEY_DOWN)) {
            direction = Sprite.DOWN;
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			direction = Sprite.UP;
		} else if (input.isKeyPressed(Input.KEY_LEFT)) {
			direction = Sprite.LEFT;
		} else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            direction = Sprite.RIGHT;
		}

		if (!direction.equals("none")) {
			Player testPlayer = new Player(player);
			testPlayer.move(direction);
			boolean validMovement = true;
			for (Sprite sprite : buses.getSprites()) {
			    boolean isSolid = sprite.hasTag(Tags.SOLID);
				if (testPlayer.isIntersectingWith(sprite) && isSolid) {
					validMovement = false;

				}
			}

			if (validMovement) {
				player.move(direction);

			}
		}


	}
}
