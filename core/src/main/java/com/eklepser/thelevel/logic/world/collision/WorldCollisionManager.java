//package com.eklepser.thelevel.logic.world.collision;
//
//import com.badlogic.gdx.math.Rectangle;
//import com.eklepser.thelevel.logic.world.zone.WorldZone;
//
//import java.util.List;
//
//public class WorldCollisionManager {
//    private final WorldOld worldOld;
//    private final List<Entity> entities;
//    private final List<Rectangle> walls;
//    private final List<WorldZone> zones;
//
//    public WorldCollisionManager(WorldOld worldOld) {
//        this.worldOld = worldOld;
//        entities = worldOld.getEntities();
//        walls = worldOld.getWalls();
//        zones = worldOld.getZones();
//    }
//
//    public void updateWorld() {
//        for (Entity entity : entities) {
//            for (WorldZone zone : zones) {
//                if (entity.getRect().overlaps(zone.getRect())) {
//                    zone.onCollision(worldOld);
//                }
//            }
//            if (isWallCollision(entity.getTargetRect())) {
//                entity.resetTargetWorldPos();
//                entity.hit();
//            }
//        }
//    }
//
//    private boolean isWallCollision(Rectangle rectangle) {
//        for (Rectangle wall : walls) {
//            if (rectangle.overlaps(wall)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
