package com.eklepser.thelevel.logic.world.collision;

import com.eklepser.thelevel.logic.world.entity.Entity;

import java.util.List;

public record CollisionContext(List<Collidable> collidables, List<Entity> entities, boolean hittingWalls) {
}
