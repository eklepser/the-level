package com.eklepser.thelevel.graphics.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tileset {
    public TextureRegion[][] tiles;

    public Tileset(String path, int tileWidth, int tileHeight) {
        Texture texture = new Texture(path);
        tiles = TextureRegion.split(texture, tileWidth, tileHeight);
    }

    public TextureRegion getTile(int index) {
        int rows = tiles.length;
        int cols = tiles[0].length;
        if (index >= rows * cols) return null;
        return tiles[index / cols][index % cols];
    }
}
