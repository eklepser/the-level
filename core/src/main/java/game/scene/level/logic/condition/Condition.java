package game.scene.level.logic.condition;

import game.common.logic.collision.CollisionContext;
import game.common.logic.entity.Entity;

public abstract class Condition {
    public abstract boolean check(Entity target, CollisionContext collisionContext);
}
