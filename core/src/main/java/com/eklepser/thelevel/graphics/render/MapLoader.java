package com.eklepser.thelevel.graphics.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class MapLoader {
    private static final Json json = new Json();

    public static TileMap load(String filePath) {
        FileHandle file = Gdx.files.internal(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Map file not found: " + filePath);
        }
        return json.fromJson(TileMap.class, file);
    }
}
