package com.eklepser.thelevel.logic.game.world;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.game.common.GameArea;
import com.eklepser.thelevel.logic.game.world.zone.LevelZone;
import com.eklepser.thelevel.logic.game.world.zone.WorldZone;

import java.util.ArrayList;
import java.util.List;

public class WorldLoader {
    public static List<Rectangle> loadWalls(GameArea area, String layerName) {
        MapLayer layer = loadLayer(area, layerName);
        List<Rectangle> objects = new ArrayList<>();
        if (layer == null) return objects;
        for (MapObject obj : layer.getObjects()) {
            if (obj instanceof RectangleMapObject rectObj) {
                Rectangle rect = rectObj.getRectangle();
                objects.add(rect);
                System.out.println("New collidable: " + rect);
            }
        }
        return objects;
    }

    public static List<WorldZone> loadZones(GameArea area, String layerName) {
        MapLayer layer = loadLayer(area, layerName);
        List<WorldZone> objects = new ArrayList<>();
        if (layer == null) return objects;
        for (MapObject obj : layer.getObjects()) {
            String type = obj.getProperties().get("type", "unknown", String.class);
            WorldZone newZone;
            if (obj instanceof RectangleMapObject rectObj)
            {
                newZone = parseZone(type, rectObj);
                objects.add(newZone);
                System.out.println("New zone: " + newZone);
            }
        }
        return objects;
    }

    private static MapLayer loadLayer(GameArea area, String layerName) {
        return area.getMap().getLayers().get(layerName);
    }

    private static WorldZone parseZone(String type, RectangleMapObject rectObj) {
        return switch (type) {
            case "level" -> LevelZone.from(rectObj);
            default -> {
                System.out.println("Unknown object type: " + type);
                yield null;
            }
        };
    }
}
