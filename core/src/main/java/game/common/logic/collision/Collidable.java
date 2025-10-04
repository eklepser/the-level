package game.common.logic.collision;

import game.common.logic.entity.Entity;

public interface Collidable {
   void onCollision(Entity entity);
}
