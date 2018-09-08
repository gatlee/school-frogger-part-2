import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class BusRow {

    private final static int SCREEN_WIDTH = App.SCREEN_WIDTH;
    private final static int SCREEN_HEIGHT = App.SCREEN_HEIGHT;
    private final static int TILE_SIZE = App.TILE_SIZE;

    public BusRow() {


    }

    public List<Bus> generateBusesArray(String direction, float yPosition,
                                        int offset, float tilesApart)
            throws SlickException {
        float startX;
        int incrementAmount;

        float distanceBetweenBuses = tilesApart * App.TILE_SIZE;
        List<Bus> buses = new ArrayList<Bus>();

        /*Set first bus location and loop increment direction based on direction*/
        switch (direction) {
            case "right":
                startX = offset;
                incrementAmount = 1;
                break;
            case "left":
                startX = SCREEN_WIDTH - offset;
                incrementAmount = (-1);
                break;
            default:
                throw new SlickException("Direction invalid or not supported yet");
        }


        /*Populate array with buses at specified location with correct tile spacing*/
        while (isInBounds(startX)) {
            buses.add(createBusAtXPosition(startX, yPosition, direction));
            startX += (incrementAmount * distanceBetweenBuses);
        }
        return buses;

    }

    /* Helper function to create a bus at x location
     * All other attributes assumed based on parameters given */
    private Bus createBusAtXPosition(float x, float yPosition, String direction) throws SlickException {
        Bus newBus = new Bus(x, yPosition, direction);
        if (direction.equals("right")) {
            newBus.setInitialXPosition(0 - newBus.getImageWidth() / 2f);
        } else if (direction.equals("left")) {
            newBus.setInitialXPosition(SCREEN_WIDTH + newBus.getImageWidth() / 2f);
        }

        return newBus;
    }

    private boolean isInBounds(float x) {
        return (x >= 0 && x <= SCREEN_WIDTH);
    }

}

