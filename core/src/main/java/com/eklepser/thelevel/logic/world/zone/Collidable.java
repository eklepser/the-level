package com.eklepser.thelevel.logic.world.zone;

import com.eklepser.thelevel.logic.world.level.Entity;

public interface Collidable {
   void onCollision(Entity entity);
}
