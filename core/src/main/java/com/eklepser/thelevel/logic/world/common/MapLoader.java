package com.eklepser.thelevel.logic.world.common;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Collidable;
import com.eklepser.thelevel.logic.world.collision.Wall;
import com.eklepser.thelevel.logic.world.collision.zone.*;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.world.World;

import java.util.List;

public class MapLoader {
    public static void loadCollidables(GameMap gameMap, List<Collidable> collidables) {
        for (MapLayer layer : gameMap.getMap().getLayers()) {
            for (MapObject obj : layer.getObjects()) {
                if (obj instanceof RectangleMapObject rectObj) {
                    Collidable collidable = parseRectObj(gameMap, rectObj);
                    collidables.add(collidable);
                }
            }
        }
    }

    private static Collidable parseRectObj(GameMap gameMap, RectangleMapObject rectObj) {
        String type = rectObj.getProperties().get("type", "wall", String.class);
        if (type.equals("wall")) return new Wall(rectObj.getRectangle());
        if (gameMap instanceof Level level) {
            Executor executor = level.getExecutor();
            return switch (type) {
                case "win" -> WinZone.from(rectObj, level.getScreen().getWinWindow(), executor);
                case "colored" -> ColoredZone.from(rectObj, executor);
                default -> {
                    System.out.println("Unknown collidable type: " + type);
                    yield null;
                }
            };
        }
        else if (gameMap instanceof World world) {
            return switch (type) {
                case "level" -> LevelZone.from(rectObj, world);
                default -> {
                    System.out.println("Unknown collidable type: " + type);
                    yield null;
                }
            };
        }
        return null;
    }
}
