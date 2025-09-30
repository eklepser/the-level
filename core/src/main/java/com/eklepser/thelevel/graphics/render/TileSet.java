package com.eklepser.thelevel.graphics.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileSet {
    private final String name;
    private final TextureRegion[][] tiles;

    public TileSet(String path, String name, int tileWidth, int tileHeight) {
        tiles = TextureRegion.split(new Texture(path), tileWidth, tileHeight);
        this.name = name;
    }

    public TextureRegion getTile(int index) {
        int rows = tiles.length;
        int cols = tiles[0].length;
        if (index >= rows * cols) return null;
        return tiles[index / cols][index % cols];
    }

    public TextureRegion getTile(int x, int y) {
        return tiles[y][x];
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return tiles != null && tiles.length > 0 ? tiles[0].length : 0;
    }

    public int getHeight() {
        return tiles != null ? tiles.length : 0;
    }
}
