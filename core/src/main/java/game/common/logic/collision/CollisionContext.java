package game.common.logic.collision;

import game.common.logic.collision.zone.Zone;
import game.common.logic.entity.Entity;

import java.util.List;

public record CollisionContext(int[][] collisionMap, List<Zone> zones, List<Entity> entities) { }
