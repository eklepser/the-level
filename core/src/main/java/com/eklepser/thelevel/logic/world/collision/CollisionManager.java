package com.eklepser.thelevel.logic.world.collision;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.collision.zone.ZoneOld;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private final List<Wall> walls = new ArrayList<>();
    private final List<ZoneOld> zoneOlds = new ArrayList<>();
    private final List<Entity> entities;
    private final boolean hittingWalls;

    public CollisionManager(CollisionSource source) {
        CollisionContext context = source.getCollisionContext();
        for (Collidable collidable : context.collidables()) {
            if (collidable instanceof Wall wall) walls.add(wall);
            if (collidable instanceof ZoneOld zoneOld) zoneOlds.add(zoneOld);
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
        for (ZoneOld zoneOld : zoneOlds) {
            Rectangle zoneRect = zoneOld.getRect();
            if (entityRect.overlaps(zoneRect)) {
                zoneOld.onCollision(entity);
            }
        }
    }
}
