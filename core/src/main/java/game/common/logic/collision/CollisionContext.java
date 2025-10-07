package game.common.logic.collision;

import game.common.logic.entity.Entity;
import game.common.logic.collision.zone.Zone;

import java.util.List;

public record CollisionContext(int[][] collisionMap, List<Zone> zones, List<Entity> entities) { }
