package com.eklepser.thelevel.logic.interaction.collision;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.World;
import com.eklepser.thelevel.logic.world.zone.WorldZone;

import java.util.List;

public class WorldCollisionManager {
    private final World world;
    private final List<Entity> entities;
    private final List<Rectangle> walls;
    private final List<WorldZone> zones;

    public WorldCollisionManager(World world) {
        this.world = world;
        entities = world.getEntities();
        walls = world.getWalls();
        zones = world.getZones();
    }

    public void updateWorld() {
        for (Entity entity : entities) {
            for (WorldZone zone : zones) {
                if (entity.getRect().overlaps(zone.getRect())) {
                    zone.onCollision(world);
                }
            }
            if (isWallCollision(entity.getTargetRect())) {
                entity.resetTargetWorldPos();
                entity.hit();
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
