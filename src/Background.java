import org.newdawn.slick.SlickException;

import java.util.LinkedList;
import java.util.List;

public final class Background {
    //Map of the background. Each character represents a row type
    //In future may be good idea to do a 2D Array for better control
    private static Character[] rowTileType = new Character[]{
            'w', 'w', 'w', 'w', 'w', 'w',
            'g',
            '0', '0', '0', '0', '0', 'g'
    };


    private static final int TILE_SIZE = App.TILE_SIZE;
    private static final int TOP_PADDING = 96;
    private static final int LEFT_PADDING = TILE_SIZE / 2;

    private static final int SCREEN_WIDTH = App.SCREEN_WIDTH;
    private static final int TILES_WIDE = SCREEN_WIDTH / TILE_SIZE + 1;

    //Returns a list of background tiles
    public static List<Sprite> generateBackgroundObjectsList() throws SlickException {

        List<Sprite> output = new LinkedList<>();

        //Goes through row by row of rowTileType and make rows of that type
        for (int row = 0; row < rowTileType.length; row++) {
            for (int col = 0; col < TILES_WIDE; col++) {

                //Checks for which tile to draw with
                if (rowTileType[row].equals('w')) {
                    Water newTile = new Water(getXRenderCoordinate(col), getYRenderCoordinate(row));
                    output.add(newTile);
                } else if (rowTileType[row].equals('g')) {
                    Grass newTile = new Grass(getXRenderCoordinate(col), getYRenderCoordinate(row));
                    output.add(newTile);
                }

            }

        }

        //Finally, return the list
        return output;


    }

    /* Get actual tile coordinate for drawing from tile coordinate
    (where top left tile is 0,0 and next tile over is 0,1) */
    private static float getXRenderCoordinate(int tileX) {
        return TILE_SIZE * tileX + LEFT_PADDING;

    }

    private static float getYRenderCoordinate(int tileY) {
        return TILE_SIZE * tileY + TOP_PADDING;

    }


}
