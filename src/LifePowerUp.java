import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.List;

public class LifePowerUp extends AutonomousSprite{
    private static final String IMG_SRC = "assets/extralife.png";
    private static final int SPEED = App.TILE_SIZE;
    private static final String DEFAULT_DIRECTION = Sprite.RIGHT;
    private static final int TIME_BETWEEN_MOVES = 2000;
    private static final float INITIAL_RELATIVE_X_POS = App.TILE_SIZE/2;
    private static final float INITIAL_RELATIVE_Y_POS = App.TILE_SIZE/2;
    private int timeInCurrentPosition = 0;
    private int totalTimeElapsed = 0;

    private Log parentLog = null;
    private boolean isConsumed;
    private List<Sprite> listWithLogs;
    private float relativeXPos;
    private float relativeYPos;

    public LifePowerUp(List<Sprite> listWithLogs) throws SlickException {
        super(IMG_SRC, 0, 0, DEFAULT_DIRECTION, SPEED);
        this.isConsumed = false;
        this.listWithLogs = listWithLogs;
        this.relativeXPos = INITIAL_RELATIVE_X_POS;
        this.relativeYPos = INITIAL_RELATIVE_Y_POS;
    }

    @Override
    public void update(Input input, int delta) {
        this.updateBoundingBox();
        this.updateAbsoluteXYPositions();
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

    private void updateAbsoluteXYPositions() {
        if (this.parentLog != null) {
            float absoluteXPos = parentLog.getX() - (parentLog.getImageWidth()/2f) + relativeXPos;
            float absoluteYPos = parentLog.getY() - (parentLog.getImageHeight()/2f) + relativeYPos;
            this.setXY(absoluteXPos, absoluteYPos);
        }

    }
    private void moveAcrossParent() {
        this.moveRelatively(this.getMovementDirection());
        if (!this.isIntersectingWith(parentLog)) {
            toggleMovementDirection();
            this.moveRelatively(this.getMovementDirection());
            this.moveRelatively(this.getMovementDirection());
        }
    }

    private void moveRelatively(String movementDirection) {
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

    private void toggleMovementDirection() {
        if (this.getMovementDirection().equals(Sprite.RIGHT)) {
            this.setMovementDirection(Sprite.LEFT);
        } else if (this.getMovementDirection().equals(Sprite.LEFT)) {
            this.setMovementDirection(Sprite.RIGHT);
        }
    }


}
