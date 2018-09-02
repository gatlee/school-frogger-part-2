import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends MovableSprite {
    Player(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
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

}
