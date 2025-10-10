package game.scene.common.logic.collision;

import game.scene.common.logic.entity.Entity;

public interface Collidable {
   void onCollision(Entity entity);
}
