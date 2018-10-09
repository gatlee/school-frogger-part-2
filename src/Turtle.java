import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Turtle extends AutonomousSprite {

    public static final String IMAGE_SRC = "assets/turtles.png";
    public static final float SPEED = 0.085f;

    //Time in ms. Time adjusted to compensate for SINK_RISE_TIME.
    public static final int TIME_ABOVE_WATER = 7000;
    public static final int TIME_UNDERWATER = 2000;
    public static final int CYCLE_TIME = TIME_ABOVE_WATER + TIME_UNDERWATER;
    public static final int SINK_RISE_TIME = 1000;

    //This needs to exist since turtle is only made unrideable at END of animation
    public static final int TIME_TO_START_SINK_ANIMATION = TIME_ABOVE_WATER - SINK_RISE_TIME;

    private int timeElapsed = 0;
    private float renderDepth = 12;

    public Turtle(float x, float y, String direction) throws SlickException {
        super(IMAGE_SRC, x, y, direction, SPEED);
        this.addTag(Tags.RIDEABLE);
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
        this.timeElapsed += delta;
        this.updateVisibility();

    }

    @Override
    public void onCollision(Sprite other) {
        this.addChildSprite(other);
    }



    private void updateVisibility() {
        int cycleStage = timeElapsed % CYCLE_TIME;
        if (cycleStage >= 0 && cycleStage <= TIME_ABOVE_WATER) {
            this.makeVisible(cycleStage);
        }

        if (cycleStage > TIME_TO_START_SINK_ANIMATION) {
            this.makeInvisible(cycleStage);
        }

    }

    private void makeVisible(int cycleStage) {
        if(!this.hasTag(Tags.RIDEABLE)) {
            this.addTag(Tags.RIDEABLE);

        }

        int imageHeight = this.getImageHeight();
        if (cycleStage < SINK_RISE_TIME) {
            renderDepth = imageHeight * (SINK_RISE_TIME - cycleStage)/(float)SINK_RISE_TIME;
        } else {
            renderDepth = 0;
        }

    }

    private void makeInvisible(int cycleStage) {
        int imageHeight = this.getImageHeight();
        int timePastStart = cycleStage - TIME_TO_START_SINK_ANIMATION;
        if (timePastStart < SINK_RISE_TIME) {
            renderDepth = imageHeight * timePastStart/(float)SINK_RISE_TIME;
        } else {
            renderDepth = imageHeight;
            if(this.hasTag(Tags.RIDEABLE)) {
                this.removeTag(Tags.RIDEABLE);
            }
        }
    }

    @Override
    public void render() {
        if (this.hasTag(Tags.RIDEABLE)) {
            //Used to draw turtle with the offset when turtle is sinking/rising
            this.getImage().draw(this.getX() - this.getImageWidth()/2f,
                    (this.getY() - this.getImageHeight()/2f) + renderDepth,
                    this.getX() + this.getImageWidth()/2f,
                    (this.getY() + this.getImageHeight()/2f),
                    0,
                    0,
                    this.getImageWidth(),
                    this.getImageHeight()-renderDepth);

        }

    }
}
