import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BusRows {
    private List<BusRow> busRows;

    public BusRows() throws SlickException {

        busRows = new ArrayList<BusRow>();

        //Hard coded positions. Better to change this later on
        addBusRow("right", 432, 48, 6.5f);
        addBusRow("left", 480, 0, 5f);
        addBusRow("right", 528, 64, 12f);
        addBusRow("left", 576, 128, 5f);
        addBusRow("right", 624, 250, 6.5f);


    }

    //Add a bus row
    private void addBusRow(String direction, float yPosition,
                           int offset, float tilesApart) throws SlickException {

        busRows.add(new BusRow(direction, yPosition, offset, tilesApart));
    }

    //Render all the buses
    public void render() {
        for (BusRow busRow : busRows) {
            busRow.render();
        }

    }

    //Update all the buses
    public void update(Input input, int delta) {
        for (BusRow busRow : busRows) {
            busRow.update(input, delta);
        }
    }
}
