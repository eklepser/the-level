package com.eklepser.thelevel.logic.world.collision;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.collision.zone.Zone;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private final List<Wall> walls = new ArrayList<>();
    private final List<Zone> zones = new ArrayList<>();
    private final List<Entity> entities;
    private final boolean hittingWalls;

    public CollisionManager(CollisionSource source) {
        CollisionContext context = source.getCollisionContext();
        for (Collidable collidable : context.collidables()) {
            if (collidable instanceof Wall wall) walls.add(wall);
            if (collidable instanceof Zone zone) zones.add(zone);
        }
        entities = context.entities();
        hittingWalls = context.hittingWalls();
    }

    public void update() {
        for (Entity entity : entities) {
            wallsUpdate(entity);
            zonesUpdate(entity);
        }
    }

    private void wallsUpdate(Entity entity) {
        Rectangle entityRect = entity.getTargetRect();
        for (Wall wall : walls) {
            Rectangle wallRect = wall.getRect();
            if (entityRect.overlaps(wallRect)) {
                System.out.println("WALL");
                wall.onCollision(entity);
                if (hittingWalls) entity.hit();
            }
        }
    }

    private void zonesUpdate(Entity entity) {
        Rectangle entityRect = entity.getTargetRect();
        for (Zone zone : zones) {
            Rectangle zoneRect = zone.getRect();
            if (entityRect.overlaps(zoneRect)) {
                zone.onCollision(entity);
            }
        }
    }
}
