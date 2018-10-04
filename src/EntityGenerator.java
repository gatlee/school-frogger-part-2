import org.newdawn.slick.SlickException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class EntityGenerator {
    private final static int SCREEN_WIDTH = App.SCREEN_WIDTH;
    private final static int SCREEN_HEIGHT = App.SCREEN_HEIGHT;
    private final static int TILE_SIZE = App.TILE_SIZE;

    //Generate array of all buses and returns it
    public static List<Sprite> getSpriteListFromFile(String fileLocation) throws SlickException {
        List<Sprite> sprites = new ArrayList<Sprite>();

        try(Scanner scanner = new Scanner(new File(fileLocation))) {

            while (scanner.hasNextLine()) {
                sprites.add(generateSpriteFromString(scanner.nextLine()));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sprites;


    }

    private static Sprite generateSpriteFromString(String spriteSpecification) throws SlickException {
        return new Bus(10, 10, "right");


    }

}
