package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.GameMap;
import com.eklepser.thelevel.logic.world.zone.Collidable;
import com.eklepser.thelevel.logic.world.zone.ColoredZone;
import com.eklepser.thelevel.logic.world.zone.LevelZone;
import com.eklepser.thelevel.logic.world.zone.WinZone;

import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    public static List<Collidable> loadCollidables(GameMap gameMap) {
        List<Collidable> collidables = new ArrayList<>();
        for (MapLayer layer : gameMap.getMap().getLayers()) {
            for (MapObject obj : layer.getObjects()) {
                if (obj instanceof RectangleMapObject rectObj) {
                    Rectangle rect = rectObj.getRectangle();
                    Collidable collidable = parseRectObj(gameMap, rectObj);
                    collidables.add(collidable);
                }
            }
        }
        return  collidables;
    }

    private static Collidable parseRectObj(GameMap gameMap, RectangleMapObject rectObj) {
        String type = rectObj.getProperties().get("type", "unknown", String.class);
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
