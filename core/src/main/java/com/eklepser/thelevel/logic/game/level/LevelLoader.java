package com.eklepser.thelevel.logic.game.level;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.game.common.GameArea;
import com.eklepser.thelevel.logic.game.level.zone.ColoredZone;
import com.eklepser.thelevel.logic.game.level.zone.PlateZone;
import com.eklepser.thelevel.logic.game.level.zone.LevelZone;
import com.eklepser.thelevel.logic.game.level.zone.WinZone;

import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    public static List<Rectangle> loadWalls(Level area, String layerName) {
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

    public static List<LevelZone> loadZones(Level area, String layerName) {
        MapLayer layer = loadLayer(area, layerName);
        List<LevelZone> objects = new ArrayList<>();
        if (layer == null) return objects;
        for (MapObject obj : layer.getObjects()) {
            String type = obj.getProperties().get("type", "unknown", String.class);
            LevelZone newZone;
            if (obj instanceof RectangleMapObject rectObj)
            {
                newZone = parseZone(type, rectObj, area);
                objects.add(newZone);
                System.out.println("New zone: " + newZone);
            }
        }
        return objects;
    }

    private static MapLayer loadLayer(Level area, String layerName) {
        return area.getMap().getLayers().get(layerName);
    }

    private static LevelZone parseZone(String type, RectangleMapObject rectObj, Level level) {
        return switch (type) {
            case "win" -> WinZone.from(rectObj, level.getScreen().getWinWindow());
            case "colored" -> ColoredZone.from(rectObj);
            case "plate" -> PlateZone.from(rectObj);
            default -> {
                System.out.println("Unknown object type: " + type);
                yield null;
            }
        };
    }
}
