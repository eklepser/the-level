package com.eklepser.thelevel.logic.world.collision;

import com.eklepser.thelevel.logic.world.entity.Entity;

public interface Collidable {
   void onCollision(Entity entity);
}
