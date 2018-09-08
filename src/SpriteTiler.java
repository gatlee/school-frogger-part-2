import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.LinkedList;
import java.util.List;

public class SpriteTiler {
    public SpriteTiler() {

    }

    public List<Sprite> generateSpriteList(Sprite exampleSprite,
                                           float topLeftSpriteXLocation,
                                           float topLeftSpriteYLocation,
                                           int numberOfSpritesRight,
                                           int numberOfSpritesDown) {

        LinkedList list = new LinkedList<>();
        int height = numberOfSpritesDown;
        int width = numberOfSpritesRight;

        int difference = App.TILE_SIZE;

        /* Iterate through width and height to generate sprites at the
        correct positions and add to list*/
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Sprite currentSprite = new Sprite(exampleSprite);
                currentSprite.setX(topLeftSpriteXLocation + i * difference);
                currentSprite.setY(topLeftSpriteYLocation + j * difference);
                list.add(currentSprite);
            }
        }

        return list;
    }
}
