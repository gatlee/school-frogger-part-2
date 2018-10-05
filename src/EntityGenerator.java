import org.newdawn.slick.SlickException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class EntityGenerator {
    private final static int SCREEN_WIDTH = App.SCREEN_WIDTH;
    private final static int SCREEN_HEIGHT = App.SCREEN_HEIGHT;
    private final static int TILE_SIZE = App.TILE_SIZE;
    private final static int LVL_SPRITE_TYPE_INDEX = 0;
    private final static int LVL_X_POS_INDEX = 1;
    private final static int LVL_Y_POS_INDEX = 2;
    private final static int LVL_DIRECTION_INDEX = 3;

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

    private static String parseDirection(String direction) throws SlickException {
        if (direction.equals("true")) {
            return "right";

        } else if (direction.equals("false")) {
            return "left";

        } else {
            throw new SlickException("Parsing Error. Unexpected direction");
        }

    }
    private static Sprite generateSpriteFromString(String spriteSpecification) throws SlickException {
        String[] spriteParameters = spriteSpecification.split(",");
        int x = Integer.parseInt(spriteParameters[LVL_X_POS_INDEX]);
        int y = Integer.parseInt(spriteParameters[LVL_Y_POS_INDEX]);
        String direction = null;

        if (spriteParameters.length == 4) {
            direction = parseDirection(spriteParameters[LVL_DIRECTION_INDEX]);
        }

        switch (spriteParameters[LVL_SPRITE_TYPE_INDEX]) {
            case "log":
                break;
            case "longLog":
                break;
            case "water":
                return new Water(x, y);
            case "grass":
                return new Grass(x, y);
            case "tree":
                break;
            case "bus":
                return new Bus(x, y, direction);
            case "bulldozer":
                break;

        }

        //Default case for now. Should probably do something else once other classes implemented
        return new Grass(5000, 5000);


    }

}
