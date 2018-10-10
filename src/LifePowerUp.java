import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.List;

public class LifePowerUp extends AutonomousSprite{
    private static final String IMG_SRC = "assets/extralife.png";
    private static final int SPEED = App.TILE_SIZE;
    private static final String DEFAULT_DIRECTION = Sprite.RIGHT;
    private static final int TIME_BETWEEN_MOVES = 2000;
    private int timeInCurrentPosition = 0;
    private int totalTimeElapsed = 0;

    private Log parentLog = null;
    private boolean isConsumed;
    private List<Sprite> listWithLogs;

    public LifePowerUp(List<Sprite> listWithLogs) throws SlickException {
        super(IMG_SRC, 0, 0, "right", SPEED);
        this.isConsumed = false;
        this.listWithLogs = listWithLogs;
    }

    @Override
    public void update(Input input, int delta) {
        if (parentLog == null) {
            setParentLog((Log) this.listWithLogs.stream().filter(s -> s instanceof Log).toArray()[0]);
        }
        this.parentLog.addChildSprite(this);
        if (timeInCurrentPosition > TIME_BETWEEN_MOVES) {
            timeInCurrentPosition = timeInCurrentPosition - TIME_BETWEEN_MOVES;
            moveAcrossParent();
        }

        timeInCurrentPosition += delta;
        totalTimeElapsed += delta;
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
            parentLog.clearChild(this);
            isConsumed = true;
            parentLog = null;
        }
    }

    public void setParentLog(Log parentLog) {
        float startXLocation = parentLog.getX() - (parentLog.getImageWidth()/2f)
                + App.TILE_SIZE/2f;
        float startYLocation = parentLog.getY();
        this.setXY(startXLocation, startYLocation);
        parentLog.addChildSprite(this);
        this.parentLog = parentLog;


    }

    private void moveAcrossParent() {
        this.move(this.getMovementDirection());
        if (!this.isIntersectingWith(parentLog)) {
            toggleMovementDirection();
            this.move(this.getMovementDirection());
            this.move(this.getMovementDirection());
        }
    }

    private void toggleMovementDirection() {
        if (this.getMovementDirection().equals(Sprite.RIGHT)) {
            this.setMovementDirection(Sprite.LEFT);
        } else if (this.getMovementDirection().equals(Sprite.LEFT)) {
            this.setMovementDirection(Sprite.RIGHT);
        }
    }


}
