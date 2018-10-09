import java.util.List;

import utilities.BoundingBox;

public interface Collidable {
    BoundingBox getBoundingBox();

    void onCollision(Sprite other);

    boolean isIntersectingWith(Collidable other);
}
