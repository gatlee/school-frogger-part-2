import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

import java.util.ArrayList;
import java.util.List;


public abstract class Sprite implements Collidable {
    public static final String RIGHT = "right";
    public static final String LEFT = "left";
    private float x;
    private float y;
    private Image image;
    private static final int SCREEN_WIDTH = App.SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT = App.SCREEN_HEIGHT;
    private ArrayList<Tags> tags = new ArrayList<>();

    private BoundingBox boundingBox;
    private float speed;

    /*****************CONSTRUCTORS*****************/
    public Sprite(float x, float y, String imageSrc) throws SlickException {
        this.setImage(new Image(imageSrc));
        this.boundingBox = new BoundingBox(x, y, this.getImageWidth(), this.getImageHeight());
        this.setXY(x, y);
        this.updateBoundingBox();
    }

    /*****************GETTERS AND SETTERS*****************/
    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        this.boundingBox.setX(x);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        this.boundingBox.setY(y);
    }

    public void setXY(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public int getImageWidth() {
        return this.image.getWidth();
    }

    public int getImageHeight() {
        return this.image.getHeight();
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }


    /*****************GETTERS AND SETTERS*****************/
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /*****************REGULAR METHODS*****************/
    public void update(Input input, int delta) {
        this.updateBoundingBox();
    }

    public void render() {
        image.drawCentered(this.getX(), this.getY());

    }

    //Calculates if sprite image exceeds limits of screen
    public boolean isOffScreen() {
        float x = this.getX();
        float y = this.getY();
        int widthBuffer = this.getImageWidth() / 2;
        int heightBuffer = this.getImageHeight() / 2;
        return (x + widthBuffer < 0 || x - widthBuffer > SCREEN_WIDTH ||
                y + heightBuffer < 0 || y - heightBuffer > SCREEN_HEIGHT);

    }

    //Updates Location of bounding box
    public void updateBoundingBox() {
        this.boundingBox.setX(this.getX());
        this.boundingBox.setY(this.getY());
    }

    public void onCollision(Sprite other) {
        //Do nothing on collision
    }

    //Checks if intersecting with other
    public boolean isIntersectingWith(Collidable other) {
        BoundingBox otherBoundingBox = other.getBoundingBox();
        return (getBoundingBox().intersects(otherBoundingBox));

    }

    public void addTag(Tags newTag) {
        this.tags.add(newTag);
    }
    public void addTag(List<Tags> newTags) {
        this.tags.addAll(newTags);
    }

    public boolean hasTag(Tags tag) {
        return this.tags.contains(tag);
    }

    /*****************METHODS*****************/

    //Move sprite based on speed
    public void move(String direction, float delta) {
        float difference = this.getSpeed() * delta;
        float nextY = this.getY();
        float nextX = this.getX();
        switch (direction) {
            case "down":
                nextY += difference;
                break;
            case "up":
                nextY -= difference;
                break;
            case LEFT:
                nextX -= difference;
                break;
            case RIGHT:
                nextX += difference;
                break;
        }

        //Checks if isAcceptableMovement is satisfied before moving to new position
        if (this.isAcceptableMovement(nextX, nextY)) {
            this.setXY(nextX, nextY);
        }

    }

    public boolean isAcceptableMovement(float x, float y) {
        return true;
    }

    //Move in direction based on speed if no delta specified
    public void move(String direction) {
        this.move(direction, 1);
    }

}
