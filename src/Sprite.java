import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

import java.util.ArrayList;
import java.util.List;

/**
 *Abstract Sprite class for slick package.
 *Most basic unit within game. Basis for all visualised things
 */
public abstract class Sprite implements Collidable {
    /*Constant for Left*/
    public static final String RIGHT = "right";
    /*Constant for Right*/
    public static final String LEFT = "left";
    public static final String DOWN = "down";
    public static final String UP = "up";

    private float x;
    private float y;
    private Image image;
    private static final int SCREEN_WIDTH = App.SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT = App.SCREEN_HEIGHT;
    private ArrayList<Tags> tags = new ArrayList<>();

    private BoundingBox boundingBox;
    private float speed;

    /*****************CONSTRUCTORS*****************/

    /**
     * Constructs a sprite object in specified location
     * @param x x position to draw on screen initially
     * @param y y position to draw on screen initially
     * @param imageSrc location of image relative to path project
     */
    public Sprite(float x, float y, String imageSrc) throws SlickException {
        this.setImage(new Image(imageSrc));
        this.boundingBox = new BoundingBox(x, y, this.getImageWidth(), this.getImageHeight());
        this.setXY(x, y);
        this.updateBoundingBox();
    }

    /*****************GETTERS AND SETTERS*****************/

    /**
     * Sets image to render as specified image
     * @param image Slick Image to use as sprites image.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Gets the slick image associated with Sprite
     * @return Slick Image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Returns current X position of sprite
     * @return current X position of sprite
     */
    public float getX() {
        return x;
    }

    /**
     * Sets X Position of sprite
     * @param x new x position (in float) to set position to
     */
    public void setX(float x) {
        this.x = x;
        this.boundingBox.setX(x);
    }

    /**
     * Gets current Y position of sprite
     * @return current Y position of sprite
     */
    public float getY() {
        return y;
    }

    /**
     * Sets Y Position of sprite.
     * @param y new y position (float) to set position to.
     */
    public void setY(float y) {
        this.y = y;
        this.boundingBox.setY(y);
    }

    /**
     * Sets both x and y position simultaneously.
     * @param x new x position (in float) to set position to
     * @param y new y position (in float) to set position to
     */
    public void setXY(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Gets width of image
     * @return image width in int
     */
    public int getImageWidth() {
        return this.image.getWidth();
    }

    /**
     * Gets height of image
     * @return image height in int
     */
    public int getImageHeight() {
        return this.image.getHeight();
    }

    /**
     * Gets BoundingBox
     * @return associated BoundingBox
     */
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }


    /**
     * Gets speed at which sprite moves
     * @return speed in float
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets speed at which sprite moves
     * @param speed new speed to set sprite to
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /*****************REGULAR METHODS*****************/
    /**
     * Updates non visual aspects of sprite
     * @param input User input slick object
     * @param delta time in ms
     */
    public void update(Input input, int delta) {
        this.updateBoundingBox();
    }

    /**
     * Visually draws sprite in correct position
     */
    public void render() {
        image.drawCentered(this.getX(), this.getY());

    }

    /**
     * Calculates if sprite image exceeds limits of screen as defined by App
     * @return true if off screen. false if on screen
     */
    public boolean isOffScreen() {
        float x = this.getX();
        float y = this.getY();
        int widthBuffer = this.getImageWidth() / 2;
        int heightBuffer = this.getImageHeight() / 2;
        return (x + widthBuffer < 0 || x - widthBuffer > SCREEN_WIDTH ||
                y + heightBuffer < 0 || y - heightBuffer > SCREEN_HEIGHT);

    }

    /**
     *Updates Location of bounding box to match visual position
     */
    public void updateBoundingBox() {
        this.boundingBox.setX(this.getX());
        this.boundingBox.setY(this.getY());
    }

    /**
     * Method to call on collision with other sprite
     * @param other Reference to other sprite
     */
    public void onCollision(Sprite other) {
        //Do nothing on collision
    }

    /**
     * Checks if intersecting with other
     * @param other Reference to other sprite
     * @return true if intersecting. false if not
     */
    public boolean isIntersectingWith(Collidable other) {
        BoundingBox otherBoundingBox = other.getBoundingBox();
        return (getBoundingBox().intersects(otherBoundingBox));

    }

    /**
     * Add a tag specifying the properties of the sprite.
     * Won't add new tags if it already exists in tag list
     * Credits to Eleanor McMurty for idea
     * @param newTag Tags Enum
     */
    public void addTag(Tags newTag) {
        if (!this.hasTag(newTag)) {
            this.tags.add(newTag);
        }
    }

    /**
     * Add multiple tags specifying the properties of the sprite.
     * Won't add new tags if it already exists in tag list
     * @param newTags List of Tags
     */
    public void addTag(List<Tags> newTags) {
        for (Tags tag : newTags) {
            this.addTag(tag);
        }
    }

    /**
     * Remove tag from tag list
     * @param tag Tags Enum
     */
    public void removeTag(Tags tag) {
        if (this.hasTag(tag)){
            this.tags.remove(tag);
        }
    }

    /**
     * Returns true if has Tag
     * @param tag Tags enum
     * @return Boolean if it has a tag
     */
    public boolean hasTag(Tags tag) {
        return this.tags.contains(tag);
    }

    /**
     * Moves sprite in direction based on delta by specified amount
     * @param direction String specifying direction
     * @param delta Time elapsed between frame refreshes in float
     * @param amount pixels per unit of time to move
     */
    public void move(String direction, float delta, float amount) {
        float difference = amount * delta;
        float nextY = this.getY();
        float nextX = this.getX();
        switch (direction) {
            case DOWN:
                nextY += difference;
                break;
            case UP:
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

    /**
     * Moves sprite in direction based on delta by set speed.
     * @param direction String specifying direction
     * @param delta Time elapsed between frame refreshes in float
     */
    public void move(String direction, float delta) {
        this.move(direction, delta, this.getSpeed());
    }

    /**
     * Returns true if new x or y position is a valid and acceptable movement
     * @param x new x position on screen
     * @param y new y position on screen
     * @return boolean value of whether it is acceptable
     */
    public boolean isAcceptableMovement(float x, float y) {
        return true;
    }

    /**
     * Move in direction based on speed once
     * @param direction String specifying direction
     */
    public void move(String direction) {
        this.move(direction, 1);
    }

}
