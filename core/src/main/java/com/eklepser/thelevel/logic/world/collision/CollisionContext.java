package com.eklepser.thelevel.logic.world.collision;

import com.eklepser.thelevel.logic.world.level.Entity;
import com.eklepser.thelevel.logic.world.zone.Collidable;

import java.util.List;

public record CollisionContext(List<Collidable> collidables, List<Entity> entities) {
}
