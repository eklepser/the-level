package game.common.logic.collision;

import game.common.logic.entity.Entity;

import java.util.List;

public record CollisionContext(List<Collidable> collidables, List<Entity> entities, boolean hittingWalls) {
}
