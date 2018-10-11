import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.List;
import java.util.Random;

public class LifePowerUp extends AutonomousSprite{
    private static final String IMG_SRC = "assets/extralife.png";
    private static final int SPEED = App.TILE_SIZE;
    private static final String DEFAULT_DIRECTION = Sprite.RIGHT;
    private static final int TIME_BETWEEN_MOVES = 2000;
    private static final int TIME_TO_DISAPPEAR = 17000;
    private static final int MIN_TIME_TILL_SPAWN = 25000;
    private static final int MAX_TIME_TILL_SPAWN = 35000;

    private static final float INITIAL_RELATIVE_X_POS = App.TILE_SIZE/2;
    private static final float INITIAL_RELATIVE_Y_POS = App.TILE_SIZE/2;
    private int timeInCurrentPosition = 0;
    private int timeAtWhichToSpawn = -1;
    private int timeAtWhichToDisappear;
    private int totalTimeElapsed = 0;

    private Log parentLog = null;
    private boolean isConsumed;
    private List<Sprite> listWithLogs;
    private float relativeXPos;
    private float relativeYPos;
    private Random rand = new Random();

    public LifePowerUp(List<Sprite> listWithLogs) throws SlickException {
        super(IMG_SRC, 0, 0, DEFAULT_DIRECTION, SPEED);
        this.isConsumed = true;
        this.listWithLogs = listWithLogs;
        this.relativeXPos = INITIAL_RELATIVE_X_POS;
        this.relativeYPos = INITIAL_RELATIVE_Y_POS;
        timeAtWhichToSpawn = getRandomSpawnTime();
    }

    @Override
    public void update(Input input, int delta) {
        this.updateBoundingBox();
        this.updateAbsoluteXYPositions();
        this.updateTimedEvents(delta);
    }

    @Override
    public void render() {
        if (!isConsumed) {
            super.render();
        }
    }

    @Override
    public void onCollision(Sprite other) {
        if (other instanceof Player && !isConsumed) {
            detachFromParentLog();
        }
    }

    private void updateTimedEvents(int delta) {
        //Spawn on random log after correct amount of time elapsed and set new time to spawn
        if (parentLog == null && timeAtWhichToSpawn < totalTimeElapsed) {
            Log[] logs = this.listWithLogs.stream().filter(s -> s instanceof Log).toArray(Log[]::new);
            Log randomLog = logs[rand.nextInt(logs.length)];
            appearOnLog(randomLog);
            timeAtWhichToSpawn = getRandomSpawnTime();
            timeAtWhichToDisappear = totalTimeElapsed + TIME_TO_DISAPPEAR;

        }

        //Despawn after enough time has passed
        if (parentLog != null && timeAtWhichToDisappear < totalTimeElapsed) {
            detachFromParentLog();
        }

        //Move left to right across parent log
        if (timeInCurrentPosition > TIME_BETWEEN_MOVES && parentLog != null) {
            timeInCurrentPosition = timeInCurrentPosition - TIME_BETWEEN_MOVES;
            moveAcrossParent();
        }

        //Finally increment time trackers by delta
        timeInCurrentPosition += delta;
        totalTimeElapsed += delta;
    }

    private int getRandomSpawnTime() {
        return totalTimeElapsed + rand.nextInt(MAX_TIME_TILL_SPAWN - MIN_TIME_TILL_SPAWN) + MIN_TIME_TILL_SPAWN;
    }


    public void appearOnLog(Log parentLog) {
        isConsumed = false;
        /*
        float startXLocation = parentLog.getX() - (parentLog.getImageWidth()/2f)
                + App.TILE_SIZE/2f;
        float startYLocation = parentLog.getY();
        this.setXY(startXLocation, startYLocation);
        */
        parentLog.addChildSprite(this);
        this.parentLog = parentLog;


    }

    private void detachFromParentLog() {
        parentLog.clearChild(this);
        isConsumed = true;
        parentLog = null;

    }

    public boolean isConsumed() {
        return isConsumed;
    }

    private void updateAbsoluteXYPositions() {
        if (this.parentLog != null) {
            float absoluteXPos = parentLog.getX() - (parentLog.getImageWidth()/2f) + relativeXPos;
            float absoluteYPos = parentLog.getY() - (parentLog.getImageHeight()/2f) + relativeYPos;
            this.setXY(absoluteXPos, absoluteYPos);
        }

    }
    private void moveAcrossParent() {
        this.moveRelativelyOneTile(this.getMovementDirection());
        if (!this.isIntersectingWith(parentLog)) {
            toggleMovementDirection();
            this.moveRelativelyOneTile(this.getMovementDirection());
            this.moveRelativelyOneTile(this.getMovementDirection());
        }
    }

    private void moveRelativelyOneTile(String movementDirection) {
        switch (movementDirection) {
            case Sprite.RIGHT:
                this.relativeXPos += App.TILE_SIZE;
                break;
            case Sprite.LEFT:
                this.relativeXPos -= App.TILE_SIZE;
                break;
        }
        this.updateAbsoluteXYPositions();

    }


}
