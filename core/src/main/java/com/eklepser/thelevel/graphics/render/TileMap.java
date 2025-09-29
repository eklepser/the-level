package com.eklepser.thelevel.graphics.render;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class TileMap {
    public String name;
    public int width;
    public int height;
    public int[][] tiles;
    public int[][] collision;
    public List<Zone> zones;

    public Vector2 getStartPos() {
        for (Zone zone : zones) {
            if (zone.type.equals("start"))
                return new Vector2(zone.x, zone.y);
        }
        return null;
    }
}
