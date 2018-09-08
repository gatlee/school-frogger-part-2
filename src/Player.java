import org.lwjgl.Sys;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends MovableSprite {
    private static final String PLAYER_IMAGE_SRC = "assets/frog.png";
    private static final Integer SCREEN_WIDTH = App.SCREEN_WIDTH;
    private static final Integer SCREEN_HEIGHT = App.SCREEN_HEIGHT;

    Player(float x, float y) throws SlickException {

        super(PLAYER_IMAGE_SRC, x, y);
        //Set distance to move by
        this.setSpeed(App.TILE_SIZE);
    }


    //Player Keymap
    //TODO: Possibly move keybindings into World class for single source of truth

    public void initialiseKeyBindings() {
        this.addKeymap(Input.KEY_DOWN, () -> this.move("down"));
        this.addKeymap(Input.KEY_UP, () -> this.move("up"));
        this.addKeymap(Input.KEY_LEFT, () -> this.move("left"));
        this.addKeymap(Input.KEY_RIGHT, () -> this.move("right"));
    }


    public void update(Input input, int delta) {
        // How can this one method deal with different types of sprites?
        super.update(input, delta);
    }

    //Returns false if any part of object is out of bounds
    public boolean isAcceptableMovement(float x, float y) {
        return !(x - (this.getImageWidth() / 2f) < 0 ||
                x + (this.getImageWidth() / 2f) > SCREEN_WIDTH ||
                y - (this.getImageHeight() / 2f) < 0 ||
                y + (this.getImageWidth() / 2f) > SCREEN_HEIGHT);

    }

}
