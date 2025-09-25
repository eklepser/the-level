//package com.eklepser.thelevel.logic.world.collision;
//
//import com.badlogic.gdx.math.Rectangle;
//import com.eklepser.thelevel.logic.decoder.execution.Executor;
//import com.eklepser.thelevel.logic.game.common.GameArea;
//import com.eklepser.thelevel.logic.world.level.Level;
//import com.eklepser.thelevel.logic.world.zone.LevelZone;
//
//import java.util.List;
//
//public class LevelCollisionManager {
//    private final GameArea level;
//    private final Executor executor;
//    private final List<Entity> entities;
//    private final List<Rectangle> walls;
//    private final List<LevelZone> zones;
//
//    public LevelCollisionManager(Level level) {
//        this.level = level;
//        executor = level.getScreen().getRootTable().getEditor().getExecutor();
//        entities = level.getEntities();
//        walls = level.getWalls();
//        zones = level.getZones();
//    }
//
//    public void updateLevel(Level level) {
//        Executor executor = level.getScreen().getRootTable().getEditor().getExecutor();
//        for (Entity entity : entities) {
//            for (LevelZone zone : zones) {
//                if (entity.getTargetRect().overlaps(zone.getRect())) {
//                    zone.onPossibleCollision();
//                }
//                if (entity.getRect().overlaps(zone.getRect())) {
//                    zone.onCollision(executor);
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
