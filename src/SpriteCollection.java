import org.newdawn.slick.Input;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Sprite collection to hold sprites
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor with empty collection
     */
    public SpriteCollection() {
        //Initialise empty list
        sprites = new LinkedList<>();
    }

    /**
     * Get list of sprites
     * @return list of sprites
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Constructor with existing list of sprites
     * @param sprites
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Adds sprite to collection
     * @param sprite
     */
    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
    }

    /**
     * Append list of sprites
     * @param sprites
     */
    public void addAll(List<Sprite> sprites) {
        this.sprites.addAll(sprites);
    }

    /**
     * Render all sprites in collection
     */
    public void render() {
        //Render all sprites in collection
        for (Sprite sprite : this.sprites) {
            sprite.render();
        }
    }

    /**
     * Update all sprites in collection
     * @param input Slick input object
     * @param delta time in ms between frames
     */
    public void update(Input input, int delta) {
        for (Sprite sprite : this.sprites) {
            sprite.update(input, delta);
        }
    }

    /**
     * Empties all sprites in collection
     */
    public void clearSprites() {
        this.sprites.clear();

    }
}

