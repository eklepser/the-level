package com.eklepser.thelevel.logic.world.collision;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.Zone;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.collision.zone.ZoneOld;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private final int[][] collisionMap;
    private final List<Zone> zones;
    private final List<Entity> entities;
    private final boolean hittingWalls = true;

    public CollisionManager(TileMap map, List<Entity> entities) {
        collisionMap = map.collision;
        zones = map.zones;
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
//        Rectangle entityRect = entity.getTargetRect();
//        for (ZoneOld zoneOld : zoneOlds) {
//            Rectangle zoneRect = zoneOld.getRect();
//            if (entityRect.overlaps(zoneRect)) {
//                zoneOld.onCollision(entity);
//            }
//        }
    }
}
