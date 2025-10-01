package com.eklepser.thelevel.graphics.render;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.eklepser.thelevel.util.Resources;

public class Zone {
    public int x;
    public int y;
    public String type;
    //public Map<String, Object> properties;

    public Zone() { }

    public Zone(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public static TextureRegion getTextureRegion(String type) {
        TextureRegion region;
        region = Resources.getTileset().getTile(1, 0);
        return region;
    }
}
