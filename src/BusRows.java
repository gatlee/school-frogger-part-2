import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public final class BusRows {

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
        return busRow.generateBusesArray(direction, yPosition, offset, tilesApart);


    }


}
