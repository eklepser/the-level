package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private final Level level;

    public LevelLoader(Level level) {
        this.level = level;
    }

    public List<Rectangle> loadLayer(String layerName) {
        MapLayer layer = level.getMap().getLayers().get(layerName);
        if (layer == null) {
            System.out.println("Layer " + layerName + " not found");
            return null;
        }
        List<Rectangle> objects = new ArrayList<>();
        for (MapObject object : layer.getObjects()) {
            if (object instanceof RectangleMapObject rectObj) {
                Rectangle rect = rectObj.getRectangle();
                objects.add(rect);
                System.out.println("New collidable: " + rect);
            }
        }
        return objects;
    }
}
