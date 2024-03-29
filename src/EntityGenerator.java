import org.newdawn.slick.SlickException;

import java.io.File;
import java.util.ArrayList;
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

    private final static int[] HOLES_X_POS = new int[]{120, 312, 504, 696, 888};
    private final static int HOLES_Y_POS = 48;

    /**
     * Generates a list of holes.
     * @return List of holes
     */
    public static List<Sprite> generateHoles() throws SlickException {
        List<Sprite> holes = new ArrayList<>();
        for (int i : HOLES_X_POS) {
            holes.add(new Hole(i, HOLES_Y_POS));

        }
        return holes;
    }


    /**
     * Generate array of all sprites and returns it
     * @param fileLocation String specifying location of .lvl file
     * @return List of sprites generated by file
     * @throws SlickException
     */
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

    /**
     * Parses directions found in .lvl files and generates the appropriate value
     * @param direction direction word as found in .lvl file
     * @return direction for usage in program
     */
    private static String parseDirection(String direction) throws SlickException {
        if (direction.equals("true")) {
            return Sprite.RIGHT;

        } else if (direction.equals("false")) {
            return Sprite.LEFT;

        } else {
            throw new SlickException("Parsing Error. Unexpected direction");
        }

    }

    /**
     * Generates sprite from single line of .lvl file
     * @param spriteSpecification Single line in .lvl file as string
     * @return Single Sprite that meets specification
     * @throws SlickException
     */
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
                return new Log(x, y, direction);
            case "longLog":
                return new LongLog(x, y, direction);
            case "water":
                return new Water(x, y);
            case "grass":
                return new Grass(x, y);
            case "tree":
                return new Tree(x, y);
            case "bus":
                return new Bus(x, y, direction);
            case "bulldozer":
                return new Bulldozer(x, y, direction);
            case "racecar":
                return new RaceCar(x, y, direction);
            case "bike":
                return new Bike(x, y, direction);
            case "turtle":
                return new Turtle(x, y, direction);


        }

        //Default case for now. Should probably do something else once other classes implemented
        return new Grass(5000, 5000);


    }

}
