import org.newdawn.slick.SlickException;

import java.util.LinkedList;
import java.util.List;

public final class Background {
    //Map of the background. Each character represents a row type
    private static Character[] rowTileType = new Character[]{
            'w', 'w', 'w', 'w', 'w', 'w',
            'g',
            '0', '0', '0', '0', '0', 'g'
    };


    public static final int TILE_SIZE = App.TILE_SIZE;
    public static final int TOP_PADDING = 96;
    public static final int LEFT_PADDING = TILE_SIZE / 2;

    public static final int SCREEN_WIDTH = App.SCREEN_WIDTH;
    public static final int TILES_WIDE = SCREEN_WIDTH / TILE_SIZE + 1;

    public static List<Sprite> generateBackgroundObjectsList() throws SlickException {
        List<Sprite> output = new LinkedList<Sprite>();

        //Goes through row by row of rowTileType and make rows of that type
        for (int row = 0; row < rowTileType.length; row++) {
            for (int col = 0; col < TILES_WIDE; col++) {

                //Checks for which tile to draw with
                if (rowTileType[row].equals('w')) {
                    Water newTile = new Water(getXCoordinate(col), getYCoordinate(row));
                    output.add(newTile);
                } else if (rowTileType[row].equals('g')) {
                    Grass newTile = new Grass(getXCoordinate(col), getYCoordinate(row));
                    output.add(newTile);
                }

            }

        }
        return output;


    }

    /* Get actual tile coordinate for drawing from tile coordinate
    (where top left tile is 0,0 and next tile over is 0,1) */
    private static float getXCoordinate(int x) {
        return TILE_SIZE * x + LEFT_PADDING;

    }

    private static float getYCoordinate(int y) {
        return TILE_SIZE * y + TOP_PADDING;

    }


}
