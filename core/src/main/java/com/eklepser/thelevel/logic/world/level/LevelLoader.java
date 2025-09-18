package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.zone.ColoredZone;
import com.eklepser.thelevel.logic.world.zone.PlateZone;
import com.eklepser.thelevel.logic.world.zone.WinZone;
import com.eklepser.thelevel.logic.world.zone.Zone;

import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private final Level level;

    public LevelLoader(Level level) {
        this.level = level;
    }

    public List<Rectangle> loadWalls(String layerName) {
        MapLayer layer = getLayer(layerName);
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

    public List<Zone> loadZones(String layerName) {
        MapLayer layer = getLayer(layerName);
        List<Zone> objects = new ArrayList<>();
        if (layer == null) return objects;
        for (MapObject obj : layer.getObjects()) {
            String type = obj.getProperties().get("type", "unknown", String.class);
            Zone newZone;
            if (obj instanceof RectangleMapObject rectObj)
            {
                newZone = parseZone(type, rectObj);
                objects.add(newZone);
                System.out.println("New zone: " + newZone);
            }
        }
        return objects;
    }

    private MapLayer getLayer(String layerName) {
        return level.getMap().getLayers().get(layerName);
    }

    private Zone parseZone(String type, RectangleMapObject rectObj) {
        return switch (type) {
            case "win" -> WinZone.from(rectObj, level.getPlayScreen().getWinWindow());
            case "colored" -> ColoredZone.from(rectObj);
            case "plate" -> PlateZone.from(rectObj);
            default -> {
                System.out.println("Unknown object type: " + type);
                yield null;
            }
        };
    }
}
