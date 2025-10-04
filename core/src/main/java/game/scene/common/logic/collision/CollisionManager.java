package game.scene.common.logic.collision;

import game.scene.common.rendering.tile.TileMap;
import game.scene.common.logic.collision.zone.Zone;
import game.scene.common.logic.entity.Entity;

import java.util.List;

public class CollisionManager {
    private final int[][] collisionMap;
    private final List<Zone> zones;
    private final List<Entity> entities;
    private final boolean hittingWalls = true;

    public CollisionManager(TileMap map, List<Zone> zones, List<Entity> entities) {
        collisionMap = map.collision;
        this.zones = zones;
        this.entities = entities;
    }

    public void update() {
        for (Entity entity : entities) {
            wallsUpdate(entity);
            zonesUpdate(entity);
        }
    }

    private void wallsUpdate(Entity entity) {
        int targetX = (int) entity.getTargetWorldPos().x;
        int targetY = (int) entity.getTargetWorldPos().y;

        if (collisionMap[targetY][targetX] == 1) {
            entity.resetTargetWorldPos();
            if (hittingWalls) entity.hit();
        }
    }

    private void zonesUpdate(Entity entity) {
        int x = (int) entity.getWorldPos().x;
        int y = (int) entity.getWorldPos().y;

        for (Zone zone : zones) {
            if (x == zone.getX() && y == zone.getY()) {
                zone.onCollision(entity);
            }
        }
    }
}
