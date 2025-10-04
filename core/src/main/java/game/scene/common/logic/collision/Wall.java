package game.scene.common.logic.collision;

import com.badlogic.gdx.math.Rectangle;
import game.scene.common.logic.entity.Entity;

public class Wall implements Collidable {
    private final Rectangle rect;

    public Wall(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void onCollision(Entity entity) {
        entity.resetTargetWorldPos();
    }

    public Rectangle getRect() {
        return rect;
    }
}
