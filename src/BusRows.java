import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public final class BusRows {
    private final static int SCREEN_WIDTH = App.SCREEN_WIDTH;
    private final static int SCREEN_HEIGHT = App.SCREEN_HEIGHT;
    private final static int TILE_SIZE = App.TILE_SIZE;

    //Generate array of all buses and returns it
    public static List<Sprite> generateBusRows() throws SlickException {
        List<Sprite> allBuses = new ArrayList<Sprite>();

        //Hardcoded for purposes of project 1
        allBuses.addAll(generateBusRow("right", 432, 48, 6.5f));
        allBuses.addAll(generateBusRow("left", 480, 0, 5f));
        allBuses.addAll(generateBusRow("right", 528, 64, 12f));
        allBuses.addAll(generateBusRow("left", 576, 128, 5f));
        allBuses.addAll(generateBusRow("right", 624, 250, 6.5f));

        return allBuses;

    }

    private static List<Bus> generateBusRow(String direction, float yPosition,
                                            int offset, float tilesApart) throws SlickException {
        BusRow busRow = new BusRow();
        return generateBusesArray(direction, yPosition, offset, tilesApart);


    }

    private static List<Bus> generateBusesArray(String direction, float yPosition,
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

    private static Bus createBusAtXPosition(float x, float yPosition, String direction) throws SlickException {
        Bus newBus = new Bus(x, yPosition, direction);
        if (direction.equals("right")) {
            newBus.setInitialXPosition(0 - newBus.getImageWidth() / 2f);
        } else if (direction.equals("left")) {
            newBus.setInitialXPosition(SCREEN_WIDTH + newBus.getImageWidth() / 2f);
        }

        return newBus;
    }

    private static boolean isInBounds(float x) {
        return (x >= 0 && x <= SCREEN_WIDTH);
    }

}
