package game.scene.common.logic.collision;

import game.scene.common.logic.collision.zone.Zone;
import game.scene.common.logic.entity.Entity;

import java.util.List;

public record CollisionContext(int[][] collisionMap, List<Zone> zones, List<Entity> entities) { }
