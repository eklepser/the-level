package game.scene.level.logic.condition;

import game.scene.common.logic.collision.CollisionContext;
import game.scene.common.logic.entity.Entity;

public final class FalseCondition extends Condition {

    @Override
    public boolean check(Entity target, CollisionContext collisionContext) {
        return false;
    }
}
