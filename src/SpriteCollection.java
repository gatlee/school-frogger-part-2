import org.newdawn.slick.Input;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite> sprites;

    public SpriteCollection() {
        //Initialise empty list
        sprites = new LinkedList<>();
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public SpriteCollection(List<Sprite> sprites) {
        //Fill list with these sprites
        this.sprites = sprites;
    }

    public void addSprite(Sprite sprite) {
        //Add Sprite to collection
        this.sprites.add(sprite);
    }

    public void render() {
        //Render all sprites in collection
        for (Sprite sprite : this.sprites) {
            sprite.render();
        }
    }

    public void update(Input input, int delta) {
        //Update all sprites in collection
        for (Sprite sprite : this.sprites) {
            sprite.update(input, delta);
        }
    }
}

