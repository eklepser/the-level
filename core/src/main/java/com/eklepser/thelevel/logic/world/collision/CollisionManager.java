package com.eklepser.thelevel.logic.world.collision;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.zone.Zone;

import java.util.List;

public class CollisionManager {
    private final List<Entity> entities;
    private final List<Rectangle> walls;
    private final List<Zone> zones;

    public CollisionManager(Level level) {
        entities = level.getEntities();
        walls = level.getWalls();
        zones = level.getZones();
    }

    public void update() {
        for (Entity entity : entities) {
            for (Zone zone : zones) {
                if (entity.getTargetRect().overlaps(zone.getRect())) {
                    zone.onPossibleCollision();
                    System.out.println("Possible collision!");
                }
                if (entity.getRect().overlaps(zone.getRect())) {
                    zone.onCollision();
                    System.out.println("Collision!");
                }
            }
            if (isWallCollision(entity.getTargetRect())) {
                entity.resetTargetWorldPos();
                entity.onPossibleCollision();
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
}
