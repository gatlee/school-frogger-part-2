import java.util.List;

import utilities.BoundingBox;

public interface Collidable {
    BoundingBox getBoundingBox();

    void onCollision(Collidable other);

    boolean isIntersectingWith(Collidable other);
}
