package game.scene.common.logic.collision;

import game.scene.common.logic.entity.Entity;

import java.util.List;

public record CollisionContext(List<Collidable> collidables, List<Entity> entities, boolean hittingWalls) {
}
