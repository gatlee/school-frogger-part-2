import java.util.ArrayList;
import java.util.List;

public final class SpriteTiler {

    public static <SpriteType extends Sprite> List<Sprite> generateSpriteList(Class<SpriteType> typeClass,
                                                                              float topLeftSpriteXLocation,
                                                                              float topLeftSpriteYLocation,
                                                                              int numberOfSpritesRight,
                                                                              int numberOfSpritesDown) {

        List list = new ArrayList<>();
        int height = numberOfSpritesDown;
        int width = numberOfSpritesRight;

        int difference = App.TILE_SIZE;

        /* Iterate through width and height to generate sprites at the
        correct positions and add to list*/
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Sprite currentSprite = new Sprite(0, 0);
                SpriteType newSprite = typeClass.cast(currentSprite);

                newSprite.setX(topLeftSpriteXLocation + i * difference);
                newSprite.setY(topLeftSpriteYLocation + j * difference);
                list.add(newSprite);
            }
        }

        return list;
    }
}
