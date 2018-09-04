import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class BusRow {
    private List<Bus> buses;
    private String direction;
    private float yPosition;
    private int offset;
    private float tilesApart;

    private final static int SCREEN_WIDTH = App.SCREEN_WIDTH;
    private final static int SCREEN_HEIGHT = App.SCREEN_HEIGHT;
    private final static int TILE_SIZE = App.TILE_SIZE;

    public BusRow(String direction, float yPosition, int offset,
                  float tilesApart) throws SlickException {
        this.direction = direction;
        this.yPosition = yPosition;
        this.offset = offset;
        this.tilesApart = tilesApart;
        this.generateBusesArray();


    }

    private void generateBusesArray() throws SlickException {
        float startX;
        int incrementAmount;

        float distanceBetweenBuses = this.tilesApart * App.TILE_SIZE;
        buses = new ArrayList<Bus>();

        /*Set first bus location and loop increment direction based on direction*/
        switch (direction) {
            case "right":
                startX = offset;
                incrementAmount = 1;
                break;
            case "left":
                startX = SCREEN_WIDTH - offset;
                incrementAmount = (-1);
            default:
                throw new SlickException("Direction invalid or not supported yet");
        }


        /*Populate array with buses at specified location with correct tile spacing*/
        while (this.isInBounds(startX)) {
            createBusAtXPosition(startX);
            startX += (incrementAmount * distanceBetweenBuses);
        }

    }

    /* Helper function to create a bus at x location
     * All other attributes assumed based on parameters given */
    private void createBusAtXPosition(float x) throws SlickException {
        Bus newBus = new Bus(x, this.yPosition, direction);
        if (direction == "right") {
            newBus.setInitialXPosition(0 - newBus.getImageWidth() / 2f);
        } else if (direction == "left") {
            newBus.setInitialXPosition(SCREEN_WIDTH + newBus.getImageWidth() / 2f);
        }

        buses.add(newBus);
    }

    private boolean isInBounds(float x) {
        return (x > 0 && x < SCREEN_WIDTH);
    }

    //Slick update function
    public void update(Input input, int delta) {
        for (Bus bus : this.buses) {
            bus.update(input, delta);
        }
    }

    //Slick render function
    public void render() {
        for (Bus bus : this.buses) {
            bus.render();
        }
    }
}

