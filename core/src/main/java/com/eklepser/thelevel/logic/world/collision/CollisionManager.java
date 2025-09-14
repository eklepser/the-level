package com.eklepser.thelevel.logic.world.collision;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.zone.Zone;

import java.util.List;

public class CollisionManager {
    private final Level level;
    private final List<Entity> entities;
    private final List<Rectangle> walls;
    private final List<Zone> zones;

    public CollisionManager(Level level) {
        this.level = level;
        entities = level.getEntities();
        walls = level.getWalls();
        zones = level.getZones();
    }

    public void update() {
        for (Entity entity : entities) {
            if (this.isWallCollision(entity.getTargetRect())) {
                entity.resetTargetWorldPos();
                entity.onPossibleCollision();
            }
            if (isZoneCollision(entity.getRect())) {
                System.out.println("Entity reaction");
            }
        }
    }

    private boolean isWallCollision(Rectangle rectangle) {
        for (Rectangle wall : walls) {
            if (rectangle.overlaps(wall)) {
                return true;
            }
        }
        return false;
    }

    private boolean isZoneCollision(Rectangle rectangle) {
        for (Zone zone : zones) {
            if (rectangle.overlaps(zone.getRect())) {
                zone.onCollision();
                return true;
            }
        }
        return false;
    }
}
