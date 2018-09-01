import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpritesTiled {

    private String imageSrc;
    private Image image;
    private float topLeftSpriteXLocation;
    private float topLeftSpriteYLocation;
    private int numberOfSpritesRight;
    private int numberOfSpritesDown;
    private Sprite[][] sprites;

    /*Initialises sprite group*/
    public SpritesTiled(String imageSrc, float topLeftSpriteXLocation,
                        float topLeftSpriteYLocation, int numberOfSpritesRight,
                        int numberOfSpritesDown) throws SlickException {
        this.imageSrc = imageSrc;
        this.image = new Image(imageSrc);
        this.topLeftSpriteXLocation = topLeftSpriteXLocation;
        this.topLeftSpriteYLocation = topLeftSpriteYLocation;
        this.numberOfSpritesDown = numberOfSpritesDown;
        this.numberOfSpritesRight = numberOfSpritesRight;

        this.generateSpritesArray();

    }

    public void generateSpritesArray( ) throws SlickException{
        this.sprites = new Sprite[numberOfSpritesDown][numberOfSpritesRight];
        int difference = 48;
        int height = numberOfSpritesDown;
        int width = numberOfSpritesRight;

        for (int i = 0; i<width; i++) {
            for (int j = 0; j <height; j++) {
                this.sprites[j][i] = new Sprite(
                        this.imageSrc,
                        this.topLeftSpriteXLocation + i*difference,
                        this.topLeftSpriteYLocation + j*difference);

            }
        }


    }

    public void render() {
        int height = numberOfSpritesDown;
        int width = numberOfSpritesRight;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.sprites[j][i].render();
            }
        }
    }
}
